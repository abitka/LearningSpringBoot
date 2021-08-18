package com.example.bookshop.controllers;

import com.example.bookshop.dto.AuthorDto;
import com.example.bookshop.dto.BookDto;
import com.example.bookshop.dto.SearchWordDto;
import com.example.bookshop.entity.AuthorEntity;
import com.example.bookshop.entity.book.BookEntity;
import com.example.bookshop.services.AuthorService;
import com.example.bookshop.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class AuthorController {

    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @ModelAttribute("authorsMap")
    public Map<String, List<AuthorEntity>> authorsMap() {
        return authorService.getAuthorsData();
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("searchResults")
    public List<BookEntity> searchResults() {
        return new ArrayList<>();
    }

    @ModelAttribute("author")
    public AuthorDto authorDto() {
        return new AuthorDto();
    }

    @ModelAttribute("author")
    public List<BookDto> bookDtoList() {
        return new ArrayList<>();
    }

    @GetMapping("/authors")
    public String authors() {
        return "/authors/index";
    }

    @GetMapping("/authors/{slug}")
    public String slug(@PathVariable(value = "slug", required = false) String slug, Model model) {
        AuthorDto authorDto = authorService.getAuthor(slug);
        model.addAttribute("author", authorDto);

        List<BookDto> booksDto = bookService.getBooksThisAuthor(slug);
        model.addAttribute("books", booksDto);

        return "/authors/slug";
    }

    @GetMapping("/books/author/{slug}")
    public String allBooksThisAuthor(@PathVariable(value = "slug", required = false) String slug, Model model) {
        AuthorDto authorDto = authorService.getAuthor(slug);
        model.addAttribute("author", authorDto);

        List<BookDto> booksDto = bookService.getBooksThisAuthor(slug);
        model.addAttribute("books", booksDto);

        return "/books/author";
    }
}
