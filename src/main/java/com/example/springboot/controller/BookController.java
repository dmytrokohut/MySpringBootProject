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
	
	private Map<String, Integer> map = new HashMap<>();
	
	/**
	 * Get information about all readers
	 * @return ResponseEntity<Collection<Book>>
	 */
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Book>> findAll() {
		return new ResponseEntity<List<Book>>(bookDAOService.findAll(), HttpStatus.OK);
	}
	
	/**
	 * Looking for a book with given id
	 * @param id
	 * @return ResponseEntity<Book>
	 */
	@RequestMapping(value="/select/{id}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<Book> selectById(@PathVariable("id") Integer id) {
		return new ResponseEntity<Book>(bookDAOService.selectById(id), HttpStatus.OK);
	}
	
	/**
	 * Create a new reader in database
	 * @param book
	 * @return ResponseEntity<Map<String, Integer>>
	 */
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String, Integer>> create(@RequestBody Book book) {
		map.put("id", bookDAOService.create(book));
		return new ResponseEntity<Map<String, Integer>>(map, HttpStatus.OK);
	}
	
	/**
	 * Update information about book
	 * @param book
	 * @return ResponseEntity<Map<String, Integer>>
	 */
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<Map<String, Integer>> update(@RequestBody Book book) {
		map.put("id", bookDAOService.update(book));
		return new ResponseEntity<Map<String, Integer>>(map, HttpStatus.OK);
	}
	
	/**
	 * Delete book from database
	 * @param id
	 * @return ResponseEntity<Map<String, Integer>>
	 */
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<Map<String, Integer>> delete(@PathVariable Integer id) {
		map.put("id", bookDAOService.delete(id));
		return new ResponseEntity<Map<String, Integer>>(map, HttpStatus.OK);
	}
}