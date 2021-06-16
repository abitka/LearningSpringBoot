package com.example.BookShop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authors")
public class AuthorPageController {

    @GetMapping
    public String authors() {
        return "./authors/index";
    }
}
