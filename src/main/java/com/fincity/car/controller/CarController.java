package com.fincity.car.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fincity.car.exception.CarDetailsNotSaveException;
import com.fincity.car.exception.NoAnyCarException;
import com.fincity.car.model.Car;
import com.fincity.car.repository.CarRepository;

@RestController
@RequestMapping("/fincity/api/v1/")
public class CarController {

	@Autowired
	private CarRepository repository;

	@RequestMapping(value = "/cars", method = RequestMethod.POST)
	public Resource<Car> saveCar(@Valid @RequestBody Car car) throws CarDetailsNotSaveException, NoAnyCarException {

		int status = repository.save(car);

		if (status > 0) {

			Resource<Car> resource = new Resource<Car>(car);

			ControllerLinkBuilder linkToName = linkTo(methodOn(this.getClass()).getCars(car.getName()));
			ControllerLinkBuilder linkToManuName = linkTo(methodOn(this.getClass()).getCars(car.getManufactureName()));
			ControllerLinkBuilder linkToModel = linkTo(methodOn(this.getClass()).getCars(car.getModel()));
			ControllerLinkBuilder linkToColor = linkTo(methodOn(this.getClass()).getCars(car.getColor()));
			ControllerLinkBuilder linkToManuYears = linkTo(
					methodOn(this.getClass()).getCars(car.getManufacturingYear()));

			resource.add(linkToName.withRel("all-carsByName"));
			resource.add(linkToManuName.withRel("all-carsByManufactureName"));
			resource.add(linkToModel.withRel("all-carsByModel"));
			resource.add(linkToColor.withRel("all-carsByColors"));
			resource.add(linkToManuYears.withRel("all-carsByManufacturingYear"));

			return resource;
		}
		throw new CarDetailsNotSaveException("Car Details is not save with name " + car.getName());
	}

	@RequestMapping(value = "/cars/{search}", method = RequestMethod.GET)
	public List<Resource<Car>> getCars(@PathVariable String search) throws NoAnyCarException {

		List<Car> cars = repository.searchCars(search);
		List<Resource<Car>> carlistResource = new ArrayList<Resource<Car>>();

		if (!cars.isEmpty()) {
			for (Car car : cars) {

				Resource<Car> resource = new Resource<Car>(car);
				resource.add(
						linkTo(methodOn(CarController.class).getCarsById(car.getId())).withRel("_self"));
				carlistResource.add(resource);

			}

			return carlistResource;
		}
		throw new NoAnyCarException("No any cars is available currently");
	}

	@RequestMapping(value = "/cars", method = RequestMethod.PUT)
	public Resource<Car> updateCarDetails(@RequestBody Car carRequest) throws NoAnyCarException {
		int status = repository.update(carRequest);

		if (status > 0) {

			Resource<Car> resource = new Resource<Car>(carRequest);
			ControllerLinkBuilder linkTo = linkTo(
					methodOn(this.getClass()).getCarsById(carRequest.getId()));
			resource.add(linkTo.withRel("car-by-id"));

			return resource;
		} else {
			throw new NoAnyCarException("No any cars is available with car id ::" + carRequest.getId());
		}
	}

	@RequestMapping(value = "/cars/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public Resource<Map> deleteCarDetails(@PathVariable Long id) throws NoAnyCarException {
		int status = repository.deleteById(id);
		if (status > 0) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("Status", "Car details deleted with id ::" + id);

			Resource<Map> resource = new Resource<Map>(map);
			ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getCarsById(id));
			resource.add(linkTo.withRel("car-by-id"));

			return resource;
		} else {
			throw new NoAnyCarException("No any cars is available with car id ::" + id);
		}
	}
	
	@RequestMapping(value = "/cars/car/{id}", method = RequestMethod.GET)
	public List<Resource<Car>> getCarsById(@PathVariable Long id) throws NoAnyCarException {
		List<Car> cars = repository.searchCarsById(id);
		List<Resource<Car>> carlistResource = new ArrayList<Resource<Car>>();

		if (!cars.isEmpty()) {
			for (Car car : cars) {

				Resource<Car> resource = new Resource<Car>(car);
				resource.add(
						linkTo(methodOn(CarController.class).getCarsById(car.getId())).withRel("_self"));
				carlistResource.add(resource);

			}

			return carlistResource;
		}
		throw new NoAnyCarException("No any cars is available currently");
	}


}
