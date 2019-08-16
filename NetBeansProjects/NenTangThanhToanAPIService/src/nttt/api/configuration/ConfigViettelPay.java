/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nttt.api.configuration;
import java.security.SignatureException;
import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author hung
 */
public class ConfigViettelPay {

    public static String vpc_PayUrl = "https://sandbox.viettel.vn/PaymentGateway/payment";
    public static String hashSecret = "d41d8cd98f00b204e9800998ecf8427e2d1dace0eb10afb4a5d6fe65bf8021da";
    public static String accessKey = "d41d8cd98f00b204e9800998ecf8427e4428de9314fe435764386cc2a72d9074";
    public static String vpc_RefundUrl = "http://125.235.40.34:8801/PaymentAPI/webresources/postData";
    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

    public static String hmac(String data, String key)throws java.security.SignatureException {
        String result;
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(data.getBytes());
            result = new String(Base64.getEncoder().encode(rawHmac));
        } catch (Exception e) {
            throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
        }
        return result;
    }
}
