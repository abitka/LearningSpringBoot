package com.example.BookShop.controllers;

import com.example.BookShop.dto.BooksPageDto;
import com.example.BookShop.dto.SearchWordDto;
import com.example.BookShop.entity.book.BookEntity;
import com.example.BookShop.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class SearchController {

    private final Logger logger = Logger.getLogger(SearchController.class.getSimpleName());
    private final BookService bookService;

    @Autowired
    public SearchController(BookService bookService) {
        this.bookService = bookService;
    }

    /*@ModelAttribute("searchWordDto")
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

        logger.info(">>>>>> getSearchResult: " + searchWordDto.getExample());

        model.addAttribute("searchWordDto", searchWordDto);
        model.addAttribute("searchResults", bookService.getPageOfSearchResultBooks(searchWordDto.getExample(), 0, 5).getContent());

        return "/search/index";
    }

    @GetMapping("/search/page/{searchWord}")
    @ResponseBody
    public BooksPageDto getNextSearchPage(@RequestParam("offset") int offset,
                                          @RequestParam("limit") int limit,
                                          @PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto) {
        logger.info(">>>>>>> getNextSearchPage:\n" +
                "offset: " + offset +
                "\n limit: " + limit +
                "\n searchWord: " +  searchWordDto.getExample());

        return new BooksPageDto(bookService.getPageOfSearchResultBooks(searchWordDto.getExample(), offset, limit).getContent());
    }*/
}
