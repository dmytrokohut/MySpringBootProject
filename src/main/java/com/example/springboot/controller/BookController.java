package com.example.springboot.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
	private IBookDAOService iBookService;
	
	private Map<String, Integer> map = new HashMap<>();
	
	/**
	 * Get information about all readers
	 * @return ResponseEntity<Collection<Book>>
	 */
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<Collection<Book>> findAll() {
		return new ResponseEntity<Collection<Book>>(iBookService.findAll(), HttpStatus.OK);
	}
	
	/**
	 * Looking for a book with given id
	 * @param id
	 * @return ResponseEntity<Book>
	 */
	@RequestMapping(value="/select/{id}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<Book> selectById(@PathVariable("id") Integer id) {
		return new ResponseEntity<Book>(iBookService.selectById(id), HttpStatus.OK);
	}
	
	/**
	 * Create a new reader in database
	 * @param book
	 * @return ResponseEntity<Map<String, Integer>>
	 */
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String, Integer>> create(@RequestBody Book book) {
		iBookService.create(book);
		map.put("id", book.getId());
		return new ResponseEntity<Map<String, Integer>>(map, HttpStatus.OK);
	}
	
	/**
	 * Update information about book
	 * @param book
	 * @return ResponseEntity<Map<String, Integer>>
	 */
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<Map<String, Integer>> update(@RequestBody Book book) {
		iBookService.update(book);
		map.put("id", book.getId());
		return new ResponseEntity<Map<String, Integer>>(map, HttpStatus.OK);
	}
	
	/**
	 * Delete book from database
	 * @param id
	 * @return ResponseEntity<Map<String, Integer>>
	 */
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<Map<String, Integer>> delete(@PathVariable Integer id) {
		iBookService.delete(id);
		map.put("id", id);
		return new ResponseEntity<Map<String, Integer>>(map, HttpStatus.OK);
	}
}
