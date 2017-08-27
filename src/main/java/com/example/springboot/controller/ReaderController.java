package com.example.springboot.controller;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.base.Reader;
import com.example.springboot.dao.DAOInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This is class that control work of application, process requests where URL start with '/reader'
 * @author Dmytro Kohut
 * @version 1.1
 */
@RestController
@RequestMapping("/reader")
public class ReaderController {
	
	@Autowired
	private DAOInterface<Reader> readerDAO;
	
	static final private ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * Get information about all readers
	 * @return
	 */
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public Collection<Reader> findAllReaders(){
		return readerDAO.findAll();
	}
	
	/**
	 * Looking for a reader with given id
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/select/{id}", method=RequestMethod.GET)
	public Reader selectById(@PathVariable("id") Integer id) {
		return readerDAO.selectById(id);
	}
	
	/**
	 * Create a new reader in database
	 * @param book
	 * @return
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public JsonNode create(@RequestBody Reader reader) throws JsonProcessingException, IOException{
		readerDAO.create(reader);
		return objectMapper.readTree("{\"result\":\"Reader was created !\"}");
	}
	
	/**
	 * Update information about reader
	 * @param book
	 * @return
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public JsonNode update(@RequestBody Reader reader) throws JsonProcessingException, IOException {
		readerDAO.update(reader);
		return objectMapper.readTree("{\"result\":\"Reader was updated !\"}");
	}
	
	/**
	 * Delete reader from database
	 * @param id
	 * @return
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public JsonNode delete(@PathVariable("id") int id) throws JsonProcessingException, IOException {
		readerDAO.delete(id);
		return objectMapper.readTree("{\"result\":\"Reader was deleted !\"}");
	}
}
