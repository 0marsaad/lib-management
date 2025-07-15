package com.librabry.sys.Model;

import java.util.ArrayList;

public class User implements model {

    protected String id;
    protected String name;

    public User(String id, String name, ArrayList<Book> borrowedBooks) {
        this.id = id;
        this.name = name;

    }
    
    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
