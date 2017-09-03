package com.example.springboot.dao;

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

import com.example.springboot.common.Reader;

/**
 * This class implement methods from IReaderDAOService interface
 * @author Kohut Dmytro
 * @version 1.0
 */
@Repository("iReaderService")
public class ReaderDAOService implements IReaderDAOService {	

	private static final String SQLfindAll = "SELECT id, name, email FROM `readers`;";
	private static final String SQLSelectById = "SELECT id, name, email FROM `readers` WHERE id=?";
	private static final String SQLCreate = "INSERT INTO `readers`(name, email) VALUES(?, ?);";
	private static final String SQLUpdate = "UPDATE `readers` SET name=?, email=? WHERE id=?;";
	private static final String SQLDelete = "DELETE FROM `readers` WHERE id=?;";
	
	/**
	 * This method create and return a connection
	 * @return Connection
	 * @throws SQLException 
	 */
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/libriary", "root", "root");
	}
	
	
	/**
	 * @see com.example.springboot.dao.IReaderDAOService#findAll()
	 */
	@Override
	public Collection<Reader> findAll() {
		List<Reader> resultList = new ArrayList<>();
		
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SQLfindAll);
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
	 * @see com.example.springboot.dao.IReaderDAOService#selectById(Integer id)
	 */
	@Override
	public Reader selectById(Integer id) {
		Reader reader = new Reader();
		
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SQLSelectById);
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
	 * @see com.example.springboot.dao.IReaderDAOService#create(Reader reader)
	 */
	@Override
	public void create(Reader reader) {
		
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SQLCreate, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, reader.getName());
			statement.setString(2, reader.getEmail());
			statement.execute();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			
			if(generatedKeys.next()) {
				reader.setId(generatedKeys.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see com.example.springboot.dao.IReaderDAOService#update(Reader reader)
	 */
	@Override
	public void update(Reader reader) {
		
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement(SQLUpdate);
			statement.setString(1, reader.getName());
			statement.setString(2, reader.getEmail());
			statement.setInt(3, reader.getId());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see com.example.springboot.dao.IReaderDAOService#delete(Integer id)
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
