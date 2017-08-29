package com.example.springboot.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.springboot.dao.IBookDAOService;

/**
 * This class implement methods from IBookDAOService interface
 * @author Kohut Dmytro
 * @version 1.0
 */
@Repository("iBookDAOService")
public class BookDAOService implements IBookDAOService {	

	private static final String URL = "jdbc:mysql://localhost:3306/libriary";
	private static final String USERNAME= "root";
	private static final String PASSWORD = "root";
	private static String SQLQuery = "";
	
	/**
	 * @see com.example.springboot.dao.IBookDAOService#findAll()
	 */
	@Override
	public Collection<Reader> findAll() {
		List<Reader> resultList = new ArrayList<>();
		SQLQuery = "SELECT id, name, email FROM `readers`;";
		
		try ( Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(SQLQuery) ) {
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Reader reader = new Reader(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("email"));
				resultList.add(reader);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultList;
	}

	/**
	 * @see com.example.springboot.dao.IBookDAOService#selectById(Integer id)
	 */
	@Override
	public Reader selectById(Integer id) {
		Reader reader = new Reader();
		SQLQuery = "SELECT id, name, email FROM `readers` WHERE id=?;";
		
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(SQLQuery) ) {
			
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				reader.setId(resultSet.getInt("id"));
				reader.setName(resultSet.getString("name"));
				reader.setEmail(resultSet.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reader;
	}

	/**
	 * @see com.example.springboot.dao.IBookDAOService#create(Reader reader)
	 */
	@Override
	public void create(Reader reader) {
		SQLQuery = "INSERT INTO `readers`(name, email) VALUES(?, ?);";
		
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(SQLQuery, Statement.RETURN_GENERATED_KEYS)) {
			
			statement.setString(1, reader.getName());
			statement.setString(2, reader.getEmail());
			ResultSet generatedKeys = statement.executeQuery();
			
			if(generatedKeys.next()) {
				reader.setId(generatedKeys.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see com.example.springboot.dao.IBookDAOService#update(Reader reader)
	 */
	@Override
	public void update(Reader reader) {
		SQLQuery = "UPDATE `readers` SET name=?, email=? WHERE id=?;";
		
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(SQLQuery)) {
			
			statement.setString(1, reader.getName());
			statement.setString(2, reader.getEmail());
			statement.setInt(3, reader.getId());
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
		SQLQuery = "DELETE FROM `readers` WHERE id=?;";
		
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(SQLQuery)) {
			
			statement.setInt(1, id);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
