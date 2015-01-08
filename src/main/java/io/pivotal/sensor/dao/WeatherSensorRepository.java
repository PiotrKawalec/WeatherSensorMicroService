package io.pivotal.sensor.dao;

import io.pivotal.sensor.model.Weather;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface WeatherSensorRepository extends MongoRepository<Weather, String> {

    public List<Weather> findBySensorID(String sensorID);
	public Weather findBySensorIDAndEventTimeBetween(String sensorID, Date startTime, Date endTime);
	public Weather findBySensorIDAndEventTimeIsAfter(String sensorID, Date time);
	public Weather findBySensorIDAndEventTimeIsBefore(String sensorID, Date time);
	
}
