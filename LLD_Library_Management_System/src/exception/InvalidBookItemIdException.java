package exception;

public class InvalidBookItemIdException extends Exception{
	public InvalidBookItemIdException(String message) {
		super(message);
	}
}
