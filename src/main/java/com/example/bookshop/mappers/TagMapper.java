package com.example.bookshop.mappers;

import com.example.bookshop.dto.TagDto;
import com.example.bookshop.entity.tags.TagEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {

    TagDto tagEntityToTagDto(TagEntity tagEntity);
}
