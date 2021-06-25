package com.example.BookShop.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;

@Repository
public abstract class AbstractHibernateDao<T> {

    @Autowired
    EntityManagerFactory managerFactory;

    private Class<T> tClass;

    public void settClass(Class<T> tClass) {
        this.tClass = tClass;
    }

    public T findOne(Long id) {
        return getSession().find(tClass, id);
    }

    public Session getSession() {
        return managerFactory.createEntityManager().unwrap(Session.class);
    }
}
