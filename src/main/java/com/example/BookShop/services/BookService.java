package com.example.BookShop.services;

import com.example.BookShop.entity.book.BookEntity;
import com.example.BookShop.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    //new books service methods

    public List<BookEntity> getBookByAuthor(String authorName) {
        return bookRepository.findBookEntityByBookIdListContaining(authorName);
    }

    public List<BookEntity> getBookByTitle(String bookTitle) {
        return bookRepository.findBookEntityByTitleContaining(bookTitle);
    }

    public List<BookEntity> getBookByPriceBetween(int min, int max) {
        return bookRepository.findBookEntityByPriceBetween(min, max);
    }

    public List<BookEntity> getBookByPriceIs(int price) {
        return bookRepository.findBookEntityByPriceIs(price);
    }

    public List<BookEntity> getBestsellers() {
        return bookRepository.getBestsellers();
    }

    public List<BookEntity> getBookWithMaxPrice() {
        return bookRepository.getBookWithMaxPrice();
    }

}
