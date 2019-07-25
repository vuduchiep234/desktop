/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sso.api.util;

import com.google.gson.Gson;
import sso.api.configuration.ConfigHelper;
import sso.api.controller.ObjectController;
import firo.utils.config.Config;
import java.lang.reflect.Constructor;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.WordUtils;

/**
 *
 * @author anbq
 */
public class ConfigUtil {
    private static ConfigUtil instance = null;
    private static String dbType = Config.getParam("database", "type");
    public synchronized static ConfigUtil getInstance() {
        if (instance == null) {
            instance = new ConfigUtil();
        }
        return instance;
    }
    
    public static boolean dbTypeSQL(){
        return dbType.equals("SQL");
    } 
    public static Object convertStringObjectToModel(String object) {
        try {
            String packageModel = ConfigHelper.getParamString("param", "model_package", "");
            Class<?> clazz = Class.forName(packageModel + WordUtils.capitalize(object));
            Constructor<?> constructor = clazz.getConstructor();
            return (Object) constructor.newInstance();
        } catch (Exception ex) {
            Logger.getLogger(ObjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static Object convertJsonToObject(String jsonData, Class clazz) {
        try {
            Gson gson = new Gson();
            Object object = gson.fromJson(jsonData, clazz);
            return object;
        } catch (Exception ex) {
            Logger.getLogger(ObjectController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
