package com.librabry.sys.Model;

public class Book implements model {
   
    private String isbn;
    private String title;
    private String author;
    private int quantity;

    public Book(String isbn, String title, String author, int quantity) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }

    @Override
    public String getId() {
        return isbn;
    }
    
    @Override
    public String getName() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void incrementQuantity() {
        this.quantity++;
    }

    public void decrementQuantity() throws IllegalArgumentException {
        if (this.quantity > 0) {
            this.quantity--;
        } else {
            throw new IllegalArgumentException("Quantity cannot be less than zero");
        }
    }
    
    
}
