package com.example.springboot.base;

/**
 * This is a basic class for reader object.
 * @author Dmytro Kohut
 * @version 1.1
 */
public class Reader {
	private Integer id;
	private String name;
	private String email;
	
	/**
	 * The default constructor for object creation, useful if necessary just create a object 
	 * without attributes
	 */
	public Reader() {
		// default constructor
	}
	
	/**
	 * This constructor useful if necessary to create a object with attributes
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
