package com.example.springboot.base;

/**
 * This is a basic class for book object.
 * @author Dmytro Kohut
 * @version 1.1
 */
public class Book {
	public Integer id;
	public String title;
	public String author;
	public Integer readerId;
	
	/**
	 * The default constructor for object creation, useful if necessary just create a object 
	 * without attributes
	 */
	public Book() {
		// default constructor
	}
	
	/**
	 * This constructor useful if necessary to create a object with attributes
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
