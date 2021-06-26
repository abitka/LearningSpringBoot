package com.example.BookShop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/popular")
public class PopularController {

    @GetMapping
    public String popular() {
        return "/books/popular";
    }
}
