/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nttt.api.service;

import java.util.List;
import nttt.api.conectordb.ExceptionsCollection;
import nttt.api.conectordb.ObjectCollections;
import nttt.api.configuration.Configuration;
import nttt.api.model.DataResponse;
import static nttt.api.util.ConfigUtil.convertJsonToListObject;
import static nttt.api.util.ConfigUtil.convertJsonToObject;
import static nttt.api.util.ConfigUtil.convertStringObjectToModel;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author anbq
 */
public class ObjectService {

    private static ObjectService instance = null;

    public synchronized static ObjectService getInstance() {
        if (instance == null) {
            instance = new ObjectService();
        }
        return instance;
    }

    public DataResponse create(String objectName, String sData) {
        try {
            Object object = convertStringObjectToModel(objectName);
            Class model = object.getClass();
            object = convertJsonToObject(sData, model);
            return ObjectCollections.getInstance().create(object);
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }
    }
    public DataResponse createList(String objectName, String sData) {
        try {
            Object object = convertStringObjectToModel(objectName);
            Class model = object.getClass();
            List<Object> listObject = convertJsonToListObject(sData, model);
            return ObjectCollections.getInstance().createList(listObject);
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }
    }

    public DataResponse get(String object, int id) {
        try {
            Object obj = convertStringObjectToModel(object);
            return ObjectCollections.getInstance().get(obj, id);

        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }
    }

    public DataResponse getByCondition(String object, String sData) {
        try {
            JSONObject jsonObject = new JSONObject(sData);
            Object objectModel = convertStringObjectToModel(object);
            return ObjectCollections.getInstance().getByCondition(objectModel, jsonObject);

        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }
    }

    public DataResponse update(String objectName, int id, String sData) {
        try {
            Object object = convertStringObjectToModel(objectName);
            JSONObject jsonObj = new JSONObject(sData);
            return ObjectCollections.getInstance().update(id, object, jsonObj);
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }
    }

    public DataResponse delete(String object, int id) {
        try {
            Object objectModel = convertStringObjectToModel(object);
            Class objectClass = objectModel.getClass();
            return ObjectCollections.getInstance().delete(objectClass, id);

        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }
    }

    public DataResponse deleteByCondition(String object, String sData) {
        try {
            JSONObject jsonObject = new JSONObject(sData);
            Object objectClass = convertStringObjectToModel(object);
            return ObjectCollections.getInstance().deleteByCondition(objectClass, jsonObject);

        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }
    }

    public DataResponse getList(String object, int numberOfSkip, int numberOfLimit) {
        try {
            Object obj = convertStringObjectToModel(object);
            Class objectClass = obj.getClass();
            return ObjectCollections.getInstance().getList(objectClass, numberOfSkip, numberOfLimit);

        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }
    }
//        public DataResponse getListByField(String object,String fieldname, int numberOfSkip, int numberOfLimit) {
//		try {
//			
//		}
//		catch (Exception ex) {
//			ExceptionsCollection.getInstance().addException(
//					Configuration.SERVICE_NAME,
//					ex.getStackTrace()[0].toString(),
//					ex.toString());
//			return new DataResponse("-1", ex.getMessage());
//		}
//	}

    public DataResponse getListByCondition(String object, String sData, int numberOfSkip, int numberOfLimit) {
        try {
            JSONObject jsonObject = new JSONObject(sData);
            JSONArray keys = jsonObject.names();
            Object obj = convertStringObjectToModel(object);
            Class objectClass = obj.getClass();
            return ObjectCollections.getInstance().getListByCondition(objectClass, keys, jsonObject, numberOfSkip, numberOfLimit);

        } catch (Exception ex) {
            ex.printStackTrace();
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }
    }
    
     public DataResponse getSuggestionByCondition(String object, String sData, int numberOfSkip, int numberOfLimit) {
        try {
            JSONObject jsonObject = new JSONObject(sData);
            JSONArray keys = jsonObject.names();
            Object obj = convertStringObjectToModel(object);
            Class objectClass = obj.getClass();
            return ObjectCollections.getInstance().getSuggestion(objectClass, keys, jsonObject, numberOfSkip, numberOfLimit);

        } catch (Exception ex) {
            ex.printStackTrace();
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }
    }
    
        public DataResponse getSearchWithOneKeyBetween(String object, String sData, int numberOfSkip, int numberOfLimit, String name, long begin, long end) {
        try {
            JSONObject jsonObject = new JSONObject(sData);
            JSONArray keys = jsonObject.names();
            Object obj = convertStringObjectToModel(object);
            Class objectClass = obj.getClass();
            return ObjectCollections.getInstance().getSearchWithOneKeyBetween(objectClass, keys, jsonObject, numberOfSkip, numberOfLimit, name, begin, end);

        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }
    }
        
}
