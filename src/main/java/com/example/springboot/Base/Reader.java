package com.example.springboot.Base;

/**
 * This is a basic class for reader object.
 * @author Dmytro Kohut
 * @version 1.0
 */
public class Reader {
	private int id;
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
	public Reader(int id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	/**
	 * This setter-method sets the value of 'id' attribute
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * The getter-method which return a value of 'id' attribute
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * This is setter-method that sets a value of 'name' attribute 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * This getter-method return a value of 'name' attribute
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * This setter-method sets a value of 'email' attribute
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * This getter return a value of 'email' attribute
	 * @return
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * This method return a string representation of object
	 * @return
	 */
	@Override
	public String toString() {
		return "ID: " + id + ", name: " + name + ", email" + email;
	}
}
