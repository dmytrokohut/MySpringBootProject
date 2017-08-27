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

import com.example.springboot.base.Book;
import com.mysql.jdbc.Statement;

/**
 * This class implement methods from BookDAO interface
 * @author Dmytro Kohut
 * @version 1.1
 */
@Repository("bookDAO")
public class BookDAOImpl implements DAOInterface<Book> {
	
	final static Logger LOGGER = Logger.getLogger(BookDAOImpl.class);
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch(InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			LOGGER.error("ERROR ", e);
		}
	}
	
	/**
	 * This method create and return a connection
	 * @return
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/libriary", "root", "root");
	}
	
	/**
	 * This method close an given connection
	 * @param connection
	 */
	private void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch(SQLException e) {
			LOGGER.error("ERROR ", e);
		}
	}
	
	/**
	 * @see com.example.springboot.dao.DAOInterface#findAllBooks()
	 */
	@Override
	public Collection<Book> findAll() {
		Connection connection = null;
		List<Book> resultList = new ArrayList<>();
		
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM `books`;");
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Book book = new Book(resultSet.getInt("id"), resultSet.getString("title"),
						resultSet.getString("author"), resultSet.getInt("reader_id"));
				resultList.add(book);
			}
		} catch(SQLException | NullPointerException e) {
			LOGGER.error("ERROR ", e);
		} finally {
			closeConnection(connection);
		}
		
		return resultList;
	}
	
	/**
	 * @see com.example.springboot.dao.DAOInterface#createBook(T object)
	 */
	@Override
	public void create(Book book) {
		Connection connection = null;
		
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO `books`(title, author, reader_id) VALUES(?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, book.getTitle());
			statement.setString(2, book.getAuthor());
			statement.setInt(3, book.getReaderId());
			statement.execute();			
			ResultSet generatedKeys = statement.getGeneratedKeys();
			
			if(generatedKeys.next()) {
				book.setId(generatedKeys.getInt(1));
			}
		} catch(SQLException e) {
			LOGGER.error("ERROR ", e);
		} finally {
			closeConnection(connection);
		}
	}
	
	/**
	 * @see com.example.springboot.dao.DAOInterface#selectById(int id)
	 */
	@Override
	public Book selectById(Integer id) {
		Connection connection = null;
		Book book = new Book();
		
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM `books` WHERE id=?;");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				book.setId(resultSet.getInt("id"));
				book.setTitle(resultSet.getString("title"));
				book.setAuthor(resultSet.getString("author"));
				book.setReaderId(resultSet.getInt("reader_id"));
			}
		} catch(SQLException e) {
			LOGGER.error("ERROR ", e);
		} finally {
			closeConnection(connection);
		}
		
		return book;
	}
	
	/**
	 * @see com.example.springboot.dao.DAOInterface#updateBook(T object)
	 */
	@Override
	public void update(Book book) {
		Connection connection = null;
		
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"UPDATE `books` SET title=?, author=?, reader_id=? WHERE id=?;");
			statement.setString(1, book.getTitle());
			statement.setString(2, book.getAuthor());
			statement.setInt(3, book.getReaderId());
			statement.setInt(4, book.getId());
			statement.execute();
		} catch(SQLException e) {
			LOGGER.error("ERROR ", e);
		} finally {
			closeConnection(connection);
		}
	}
	
	/**
	 * @see com.example.springboot.dao.DAOInterface#deleteBook(int id)
	 */
	@Override
	public void delete(Integer id) {
		Connection connection = null;
		
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement("DELETE FROM `books` WHERE id=?;");
			statement.setInt(1, id);
			statement.execute();
		} catch(SQLException e) {
			LOGGER.error("ERROR ", e);
		} finally {
			closeConnection(connection);
		}
	}
}
