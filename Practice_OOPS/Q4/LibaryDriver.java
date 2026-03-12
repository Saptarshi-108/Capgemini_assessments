// Library.java
package oops_Qs;

import java.util.*;

public class LibraryDriver implements Library {
	// bookId -> Book (all books in inventory)
	private final Map<String, Book> bookInventory = new HashMap<>();
	// bookId -> memberId (tracks who has which book)
	private final Map<String, String> issuedBooks = new HashMap<>();
	// memberId -> List of bookIds they have borrowed
	private final Map<String, List<String>> memberBorrowing = new HashMap<>();

	@Override
    public boolean addBook(Book book) {
        if (book == null || bookInventory.containsKey(book.getBookId())) {
            return false;
        }
        bookInventory.put(book.getBookId(), book);
        return true;
    }

	@Override
	public boolean issueBook(String bookId, String memberId) {
		if (!issuedBooks.containsKey(bookId) && bookInventory.containsKey(bookId)) {
			issuedBooks.put(bookId, memberId);
			List<String> res = new ArrayList<String>();
			res.add(bookId);
			memberBorrowing.put(memberId, res);
			return true;
		}
		return false;
	}

	@Override
	public boolean returnBook(String bookId) {
		if (bookInventory.containsKey(bookId)) {
			String memId = issuedBooks.remove(bookId);
			List<String> edit = memberBorrowing.get(memId);
			if (edit != null) {
				edit.remove(bookId);
			}
			memberBorrowing.put(memId, edit);
			return true;
		}
		return false;

	}

	@Override
	public boolean isBookAvailable(String bookId) {
		if(bookInventory.containsKey(bookId) && !issuedBooks.containsKey(bookId)) {
			return true;
		}
		return false;
	}

	@Override
	public List<Book> getBooksIssuedToMember(String memberId) {

		List<Book> books = new ArrayList<Book>();
		List<String> bookIds = memberBorrowing.get(memberId);
		if (bookIds == null)
			return new ArrayList<Book>();
		for (String bookId : bookIds) {
			Book book = bookInventory.get(bookId);
			if (book != null)
				books.add(book);
		}

		return books;
	}

	public static void main(String[] args) {
		LibraryDriver library = new LibraryDriver();

		Book b1 = new Book("b1", "Java Basics", "John Doe");
		Book b2 = new Book("b2", "Data Structures", "Jane Smith");
		Book b3 = new Book("b1", "Duplicate", "Bob"); // duplicate ID

		System.out.println("=== Test 1: Add Books ===");
		System.out.println("Add b1: " + library.addBook(b1));     // **true**
		System.out.println("Add b2: " + library.addBook(b2));     // **true**
		System.out.println("Add duplicate b1: " + library.addBook(b3)); // **false**

		System.out.println("\n=== Test 2: Issue Books ===");
		System.out.println("Issue b1 to Alice: " + library.issueBook("b1", "alice"));  // **true**
		System.out.println("Issue b1 again to Bob: " + library.issueBook("b1", "bob")); // **false**
		System.out.println("Issue b2 to Alice: " + library.issueBook("b2", "alice"));   // **true**

		System.out.println("\n=== Test 3: Availability ===");
		System.out.println("b1 available: " + library.isBookAvailable("b1"));     // **false**
		System.out.println("b2 available: " + library.isBookAvailable("b2"));     // **false**
		System.out.println("non-existent book: " + library.isBookAvailable("b3")); // **false**

		System.out.println("\n=== Test 4: Member Books ===");
		List<Book> aliceBooks = library.getBooksIssuedToMember("alice");
		System.out.println("Alice's books count: " + aliceBooks.size());           // **2**
		System.out.println("Alice has Java Basics: " + 
		    aliceBooks.stream().anyMatch(b -> b.getTitle().equals("Java Basics"))); // **true**

		List<Book> bobBooks = library.getBooksIssuedToMember("bob");
		System.out.println("Bob's books count: " + bobBooks.size());               // **0**

		System.out.println("\n=== Test 5: Return Book ===");
		System.out.println("Return b1: " + library.returnBook("b1"));              // **true**
		System.out.println("b1 available after return: " + library.isBookAvailable("b1")); // **true**
		System.out.println("Return non-issued b1 again: " + library.returnBook("b1")); // **false**

		Book b4 = new Book("b4", "Algorithms", "CLRS");
		library.addBook(b4);

		System.out.println("\n=== Test 6: Multiple Members ===");
		System.out.println("Issue b4 to Charlie: " + library.issueBook("b4", "charlie")); // **true**
		System.out.println("Charlie's books: " + library.getBooksIssuedToMember("charlie").size()); // **1**

		System.out.println("\n=== Test 7: Edge Cases ===");
		System.out.println("Add null book: " + library.addBook(null));             // **false**
		System.out.println("Issue non-existent book: " + library.issueBook("b999", "test")); // **false**
		System.out.println("Return non-existent book: " + library.returnBook("b999")); // **false**

	}
}
