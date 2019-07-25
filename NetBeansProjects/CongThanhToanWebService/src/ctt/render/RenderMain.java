/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ctt.render;

import ctt.service.ServletUtil;
import ctt.transport.SSOAPI;
import ctt.utils.CookieUtil;
import static ctt.utils.CookieUtil.tokenCookieName;
import java.io.IOException;
import java.util.HashMap;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import firo.Request;
import firo.Response;
import firo.utils.config.Config;
import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author namdv
 */
public class RenderMain extends RenderEngine {

    private static final Logger log = Log.getLogger(RenderMain.class);
    private static RenderMain _instance = null;

    public static RenderMain getInstance() {
        if (_instance == null) {
            _instance = new RenderMain();
        }
        return _instance;
    }

    public String renderSearch(Request request, Response response, HashMap<String, String> params) throws IOException {
        String content = "";
        Map<String, Object> attributes = new HashMap<>();
        try {
            attributes.put("static_url", Config.getParamString("config", "static_url", ""));
            attributes.put("tenDichVu", params.get("tenDichVu"));
            attributes.put("anhDichVu", params.get("anhDichVu"));
            attributes.put("inputDichVu", params.get("inputDichVu"));
            attributes.put("header", renderHeader(request));
            attributes.put("menu", renderMenu());
            content = RenderEngine.getInstance().render(attributes, "searchOrder.ftl");

        } catch (Exception ex) {
            ex.printStackTrace();
            log.warn("render renderHome", ex);
        }
        return content;
    }

    private String renderHeader(Request request) {
        String content = "";
        Map<String, Object> attributes = new HashMap<>();
        try {
            attributes.put("static_url", Config.getParamString("config", "static_url", ""));
            String urlSingup =  Config.getParamString("CongSOO", "sso_signup_url", "");
            String urlLogin =  Config.getParamString("CongSOO", "sso_login_url", "");
            attributes.put("sso_login_url", Config.getParamString("CongSOO", "sso_login_url", ""));
            String htmlUser = "<ul><li>" +
"                                                    <a class=\"p-2 text-dark cd-signin\" href=\""+urlLogin+"\">Đăng nhập</a>                                \n" +
"                                                </li>\n" +
"                                                <li>\n" +
"                                                    <a class=\"cd-signup\" href=\""+urlSingup+"\">Đăng kí</a>\n" +
"                                                </li>                        \n" +
"                                            </ul>";
            String token = CookieUtil.getValue(request, tokenCookieName);
            if(token != null && request.queryMap().hasKey("token")){
                token = request.queryParams("token");
            }
            if(token != null && !token.equals("")){
                JSONObject data = new JSONObject();
                data.put("sessionKey", token);
                data.put("partnerCode", Config.getParamString("CongSOO", "partnerCode", ""));
                data.put("accessKey", Config.getParamString("CongSOO", "accessKey", ""));
                JSONObject responeAPI = SSOAPI.getInstance().postApi("/user/validateSession", data);
                if (responeAPI.getString("error_code").equals("SUCCESSFUL")) {
                    data = responeAPI.getJSONObject("data");
                    htmlUser = "<div class=\"user-login\">\n"
                        + "                                            <div style=\"float:right;margin-bottom: -13px;\" class=\"dropdown show\">\n"
                        + "                                            <img class=\"avata-login\" src=\"https://hcmpass.tphcm.gov.vn/static/assets/img/avata.png\">\n"
                        + "                                            <a class=\"btn btn-secondary dropdown-toggle\" style=\"color: #4e555b !important;\" href=\""+Config.getParamString("CongSOO", "profile_url", "")+"?id=" + data.getInt("id") + "\" role=\"button\" id=\"dropdownMenuLink\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n"
                        + "                                              " + data.getString("tenDangNhap") + "\n"
                        + "                                            </a>\n"
                        + "                                            \n"
                        + "                                            <div class=\"dropdown-menu\" aria-labelledby=\"dropdownMenuLink\">\n"
                        + "                                              <a class=\"dropdown-item\" href=\""+Config.getParamString("CongSOO", "profile_url", "")+"\">Hồ sơ của tôi</a>\n"
                        + "                                              </br>\n"
                        + "                                              <a class=\"dropdown-item\" href=\""+Config.getParamString("CongSOO", "logout_url", "")+"\">Thoát</a>\n"
                        + "                                            </div>\n"
                        + "                                          </div>\n"
                        + "                                        </div>";
                }
            }
            attributes.put("html_user", htmlUser);
            content = RenderEngine.getInstance().render(attributes, "header.ftl");

        } catch (Exception ex) {
            ex.printStackTrace();
            log.warn("render header", ex);
        }
        return content;
    }

    private String renderMenu() {
        String content = "";
        Map<String, Object> attributes = new HashMap<>();
        try {
            attributes.put("static_url", Config.getParamString("config", "static_url", ""));
            content = RenderEngine.getInstance().render(attributes, "menu.ftl");

        } catch (Exception ex) {
            ex.printStackTrace();
            log.warn("render menu", ex);
        }
        return content;
    }
    
    private String renderFooter() {
        String content = "";
        Map<String, Object> attributes = new HashMap<>();
        try {
            attributes.put("static_url", Config.getParamString("config", "static_url", ""));
            content = RenderEngine.getInstance().render(attributes, "footer.ftl");

        } catch (Exception ex) {
            ex.printStackTrace();
            log.warn("render footer", ex);
        }
        return content;
    }

    public String renderPage(Request request, Response response, String page) {
        String content = "";
       
        if(request.queryMap().hasKey("token")){
             String token = request.queryParams("token");
            CookieUtil.clear(response, tokenCookieName);
            CookieUtil.create(response, tokenCookieName, token, true, 86400);
        }
        Map<String, Object> attributes = new HashMap<>();
        try {
            attributes.put("header", renderHeader(request));
            attributes.put("menu", renderMenu());
            attributes.put("footer", renderFooter());
            attributes.put("static_url", Config.getParamString("config", "static_url", ""));
            content = RenderEngine.getInstance().render(attributes, page);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.warn("render " + page, ex);
        }
        return content;
    }

}
