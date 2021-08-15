package com.example.BookShop.repositories;

import com.example.BookShop.entity.genre.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Integer> {

//    List<GenreEntity> findAll();
}
