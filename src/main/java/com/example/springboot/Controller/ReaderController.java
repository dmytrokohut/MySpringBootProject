package com.example.springboot.Controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.Base.Reader;
import com.example.springboot.DAO.ReaderDAO;

/**
 * This is class that control work of application, process requests where URL start with '/reader'
 * @author Dmytro Kohut
 * @version 1.0
 */
@RestController
@RequestMapping("/reader")
public class ReaderController {
	
	@Autowired
	private ReaderDAO readerDAO;
	
	/**
	 * Get information about all readers
	 * @return
	 */
	@RequestMapping(path="/all", method=RequestMethod.GET)
	public Collection<Reader> findAllReaders(){
		return readerDAO.findAllReaders();
	}
	
	/**
	 * Looking for a reader with given id
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/select/{id}", method=RequestMethod.GET)
	public Reader readById(@PathVariable("id") int id) {
		return readerDAO.readById(id);
	}
	
	/**
	 * Create a new reader in database
	 * @param name
	 * @param email
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String createReader(@RequestBody Reader reader){
		readerDAO.createReader(reader);
		return "Reader - was created";
	}
	
	/**
	 * Update information about reader by given id
	 * @param id
	 * @param name
	 * @param email
	 * @return
	 */
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public String updateReader(@RequestBody Reader reader) {
		readerDAO.updateReader(reader);
		return "Reader with id=" + reader.getId() + " was updated";
	}
	
	/**
	 * Delete reader from database
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public String removeReader(@PathVariable("id") int id) {
		readerDAO.removeById(id);
		return "Reader with id=" + id + " was removed from database";
	}
}
