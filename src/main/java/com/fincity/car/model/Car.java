package com.fincity.car.model;

public class Car{

	private Long Id;
	private String name;
	private String model;
	private String manufactureName;
	private String manufacturingYear;
	private String color;

	public Car() {
	}

	public Car(Long id, String name, String model, String manufactureName, String manufacturingYear, String color) {
		super();
		Id = id;
		this.name = name;
		this.model = model;
		this.manufactureName = manufactureName;
		this.manufacturingYear = manufacturingYear;
		this.color = color;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getManufactureName() {
		return manufactureName;
	}

	public void setManufactureName(String manufactureName) {
		this.manufactureName = manufactureName;
	}

	public String getManufacturingYear() {
		return manufacturingYear;
	}

	public void setManufacturingYear(String manufacturingYear) {
		this.manufacturingYear = manufacturingYear;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
