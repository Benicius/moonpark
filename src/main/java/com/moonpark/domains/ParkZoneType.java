package com.moonpark.domains;

public enum ParkZoneType {

	M1("M1"),
	M2("M2"),
	M3("M3");

	private String description;

	private ParkZoneType(String desc){
		this.description = desc;
	}

	public String getDescription() {
		return this.description;
	}
}
