package com.mobiquity.exception;

public enum APIExceptionType {

	INVALID_INPUT("Invalid Input!"), 
	MAX_PACKAGE_WEIGHT("package weight exceeds the max limit"), 
	INVALID_ITEMS_NUMBER("items are empty or exceeds the max limit"), 
	MAX_ITEM_WEIGHT("item weight exceeds the max limit"), 
	MAX_ITEM_COST("item cost exceeds the max limit");

	private String message;

	private APIExceptionType(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
