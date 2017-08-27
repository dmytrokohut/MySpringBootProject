package com.example.springboot.Controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.Base.Book;
import com.example.springboot.DAO.BookDAO;

/**
 * This is class that control work of application, process requests where URL start with '/book'
 * @author Dmytro Kohut
 * @version 1.0
 */
@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookDAO bookDAO;
	
	/**
	 * Get information about all books
	 * @return
	 */
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public Collection<Book> findAllBooks(){
		return bookDAO.findAllBooks();
	}
	
	/**
	 * Looking for a book with given id
	 * @param id
	 * @return 
	 */
	@RequestMapping(value="/select/{id}", method=RequestMethod.GET)
	public Book selectById(@PathVariable("id") int id) {
		return bookDAO.selectById(id);
	}
	
	/**
	 * Create a new book in database
	 * @param book
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String createBook(@RequestBody Book book) {
		bookDAO.createBook(book);
		return "New book was created !";
	}
	
	/**
	 * Update information about book
	 * @param book
	 * @return
	 */
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public String updateBook(@RequestBody Book book) {
		bookDAO.updateBook(book);
		return "Book with id=" + book.getId() + " was updated !";
	}
	
	/**
	 * Delete book in database by given id
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public String deleteBook(@PathVariable("id") int id) {
		bookDAO.deleteBook(id);
		return "Book with id=" + id + " was deleted !";
	}
}
