package com.example.BookShop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search")
public class SearchController {

    @GetMapping
    public String search() {
        return "index";
    }

    @PostMapping
    public String search(@RequestParam String value) {
        System.out.println(">>>>>> search: " + value);
        return "index";
    }
}
