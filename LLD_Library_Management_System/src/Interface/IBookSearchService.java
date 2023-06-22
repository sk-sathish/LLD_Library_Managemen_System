package Interface;

import java.util.List;

import entity.BookItem;

public interface IBookSearchService {
	public List<BookItem> searchByTitle(String title);
	public List<BookItem> searchByAuthor(String author);
	public List<BookItem> searchByPublicationDate(String date);
}
