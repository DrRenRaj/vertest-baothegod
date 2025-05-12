package rrVer;

import java.util.ArrayList;
import java.util.Scanner;


//Add other classes here or separate java files in same package
class Book {
	//Fields
        private String title;
        private String author;
        private String isbn;
        private boolean isAvailable;
    //Methods
        //Constructors
        public Book(String title, String author, String isbn) {
            this.title = title;
            this.author = author;
            this.isbn = isbn;
            this.isAvailable = true; // By default, the book is available
        }
        //Getters/Setters as needed
        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public String getIsbn() {
            return isbn;
        }

        public boolean isAvailable() {
            return isAvailable;
        }

        public void checkOut() { //checkout a book method for boolean setting
            isAvailable = false;
        }

        public void returnBook() { //return a book method for boolean setting
            isAvailable = true;
        }

        @Override //From Chatgpt for debugging because I wanted books to print in this format
        public String toString() {
            return "Title: " + title +
                   ", Author: " + author +
                   ", ISBN: " + isbn +
                   ", Available: " + (isAvailable ? "Yes" : "No");
        }
    }
class Library {
	 //Field
        private ArrayList<Book> books;

        public Library() {
            books = new ArrayList<>();
        }
    //Methods
        //Add book method
        public void addBook(Book book) {
            for (Book b : books) {
                if (b.getIsbn().equals(book.getIsbn())) { //Ensure no reapt Isbns
                    System.out.println("ISBN already exists could not add");
                    return;
                }
            }
            books.add(book);
            System.out.println("Book added");
        }
        //Remove book method
        public void removeBook(String isbn) {
            for (Book b : books) {
                if (b.getIsbn().equals(isbn)) {
                    books.remove(b);
                    System.out.println("Book removed");
                    return;
                }
            }
        }
      //Display All Books method
        public void displayAllBooks() {
            if (books.isEmpty()) {       //If books aren't in list
                System.out.println("No books in Library System, please add books");
                return;
            }
            for (Book b : books) {
                System.out.println(b);
            }
        }
      //Search by title
        public void searchByTitle(String title) {
            boolean search = false; //Chatgpt added because of a bug where it checked each book in list even after finding title
            for (Book b : books) { 
                if (b.getTitle().equalsIgnoreCase(title)) { //ignore case for user use
                    System.out.println("Book Found");
                    System.out.println(b);
                    search = true;
                    break; //Stop after first match:
                }
            }
            if(!search) System.out.println("No books found"); //Chatgpt added
        }
        
      //Search by author
        public void searchByAuthor(String author) {
            boolean search = false; //Replicated from search by title method
            for (Book b : books) {
                if (b.getAuthor().equalsIgnoreCase(author)) { //ignore case for user use
                    System.out.println("Book Found");
                    System.out.println(b);
                    search = true;
                    break;
                }
            }
            if(!search) System.out.println("No books found"); //Replicated from search by title method
        }
        
      //Checkout a book method
        public void checkOutBook(String isbn) {
            for (Book b : books) {
                if (b.getIsbn().equals(isbn)) {
                    if (b.isAvailable()) {
                        b.checkOut();
                        System.out.println("Checked out book");
                    } else {
                        System.out.println("Book is already checked out");
                    }
                    return;
                }
            }
            System.out.println("Book not found");
        }
      //Return a book method
        public void returnBook(String isbn) {
            for (Book b : books) {
                if (b.getIsbn().equals(isbn)) {
                    if (!b.isAvailable()) {
                        b.returnBook();
                        System.out.println("Book returned ");
                    } else {
                        System.out.println("Book was not checked out");
                    }
                    return;
                }
            }
            System.out.println("Book not found");
        }
}
 
public class Main {

	public static void main(String[] args) {
				
		//Add your Menu Here
		
		Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while(true) {
        	//Create a Main class with a menu
            System.out.println("\n=== Library Menu ===");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Display All Books");
            System.out.println("4. Search by Title");
            System.out.println("5. Search by Author");
            System.out.println("6. Check Out Book");
            System.out.println("7. Return Book");
            System.out.println("8. Exit");
            System.out.print("Enter your choice 1-8: ");
            
            int choice;
    		try {
    			choice = scanner.nextInt();
    			scanner.nextLine(); // Clear the newline
    		} catch (Exception e){
    			System.out.println("Enter valid number");
    			scanner.nextLine(); 
                continue;
    		}

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    library.addBook(new Book(title, author, isbn));
                    break;

                case 2:
                    System.out.print("Enter ISBN to remove: ");
                    String removeIsbn = scanner.nextLine();
                    library.removeBook(removeIsbn);
                    break;

                case 3:
                    library.displayAllBooks();
                    break;

                case 4:
                    System.out.print("Enter title to search: ");
                    String searchTitle = scanner.nextLine();
                    library.searchByTitle(searchTitle);
                    break;

                case 5:
                    System.out.print("Enter author to search: ");
                    String searchAuthor = scanner.nextLine();
                    library.searchByAuthor(searchAuthor);
                    break;

                case 6:
                    System.out.print("Enter ISBN to check out: ");
                    String checkOutIsbn = scanner.nextLine();
                    library.checkOutBook(checkOutIsbn);
                    break;

                case 7:
                    System.out.print("Enter ISBN to return: ");
                    String returnIsbn = scanner.nextLine();
                    library.returnBook(returnIsbn);
                    break;

                case 8:
                    System.out.println("Exiting");
                    scanner.close();
                    return;
                    
                default:
               	 System.out.println("Enter valid number");
            }
        }
    } 
}
