import java.util.List;

import constants.SubjectCategory;
import entity.Author;
import entity.Book;
import entity.BookItem;
import entity.Catalog;
import member.Member;
import service.BookLendingService;
import service.ReservationService;

public class Tester {
	public void runTest() throws Exception {
		Author author1 = new Author("Alex", "Lexi");
		Author author2 = new Author("Ralph", "Dunhan");
		Author author3 = new Author("Lohan", "Vexlon");
		Author author4 = new Author("Carrie", "Frank");
		Author author5 = new Author("Sandra", "Wilson");
		List<Author> authorCombo1 = List.of( new Author[]{author1, author2});
		List<Author> authorCombo2 = List.of( new Author[]{author2, author3});
		List<Author> authorCombo3 = List.of( new Author[]{author3, author4});
		List<Author> authorCombo4 = List.of( new Author[]{author5, author1});
		List<Author> authorCombo5 = List.of( new Author[]{author1, author3});
		Book book1 = new Book("Study on Biology 1", SubjectCategory.SCIENCE, authorCombo1);
		Book book2 = new Book("Study on Technology 1", SubjectCategory.TECHNOLOGY, authorCombo2);
		Book book3 = new Book("Study on Entertainment 2", SubjectCategory.ENTERTAINMENT, authorCombo3);
		Book book4 = new Book("Study on Tamil", SubjectCategory.LANGUAGE, authorCombo4);
		Book book5 = new Book("Study on Literature 3", SubjectCategory.LITERATURE, authorCombo5);
		
		BookItem bookItem1a = new BookItem(book1.getBookUniqueID());
		BookItem bookItem1b = new BookItem(book1.getBookUniqueID());
		BookItem bookItem2a = new BookItem(book2.getBookUniqueID());
		BookItem bookItem2b = new BookItem(book2.getBookUniqueID());
		BookItem bookItem3a = new BookItem(book3.getBookUniqueID());
		BookItem bookItem3b = new BookItem(book3.getBookUniqueID());
		BookItem bookItem4a = new BookItem(book4.getBookUniqueID());
		BookItem bookItem4b = new BookItem(book4.getBookUniqueID());
		BookItem bookItem5a = new BookItem(book5.getBookUniqueID());
		BookItem bookItem5b = new BookItem(book5.getBookUniqueID());
		BookItem bookItem5c = new BookItem(book5.getBookUniqueID());

		Catalog catalog = Catalog.getInstance();
		catalog.addBook(book1);
		catalog.addBook(book2);
		catalog.addBook(book3);
		catalog.addBook(book4);
		catalog.addBook(book5);
		
		catalog.addBookItem(bookItem1a);
		catalog.addBookItem(bookItem1b);
		catalog.addBookItem(bookItem2a);
		catalog.addBookItem(bookItem2b);
		catalog.addBookItem(bookItem3a);
		catalog.addBookItem(bookItem3b);
		catalog.addBookItem(bookItem4a);
		catalog.addBookItem(bookItem4b);
		catalog.addBookItem(bookItem5a);
		catalog.addBookItem(bookItem5b);
		catalog.addBookItem(bookItem5c);
	
		Member member1 = new Member("James", "Jackson", null, null, null);
		Member member2 = new Member("Hilary", "Perry", null, null, null);
		Member member3 = new Member("Drake", "Ruffalo", null, null, null);
		Member member4 = new Member("John", "Smith", null, null, null);
		Member member5 = new Member("Katy", "Walsh", null, null, null);
		
		BookLendingService lendingService = BookLendingService.getInstance();
		ReservationService reservationService = ReservationService.getInstance();
		
		lendingService.checkOut(member1.getMemberId(), bookItem2a.getBookItemID());
		lendingService.checkOut(member1.getMemberId(), bookItem3a.getBookItemID());
		lendingService.checkOut(member1.getMemberId(), bookItem4a.getBookItemID());
		lendingService.checkOut(member1.getMemberId(), bookItem5a.getBookItemID());
		lendingService.checkOut(member1.getMemberId(), bookItem1a.getBookItemID());
		try{lendingService.checkOut(member1.getMemberId(), bookItem5b.getBookItemID());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		reservationService.reserveBook(member2.getMemberId(), bookItem1a.getBookItemID());
		lendingService.returnBookItem(bookItem1a.getBookItemID());
		lendingService.checkOut(member1.getMemberId(), bookItem5b.getBookItemID());
		
		
	}
}
