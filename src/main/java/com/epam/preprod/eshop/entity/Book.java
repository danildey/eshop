package com.epam.preprod.eshop.entity;


import com.epam.preprod.eshop.anotation.Init;

import java.time.LocalDateTime;

public abstract class Book extends Product {

    private int isbn;

    private LocalDateTime publicationDate;

    private String genre;

    private String author;

    private String publisher;


    public Book() {
    }

    public Book(long id, long price, String name, String description) {
        super(id, price, name, description);
    }

    public Book(int isbn, String genre, int pages, LocalDateTime publicationDate, String author, String publisher) {
        this.publisher = publisher;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
        this.publicationDate = publicationDate;
    }

    @Init(resource = "bookPublisher")
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Init(resource = "bookIsbn")
    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    @Init(resource = "bookGenre")
    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Init(resource = "bookAuthor")
    public void setAuthor(String author) {
        this.author = author;
    }

    @Init(resource = "bookPublicationDate")
    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getIsbn() {
        return isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Book book = (Book) o;

        if (isbn != book.isbn) return false;
        return publicationDate != null ? publicationDate.equals(book.publicationDate) : book.publicationDate == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + isbn;
        result = 31 * result + (publicationDate != null ? publicationDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + isbn +
                ", genre=" + genre +
                ", publicationDate=" + publicationDate +
                '}';
    }
}
