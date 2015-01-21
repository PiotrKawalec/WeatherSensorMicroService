package io.pivotal.sensor.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class GasSmokeEvent {

	@Id
	private Long id;
	private String gasSensorId;
	private String value;
	private String location;
	private Date eventTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGasSensorId() {
		return gasSensorId;
	}
	public void setGasSensorId(String gasSensorId) {
		this.gasSensorId = gasSensorId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getEventTime() {
		return eventTime;
	}
	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
