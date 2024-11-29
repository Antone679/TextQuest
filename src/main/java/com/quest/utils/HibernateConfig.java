package com.quest.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfig {
    private static SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration()
                .addAnnotatedClass(com.quest.entity.User.class)
                .addAnnotatedClass(com.quest.entity.Quest.class)
                .addAnnotatedClass(com.quest.entity.Question.class)
                .addAnnotatedClass(com.quest.entity.Game.class)
                .buildSessionFactory();
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}