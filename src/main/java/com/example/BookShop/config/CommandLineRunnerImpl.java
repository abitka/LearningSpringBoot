package com.example.BookShop.config;

import com.example.BookShop.dao.TestEntityDao;
import com.example.BookShop.entity.TestEntity;
import org.aspectj.weaver.ast.Test;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.logging.Logger;

@Configuration
public class CommandLineRunnerImpl  implements CommandLineRunner {

    EntityManagerFactory managerFactory;
    TestEntityDao testEntityDao;

    @Autowired
    public CommandLineRunnerImpl(EntityManagerFactory managerFactory, TestEntityDao testEntityDao) {
        this.managerFactory = managerFactory;
        this.testEntityDao = testEntityDao;
    }


    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 5; i++) {
            createTestEntity(new TestEntity());
        }

        TestEntity readTestEntity = testEntityDao.findOne(3L);//readTestEntityById(3L);
        if (readTestEntity != null)
            Logger.getLogger(CommandLineRunnerImpl.class.getSimpleName())
                    .info("readTestEntityById ->" + readTestEntity.toString());
        else
            throw new NullPointerException();

        TestEntity updateTestEntity = updateTestEntityById(3L);
        if (updateTestEntity != null)
            Logger.getLogger(CommandLineRunnerImpl.class.getSimpleName())
                    .info("updateTestEntityById ->" + updateTestEntity.toString());
        else
            throw new NullPointerException();

        deleteTestEntityById(3L);
    }

    private void deleteTestEntityById(long id) {
        Session session = managerFactory.createEntityManager().unwrap(Session.class);
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            TestEntity findById = readTestEntityById(id);
            TestEntity mergedTestEntity = (TestEntity) session.merge(findById);
            session.remove(mergedTestEntity);
            transaction.commit();
        } catch (HibernateException hex) {
            if (transaction != null)
                transaction.rollback();
            else
                hex.printStackTrace();
        } finally {
            session.close();
        }
    }

    private TestEntity updateTestEntityById(long id) {
        Session session = managerFactory.createEntityManager().unwrap(Session.class);
        Transaction transaction = null;
        TestEntity entity = null;

        try {
            transaction = session.beginTransaction();
            TestEntity findById = readTestEntityById(id);
            findById.setData("new data");
            entity = (TestEntity) session.merge(findById);
            transaction.commit();
        } catch (HibernateException hex) {
            if (transaction != null)
                transaction.rollback();
            else
                hex.printStackTrace();
        } finally {
            session.close();
        }
        return entity;
    }

    private TestEntity readTestEntityById(long id) {
        Session session = managerFactory.createEntityManager().unwrap(Session.class);
        Transaction transaction = null;
        TestEntity entity = null;

        try {
            transaction = session.beginTransaction();
            entity = session.find(TestEntity.class, id);
            transaction.commit();
        } catch (HibernateException hex) {
            if (transaction != null)
                transaction.rollback();
            else
                hex.printStackTrace();
        } finally {
            session.close();
        }
        return entity;
    }

    private void createTestEntity(TestEntity entity) {
        Session session = managerFactory.createEntityManager().unwrap(Session.class);
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            entity.setData(entity.getClass().getSimpleName() + entity.hashCode());
            session.save(entity);
            transaction.commit();
        } catch (HibernateException hex) {
            if (transaction != null)
                transaction.rollback();
            else
                hex.printStackTrace();
        } finally {
            session.close();
        }
    }
}
