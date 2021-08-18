package com.example.bookshop.mappers;

import com.example.bookshop.dto.BookDto;
import com.example.bookshop.entity.book.BookEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

//    void updateBookDto(List<BookEntity> bookEntity, @MappingTarget List<BookDto> booksDto);

    List<BookDto> bookEntityToBookDto(List<BookEntity> bookEntityList);

}
