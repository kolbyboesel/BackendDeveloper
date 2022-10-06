import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Instances of this interface implement the search and filter functionality of
 * the Book Mapper app using hash maps.
 */
@SuppressWarnings("rawtypes")
public class BookMapperBackend {
	HashtableMap backendDatabase = new HashtableMap();
	String authorFilter = null;

	/**
	 * Adds a new book to the backend's database and is stored in a hash table
	 * internally.
	 * 
	 * @param book the book to add
	 */
	public void addBook(IBook book) {
		Object bookIBSN = book.getISBN13();
		ArrayList<String> titleAuthor = new ArrayList<String>();
		titleAuthor.add(book.getTitle());
		titleAuthor.add(book.getAuthors());

		backendDatabase.put(bookIBSN, titleAuthor);
	}

	/**
	 * Returns the number of books stored in the backend's database.
	 * 
	 * @return the number of books
	 */
	public int getNumberOfBooks() {
		return backendDatabase.size();
	}

	/**
	 * This method can be used to set a filter for the author names contained in the
	 * search results. A book is only returned as a result for a search by title, it
	 * is also contains the string filterBy in the names of its authors.
	 * 
	 * @param filterBy the string that the book's author names must contain
	 */
	public void setAuthorFilter(String filterBy) {
		authorFilter = filterBy;
	}

	/**
	 * Returns the string used as the author filter, null if no author filter is
	 * currently set.
	 * 
	 * @return the string used as the author filter, or null if none is set
	 */
	public String getAuthorFilter() {
		return authorFilter;
	}

	/**
	 * Resets the author filter to null (no filter).
	 */
	public void resetAuthorFilter() {
		authorFilter = null;
	}

	/**
	 * Search through all the books in the title base and return books whose title
	 * contains the string word (and that satisfies the author filter, if an author
	 * filter is set).
	 * 
	 * @param word word that must be contained in a book's title in result set
	 * @return list of books found
	 */

	public List<IBook> searchByTitleWord(String word) {
		List<IBook> foundBookList = new ArrayList<IBook>();
		ArrayList books = backendDatabase.keySet();

		Iterator bookIterator = books.iterator();
		while (bookIterator.hasNext()) {
			Object tempObject = bookIterator.next();
			String book = tempObject.toString();
			String ISBN = book.substring(1, book.indexOf(","));
			String title = book.substring(book.indexOf(",") + 3, book.lastIndexOf(","));
			String author = book.substring(book.lastIndexOf(",") + 2, book.lastIndexOf("]") - 1);

			if (word.equals("")) {
				if (author.equals(authorFilter)) {
					IBook tempBook = new IBook(title, author, ISBN);
					foundBookList.add(tempBook);
				}
			} else if (authorFilter == null) {
				if (title.contains(word)) {
					IBook tempBook = new IBook(title, author, ISBN);
					foundBookList.add(tempBook);
				}
			} else {
				if (author.equals(authorFilter) && title.contains(word)) {
					IBook tempBook = new IBook(title, author, ISBN);

					foundBookList.add(tempBook);
				}
			}

		}

		return foundBookList;

	}

	/**
	 * Return the book uniquely identified by the ISBN, or null if ISBN is not
	 * present in the dataset.
	 * 
	 * @param ISBN the book's ISBN number
	 * @return the book identified by the ISBN, or null if ISBN not in database
	 */
	public IBook getByISBN(String ISBN) {
		Object bookISBN = ISBN;
		if (backendDatabase.get(bookISBN) != null) {
			String tempBook = backendDatabase.get(bookISBN).toString();
			String title = tempBook.substring(1, tempBook.indexOf(","));
			String authors = tempBook.substring(tempBook.indexOf(",") + 2, tempBook.length() - 1);
			IBook foundBook = new IBook(title, authors, ISBN);
			return foundBook;
		}
		return null;

	}
}