package entity;

import java.util.ArrayList;
import java.util.List;

import constants.BookItemStatus;
import exception.InvalidBookIdException;
import helper.UniqueIDGenerator;

public class BookItem extends Book{
	private String edition;
	private String publicationDate;
	private String bookItemID;
	private String rackNumber;
	private BookItemStatus status;
	private UniqueIDGenerator uuidGenerator = UniqueIDGenerator.getInstance("BookItem");
	List<CheckOut> checkOuts;
	public BookItem(String bookId) throws InvalidBookIdException {
		super(bookId);
		this.bookItemID = uuidGenerator.generateUUID();
		this.status = BookItemStatus.AVAILABLE;
		checkOuts = new ArrayList<CheckOut>();
	}
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	public String getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}
	public String getBookItemID() {
		return bookItemID;
	}
	public void setBookItemID(String bookItemID) {
		this.bookItemID = bookItemID;
	}
	public String getRackNumber() {
		return rackNumber;
	}
	public void setRackNumber(String rackNumber) {
		this.rackNumber = rackNumber;
	}
	public BookItemStatus getStatus() {
		return status;
	}
	public void setStatus(BookItemStatus status) {
		this.status = status;
	}
	public List<CheckOut> getCheckOuts() {
		return checkOuts;
	}
	public void setCheckOuts(List<CheckOut> checkOuts) {
		this.checkOuts = checkOuts;
	}
	public String getName() {
		return title;
	}
	
}
