package com.example.springboot.controller;

import java.util.HashMap;
import java.util.List;
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

import com.example.springboot.dao.IBookDAOService;
import com.example.springboot.entity.Book;

/**
 * This is class that control work of application, process requests where URL start with '/book'
 * @author Kohut Dmytro
 * @version 1.0
 */
@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private IBookDAOService bookDAOService;
	
	/**
	 * Get information about all readers
	 * @return ResponseEntity<List<Book>>
	 */
	@ResponseBody
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public ResponseEntity<List<Book>> findAll() {
		List<Book> list = bookDAOService.findAll();
		if(list.isEmpty())
			return new ResponseEntity<List<Book>>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		return new ResponseEntity<List<Book>>(list, HttpStatus.OK);
	}
	
	/**
	 * Looking for a book with given id
	 * @param id
	 * @return ResponseEntity<Book>
	 */
	@ResponseBody
	@RequestMapping(value="/select/{id}", method=RequestMethod.GET)
	public ResponseEntity<Book> selectById(@PathVariable("id") Integer id) {
		Book book = bookDAOService.selectById(id);
		if(book == null)
			return new ResponseEntity<Book>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
	
	/**
	 * Create a new reader in database
	 * @param book
	 * @return ResponseEntity<Book>
	 */
	@ResponseBody
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ResponseEntity<Book> create(@RequestBody Book book) {
		Integer id = bookDAOService.create(book);
		if(id == null)
			return new ResponseEntity<Book>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		book.setId(id);
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
	
	/**
	 * Update information about book
	 * @param book
	 * @return ResponseEntity<Book>
	 */
	@ResponseBody
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public ResponseEntity<Book> update(@RequestBody Book book) {
		if(bookDAOService.update(book) == null)
			return new ResponseEntity<Book>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
	
	/**
	 * Delete book from database
	 * @param id
	 * @return ResponseEntity<Map<String, Integer>>
	 */
	@ResponseBody
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id) {
		Map<String, Object> mapToResponse = new HashMap<>();
		
		if(bookDAOService.delete(id) == null)
			return new ResponseEntity<Map<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		mapToResponse.put("id", id);
		mapToResponse.put("response", "deleted");
		return new ResponseEntity<Map<String, Object>>(mapToResponse, HttpStatus.OK);
	}
}
