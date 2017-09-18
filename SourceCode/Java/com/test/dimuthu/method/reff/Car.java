package com.test.dimuthu.method.reff;

import java.util.function.Supplier;

public class Car {

	private String rejoNumber;
	private long milage;
	
	public static Car create(final Supplier< Car > supplier ) {
		return supplier.get();
	}              

	public void startCar() {   
		System.out.println("Starting " + this.rejoNumber);
	}
	
	public static void wipersOn(final Car car) {
		System.out.println("Wiper on:" + car.rejoNumber);
	}

	public void turnHeadLights(final Car car) {
		System.out.println("Headlights On:" + car.rejoNumber);
	}

	public String getRejoNumber() {
		return rejoNumber;
	}

	public void setRejoNumber(String rejoNuumber) {
		this.rejoNumber = rejoNuumber;
	}
	
	public long getMilage() {
		return milage;
	}

	public void setMilage(long milage) {
		this.milage = milage;
	}
	
	
}
