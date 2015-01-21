package io.pivotal.sensor.messaging;

import io.pivotal.sensor.model.GasSmokeEvent;
import io.pivotal.sensor.service.GasSmokeSensorService;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

public class GasSmokeReceiver {

	@Autowired
	private GasSmokeSensorService gasSmokeSensorService;
	
	public void receiveMessage(byte[] message) {
		//SensorId,value,location
		
		String msg = new String(message);
		String[] readings = msg.split(",");
		if (readings.length != 3) {
			//log error
			System.out.println("Message did not have the corect number of values!!! [" + msg + "]");
		} else {
//			try {
				System.out.println("Received [" + msg + "]");
				GasSmokeEvent gse = new GasSmokeEvent();
				gse.setEventTime(new Date());
				gse.setGasSensorId(readings[0]);
				gse.setValue(readings[1]);
				gse.setLocation(readings[2]);
				gasSmokeSensorService.saveGasSmokeSensorEvent(gse);
//			} catch (NumberFormatException e) {
//				e.printStackTrace();
//				System.out.println("Encountered an error with either temp/humidity not being a number!! [" + msg + "]");
//			}
		}
	}
	
}
