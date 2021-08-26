package com.example.bookshop.controllers;

import com.example.bookshop.dto.BookDto;
import com.example.bookshop.dto.SearchWordDto;
import com.example.bookshop.entity.book.BookEntity;
import com.example.bookshop.mappers.BookMapper;
import com.example.bookshop.services.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/popular")
public class PopularController {

    private final Logger logger = LogManager.getLogger(PopularController.class);
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
    public List<BookDto> popularBooks() {
        logger.info(">>>>>>> PopularController: popularBooks");
        List<BookDto> bookDtoList;
        bookDtoList = bookMapper.bookEntityToBookDto(
                bookService.getPageOfBookRatingAndPopularity(0, 20).getContent());

        return bookDtoList;
    }
}