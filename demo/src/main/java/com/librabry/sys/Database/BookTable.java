package com.librabry.sys.Database;

import java.util.Map;

import com.librabry.sys.Model.Book;
import com.librabry.sys.Model.model;

public class BookTable implements Table {

    private Map<String, Book> bookRecords;
    static final String TABLE_NAME = "BookTable";
    private static BookTable instance;

    // Singleton pattern to ensure only one instance of BookTable exists
    public static BookTable getInstance() {
        if (instance == null) {
            instance = new BookTable();
        }
        return instance;
    }
    
    private BookTable() {
        // Initialize the bookRecords map or any other necessary setup
        this.bookRecords = new java.util.HashMap<>();
    }

    @Override
    public void addRecord(model record) throws IllegalArgumentException {
        if (record instanceof Book) {
            Book book = (Book) record;
            bookRecords.put(book.getId(), book);
        } else {
            throw new IllegalArgumentException("Record must be of type Book");
        }
    }

    @Override
    public void deleteRecord(String id) throws IllegalArgumentException {
        if (!bookRecords.containsKey(id)) {
            throw new IllegalArgumentException("No record found with id: " + id);
        }
        bookRecords.remove(id);
        if (!bookRecords.containsKey(id)) {
            System.out.println("Record with id " + id + " has been successfully deleted.");
        } else {
            System.out.println("Failed to delete record with id " + id + ".");
        }
    }

    @Override
    public model getRecord(String id) throws IllegalArgumentException {
        if (!bookRecords.containsKey(id)) {
            throw new IllegalArgumentException("No record found with id: " + id);
        }
        return bookRecords.get(id);
    }

    @Override
    public void updateRecord(model record) {
        if (record instanceof Book) {
            Book book = (Book) record;
            bookRecords.put(book.getId(), book);
        } else {
            throw new IllegalArgumentException("Record must be of type Book");
        }
    }

    @Override
    public model[] getAllRecords() {
        return bookRecords.values().toArray(new Book[0]);
    }

    public Book getBookByTitle(String title) {
        return bookRecords.values().stream()
                .filter(book -> book.getName().equalsIgnoreCase(title))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No book found with title: " + title));
    }

    public Book[] getBooksByAuthor(String author) {
        return bookRecords.values().stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .toArray(Book[]::new);
    }


}
