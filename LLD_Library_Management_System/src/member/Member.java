package member;

import java.util.ArrayList;
import java.util.List;

import entity.BookItem;
import helper.UniqueIDGenerator;
import service.MemberService;

public class Member extends Person{
	private UniqueIDGenerator uuidGenerator = UniqueIDGenerator.getInstance("Member");
	String memberId;
	List<BookItem> borrowedBookItems;
	public Member() {
		super();
	}
	public Member(String firstName, String lastName, String emailAddress, String userName, String password) {
		super(firstName, lastName, emailAddress, userName, password);
		this.memberId = uuidGenerator.generateUUID();
		borrowedBookItems = new ArrayList<BookItem>();
		MemberService.getInstance().addMember(this);
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public List<BookItem> getBorrowedBookItems() {
		return borrowedBookItems;
	}
	public void setBorrowedBookItems(List<BookItem> borrowedBookItems) {
		this.borrowedBookItems = borrowedBookItems;
	}
	public String getName() {
		return firstName+" "+lastName;
	}
	public void removeBorrowedBook(BookItem bookitem) {
		borrowedBookItems.remove(bookitem);
	}
	
}
