package com.librabry.sys.Model;

public class User implements model {

    protected String id;
    protected String name;

    public User(String id, String name) {
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
