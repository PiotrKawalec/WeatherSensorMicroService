package io.pivotal.sensor.dao;

import io.pivotal.sensor.model.GasSmokeEvent;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface GasSmokeSensorRepository extends MongoRepository<GasSmokeEvent, String> {

	
}
