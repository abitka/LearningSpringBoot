package com.example.BookShop.services;

import com.example.BookShop.entity.book.BookEntity;
import com.example.BookShop.repositories.BookRepository;
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

    public Page<BookEntity> getPageOfRecentFromToBooks(Date from, Date to, int offset, int limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
//        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(from);
        return bookRepository.findByPubDateBetweenOrderByPubDateDesc(from, to, nextPage);
    }

    public Page<BookEntity> getPageOfSearchResultBooks(String searchWord, int offset, int limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findBookEntityByTitleContaining(searchWord, nextPage);
    }
}
