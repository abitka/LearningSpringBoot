package com.example.BookShop.controllers;

import com.example.BookShop.entity.book.BookEntity;
import com.example.BookShop.services.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(description = "book data api")
public class BooksRestApiController {

    private final BookService bookService;

    @Autowired
    public BooksRestApiController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books/by-author")
    @ApiOperation("operation to get book list of book shop by passed author name")
    public ResponseEntity<List<BookEntity>> bookByAuthor(@RequestParam("author") String authorName) {
        return ResponseEntity.ok(bookService.getBookByAuthor(authorName));
    }

    @GetMapping("/books/by-title")
    @ApiOperation("operation to get book list of book shop by book title")
    public ResponseEntity<List<BookEntity>> bookByTitle(@RequestParam("title") String title) {
        return ResponseEntity.ok(bookService.getBookByTitle(title));
    }

    @GetMapping("/books/by-price-range")
    @ApiOperation("operation to get book by price range from min to max price")
    public ResponseEntity<List<BookEntity>> priceRangeBooks(
            @RequestParam("min") int min,
            @RequestParam("max") int max) {
        return ResponseEntity.ok(bookService.getBookByPriceBetween(min, max));
    }

    @GetMapping("/books/with-max-price")
    @ApiOperation("operation to get book with max price")
    public ResponseEntity<List<BookEntity>> getBookWithMaxPrice() {
        return ResponseEntity.ok(bookService.getBookWithMaxPrice());
    }

    @GetMapping("/books/bestsellers")
    @ApiOperation("get bestsellers books (which is_bestseller = 1)")
    public ResponseEntity<List<BookEntity>> getBestsellers() {
        return ResponseEntity.ok(bookService.getBestsellers());
    }
}
