package com.example.springboot.common;

/**
 * This is a basic class for reader object.
 * @author Kohut Dmytro
 * @version 1.0
 */
public class Reader {

	private Integer id;
	private String name;
	private String email;
	
	/**
	 * The default constructor for object creation, useful if necessary just create an object 
	 * without filling of attributes
	 */
	public Reader() {
		// default constructor
	}
	
	/**
	 * This constructor useful if necessary to create an object with filled attributes
	 * @param id
	 * @param name
	 * @param email
	 */
	public Reader(Integer id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
}
