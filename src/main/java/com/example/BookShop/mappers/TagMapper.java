package com.example.BookShop.mappers;

import com.example.BookShop.dto.TagDto;
import com.example.BookShop.entity.tags.TagEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {

    TagDto tagEntityToTagDto(TagEntity tagEntity);
}
