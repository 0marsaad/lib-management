package com.librabry.sys.Database;
import java.util.stream.Stream;

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
        userTable = UserTable.getInstance();
        bookTable = BookTable.getInstance();
        userBookTable = UserBookTable.getInstance();
        fileHandler = FileHandler.getFileHandlerInstance();

    
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

    public void addUser(User user) {
        try {
            Stream<User> userStream = Stream.of((User[]) userTable.getAllRecords());
            userStream.filter(u -> u.getId().equals(user.getId()))
                      .findFirst()
                      .ifPresentOrElse(
                          u -> { throw new IllegalArgumentException("User with this ID already exists"); },
                          () -> System.out.println("user Added: " + user.getName())
                    );
            userTable.addRecord(user);
            fileHandler.saveUsers((User[]) userTable.getAllRecords());
        } catch (IllegalArgumentException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    public void addBook(Book book) {
        try {
            Stream<Book> bookStream = Stream.of((Book[]) bookTable.getAllRecords());
            bookStream.filter(b -> b.getId().equals(book.getId()))
                    .findFirst()
                    .ifPresentOrElse(
                            b -> {
                                throw new IllegalArgumentException("Book with this ISBN already exists");
                            },
                            () -> System.out.println("Book Added: " + book.getName()));
            bookTable.addRecord(book);
            fileHandler.saveBooks((Book[]) bookTable.getAllRecords());
        } catch (IllegalArgumentException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    public void returnBook(UserBook userBook) {
        try {
            String bookId = userBook.getId().split(",")[1];
            userBookTable.deleteRecord(userBook.getId());
            Book retBook = (Book) bookTable.getRecord(bookId);
            retBook.incrementQuantity();
            bookTable.updateRecord(retBook);
            fileHandler.saveBooks((Book[]) bookTable.getAllRecords());
            fileHandler.saveUserBooks((UserBook[]) userBookTable.getAllRecords());
        } catch (IllegalArgumentException e) {
            System.out.println("Error returning book: " + e.getMessage());
        }
    }   
    

    public void deleteUser(String id) throws IllegalArgumentException {
        UserBook[] userBooks = (UserBook[]) userBookTable.getAllRecords();
        Stream<UserBook> userBookStream = Stream.of(userBooks).filter(ub -> ub.getId().equals(id));
        userBookStream.forEach(ub -> {
            returnBook(ub);
        });
        
        try {
            userTable.deleteRecord(id);
            fileHandler.saveUsers((User[]) userTable.getAllRecords());
        } catch (IllegalArgumentException e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }

    public void deleteBook(String id) {
        try {
            UserBook[] userBooks = (UserBook[]) userBookTable.getAllRecords();
            Stream<UserBook> userBookStream = Stream.of(userBooks).filter(ub -> ub.getId().split(",")[1].equals(id));
            userBookStream.forEach(ub -> {
                userBookTable.deleteRecord(ub.getId());
            });
            fileHandler.saveUserBooks((UserBook[]) userBookTable.getAllRecords());
            bookTable.deleteRecord(id);
            fileHandler.saveBooks((Book[]) bookTable.getAllRecords());
        } catch (IllegalArgumentException e) {
            System.out.println("Error deleting book: " + e.getMessage());
        }
    }

    public void borrowBook(UserBook userBook) {
        try {
            String bookId = userBook.getId().split(",")[1];
            Book book = (Book) bookTable.getRecord(bookId);
            if (book.getQuantity() > 0) {
                book.decrementQuantity();
                bookTable.updateRecord(book);
                userBookTable.addRecord(userBook);
                fileHandler.saveBooks((Book[]) bookTable.getAllRecords());
                fileHandler.saveUserBooks((UserBook[]) userBookTable.getAllRecords());
            } else {
                System.out.println("No copies available for borrowing.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error borrowing book: " + e.getMessage());
        }
    }


    public Book getBookById(String id) {
        try {
            return (Book) bookTable.getRecord(id);
        } catch (IllegalArgumentException e) {
            System.out.println("Error retrieving book: " + e.getMessage());
            return null;
        }
    }

    public User getUserById(String id) {
        try {
            return (User) userTable.getRecord(id);
        } catch (IllegalArgumentException e) {
            System.out.println("Error retrieving user: " + e.getMessage());
            return null;
        }
    }

    public Book getBookByTitle(String title) {
        try {
            return bookTable.getBookByTitle(title);
        } catch (IllegalArgumentException e) {
            System.out.println("Error retrieving book by title: " + e.getMessage());
            return null;
        }
    }

    public Book[] getAllBooks() {
        return (Book[]) bookTable.getAllRecords();
    }

    public User[] getAllUsers() {
        return (User[]) userTable.getAllRecords();
    }

    public UserBook[] getAllUserBooks() {
        return (UserBook[]) userBookTable.getAllRecords();
    }

    public Book[] getBooksByUserId(String userId) {
        return Stream.of((UserBook[]) userBookTable.getAllRecords())
                .filter(ub -> ub.getId().split(",")[0].equals(userId))
                .map(ub -> bookTable.getRecord(ub.getId().split(",")[1]))
                .toArray(Book[]::new);
    }

    public Book[] getBooksByAuthor(String author) {
        return Stream.of((Book[]) bookTable.getAllRecords())
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .toArray(Book[]::new);
    }


    public User[] getUsersByBookId(String bookId) {
        return Stream.of((UserBook[]) userBookTable.getAllRecords())
                .filter(ub -> ub.getId().split(",")[1].equals(bookId))
                .map(ub -> userTable.getRecord(ub.getId().split(",")[0]))
                .toArray(User[]::new);
    }
}
