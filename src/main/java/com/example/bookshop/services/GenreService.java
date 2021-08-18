package com.example.bookshop.services;

import com.example.bookshop.dto.GenreDto;
import com.example.bookshop.mappers.GenreMapper;
import com.example.bookshop.repositories.GenreRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
