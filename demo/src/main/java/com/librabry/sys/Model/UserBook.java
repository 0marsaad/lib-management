package com.librabry.sys.Model;

public class UserBook implements model {
    private String userId;
    private String bookIsbn;

    public UserBook(String userId, String bookIsbn) {
        this.userId = userId;
        this.bookIsbn = bookIsbn;
    }

    @Override
    public String getId() {
        return userId + "," + bookIsbn;
    }
    
    @Override
    public String getName() {
        return "UserBook: " + userId + " - " + bookIsbn;
    }
    
}
