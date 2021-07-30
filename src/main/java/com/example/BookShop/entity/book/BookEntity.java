package com.example.BookShop.entity.book;

import com.example.BookShop.entity.AuthorEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "book")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pub_date", nullable = false)
    private Date pubDate;

    @Column(name = "is_bestseller", nullable = false)
    private short isBestseller;

    @Column(nullable = false)
    private String slug;

    @Column(nullable = false)
    private String title;

    private String image;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, columnDefinition = "INT")
    private int price;

    @Column(nullable = false)
    private short discount;

    @ManyToMany
    @JoinTable(name = "book2author",
            joinColumns = {@JoinColumn(name = "book_id", foreignKey = @ForeignKey(name = "book_id_fk"))},
            inverseJoinColumns = { @JoinColumn(name = "author_id", foreignKey = @ForeignKey(name = "author_id_fk"))}
    )
    private List<AuthorEntity> authorList = new ArrayList<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<AuthorEntity> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<AuthorEntity> authorList) {
        this.authorList = authorList;
    }
}
