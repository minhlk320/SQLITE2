package com.minh.sqlite;

public class Book {
    private int id;
    private String title;
    private int id_author;

    public Book() {
        this.id = 0;
        this.title="";
        this.id_author=0;
    }

    public Book(int id, String title, int author) {
        this.id = id;
        this.title = title;
        this.id_author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthor() {
        return id_author;
    }

    public void setAuthor(int author) {
        this.id_author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + id_author + '\'' +
                '}';
    }
}
