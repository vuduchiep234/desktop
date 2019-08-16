/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nttt.api.server;

import firo.Firo;
import firo.utils.config.Config;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;


/**
 *
 * @author anbq
 */
public class ServiceDaemon {

    private static SessionFactory factory;
    static Logger logger = Logger.getLogger(ServiceDaemon.class.getName());
    public static void main(String[] args) throws Exception {

        Firo.getInstance().init(Config.getParamString("service", "host", "127.0.0.1"), Config.getParamInt("service", "port", 2004));
        Firo.getInstance().initializeControllerFromPackage("nttt.api.controller", ServiceDaemon.class);
        logger.info("Start Service NenTangThanhToanAPIService");
    }
}
