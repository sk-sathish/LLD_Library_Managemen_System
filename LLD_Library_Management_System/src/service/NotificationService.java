package service;

import java.util.Date;
import java.util.List;

import entity.BookItem;
import entity.CheckOut;
import exception.InvalidMemberIdException;
import member.Member;

public class NotificationService {
	private static NotificationService instance;
	public static NotificationService getInstance() {
		if(instance == null) {
			instance = new NotificationService();
		}
		return instance;
	}
	public void notifyReservedMember(BookItem bookItem) throws InvalidMemberIdException {
		String memberId = ReservationService.getInstance().getReservedMemberBasedOnSeniority(bookItem.getBookItemID());
		if(memberId != null) {
			notifyMember(bookItem.getName()+" is availalbe", memberId);
		}
	}
	public void notifyDueDate() throws InvalidMemberIdException {
		List<CheckOut> currentCheckOuts = BookLendingService.getInstance().getCheckOuts();
		Date now = new Date();
		for(CheckOut checkout: currentCheckOuts) {
			if(!checkout.getDueDate().after(now)) {
				notifyMember(checkout.getBookItem().getName()+" is up for due, kindly return it soon", checkout.getMemberId());
			}
		}
	}
	public void notifyMember(String message, String memberId) throws InvalidMemberIdException {
		Member member = MemberService.getInstance().getMember(memberId);
		System.out.println("Notified member :"+ member.getName()+", the message is :"+message);
	}
}
