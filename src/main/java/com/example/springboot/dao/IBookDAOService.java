package com.example.springboot.dao;

import java.util.Collection;

import com.example.springboot.common.Book;

/**
 * This interface defines a methods for work with database table
 * @author User
 * @version 1.0
 */
public interface IBookDAOService {

	/**
	 * This method return all books from database
	 * @return Collection<Book>
	 */
	Collection<Book> findAll();
	
	/**
	 * This method looking for a book in database by given id
	 * @param id
	 * @return Book
	 */
	Book selectById(Integer id);
	
	/**
	 * This method create a new instance of book in database
	 * @param book
	 */
	void create(Book book);
	
	/**
	 * This method update information of an existing book
	 * @param book
	 */
	void update(Book book);
	
	/**
	 * This method delete existing book in database by given id
	 * @param id
	 */
	void delete(Integer id);
}
