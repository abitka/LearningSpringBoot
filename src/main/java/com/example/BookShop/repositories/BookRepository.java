package com.example.BookShop.repositories;

import com.example.BookShop.entity.book.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    @Query("from BookEntity")
    List<BookEntity> customFindAllBooks();
}
