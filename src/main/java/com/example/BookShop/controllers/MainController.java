package com.example.BookShop.controllers;

import com.example.BookShop.dto.AuthorDto;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class MainController {

    private final Logger logger = Logger.getLogger(MainController.class.getSimpleName());
    private final BookService bookService;
    private final BookMapper bookMapper;

    @Autowired
    public MainController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @ModelAttribute("recommendedBooks")
    public List<BookDto> recommendedBooks() {
//        return bookService.getPageOfBooks(0, 6).getContent();
        return bookMapper.bookEntityToBookDto(bookService.getPageOfBooks(0, 6).getContent());
    }

    @ModelAttribute("recentBooks")
    public List<BookDto> recentBooks() {
        logger.info(">>>>>>> MainController: recentBooks");
//        return bookService.getPageOfRecentBooks(0, 6).getContent();
        return bookMapper.bookEntityToBookDto(bookService.getPageOfRecentBooks(0, 6).getContent());
    }

    @ModelAttribute("popularBooks")
    public List<BookDto> popularBooks() {
//        return bookService.getPageOfPopularBooks(0, 6).getContent();
        return bookMapper.bookEntityToBookDto(bookService.getPageOfPopularBooks(0, 6).getContent());
    }

    @GetMapping("/books/recommended")
    @ResponseBody
    public BooksPageDto getRecommendedBooksPage(@RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        logger.info(">>>>>>> MainController: getRecommendedBooksPage");
        List<BookDto> bookDtoList;
        List<AuthorDto> authorDtoList;

        List<BookEntity> bookEntityList = bookService.getPageOfBooks(offset, limit).getContent();


        bookDtoList = bookMapper.bookEntityToBookDto(bookEntityList);

        return new BooksPageDto(bookDtoList);
    }

    @GetMapping("/books/recent")
    @ResponseBody
    public BooksPageDto getRecentBooksPage(@RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        logger.info(">>>>>>> MainController: getRecentBooksPage");
        List<BookDto> bookDtoList = new ArrayList<>();
//        bookMapper.bookDto(bookService.getPageOfRecentBooks(offset, limit).getContent(), bookDtoList);
        bookDtoList = bookMapper.bookEntityToBookDto(bookService.getPageOfRecentBooks(0, 6).getContent());
        return new BooksPageDto(bookDtoList);
    }

    @GetMapping("/books/popular")
    @ResponseBody
    public BooksPageDto getPopularBooksPage(@RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        List<BookDto> bookDtoList = new ArrayList<>();
//        bookMapper.bookDto(bookService.getPageOfBooks(offset, limit).getContent(), bookDtoList);
        bookDtoList = bookMapper.bookEntityToBookDto(bookService.getPageOfPopularBooks(0, 6).getContent());
        return new BooksPageDto(bookDtoList);
    }

    @GetMapping
    public String mainPage() {
        return "index";
    }

    @GetMapping("/tags")
    public String tagPage() {
        return "tags/index";
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("searchResults")
    public List<BookEntity> searchResults() {
        return new ArrayList<>();
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
