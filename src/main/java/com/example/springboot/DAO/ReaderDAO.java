package com.example.springboot.DAO;

import com.example.springboot.Base.Reader;
import java.util.Collection;

/**
 * This interface defines a methods for work with database table - 'Reader'
 * @author Dmytro Kohut
 * @version 1.0
 */
public interface ReaderDAO {
	
	/**
	 * This method return all readers from database
	 * @return
	 */
	Collection<Reader> findAllReaders();
	
	/**
	 * This method looking for a reader with given id
	 * @param id
	 * @return
	 */
	Reader readById(int id);
	
	/**
	 * This method delete information about reader for the reader's id
	 * @param id
	 */
	void removeById(int id);
	
	/**
	 * This method insert the new reader in database
	 * @param reader
	 */
	void createReader(Reader reader);
	
	/**
	 * This method update the information about reader
	 * @param reader
	 */
	void updateReader(Reader reader);
}
