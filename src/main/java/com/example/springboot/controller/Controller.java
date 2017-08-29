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

import com.example.springboot.common.Reader;
import com.example.springboot.common.StringResponse;
import com.example.springboot.dao.IReaderDAOService;

/**
 * This is class that control work of application, process requests where URL start with '/reader'
 * @author Kohut Dmytro
 * @version 1.0
 */
@RestController
@RequestMapping("/reader")
public class Controller {
	
	@Autowired
	private IReaderDAOService iReaderDAOService;
	
	/**
	 * Get information about all readers
	 * @return ResponseEntity<Collection<Reader>>
	 */
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<Collection<Reader>> findAll(){
		return new ResponseEntity<Collection<Reader>>(iReaderDAOService.findAll(), HttpStatus.OK);
	}
	
	/**
	 * Looking for a reader with given id
	 * @param id
	 * @return ResponseEntity<Reader>
	 */
	@RequestMapping(value="/select/{id}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<Reader> selectById(@PathVariable("id") Integer id) {
		return new ResponseEntity<Reader>(iReaderDAOService.selectById(id), HttpStatus.OK);
	}
	
	/**
	 * Create a new reader in database
	 * @param reader
	 * @return ResponseEntity<StringResponse>
	 */
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<StringResponse> create(@RequestBody Reader reader) {
		iReaderDAOService.create(reader);
		return new ResponseEntity<StringResponse>(new StringResponse("New reader was created !"), HttpStatus.OK);
	}
	
	/**
	 * Update information about reader
	 * @param reader
	 * @return ResponseEntity<StringResponse>
	 */
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<StringResponse> update(@RequestBody Reader reader) {
		iReaderDAOService.update(reader);
		return new ResponseEntity<StringResponse>(new StringResponse("Reader was updated !"), HttpStatus.OK);
	}
	
	/**
	 * Delete reader from database
	 * @param id
	 * @return ResponseEntity<StringResponse>
	 */
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<StringResponse> delete(@PathVariable("id") Integer id) {
		iReaderDAOService.delete(id);
		return new ResponseEntity<StringResponse>(new StringResponse("Reader was deleted !"), HttpStatus.OK);
	}

}
