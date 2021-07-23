package com.example.BookShop.mappers;

import com.example.BookShop.dto.BookDto;
import com.example.BookShop.entity.book.BookEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void bookDto(List<BookEntity> bookEntity, @MappingTarget List<BookDto> booksDto);
}
