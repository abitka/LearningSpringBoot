package com.example.BookShop.services;

import com.example.BookShop.dto.GenreDto;
import com.example.BookShop.mappers.GenreMapper;
import com.example.BookShop.repositories.GenreRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper = Mappers.getMapper(GenreMapper.class);

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<GenreDto> getAllGenres() {
        return genreMapper.genreEntityToGenreDto(genreRepository.findAll());
    }
}
