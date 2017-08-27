package com.example.springboot.DAO;

import java.util.Collection;

import com.example.springboot.Base.Book;

/**
 * This interface defines a methods for work with database table - "Book"
 * @author Dmytro Kohut
 * @version 1.0
 */
public interface BookDAO {
	
	/**
	 * This method return all books from database
	 * @return
	 */
	Collection<Book> findAllBooks();
	
	/**
	 * This method looking for a book in database by given id
	 * @param id
	 * @return
	 */
	Book selectById(int id);
	
	/**
	 * This method create a new book in database
	 * @param book
	 */
	void createBook(Book book);
	
	/**
	 * This method update information of an existing book
	 * @param book 
	 */
	void updateBook(Book book);
	
	/**
	 * This method delete existing book in database by given id
	 * @param id
	 */
	void deleteBook(int id);
	
}
