package com.example.BookShop.dao;

import com.example.BookShop.entity.TestEntity;
import org.springframework.stereotype.Repository;

@Repository
public class TestEntityDao extends AbstractHibernateDao<TestEntity> {

    public TestEntityDao() {
        super();
        settClass(TestEntity.class);
    }
}
