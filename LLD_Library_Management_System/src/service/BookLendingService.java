package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import constants.BookItemStatus;
import constants.CheckOutStatus;
import constants.Constants;
import entity.BookItem;
import entity.Catalog;
import entity.CheckOut;
import exception.BookUnAvailableException;
import exception.InvalidBookItemIdException;
import exception.InvalidMemberIdException;
import exception.MemberCheckOutQuotaFullException;
import member.Member;

public class BookLendingService {
	List<CheckOut> checkOuts;
	List<CheckOut> completedCheckOuts;
	private static BookLendingService instance;
	public static BookLendingService getInstance() {
		if(instance == null) {
			instance = new BookLendingService();
		}
		return instance;
	}
	private BookLendingService() {
		checkOuts = new ArrayList<CheckOut>();
		completedCheckOuts = new ArrayList<CheckOut>();
	}
	public void checkOut(String memberId, String bookItemId) throws InvalidMemberIdException, MemberCheckOutQuotaFullException, InvalidBookItemIdException, BookUnAvailableException {
		Member member = MemberService.getInstance().getMember(memberId);
		List<BookItem> memberBorrowedBooks = member.getBorrowedBookItems();
		int memberBorrowCount = memberBorrowedBooks.size();
		//checking limit exceed for books to borrow
		if(memberBorrowCount == Constants.MAX_BOOK_COUNT) {
			throw new MemberCheckOutQuotaFullException("Member Max Book Count Reached");
		}
		Catalog catalog = Catalog.getInstance();
		BookItem bookItem = catalog.getBookItem(bookItemId);
		//checking if the book is available
		if(!bookItem.getStatus().equals(BookItemStatus.AVAILABLE)) {
			throw new BookUnAvailableException("Book Item unavailalbe");
		}
		Date date = new Date();
		long millis = date.getTime();
		long maxDaysToKeep = Constants.MAX_DAYS_TO_KEEP;
		millis += maxDaysToKeep*24*60*60*1000;
		//calculating due date
		Date dueDate = new Date(millis);
		CheckOut checkout = new CheckOut(bookItem, dueDate, memberId, CheckOutStatus.LENT);
		checkOuts.add(checkout);
		//adding the book item to member obj
		member.getBorrowedBookItems().add(bookItem);
		//removing the member from reservation if he's reserved for the book
		ReservationService.getInstance().removeMemberFromReservation(memberId, bookItemId);
		bookItem.getCheckOuts().add(checkout);
		System.out.println(member.getName()+" check out the "+bookItem.getName()+" with due on "+dueDate.toString());
	}
	public void returnBookItem(String bookItemId) throws InvalidBookItemIdException, InvalidMemberIdException {
		Catalog catalog = Catalog.getInstance();
		BookItem bookItem = catalog.getBookItem(bookItemId);
		List<CheckOut> checkOuts = bookItem.getCheckOuts();
		CheckOut checkOut = checkOuts.get(checkOuts.size()-1);
		double fine = 0;
		//checking for fine
		if(!checkOut.getDueDate().after(new Date())) {
			FineService fineService = FineService.getInstance();
			fine = fineService.calculateFine(checkOut);
		}
		checkOut.setStatus(CheckOutStatus.RETURNED);
		bookItem.setStatus(BookItemStatus.AVAILABLE);
		Member member = MemberService.getInstance().getMember(checkOut.getMemberId());
		//removing book from member obj
		member.removeBorrowedBook(bookItem);
		
		if(fine == 0) {
			System.out.println(member.getName()+" check out the "+bookItem.getName());
		}
		else {
			System.out.println(member.getName()+" check out the "+bookItem.getName()+"with fine $"+fine);		
		}
		this.checkOuts.remove(checkOut);
		this.completedCheckOuts.add(checkOut);
		//notifying the first reserved member
		NotificationService notificationService = NotificationService.getInstance();
		notificationService.notifyReservedMember(bookItem);
	}
	public List<CheckOut> getCheckOuts() {
		return checkOuts;
	}
	
}
