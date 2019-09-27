package com.tradair.orders.exception;

public class ModifyOrderException extends OrderExistException {

	private static final long serialVersionUID = 8163150477034397576L;

	public ModifyOrderException(String message) {
		super(message);
	}
}
