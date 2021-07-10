package com.example.BookShop.repositories;

import com.example.BookShop.entity.book.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    @Query("from BookEntity")
    List<BookEntity> customFindAllBooks();

    //new book rest repository commands

    List<BookEntity> findBookEntityByBookIdListContaining(String authorName);

    List<BookEntity> findBookEntityByTitleContaining(String bookTitle);

    List<BookEntity> findBookEntityByPriceBetween(int discount, int discount2);

    List<BookEntity> findBookEntityByPriceIs(int price);

    @Query("FROM BookEntity WHERE isBestseller=1")
    List<BookEntity> getBestsellers();

    @Query(value = "SELECT * FROM book WHERE price = (SELECT MAX(price) FROM book)",
            nativeQuery = true)
    List<BookEntity> getBookWithMaxPrice();

    Page<BookEntity> findBookEntityByTitleContaining(String bookTitle, Pageable nextPage);
}
