package com.example.BookShop.controllers;

import com.example.BookShop.dto.BookDto;
import com.example.BookShop.dto.BooksPageDto;
import com.example.BookShop.dto.SearchWordDto;
import com.example.BookShop.dto.TagDto;
import com.example.BookShop.entity.book.BookEntity;
import com.example.BookShop.mappers.BookMapper;
import com.example.BookShop.services.BookService;
import com.example.BookShop.services.TagService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/tags")
public class TagController {

    private final Logger logger = Logger.getLogger(TagController.class.getSimpleName());
    private final TagService tagService;
    private final BookService bookService;
    private final BookMapper bookMapper = Mappers.getMapper(BookMapper.class);

    @Autowired
    public TagController(TagService tagService, BookService bookService) {
        this.tagService = tagService;
        this.bookService = bookService;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("searchResults")
    public List<BookEntity> searchResults() {
        return new ArrayList<>();
    }

    @ModelAttribute("bookDto")
    public List<BookDto> bookDto() {
        return new ArrayList<>();
    }


    @GetMapping
    public String tagsPage() {
        return "/tags/index";
    }

    @GetMapping("/{tag}")
    public String tag(@PathVariable(value = "tag", required = false) Integer tagId, Model model) {
        logger.info(">>>>>>> TagController: tagId: " + tagId);
        TagDto tagDto = tagService.tagDto(tagId);
        List<BookDto> bookDto = bookMapper.bookEntityToBookDto(bookService.getBookWithTagIdContains(tagId, 0, 20));
        model.addAttribute("tagDto", tagDto);
        model.addAttribute("bookDto", bookDto);
        return "/tags/index";
    }

    @GetMapping("/books/tag/{tag}")
    @ResponseBody
    public BooksPageDto tag(@RequestParam("offset") int offset,
                      @RequestParam("limit") int limit,
                      @PathVariable(value = "tag", required = false) Integer tagId, Model model) {
        TagDto tagDto = tagService.tagDto(tagId);
        List<BookDto> bookDto = bookMapper.bookEntityToBookDto(bookService.getBookWithTagIdContains(tagId, offset, limit));
        model.addAttribute("tagDto", tagDto);
        model.addAttribute("bookDto", bookDto);
        return new BooksPageDto(bookDto);
    }
}
