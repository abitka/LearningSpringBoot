package com.example.BookShop.mappers;

import com.example.BookShop.dto.GenreDto;
import com.example.BookShop.entity.genre.GenreEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    List<GenreDto> genreEntityToGenreDto(List<GenreEntity> genreEntity);
}
