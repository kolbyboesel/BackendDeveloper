import java.util.ArrayList;
import java.util.List;

public class TestFile {
	public static void main(String[] args) {
		System.out.println("Test 1: " + test1());
		System.out.println("Test 2: " + test2());
		System.out.println("Test 3: " + test3());
		System.out.println("Test 4: " + test4());
		System.out.println("Test 5: " + test5());
	}

	/**
	 * This method tests the getByISBN method
	 * 
	 * @return true if the test is successful and false otherwise
	 */
	public static boolean test1() {
		BookMapperBackend testBackend = new BookMapperBackend();
		IBook testBook = new IBook("Book 1", "Kolby", "584");
		IBook testBook1 = new IBook("Book 2", "Kolby", "231");
		IBook testBook2 = new IBook("Book 3", "Kolby", "589");

		testBackend.addBook(testBook);
		testBackend.addBook(testBook1);
		testBackend.addBook(testBook2);

		if (testBackend.getByISBN("589").toString().equals(testBook2.toString())
				&& testBackend.getNumberOfBooks() == 3) {
			return true;

		}
		return false;
	}

	/**
	 * This method tests the searchByTitleWord method
	 * 
	 * @return true if the test is successful and false otherwise
	 */
	public static boolean test2() {

		BookMapperBackend testBackend = new BookMapperBackend();
		IBook testBook = new IBook("Book 1", "Kolby", "584");
		IBook testBook1 = new IBook("Book 2", "John", "394");
		IBook testBook2 = new IBook("Book 3", "Carlos", "143");

		testBackend.addBook(testBook);
		testBackend.addBook(testBook1);
		testBackend.addBook(testBook2);

		List<IBook> tempList = new ArrayList<IBook>();
		tempList.add(testBook2);

		if (testBackend.getNumberOfBooks() == 3
				&& testBackend.searchByTitleWord("3").toString().equals(tempList.toString())) {
			return true;

		}

		return false;
	}

	/**
	 * This method tests the functionality of the authorFilter functions
	 * 
	 * @return true if the test is successful and false otherwise
	 */
	public static boolean test3() {
		BookMapperBackend testBackend = new BookMapperBackend();
		IBook testBook = new IBook("Book 1", "Kolby", "584");
		IBook testBook1 = new IBook("Book 2", "John", "394");
		IBook testBook2 = new IBook("Book 3", "Carlos", "143");
		IBook testBook3 = new IBook("Book 1", "Carter", "490");
		IBook testBook4 = new IBook("Book 2", "Colin", "1234");
		IBook testBook5 = new IBook("Book 3", "James", "1958");

		testBackend.addBook(testBook);
		testBackend.addBook(testBook1);
		testBackend.addBook(testBook2);
		testBackend.addBook(testBook3);
		testBackend.addBook(testBook4);
		testBackend.addBook(testBook5);

		if (testBackend.getNumberOfBooks() == 6) {
			return true;
		}
		return false;
	}

	/**
	 * This method tests the functionality of the authorFilter functions
	 * 
	 * @return true if the test is successful and false otherwise
	 */
	public static boolean test4() {
		boolean check1 = false;
		boolean check2 = false;

		BookMapperBackend testBackend = new BookMapperBackend();
		testBackend.setAuthorFilter("Kolby");

		if (testBackend.getAuthorFilter().equals("Kolby")) {
			check1 = true;
		}

		testBackend.resetAuthorFilter();

		if (testBackend.getAuthorFilter() == null) {
			check2 = true;
		}

		if (check1 == true && check2 == true) {
			return true;
		}

		return false;
	}

	/**
	 * This method tests searching for a book when both a word and author filter are
	 * given
	 * 
	 * @return true if the test is successful and false otherwise
	 */
	public static boolean test5() {
		BookMapperBackend testBackend = new BookMapperBackend();
		IBook testBook = new IBook("Book 1", "Kolby", "584");
		IBook testBook1 = new IBook("Book 2", "John", "394");
		IBook testBook2 = new IBook("Book 3", "Carlos", "143");
		IBook testBook3 = new IBook("Book 4", "Carter", "490");
		IBook testBook4 = new IBook("Book 5", "Colin", "1234");
		IBook testBook5 = new IBook("Book 6", "Kolby", "1958");

		testBackend.addBook(testBook);
		testBackend.addBook(testBook1);
		testBackend.addBook(testBook2);
		testBackend.addBook(testBook3);
		testBackend.addBook(testBook4);
		testBackend.addBook(testBook5);

		testBackend.setAuthorFilter("Kolby");

		List<IBook> tempList = new ArrayList<IBook>();
		tempList.add(testBook5);
		tempList.add(testBook);

		if (testBackend.searchByTitleWord("Book").toString().equals(tempList.toString())) {
			return true;
		}

		return false;
	}

}
