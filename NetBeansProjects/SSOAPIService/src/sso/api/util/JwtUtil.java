/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sso.api.util;

import io.jsonwebtoken.Jwts;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author anbq
 */
public class JwtUtil {
    public static String generateToken(String signingKey, String subject) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Random random = new Random();
//        JwtBuilder builder = Jwts.builder()
//                .setSubject(subject)
//                .setIssuedAt(now)
//                .signWith(SignatureAlgorithm.HS256, signingKey);
//
//        return builder.compact();
        return genToken(signingKey,subject, Integer.toString(random.nextInt()));
    }

    public static String getSubject(HttpServletRequest httpServletRequest, String jwtTokenCookieName, String signingKey){
        String token = CookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
        if(token == null) return null;
        return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody().getSubject();
    }
    private static String genToken(String key,String username, String salt) {
        try {
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);
            String saltedAndHashed = username +now +"," + salt;
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(saltedAndHashed.getBytes());
            byte hashedBytes[] = (new String(digest.digest(), "UTF-8")).getBytes();
            return Base64.getEncoder().encodeToString(hashedBytes) + "," + salt;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 is not available", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 unavailable?  Not a chance", e);
        }
    }
}
