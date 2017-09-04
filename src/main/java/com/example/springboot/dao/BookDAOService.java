package com.example.springboot.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
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
@Repository("iBookService")
public class BookDAOService implements IBookDAOService {

	private final Logger LOG = Logger.getLogger(BookDAOService.class);
	
	private static final String SQL_FIND_ALL = "SELECT id, title, author, reader_id FROM `books`;";
	private static final String SQL_SELECT_BY_ID = "SELECT id, title, author, reader_id FROM `books` WHERE id=?;";
	private static final String SQL_CREATE = "INSERT INTO `books`(title, author, reader_id) VALUES(?, ?, ?);";
	private static final String SQL_UPDATE = "UPDATE `books` SET title=?, author=?, reader_id=? WHERE id=?;";
	private static final String SQL_DELETE = "DELETE FROM `books` WHERE id=?;";
	
	/**
	 * This method create and return a connection
	 * @return Connection
	 * @throws SQLException 
	 */
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/libriary", "root", "root");
	}
	
	/**
	 * This method return all books from database
	 * @return Collection<Book>
	 */
	@Override
	public Collection<Book> findAll() {
		List<Book> resultList = new ArrayList<>();
		
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Book book = new Book(resultSet.getInt("id"), resultSet.getString("title"),
						resultSet.getString("author"), resultSet.getInt("reader_id"));
				resultList.add(book);
			}
		} catch(SQLException e) {
			LOG.error(e.getMessage());
		}
		
		return resultList;
	}
	
	/**
	 * This method looking for a book in database by given id
	 * @param id
	 * @return Book
	 */
	@Override
	public Book selectById(Integer id) {
		Book book = new Book();
		
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				book.setId(resultSet.getInt("id"));
				book.setTitle(resultSet.getString("title"));
				book.setAuthor(resultSet.getString("author"));
				book.setReaderId(resultSet.getInt("reader_id"));
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage());
		}
		
		return book;
	}
	
	/**
	 * This method create a new instance of book in database
	 * @param book
	 * @return Integer
	 */
	@Override
	public Integer create(Book book) {
		
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, book.getTitle());
			statement.setString(2, book.getAuthor());
			statement.setInt(3, book.getReaderId());
			statement.execute();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			
			if(generatedKeys.next()) {
				book.setId(generatedKeys.getInt(1));
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage());
		}
		
		return book.getId();
	}
	
	/**
	 * This method update information of an existing book
	 * @param book
	 * @return Integer
	 */
	@Override
	public Integer update(Book book) {
		
		try (Connection connection = getConnection()){
			PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);
			statement.setString(1, book.getTitle());
			statement.setString(2, book.getAuthor());
			statement.setInt(3, book.getReaderId());
			statement.setInt(4, book.getId());
			statement.execute();
		} catch (SQLException e) {
			LOG.error(e.getMessage());
		}
		
		return book.getId();
	}
	
	/**
	 * This method delete existing book in database by given id
	 * @param id
	 * @return Integer
	 */
	@Override
	public Integer delete(Integer id) {
		
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
			statement.setInt(1, id);
			statement.execute();
		} catch (SQLException e) {
			LOG.error(e.getMessage());
		}
		
		return id;
	}
	
	
}
