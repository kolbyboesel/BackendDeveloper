
public class IBook {
	String title;
	String authors;
	String ISBN13;

	public IBook(String title, String authors, String ISBN13) {
		this.title = title;
		this.authors = authors;
		this.ISBN13 = ISBN13;
	}

	/**
	 * Returns the title of the book.
	 * 
	 * @return title of the book
	 */
	String getTitle() {
		return title;

	}

	/**
	 * Returns a string that contains the authors of the book as a single string
	 * with different authors separated by /.
	 * 
	 * @return author names as single string
	 */
	String getAuthors() {
		return authors;
	}

	/**
	 * Returns the 13 digit ISBN (ISBN13) that uniquely identifies this book.
	 * 
	 * @return ISBN number of book
	 */
	String getISBN13() {
		return ISBN13;
	}

	public String toString() {
		String tempString = "\"" + title + "\"" + " by " + authors + ", ISBN: " + ISBN13;
		return tempString;

	}

}
