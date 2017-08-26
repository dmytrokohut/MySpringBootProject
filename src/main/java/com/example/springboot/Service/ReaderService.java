package com.example.springboot.Service;

import org.springframework.stereotype.Service;

import com.example.springboot.Base.Reader;
import com.example.springboot.DAO.ReaderDAO;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * This is additional service-layer. 
 * @author Dmytro Kohut
 */
@Service
public class ReaderService {
	
	@Autowired
	@Qualifier("readerDAO")
	private ReaderDAO readerDAO;
	
	public Collection<Reader> findAllReaders(){
		return readerDAO.findAllReaders();
	}
	
	public Reader selectById(int id) {
		return readerDAO.readById(id);
	}
	
	public void deleteById(int id) {
		readerDAO.removeById(id);
	}
	
	public void createReader(Reader reader) {
		readerDAO.createReader(reader);
	}
	
	public void updateReader(Reader reader) {
		readerDAO.updateReader(reader);
	}	
	
}
