package com.example.BookShop.controllers;

import com.example.BookShop.dto.BookDto;
import com.example.BookShop.dto.SearchWordDto;
import com.example.BookShop.entity.book.BookEntity;
import com.example.BookShop.mappers.BookMapper;
import com.example.BookShop.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/popular")
public class PopularController {

    private final Logger logger = Logger.getLogger(NewsController.class.getSimpleName());
    private final BookService bookService;
    private final BookMapper bookMapper;

    @Autowired
    public PopularController(BookService bookService, BookMapper bookMapper) {
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
    public String popular() {
        return "/books/popular";
    }

    @ModelAttribute("popularBooks")
    public List<BookDto> popularBooks() throws ParseException {
        logger.info(">>>>>>> PopularController: popularBooks");
        List<BookDto> bookDtoList = new ArrayList<>();
        bookMapper.bookDto(bookService.getPageOfPopularBooks(0, 20).getContent(), bookDtoList);
        return bookDtoList;
    }
}
