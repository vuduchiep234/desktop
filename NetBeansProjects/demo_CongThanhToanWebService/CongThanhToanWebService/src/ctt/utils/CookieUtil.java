/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctt.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import ctt.configuration.ConfigHelper;
import firo.Request;
import firo.Response;
import java.util.Map;
import javax.servlet.http.Cookie;

/**
 *
 * @author anbq
 */
public class CookieUtil {
    public  static String tokenCookieName = "CONGTHANHTOAN_TOKEN";
    public static void create(Response response, String name, String value, Boolean secure, Integer maxAge) {
        Cookie cookie = new Cookie(name, value);
//        cookie.setSecure(secure);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.raw().addCookie(cookie);
    }

    public static void clear(Response response, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath("/");
//        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        response.raw().addCookie(cookie);
    }

    public static String getValue(Request request, String name) {
        try {
            Cookie[] cookies = request.raw().getCookies();
            if(cookies == null){
                return null;
            }
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return (cookie.getValue());
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        return null;
    }
    public static void create(Response response, String name, String value, Boolean secure, Integer maxAge,String domain) {
        Cookie cookie = new Cookie(name, value);
//        cookie.setSecure(secure);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        cookie.setDomain(domain);        
        response.raw().addCookie(cookie);
    }
    public static DecodedJWT getDecodedJWT(String token){
        try{
            String secret = ConfigHelper.getParamString("JWT","JWT_KEY", "");
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt;
        }catch(Exception e){
            return null;
        }
    }
    public static Map<String, Claim> getClaims(String token){
        try{
            return getDecodedJWT(token).getClaims();
        }catch(Exception e){
            return null;
        }
    }
    public static String getValueTokenKey(String token,String key){
        return getClaims(token).get(key).asString();
    }
}
