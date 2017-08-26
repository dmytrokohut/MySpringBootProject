package com.example.springboot.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.example.springboot.Base.Reader;

/**
 * This class implement methods from ReaderDAO interface. Repository annotation more suitable to DAO than Component
 * @author Dmytro Kohut
 * @version 1.0
 */
@Repository("readerDAO")
public class ReaderDAOImpl implements ReaderDAO{
	
	final static Logger LOGGER = Logger.getLogger(ReaderDAOImpl.class);
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch(InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			LOGGER.error("ERROR!", e);
		}
	}
	
	/**
	 * This method create and return a connection for database access
	 * @return
	 * @throws SQLException 
	 */
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/libriary", "root", "root");
	}
	
	/**
	 * This method close a given connection
	 * @param connection
	 */
	private void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch(SQLException e) {
			LOGGER.error("Cannot close the connection", e);
		}
		
	}
	
	/**
	 * @see com.example.springboot.DAO#findAllReaders()
	 */
	@Override
	public Collection<Reader> findAllReaders(){
		Connection connection = null;
		List<Reader> resultList = new ArrayList<>();
		
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM `readers`;");
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Reader reader = new Reader(resultSet.getInt("id"), 
						resultSet.getString("name"), resultSet.getString("email"));
				resultList.add(reader);
			}
		} catch(SQLException e) {
			LOGGER.error("ERROR ", e);
		} finally {
			closeConnection(connection);
		}
		
		return resultList;
	}
	
	/**
	 * @see com.example.springboot.DAO#createReader(Reader reader)
	 */
	@Override
	public void createReader(Reader reader) {
		Connection connection = null;
		
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO `readers`(name, email) VALUES(?, ?);",	Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, reader.getName());
			statement.setString(2, reader.getEmail());
			statement.execute();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			
			if(generatedKeys.next()) {
				reader.setId(generatedKeys.getInt(1));
			}
		} catch(SQLException e) {
			LOGGER.error("ERROR ", e);
		} finally {
			closeConnection(connection);
		}
	}
	
	/**
	 * @see com.example.springboot.DAO#readById(int id)
	 */
	@Override
	public Reader readById(int id) {
		Connection connection = null;
		Reader reader = null;
		
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM `readers` WHERE id=?;");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				reader = new Reader(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("email"));
			}
		} catch(SQLException e) {
			LOGGER.error("ERROR ", e);
		} finally {
			closeConnection(connection);
		}
		
		return reader;
	}
	
	/**
	 * @see com.example.springboot.DAO#updateReader(Reader reader)
	 */
	@Override
	public void updateReader(Reader reader) {
		Connection connection = null;
		
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement("UPDATE `readers` SET name=?, email=? WHERE id=?;");
			statement.setString(1, reader.getName());
			statement.setString(2, reader.getEmail());
			statement.setInt(3, reader.getId());
			statement.execute();
		} catch(SQLException e) {
			LOGGER.error("ERROR ", e);
		} finally {
			closeConnection(connection);
		}
	}
	
	/**
	 * @see com.example.springboot.DAO#removeById(int id)
	 */
	@Override
	public void removeById(int id) {
		Connection connection = null;
		
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement("DELETE FROM `readers` WHERE id=?;");
			statement.setInt(1, id);
			statement.execute();
		} catch(SQLException e) {
			LOGGER.error("ERROR ", e);
		} finally {
			closeConnection(connection);
		}
	}
	
}
