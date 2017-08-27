package com.example.springboot.dao;

import java.util.Collection;

/**
 * This interface defines a methods for work with database
 * @author Dmytro Kohut
 * @version 1.1
 */
public interface DAOInterface<T> {

	/**
	 * This method return all records given object-type from database
	 * @return
	 */
	Collection<T> findAll();
	
	/**
	 * This method looking for a record in database table by given id
	 * @param id
	 * @return
	 */
	T selectById(Integer id);
	
	/**
	 * This method create a new instance of object in database
	 * @param object
	 */
	void create(T object);
	
	/**
	 * This method update information of an existing record
	 * @param object 
	 */
	void update(T object);
	
	/**
	 * This method delete existing record in database by given id
	 * @param id
	 */
	void delete(Integer id);
}
