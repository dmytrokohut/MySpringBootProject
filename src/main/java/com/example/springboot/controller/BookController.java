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

import com.example.springboot.common.Reader;
import com.example.springboot.dao.IReaderDAOService;

/**
 * This is class that control work of application, process requests where URL start with '/reader'
 * @author Kohut Dmytro
 * @version 1.0
 */
@RestController
@RequestMapping("/reader")
public class BookController {
	
	@Autowired
	private IReaderDAOService iReaderService;
	
	private Map<String, Integer> map = new HashMap<>();
	
	/**
	 * Get information about all readers
	 * @return ResponseEntity<Collection<Reader>>
	 */
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<Collection<Reader>> findAll() {
		return new ResponseEntity<Collection<Reader>>(iReaderService.findAll(), HttpStatus.OK);
	}
	
	/**
	 * Looking for a reader with given id
	 * @param id
	 * @return ResponseEntity<Reader>
	 */
	@RequestMapping(value="/select/{id}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<Reader> selectById(@PathVariable("id") Integer id) {
		return new ResponseEntity<Reader>(iReaderService.selectById(id), HttpStatus.OK);
	}
	
	/**
	 * Create a new reader in database
	 * @param reader
	 * @return ResponseEntity<Map<String, Integer>>
	 */
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<Map<String, Integer>> create(@RequestBody Reader reader) {
		iReaderService.create(reader);
		map.put("id", reader.getId());
		return new ResponseEntity<Map<String, Integer>>(map, HttpStatus.OK);
	}
	
	/**
	 * Update information about reader
	 * @param reader
	 * @return ResponseEntity<Map<String, Integer>>
	 */
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<Map<String, Integer>> update(@RequestBody Reader reader) {
		iReaderService.update(reader);
		map.put("id", reader.getId());
		return new ResponseEntity<Map<String, Integer>>(map, HttpStatus.OK);
	}
	
	/**
	 * Delete reader from database
	 * @param id
	 * @return ResponseEntity<Map<String, Integer>>
	 */
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<Map<String, Integer>> delete(@PathVariable("id") Integer id) {
		iReaderService.delete(id);
		map.put("id", id);
		return new ResponseEntity<Map<String, Integer>>(map, HttpStatus.OK);
	}

}
