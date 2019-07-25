/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sso.api.conector.sql;

import sso.api.conector.mongodb.ExceptionsCollection;
import sso.api.configuration.Configuration;
import sso.api.model.DataResponse;
import sso.api.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author anbq
 */
public class ObjectSQLCollections {

    private static ObjectSQLCollections instance = null;
    private final JsonWriterSettings settings = JsonWriterSettings.builder()
            .outputMode(JsonMode.RELAXED)
            .objectIdConverter((value, writer) -> writer.writeString(value.toHexString()))
            .build();
    private static SessionFactory factory;
    private Transaction tx = null;
    private Session session = null;

    public synchronized static ObjectSQLCollections getInstance() {
        if (instance == null) {
            instance = new ObjectSQLCollections();
        }
        return instance;
    }

    public DataResponse create(String objectName, Object objectClass) {
        try {
            factory = new HibernateUtil().getSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();

            int id = (Integer) session.save(objectClass);
            if (id < 1) {
                return DataResponse.COUNTERS_ERROR;
            }

            tx.commit();
            return new DataResponse(new Document("id", id).toJson(settings),
                    DataResponse.DataType.JSON_STR,
                    false);
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public DataResponse get(Class objectClass, int id) {
        try {
            factory = new HibernateUtil().getSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();

            Object result = session.get(objectClass, id);

            tx.commit();
            return new DataResponse(result);
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public DataResponse getByCondition(Object object, JSONArray keys, Class objectClass, JSONObject jsonObject) {
        try {
            factory = new HibernateUtil().getSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(objectClass);
            for (int i = 0; i < keys.length(); i++) {
                criteria.add(Restrictions.eq(keys.getString(i), jsonObject.getString(keys.getString(i))));
            }
            Object objectResult = criteria.uniqueResult();

            tx.commit();
            return new DataResponse(objectResult);
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

//    public DataResponse getField(String objectName, int _id, String fieldname) {
//        try {
//
//        } catch (Exception ex) {
//            ExceptionsCollection.getInstance().addException(
//                    Configuration.SERVICE_NAME,
//                    ex.getStackTrace()[0].toString(),
//                    ex.toString());
//            return new DataResponse("-1", ex.getMessage());
//        }finally {
//            if (factory != null) {
//                factory.close();
//            }
//            if (session != null) {
//                session.close();
//            }
//        }
//    }
    public DataResponse update(String objectName, Object objectClass) {
        try {
            factory = new HibernateUtil().getSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();
            session.update(objectClass);

            tx.commit();
            return DataResponse.SUCCESSFUL;
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public DataResponse delete(Class objectClass, int id) {
        try {
            factory = new HibernateUtil().getSessionFactory();
            session = factory.openSession();
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
        } finally {
            if (factory != null) {
                factory.close();
            }
            if (session != null) {
                session.close();
            }
        }
    }

    public DataResponse deleteByCondition(Object object, JSONArray keys, Class objectClass, JSONObject jsonObject) {
        try {
            factory = new HibernateUtil().getSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(objectClass);
            for (int i = 0; i < keys.length(); i++) {
                criteria.add(Restrictions.eq(keys.getString(i), jsonObject.getString(keys.getString(i))));
            }
            object = criteria.uniqueResult();
            session.delete(object);

            tx.commit();
            return DataResponse.SUCCESSFUL;
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public DataResponse getList(Class objectClass, int numberOfSkip, int numberOfLimit) {
        try {
            factory = new HibernateUtil().getSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();
            List<Object> listResult = new ArrayList<>();
            listResult = session.createCriteria(objectClass)
                    .setFirstResult(numberOfSkip)
                    .setMaxResults(numberOfLimit)
                    .list();
            tx.commit();
            return new DataResponse(listResult);
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
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
            factory = new HibernateUtil().getSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(objectClass);
            for (int i = 0; i < keys.length(); i++) {
                criteria.add(Restrictions.like(keys.getString(i), jsonObject.getString(keys.getString(i)), MatchMode.ANYWHERE));
            }
            List<Object> listResult = new ArrayList<>();
            criteria.setFirstResult(numberOfSkip);
            criteria.setMaxResults(numberOfLimit);
            listResult = criteria.list();

            tx.commit();
            return new DataResponse(listResult);
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            return new DataResponse("-1", ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
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
}
