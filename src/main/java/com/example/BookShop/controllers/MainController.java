package com.example.BookShop.controllers;

import com.example.BookShop.dto.RecommendedBooksDto;
import com.example.BookShop.entity.book.BookEntity;
import com.example.BookShop.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    private final BookService bookService;

    @Autowired
    public MainController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("recommendedBooks")
    public List<BookEntity> recommendedBooks() {
        return bookService.getPageOfRecommendedBooks(0, 6).getContent();
    }

    @GetMapping
    public String mainPage() {
        return "index";
    }

    @GetMapping("/books/recommended")
    @ResponseBody
    public RecommendedBooksDto getBooksPage(@RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        return new RecommendedBooksDto(bookService.getPageOfRecommendedBooks(offset, limit).getContent());
    }
}
