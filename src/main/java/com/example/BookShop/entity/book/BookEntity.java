package com.example.BookShop.entity.book;

import com.example.BookShop.entity.book.links.Book2AuthorEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "book")
@ApiModel(description = "entity representing a book")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("id generated by db automatically")
    private int id;

    @Column(name = "pub_date", nullable = false)
    @ApiModelProperty("date of book publication")
    private Date pubDate;

    @Column(name = "is_bestseller", nullable = false)
    @ApiModelProperty("if isBestseller == 1 so the bok is considered to be bestseller and " +
            "if 0 the book is not a bestseller")
    private short isBestseller;

    @Column(nullable = false)
    @ApiModelProperty("mnemonically identity sequence of characters")
    private String slug;

    @Column(nullable = false)
    @ApiModelProperty("book title")
    private String title;

    @ApiModelProperty("img url")
    private String image;

    @Column(columnDefinition = "TEXT")
    @ApiModelProperty("book description text")
    private String description;

    @Column(nullable = false, columnDefinition = "INT")
    @ApiModelProperty("book price without discount")
    private int price;

    @Column(nullable = false)
    @ApiModelProperty("discount value for book")
    private short discount;

    @OneToMany(mappedBy = "bookId")
    @JsonIgnore
    private List<Book2AuthorEntity> bookIdList = new ArrayList<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public short getIsBestseller() {
        return isBestseller;
    }

    public void setIsBestseller(short isBestseller) {
        this.isBestseller = isBestseller;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public short getDiscount() {
        return discount;
    }

    public void setDiscount(short discount) {
        this.discount = discount;
    }

    public List<Book2AuthorEntity> getBookIdList() {
        return bookIdList;
    }

    public void setBookIdList(List<Book2AuthorEntity> bookIdList) {
        this.bookIdList = bookIdList;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", isBestseller=" + isBestseller +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }
}
