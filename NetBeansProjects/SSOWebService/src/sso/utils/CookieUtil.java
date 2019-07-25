/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sso.utils;

import firo.Request;
import firo.Response;
import javax.servlet.http.Cookie;

/**
 *
 * @author anbq
 */
public class CookieUtil {

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
}
