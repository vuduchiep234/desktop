/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nttt.api.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import nttt.api.model.CaptureMoMoRequest;
import nttt.api.model.CaptureMoMoResponse;
import nttt.api.model.DataResponse;
import nttt.api.model.Environment;
import nttt.api.model.PartnerInfo;
import nttt.api.model.QueryStatusTransactionRequest;
import nttt.api.model.QueryStatusTransactionResponse;
import nttt.api.model.ResponseCode;
import nttt.api.server.Processor;
import firo.Controller;
import firo.Request;
import firo.Response;
import firo.Route;
import firo.RouteInfo;
import nttt.api.configuration.Configuration;
import static nttt.api.controller.VNPAYController.congThanhToanMomoId;
import static nttt.api.controller.VNPAYController.getThongTinThanhToan;
import nttt.api.model.ThongTinThanhToan;
import org.apache.log4j.Logger;
import org.json.JSONObject;

/**
 *
 * @author ASUS
 */
public class MOMOController extends Controller {

    public MOMOController() {
        rootPath = "/momo";
    }
    static Logger logger = Logger.getLogger(MOMOController.class.getName());
    /**
     * *
     * Select environment You can load config from file MoMo only provide once
     * endpoint for each envs: dev and prod
     *
     * @param target
     * @param momo_partnerCode
     * @param momo_accessKey
     * @param momo_secretKey
     * @return
     */
    public static Environment selectEnv(String target, String momo_partnerCode, String momo_accessKey, String momo_secretKey) {
        switch (target) {
            case "dev":
//                 PartnerInfo devInfo = new PartnerInfo(ConfigMomo.momo_partnerCode, ConfigMomo.momo_accessKey, ConfigMomo.momo_secretKey);
                PartnerInfo devInfo = new PartnerInfo(momo_partnerCode, momo_accessKey, momo_secretKey);
                Environment dev = new Environment("https://testing.momo.vn/gw_payment/transactionProcessor", devInfo, "development");
                return dev;
            case "prod":
                PartnerInfo productionInfo = new PartnerInfo(momo_partnerCode, momo_accessKey, momo_secretKey);
                Environment production = new Environment("https://payment.momo.vn/gw_payment/transactionProcessor", productionInfo, "production");
                return production;
            default:
                throw new IllegalArgumentException("MoMo doesnt provide other environment: dev and prod");
        }
    }

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
                    String partnerCode = data.getString("partnerCode");
                    String requestId = data.getString("requestCode");
                    String orderId = data.getString("orderId");
                    String amount = data.getString("amount");
                    String orderInfo = data.getString("orderInfo");
                    String extraData = data.getString("extraData");
                    String returnUrl = data.getString("returnUrl");
                    String notifiUrl = data.getString("notifiUrl");

                    DataResponse resThongTinThanhToan = getThongTinThanhToan(partnerCode, congThanhToanMomoId);
                    if (resThongTinThanhToan.getError().equals("-1") || resThongTinThanhToan.getData() == null) {
                        return DataResponse.FAILED;
                    }
                    ThongTinThanhToan thongTinThanhToan = (ThongTinThanhToan) resThongTinThanhToan.getData();

                    Environment environment = selectEnv("dev", thongTinThanhToan.getPayPartnerCode(), thongTinThanhToan.getPayAccessKey(), thongTinThanhToan.getPaySecretKey());
//                    Console.log("========================== START CAPTURE MOMO WALLET ==================");

                    CaptureMoMoRequest captureMoMoRequest = Processor.createCaptureMoMoRequest(orderId, requestId, amount, orderInfo, returnUrl, notifiUrl, extraData, environment.getPartnerInfo());
                    CaptureMoMoResponse captureMoMoResponse = Processor.getCaptureMoMoResponse(environment, captureMoMoRequest);
                    // Your handler
                    System.out.println("captureMoMoResponse.getErrorCode():" + captureMoMoResponse.toString());
                    if (captureMoMoResponse.getErrorCode() != 0) {
//                        Console.debug("errorCode::", captureMoMoResponse.getErrorCode() + "");
//                        Console.debug("errorMessage::", captureMoMoResponse.getMessage());
//                        Console.debug("localMessage::", captureMoMoResponse.getLocalMessage());
                        JSONObject job = new JSONObject();
                        job.put("code", captureMoMoResponse.getErrorCode() + "");
                        job.put("message", captureMoMoResponse.getMessage());
                        job.put("localMessage", captureMoMoResponse.getLocalMessage());
                        return new DataResponse(job.get("code").toString(), job.get("message").toString());
                    } else {
                        // To do something here ...
                        // You can get payUrl to redirect new tab browser or open link on iframe to serve payment
                        // Using deeplink to open MoMo App
                        // Using qrCodeUrl to generate QrCode with data is this
//                        Console.debug("payURL::", captureMoMoResponse.getPayUrl() + "");
//                        Console.debug("deepLink::", captureMoMoResponse.getDeeplink());
//                        Console.debug("qrCodeURL::", captureMoMoResponse.getQrCodeUrl());

                        // Payment with website
                        return new DataResponse(ResponseCode.SUCCESSFUL, ResponseCode.SUCCESSFUL, captureMoMoResponse.getPayUrl());
                    }
                } catch (Exception ex) {
                    logger.error(Configuration.SERVICE_NAME+": " +ex.getStackTrace()[0].toString()+":"+ex.toString());
                    return new DataResponse("-1", ex.getMessage());
                }
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
    @RouteInfo(method = "get", path = "/getStatusTransaction")
    public Route getStatusTransaction() {
        return (Request request, Response response) -> {
            try {

                response.header("Content-Type", "application/json");
                response.header("Access-Control-Allow-Origin", "*");
                response.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
                String sJson = request.body();
                JSONObject data = new JSONObject(sJson);
                String requestId = data.getString("requestId");
                String partnerCode = data.getString("partnerCode");
                String orderId = data.getString("orderId");
                DataResponse resThongTinThanhToan = getThongTinThanhToan(partnerCode, congThanhToanMomoId);
                if (resThongTinThanhToan.getError().equals("-1") || resThongTinThanhToan.getData() == null) {
                    return DataResponse.FAILED;
                }
                ThongTinThanhToan thongTinThanhToan = (ThongTinThanhToan) resThongTinThanhToan.getData();

                Environment environment = selectEnv("dev", thongTinThanhToan.getPayPartnerCode(), thongTinThanhToan.getPayAccessKey(), thongTinThanhToan.getPaySecretKey());

//                Console.log("========================== START QUERY QUERY STATUS ==================");
                QueryStatusTransactionRequest queryStatusRequest = Processor.createQueryTransactionRequest(orderId, requestId, environment.getPartnerInfo());
                QueryStatusTransactionResponse queryStatusResponse = Processor.getQueryStatusResponse(environment, queryStatusRequest);
                // Your handler
//                Console.error("errorCode::", queryStatusResponse.getErrorCode() + "");
//                Console.error("errorMessage::", queryStatusResponse.getMessage());
//                Console.error("localMessage::", queryStatusResponse.getLocalMessage());
//                Console.log("========================== END QUERY QUERY STATUS ==================");
                com.google.gson.JsonObject job = new JsonObject();
                job.addProperty("code", queryStatusResponse.getErrorCode() + "");
                job.addProperty("message", queryStatusResponse.getMessage());
                job.addProperty("localMessage", queryStatusResponse.getLocalMessage());
                Gson gson = new Gson();
                return new DataResponse(ResponseCode.SUCCESSFUL, ResponseCode.SUCCESSFUL, gson.toJson(job));

            } catch (Exception ex) {
                logger.error(Configuration.SERVICE_NAME+": " +ex.getStackTrace()[0].toString()+":"+ex.toString());
                return new DataResponse("-1", ex.getMessage());
            }
        };
    }

}
