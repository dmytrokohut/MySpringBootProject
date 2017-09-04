package com.example.springboot.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.example.springboot.entity.Book;
import com.mysql.jdbc.Statement;

/**
 * This class implement methods from IBookDAOService interface
 * @author Kohut Dmytro
 * @version 1.0
 */
@Repository
public class BookDAOService implements IBookDAOService {

	private static final Logger LOGGER = Logger.getLogger(BookDAOService.class);
	
	private static final String QUERY_FIND_ALL = "SELECT id, title, author, reader_id FROM `books`;";
	private static final String QUERY_SELECT_BY_ID = "SELECT id, title, author, reader_id FROM `books` WHERE id=?;";
	private static final String QUERY_CREATE = "INSERT INTO `books`(title, author, reader_id) VALUES(?, ?, ?);";
	private static final String QUERY_UPDATE = "UPDATE `books` SET title=?, author=?, reader_id=? WHERE id=?;";
	private static final String QUERY_DELETE = "DELETE FROM `books` WHERE id=?;";
	
	private static final String ID = "id";
	private static final String TITLE = "title";
	private static final String AUTHOR = "author";
	private static final String READER_ID = "reader_id";
	
	/**
	 * This method create and return a connection
	 * @return Connection
	 * @throws SQLException 
	 */
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/libriary", "root", "root");
	}
	
	/**
	 * This method create new instance of Book and return it
	 * @return Book
	 * @throws SQLException 
	 */
	private Book getBook(ResultSet resultSet) throws SQLException {
		return new Book(resultSet.getInt(ID), resultSet.getString(TITLE), resultSet.getString(AUTHOR), resultSet.getInt(READER_ID));
	}
	
	/**
	 * This method return all books from database
	 * @return List<Book>
	 */
	@Override
	public List<Book> findAll() {
		List<Book> resultList = new ArrayList<>();
		
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(QUERY_FIND_ALL);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				resultList.add(getBook(resultSet));
			}
			
			return resultList;
			
		} catch(SQLException | RuntimeException e) {
			LOGGER.error(e.getMessage());
		}
		
		return null;
	}
	
	/**
	 * This method looking for a book in database by given id
	 * @param id
	 * @return Book
	 */
	@Override
	public Book selectById(Integer id) {
		
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_BY_ID);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			resultSet.next();
			Book book = getBook(resultSet);
			
			return book;
			
		} catch (SQLException | RuntimeException e) {
			LOGGER.error(e.getMessage());
		}
		
		return null;
	}
	
	/**
	 * This method create a new instance of book in database
	 * @param book
	 * @return Integer
	 */
	@Override
	public Integer create(Book book) {
		
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(QUERY_CREATE, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, book.getTitle());
			statement.setString(2, book.getAuthor());
			statement.setInt(3, book.getReaderId());
			statement.execute();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			
			generatedKeys.next();
			
			return generatedKeys.getInt(1);
			
		} catch (SQLException | RuntimeException e) {
			LOGGER.error(e.getMessage());
		}
		
		return null;
	}
	
	/**
	 * This method update information of an existing book
	 * @param book
	 * @return Integer
	 */
	@Override
	public Integer update(Book book) {
		
		try (Connection connection = getConnection()){
			PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE);
			statement.setString(1, book.getTitle());
			statement.setString(2, book.getAuthor());
			statement.setInt(3, book.getReaderId());
			statement.setInt(4, book.getId());
			statement.execute();
			
			return book.getId();
			
		} catch (SQLException | RuntimeException e) {
			LOGGER.error(e.getMessage());
		}
		
		return null;
	}
	
	/**
	 * This method delete existing book in database by given id
	 * @param id
	 * @return Integer
	 */
	@Override
	public Integer delete(Integer id) {
		
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(QUERY_DELETE);
			statement.setInt(1, id);
			statement.execute();
			
			return id;
			
		} catch (SQLException | RuntimeException e) {
			LOGGER.error(e.getMessage());
		}
		
		return null;
	}
	
	
}
