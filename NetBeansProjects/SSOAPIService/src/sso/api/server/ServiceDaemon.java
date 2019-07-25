/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sso.api.server;

import firo.Firo;
import firo.utils.config.Config;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.hibernate.SessionFactory;

/**
 *
 * @author anbq
 */
public class ServiceDaemon {

    private static SessionFactory factory;

    public static void main(String[] args) throws Exception {
        Firo.getInstance().init(Config.getParamString("service", "host", "127.0.0.1"), Config.getParamInt("service", "port", 3003));
        Firo.getInstance().initializeControllerFromPackage("sso.api.controller", ServiceDaemon.class);
        
//        String token = generateToken("signingKey","anbq");
    }

   public static String generateToken(String signingKey, String subject) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, signingKey);

        return builder.compact();
    }
}
