/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nttt.api.configuration;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hung
 */
public class ConfigNapas {
    public static String vpc_PayUrl = "https://sandbox.napas.com.vn/gateway/vpcpay.do";
//    public static String vpc_Returnurl_MCDT = "http://localhost:8080/vnpay_jsp/vnpay_return.jsp";
//    public static String vpc_TmnCode = "ADPKENH1";
    public static String hashSecret = "198BE3F2E8C75A53F38C1C4A5B6DBA27";
    public static String vpc_RefundUrl = "https://sandbox.napas.com.vn/gateway/vpcdps";
    static final char[] HEX_TABLE = new char[]{
        '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    
    public static String md5(Map fields) {
        // create a list and sort it
        List fieldNames = new ArrayList(fields.keySet());
        Collections.sort(fieldNames);

        // create a buffer for the md5 input and add the secure secret first
        StringBuffer buf = new StringBuffer();
        buf.append(hashSecret);

        // iterate through the list and add the remaining field values
        Iterator itr = fieldNames.iterator();

        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) fields.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                System.out.println(fieldName + ": " + fieldValue);
                buf.append(fieldValue);
            }
        }

        MessageDigest md5 = null;
        byte[] ba = null;
        System.out.println("input: " + buf.toString());
        // create the md5 hash and UTF-8 encode it
        try {
            md5 = MessageDigest.getInstance("MD5");
            ba = md5.digest(buf.toString().getBytes("UTF-8"));
        } catch (Exception e) {
        } // wont happen

        //return buf.toString();
        return hex(ba);
    }
    
    private static String hex(byte[] input) {
        // create a StringBuffer 2x the size of the hash array
        StringBuffer sb = new StringBuffer(input.length * 2);

        // retrieve the byte array data, convert it to hex
        // and add it to the StringBuffer
        for (int i = 0; i < input.length; i++) {
            sb.append(HEX_TABLE[(input[i] >> 4) & 0xf]);
            sb.append(HEX_TABLE[input[i] & 0xf]);
        }
        return sb.toString();
    }
}
