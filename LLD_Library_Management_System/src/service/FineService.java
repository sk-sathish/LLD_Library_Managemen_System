package service;

import java.util.Date;

import constants.Constants;
import entity.CheckOut;

public class FineService {
	private static FineService instance;
	public static FineService getInstance() {
		if(instance == null) {
			instance = new FineService();
		}
		return instance;
	}
	public double calculateFine(CheckOut checkOut) {
		Date dueDate = checkOut.getDueDate();
		double millis = new Date().getTime()-dueDate.getTime();
		double days = Math.ceil(millis/Constants.DAY_IN_MILLI_SECONDS);
		double fine = days*Constants.PER_DAY_FINE;
		return fine; 
	}
}
