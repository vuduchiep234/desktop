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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import nttt.api.configuration.ConfigNapas;
import nttt.api.configuration.ConfigViettelPay;
import nttt.api.configuration.Configuration;
import static nttt.api.controller.VNPAYController.getThongTinThanhToan;
import nttt.api.model.DataResponse;
import nttt.api.model.ResponseCode;
import nttt.api.model.ThongTinThanhToan;
import nttt.api.service.ObjectService;
import org.apache.log4j.Logger;
import org.json.JSONObject;

/**
 *
 * @author hung
 */
public class ViettelPayController extends Controller {
    static Logger logger = Logger.getLogger(ViettelPayController.class.getName());
    public ViettelPayController() {
        rootPath = "/viettel";
    }
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
                String orderId = data.getString("order_id");
                String amount = data.getString("trans_amount");
                String orderInfo = data.getString("desc");
                String billcode = data.getString("billcode");
                String cancelURL = data.getString("cancel_url");
                String returnURL = data.getString("return_url");

                DataResponse resThongTinThanhToan = getThongTinThanhToan(partnerCode, congThanhToanVetelId);
                if (resThongTinThanhToan.getError().equals("-1") || resThongTinThanhToan.getData() == null) {
                    return DataResponse.FAILED;
                }
                ThongTinThanhToan thongTinThanhToan = (ThongTinThanhToan) resThongTinThanhToan.getData();

                Map<String, String> vpc_Params = new HashMap<>();
                vpc_Params.put("merchant_code", thongTinThanhToan.getPayPartnerCode());
                vpc_Params.put("access_code", thongTinThanhToan.getPayAccessKey());
                vpc_Params.put("version", "2.0");
                vpc_Params.put("command", "PAYMENT");
                vpc_Params.put("trans_amount", amount);
                vpc_Params.put("billcode", billcode);
                vpc_Params.put("locale", "vn");
                vpc_Params.put("desc", orderInfo);
                vpc_Params.put("cancel_url", cancelURL);
                vpc_Params.put("return_url", returnURL);
                vpc_Params.put("order_id", orderId);

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
                String checksum = thongTinThanhToan.getPayAccessKey() + billcode + "PAYMENT" + thongTinThanhToan.getPayPartnerCode() + orderId + amount + "2.0";
                String queryUrl = query.toString();
                String vpc_SecureHash = ConfigViettelPay.hmac(checksum, thongTinThanhToan.getPaySecretKey());
                queryUrl += "&check_sum=" + vpc_SecureHash;
                String paymentUrl = ConfigViettelPay.vpc_PayUrl + "?" + queryUrl;

                return new DataResponse(ResponseCode.SUCCESSFUL, ResponseCode.SUCCESSFUL, paymentUrl);

            } catch (Exception ex) {
                logger.error(Configuration.SERVICE_NAME+": " +ex.getStackTrace()[0].toString()+":"+ex.toString());
                ex.printStackTrace();
                return new DataResponse("-1", ex.getMessage());
            }
        };
    }

//    @RouteInfo(method = "post", path = "/confirmTrans")
//    public Route confirmTrans() {
//        return (Request request, Response response) -> {
//            try {
//                response.header("Content-Type", "application/x-www-form-urlencoded");
//                response.header("Access-Control-Allow-Origin", "*");
//                response.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
//                String sJson = request.body();
//
//                JSONObject data = new JSONObject(sJson);
//
//                String billcode = data.getString("billcode");
//                String accessCode = data.getString("access_code");
//                String merchantCode = data.getString("merchant_code");
//                String orderId = data.getString("order_id");
//                String checksum;
//                String transAmount = data.getString("trans_amount");
//                String errorCode = data.getString("error_code");
//            
//                String hashData = accessCode + billcode + errorCode + merchantCode + orderId + transAmount;
//                checksum = ConfigViettelPay.hmac(hashData, ConfigViettelPay.hashSecret);
//
//                Map<String,String> vpc_Params = new HashMap<>();
//                vpc_Params.put("merchant_code", merchantCode);
//                vpc_Params.put("trans_amount", transAmount);
//                vpc_Params.put("billcode", billcode);
//                vpc_Params.put("check_sum", checksum);
//                vpc_Params.put("order_id", orderId);
//                vpc_Params.put("error_code", errorCode);
//              
//                return new DataResponse(vpc_Params);
//            } catch (Exception ex) {
//                logger.error(Configuration.SERVICE_NAME+": " +ex.getStackTrace()[0].toString()+":"+ex.toString());
//                ex.printStackTrace();
//                return new DataResponse("-1", ex.getMessage());
//            }
//        };
//    }
    
//    @RouteInfo(method = "post", path = "/queryTrans")
//    public Route queryTrans() {
//        return (Request request, Response response) -> {
//            try {
//                response.header("Content-Type", "application/x-www-form-urlencoded");
//                response.header("Access-Control-Allow-Origin", "*");
//                response.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
//                String sJson = request.body();
//
//                JSONObject data = new JSONObject(sJson);
//
//                String merchantCode = data.getString("merchant_code");
//                String orderId = data.getString("order_id");
//                String errorCode = "00";
//                
//                DataResponse resLichSuThanhToan = getLichSuThanhToan(orderId);
//                if (resLichSuThanhToan.getError().equals("-1") || resLichSuThanhToan.getData() == null) {
//                    System.out.println("resLichSuThanhToan.getData() ;" + resLichSuThanhToan.getData());
//                    errorCode = "01";
//                }
//            
//                String hashData = ConfigViettelPay.accessKey + errorCode + merchantCode + orderId;
//                String checksum = ConfigViettelPay.hmac(hashData, ConfigViettelPay.hashSecret);
//
//                Map<String,String> vpc_Params = new HashMap<>();
//                vpc_Params.put("merchant_code", merchantCode);
//                vpc_Params.put("error_code", errorCode);
//                vpc_Params.put("return_url", "");
//                vpc_Params.put("check_sum", checksum);
//                vpc_Params.put("return_bill_code", "");
//                vpc_Params.put("return_other_info", "");
//              
//                return new DataResponse(vpc_Params);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//                return new DataResponse("-1", ex.getMessage());
//            }
//        };
//    }

    @RouteInfo(method = "post", path = "/refund")
    public Route refund() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/x-www-form-urlencoded");
                response.header("Access-Control-Allow-Origin", "*");
                response.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
                String sJson = request.body();
                JSONObject data = new JSONObject(sJson);

                String merchantCode = data.getString("merchant_code");
                String orderId = data.getString("order_id");
                String originalRequestId = data.getString("originalRequestId");
                String transAmount = data.getString("trans_amount");
                String transContent = data.getString("trans_content");
                String partnerCode = data.getString("partnerCode");

                DataResponse resThongTinThanhToan = getThongTinThanhToan(partnerCode, congThanhToanVetelId);
                if (resThongTinThanhToan.getError().equals("-1") || resThongTinThanhToan.getData() == null) {
                    return DataResponse.FAILED;
                }
                ThongTinThanhToan thongTinThanhToan = (ThongTinThanhToan) resThongTinThanhToan.getData();
                String accessKey = thongTinThanhToan.getPayAccessKey();
                String hashData = accessKey + "2.0"+ merchantCode + orderId + originalRequestId + "0"+ transAmount + "2.0";
                
                String hash = ConfigViettelPay.hmac(hashData, ConfigViettelPay.hashSecret);

                Map<String, String> vpc_Params = new HashMap<>();
                vpc_Params.put("version", "2.0");
                vpc_Params.put("cmd", "REFUND_PAYMENT");
                vpc_Params.put("merchant_code", merchantCode);
                vpc_Params.put("order_id", orderId);
                vpc_Params.put("refundType", "0");
                vpc_Params.put("trans_amount", transAmount);
                vpc_Params.put("trans_content", transContent);
                vpc_Params.put("check_sum", hash);

                List fieldNames = new ArrayList(vpc_Params.keySet());
                Collections.sort(fieldNames);
                StringBuilder query = new StringBuilder();
                Iterator itr = fieldNames.iterator();
                while (itr.hasNext()) {
                    String fieldName = (String) itr.next();
                    String fieldValue = (String) vpc_Params.get(fieldName);
                    if ((fieldValue != null) && (fieldValue.length() > 0)) {       
                        //Build query
                        query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                        query.append('=');
                        query.append(URLEncoder.encode(fieldValue, StandardCharsets.UTF_8.toString()));

                        if (itr.hasNext()) {
                            query.append('&');               
                        }
                    }
                }
                String queryUrl = query.toString();
                String paymentUrl = ConfigNapas.vpc_RefundUrl + "?" + queryUrl;

                return new DataResponse(ResponseCode.SUCCESSFUL, ResponseCode.SUCCESSFUL, paymentUrl);

            } catch (Exception ex) {
                logger.error(Configuration.SERVICE_NAME+": " +ex.getStackTrace()[0].toString()+":"+ex.toString());
                ex.printStackTrace();
                return new DataResponse("-1", ex.getMessage());
            }
        };
    }
    
    public static DataResponse getLichSuThanhToan(String orderId) {
        try {
            JSONObject data = new JSONObject();
            data.put("orderId", orderId);
            return ObjectService.getInstance().getByCondition("LichSuThanhToan", data.toString());

        } catch (Exception ex) {
            logger.error(Configuration.SERVICE_NAME+": " +ex.getStackTrace()[0].toString()+":"+ex.toString());
            System.err.println("Exception getPayPartnerCode :" + ex.toString());
        }
        return null;
    }
}
