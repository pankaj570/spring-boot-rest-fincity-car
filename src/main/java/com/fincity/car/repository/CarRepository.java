package com.fincity.car.repository;

import java.util.List;

import com.fincity.car.model.Car;

public interface CarRepository {

	public int save(Car car);

	public int update(Car car);

	public int deleteById(Long id);
	
	public List<Car> searchCars(String search);
	
	public List<Car> searchCarsById(Long id);
}
