package com.example.bookshop.controllers;

import com.example.bookshop.dto.*;
import com.example.bookshop.entity.book.BookEntity;
import com.example.bookshop.mappers.BookMapper;
import com.example.bookshop.services.BookService;
import com.example.bookshop.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class MainController {

    private final Logger logger = Logger.getLogger(MainController.class.getSimpleName());
    private final BookService bookService;
    private final BookMapper bookMapper;
    private final TagService tagService;

    @Autowired
    public MainController(BookService bookService, BookMapper bookMapper, TagService tagService) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
        this.tagService = tagService;
    }

    @ModelAttribute("recommendedBooks")
    public List<BookDto> recommendedBooks() {
        return bookMapper.bookEntityToBookDto(bookService.getPageOfBooks(0, 6).getContent());
    }

    @ModelAttribute("recentBooks")
    public List<BookDto> recentBooks() {
        logger.info(">>>>>>> MainController: recentBooks");
        List<BookDto> bookDtoList = bookMapper.bookEntityToBookDto(bookService.getPageOfRecentBooks(0, 6).getContent());
        return bookDtoList;
    }

    @ModelAttribute("popularBooks")
    public List<BookDto> popularBooks() {
        return bookMapper.bookEntityToBookDto(bookService.getPageOfBookRatingAndPopularity(0, 6).getContent());
    }

    @GetMapping("/books/recommended")
    @ResponseBody
    public BooksPageDto getRecommendedBooksPage(@RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        logger.info(">>>>>>> MainController: getRecommendedBooksPage");
        List<BookDto> bookDtoList;
        List<BookEntity> bookEntityList = bookService.getPageOfBooks(offset, limit).getContent();

        bookDtoList = bookMapper.bookEntityToBookDto(bookEntityList);

        return new BooksPageDto(bookDtoList);
    }

    @GetMapping("/books/recent")
    @ResponseBody
    public BooksPageDto getRecentBooksPage(@RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        logger.info(">>>>>>> MainController: getRecentBooksPage");
        List<BookDto> bookDtoList;
        bookDtoList = bookMapper.bookEntityToBookDto(bookService.getPageOfRecentBooks(0, 6).getContent());
        return new BooksPageDto(bookDtoList);
    }

    @GetMapping("/books/popular")
    @ResponseBody
    public BooksPageDto getPopularBooksPage(@RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        List<BookDto> bookDtoList;
        bookDtoList = bookMapper.bookEntityToBookDto(bookService.getPageOfBookRatingAndPopularity(0, 6).getContent());
        return new BooksPageDto(bookDtoList);
    }

    @GetMapping
    public String mainPage() {
        return "index";
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("searchResults")
    public List<BookEntity> searchResults() {
        return new ArrayList<>();
    }

    @ModelAttribute("allTag")
    public List<TagDto> allTag() {
        logger.info(">>>>>>> allTag");
        List<TagDto> tagDtoList = tagService.allTagCountTagId();
        logger.info(">>>>>>> tagDtoList: " + tagDtoList.size());
        return tagDtoList;
    }

    @GetMapping(value = {"/search/{searchWord}"})
    public String getSearchResult(@PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto, Model model) {
        if (searchWordDto == null) return "/search/index";

        model.addAttribute("searchWordDto", searchWordDto);
        model.addAttribute("searchResults", bookService.getPageOfSearchResultBooks(searchWordDto.getExample(), 0, 5).getContent());

        return "/search/index";
    }

    @GetMapping("/search/page/{searchWord}")
    @ResponseBody
    public BooksPageDto getNextSearchPage(@RequestParam("offset") int offset,
                                          @RequestParam("limit") int limit,
                                          @PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto) {

        List<BookDto> bookDtoList = new ArrayList<>();
//        bookMapper.bookDto(bookService.getPageOfSearchResultBooks(searchWordDto.getExample(), offset, limit).getContent(), bookDtoList);
        bookDtoList = bookMapper.bookEntityToBookDto(bookService.getPageOfSearchResultBooks(searchWordDto.getExample(), offset, limit).getContent());
        return new BooksPageDto(bookDtoList);
    }
}
