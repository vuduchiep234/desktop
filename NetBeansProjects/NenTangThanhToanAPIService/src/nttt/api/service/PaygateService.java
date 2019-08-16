/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nttt.api.service;

import firo.Request;
import firo.Response;
import firo.Route;
import nttt.api.model.DataResponse;
import nttt.api.model.DichVu;
import nttt.api.model.LichSuThanhToan;
import nttt.api.model.OrderType;
import nttt.api.model.TaiKhoanKhaiThac;
import nttt.api.server.ServletUtil;
import nttt.api.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.json.JSONObject;

/**
 *
 * @author dongvd3
 */
public class PaygateService {

    public static PaygateService instance = null;

    public static PaygateService getInstance() {
        if (instance == null) {
            instance = new PaygateService();
        }
        return instance;
    }

    public DataResponse getHistory(Request request, Response response) {
        try {
            response.header("Content-Type", "application/json");
            String valueSearch = ServletUtil.getStringParameter(request, "valueSearch");
            SessionFactory factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            Criteria criteria = session.createCriteria(LichSuThanhToan.class);
            if (isInteger(valueSearch)) {
                criteria.add(Restrictions.disjunction()
                        .add(Restrictions.like("orderPayId", "%"+valueSearch+"%"))
                        .add(Restrictions.eq("requestCode", valueSearch))
                        .add(Restrictions.like("payCenter", "%"+valueSearch+"%"))
                        .add(Restrictions.like("orderInfo", "%"+valueSearch+"%"))
//                        .add(Restrictions.eq("id", Integer.parseInt(valueSearch)))
                        .add(Restrictions.eq("payTransactionNo", Long.parseLong(valueSearch))));
            } else {
                criteria.add(Restrictions.disjunction()
                        .add(Restrictions.like("orderPayId", "%"+valueSearch+"%"))
                        .add(Restrictions.eq("requestCode", "%"+valueSearch+"%"))
                        .add(Restrictions.like("payCenter", "%"+valueSearch+"%"))
                        .add(Restrictions.like("orderInfo", "%"+valueSearch)));
            }

            Object resultObj = criteria.list();
            if (resultObj == null) {
                return DataResponse.NOT_FOUND;
            }
            return new DataResponse(resultObj);
        } catch (Exception ex) {
            return new DataResponse("-1", ex.getMessage());
        }
    }

    public DataResponse getRedirectInfo(Request request, Response response) {
        try {
            response.header("Content-Type", "application/json");
            int dichvuId = ServletUtil.getIntParameter(request, "id");
            SessionFactory factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            // get dich vu 
            Criteria criteria = session.createCriteria(DichVu.class);
            criteria.add(Restrictions.eq("id", dichvuId));
            criteria.add(Restrictions.eq("status", 1));
            DichVu dichVu = (DichVu) criteria.uniqueResult();
            if (dichVu == null) {
                return DataResponse.NOT_FOUND;
            }
            String partnerCode = ServletUtil.getStringParameter(request, "partnerCode");
            String apiUpdateInfo = dichVu.getApiUpdateInfo();
            int orderTypeId = dichVu.getOrderTypeId();
            // get order type 
//            OrderType orderType = (OrderType) session.get(OrderType.class, orderTypeId);
//            if (orderType == null) {
//                return DataResponse.NOT_FOUND;
//            }
//            String type = orderType.getName();
            // get tai khoan khai thac
            criteria = session.createCriteria(TaiKhoanKhaiThac.class);
            criteria.add(Restrictions.eq("partnerCode", partnerCode));
            TaiKhoanKhaiThac taiKhoanKhaiThac = (TaiKhoanKhaiThac) criteria.uniqueResult();
            if (taiKhoanKhaiThac == null) {
                return DataResponse.NOT_FOUND;
            }
            String accessKey = taiKhoanKhaiThac.getAccessKey();
            JSONObject respData = new JSONObject();
            respData.put("partnerCode", partnerCode);
            respData.put("orderType", orderTypeId);
            respData.put("accessKey", accessKey);
            respData.put("apiUpdateInfo", apiUpdateInfo);

            return new DataResponse(respData, DataResponse.DataType.JSON_STR, false);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new DataResponse("-1", ex.getMessage());
        }
    }

    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}
