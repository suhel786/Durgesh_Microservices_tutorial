package com.example.demo.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException() {
		super("Resource Not Found !!!");
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}

}
