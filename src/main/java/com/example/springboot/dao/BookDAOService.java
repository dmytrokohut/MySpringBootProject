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
@Repository("iBookDAOService")
public class BookDAOService implements IBookDAOService {

	private static final String HOST = "jdbc:mysql://localhost:3306/libriary";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	private static String SQLQuery = "";
	
	/**
	 * @see com.example.springboot.dao.IBookDAOService#findAll()
	 */
	@Override
	public Collection<Book> findAll() {
		List<Book> resultList = new ArrayList<>();
		SQLQuery = "SELECT id, title, author, reader_id FROM `books`;";
		
		try (Connection connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(SQLQuery)) {
			
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
		SQLQuery = "SELECT id, title, author, reader_id FROM `books` WHERE id=?;";
		
		try (Connection connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(SQLQuery)) {
			
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
		SQLQuery = "INSERT INTO `books`(title, author, reader_id) VALUES(?, ?, ?);";
		
		try (Connection connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(SQLQuery, Statement.RETURN_GENERATED_KEYS)) {
			
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
		SQLQuery = "UPDATE `books` SET title=?, author=?, reader_id=? WHERE id=?;";
		
		try (Connection connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(SQLQuery)){
			
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
		SQLQuery = "DELETE FROM `books` WHERE id=?;";
		
		try (Connection connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(SQLQuery)) {
			
			statement.setInt(1, id);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
