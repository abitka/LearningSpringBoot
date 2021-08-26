package com.example.bookshop.services;

import com.example.bookshop.dto.BookDto;
import com.example.bookshop.entity.book.BookEntity;
import com.example.bookshop.mappers.BookMapper;
import com.example.bookshop.repositories.BookRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper = Mappers.getMapper(BookMapper.class);

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookEntity> getBooksData() {
        return bookRepository.findAll();
    }

    public Page<BookEntity> getPageOfBooks(int offset, int limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findAll(nextPage);
    }

    public Page<BookEntity> getPageOfRecentBooks(int offset, int limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findByOrderByPubDateDesc(nextPage);
    }

    /**
     * Популярность книги представляет собой неотрицательное число, которое можно рассчитать по следующей формуле:
     *
     * P = B + 0,7 * C + 0,4 * K,
     *
     * B — количество пользователей, купивших книгу,
     * C — количество пользователей, у которых книга находится в корзине, а
     * K — количество пользователей, у которых книга отложена.*/
    public Page<BookEntity> getPageOfBookRatingAndPopularity(int offset, int limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findByOrderByBookRatingAndPopularityDesc(nextPage);
    }

    public Page<BookEntity> getPageOfRecentFromToBooks(String from, String to, int offset, int limit) throws ParseException {
        Pageable nextPage = PageRequest.of(offset, limit);
        Date dateFrom = new SimpleDateFormat("dd.MM.yyyy").parse(from);
        Date dateTo = new SimpleDateFormat("dd.MM.yyyy").parse(to);
        return bookRepository.findByPubDateBetweenOrderByPubDateDesc(dateFrom, dateTo, nextPage);
    }

    public Page<BookEntity> getPageOfSearchResultBooks(String searchWord, int offset, int limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findBookEntityByTitleContaining(searchWord, nextPage);
    }

    public List<BookEntity> getBookWithTagIdContains(Integer tagId, int offset, int limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findBookEntityByByTagIdContains(tagId, nextPage);
    }

    public List<BookDto> getBooksThisAuthor(String slug) {
        return bookMapper.bookEntityToBookDto(bookRepository.findBookByThisAuthor(slug));
    }
}