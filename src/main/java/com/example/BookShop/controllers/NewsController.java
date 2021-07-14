package com.example.BookShop.controllers;

import com.example.BookShop.dto.BookDto;
import com.example.BookShop.dto.BooksPageDto;
import com.example.BookShop.dto.SearchWordDto;
import com.example.BookShop.entity.book.BookEntity;
import com.example.BookShop.mappers.BookMapper;
import com.example.BookShop.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @Autowired
    public NewsController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("searchResults")
    public List<BookEntity> searchResults() {
        return new ArrayList<>();
    }

    @GetMapping
    public String news(Model model) {
        List<BookDto> bookDtoList = new ArrayList<>();
        bookMapper.bookDto(bookService
                .getPageOfRecentFromToBooks(new Date(), new Date(), 0, 20).getContent(), bookDtoList);

        model.addAttribute("recentBooks", bookDtoList);
        return "/books/recent";
    }

    @GetMapping("/books/recent")
    @ResponseBody
    public BooksPageDto getRecentBooksPage(@RequestParam("from") Date from, @RequestParam("to") Date to,
                                           @RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        List<BookDto> bookDtoList = new ArrayList<>();
        bookMapper.bookDto(bookService
                .getPageOfRecentFromToBooks(from, to, offset, limit).getContent(), bookDtoList);
        return new BooksPageDto(bookDtoList);
    }
}
