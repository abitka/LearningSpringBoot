package com.example.BookShop.mappers;

import com.example.BookShop.dto.AuthorDto;
import com.example.BookShop.entity.AuthorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDto authorEntityToAuthorDto(AuthorEntity authorEntity);
}
