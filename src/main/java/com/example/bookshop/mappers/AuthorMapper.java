package com.example.bookshop.mappers;

import com.example.bookshop.dto.AuthorDto;
import com.example.bookshop.entity.AuthorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDto authorEntityToAuthorDto(AuthorEntity authorEntity);
}
