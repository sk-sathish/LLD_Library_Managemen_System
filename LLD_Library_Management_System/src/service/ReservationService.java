package service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import exception.InvalidBookItemIdException;
import exception.InvalidMemberIdException;

public class ReservationService {
	HashMap<String, Queue<String>> reservationList;
	private static ReservationService instance;
	public static ReservationService getInstance() {
		if(instance == null) {
			instance = new ReservationService();
		}
		return instance;
	}
	
	public ReservationService() {
		super();
		this.reservationList = new HashMap<String, Queue<String>>();
	}

	public void reserveBook(String memberId, String bookItemId) throws InvalidMemberIdException, InvalidBookItemIdException {
		if(!reservationList.containsKey(bookItemId)) {
			reservationList.put(bookItemId, new LinkedList<String>());
		}
		reservationList.get(bookItemId).add(memberId);
	}
	public String getReservedMemberBasedOnSeniority(String bookItemId) {
		if(reservationList.containsKey(bookItemId)) {
			Queue<String> queue = reservationList.get(bookItemId);
			if(!queue.isEmpty()) {
				return queue.peek();
			}
		}
		return null;
	}
	public void removeMemberFromReservation(String memberId, String bookItemId) {
		if(reservationList.containsKey(bookItemId)) {
			Queue<String> queue = reservationList.get(bookItemId);
			if(!queue.isEmpty()) {
				queue.remove(memberId);
			}
		}
	}
}
