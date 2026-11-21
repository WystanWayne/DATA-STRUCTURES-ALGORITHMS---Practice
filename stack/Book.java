package stack;

public class Book {
    private String isbn;
    private String title;
    private String author;

    // Constructor
    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    // Getters
    public String getIsbn() { 
        return isbn; 
    }

    public String getTitle() { 
        return title; 
    }

    public String getAuthor() { 
        return author; 
    }

    // toString method
    @Override
    public String toString() {
        return "ISBN: " + isbn + " | Title: " + title + " | Author: " + author;
    }
}

