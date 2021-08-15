package com.example.BookShop.repositories;

import com.example.BookShop.entity.book.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    @Query("FROM BookEntity book JOIN Book2TagEntity book2tag ON book.id = book2tag.bookId " +
            "WHERE book2tag.tagId=:tagId")
    List<BookEntity> findBookEntityByByTagIdContains(Integer tagId, Pageable nextPage);

    @Query("FROM BookEntity book JOIN Book2AuthorEntity book2author ON book.id = book2author.bookId " +
            "WHERE book2author.authorId = (FROM AuthorEntity author WHERE author.slug = :slug)")
    List<BookEntity> findBookByThisAuthor(String slug);

    Page<BookEntity> findBookEntityByTitleContaining(String bookTitle, Pageable nextPage);

    Page<BookEntity> findByOrderByPubDateDesc(Pageable nextPage);

    Page<BookEntity> findByOrderByBookRatingAndPopularityDesc(Pageable nextPage);

    Page<BookEntity> findByPubDateBetweenOrderByPubDateDesc(Date from, Date to, Pageable nextPage);
}
