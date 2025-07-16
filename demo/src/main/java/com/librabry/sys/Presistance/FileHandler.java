package com.librabry.sys.Presistance;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
             
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            bw.write("Name,Id\n"); // Header
            for (User u : user) {
                bw.write(u.getName() + "," + u.getId() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public void saveBooks(Book book[]) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH2))) {
            bw.write("Title,Author,ISBN,Quantity\n"); // Header
            for (Book b : book) {
                bw.write(b.getName() + "," + b.getAuthor() + "," + b.getId() + "," + b.getQuantity() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void saveUserBooks(UserBook userBook[]) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH3))) {
            bw.write("UserId,BookISBN\n"); // Header
            for (UserBook ub : userBook) {
                bw.write(ub.getId() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User[] loadUsers() {
        ArrayList<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) { // skip header
                    firstLine = false;
                    continue;
                }
                String[] parts = line.split(",", 2);
                if (parts.length >= 1) {
                    String name = parts[0];
                    String userId = parts[1];
                    
                    users.add(new User(name, userId));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users.toArray(new User[0]);
    }

    public Book[] loadBooks() {
        ArrayList<Book> books = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH2))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) { // skip header
                    firstLine = false;
                    continue;
                }
                String[] parts = line.split(",", 4);
                if (parts.length >= 3) {
                    String title = parts[0];
                    String author = parts[1];
                    String isbn = parts[2];
                    int quantity = Integer.parseInt(parts[3]);
                    books.add(new Book(isbn, title, author, quantity));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books.toArray(new Book[0]);
    }

    public UserBook[] loadUserBooks() {
        ArrayList<UserBook> userBooks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH3))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) { // skip header
                    firstLine = false;
                    continue;
                }
                String[] parts = line.split(",", 2);
                if (parts.length >= 2) {
                    String userId = parts[0];
                    String bookIsbn = parts[1];
                    userBooks.add(new UserBook(userId, bookIsbn));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!userBooks.isEmpty()) {
            return userBooks.toArray(new UserBook[0]);
        }
        // Return an empty array if no user books were found
        System.out.println("No user books found in the file.");
        return new UserBook[0]; 
    }

    
}
