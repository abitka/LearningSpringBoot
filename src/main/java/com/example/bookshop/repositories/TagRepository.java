package com.example.bookshop.repositories;

import com.example.bookshop.entity.tags.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<TagEntity, Integer> {

    @Query("select tag.tagName, tag.tagId, count(tag.tagId) " +
            "from TagEntity tag join Book2TagEntity book2tag on tag.tagId = book2tag.tagId " +
            "GROUP BY tag.tagId, tag.tagName")
    List<Object[]> allTagCountTagIdAndGroupByTagId();
}
