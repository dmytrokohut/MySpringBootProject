package com.example.springboot.dao;

import java.util.Collection;

import com.example.springboot.common.Reader;

/**
 * This interface defines a methods for work with database table 
 * @author Kohut Dmytro
 * @version 1.0
 */
public interface IBookDAOService {
	
	/**
	 * This method return all readers from database
	 * @return Collection<Reader>
	 */
	Collection<Reader> findAll();
	
	/**
	 * This method looking for a reader in database by given id
	 * @param id
	 * @return Reader
	 */
	Reader selectById(Integer id);
	
	/**
	 * This method create a new instance of reader in database
	 * @param reader
	 */
	void create(Reader reader);
	
	/**
	 * This method update information of an existing reader
	 * @param reader 
	 */
	void update(Reader reader);
	
	/**
	 * This method delete existing reader in database by given id
	 * @param id
	 */
	void delete(Integer id);
}
