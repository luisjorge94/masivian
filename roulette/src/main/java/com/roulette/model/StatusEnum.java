package com.roulette.model;

public enum StatusEnum {

	OPEN("OPEN"),
	CLOSE("CLOSE");
	
	private final String status;

	StatusEnum(final String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.status;
	}
	
}
