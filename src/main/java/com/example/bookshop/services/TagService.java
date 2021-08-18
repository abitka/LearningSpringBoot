package com.example.bookshop.services;

import com.example.bookshop.dto.TagDto;
import com.example.bookshop.entity.tags.TagEntity;
import com.example.bookshop.mappers.TagMapper;
import com.example.bookshop.repositories.TagRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper = Mappers.getMapper(TagMapper.class);

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public TagDto tagDto(Integer tagId) {
        return tagMapper.tagEntityToTagDto(tagRepository.findById(tagId).orElse(new TagEntity()));
    }

    public List<TagDto> allTagCountTagId() {
        List<Object[]> objectList = tagRepository.allTagCountTagIdAndGroupByTagId();
        List<TagDto> tagDtoList = new ArrayList<>();
        for (Object[] object : objectList) {
            tagDtoList.add(new TagDto(
                    (String) object[0],
                    ((Number) object[1]).intValue(),
                    ((Number)(long) object[2]).intValue()));
        }
        return tagDtoList;
    }
}
