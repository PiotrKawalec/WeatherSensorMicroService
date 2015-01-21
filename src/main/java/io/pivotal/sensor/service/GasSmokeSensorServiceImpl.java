package io.pivotal.sensor.service;

import io.pivotal.sensor.dao.GasSmokeSensorRepository;
import io.pivotal.sensor.model.GasSmokeEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GasSmokeSensorServiceImpl implements GasSmokeSensorService {

	@Autowired
	private GasSmokeSensorRepository repository;

	@Override
	public GasSmokeEvent saveGasSmokeSensorEvent(GasSmokeEvent gasSmokeEvent) {
		return repository.save(gasSmokeEvent);
	}
	
	

}
