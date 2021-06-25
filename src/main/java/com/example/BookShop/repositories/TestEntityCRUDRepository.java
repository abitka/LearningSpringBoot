package com.example.BookShop.repositories;

import com.example.BookShop.entity.TestEntity;
import org.springframework.data.repository.CrudRepository;

public interface TestEntityCRUDRepository extends CrudRepository<TestEntity, Long> {

}
