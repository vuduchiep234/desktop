package nttt.api.server;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import nttt.api.configuration.ConfigMomo;
import nttt.api.model.CaptureMoMoRequest;
import nttt.api.model.CaptureMoMoResponse;
import nttt.api.model.Environment;
import nttt.api.model.PartnerInfo;
import nttt.api.model.PayATMRequest;
import nttt.api.model.PayATMResponse;
import nttt.api.model.PaymentResponse;
import nttt.api.model.QueryStatusTransactionRequest;
import nttt.api.model.QueryStatusTransactionResponse;
import nttt.api.model.RefundATMMoMoRequest;
import nttt.api.model.RefundMoMoRequest;
import nttt.api.util.Console;
import nttt.api.util.Encoder;

public class Processor {

    public static PayATMRequest createPayWithATMRequest(String requestId, String orderId, String bankCode, String amount, String orderInfo, String returnUrl,
                                                        String notifyUrl, String extra, PartnerInfo partnerInfo) {
        String dataCryption
                = ConfigMomo.PARTNER_CODE + "=" + partnerInfo.getPartnerCode() + "&"
                + ConfigMomo.ACCESS_KEY + "=" + partnerInfo.getAccessKey() + "&"
                + ConfigMomo.REQUEST_ID + "=" + requestId + "&"
                + ConfigMomo.BANK_CODE + "=" + bankCode + "&"
                + ConfigMomo.AMOUNT + "=" + amount + "&"
                + ConfigMomo.ORDER_ID + "=" + orderId + "&"
                + ConfigMomo.ORDER_INFO + "=" + orderInfo + "&"
                + ConfigMomo.RETURN_URL + "=" + returnUrl + "&"
                + ConfigMomo.NOTIFY_URL + "=" + notifyUrl + "&"
                + ConfigMomo.EXTRA_DATA + "=" + extra + "&"
                + ConfigMomo.REQUEST_TYPE + "=" + ConfigMomo.PAY_WITH_ATM;
        try {
            String signature = Encoder.signHmacSHA256(dataCryption, partnerInfo.getSecretKey());

            PayATMRequest payATMRequest = new PayATMRequest();
            payATMRequest.setPartnerCode(partnerInfo.getPartnerCode());
            payATMRequest.setAccessKey(partnerInfo.getAccessKey());
            payATMRequest.setAmount(amount);
            payATMRequest.setRequestId(requestId);
            payATMRequest.setOrderId(orderId);
            payATMRequest.setReturnUrl(returnUrl);
            payATMRequest.setNotifyUrl(notifyUrl);
            payATMRequest.setOrderInfo(orderInfo);
            payATMRequest.setBankCode(bankCode);
            payATMRequest.setRequestType(ConfigMomo.PAY_WITH_ATM);
            payATMRequest.setSignature(signature);
            return payATMRequest;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Invalid params capture MoMo Request");
        }
    }

    public static RefundATMMoMoRequest createRefundMoMoATMRequest(String requestId, String orderId, String bankCode, String amount, String momoTransId, PartnerInfo partnerInfo) {
        RefundATMMoMoRequest refundATMMoMoRequest = new RefundATMMoMoRequest();
        String dataCryption
                = ConfigMomo.PARTNER_CODE + "=" + refundATMMoMoRequest.getPartnerCode() + "&"
                + ConfigMomo.ACCESS_KEY + "=" + refundATMMoMoRequest.getAccessKey() + "&"
                + ConfigMomo.REQUEST_ID + "=" + refundATMMoMoRequest.getRequestId() + "&"
                + ConfigMomo.BANK_CODE + "=" + refundATMMoMoRequest.getBankCode() + "&"
                + ConfigMomo.AMOUNT + "=" + refundATMMoMoRequest.getAmount() + "&"
                + ConfigMomo.ORDER_ID + "=" + refundATMMoMoRequest.getOrderId() + "&"
                + ConfigMomo.TRANS_ID + "=" + refundATMMoMoRequest.getTransId() + "&"
                + ConfigMomo.REQUEST_TYPE + "=" + refundATMMoMoRequest.getRequestType();
        String signature = "";
        try {
            signature = Encoder.signHmacSHA256(dataCryption, partnerInfo.getSecretKey());
            refundATMMoMoRequest.setPartnerCode(partnerInfo.getPartnerCode());
            refundATMMoMoRequest.setAccessKey(partnerInfo.getAccessKey());
            refundATMMoMoRequest.setRequestId(requestId);
            refundATMMoMoRequest.setAmount(amount);
            refundATMMoMoRequest.setBankCode(bankCode);
            refundATMMoMoRequest.setOrderId(orderId);
            refundATMMoMoRequest.setTransId(momoTransId);
            refundATMMoMoRequest.setSignature(signature);
            refundATMMoMoRequest.setRequestType(ConfigMomo.REFUND_ATM);

            return refundATMMoMoRequest;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid params capture MoMo Request");
        }
    }

    public static RefundMoMoRequest createRefundMoMoRequest(String requestId, String orderId, String amount, String momoTransId, PartnerInfo partnerInfo) {
        RefundMoMoRequest refundMoMoRequest = new RefundMoMoRequest();
        String dataCryption =
                ConfigMomo.PARTNER_CODE + "=" + partnerInfo.getPartnerCode() +
                        "&" + ConfigMomo.ACCESS_KEY + "=" + partnerInfo.getAccessKey() +
                        "&" + ConfigMomo.REQUEST_ID + "=" + requestId +
                        "&" + ConfigMomo.AMOUNT + "=" + amount +
                        "&" + ConfigMomo.ORDER_ID + "=" + orderId +
                        "&" + ConfigMomo.TRANS_ID + "=" + momoTransId +
                        "&" + ConfigMomo.REQUEST_TYPE + "=" + ConfigMomo.REFUND_MOMO_WALLET;
        String signature = "";
        try {
            signature = Encoder.signHmacSHA256(dataCryption, partnerInfo.getSecretKey());
            refundMoMoRequest.setPartnerCode(partnerInfo.getPartnerCode());
            refundMoMoRequest.setAccessKey(partnerInfo.getAccessKey());
            refundMoMoRequest.setRequestId(requestId);
            refundMoMoRequest.setAmount(amount);
            refundMoMoRequest.setOrderId(orderId);
            refundMoMoRequest.setTransId(momoTransId);
            refundMoMoRequest.setSignature(signature);
            refundMoMoRequest.setRequestType(ConfigMomo.REFUND_MOMO_WALLET);

            return refundMoMoRequest;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid params capture MoMo Request");
        }
    }

    public static QueryStatusTransactionRequest createQueryTransactionRequest(String requestId, String orderId, PartnerInfo partnerInfo) {
        QueryStatusTransactionRequest request = new QueryStatusTransactionRequest();
        String rawData =
                ConfigMomo.PARTNER_CODE + "=" + partnerInfo.getPartnerCode() +
                        "&" + ConfigMomo.ACCESS_KEY + "=" + partnerInfo.getAccessKey() +
                        "&" + ConfigMomo.REQUEST_ID + "=" + requestId +
                        "&" + ConfigMomo.ORDER_ID + "=" + orderId +
                        "&" + ConfigMomo.REQUEST_TYPE + "=" + ConfigMomo.TRANSACTION_STATUS;
        String signature = "";
        try {
            Console.debug("createQueryStatusRequest::rawDataBeforeHash::" + rawData);
            signature = Encoder.signHmacSHA256(rawData, partnerInfo.getSecretKey());
            Console.debug("createQueryStatusRequest::signature::" + signature);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAccessKey(partnerInfo.getAccessKey());
        request.setPartnerCode(partnerInfo.getPartnerCode());
        request.setOrderId(orderId);
        request.setRequestId(requestId);
        request.setRequestType(ConfigMomo.TRANSACTION_STATUS);
        request.setSignature(signature);
        return request;
    }

    public static CaptureMoMoRequest createCaptureMoMoRequest(String orderId, String requestId, String amount, String orderInfo, String returnUrl, String notifyUrl, String extraData, PartnerInfo partnerInfo) {
        try {
            String rawData =
                    ConfigMomo.PARTNER_CODE + "=" + partnerInfo.getPartnerCode() +
                            "&" + ConfigMomo.ACCESS_KEY + "=" + partnerInfo.getAccessKey() +
                            "&" + ConfigMomo.REQUEST_ID + "=" + requestId +
                            "&" + ConfigMomo.AMOUNT + "=" + amount +
                            "&" + ConfigMomo.ORDER_ID + "=" + orderId +
                            "&" + ConfigMomo.ORDER_INFO + "=" + orderInfo +
                            "&" + ConfigMomo.RETURN_URL + "=" + returnUrl +
                            "&" + ConfigMomo.NOTIFY_URL + "=" + notifyUrl +
                            "&" + ConfigMomo.EXTRA_DATA + "=" + extraData;
            Console.debug("createCaptureMoMoRequest::rawDataBeforeHash::" + rawData);
            String signature = Encoder.signHmacSHA256(rawData, partnerInfo.getSecretKey());
            Console.debug("createCaptureMoMoRequest::signature::" + signature);

            CaptureMoMoRequest captureMoMoRequest = new CaptureMoMoRequest();
            captureMoMoRequest.setAccessKey(partnerInfo.getAccessKey());
            captureMoMoRequest.setRequestId(requestId);
            captureMoMoRequest.setPartnerCode(partnerInfo.getPartnerCode());
            captureMoMoRequest.setRequestType(ConfigMomo.CAPTURE_MOMO_WALLET);
            captureMoMoRequest.setNotifyUrl(notifyUrl);
            captureMoMoRequest.setReturnUrl(returnUrl);
            captureMoMoRequest.setOrderId(orderId);
            captureMoMoRequest.setAmount(amount);
            captureMoMoRequest.setSignature(signature);
            captureMoMoRequest.setExtraData(extraData);
            captureMoMoRequest.setOrderInfo(orderInfo);
            return captureMoMoRequest;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException("Invalid params capture MoMo Request");
        }
    }


    /*** Send to MoMo Service**/
	public static CaptureMoMoResponse getCaptureMoMoResponse(Environment env, CaptureMoMoRequest captureMoMoRequest) throws Exception {
        PartnerInfo partnerInfo = env.getPartnerInfo();
        Execute execute = new Execute();
        String payload = execute.getGson().toJson(captureMoMoRequest, CaptureMoMoRequest.class);
        String response = execute.sendToMoMo(env, payload);
        CaptureMoMoResponse captureMoMoResponse = execute.getGson().fromJson(response, CaptureMoMoResponse.class);
        errorMoMoProcess(captureMoMoResponse.getErrorCode());
        if (captureMoMoResponse.getErrorCode() == 0) {
            String rawData =
                    ConfigMomo.REQUEST_ID + "=" + captureMoMoResponse.getRequestId() +
                            "&" + ConfigMomo.ORDER_ID + "=" + captureMoMoResponse.getOrderId() +
                            "&" + ConfigMomo.MESSAGE + "=" + captureMoMoResponse.getMessage() +
                            "&" + ConfigMomo.LOCAL_MESSAGE + "=" + captureMoMoResponse.getLocalMessage() +
                            "&" + ConfigMomo.PAY_URL + "=" + captureMoMoResponse.getPayUrl() +
                            "&" + ConfigMomo.ERROR_CODE + "=" + captureMoMoResponse.getErrorCode() +
                            "&" + ConfigMomo.REQUEST_TYPE + "=" + ConfigMomo.CAPTURE_MOMO_WALLET;

            Console.debug("getCaptureMoMoResponse::partnerRawDataBeforeHash::" + rawData);
            String signature = Encoder.signHmacSHA256(rawData, partnerInfo.getSecretKey());
            Console.debug("getCaptureMoMoResponse::partnerSignature::" + signature);
            Console.debug("getCaptureMoMoResponse::momoSignature::" + captureMoMoResponse.getSignature());

            if (signature.equals(captureMoMoResponse.getSignature())) {
                return captureMoMoResponse;
            } else {
                throw new IllegalArgumentException("Wrong signature from MoMo side - please contact with us");
            }
        }
        return captureMoMoResponse;
    }

    public static QueryStatusTransactionResponse getQueryStatusResponse(Environment env, QueryStatusTransactionRequest queryStatusRequest) throws Exception {
        PartnerInfo partnerInfo = env.getPartnerInfo();
        String payload = Execute.getGson().toJson(queryStatusRequest, CaptureMoMoRequest.class);
        String response = Execute.sendToMoMo(env, payload);
        QueryStatusTransactionResponse queryStatusResponse = Execute.getGson().fromJson(response, QueryStatusTransactionResponse.class);

        errorMoMoProcess(queryStatusResponse.getErrorCode());
        String rawData = ConfigMomo.PARTNER_CODE + "=" + queryStatusResponse.getPartnerCode() +
                "&" + ConfigMomo.ACCESS_KEY + "=" + queryStatusResponse.getAccessKey() +
                "&" + ConfigMomo.REQUEST_ID + "=" + queryStatusResponse.getRequestId() +
                "&" + ConfigMomo.ORDER_ID + "=" + queryStatusResponse.getOrderId() +
                "&" + ConfigMomo.ERROR_CODE + "=" + queryStatusResponse.getErrorCode() +
                "&" + ConfigMomo.TRANS_ID + "=" + queryStatusResponse.getTransId() +
                "&" + ConfigMomo.AMOUNT + "=" + queryStatusResponse.getAmount() +
                "&" + ConfigMomo.MESSAGE + "=" + queryStatusResponse.getMessage() +
                "&" + ConfigMomo.LOCAL_MESSAGE + "=" + queryStatusResponse.getLocalMessage() +
                "&" + ConfigMomo.REQUEST_TYPE + "=" + ConfigMomo.TRANSACTION_STATUS +
                "&" + ConfigMomo.PAY_TYPE + "=" + queryStatusResponse.getPayType() +
                "&" + ConfigMomo.EXTRA_DATA + "=" + queryStatusResponse.getExtraData();
        Console.debug("getQueryStatusResponse::rawDataBeforeHash::" + rawData);
        String signature = Encoder.signHmacSHA256(rawData, partnerInfo.getSecretKey());
        Console.debug("getQueryStatusResponse::signature::" + signature);

        if (signature.equals(queryStatusResponse.getSignature())) {
            return queryStatusResponse;
        } else {
            throw new Exception("Wrong signature from MoMo side - please contact with us");
        }
    }

    public static PayATMResponse getPayMoMoATMResponse(Environment env, PayATMRequest payATMRequest) throws Exception {
        PartnerInfo partnerInfo = env.getPartnerInfo();
        Execute execute = new Execute();
        String payload = execute.getGson().toJson(payATMRequest, PayATMRequest.class);
        String response = execute.sendToMoMo(env, payload);
        PayATMResponse payATMResponse = execute.getGson().fromJson(response, PayATMResponse.class);
        errorMoMoProcess(payATMResponse.getErrorCode());

        if (payATMResponse.getErrorCode() == 0) {
            String rawData =
                    ConfigMomo.PARTNER_CODE + "=" + payATMRequest.getPartnerCode() +
                            "&" + ConfigMomo.ACCESS_KEY + "=" + payATMRequest.getAccessKey() +
                            "&" + ConfigMomo.REQUEST_ID + "=" + payATMRequest.getRequestId() +
                            "&" + ConfigMomo.PAY_URL + "=" + payATMResponse.getPayUrl() +
                            "&" + ConfigMomo.ERROR_CODE + "=" + payATMResponse.getErrorCode() +
                            "&" + ConfigMomo.ORDER_ID + "=" + payATMResponse.getOrderId() +
                            "&" + ConfigMomo.MESSAGE + "=" + payATMResponse.getMessage() +
                            "&" + ConfigMomo.LOCAL_MESSAGE + "=" + payATMResponse.getLocalMessage() +
                            "&" + ConfigMomo.REQUEST_TYPE + "=" + ConfigMomo.PAY_WITH_ATM;

            Console.debug("getPayATMMoMoResponse::partnerRawDataBeforeHash::" + rawData);
            String signature = Encoder.signHmacSHA256(rawData, partnerInfo.getSecretKey());
            Console.debug("getPayATMMoMoResponse::partnerSignature::" + signature);
            Console.debug("getPayATMMoMoResponse::momoSignature::" + payATMResponse.getSignature());

            if (signature.equals(payATMResponse.getSignature())) {
                return payATMResponse;
            } else {
                throw new IllegalArgumentException("Wrong signature from MoMo side - please contact with us");
            }
        }
        return payATMResponse;
    }

    /**
     * After end-user do pay order, MoMo will return result to partner by two ways:
     * - 1. On browser, MoMo website will be redirected to Partner website by returnUrl field which passed to api captureMoMoWallet
     * - 2. MoMo uses notifyUrl field to send http request with POST method to partner endpoint
     *
     * For returnUrl: we will attach and fill data to params: refer PAYLOAD
     * For notifyUrl: we will build request with information:
     * METHOD: POST
     * HEADER: CONTENT-TYPE: X-WWW-FORM-URLENCODED
     *          CHARSET : UTF-8
     *
     * PAYLOAD: partnerCode=$partnerCode&accessKey=$accessKey&requestId=$requestId&amount=$amount&orderId=$oderId&orderInfo=$orderInfo&orderType=$orderType&transId=$transId&message=$message&localMessage=$localMessage&responseTime=$responseTime&errorCode=$errorCode& payType=$payType&extraData=$extraData
     *
     *
     * <p>
     * You can use this function to get and validate result
     * Using for two commands: captureMoMoWallet and payWithATM
     * ErrorCode is key to detect transaction is success or fail
     *
     * if ErrorCode = 0 mean transaction is payment success else fail
     *
     * @param env
     * @param paymentResponse
     * @return
     * @throws Exception
     */
    public static PaymentResponse resultCaptureMoMoWallet(Environment env, PaymentResponse paymentResponse) throws Exception {
        PartnerInfo partnerInfo = env.getPartnerInfo();
        String rawData
                = ConfigMomo.PARTNER_CODE + "=" + paymentResponse.getPartnerCode() + "&"
                + ConfigMomo.ACCESS_KEY + "=" + paymentResponse.getAccessKey() + "&"
                + ConfigMomo.REQUEST_ID + "=" + paymentResponse.getRequestId() + "&"
                + ConfigMomo.AMOUNT + "=" + paymentResponse.getAmount() + "&"
                + ConfigMomo.ORDER_ID + "=" + paymentResponse.getOrderId() + "&"
                + ConfigMomo.ORDER_INFO + "=" + paymentResponse.getOrderInfo() + "&"
                + ConfigMomo.ORDER_TYPE + "=" + paymentResponse.getOrderType() + "&"
                + ConfigMomo.TRANS_ID + "=" + paymentResponse.getTransId() + "&"
                + ConfigMomo.MESSAGE + "=" + paymentResponse.getMessage() + "&"
                + ConfigMomo.LOCAL_MESSAGE + "=" + paymentResponse.getLocalMessage() + "&"
                + ConfigMomo.DATE + "=" + paymentResponse.getResponseDate() + "&"
                + ConfigMomo.ERROR_CODE + "=" + paymentResponse.getErrorCode() + "&"
                + ConfigMomo.PAY_TYPE + "=" + paymentResponse.getPayType() + "&"
                + ConfigMomo.EXTRA_DATA + "=" + paymentResponse.getExtraData();

        Console.debug("resultCaptureMoMoWallet::partnerRawDataBeforeHash::" + rawData);
        String signature = Encoder.signHmacSHA256(rawData, partnerInfo.getSecretKey());
        Console.debug("resultCaptureMoMoWallet::partnerSignature::" + signature);
        Console.debug("resultCaptureMoMoWallet::momoSignature::" + paymentResponse.getSignature());

        if (signature.equals(paymentResponse.getSignature())) {
            return paymentResponse;
        } else {
            throw new IllegalArgumentException("Wrong signature from MoMo side - please contact with us");
        }
    }

    /**
     * Some errors will be showed in process
     * Read detail error in documents
     * [Find out] (https://business.momo.vn/solution/document) - Section 7
     *
     * @param errorCode
     * @throws Exception
     */
    public static void errorMoMoProcess(int errorCode) throws Exception {

        switch (errorCode) {
            case 0:
                // O is meaning success
                break;
            case 1:
                throw new Exception("Empty access key or partner code");

        }
    }
    
    /**
     * @author nhat.nguyen
     */
    public static String generateRSA(String phoneNumber, String billId, String tranId, String username, String partnerCode, long amount, String publicKey) throws Exception {
        // current version of Parameter key name is 2.0
    	Map<String, Object> rawData = new HashMap<>();
    	rawData.put(ConfigMomo.CUSTOMER_NUMBER, phoneNumber);
    	rawData.put(ConfigMomo.PARTNER_REF_ID, billId);
    	rawData.put(ConfigMomo.PARTNER_TRANS_ID, tranId);
    	rawData.put(ConfigMomo.USERNAME, username);
    	rawData.put(ConfigMomo.PARTNER_CODE, partnerCode);
    	rawData.put(ConfigMomo.AMOUNT, amount);
    	
    	Gson gson = new Gson();
    	String jsonStr = gson.toJson(rawData);
	    byte[] testByte = jsonStr.getBytes("UTF-8");
	    final String hash = Encoder.encryptRSA(testByte, publicKey);
	    return hash;
    }
}
