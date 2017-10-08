package com.test.dimuthu.model;

import java.util.List;
import java.util.Optional;

public class Employee extends Person implements Comparable<Employee> {

	private int employeeNumber;
	
	private String address;
	
	private List<Person> dependences;
	
	public Employee(String name, int age,int employeeNumber, String address) {
		super(name, age);
		this.employeeNumber = employeeNumber;
		this.address = address;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employee [Name="+ getName() +", Age="+ getAge() +" Employee Number=" + employeeNumber + ", Address=" + address + ", dependences=" + dependences + "]";
	}

	@Override
	public int compareTo(Employee o) {
		return Integer.compare(this.getAge(), o.getAge());
	}
	
	
	
}
