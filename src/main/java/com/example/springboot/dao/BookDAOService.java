package com.example.springboot.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.springboot.common.Book;
import com.mysql.jdbc.Statement;

/**
 * This class implement methods from IBookDAOService interface
 * @author Kohut Dmytro
 * @version 1.0
 */
@Repository("iBookService")
public class BookDAOService implements IBookDAOService {

	private static final String SQLFindAll = "SELECT id, title, author, reader_id FROM `books`;";
	private static final String SQLSelectById = "SELECT id, title, author, reader_id FROM `books` WHERE id=?;";
	private static final String SQLCreate = "INSERT INTO `books`(title, author, reader_id) VALUES(?, ?, ?);";
	private static final String SQLUpdate = "UPDATE `books` SET title=?, author=?, reader_id=? WHERE id=?;";
	private static final String SQLDelete = "DELETE FROM `books` WHERE id=?;";
	
	/**
	 * This method create and return a connection
	 * @return Connection
	 * @throws SQLException 
	 */
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/libriary", "root", "root");
	}
	
	/**
	 * @see com.example.springboot.dao.IBookDAOService#findAll()
	 */
	@Override
	public Collection<Book> findAll() {
		List<Book> resultList = new ArrayList<>();
		
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SQLFindAll);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Book book = new Book(resultSet.getInt("id"), resultSet.getString("title"),
						resultSet.getString("author"), resultSet.getInt("reader_id"));
				resultList.add(book);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return resultList;
	}
	
	/**
	 * @see com.example.springboot.dao.IBookDAOService#selectById(Integer id)
	 */
	@Override
	public Book selectById(Integer id) {
		Book book = new Book();
		
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SQLSelectById);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				book.setId(resultSet.getInt("id"));
				book.setTitle(resultSet.getString("title"));
				book.setAuthor(resultSet.getString("author"));
				book.setReaderId(resultSet.getInt("reader_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return book;
	}
	
	/**
	 * @see com.example.springboot.dao.IBookDAOService#create(Book book)
	 */
	@Override
	public void create(Book book) {
		
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SQLCreate, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, book.getTitle());
			statement.setString(2, book.getAuthor());
			statement.setInt(3, book.getReaderId());
			statement.execute();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			
			if(generatedKeys.next()) {
				book.setId(generatedKeys.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @see com.example.springboot.dao.IBookDAOService#update(Book book)
	 */
	@Override
	public void update(Book book) {
		
		try (Connection connection = getConnection()){
			PreparedStatement statement = connection.prepareStatement(SQLUpdate);
			statement.setString(1, book.getTitle());
			statement.setString(2, book.getAuthor());
			statement.setInt(3, book.getReaderId());
			statement.setInt(4, book.getId());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @see com.example.springboot.dao.IBookDAOService#delete(Integer id)
	 */
	@Override
	public void delete(Integer id) {
		
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SQLDelete);
			statement.setInt(1, id);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
