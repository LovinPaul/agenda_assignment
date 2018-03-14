package com.agenda.repository;

import com.agenda.model.Item;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class MySQLData implements Persistence {

    private static final MySQLData INSTACE = new MySQLData();
    private static final MySQLConnection MY_SQL_CONNECTION = new MySQLConnection();

    public static MySQLData GetINSTACE() {
        return INSTACE;
    }

    private MySQLData() {
    }

    @Override
    public void saveObject(Object object) {
        MY_SQL_CONNECTION.persistObj(object);
    }

    @Override
    public void updateObject(Object object) {
        MY_SQL_CONNECTION.updateObject(object);
    }

    @Override
    public void deleteObject(Object o) {
        MY_SQL_CONNECTION.deleteObject(o);
    }

    @Override
    public Item getItem(Long itemId) {
        String hql = String.format("SELECT i FROM Item i where i.id = %d", itemId);
        return (Item) MY_SQL_CONNECTION.getObject(hql);
    }

    @Override
    public List<Item> getItems() {
        // TODO : limit select query
        String hql = String.format("SELECT i FROM Item i");
        return  new ArrayList<Item>(MY_SQL_CONNECTION.getObjects(hql));
    }


    private static class MySQLConnection {

        private static final SessionFactory FACTORY = BuildFactory();

        private static SessionFactory BuildFactory() {
            try {
                return new Configuration().configure().buildSessionFactory();
            } catch (HibernateException ex) {
                System.err.println("Failed to create sessionFactory object." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }

        void persistObj(Object object) {
            Session session = FACTORY.openSession();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(object);
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw new RuntimeException(e);
            } finally {
                session.close();
            }
        }

        void updateObject(Object object) {
            Session session = FACTORY.openSession();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.saveOrUpdate(session.merge(object));
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw new RuntimeException(e);
            } finally {
                session.close();
            }
        }

        Object getObject(String hql) {
            Session session = FACTORY.openSession();
            Transaction tx = null;
            Object object = null;
            try {
                tx = session.beginTransaction();
                object = session.createQuery(hql).uniqueResult();
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw new RuntimeException(e);
            } finally {
                session.close();
            }
            return object;
        }

        Collection getObjects(String hql) {
            Session session = FACTORY.openSession();
            Transaction tx = null;
            Collection objects = null;
            try {
                tx = session.beginTransaction();
                objects = session.createQuery(hql).list();
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw new RuntimeException(e);
            } finally {
                session.close();
            }
            return objects;
        }

        void deleteObject(Object object) {
            Session session = FACTORY.openSession();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.delete(object);
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw new RuntimeException(e);
            } finally {
                session.close();
            }
        }

    }
}
