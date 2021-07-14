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

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @Autowired
    public MainController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @ModelAttribute("recommendedBooks")
    public List<BookEntity> recommendedBooks() {
        return bookService.getPageOfBooks(0, 6).getContent();
    }

    @ModelAttribute("recentBooks")
    public List<BookEntity> recentBooks() {
        return bookService.getPageOfRecentBooks(0, 6).getContent();
    }

    @ModelAttribute("popularBooks")
    public List<BookEntity> popularBooks() {
        return bookService.getPageOfBooks(0, 6).getContent();
    }

    @GetMapping("/books/recommended")
    @ResponseBody
    public BooksPageDto getRecommendedBooksPage(@RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        List<BookDto> bookDtoList = new ArrayList<>();
        bookMapper.bookDto(bookService.getPageOfBooks(offset, limit).getContent(), bookDtoList);
        return new BooksPageDto(bookDtoList);
    }

    @GetMapping("/books/recent")
    @ResponseBody
    public BooksPageDto getRecentBooksPage(@RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        List<BookDto> bookDtoList = new ArrayList<>();
        bookMapper.bookDto(bookService.getPageOfRecentBooks(offset, limit).getContent(), bookDtoList);
        return new BooksPageDto(bookDtoList);
    }

    @GetMapping("/books/popular")
    @ResponseBody
    public BooksPageDto getPopularBooksPage(@RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        List<BookDto> bookDtoList = new ArrayList<>();
        bookMapper.bookDto(bookService.getPageOfBooks(offset, limit).getContent(), bookDtoList);
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

    @GetMapping(value = {"/search", "/search/{searchWord}"})
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
        bookMapper.bookDto(bookService.getPageOfSearchResultBooks(searchWordDto.getExample(), offset, limit).getContent(), bookDtoList);
        return new BooksPageDto(bookDtoList);
    }
}
