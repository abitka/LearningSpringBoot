package com.example.BookShop.mappers;

import com.example.BookShop.dto.AuthorDto;
import com.example.BookShop.entity.AuthorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    void authorDto(List<AuthorEntity> authorEntity, @MappingTarget List<AuthorDto> authorDto);
}
