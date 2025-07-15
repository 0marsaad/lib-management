package com.librabry.sys.Database;

import com.librabry.sys.Model.model;
import com.librabry.sys.Model.UserBook;
import java.util.*;



public class UserBookTable implements Table {
    
    private Map<String, String> userBooks;
    private static UserBookTable instance;

    public static UserBookTable getInstance() {
        if (instance == null) {
            instance = new UserBookTable();
        }
        return instance;
    }

    private UserBookTable() {
        this.userBooks = new HashMap<>();
    }

    @Override
    public void addRecord(model record) throws IllegalArgumentException {
        if (record instanceof UserBook) {
            UserBook userBook = (UserBook) record;
            userBooks.put(userBook.getId(), userBook.getId());
        } else {
            throw new IllegalArgumentException("Record must be of type UserBook");
        }

    }
    


    @Override
    public void deleteRecord(String id) throws IllegalArgumentException {
        
        if(!userBooks.containsKey(id)) {
            throw new IllegalArgumentException("No record found with id: " + id);
        }
        userBooks.remove(id);
        if (!userBooks.containsKey(id)) {
            System.out.println("Record with id " + id + " has been successfully deleted.");
        } else {
            System.out.println("Failed to delete record with id " + id + ".");
        }

    }
    @Override
    public model getRecord(String id) throws IllegalArgumentException {
        String record = userBooks.get(id);
        if (record != null) {
            String[] parts = record.split(",");
            return new UserBook(parts[0], parts[1]);
        }
        return null;
    }

    @Override
    public void updateRecord(model record) throws IllegalArgumentException {
        if (record instanceof UserBook) {
            UserBook userBook = (UserBook) record;
            userBooks.put(userBook.getId(), userBook.getId());
        } else {
            throw new IllegalArgumentException("Record must be of type UserBook");
        }
    }

    @Override
    public model[] getAllRecords() {
        return userBooks.values().stream()
                .map(id -> {
                    String[] parts = id.split(",");
                    return new UserBook(parts[0], parts[1]);
                })
                .toArray(UserBook[]::new);
    }


}
