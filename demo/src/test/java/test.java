
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import com.librabry.sys.Model.*;
import com.librabry.sys.Database.database;

public class test {

    private static database db;

    @BeforeEach
    public void setUp() {
        db = database.getDatabaseInstance();
        
    }

    @Test
    public void testAddUser() {
        User user = new User("1", "Alice");
        db.addUser(user);
        assertEquals(user, db.getUserById("1"));
    }

    @Test
    public void testAddBook() {
        Book book = new Book("Invincible ", "omar", "1234567890", 5);
        db.addBook(book);
        assertEquals(book, db.getBookById("1234567890"));
    }

    @Test
    public void testBorrowBook() {
        User user = new User("1", "Alice");
        Book book = new Book("Invincible ", "omar", "1234567890", 5);
        db.addUser(user);
        db.addBook(book);

        UserBook userBook = new UserBook("1", "1234567890");
        db.borrowBook(userBook);

        assertArrayEquals(new User[] { user }, db.getUsersByBookId("1234567890"));
    }
    
    @Test
    public void testReturnBook() {
        User user = new User("1", "Alice");
        Book book = new Book("Invincible ", "omar", "1234567890", 5);
        db.addUser(user);
        db.addBook(book);
    }
    
}
