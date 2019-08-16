/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nttt.api.controller;

import nttt.api.configuration.ConfigVNPAY;
import nttt.api.model.DataResponse;
import nttt.api.model.ResponseCode;
import firo.Controller;
import firo.Request;
import firo.Response;
import firo.Route;
import firo.RouteInfo;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import nttt.api.conectordb.ObjectCollections;
import nttt.api.configuration.Configuration;
import nttt.api.model.ThongTinThanhToan;
import nttt.api.service.ObjectService;
import org.apache.log4j.Logger;
import org.json.JSONObject;

/**
 *
 * @author anbq
 */
public class VNPAYController extends Controller {

    public VNPAYController() {
        rootPath = "/vnpay";
    }
    static Logger logger = Logger.getLogger(VNPAYController.class.getName());
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
                JSONObject data = new JSONObject(sJson);

                String vnp_OrderInfo = data.getString("orderInfo");
                String orderType = data.getString("orderType");
                int amount = Integer.parseInt(data.getString("amount")) * 100;
                String vnp_TxnRef = data.getString("requestCode");
                String urlReturn = data.getString("returnUrl");
                String partnerCode = data.getString("partnerCode");

                String vnp_Version = "2.0.0";
                String vnp_Command = "pay";
                String vnp_IpAddr = ConfigVNPAY.getIpAddress(request);
                String vnp_TmnCode = "";

                DataResponse resThongTinThanhToan = getThongTinThanhToan(partnerCode, congThanhToanVNpayId);
                if (resThongTinThanhToan.getError().equals("-1") || resThongTinThanhToan.getData() == null) {
                    return DataResponse.FAILED;
                }
                ThongTinThanhToan thongTinThanhToa = (ThongTinThanhToan) resThongTinThanhToan.getData();
                vnp_TmnCode = thongTinThanhToa.getPayPartnerCode();
//                String vnp_TransactionNo = vnp_TxnRef;
//                String vnp_hashSecret = ConfigVNPAY.vnp_HashSecret;
                Map<String, String> vnp_Params = new HashMap<>();
                vnp_Params.put("vnp_Version", vnp_Version);
                vnp_Params.put("vnp_Command", vnp_Command);
                vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
                vnp_Params.put("vnp_Amount", String.valueOf(amount));
                vnp_Params.put("vnp_CurrCode", "VND");
//                String bank_code = data.getString("bankcode");
//                if (bank_code != null && bank_code.isEmpty()) {
//                    vnp_Params.put("vnp_BankCode", bank_code);
//                }
                vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
                vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
                vnp_Params.put("vnp_OrderType", orderType);

//                String locate = data.getString("language");
//                if (locate != null && !locate.isEmpty()) {
//                    vnp_Params.put("vnp_Locale", locate);
//                } else {
                    vnp_Params.put("vnp_Locale", "vn");
//                }

                vnp_Params.put("vnp_ReturnUrl", urlReturn);
                vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

                Date dt = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                String dateString = formatter.format(dt);
                String vnp_CreateDate = dateString;
                String vnp_TransDate = vnp_CreateDate;
                vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

                //Build data to hash and querystring
                List fieldNames = new ArrayList(vnp_Params.keySet());
                Collections.sort(fieldNames);
                StringBuilder hashData = new StringBuilder();
                StringBuilder query = new StringBuilder();
                Iterator itr = fieldNames.iterator();
                while (itr.hasNext()) {
                    String fieldName = (String) itr.next();
                    String fieldValue = (String) vnp_Params.get(fieldName);
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
                String vnp_SecureHash = ConfigVNPAY.md5(thongTinThanhToa.getPaySecretKey() + hashData.toString());
                queryUrl += "&vnp_SecureHashType=MD5&vnp_SecureHash=" + vnp_SecureHash;
                String paymentUrl = ConfigVNPAY.vnp_PayUrl + "?" + queryUrl;

                return new DataResponse(ResponseCode.SUCCESSFUL, ResponseCode.SUCCESSFUL, paymentUrl);

            } catch (Exception ex) {
                logger.error(Configuration.SERVICE_NAME+": " +ex.getStackTrace()[0].toString()+":"+ex.toString());
                ex.printStackTrace();
                return new DataResponse("-1", ex.getMessage());
            }
        };
    }
    
    @RouteInfo(method = "post", path = "/refund")
    public Route refund() {
        return new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                try {

                    response.header("Content-Type", "application/json");
                    response.header("Access-Control-Allow-Origin", "*");
                    response.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
                    String sJson = request.body();
                    JSONObject data = new JSONObject(sJson);
                    String partnerCode = data.getString("partnerCode");
                    String requestId = data.getString("requestCode");
                    String orderId = data.getString("orderId");
                    

                    DataResponse resThongTinThanhToan = getThongTinThanhToan(partnerCode, congThanhToanMomoId);
                    if (resThongTinThanhToan.getError().equals("-1") || resThongTinThanhToan.getData() == null) {
                        return DataResponse.FAILED;
                    }
//                    
                } catch (Exception ex) {
                    logger.error(Configuration.SERVICE_NAME+": " +ex.getStackTrace()[0].toString()+":"+ex.toString());
                    return new DataResponse("-1", ex.getMessage());
                }
                return DataResponse.SUCCESSFUL;
            }
        };
    }
    
    public static DataResponse getThongTinThanhToan(String partnerCode, int congThanhToanId) {
        try {
            JSONObject data = new JSONObject();
            data.put("partnerCode", partnerCode);
            data.put("congThanhToanId", congThanhToanId);
            return ObjectService.getInstance().getByCondition("ThongTinThanhToan", data.toString());

        } catch (Exception e) {
            logger.error(Configuration.SERVICE_NAME+": " +e.getStackTrace()[0].toString()+":"+e.toString());
            System.err.println("Exception getPayPartnerCode :" + e.toString());
        }
        return null;
    }
}
