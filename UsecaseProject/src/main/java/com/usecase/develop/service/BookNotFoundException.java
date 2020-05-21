package com.usecase.develop.service;

public class BookNotFoundException extends Exception {

	/**
	 * Implementing Custom Exception
	 * 
	 * @param message
	 */
    public BookNotFoundException(String message) {
        super(message);
    }

}