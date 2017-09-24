package com.test.dimuthu.model;

import java.util.List;
import java.util.Optional;

public class Employee extends Person {

	private int employeeNumber;
	
	private List<Person> dependences;
	
	public Employee(String name, int age,int employeeNumber) {
		super(name, age);
		this.employeeNumber = employeeNumber;
	}
	
	public int getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public Optional<List<Person>> getDependences() {
		return Optional.ofNullable(dependences);
	}
	
/*	public List<Person> getDependences() {
		return dependences;
	}*/

	public void setDependences(List<Person> dependences) {
		this.dependences = dependences;
	}

	@Override
	public String toString() {
		return "Employee [Name="+ getName() +", Age="+ getAge() +" Employee Number=" + employeeNumber + ", dependences=" + dependences + "]";
	}
	
	
}
