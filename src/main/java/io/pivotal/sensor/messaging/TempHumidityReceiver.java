package io.pivotal.sensor.messaging;

import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import io.pivotal.sensor.model.Weather;
import io.pivotal.sensor.service.WeatherSensorService;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@EnableCircuitBreaker
@EnableHystrixDashboard
public class TempHumidityReceiver {

	@Autowired
	private WeatherSensorService weatherSensorService;
	
	@HystrixCommand(fallbackMethod = "weatherDefaultFallback")
	public void receiveMessage(byte[] message) {
		//TODO need to work out message here!
		//SensorId,temp,humidity
		
		String msg = new String(message);
		String[] readings = msg.split(",");
		if (readings.length != 3) {
			//log error
			System.out.println("Message did not have the corect number of values!!! [" + msg + "]");
		} else {
			try {
				System.out.println("Received [" + msg + "]");
				Weather w = new Weather();
				w.setEventTime(new Date());
				w.setSensorID(readings[0]);
				w.setTemperature(Double.valueOf(readings[1]));
				w.setHumidity(Double.valueOf(readings[2]));
				weatherSensorService.saveWeatherSensorReading(w);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.out.println("Encountered an error with either temp/humidity not being a number!! [" + msg + "]");
			}
		}
	}
	
	public String weatherDefaultFallback(byte[] message) {
        return "Weather fallback method";
	}
}
