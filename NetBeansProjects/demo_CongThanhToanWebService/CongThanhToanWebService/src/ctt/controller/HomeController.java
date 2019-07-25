/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctt.controller;

import firo.Controller;
import firo.Request;
import firo.Response;
import firo.Route;
import firo.RouteInfo;
import ctt.render.RenderMain;

public class HomeController extends Controller {

    public HomeController() {
    }

    @RouteInfo(method = "get", path = "/")
    public Route renderHome() {
        return (Request request, Response response) -> {
            return RenderMain.getInstance().renderPage(request,response, "home.ftl");
        };
    }

    @RouteInfo(method = "get", path = "/payment/history")
    public Route renderPaymentHistory() {
        return (Request request, Response response) -> {
            return RenderMain.getInstance().renderPage(request,response, "transactionHistory.ftl");
        };
    }
}
