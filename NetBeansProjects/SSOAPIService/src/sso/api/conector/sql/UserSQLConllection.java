/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sso.api.conector.sql;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import sso.api.conector.mongodb.ExceptionsCollection;
import sso.api.configuration.Configuration;
import sso.api.model.SessionUser;
import sso.api.model.User;
import sso.api.util.HibernateUtil;

/**
 *
 * @author anbq
 */
public class UserSQLConllection {

    private static UserSQLConllection instance = null;
    private static SessionFactory factory;
    private Transaction tx = null;
    private Session session = null;

    public synchronized static UserSQLConllection getInstance() {
        if (instance == null) {
            instance = new UserSQLConllection();
        }
        return instance;
    }

    public User getUserByField(String fileName,String fileValue) {
        try {
            factory = new HibernateUtil().getSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq(fileName, fileValue));
            User user = (User) criteria.uniqueResult();

            tx.commit();
            return user;
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
    public User getUserLogin(String userName) {
        try {
            factory = new HibernateUtil().getSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.disjunction()
                .add(Restrictions.eq("tenDangNhap", userName))
                .add(Restrictions.eq("soDienThoai", userName))
                .add(Restrictions.eq("email", userName))
            );
            User user = (User) criteria.uniqueResult();

            tx.commit();
            return user;
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
    public User getUserSync(String soGiayTo, String hoVaTen, String ngaySinh) {
        try {
            factory = new HibernateUtil().getSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("soGiayTo", soGiayTo));
            criteria.add(Restrictions.eq("hoVaTen", hoVaTen));
            criteria.add(Restrictions.eq("ngaySinh", ngaySinh));
            User user = (User) criteria.uniqueResult();

            tx.commit();
            return user;
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
    public User getUserByField(String fileName,int fileValue) {
        try {
            factory = new HibernateUtil().getSessionFactory();
            session = factory.getCurrentSession();
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq(fileName, fileValue));
            User user = (User) criteria.uniqueResult();

            tx.commit();
            return user;
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            
        } finally {
//            if (session != null) {
//                session.close();
//            }
        }
        return null;
    }
    public SessionUser getSessionUserBySessionKey(String sessionKey) {
        try {
            factory = new HibernateUtil().getSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(SessionUser.class);
            criteria.add(Restrictions.eq("sessionKey", sessionKey));
            SessionUser sessionUser = (SessionUser) criteria.uniqueResult();

            tx.commit();
            return sessionUser;
        } catch (Exception ex) {
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
            
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
    public int addSession(SessionUser sessionUser) {
        try {
            factory = new HibernateUtil().getSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();
            
            Criteria criteria = session.createCriteria(sessionUser.getClass());
            criteria.add(Restrictions.eq("nguoiDungId",sessionUser.getNguoiDungId()));
           
            Object obj = criteria.uniqueResult();
            if(obj!= null){
                session.delete(obj);
            }
            
            int id = (Integer) session.save(sessionUser);
            tx.commit();
            return id;
        } catch (Exception ex) {
            if (tx != null) {
                    tx.rollback();
                }
            ExceptionsCollection.getInstance().addException(
                    Configuration.SERVICE_NAME,
                    ex.getStackTrace()[0].toString(),
                    ex.toString());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return -1;
    }
}
