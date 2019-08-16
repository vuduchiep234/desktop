/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nttt.api.conectordb;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import nttt.api.configuration.Configuration;
import nttt.api.model.DataResponse;
import nttt.api.util.HibernateUtil;
import org.apache.commons.lang.WordUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author anbq
 */
public class ObjectCollections {

    private static ObjectCollections instance = null;
    private static SessionFactory factory;
    private Transaction tx = null;
    private Session session = null;

    public synchronized static ObjectCollections getInstance() {
        if (instance == null) {
            instance = new ObjectCollections();
        }
        return instance;
    }
    
    public DataResponse create(Object objectClass) {
        try {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();
            int id = (int) session.save(objectClass);
            if (id < 1) {
                return DataResponse.COUNTERS_ERROR;
            }
            tx.commit();
            return new DataResponse(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }
    }
    public DataResponse createList(List<Object> listObject) {
        try {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();
            for (Object listObject1 : listObject) {
                int id = (int) session.save(listObject1);
                if (id < 1) {
                    return DataResponse.COUNTERS_ERROR;
                }
            }
            
            tx.commit();
            return DataResponse.SUCCESSFUL;
        } catch (Exception ex) {
            ex.printStackTrace();
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }
    }
    public DataResponse get(Object object, int id) {
        try {
            factory = new HibernateUtil().getSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();
            Object result = session.get(object.getClass(), id);
            tx.commit();
            if (result == null) {
                return DataResponse.NOT_FOUND;
            }
            return new DataResponse(result);
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        } 
    }

    public DataResponse getByCondition(Object object, JSONObject jsonObject) {
        try {
            factory = new HibernateUtil().getSessionFactory();
            session = factory.openSession();
//            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(object.getClass());
            Iterator<String> keys = jsonObject.keys();
            String key;
            Object value;
            while (keys.hasNext()) {
                key = keys.next();
                value = jsonObject.get(key);
                criteria.add(Restrictions.eq(key, value));
            }
            Object objectResult = criteria.setMaxResults(1).uniqueResult();
//            tx.commit();
            if (objectResult == null) {
                return DataResponse.NOT_FOUND;
            }
            return new DataResponse(objectResult);
        } catch (Exception ex) {
            ex.printStackTrace();
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }
    }

    public DataResponse update(int id, Object object, JSONObject updateData) {
        try {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();
            Object currentObj = session.get(object.getClass(), id);
            Iterator iterator = updateData.keys();
            Method method;
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                Object value = updateData.get(key);
                Class fieldClass = currentObj.getClass().getDeclaredField(key).getType();
                method = currentObj.getClass().getMethod("set" + WordUtils.capitalize(key), fieldClass);
                method.invoke(currentObj, value);
            }
            session.update(currentObj);
            tx.commit();
            return DataResponse.SUCCESSFUL;
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        } 

    }

    public DataResponse delete(Class objectClass, int id) {
        try {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();
            Object deleteObject = session.get(objectClass, id);
            session.delete(deleteObject);
            tx.commit();
            return DataResponse.SUCCESSFUL;
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }
    }

    public DataResponse deleteByCondition(Object object, JSONObject jsonObject) {
        try {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(object.getClass());
            Iterator<String> iterator = jsonObject.keys();
            String key;
            Object value;
            while (iterator.hasNext()) {
                key = iterator.next();
                value = jsonObject.get(key);
                criteria.add(Restrictions.eq(key, value));
            }
            
            List<Object> listResult = criteria.list();
            for (Object listResult1 : listResult) {
                session.delete(listResult1);
            }

            tx.commit();
            return DataResponse.SUCCESSFUL;
        } catch (Exception ex) {
            ex.printStackTrace();
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        } 
    }

    public DataResponse getList(Class objectClass, int numberOfSkip, int numberOfLimit) {
        try {
            factory = new HibernateUtil().getSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(objectClass);
            List<Object> listResult = new ArrayList<>();
            criteria.setFirstResult(numberOfSkip);
            if (numberOfLimit > 0) {
                criteria.setMaxResults(numberOfLimit);
            }
            listResult = criteria.list();
            Map<String, Object> result = new HashMap<>();
            result.put("items", listResult);
            result.put("total", criteria.setFirstResult(0).setProjection(Projections.rowCount()).uniqueResult());
            tx.commit();
            return new DataResponse(result);
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }
    }

//    public DataResponse getListByField(String objectName, String fieldname, int numberOfSkip, int numberOfLimit) {
//        try {
//
//        } catch (Exception ex) {
//            ExceptionsCollection.getInstance().addException(
//                    Configuration.SERVICE_NAME,
//                    ex.getStackTrace()[0].toString(),
//                    ex.toString());
//            return new DataResponse("-1", ex.getMessage());
//        } finally {
//            if (factory != null) {
//                factory.close();
//            }
//            if (session != null) {
//                session.close();
//            }
//        }
//    }
    public DataResponse getListByCondition(Class objectClass, JSONArray keys, JSONObject jsonObject, int numberOfSkip, int numberOfLimit) {
        try {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();

            Criteria criteria = session.createCriteria(objectClass);
            String key;
            Object value;
            for (int i = 0; i < keys.length(); i++) {
                key = keys.getString(i);
                value = jsonObject.get(key);
                criteria.add(Restrictions.eq(key, value));
            }
            criteria.setFirstResult(numberOfSkip);
            if (numberOfLimit > 0) {
                criteria.setMaxResults(numberOfLimit);
            }
            Map<String, Object> result = new HashMap<>();
            result.put("items", criteria.list());
            result.put("total", criteria.setFirstResult(0).setProjection(Projections.rowCount()).uniqueResult());
            return new DataResponse(result);
        } catch (Exception ex) {
            ex.printStackTrace();
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        } 
    }
    
    public DataResponse getSuggestion(Class objectClass, JSONArray keys, JSONObject jsonObject, int numberOfSkip, int numberOfLimit) {
        try {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();

            Criteria criteria = session.createCriteria(objectClass);
            String key;
            Object value;
            for (int i = 0; i < keys.length(); i++) {
                key = keys.getString(i);
                value = jsonObject.get(key);
                criteria.add(Restrictions.like(key, value));
            }
            criteria.setFirstResult(numberOfSkip);
            if (numberOfLimit > 0) {
                criteria.setMaxResults(numberOfLimit);
            }
            Map<String, Object> result = new HashMap<>();
            result.put("items", criteria.list());
            result.put("total", criteria.setFirstResult(0).setProjection(Projections.rowCount()).uniqueResult());
            return new DataResponse(result);
        } catch (Exception ex) {
            ex.printStackTrace();
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        } 
    }

//    public DataResponse getListField(String objectName, JSONObject fileNames, int numberOfSkip, int numberOfLimit) {
//        try {
//
//        } catch (Exception ex) {
//            ExceptionsCollection.getInstance().addException(
//                    Configuration.SERVICE_NAME,
//                    ex.getStackTrace()[0].toString(),
//                    ex.toString());
//            return new DataResponse("-1", ex.getMessage());
//        } finally {
//            if (factory != null) {
//                factory.close();
//            }
//            if (session != null) {
//                session.close();
//            }
//        }
//    }
    
    public DataResponse getSearchWithOneKeyBetween(Class objectClass, JSONArray keys, JSONObject jsonObject, int numberOfSkip, int numberOfLimit, String name, long begin, long end) {
        try {
            factory = HibernateUtil.getSessionFactory();
            session = factory.openSession();
            Criteria criteria = session.createCriteria(objectClass);
            String key;
            Object value;
            for (int i = 0; i < keys.length(); i++) {
                key = keys.getString(i);
                value = jsonObject.get(key);
                if (value.toString().equalsIgnoreCase("all")) {
                    criteria.add(Restrictions.like(key, "%"));
                } else {
                    criteria.add(Restrictions.eq(key, value));
                }
            }
            if (begin > 0 && end > 0) {
                criteria.add(Restrictions.between(name, begin, end));
            }
            criteria.setFirstResult(numberOfSkip);
            if (numberOfLimit > 0) {
                criteria.setMaxResults(numberOfLimit);
            } 
            Map<String, Object> result = new HashMap<>();
            result.put("items", criteria.list());
            result.put("total", criteria.setFirstResult(0).setProjection(Projections.rowCount()).uniqueResult());   
            return new DataResponse(result);
        } catch (Exception ex) {
            ex.printStackTrace();
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        }

    }
    
}
