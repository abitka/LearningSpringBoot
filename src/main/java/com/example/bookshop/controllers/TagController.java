package com.example.bookshop.controllers;

import com.example.bookshop.dto.BookDto;
import com.example.bookshop.dto.BooksPageDto;
import com.example.bookshop.dto.SearchWordDto;
import com.example.bookshop.dto.TagDto;
import com.example.bookshop.entity.book.BookEntity;
import com.example.bookshop.mappers.BookMapper;
import com.example.bookshop.services.BookService;
import com.example.bookshop.services.TagService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tags")
public class TagController {

    private final Logger logger = LogManager.getLogger(TagController.class);
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
        logger.info(">>>>>>> TagController: tagId: {}", tagId);
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
                            @PathVariable(value = "tag", required = false) Integer tagId,
                            Model model) {

        TagDto tagDto = tagService.tagDto(tagId);
        List<BookDto> bookDto = bookMapper.bookEntityToBookDto(bookService.getBookWithTagIdContains(tagId, offset, limit));
        model.addAttribute("tagDto", tagDto);
        model.addAttribute("bookDto", bookDto);

        return new BooksPageDto(bookDto);
    }
}