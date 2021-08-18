package com.example.bookshop.services;

import com.example.bookshop.dto.AuthorDto;
import com.example.bookshop.entity.AuthorEntity;
import com.example.bookshop.mappers.AuthorMapper;
import com.example.bookshop.repositories.AuthorRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper = Mappers.getMapper(AuthorMapper.class);

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Map<String, List<AuthorEntity>> getAuthorsData() {
        return authorRepository.findAll().stream()
                .collect(Collectors.groupingBy((AuthorEntity a) -> a.getName().substring(0, 1)));
    }

    public AuthorDto getAuthor(String slug) {
        return authorMapper.authorEntityToAuthorDto(authorRepository.findTopBySlug(slug));
    }
}
