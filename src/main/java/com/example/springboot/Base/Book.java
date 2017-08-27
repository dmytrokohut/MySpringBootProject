package com.example.springboot.Base;

/**
 * This is a basic class for book object.
 * @author Dmytro Kohut
 * @version 1.0
 */
public class Book {
	public int id;
	public String title;
	public String author;
	public int readerId;
	
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
	public Book(int id, String title, String author, int readerId) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.readerId = readerId;
	}
	
	/**
	 * This setter sets a the value of 'id' attribute
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * This getter return a value of 'id' attribute
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * The setter which sets a value of 'title' attribute
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * The getter that return a value of 'title' attribute
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * This setter sets of 'author' attribute
	 * @param author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/**
	 * This getter return a value of 'author'
	 * @return
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * The setter that sets a value of 'readerId' attribute
	 * @param readerId
	 */
	public void setReaderId(int readerId) {
		this.readerId = readerId;
	}
	
	/**
	 * The getter which sets a value of 'readerId' attribute
	 * @return
	 */
	public int getReaderId() {
		return readerId;
	}
	
	/**
	 * The method which represent a object in string view
	 * @return
	 */
	@Override
	public String toString() {
		return "ID: " + id + ", title: " + title + ", author " + author + ", reader id: " + readerId;
	}
}
