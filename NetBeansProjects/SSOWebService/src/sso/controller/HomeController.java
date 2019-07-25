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
import sso.render.RenderMain;

public class HomeController extends Controller {

    public static final String _className = "=============HomeController";

    public HomeController() {
    }

    @RouteInfo(method = "get", path = "/")
    public Route renderHome() {
        return (Request request, Response response) -> {
            System.out.println("Render Home");
            return RenderMain.getInstance().renderHome(request, response);
        };
    }
    @RouteInfo(method = "get", path = "/profile")
    public Route renderProfile() {
        return (Request request, Response response) -> {
            System.out.println("Render Profile");
            return RenderMain.getInstance().renderProfile(request, response);
        };
    }
    @RouteInfo(method = "get", path = "/updateProfile")
    public Route renderUpdateProfile() {
        return (Request request, Response response) -> {
            System.out.println("Render Profile");
            return RenderMain.getInstance().renderUpdateProfile(request, response);
        };
    }
    @RouteInfo(method = "get", path = "/changpassword")
    public Route renderChangpassword() {
        return (Request request, Response response) -> {
            System.out.println("Render Profile");
            return RenderMain.getInstance().renderChangpassword(request, response);
        };
    }
    @RouteInfo(method = "get", path = "/syncAccount")
    public Route renderSyncAccount() {
        return (Request request, Response response) -> {
            System.out.println("Render Profile");
            return RenderMain.getInstance().renderSyncAccount(request, response);
        };
    }
    @RouteInfo(method = "get", path = "/updateAccount")
    public Route renderUpdateAccount() {
        return (Request request, Response response) -> {
            System.out.println("Render Profile");
            return RenderMain.getInstance().renderUpdateAccount(request, response);
        };
    }
    @RouteInfo(method = "get", path = "/validateToken")
    public Route validateToken() {
        return (Request request, Response response) -> {
            System.out.println("Render validateToken");
            return RenderMain.getInstance().validateToken(request, response);
        };
    }
    @RouteInfo(method = "get", path = "/login")
    public Route renderLogin() {
        return (Request request, Response response) -> {
            System.out.println("Render Login");
            return RenderMain.getInstance().renderLogin(request, response);
        };
    }
    @RouteInfo(method = "get", path = "/register")
    public Route renderRegister(){
        return (Request request, Response response) -> {
            System.out.println("Render Register");
            return RenderMain.getInstance().renderRegister(request, response);
        };
    }
}
