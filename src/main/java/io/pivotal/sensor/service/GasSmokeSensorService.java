package io.pivotal.sensor.service;

import io.pivotal.sensor.model.GasSmokeEvent;

public interface GasSmokeSensorService {

	GasSmokeEvent saveGasSmokeSensorEvent(GasSmokeEvent gasSmokeEvent);
	
	
}
