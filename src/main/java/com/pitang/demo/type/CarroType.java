package com.pitang.demo.type;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CarroType {
	
	private int year;
	private String licensePlate;
	private String model;
	private String color;

	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}	
}