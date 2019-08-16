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
public class LichSuThanhToan {

    private int id; // mã giao dịch của hệ thống.
    private String tenDangNhap;

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }
    private String requestCode; // mỗi lần giao dịch sẽ tương ứng với 1 requestCode.
    private long amount;
    private String orderId;
    private String orderPayId;
    private String orderInfo;
    private long payTransactionNo; // mã số giao dịch trên cổng thanh toán
    private long payDate;
    private String errorCode; // 00-Thanh cong
    private String payCenter;
    private String partnerCode;
    private int serviceId;
    private long createdTime;
    private String billcode;
    private String accountPay;

    public LichSuThanhToan() {
    }

    public String getAccountPay() {
        return accountPay;
    }

    public void setAccountPay(String accountPay) {
        this.accountPay = accountPay;
    }

    public int getId() {
        return id;
    }

    public String getBillcode() {
        return billcode;
    }

    public void setBillcode(String billcode) {
        this.billcode = billcode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public long getPayTransactionNo() {
        return payTransactionNo;
    }

    public void setPayTransactionNo(long payTransactionNo) {
        this.payTransactionNo = payTransactionNo;
    }

    public long getPayDate() {
        return payDate;
    }

    public void setPayDate(long payDate) {
        this.payDate = payDate;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getPayCenter() {
        return payCenter;
    }

    public void setPayCenter(String payCenter) {
        this.payCenter = payCenter;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }


    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }
    public String getOrderPayId() {
        return orderPayId;
    }

    public void setOrderPayId(String orderPayId) {
        this.orderPayId = orderPayId;
    }

}