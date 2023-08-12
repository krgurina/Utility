package com.example.fromrar;

public class Book {
    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public void setPublication_year(String publication_year) {
        this.publication_year = publication_year;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }

    public String getBook_name() {
        return book_name;
    }

    public String getPublication_year() {
        return publication_year;
    }

    public int getPages() {
        return pages;
    }

    private String author;
    private String book_name;
    private String publication_year;
    private int pages;
}
