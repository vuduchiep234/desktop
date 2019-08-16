/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nttt.api.controller;

import nttt.api.configuration.ConfigHelper;
import nttt.api.model.DataResponse;
import nttt.api.model.ResponseCode;
import firo.Controller;
import firo.Request;
import firo.Response;
import firo.Route;
import firo.RouteInfo;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import nttt.api.configuration.Configuration;
import nttt.api.service.PaygateService;
import org.apache.log4j.Logger;
import org.json.JSONObject;

/**
 *
 * @author ASUS
 */
public class PaygateController extends Controller {

    public PaygateController() {
        rootPath = Configuration.PREFIX + "/paygate";
    }
    Logger logger = Logger.getLogger(PaygateController.class.getName());
    @RouteInfo(method = "post", path = "/payment")
    public Route payment() {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                try {

                    response.header("Content-Type", "application/json");
                    response.header("Access-Control-Allow-Origin", "*");
                    response.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
                    String sJson = request.body();
                    JSONObject data = new JSONObject(sJson);
                    String amount = data.getString("amount");
                    String orderInfo = URLDecoder.decode(data.getString("orderInfo"),StandardCharsets.US_ASCII.toString());
                    String urlReturn = data.getString("urlReturn");

                    StringBuilder query = new StringBuilder();

                    query.append(URLEncoder.encode("amount", StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(amount, StandardCharsets.US_ASCII.toString()));
                    query.append('&');
                    query.append(URLEncoder.encode("orderInfo", StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(orderInfo, StandardCharsets.US_ASCII.toString()));
                    query.append('&');
                    query.append(URLEncoder.encode("urlReturn", StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(urlReturn, StandardCharsets.US_ASCII.toString()));

                    String queryUrl = query.toString();

                    String paygateUrl = ConfigHelper.getParamString("config", "cong_thanh_toan_web_url", "http://127.0.0.1:2005/paygate");

                    String paymentUrl = paygateUrl + "?" + queryUrl;

                    return new DataResponse(ResponseCode.SUCCESSFUL, ResponseCode.SUCCESSFUL, paymentUrl);
                } catch (Exception ex) {
                    logger.error(Configuration.SERVICE_NAME+": " +ex.getStackTrace()[0].toString()+":"+ex.toString());
                    return new DataResponse("-1", ex.getMessage());
                }
            }
        };
    }

    @RouteInfo(method = "get", path = "/getHistory")
    public Route getHistory() {
        return (request, response) -> PaygateService.getInstance().getHistory(request, response);
    }

    @RouteInfo(method = "get", path = "/getRedirectInfo")
    public Route getRedirectInfo() {
        return (request, response) -> PaygateService.getInstance().getRedirectInfo(request, response);
    }
}
