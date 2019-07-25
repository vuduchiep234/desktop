/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sso.controller;

import firo.Controller;
import firo.Request;
import firo.Response;
import firo.Route;
import firo.RouteInfo;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import sso.response.DataResponse;
import sso.transport.SSOAPIService;
import org.json.JSONObject;
import sso.transport.SendEmailService;
import sso.utils.CookieUtil;

public class UserController extends Controller {

    public static final String _className = "=============UserController";
    public static final String tokenCookieName = "SSO-TOKEN";
    public static final HashMap<String, String> mapsToken = new HashMap();
    public UserController() {
        rootPath = "/user";
    }

    @RouteInfo(method = "post", path = "/login")
    public Route logIn() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                CookieUtil.clear(response, tokenCookieName);
                String json = request.body();
                JSONObject jSonData = new JSONObject(json);
                String username = jSonData.getString("username");
                String password = jSonData.getString("password");
                JSONObject data = new JSONObject();
                data.put("tenDangNhap", username);
                data.put("matKhau", password);
                JSONObject responeAPI = SSOAPIService.getInstance().postApi("user/login", data);
                if (responeAPI.getString("error_code").equals("NO_ACTIVE")){
                    return new DataResponse(99,"Tai khoan chua active");
                }else if (!responeAPI.getString("error_code").equals("SUCCESSFUL")) {
                    return DataResponse.AUTHENTICATION_FAIL;
                }
                
                
                String sessionKey = responeAPI.getJSONObject("data").getString("sessionKey");
                CookieUtil.create(response, tokenCookieName, sessionKey, true, 86400);
                System.out.println("res api" + responeAPI.toString());
                return DataResponse.SUCCESS;
            } catch (Exception ex) {

                ex.printStackTrace();
                return new DataResponse(-1, ex.getMessage());
            }
        };
    }

    @RouteInfo(method = "post", path = "/updateProfile")
    public Route updateProfile() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                String json = request.body();
                JSONObject jSonData = new JSONObject(json);
                String token = CookieUtil.getValue(request, tokenCookieName);
                if (token == null || token.equals("null")) {
                    return DataResponse.AUTHENTICATION_FAIL;
                }
                JSONObject data = new JSONObject();
                data.put("sessionKey", token);
                JSONObject responeAPI = SSOAPIService.getInstance().postApi("user/validateSession", data);
                if (!responeAPI.getString("error_code").equals("SUCCESSFUL")) {
                    return DataResponse.AUTHENTICATION_FAIL;
                }
                JSONObject userInfo = responeAPI.getJSONObject("data");
                jSonData.put("tenDangNhap", userInfo.getString("tenDangNhap"));
                JSONObject responeUpdateUser = SSOAPIService.getInstance().putApi("user/" + userInfo.getInt("id"), jSonData);
                if (!responeUpdateUser.getString("error_code").equals("SUCCESSFUL")) {
                    return new DataResponse(-1, responeUpdateUser.getString("error_message"));
                }

                return DataResponse.SUCCESS;
            } catch (Exception ex) {

                ex.printStackTrace();
                return new DataResponse(-1, ex.getMessage());
            }
        };
    }
    @RouteInfo(method = "post", path = "/changePassword")
        public Route changePassword() {
            return (Request request, Response response) -> {
                try {
                    response.header("Content-Type", "application/json");
                    String json = request.body();
                    JSONObject jSonData = new JSONObject(json);
                    String token = CookieUtil.getValue(request, tokenCookieName);
                    if (token == null || token.equals("null")) {
                        return DataResponse.AUTHENTICATION_FAIL;
                    }
                    JSONObject data = new JSONObject();
                    data.put("sessionKey", token);
                    JSONObject responeAPI = SSOAPIService.getInstance().postApi("user/validateSession", data);
                    if (!responeAPI.getString("error_code").equals("SUCCESSFUL")) {
                        return DataResponse.AUTHENTICATION_FAIL;
                    }
                    JSONObject userInfo = responeAPI.getJSONObject("data");
                    jSonData.put("id",userInfo.getInt("id"));
                    JSONObject responeUpdateUser = SSOAPIService.getInstance().postApi("user/changPassword", jSonData);
                    if (!responeUpdateUser.getString("error_code").equals("SUCCESSFUL")) {
                        return new DataResponse(-1, responeUpdateUser.getString("error_message"));
                    }

                    return DataResponse.SUCCESS;
                } catch (Exception ex) {

                    ex.printStackTrace();
                    return new DataResponse(-1, ex.getMessage());
                }
            };
        }
        @RouteInfo(method = "post", path = "/checkSyncAccount")
        public Route checkSyncAccount() {
            return (Request request, Response response) -> {
                try {
                    response.header("Content-Type", "application/json");
                    String json = request.body();
                    JSONObject jSonData = new JSONObject(json);
                    JSONObject responeUpdateUser = SSOAPIService.getInstance().postApi("user/checkSyncAccount", jSonData);
                    
                    String resCode = responeUpdateUser.getString("error_code");
                    if (resCode.equals("SUCCESSFUL")) {
                        return DataResponse.SUCCESS;
                        
                    }else if(resCode.equals("2")){
                        return new DataResponse(2, responeUpdateUser.getString("error_message"));
                    }
                    else if(resCode.equals("3")){
                        return new DataResponse(3, responeUpdateUser.getString("error_message"));
                    }else{
                        return new DataResponse(-1, responeUpdateUser.getString("error_message"));
                    }
                } catch (Exception ex) {

                    ex.printStackTrace();
                    return new DataResponse(-1, ex.getMessage());
                }
            };
        }
        @RouteInfo(method = "post", path = "/syncAccount")
        public Route syncAccount() {
            return (Request request, Response response) -> {
                try {
                    response.header("Content-Type", "application/json");
                    String json = request.body();
                    JSONObject jSonData = new JSONObject(json);
                    JSONObject responeUpdateUser = SSOAPIService.getInstance().postApi("user/syncAccount", jSonData);
                    
                    String resCode = responeUpdateUser.getString("error_code");
                    if (resCode.equals("SUCCESSFUL")) {
                        String email = responeUpdateUser.getJSONObject("data").getString("email");
                        String tokenKey = makeEmailHash(email+"agwfacaANBQcascsa");
                        String token = "http://103.9.0.215/hcm-sso/validateToken?type=active&email="+email+"&token="+tokenKey;
                        JSONObject dataEmail = new JSONObject();
                        dataEmail.put("recipient", email);
                        dataEmail.put("subject", "Kích hoạt tài khoản Cổng đăng nhập tập trung TP. Hồ Chí Minh");
                        dataEmail.put("body", " <p>Ban vui long click voa duong dan nay de kich hoat tai khoan:</p><a href= \""+token+"\"> Link </a>");
                        JSONObject resEmail = SendEmailService.getInstance().postApi("sendmail/sendMail", dataEmail);
                        if (!resEmail.getString("error_code").equals("SUCCESSFUL")) {
                            return new DataResponse(-1, resEmail.getString("error_message"));
                        }
                        mapsToken.put(email, tokenKey);
                        return DataResponse.SUCCESS;
                        
                    }else{
                        return new DataResponse(-1, responeUpdateUser.getString("error_message"));
                    }
                } catch (Exception ex) {

                    ex.printStackTrace();
                    return new DataResponse(-1, ex.getMessage());
                }
            };
        }
        
    @RouteInfo(method = "get", path = "/logout")
    public Route logOut() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                String token = CookieUtil.getValue(request, tokenCookieName);
                CookieUtil.clear(response, tokenCookieName);
                JSONObject data = new JSONObject();
                data.put("sessionKey", token);
                SSOAPIService.getInstance().deleteByconditionApi("SessionUser", data);
                response.redirect("/");
                return DataResponse.SUCCESS;
            } catch (Exception ex) {

                ex.printStackTrace();
                return new DataResponse(-1, ex.getMessage());
            }
        };
    }

    @RouteInfo(method = "post", path = "/signup")
    public Route signUp() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                String json = request.body();
                JSONObject jSonData = new JSONObject(json);
                String tenDangNhap = jSonData.getString("username");
                String soDienThoai = jSonData.getString("phone");
                String matKhau = jSonData.getString("password");
                String email = jSonData.getString("email");
                JSONObject data = new JSONObject();
                data.put("tenDangNhap", tenDangNhap);
                data.put("soDienThoai", soDienThoai);
                data.put("matKhau", matKhau);
                data.put("email", email);

                JSONObject responeAPI = SSOAPIService.getInstance().postApi("user/signup", data);
                System.out.println("res api" + responeAPI.toString());
                if (!responeAPI.getString("error_code").equals("SUCCESSFUL")) {
                    return new DataResponse(-1, responeAPI.getString("error_message"));
                }
                String tokenKey = makeEmailHash(email+"agwfacaANBQcascsa");
                String token = "http://120.72.116.139/validateToken?type=active&email="+email+"&token="+tokenKey;
                JSONObject dataEmail = new JSONObject();
                dataEmail.put("recipient", email);
                dataEmail.put("subject", "Kích hoạt tài khoản Cổng đăng nhập tập trung TP. Hồ Chí Minh");
                dataEmail.put("body", " <p>Ban vui long click voa duong dan nay de kich hoat tai khoan:</p><a href= \""+token+"\"> Link </a>");
                JSONObject resEmail = SendEmailService.getInstance().postApi("sendmail/sendMail", dataEmail);
                if (!resEmail.getString("error_code").equals("SUCCESSFUL")) {
                    return new DataResponse(-1, resEmail.getString("error_message"));
                }
                mapsToken.put(email, tokenKey);
                return DataResponse.SUCCESS;
            } catch (Exception ex) {

                ex.printStackTrace();
                return new DataResponse(-1, ex.getMessage());
            }
        };
    }
    private String makeEmailHash(String email) {
        try {
            String saltedAndHashed = email ;
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(saltedAndHashed.getBytes());
            byte hashedBytes[] = (new String(digest.digest(), "UTF-8")).getBytes();
            String result =  Base64.getEncoder().encodeToString(hashedBytes);
            return result.replace("+", "a");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 is not available", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 unavailable?  Not a chance", e);
        }
    }
}
