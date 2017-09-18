package com.test.dimuthu.method.reff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MethodRefferenceMain {

	public static void main(String[] args) {

		Car car100 = Car.create(Car::new);
		car100.setRejoNumber("100");
		
		Car car200 = Car.create(Car::new);
		car200.setRejoNumber("200");
		
		Car car500 = Car.create(Car::new);
		car500.setRejoNumber("500");
		
		
	}



}
