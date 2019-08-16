/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nttt.api.configuration;

/**
 *
 * @author ASUS
 */
public class ConfigMomo {

    public static String momo_returnURL = "https://google.com.vn";
    public static String momo_notifyURL = "https://google.com.vn";
    public static String momo_partnerCode = "MOMO";
    public static String momo_accessKey = "F8BBA842ECF85";
    public static String momo_secretKey = "K951B6PE1waDMi640xX08PD3vg6EkVlz";

    public static String PARTNER_CODE = "partnerCode";
    public static String ACCESS_KEY = "accessKey";
    public static String REQUEST_ID = "requestId";
    public static String AMOUNT = "amount";

    public static String ORDER_ID = "orderId";
    public static String ORDER_INFO = "orderInfo";

    public static String RETURN_URL = "returnUrl";
    public static String NOTIFY_URL = "notifyUrl";

    public static String REQUEST_TYPE = "requestType";
    public static String EXTRA_DATA = "extraData";
    public static String BANK_CODE = "bankCode";
    public static String TRANS_ID = "transId";
    public static String MESSAGE = "message";
    public static String LOCAL_MESSAGE = "localMessage";
    public static String PAY_URL = "payUrl";
    public static String DEEP_LINK = "deeplink";
    public static String QR_CODE = "qrCode";
    public static String ERROR_CODE = "errorCode";
    public static String PAY_TYPE = "payType";
    public static String ORDER_TYPE = "orderType";

    public static String DATE = "responseTime";

    public static final String CUSTOMER_NUMBER = "customerNumber";
    public static final String PARTNER_REF_ID = "partnerRefId";
    public static final String PARTNER_TRANS_ID = "partnerTransId";
    public static final String USERNAME = "userName";
    
    /**
     * ======================= USING FOR MERCHANT/PARTNER PUBLIC =================
     */
    public final static String UN_SUPPORT = "UN_SUPPORT";
    public final static String CAPTURE_MOMO_WALLET = "captureMoMoWallet";
    public final static String TRANSACTION_STATUS = "transactionStatus";
    public final static String REFUND_MOMO_WALLET = "refundMoMoWallet";
    public final static String QUERY_REFUND = "refundStatus";
    public final static String REFUND_ATM = "refundMoMoATM";
    public final static String WALLET_BALANCE = "walletBalance";
    public final static String PAY_WITH_ATM = "payWithMoMoATM";
    public final static String TOPUP_MOBILE = "topUpMoMo";
    public final static String BUY_CARD_PHONE = "buyCardMoMo";
    public final static String SUBSCRIBE = "subscribeMoMo";
    public final static String PAY_WITH_SUBSCRIBE = "payWithSubscribeMoMo";
    public final static String AUTHORIZE_MOMO_WALLET = "subscriptionToken";

    public final static String FINISH_WITH_MOMO_ATM = "finishProcessMoMoATM";

    public final static String PAY_WITH_QR = "finishProcessMoMoATM";


    /**
     * ========================= USING INTERNAL ==============================
     */
    public final static String QUERY_STATUS_PAY_WITH_APP = "queryStatusPayWithApp";
    public final static String QUERY_STATUS_AUTHORIZE_WITH_APP = "queryStatusAuthorizeWithApp";
    public final static String PAY_WITH_APP = "payWithApp";

}
