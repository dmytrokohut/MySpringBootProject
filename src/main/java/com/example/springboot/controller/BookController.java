package com.example.springboot.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.common.Book;
import com.example.springboot.common.StringResponse;
import com.example.springboot.dao.IBookDAOService;

/**
 * This is class that control work of application, process requests where URL start with '/book'
 * @author Kohut Dmytro
 * @version 1.0
 */
@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private IBookDAOService iBookDAOService;
	
	/**
	 * Get information about all readers
	 * @return ResponseEntity<Collection<Book>>
	 */
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<Collection<Book>> findAll() {
		return new ResponseEntity<Collection<Book>>(iBookDAOService.findAll(), HttpStatus.OK);
	}
	
	/**
	 * Looking for a book with given id
	 * @param id
	 * @return ResponseEntity<Book>
	 */
	@RequestMapping(value="/select/{id}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<Book> selectById(@PathVariable("id") Integer id) {
		return new ResponseEntity<Book>(iBookDAOService.selectById(id), HttpStatus.OK);
	}
	
	/**
	 * Create a new reader in database
	 * @param book
	 * @return ResponseEntity<StringResponse>
	 */
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<StringResponse> create(@RequestBody Book book) {
		iBookDAOService.create(book);
		return new ResponseEntity<StringResponse>(new StringResponse("Book was created !"), HttpStatus.OK);
	}
	
	/**
	 * Update information about book
	 * @param book
	 * @return ResponseEntity<StringResponse>
	 */
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<StringResponse> update(@RequestBody Book book) {
		iBookDAOService.update(book);
		return new ResponseEntity<StringResponse>(new StringResponse("Book was updated !"), HttpStatus.OK);
	}
	
	/**
	 * Delete book from database
	 * @param id
	 * @return ResponseEntity<StringResponse>
	 */
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<StringResponse> delete(@PathVariable Integer id) {
		iBookDAOService.delete(id);
		return new ResponseEntity<StringResponse>(new StringResponse("Book was deleter !"), HttpStatus.OK);
	}
}
