package com.example.bookshop.dto;

public class TagDto {

    private String tagName;
    private Integer tagId;
    private Integer count;

    public TagDto() {
    }

    public TagDto(String tagName, Integer tagId, Integer count) {
        this.tagName = tagName;
        this.tagId = tagId;
        this.count = count;
    }


    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
