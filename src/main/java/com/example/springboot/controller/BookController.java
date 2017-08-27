package com.example.springboot.controller;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.springboot.base.Book;
import com.example.springboot.dao.DAOInterface;

/**
 * This is class that control work of application, process requests where URL start with '/book'
 * @author Dmytro Kohut
 * @version 1.1
 */
@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private DAOInterface<Book> bookDAO;
	
	static private ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * Get information about all books
	 * @return
	 */
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public Collection<Book> findAllBooks(){
		return bookDAO.findAll();
	}
	
	/**
	 * Looking for a book with given id
	 * @param id
	 * @return 
	 */
	@RequestMapping(value="/select/{id}", method=RequestMethod.GET)
	public @ResponseBody Book selectById(@PathVariable("id") Integer id) {
		return bookDAO.selectById(id);
	}
	
	/**
	 * Create a new book in database
	 * @param book
	 * @return
	 * @throws JsonProcessingException 
	 * @throws IOException 
	 */
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public JsonNode create(@RequestBody Book book) throws JsonProcessingException, IOException {
		bookDAO.create(book);
		return objectMapper.readTree("{\"result\":\"Book was created !\"}");
	}
	
	/**
	 * Update information about book
	 * @param book
	 * @return
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public JsonNode update(@RequestBody Book book) throws JsonProcessingException, IOException {
		bookDAO.update(book);
		return objectMapper.readTree("{\"result\":\"Book was updated !\"}");
	}
	
	/**
	 * Delete book in database by given id
	 * @param id
	 * @return
	 * @throws JsonProcessingException 
	 * @throws IOException 
	 */
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public JsonNode delete(@PathVariable("id") Integer id) throws JsonProcessingException, IOException {
		bookDAO.delete(id);	
		return objectMapper.readTree("{\"result\":\"Book was deleted !\"}");
	}
}
