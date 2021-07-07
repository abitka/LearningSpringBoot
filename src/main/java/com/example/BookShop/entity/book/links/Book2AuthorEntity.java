package com.example.BookShop.entity.book.links;

import com.example.BookShop.entity.AuthorEntity;
import com.example.BookShop.entity.book.BookEntity;

import javax.persistence.*;

@Entity
@Table(name = "book2author")
public class Book2AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id", columnDefinition = "INT NOT NULL",
            foreignKey = @ForeignKey(name = "book_id_fk"))
    private BookEntity bookId;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id", columnDefinition = "INT NOT NULL",
            foreignKey = @ForeignKey(name = "author_id_fk"))
    private AuthorEntity authorId;

    @Column(name = "sort_index", columnDefinition = "INT NOT NULL  DEFAULT 0")
    private int sortIndex;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookEntity getBookId() {
        return bookId;
    }

    public void setBookId(BookEntity bookId) {
        this.bookId = bookId;
    }

    public AuthorEntity getAuthorId() {
        return authorId;
    }

    public void setAuthorId(AuthorEntity authorId) {
        this.authorId = authorId;
    }

    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }
}
