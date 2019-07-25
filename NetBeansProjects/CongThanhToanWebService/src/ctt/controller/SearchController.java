/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctt.controller;

import ctt.configuration.Configuration;
import ctt.render.RenderMain;
import firo.Controller;
import firo.Request;
import firo.Response;
import firo.Route;
import firo.RouteInfo;
import org.json.JSONObject;
import ctt.response.DataResponse;
import ctt.response.ResponseCode;
import ctt.transport.ThongTinDichVuApi;
import ctt.transport.NenTangThanhToanApi;
import firo.utils.config.Config;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.json.JSONArray;

/**
 *
 * @author dongvd3
 */
public class SearchController extends Controller {

    public SearchController() {
        rootPath = "/payment/timKiem";
    }
    Logger logger = Logger.getLogger(SearchController.class.getName());
    @RouteInfo(method = "post", path = "/order")
    public Route paymentTienDien() {
        return (Request request, Response response) -> {
            try {
                response.header("Content-Type", "application/json");
                int dichvuId = Config.getParamInt("config", "dichVuId");
                String maHoaDon = new JSONObject(request.body()).getString("maHoaDon");
                JSONObject dichVuInfo = NenTangThanhToanApi.getInstance().getApi("DichVu/"+dichvuId);
                if(!dichVuInfo.getString("error_code").equals("SUCCESSFUL")){
                    return new DataResponse(ResponseCode.FAILED, dichVuInfo.getString("error_message"));
                }
                System.out.println("resDichVu:"+dichVuInfo);
                String urlGetAPI = dichVuInfo.getJSONObject("data").getString("apiGetInfo");
                System.out.println("urlGetAPI:"+urlGetAPI);
                JSONObject data = ThongTinDichVuApi.getInstance().getApi(urlGetAPI + maHoaDon);

                if (data.getJSONArray("data").length() == 0) {
                    return new DataResponse("-1", "Khong tim thay hoa don " + maHoaDon);
                }
                JSONArray resultArray = data.getJSONArray("data");
                data = new JSONObject();
                data.put("dichvuId", dichvuId);
                data.put("maHoaDon", maHoaDon);
                data.put("tenNguoiNop", resultArray.getJSONObject(0).getString("customerName"));
                data.put("soCMND", "0312512521");
                data.put("diaChi", resultArray.getJSONObject(0).getString("address"));
                JSONArray invoices = new JSONArray();
                JSONObject invoice, item;
                for (int i = 0; i < resultArray.length(); i++) {
                    item = resultArray.getJSONObject(i);
                    invoice = new JSONObject();
                    invoice.put("soTien", item.getInt("amount"));
                    invoice.put("trangThai", item.getInt("status"));
                    invoice.put("moTa", item.getString("orderInfo"));
                    invoice.put("orderId", item.getString("orderId"));
                    invoice.put("partnerCode", item.getString("organId"));
                    invoices.put(invoice);
                }
                data.put("danhSachThanhToan", invoices);
                return new DataResponse(data, DataResponse.DataType.JSON_STR, false);
            } catch (Exception ex) {
                logger.error(Configuration.SERVICE_NAME+": " +ex.getStackTrace()[0].toString()+":"+ex.toString());
                return new DataResponse(ResponseCode.FAILED, ex.getMessage());
            }
        };
    }

    @RouteInfo(method = "get", path = "/hoaDon")
    public Route timKiemThongTinHoaDon() {
        return (Request request, Response response) -> {
            try {
                String loaiHoaDon = request.queryParams("loai");
                String tenDichVu = "";
                String anhDichVu = "";
                String inputDichVu = "";
                if (loaiHoaDon == null) {
                    return RenderMain.getInstance().renderPage(request,response, "404.ftl");
                }

                switch (loaiHoaDon) {
                    case "thuTucHanhChinh":
                        tenDichVu = "Thủ tục hành chính";
                        anhDichVu = "img/icon_tien_vi_pham_hanh_chinh.png";
                        inputDichVu = "Nhập mã số biên nhận";
                        break;
                    case "tienDien":
                        tenDichVu = "Tiền điện";
                        anhDichVu = "img/icon_tien-dien.png";
                        inputDichVu = "Nhập mã số hợp đồng";
                        break;
                    case "tienNuoc":
                        tenDichVu = "Tiền nước";
                        anhDichVu = "img/icon_tien_nuoc.png";
                        inputDichVu = "Nhập mã số hợp đồng";
                        break;
                    case "viPhamHanhChinh":
                        tenDichVu = "Vi phạm hành chính";
                        anhDichVu = "img/icon_tien_vi_pham_hanh_chinh.png";
                        inputDichVu = "Nhập mã số hợp đồng";
                        break;
                    case "napTienDienThoai":
                        tenDichVu = "Nạp tiền điện thoại";
                        anhDichVu = "img/icon_nap_tien_dt.png";
                        inputDichVu = "Nhập mã số hợp đồng";
                        break;
                }

                HashMap<String, String> params = new HashMap<>();
                params.put("tenDichVu", tenDichVu);
                params.put("anhDichVu", anhDichVu);
                params.put("inputDichVu", inputDichVu);
                return RenderMain.getInstance().renderSearch(request, response, params);
            } catch (Exception ex) {
                logger.error(Configuration.SERVICE_NAME+": " +ex.getStackTrace()[0].toString()+":"+ex.toString());
                return RenderMain.getInstance().renderPage(request, response,"404.ftl");
            }
        };
    }
}
