/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sso.api.service;

import sso.api.conector.mongodb.CountersCollection;
import sso.api.conector.mongodb.ExceptionsCollection;
import sso.api.conector.mongodb.ObjectMongoDBCollections;
import sso.api.conector.sql.ObjectSQLCollections;
import sso.api.configuration.Configuration;
import sso.api.model.DataResponse;
import static sso.api.util.ConfigUtil.convertJsonToObject;
import static sso.api.util.ConfigUtil.convertStringObjectToModel;
import static sso.api.util.ConfigUtil.dbTypeSQL;
import java.lang.reflect.Method;
import org.bson.Document;
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

    public DataResponse create(String object, String sData) {
        try {
            if (dbTypeSQL()) {
                Object objectClass = convertStringObjectToModel(object);
                Class model = objectClass.getClass();
                objectClass = convertJsonToObject(sData, model);
                return ObjectSQLCollections.getInstance().create(object, objectClass);
            } else {
                Document document = Document.parse(sData);
                Long id = CountersCollection.getInstance().getNextValue(object);
                if (id <= 0) {
                    return DataResponse.COUNTERS_ERROR;
                }
                document.append("id", id);
                return ObjectMongoDBCollections.getInstance().create(object, document);
            }
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }
    }

    public DataResponse get(String object, Long id) {
        try {
            if (dbTypeSQL()) {
                Object obj = convertStringObjectToModel(object);
                Class objectClass = obj.getClass();
                return ObjectSQLCollections.getInstance().get(objectClass, id.intValue());
            } else {
                return ObjectMongoDBCollections.getInstance().get(object, id);
            }
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
            if (dbTypeSQL()) {
                JSONObject jsonObject = new JSONObject(sData);
                JSONArray keys = jsonObject.names();
                Object objectModel = convertStringObjectToModel(object);
                Class model = objectModel.getClass();
                return ObjectSQLCollections.getInstance().getByCondition(objectModel, keys, model, jsonObject);
            } else {
                Document document = Document.parse(sData);
                return ObjectMongoDBCollections.getInstance().getByCondition(object, document);
            }
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }
    }

//	public DataResponse getField(String object, Long _id, String fieldname) {
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
    public DataResponse update(String object, Long id, String sData) {
        try {
            if (dbTypeSQL()) {
                Object objectClass = convertStringObjectToModel(object);
                Class model = objectClass.getClass();
                objectClass = convertJsonToObject(sData, model);
                Method setNameMethod = objectClass.getClass().getMethod("setId", int.class);
                Integer objectID = id != null ? id.intValue() : null;
                setNameMethod.invoke(objectClass, objectID);
                return ObjectSQLCollections.getInstance().update(object, objectClass);
            } else {
                Document document = Document.parse(sData);
                return ObjectMongoDBCollections.getInstance().update(object, id, document);
            }
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }
    }

    public DataResponse delete(String object, String id) {
        try {
            if (dbTypeSQL()) {
                Object objectModel = convertStringObjectToModel(object);
                Class objectClass = objectModel.getClass();
                return ObjectSQLCollections.getInstance().delete(objectClass, Integer.parseInt(id));
            } else {
                return ObjectMongoDBCollections.getInstance().delete(object, Long.parseLong(id));
            }
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
            if (dbTypeSQL()) {
                JSONObject jsonObject = new JSONObject(sData);
                JSONArray keys = jsonObject.names();
                Object objectClass = convertStringObjectToModel(object);
                Class model = objectClass.getClass();
                return ObjectSQLCollections.getInstance().deleteByCondition(objectClass, keys, model, jsonObject);
            } else {
                Document document = Document.parse(sData);
                return ObjectMongoDBCollections.getInstance().deleteByCondition(object, document);
            }
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
            if (dbTypeSQL()) {
                Object obj = convertStringObjectToModel(object);
                Class objectClass = obj.getClass();
                return ObjectSQLCollections.getInstance().getList(objectClass, numberOfSkip, numberOfLimit);
            } else {
                return ObjectMongoDBCollections.getInstance().getList(object, numberOfSkip, numberOfLimit);
            }
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
            if (dbTypeSQL()) {
                JSONObject jsonObject = new JSONObject(sData);
                JSONArray keys = jsonObject.names();
                Object obj = convertStringObjectToModel(object);
                Class objectClass = obj.getClass();
                return ObjectSQLCollections.getInstance().getListByCondition(objectClass, keys, jsonObject, numberOfSkip, numberOfLimit);
            } else {
                Document document = Document.parse(sData);
                return ObjectMongoDBCollections.getInstance().getListByCondition(object, document, numberOfSkip, numberOfLimit);
            }
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }
    }
//        public DataResponse getListField(String object,Document fieldNames, int numberOfSkip, int numberOfLimit) {
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
}
