package com.test.dimuthu.lambda;

import java.util.function.Function;

public class LambdaMain {

	private static int staticNumber = 10;

	private int globleVariable = 20;

	public static void main(String[] args) {

		LambdaMain lamdaTest = new LambdaMain();

		lamdaTest.calculateStaticVal();
		lamdaTest.calculatePrivateVal();
		lamdaTest.calculateFinalVal();

		lamdaTest.callingMyFunctionalInterface();
	}

	private void calculateStaticVal() {
		Function<Integer, Integer> calculateStaticVal = val -> val * staticNumber;

		System.out.println("calculateStaticVal: " + calculateStaticVal.apply(100));
	}

	private void calculatePrivateVal() {
		Function<Integer, Integer> calculateStaticVal = val -> val * globleVariable;

		System.out.println("calculateStaticVal: " + calculateStaticVal.apply(100));
	}

	private void calculateFinalVal() {

		int implicitlyFinalVariable = 50; //Implicitly final

		Function<Integer, Integer> calculateFinalVal = val -> val * implicitlyFinalVariable;

		System.out.println("calculateFinalVal: " + calculateFinalVal.apply(100));
	}

	private void callingMyFunctionalInterface() {
		MyFunctionalInterface myAnnonymusClass = new MyFunctionalInterface() { //With in the scope the function has access to the default method

			@Override
			public String printMyMessage(String str) {
				myDefaultMethod();
				return "Print by AnonnymusClass : " + str;
			}
		};
		myAnnonymusClass.printMyMessage("Calling AnonnymusClass");

		//		Below two options have no access to the default method. Just providing implementation to the functional interface 
		Function<String, String> myFunctionalInterfaceLmbda = str -> "Print by Function<String, String> : " + str;
		myFunctionalInterfaceLmbda.apply("Calling Function<String, String>");

		MyFunctionalInterface myFunctionalInterface = str -> "Print by MyFunctionalInterface : " + str; //Calling myDefaultMethod() will not compile
		myFunctionalInterface.printMyMessage("Calling MyFunctionalInterface");
	}



}
