package com.example.test1;

public class BookData {
    String BookTitle, Author, Gener, description, imageUrl;

    public BookData() {
    }

    public BookData(String bookTitle, String author, String gener, String description) {
        BookTitle = bookTitle;
        Author = author;
        Gener = gener;
        this.description = description;
    }

    public String getBookTitle() {
        return BookTitle;
    }

    public void setBookTitle(String bookTitle) {
        BookTitle = bookTitle;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getGener() {
        return Gener;
    }

    public void setGener(String gener) {
        Gener = gener;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
