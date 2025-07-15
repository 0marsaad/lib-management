package com.librabry.sys.Database;

import com.librabry.sys.Model.model;

import java.util.Map;

import com.librabry.sys.Model.User;

public class UserTable implements Table {

    private static UserTable instance;
    public static final String TABLE_NAME = "users";
    private Map<String, User> users;

    // Singleton pattern to ensure only one instance of UserTable exists
    public static UserTable getInstance() {
        if (instance == null) {
            instance = new UserTable();
        }
        return instance;
    }

    private UserTable() {
        // Initialize the users map or any other necessary setup
        this.users = new java.util.HashMap<>();
    }

    @Override
    public void addRecord(model record) throws IllegalArgumentException {
        if (record instanceof User) {
            User person = (User) record;
            users.put(person.getId(), person);
            
        } else {
            throw new IllegalArgumentException("Record must be of type User");
        }
    }

    @Override
    public void deleteRecord(String id) throws IllegalArgumentException {
        if (!users.containsKey(id)) {
            throw new IllegalArgumentException("No record found with id: " + id);
        }
        users.remove(id);
        if (!users.containsKey(id)) {
            System.out.println("Record with id " + id + " has been successfully deleted.");
        } else {
            System.out.println("Failed to delete record with id " + id + ".");
        }
    }

    @Override
    public model getRecord(String id) throws IllegalArgumentException {
        if (!users.containsKey(id)) {
            throw new IllegalArgumentException("No record found with id: " + id);
        }
        return users.get(id);
    }

    @Override
    public void updateRecord(model record) throws IllegalArgumentException {
        if (record instanceof User) {
            // Logic to update User record
            User person = (User) record;
            users.put(person.getId(), person);
        } else {
            throw new IllegalArgumentException("Record must be of type User");
        }
    }

    @Override
    public model[] getAllRecords() {
        // Logic to retrieve all User records
        return users.values().toArray(new User[0]); // Placeholder return
    }
}
