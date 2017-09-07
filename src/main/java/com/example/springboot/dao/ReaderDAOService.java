package com.example.springboot.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.example.springboot.common.Reader;

/**
 * This class implement methods from IReaderDAOService interface
 * @author Kohut Dmytro
 * @version 1.0
 */
@Repository
public class ReaderDAOService implements IReaderDAOService {
	
	@Value("${jdbc.url}") private String URL;
	@Value("${jdbc.username}") private String USERNAME;
	@Value("${jdbc.password}") private String PASSWORD;
	
	private static final Logger LOGGER = Logger.getLogger(ReaderDAOService.class);

	private static final String QUERY_FIND_ALL = "SELECT id, name, email FROM `readers`;";
	private static final String QUERY_SELECT_BY_ID = "SELECT id, name, email FROM `readers` WHERE id=?";
	private static final String QUERY_INSERT = "INSERT INTO `readers`(name, email) VALUES(?, ?);";
	private static final String QUERY_UPDATE = "UPDATE `readers` SET name=?, email=? WHERE id=?;";
	private static final String QUERY_DELETE = "DELETE FROM `readers` WHERE id=?;";
	
	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String EMAIL = "email";
	
	/**
	 * This method create and return a connection
	 * @return Connection
	 * @throws SQLException 
	 */
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
	
	/**
	 * This method create instance of Reader and return it
	 * @return Reader
	 * @throws SQLException 
	 */
	private Reader getReader(ResultSet resultSet) throws SQLException {
		return new Reader(resultSet.getInt(ID), resultSet.getString(NAME), resultSet.getString(EMAIL));
	}
	
	
	/**
	 * This method return all readers from database
	 * @return List<Reader>
	 */
	@Override
	public List<Reader> findAll() {	
		
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(QUERY_FIND_ALL);
			ResultSet resultSet = statement.executeQuery();
			
			List<Reader> resultList = new ArrayList<>();
			
			while(resultSet.next()) {
				resultList.add(getReader(resultSet));
			}
			
			return resultList;
			
		} catch (SQLException | RuntimeException e) {
			LOGGER.error(e.getMessage());
		}
		
		return null;
	}

	/**
	 * This method looking for a reader in database by given id
	 * @param id
	 * @return Reader
	 */
	@Override
	public Reader selectById(Integer id) {
		
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_BY_ID);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			resultSet.next();
			
			Reader reader = getReader(resultSet);
			
			return reader;
			
		} catch (SQLException | RuntimeException e) {
			LOGGER.error(e.getMessage());
		}
		
		return null;
	}

	/**
	 * This method create a new instance of reader in database
	 * @param reader
	 * @return Integer
	 */
	@Override
	public Integer create(Reader reader) {
		
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, reader.getName());
			statement.setString(2, reader.getEmail());
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
	 * This method update information of an existing reader
	 * @param reader 
	 * @return Integer
	 */
	@Override
	public Integer update(Reader reader) {
		
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE);
			statement.setString(1, reader.getName());
			statement.setString(2, reader.getEmail());
			statement.setInt(3, reader.getId());
			statement.execute();
			
			return reader.getId();
					
		} catch (SQLException | RuntimeException e) {
			LOGGER.error(e.getMessage());
		}
		
		return null;
	}

	/**
	 * This method delete existing reader in database by given id
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
