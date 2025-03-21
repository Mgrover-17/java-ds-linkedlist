class Book {
    String title;
    String author;
    String genre;
    int bookId;
    boolean isAvailable;
    Book next;
    Book prev;

    public Book(String title, String author, String genre, int bookId, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.isAvailable = isAvailable;
        this.next = null;
        this.prev = null;
    }
}

class Library {
    private Book head;
    private Book tail;
    private int count;

    public void addAtBeginning(String title, String author, String genre, int bookId, boolean isAvailable) {
        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        if (head == null) {
            head = tail = newBook;
        } else {
            newBook.next = head;
            head.prev = newBook;
            head = newBook;
        }
        count++;
    }

    public void addAtEnd(String title, String author, String genre, int bookId, boolean isAvailable) {
        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        if (tail == null) {
            head = tail = newBook;
        } else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }
        count++;
    }

    public void addAtPosition(String title, String author, String genre, int bookId, boolean isAvailable, int position) {
        if (position <= 1) {
            addAtBeginning(title, author, genre, bookId, isAvailable);
            return;
        }

        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        Book current = head;
        for (int i = 1; current != null && i < position - 1; i++) {
            current = current.next;
        }

        if (current == null || current.next == null) {
            addAtEnd(title, author, genre, bookId, isAvailable);
            return;
        }

        newBook.next = current.next;
        newBook.prev = current;
        if (current.next != null) {
            current.next.prev = newBook;
        }
        current.next = newBook;
        count++;
    }

    public void removeByBookId(int bookId) {
        Book current = head;
        while (current != null) {
            if (current.bookId == bookId) {
                if (current == head) {
                    head = head.next;
                    if (head != null) head.prev = null;
                } else if (current == tail) {
                    tail = tail.prev;
                    tail.next = null;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                count--;
                return;
            }
            current = current.next;
        }
    }

    public void searchByTitle(String title) {
        Book current = head;
        while (current != null) {
            if (current.title.equalsIgnoreCase(title)) {
                System.out.println(current.bookId + " - " + current.title + " - " + current.author + " - " + current.genre + " - Available: " + current.isAvailable);
            }
            current = current.next;
        }
    }

    public void searchByAuthor(String author) {
        Book current = head;
        while (current != null) {
            if (current.author.equalsIgnoreCase(author)) {
                System.out.println(current.bookId + " - " + current.title + " - " + current.genre + " - Available: " + current.isAvailable);
            }
            current = current.next;
        }
    }

    public void updateAvailability(int bookId, boolean isAvailable) {
        Book current = head;
        while (current != null) {
            if (current.bookId == bookId) {
                current.isAvailable = isAvailable;
                return;
            }
            current = current.next;
        }
    }

    public void displayForward() {
        Book current = head;
        while (current != null) {
            System.out.println(current.bookId + " | " + current.title + " | " + current.author + " | " + current.genre + " | Available: " + current.isAvailable);
            current = current.next;
        }
    }

    public void displayReverse() {
        Book current = tail;
        while (current != null) {
            System.out.println(current.bookId + " | " + current.title + " | " + current.author + " | " + current.genre + " | Available: " + current.isAvailable);
            current = current.prev;
        }
    }

    public int countBooks() {
        return count;
    }
}

public class LibraryManagement {
    public static void main(String[] args) {
        Library library = new Library();
        library.addAtBeginning("The Hobbit", "J.R.R. Tolkien", "Fantasy", 101, true);
        library.addAtEnd("1984", "George Orwell", "Dystopian", 102, true);
        library.addAtPosition("To Kill a Mockingbird", "Harper Lee", "Fiction", 103, true, 2);

        System.out.println("Library Books in Forward Order:");
        library.displayForward();

        System.out.println("\nLibrary Books in Reverse Order:");
        library.displayReverse();

        System.out.println("\nSearching for books by author 'George Orwell':");
        library.searchByAuthor("George Orwell");

        System.out.println("\nUpdating availability for book ID 102:");
        library.updateAvailability(102, false);
        library.displayForward();

        System.out.println("\nRemoving book with ID 101:");
        library.removeByBookId(101);
        library.displayForward();

        System.out.println("\nTotal number of books in the library: " + library.countBooks());
    }
}