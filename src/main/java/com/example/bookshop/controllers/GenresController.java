package com.example.bookshop.controllers;

import com.example.bookshop.dto.GenreDto;
import com.example.bookshop.dto.SearchWordDto;
import com.example.bookshop.entity.book.BookEntity;
import com.example.bookshop.services.GenreService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/genres")
public class GenresController {

    private final Logger logger = LogManager.getLogger(GenresController.class);
    private final GenreService genreService;

    @Autowired
    public GenresController(GenreService genreService) {
        this.genreService = genreService;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("searchResults")
    public List<BookEntity> searchResults() {
        return new ArrayList<>();
    }

    @ModelAttribute("allGenres")
    public List<GenreDto> getAllGenres() {
        logger.info(">>>>>>> allGenres");
        List<GenreDto> genreDtoList = genreService.getAllGenres();
        logger.info(">>>>>>> genreDtoList: {}", genreDtoList.size());
        return genreDtoList;
    }

    @GetMapping
    public String genres() {
        return "/genres/index";
    }

    @GetMapping("/slug")
    public String genresSlug() {
        return "/genres/slug";
    }
}
