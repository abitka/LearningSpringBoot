package com.example.BookShop.dto;

import com.example.BookShop.entity.book.BookEntity;

import java.util.List;

public class RecommendedBooksDto {

    private int count;
    private List<BookEntity> books;

    public RecommendedBooksDto() {
    }

    public RecommendedBooksDto(List<BookEntity> books) {
        this.books = books;
        this.count = books.size();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }
}
