package com.example.BookShop.dto;

import java.util.List;

public class BooksPageDto {

    private int count;
    private List<BookDto> books;

    public BooksPageDto() {
    }

    public BooksPageDto(List<BookDto> books) {
        this.books = books;
        this.count = books.size();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<BookDto> getBooks() {
        return books;
    }

    public void setBooks(List<BookDto> books) {
        this.books = books;
    }
}
