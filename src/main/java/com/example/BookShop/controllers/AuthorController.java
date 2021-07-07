package com.example.BookShop.controllers;

import com.example.BookShop.entity.AuthorEntity;
import com.example.BookShop.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ModelAttribute("authorsMap")
    public Map<String, List<AuthorEntity>> authorsMap() {
        return authorService.getAuthorsData();
    }

    @GetMapping
    public String authors() {
        return "/authors/index";
    }
}
