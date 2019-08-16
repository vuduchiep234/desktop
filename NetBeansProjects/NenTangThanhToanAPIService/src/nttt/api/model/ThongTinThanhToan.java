/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nttt.api.model;

/**
 *
 * @author dongvd3
 */
public class ThongTinThanhToan {

    private int id;
    private String payPartnerCode;
    private String payAccessKey;
    private String paySecretKey;
    private int congThanhToanId;
    private String partnerCode;

    public ThongTinThanhToan() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPayPartnerCode() {
        return payPartnerCode;
    }

    public void setPayPartnerCode(String payPartnerCode) {
        this.payPartnerCode = payPartnerCode;
    }

    public String getPayAccessKey() {
        return payAccessKey;
    }

    public void setPayAccessKey(String payAccessKey) {
        this.payAccessKey = payAccessKey;
    }

    public String getPaySecretKey() {
        return paySecretKey;
    }

    public void setPaySecretKey(String paySecretKey) {
        this.paySecretKey = paySecretKey;
    }

    public int getCongThanhToanId() {
        return congThanhToanId;
    }

    public void setCongThanhToanId(int congThanhToanId) {
        this.congThanhToanId = congThanhToanId;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

}
