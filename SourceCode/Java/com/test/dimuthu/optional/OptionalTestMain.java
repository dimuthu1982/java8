package com.test.dimuthu.optional;

import java.util.NoSuchElementException;
import java.util.Optional;

import com.test.dimuthu.method.reff.Car;

public class OptionalTestMain {

	public static void main(String[] args) {

		Optional<String> empty = Optional.empty();
		System.out.println("empty.get(): " + empty.isPresent());

		//		 When calling of() the parameter cannot be null, Otherwise NullPointerException
		Optional<String> test  = Optional.of("Test");
		System.out.println("test.isPresent(): " + test.isPresent()); //true


		//		 When calling ofNullable() the parameter can be null, If so it will add an empty value
		Optional<String> nullableValue  = Optional.ofNullable(null);
		System.out.println("nullableValue.isPresent(): " + nullableValue.isPresent()); //false

		String emptyVal = " ";
		Optional<String> emptyValOpction  = Optional.ofNullable(emptyVal);
		System.out.println("emptyValOpction.isPresent(): " + emptyValOpction.isPresent()); //false

		emptyValOpction.ifPresent(name -> System.out.println("There is a value : " + name));


		//		================== Difference Between orElse and orElseGet ================== 

		String nullVal = null;
		System.out.println("Optional.ofNullable(nullVal).orElse(getElseValue()): " + Optional.ofNullable(nullVal).orElse(getElseValue()));

		System.out.println("Optional.ofNullable(nullVal).orElseGet(() -> getElseValue()): " + Optional.ofNullable(nullVal).orElseGet(() -> getElseValue()));

		/**
		 * Output:
		 * Calling getElseValue()
			Optional.ofNullable(nullVal).orElse(getElseValue()): Else
			Calling getElseValue()
			Optional.ofNullable(nullVal).orElseGet(() -> getElseValue()): Else
		 */
		// Note: Both orElse and orElseGet behaves same sine the value is null


		System.out.println("\n======= Calling valide values to orElse vs orElseGet ====");

		String notNullVal = "Not Null Value";
		System.out.println("Optional.ofNullable(nullVal).orElse(getElseValue()): " + Optional.ofNullable(notNullVal).orElse(getElseValue()));

		System.out.println("Optional.ofNullable(nullVal).orElseGet(() -> getElseValue()): " + Optional.ofNullable(notNullVal).orElseGet(() -> getElseValue()));

		/**
		 * Output:
		 * Calling getElseValue()
		   Optional.ofNullable(nullVal).orElse(getElseValue()): Not Null Value
		   Optional.ofNullable(nullVal).orElseGet(() -> getElseValue()): Not Null Value
		 */
		// Note: Output is the same. But orElse called getElseValue() enthought there is a valid value


		//		================== Exceptions with orElseThrow ==================
		System.out.println("\n======= Exceptions with orElseThrow ====");
		/*
		 * orElseThrow will throw an error if the value is null
		 */
		String nullValToThrow = null;

		try {
			Optional.ofNullable(nullValToThrow).orElseThrow(RuntimeException::new);
		} catch (RuntimeException e) {
			System.out.println("Error Occured");
		}

		//		================== Returning Value with get() ==================
		System.out.println("\n======= Returning Value with get() ====");
		
		// Note: get() will only return value if it has a valid, otherwise NoSuchElementException	
		Optional<String> value = Optional.of("Value");
		System.out.println("value.get(): " + value.get()); // returns "Value"
		
		try {
			value = Optional.ofNullable(nullVal);
			value.get();
		} catch (NoSuchElementException e) {
			System.out.println("Error in calling get() for nul value");
		}
		
		//		================== Conditional Return with filter() ==================
		System.out.println("\n======= Conditional Return with filter() ====");
		
		Optional<String> mainValue = Optional.of("1234567");
		System.out.println("mainValue.filter(val -> val.contains(\"3456\")).isPresent(): " + mainValue.filter(val -> val.contains("3456")).isPresent()); //true
		System.out.println("mainValue.filter(val -> val.contains(\"99999\")).isPresent(): " + mainValue.filter(val -> val.contains("99999")).isPresent()); //false
		
		
		Car myCar = new Car();
		myCar.setMilage(300);
		myCar.setRejoNumber("qwerty");
		
//		Optional<Car> myCarOp = ;
		boolean isMilagePresent = Optional.of(myCar)
		.map(Car::getMilage)
		.filter(m -> m > 100)
		.filter(m -> m > 200)
		.isPresent();
		
		boolean isRejoPresent = Optional.of(myCar).map(Car::getRejoNumber).filter(r -> r.equals("qwerty")).isPresent();
		
		System.out.println("isMilagePresent: " + isMilagePresent);
		System.out.println("isRejoPresent: " + isRejoPresent);
		
		
	}

	private static String getElseValue() {
		System.out.println("Calling getElseValue()");
		return "Else";
	}

}
