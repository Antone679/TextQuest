package com.quest.repository;

import com.quest.entity.User;
import com.quest.utils.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserRepository {

    private Session session;

    public UserRepository() {
        this.session = HibernateConfig.getSessionFactory().openSession();
    }

    public void saveUser(User user) {
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();

        } catch (Exception ex) {
            if (transaction != null)
                transaction.rollback();

            ex.printStackTrace();
        }
    }

    public User getUser(int id) {
        return session.get(User.class, id);
    }

    public boolean checkLogin(String name) {
        try {
            Query<User> query = session.createQuery("SELECT u FROM User u where u.name = :name", User.class);
            query.setParameter("name", name);
            return query.uniqueResult() != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkPassword(String password) {
        try {
            Query<User> query = session.createQuery("SELECT u FROM User u where u.password = :password", User.class);
            query.setParameter("password", password);
            return query.uniqueResult() != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
