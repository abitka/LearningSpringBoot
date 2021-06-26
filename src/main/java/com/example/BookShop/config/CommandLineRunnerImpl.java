package com.example.BookShop.config;

import com.example.BookShop.entity.TestEntity;
import com.example.BookShop.repositories.BookRepository;
import com.example.BookShop.repositories.TestEntityCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class CommandLineRunnerImpl  implements CommandLineRunner {

    TestEntityCRUDRepository crudRepository;
    BookRepository bookRepository;

    @Autowired
    public CommandLineRunnerImpl(TestEntityCRUDRepository crudRepository, BookRepository bookRepository) {
        this.crudRepository = crudRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        /*for (int i = 0; i < 5; i++) {
            createTestEntity(new TestEntity());
        }

        TestEntity readTestEntity = readTestEntityById(3L);
        if (readTestEntity != null)
            Logger.getLogger(CommandLineRunnerImpl.class.getSimpleName())
                    .info("readTestEntityById ->" + readTestEntity.toString());
        else
            throw new NullPointerException();

        TestEntity updateTestEntity = updateTestEntityById(1L);
        if (updateTestEntity != null)
            Logger.getLogger(CommandLineRunnerImpl.class.getSimpleName())
                    .info("updateTestEntityById ->" + updateTestEntity.toString());
        else
            throw new NullPointerException();

        deleteTestEntityById(3L);*/

        Logger.getLogger(CommandLineRunnerImpl.class.getSimpleName())
                .info("first name\n" + bookRepository.findBooksByAuthor_FirstName("Had").toString());

        Logger.getLogger(CommandLineRunnerImpl.class.getSimpleName())
                .info("customFindAllBooks\n" + bookRepository.customFindAllBooks().toString());
    }

    private void deleteTestEntityById(long id) {
        crudRepository.deleteById(id);
    }

    private TestEntity updateTestEntityById(long id) {
        TestEntity testEntity = crudRepository.findById(id).get();
        testEntity.setData("new data");
        crudRepository.save(testEntity);
        return testEntity;
    }

    private TestEntity readTestEntityById(long id) {
        return crudRepository.findById(id).get();
    }

    private void createTestEntity(TestEntity entity) {
        entity.setData(entity.getClass().getSimpleName() + entity.hashCode());
        crudRepository.save(entity);
    }
}
