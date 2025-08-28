package com.inventory.exception;

public class NoInventoryFoundException extends Exception{
	public NoInventoryFoundException (String msg) {
		super(msg);
	}
}
