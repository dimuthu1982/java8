package com.test.dimuthu.lambda;

@FunctionalInterface
public interface MyFunctionalInterface {

	String printMyMessage(String str);
	
	default String myDefaultMethod() {
		return "Prnting Default Method...";
	}
	
}
