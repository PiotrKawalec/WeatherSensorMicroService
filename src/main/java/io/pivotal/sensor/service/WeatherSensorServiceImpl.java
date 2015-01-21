package io.pivotal.sensor.service;

import io.pivotal.sensor.dao.WeatherSensorRepository;
import io.pivotal.sensor.model.Weather;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherSensorServiceImpl implements WeatherSensorService {

	@Autowired
	private WeatherSensorRepository repository;
	
	@Override
	public Weather saveWeatherSensorReading(Weather weather) {
		return repository.save(weather);
	}

	@Override
	public List<Weather> findBySensorID(String sensorID) {
		return repository.findBySensorID(sensorID);
	}

	@Override
	public Weather findWeatherSensorReading(String sensorID, Date startTime, Date endTime) {
		return repository.findBySensorIDAndEventTimeBetween(sensorID, startTime, endTime);
	}

	@Override
	public Weather findWeatherSensorReadingAfterDate(String sensorID, Date time) {
		return repository.findBySensorIDAndEventTimeIsAfter(sensorID, time);
	}

	@Override
	public Weather findWeatherSensorReadingBeforeDate(String sensorID, Date time) {
		return repository.findBySensorIDAndEventTimeIsBefore(sensorID, time);
	}

}
