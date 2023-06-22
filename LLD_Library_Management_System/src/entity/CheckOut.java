package entity;

import java.util.Date;

import constants.CheckOutStatus;

public class CheckOut {
	private BookItem bookItem;
	private Date dueDate;
	private String memberId;
	private CheckOutStatus status;
	public CheckOut(BookItem bookItem, Date dueDate, String memberId, CheckOutStatus status) {
		super();
		this.bookItem = bookItem;
		this.dueDate = dueDate;
		this.memberId = memberId;
		this.status = status;
	}
	public BookItem getBookItem() {
		return bookItem;
	}
	public void setBookItem(BookItem bookItem) {
		this.bookItem = bookItem;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public CheckOutStatus getStatus() {
		return status;
	}
	public void setStatus(CheckOutStatus status) {
		this.status = status;
	}
	
}
