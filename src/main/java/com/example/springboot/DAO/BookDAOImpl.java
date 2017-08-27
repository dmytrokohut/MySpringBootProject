package com.example.springboot.DAO;

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

import com.example.springboot.Base.Book;
import com.mysql.jdbc.Statement;

/**
 * This class implement methods from BookDAO interface. Repository annotation more suitable to DAO than Component
 * @author Dmytro Kohut
 * @version 1.0
 */
@Repository("bookDAO")
public class BookDAOImpl implements BookDAO {
	
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
	 * @see com.example.springboot.DAO.BookDAO#findAllBooks()
	 */
	@Override
	public Collection<Book> findAllBooks() {
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
	 * @see com.example.springboot.DAO.BookDAO#createBook(Book book)
	 */
	@Override
	public void createBook(Book book) {
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
	 * @see com.example.springboot.DAO.BookDAO#selectById(int id)
	 */
	@Override
	public Book selectById(int id) {
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
	 * @see com.example.springboot.DAO.BookDAO#updateBook(Book book)
	 */
	@Override
	public void updateBook(Book book) {
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
	 * @see com.example.springboot.DAO.BookDAO#deleteBook(int id)
	 */
	@Override
	public void deleteBook(int id) {
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
