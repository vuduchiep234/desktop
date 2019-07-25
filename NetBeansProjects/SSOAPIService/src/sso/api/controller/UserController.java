/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sso.api.controller;

import firo.Controller;
import firo.Request;
import firo.Response;
import firo.Route;
import firo.RouteInfo;
import org.json.JSONObject;
import sso.api.conector.mongodb.ExceptionsCollection;
import sso.api.configuration.Configuration;
import sso.api.model.DataResponse;
import sso.api.service.UserService;

/**
 *
 * @author anbq
 */
public class UserController extends Controller {


    public UserController() {
        rootPath = "/user";
    }

    @RouteInfo(method = "post", path = "/login")
    public Route login() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                String sJson = request.body();
                JSONObject data = new JSONObject(sJson);
                String tenDangNhap = data.getString("tenDangNhap");
                String matKhau = data.getString("matKhau");
                
                return UserService.getInstance().logIn(tenDangNhap, matKhau);
            } catch (Exception ex) {
                ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());

                ex.printStackTrace();
                return new DataResponse("-1", ex.getMessage());
            }
        };
    }
    @RouteInfo(method = "post", path = "/changPassword")
    public Route changPassword() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                String sJson = request.body();
                JSONObject data = new JSONObject(sJson);
                
                String password = data.getString("oldPassword");
                String newPassword = data.getString("newPassword");
                int uid = data.getInt("id");
                return UserService.getInstance().changePassword(uid,password, newPassword);
            } catch (Exception ex) {
                ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());

                ex.printStackTrace();
                return new DataResponse("-1", ex.getMessage());
            }
        };
    }
    
    @RouteInfo(method = "post", path = "/checkSyncAccount")
    public Route checkSyncAccount() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                String sJson = request.body();
                JSONObject data = new JSONObject(sJson);
                
                String soGiayTo = data.getString("soGiayTo");
                String hoVaTen = data.getString("hoVaTen");
                String ngaySinh = data.getString("ngaySinh");
                return UserService.getInstance().checkSyncAccount(soGiayTo,hoVaTen, ngaySinh);
            } catch (Exception ex) {
                ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());

                ex.printStackTrace();
                return new DataResponse("-1", ex.getMessage());
            }
        };
    }
    @RouteInfo(method = "post", path = "/syncAccount")
    public Route syncAccount() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                String sJson = request.body();
                JSONObject data = new JSONObject(sJson);
                
                String soGiayTo = data.getString("soGiayTo");
                String hoVaTen = data.getString("hoVaTen");
                String ngaySinh = data.getString("ngaySinh");
                String tenDangNhap = data.getString("tenDangNhap");
                String email = data.getString("email");
                String matKhau = data.getString("matKhau");
                return UserService.getInstance().syncAccount(soGiayTo,hoVaTen, ngaySinh,tenDangNhap,email,matKhau);
            } catch (Exception ex) {
                ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());

                ex.printStackTrace();
                return new DataResponse("-1", ex.getMessage());
            }
        };
    }
    @RouteInfo(method = "post", path = "/signup")
    public Route signUp() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                String sJson = request.body();
                return UserService.getInstance().signUp(sJson);
            } catch (Exception ex) {
                
                ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());

                ex.printStackTrace();
                return new DataResponse("-1", ex.getMessage());
            } 
        };
    }
    @RouteInfo(method = "post", path = "/validateSession")
    public Route checkSession() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                String sJson = request.body();
                JSONObject json = new JSONObject(sJson);
                String sessionKey = json.getString("sessionKey");
                return UserService.getInstance().validateSession(sessionKey);
            } catch (Exception ex) {
                
                ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());

                ex.printStackTrace();
                return new DataResponse("-1", ex.getMessage());
            } 
        };
    }
    @RouteInfo(method = "get", path = "/active/:email")
    public Route getObject() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                String email = request.params(":email");

                return UserService.getInstance().activeAccount(email);
            } catch (Exception ex) {
                ExceptionsCollection.getInstance().addException(
                        Configuration.SERVICE_NAME,
                        ex.getStackTrace()[0].toString(),
                        ex.toString());

                ex.printStackTrace();
                return new DataResponse("-1", ex.getMessage());
            }
        };
    }
}

