package com.example.bookshop.repositories;

import com.example.bookshop.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Integer> {

    AuthorEntity findTopBySlug(String slug);
}
