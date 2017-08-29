package com.example.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.dao.IBookDAOService;

/**
 * This is class that control work of application, process requests where URL start with '/reader'
 * @author Kohut Dmytro
 * @version 1.0
 */
@RestController
@RequestMapping("/reader")
public class Controller {
	
	@Autowired
	private IBookDAOService readerDAO;

}
