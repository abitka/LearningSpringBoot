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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/news")
public class NewsController {

    private final Logger logger = Logger.getLogger(NewsController.class.getSimpleName());
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

    @ModelAttribute("recentBooks")
    public BooksPageDto recentBooks() throws ParseException {
        logger.info(">>>>>>> NewsController: recentBooks");
        List<BookDto> bookDtoList;
        bookDtoList = bookMapper.bookEntityToBookDto(bookService
                .getPageOfRecentBooks(0, 20).getContent());
        return new BooksPageDto(bookDtoList);
    }

    @GetMapping
    public String news(Model model) throws ParseException {
        logger.info(">>>>>>> news");
        List<BookDto> bookDtoList;
        bookDtoList = bookMapper.bookEntityToBookDto(bookService
                .getPageOfRecentBooks(0, 20).getContent());

        model.addAttribute("recentBooks", bookDtoList);
        return "/books/recent";
    }

    @GetMapping("/books/recent")
    @ResponseBody
    public BooksPageDto getRecentBooksDatePage(@RequestParam("from") String from, @RequestParam("to") String to,
                                           @RequestParam("offset") int offset, @RequestParam("limit") int limit) throws ParseException {
        logger.info(">>>>>>> getRecentBooksPage: from: " + from + " | to: " + to + " || offset: " + offset + " | limit: " + limit);
        List<BookDto> bookDtoList;
        bookDtoList = bookMapper.bookEntityToBookDto(bookService
                .getPageOfRecentFromToBooks(from, to, offset, limit).getContent());
        return new BooksPageDto(bookDtoList);
    }
}
