package com.example.bookshop.mappers;

import com.example.bookshop.dto.GenreDto;
import com.example.bookshop.entity.genre.GenreEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    List<GenreDto> genreEntityToGenreDto(List<GenreEntity> genreEntity);
}
