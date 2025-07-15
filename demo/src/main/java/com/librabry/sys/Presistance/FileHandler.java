package com.librabry.sys.Presistance;

import java.io.File;
import java.io.IOException;
import com.librabry.sys.Model.*;

public class FileHandler {

    private static final String FILE_PATH = "src/main/resources/User.csv";
    private static final String FILE_PATH2 = "src/main/resources/Book.csv";
    private static final String FILE_PATH3 = "src/main/resources/UserBook.csv";
    private static FileHandler instance;

    public static FileHandler getFileHandlerInstance() {
        // Constructor can be used for initialization if needed
        if (instance == null) {
            instance = new FileHandler();
            return instance;
        }else {
            return instance;
        }
    }

    private FileHandler() {
        // Initialize the file handler or any necessary setup
        File file = new File(FILE_PATH);
        File file2 = new File(FILE_PATH2);
        File file3 = new File(FILE_PATH3);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            if (!file2.exists()) {
                file2.createNewFile();
            }
            if (!file3.exists()) {
                file3.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveUsers (User user[]) {
        



    }
    
    public void saveBooks(Book book[]) {
    }
    
    public void saveUserBooks(UserBook userBook[]) {
    }

    public User[] loadUsers() {
        return new User[0]; 
    }

    public Book[] loadBooks() {
        return new Book[0]; 
    }

    public UserBook[] loadUserBooks() {
        return new UserBook[0]; 
    }

    
}
