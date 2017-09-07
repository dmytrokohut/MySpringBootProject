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
	private IReaderDAOService readerDAOService;
	
	private Map<String, Integer> mapToResponse = new HashMap<>();
	
	/**
	 * Get information about all readers
	 * @return ResponseEntity<List<Reader>>
	 */
	@ResponseBody
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public ResponseEntity<List<Reader>> findAll() {
		List<Reader> resultList = readerDAOService.findAll();
		if(resultList.isEmpty())
			return new ResponseEntity<List<Reader>>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		return new ResponseEntity<List<Reader>>(resultList, HttpStatus.OK);
	}
	
	/**
	 * Looking for a reader with given id
	 * @param id
	 * @return ResponseEntity<Reader>
	 */
	@ResponseBody
	@RequestMapping(value="/select/{id}", method=RequestMethod.GET)
	public ResponseEntity<Reader> selectById(@PathVariable("id") Integer id) {
		Reader reader = readerDAOService.selectById(id);
		if(reader == null)
			return new ResponseEntity<Reader>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		return new ResponseEntity<Reader>(reader, HttpStatus.OK);
	}
	
	/**
	 * Create a new reader in database
	 * @param reader
	 * @return ResponseEntity<Map<String, Integer>>
	 */
	@ResponseBody
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Integer>> create(@RequestBody Reader reader) {
		Integer id = readerDAOService.create(reader);
		if(id == null)
			return new ResponseEntity<Map<String, Integer>>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		mapToResponse.put("id", id);
		return new ResponseEntity<Map<String, Integer>>(mapToResponse, HttpStatus.OK);
	}
	
	/**
	 * Update information about reader
	 * @param reader
	 * @return ResponseEntity<Map<String, Integer>>
	 */
	@ResponseBody
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public ResponseEntity<Map<String, Integer>> update(@RequestBody Reader reader) {
		Integer id = readerDAOService.update(reader);
		if(id == null)
			return new ResponseEntity<Map<String, Integer>>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		mapToResponse.put("id", id);
		return new ResponseEntity<Map<String, Integer>>(mapToResponse, HttpStatus.OK);
	}
	
	/**
	 * Delete reader from database
	 * @param id
	 * @return ResponseEntity<Map<String, Integer>>
	 */
	@ResponseBody
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Map<String, Integer>> delete(@PathVariable("id") Integer id) {
		if(readerDAOService.delete(id) == null)
			return new ResponseEntity<Map<String, Integer>>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		mapToResponse.put("id", id);
		return new ResponseEntity<Map<String, Integer>>(mapToResponse, HttpStatus.OK);
	}

}
