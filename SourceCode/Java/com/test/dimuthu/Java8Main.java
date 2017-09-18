package com.test.dimuthu;

import java.util.Arrays;
import java.util.List;

public class Java8Main {

	public static void main(String[] args) {

		List<String> stringList = Arrays.asList( "a", "b", "d" );
		
		System.out.println("Before Sorting: " + stringList.toString());
		
		stringList.sort( ( e1, e2 ) -> e1.compareTo( e2 ) );

		System.out.println("After Sorting: " + stringList.toString());
	}

}
