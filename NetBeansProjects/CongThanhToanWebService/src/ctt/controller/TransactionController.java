/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctt.controller;

import ctt.configuration.Configuration;
import ctt.response.DataResponse;
import ctt.response.ResponseCode;
import ctt.service.ServletUtil;
import ctt.transport.NenTangThanhToanApi;
import firo.Controller;
import firo.Request;
import firo.Response;
import firo.Route;
import firo.RouteInfo;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.apache.log4j.Logger;
import org.json.JSONObject;

/**
 *
 * @author dongvd3
 */
public class TransactionController extends Controller {
    Logger logger = Logger.getLogger(TransactionController.class.getName());
    public TransactionController() {
        rootPath = "/payment/transaction";
    }

    @RouteInfo(method = "get", path = "/history")
    public Route getHistory() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                String valueSearch = ServletUtil.getStringParameter(request, "valueSearch", false);
               
                if (valueSearch.isEmpty()) {
                    return DataResponse.MISSING_PARAMS;
                }
                String params = "?valueSearch=" + URLEncoder.encode(valueSearch, StandardCharsets.UTF_8.toString());
                JSONObject res = NenTangThanhToanApi.getInstance().getApi("api/paygate/getHistory" + params);
                
                    if (!res.getString("error_code").equals(ResponseCode.SUCCESSFUL)) {
                        return new DataResponse(ResponseCode.CONNECT_BACKEND_ERROR, ResponseCode.CONNECT_BACKEND_ERROR);
                        
                    }
                    return new DataResponse(res.getJSONArray("data"), DataResponse.DataType.JSON_STR, false);
                
            } catch (Exception ex) {
                logger.error(Configuration.SERVICE_NAME+": " +ex.getStackTrace()[0].toString()+":"+ex.toString());
                ex.printStackTrace();
                return new DataResponse(ResponseCode.FAILED, ex.getMessage());
            }
        };
    }

}
