package com.example.springboot.common;

/**
 * This is a basic class for book object.
 * @author Kohut Dmytro
 * @version 1.0
 */
public class Book {
	
	private Integer id;
	private String title;
	private String author;
	private Integer readerId;
	
	/**
	 * The default constructor for object creation, useful if necessary just create an object 
	 * without filling of attributes
	 */
	public Book() {
		// default constructor
	}
	
	/**
	 * This constructor useful if necessary to create an object and sets values of attributes
	 * @param id
	 * @param title
	 * @param author
	 * @param readerId
	 */
	public Book(Integer id, String title, String author, Integer readerId) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.readerId = readerId;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setReaderId(Integer readerId) {
		this.readerId = readerId;
	}
	
	public Integer getReaderId() {
		return readerId;
	}
	
}
