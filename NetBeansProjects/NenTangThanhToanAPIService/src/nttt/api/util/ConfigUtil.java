/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nttt.api.util;

import com.google.gson.Gson;
import firo.utils.config.Config;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nttt.api.configuration.ConfigHelper;
import nttt.api.controller.ObjectController;
import org.apache.commons.lang.WordUtils;
import org.json.JSONArray;
import org.json.JSONObject;

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
    
    public static Object convertStringObjectToModel(String object) {
        try {
            String packageModel = ConfigHelper.getParamString("param", "model_package", "nttt.api.model.");
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
    public static List<Object> convertJsonToListObject(String jsonData, Class clazz) {
        try {
            Gson gson = new Gson();
            List<Object> listObject = new  ArrayList<>();
            JSONArray arrayData = new JSONArray(jsonData);
            for(int i =0; i< arrayData.length(); i++){
                JSONObject data = arrayData.getJSONObject(i);
                Object object = gson.fromJson(data.toString(), clazz);
                listObject.add(object);
            }
            
            return listObject;
        } catch (Exception ex) {
            Logger.getLogger(ObjectController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
