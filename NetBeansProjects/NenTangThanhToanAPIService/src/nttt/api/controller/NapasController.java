/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nttt.api.controller;

import firo.Controller;
import firo.Request;
import firo.Response;
import firo.Route;
import firo.RouteInfo;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import nttt.api.configuration.ConfigNapas;
import nttt.api.configuration.Configuration;
import static nttt.api.controller.VNPAYController.getThongTinThanhToan;
import nttt.api.model.DataResponse;
import nttt.api.model.ResponseCode;
import nttt.api.model.ThongTinThanhToan;
import org.apache.log4j.Logger;
import org.json.JSONObject;

/**
 *
 * @author hung
 */
public class NapasController extends Controller {

    public NapasController() {
        rootPath = "/napas";
    }
    Logger logger = Logger.getLogger(NapasController.class.getName());
    public static int congThanhToanVNpayId = 1;
    public static int congThanhToanMomoId = 2;
    public static int congThanhToanNapasId = 3;
    public static int congThanhToanVetelId = 4;

    @RouteInfo(method = "post", path = "/payment")
    public Route payment() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                response.header("Access-Control-Allow-Origin", "*");
                response.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
                String sJson = request.body();
                String ip = InetAddress.getLocalHost().getHostAddress();

                JSONObject data = new JSONObject(sJson);

                String partnerCode = data.getString("partnerCode");
                String requestCode = data.getString("requestCode");
                String orderType = data.getString("orderType");
                String orderId = data.getString("orderId");
                String vpc_Amount = data.getString("vpc_Amount");
                String vpc_OrderInfo = data.getString("vpc_OrderInfo");
                String vpc_BackURL = data.getString("vpc_BackURL");
                String vpc_ReturnURL = data.getString("vpc_ReturnURL");

                Date today = new Date();
                String vpc_MerchTxnRef = data.getString("orderId");

                DataResponse resThongTinThanhToan = getThongTinThanhToan(partnerCode, congThanhToanNapasId);
                if (resThongTinThanhToan.getError().equals("-1") || resThongTinThanhToan.getData() == null) {
                    return DataResponse.FAILED;
                }
                ThongTinThanhToan thongTinThanhToan = (ThongTinThanhToan) resThongTinThanhToan.getData();

                Map<String, String> vpc_Params = new HashMap<>();
                vpc_Params.put("vpc_Merchant", thongTinThanhToan.getPayPartnerCode());
                vpc_Params.put("vpc_AccessCode", thongTinThanhToan.getPayAccessKey());
                vpc_Params.put("vpc_Version", "2.0");
                vpc_Params.put("vpc_Command", "pay");
                vpc_Params.put("vpc_Amount", vpc_Amount);
                vpc_Params.put("vpc_TicketNo", ip);
                vpc_Params.put("vpc_Currency", "VND");
                vpc_Params.put("vpc_Locale", "vn");
                vpc_Params.put("vpc_OrderInfo", vpc_OrderInfo);
                vpc_Params.put("vpc_BackURL", vpc_BackURL);
                vpc_Params.put("vpc_ReturnURL", vpc_ReturnURL);
                vpc_Params.put("vpc_MerchTxnRef", vpc_MerchTxnRef + "-" + String.valueOf(today.getTime()).substring(5));

                List fieldNames = new ArrayList(vpc_Params.keySet());
                Collections.sort(fieldNames);
                StringBuilder hashData = new StringBuilder();
                StringBuilder query = new StringBuilder();
                Iterator itr = fieldNames.iterator();
                while (itr.hasNext()) {
                    String fieldName = (String) itr.next();
                    String fieldValue = (String) vpc_Params.get(fieldName);
                    if ((fieldValue != null) && (fieldValue.length() > 0)) {
                        //Build hash data
                        hashData.append(fieldName);
                        hashData.append('=');
                        hashData.append(fieldValue);
                        //Build query
                        query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                        query.append('=');
                        query.append(URLEncoder.encode(fieldValue, StandardCharsets.UTF_8.toString()));

                        if (itr.hasNext()) {
                            query.append('&');
                            hashData.append('&');
                        }
                    }
                }

                String queryUrl = query.toString();
                String vpc_SecureHash = ConfigNapas.md5(vpc_Params);
                queryUrl += "&vpc_SecureHash=" + vpc_SecureHash;
                String paymentUrl = ConfigNapas.vpc_PayUrl + "?" + queryUrl;

                return new DataResponse(ResponseCode.SUCCESSFUL, ResponseCode.SUCCESSFUL, paymentUrl);

            } catch (Exception ex) {
                logger.error(Configuration.SERVICE_NAME+": " +ex.getStackTrace()[0].toString()+":"+ex.toString());
                return new DataResponse("-1", ex.getMessage());
            }
        };
    }

    @RouteInfo(method = "post", path = "/refund")
    public Route refund() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                response.header("Access-Control-Allow-Origin", "*");
                response.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
                String sJson = request.body();
                JSONObject data = new JSONObject(sJson);

                String partnerCode = data.getString("partnerCode");
                String vpc_User = data.getString("vpc_User");
                String vpc_Password = data.getString("vpc_Password");
                String vpc_TransactionNo = data.getString("vpc_TransacionNo");
                String vpc_Amount = data.getString("vpc_Amount");
                String vpc_MerchTxnRef = data.getString("orderId");

                DataResponse resThongTinThanhToan = getThongTinThanhToan(partnerCode, congThanhToanNapasId);
                if (resThongTinThanhToan.getError().equals("-1") || resThongTinThanhToan.getData() == null) {
                    return DataResponse.FAILED;
                }
                ThongTinThanhToan thongTinThanhToan = (ThongTinThanhToan) resThongTinThanhToan.getData();

                Map<String, String> vpc_Params = new HashMap<>();
                vpc_Params.put("vpc_Merchant", thongTinThanhToan.getPayPartnerCode());
                vpc_Params.put("vpc_AccessCode", thongTinThanhToan.getPayAccessKey());
                vpc_Params.put("vpc_Version", "2.0");
                vpc_Params.put("vpc_Command", "refund");
                vpc_Params.put("vpc_Amount", vpc_Amount);
                vpc_Params.put("vpc_CurrencyCode", "VND");
                vpc_Params.put("vpc_User", vpc_User);
                vpc_Params.put("vpc_Password", vpc_Password);
                vpc_Params.put("vpc_TransactionNo", vpc_TransactionNo);
                vpc_Params.put("vpc_MerchTxnRef", vpc_MerchTxnRef);

                List fieldNames = new ArrayList(vpc_Params.keySet());
                Collections.sort(fieldNames);
                StringBuilder hashData = new StringBuilder();
                StringBuilder query = new StringBuilder();
                Iterator itr = fieldNames.iterator();
                while (itr.hasNext()) {
                    String fieldName = (String) itr.next();
                    String fieldValue = (String) vpc_Params.get(fieldName);
                    if ((fieldValue != null) && (fieldValue.length() > 0)) {
                        //Build hash data
                        hashData.append(fieldName);
                        hashData.append('=');
                        hashData.append(fieldValue);
                        //Build query
                        query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                        query.append('=');
                        query.append(URLEncoder.encode(fieldValue, StandardCharsets.UTF_8.toString()));

                        if (itr.hasNext()) {
                            query.append('&');
                            hashData.append('&');
                        }
                    }
                }

                String queryUrl = query.toString();
                String vpc_SecureHash = ConfigNapas.md5(vpc_Params);
                queryUrl += "&vpc_SecureHash=" + vpc_SecureHash;
                String paymentUrl = ConfigNapas.vpc_RefundUrl + "?" + queryUrl;

                return new DataResponse(ResponseCode.SUCCESSFUL, ResponseCode.SUCCESSFUL, paymentUrl);

            } catch (Exception ex) {
                logger.error(Configuration.SERVICE_NAME+": " +ex.getStackTrace()[0].toString()+":"+ex.toString());
                return new DataResponse("-1", ex.getMessage());
            }
        };
    }
}
