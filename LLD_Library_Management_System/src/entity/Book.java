package entity;

import java.util.List;

import constants.SubjectCategory;
import exception.InvalidBookIdException;
import helper.UniqueIDGenerator;

public class Book {
	String title;
	SubjectCategory category;
	List<Author> authors;
	private UniqueIDGenerator uuidGenerator = UniqueIDGenerator.getInstance("Book");
	String BookUniqueID;
	public Book(String title, SubjectCategory category, List<Author> authors) {
		super();
		this.title = title;
		this.category = category;
		this.authors = authors;
		this.BookUniqueID = uuidGenerator.generateUUID();
		Catalog catalog = Catalog.getInstance();
		catalog.addBook(this);
	}
	public Book(String bookUniqueId) throws InvalidBookIdException {
		super();
		Catalog catalog = Catalog.getInstance();
		Book book = catalog.getBook(bookUniqueId);
		this.title = book.title;
		this.category = book.category;
		this.authors = book.authors;
		this.BookUniqueID = bookUniqueId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public SubjectCategory getCategory() {
		return category;
	}
	public void setCategory(SubjectCategory category) {
		this.category = category;
	}
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	public String getBookUniqueID() {
		return BookUniqueID;
	}
	public void setBookUniqueID(String bookUniqueID) {
		BookUniqueID = bookUniqueID;
	}
	
}
