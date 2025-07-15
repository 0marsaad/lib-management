package com.librabry.sys.Database;

import java.io.IOException;

import com.librabry.sys.Model.*;

import com.librabry.sys.Presistance.*;

public class database {
    private static UserTable userTable;
    private static BookTable bookTable;
    private static UserBookTable userBookTable;
    private static FileHandler fileHandler;
    private static database instance;

    public static database getDatabaseInstance() {
        
        if(instance == null){
            instance = new database();
            return instance;
        }else {
            return instance;
        }

    }

    private database() {
        userTable = userTable.getInstance();
        bookTable = bookTable.getInstance();
        userBookTable = userBookTable.getInstance();
        fileHandler = fileHandler.getFileHandlerInstance();

    
            User[] users = fileHandler.loadUsers();
            for (User user : users) {
                userTable.addRecord(user);
            }
            Book[] books = fileHandler.loadBooks();
            for (Book book : books) {
                bookTable.addRecord(book);
            }
            UserBook[] userBooks = fileHandler.loadUserBooks();
            for (UserBook userBook : userBooks) {
                userBookTable.addRecord(userBook);
            }
        
    }

    public UserTable getUserTable() {
        return userTable;
    }

    public BookTable getBookTable() {
        return bookTable;
    }

    public UserBookTable getUserBookTable() {
        return userBookTable;
    }


    
}
