package com.test.sku.err;

public class PasswordException extends Exception 
{
	public PasswordException() {}

	public PasswordException(String message) {
		super(message);
		System.err.println(message);
	}
}
