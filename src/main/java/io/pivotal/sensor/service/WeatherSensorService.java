package io.pivotal.sensor.service;

import io.pivotal.sensor.model.Weather;

import java.util.Date;
import java.util.List;

public interface WeatherSensorService {

	Weather saveWeatherSensorReading(Weather weather);
	List<Weather> findBySensorID(String sensorID);
	Weather findWeatherSensorReading(String sensorID, Date startTime, Date endTime);
	Weather findWeatherSensorReadingAfterDate(String sensorID, Date time);
	Weather findWeatherSensorReadingBeforeDate(String sensorID, Date time);
	
}
