package oops_Qs;

// LibraryDesign.java
import java.util.List;
import java.util.Map;

interface Library {
	/**
	 * Adds a new book to the library inventory. Returns true if added successfully.
	 */
	boolean addBook(Book book);

	/**
	 * Issues a book to a member if the book is available. Returns true if issued
	 * successfully.
	 */
	boolean issueBook(String bookId, String memberId);

	/**
	 * Returns a book, making it available again. Returns true if book was found and
	 * returned.
	 */
	boolean returnBook(String bookId);

	/**
	 * Checks if a book is currently available.
	 */
	boolean isBookAvailable(String bookId);

	/**
	 * Returns all books currently issued to a member.
	 */
	List<Book> getBooksIssuedToMember(String memberId);
}

final class Book {
	private final String bookId;
	private final String title;
	private final String author;

	public Book(String bookId, String title, String author) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
	}

	public String getBookId() {
		return bookId;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}
}

final class Member {
	private final String memberId;
	private final String name;

	public Member(String memberId, String name) {
		this.memberId = memberId;
		this.name = name;
	}

	public String getMemberId() {
		return memberId;
	}

	public String getName() {
		return name;
	}
}
