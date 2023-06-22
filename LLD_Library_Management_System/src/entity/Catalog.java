package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exception.InvalidBookIdException;
import exception.InvalidBookItemIdException;

public class Catalog {
	List<Book> books;
	List<BookItem> bookItems;
	Map<String, BookItem> bookItemMap;
	Map<String, Book> bookMap;
	private static Catalog instance;
	public static Catalog getInstance() {
		if(instance == null) {
			instance = new Catalog();
		}
		return instance;
	}
	private Catalog() {
		books = new ArrayList<Book>();
		bookMap = new HashMap<String, Book>();
		bookItems = new ArrayList<BookItem>();
		bookItemMap = new HashMap<String, BookItem>();
	}
	public void addBookItem(BookItem bookItem) {
		bookItems.add(bookItem);
		bookItemMap.put(bookItem.getBookItemID(), bookItem);
	}
	public void addBook(Book book) {
		books.add(book);
		bookMap.put(book.BookUniqueID, book);
	}
	public BookItem getBookItem(String bookItemId) throws InvalidBookItemIdException {
		if(!bookItemMap.containsKey(bookItemId)) {
			throw new InvalidBookItemIdException("Member Id provided is invalid");
		}
		return bookItemMap.get(bookItemId);
	}
	public Book getBook(String bookId) throws InvalidBookIdException {
		if(!bookMap.containsKey(bookId)) {
			throw new InvalidBookIdException("Member Id provided is invalid");
		}
		return bookMap.get(bookId);
	}
}
