/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctt.controller;

import ctt.configuration.Configuration;
import firo.Controller;
import firo.Request;
import firo.Response;
import firo.Route;
import firo.RouteInfo;
import org.json.JSONException;
import org.json.JSONObject;
import ctt.render.RenderMain;
import ctt.response.DataResponse;
import ctt.response.ResponseCode;
import ctt.transport.NenTangThanhToanApi;
import ctt.utils.CommonUltil;
import ctt.utils.CookieUtil;
import static ctt.utils.CookieUtil.tokenCookieName;
import firo.utils.config.Config;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.apache.log4j.Logger;

/**
 *
 * @author dongvd3
 */
public class PaymentController extends Controller {

    public PaymentController() {
        rootPath = "/payment";
    }
     Logger logger = Logger.getLogger(PaymentController.class.getName());
    @RouteInfo(method = "post", path = "/urlredirect/get")
    public Route payment() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
//                String host = "https://pay.tphcm.gov.vn/paygate?";
                String returnUrl = Config.getParamString("config", "returnUrlPaymentCenter", "");
                JSONObject data = new JSONObject(request.body());
                if (data.has("returnUrl") && !data.getString("returnUrl").equals("null")) {
                    returnUrl = data.getString("returnUrl");
                }
                int dichvuId = data.getInt("dichvuId");
                int soTien = data.getInt("soTien");
                String orderId = data.getString("orderId");
                String orderInfo = data.getString("orderInfo");
                String partnerCode = data.getString("partnerCode");
                data = NenTangThanhToanApi.getInstance().getApi("api/paygate/getRedirectInfo?id=" + dichvuId + "&partnerCode=" + partnerCode);
                if (!data.getString("error_code").equals(ResponseCode.SUCCESSFUL)) {
                    return new DataResponse(data.getString("error_code"), data.getString("error_message"));
                }

                data = data.getJSONObject("data");
                partnerCode = data.getString("partnerCode");
                String accessKey = data.getString("accessKey");
                String orderType = data.getString("orderType");
                long requestCode = System.currentTimeMillis();
//                String apiUpdateInfo = data.getString("apiUpdateInfo");
                String notifiUrl = Config.getParamString("config", "notifiUrlPaymentCenter", "")
                        + partnerCode + "_" + requestCode + "_" + orderId + "_" + dichvuId;

                StringBuilder redirectPath = new StringBuilder(Config.getParamString("config", "redirectUrl", "") + "paygate");
                redirectPath.append("?amount=");
                redirectPath.append(soTien);
                redirectPath.append("&partnerCode=");
                redirectPath.append(partnerCode);
                redirectPath.append("&accessKey=");
                redirectPath.append(accessKey);
                redirectPath.append("&requestCode=");
                redirectPath.append(requestCode);
                redirectPath.append("&orderId=");
                redirectPath.append(orderId);
                redirectPath.append("&notifiUrl=");
                redirectPath.append(notifiUrl);
                redirectPath.append("&ipAddress=");
                redirectPath.append(request.ip());
//                redirectPath.append(request.ip());
                redirectPath.append("&orderType=");
                redirectPath.append(orderType);
                redirectPath.append("&orderInfo=");
                redirectPath.append(URLEncoder.encode(orderInfo, StandardCharsets.UTF_8.toString()));
                redirectPath.append("&returnUrl=");
                redirectPath.append(returnUrl);
                redirectPath.append("&dichvuId=");
                redirectPath.append(dichvuId);
                String checksum = CommonUltil.convertSha256(redirectPath.toString());
                redirectPath.append("&checksum=");
                redirectPath.append(checksum);

                return new DataResponse(redirectPath);
            } catch (JSONException ex) {
                logger.error(Configuration.SERVICE_NAME+": " +ex.getStackTrace()[0].toString()+":"+ex.toString());
                return new DataResponse("-1", ex.getMessage());
            }
        };
    }

    @RouteInfo(method = "get", path = "/detailPayment/result")
    public Route renderResult() {
        return (Request request, Response response) -> {
            try {
                response.header("Access-Control-Allow-Origin", "*");
                response.header("Access-Control-Allow-Credentials", "true");
                response.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
                String maHoaDon = request.queryParams("orderId");
                String errorCode = request.queryParams("errorCode");
                int dichvuId = Config.getParamInt("config", "dichVuId");
                String token = CookieUtil.getValue(request, tokenCookieName);
                if (token != null && !token.isEmpty()) {
                    String userName = CookieUtil.getValueTokenKey(token, "username");
                    JSONObject updateInfoTT = new JSONObject();
                    updateInfoTT.put("tenDangNhap", userName);
                    NenTangThanhToanApi.getInstance().putApi("LichSuThanhToan/" + request.queryParams("transactionId"), updateInfoTT);
                }

                if (errorCode.equals("00")) {
                    return RenderMain.getInstance().renderPage(request, response, "paymentSuccess.ftl");
                }
                return RenderMain.getInstance().renderPage(request, response, "paymentFalse.ftl");
            } catch (Exception ex) {
                logger.error(Configuration.SERVICE_NAME+": " +ex.getStackTrace()[0].toString()+":"+ex.toString());
                return RenderMain.getInstance().renderPage(request, response, "404.ftl");
            }
        };
    }
}
