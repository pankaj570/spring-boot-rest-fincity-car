package com.fincity.car.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fincity.car.model.Car;

@Repository
public class CarRepositoryImpl implements CarRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public int save(Car car) {
		String sql = "insert into cars (name, model, manufactureName, manufacturingYear, color) values(:name,:model,:manufactureName,:manufacturingYear,:color)";
		Map<String, String> params = new HashMap<>();
		params.put("name", car.getName());
		params.put("model", car.getModel());
		params.put("manufactureName", car.getManufactureName());
		params.put("manufacturingYear", car.getManufacturingYear());
		params.put("color", car.getColor());
		return namedParameterJdbcTemplate.update(sql, params);
	}

	@Override
	public List<Car> searchCars(String search) {

		String sql = "select * from cars where name = :search or model =:search or manufactureName = :search or manufacturingYear = :search or color = :search";
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("search", search);

		return namedParameterJdbcTemplate.query(sql, mapSqlParameterSource,
				(rs, rowNum) -> new Car(rs.getLong("id"), rs.getString("name"), rs.getString("model"),
						rs.getString("manufactureName"), rs.getString("manufacturingYear"), rs.getString("color")));
	}

	@Override
	public int update(Car car) {

			String sql = "update cars set name=:name, model=:model, manufactureName=:manufactureName, manufacturingYear=:manufacturingYear, color=:color where id=:id";

			Map<String, Object> params = new HashMap<>();
			params.put("name", car.getName());
			params.put("model", car.getModel());
			params.put("manufactureName", car.getManufactureName());
			params.put("manufacturingYear", car.getManufacturingYear());
			params.put("color", car.getColor());
			params.put("id", car.getId());

			return namedParameterJdbcTemplate.update(sql, params);
	}

	@Override
	public int deleteById(Long id) {

		String sql = "delete cars where id = :id";

		Map<String, Long> params = new HashMap<>();
		params.put("id", id);

		return namedParameterJdbcTemplate.update(sql, params);
	}

	@Override
	public List<Car> searchCarsById(Long id) {
		
		String sql = "select * from cars where id = :id ";
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("id", id);
		
		return namedParameterJdbcTemplate.query(sql, mapSqlParameterSource,
				(rs, rowNum) -> new Car(rs.getLong("id"), rs.getString("name"), rs.getString("model"),
						rs.getString("manufactureName"), rs.getString("manufacturingYear"), rs.getString("color")));
	}

}
