package com.test.dimuthu.collectors;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.test.dimuthu.method.reff.Car;
import com.test.dimuthu.method.reff.Owners;
import com.test.dimuthu.model.Employee;
import com.test.dimuthu.model.Person;

public class CollectorsMain {

	public static void main(String[] args) {

		System.out.println("============= Initiating Employee Lookup Example =============");
		employeeLookupExample();

		System.out.println("\n============= Initiating Car Lookup Example =============");
		carLookupExample();

		System.out.println("\n============= Initiating File Lookup Example =============");
		fileLookupExample();
	}

	private static void employeeLookupExample() {
		Employee noDependent = new Employee("No Dependent", 30, 300) ;

		Employee emptyDependent = new Employee("Null Dependent", 34, 340) ;
		emptyDependent.setDependences(new ArrayList<Person>());

		Employee oneDependent = new Employee("One Dependent", 40, 400) ;
		oneDependent.setDependences(Arrays.asList(new Person("One Dependent- Dependent 1", 10)));

		Employee twoDependent = new Employee("Two Dependent", 50, 500) ;
		twoDependent.setDependences(Arrays.asList(new Person("Two Dependent- Dependent 1", 14), new Person("Two Dependent- Dependent 2", 31)));

		Employee threeDependent = new Employee("Three Dependent", 60, 600) ;
		threeDependent.setDependences(Arrays.asList(new Person("Three Dependent- Dependent 1", 5), new Person("Three Dependent- Dependent 2", 20), new Person("Three Dependent- Dependent 3", 15)));


		List<Employee> employees = new ArrayList<>();
		employees.add(noDependent);
		employees.add(emptyDependent);
		employees.add(oneDependent);
		employees.add(twoDependent);
		employees.add(threeDependent);

		System.out.println("\n\t------------------ Find employees age above 40 ------------------");
		employees.stream().
		filter(e -> e.getAge() > 40).
		forEach(System.out::println);

		System.out.println("\n\t------------------ Find Employees with no dependents ------------------");
		employees.stream().
		filter(e -> ((!e.getDependences().isPresent()) || e.getDependences().get().isEmpty())).
		forEach(System.out::println);

		System.out.println("\n\t------------------ Find Employees with 1 dependents ------------------");
		employees.stream().
		map(e -> e.getDependences()).
		filter(m -> m.isPresent()). //Eliminates null values
		filter(m -> m.get().size() == 1).
		forEach(System.out::println);

		System.out.println("\n\t------------------ Find Employees with 2 dependents ------------------");
		employees.stream().
		map(e -> e.getDependences()).
		filter(m -> m.isPresent()). //Eliminates null values
		filter(m -> m.get().size() == 2).
		forEach(System.out::println);

		System.out.println("\n\t------------------ Find Employees with 3 dependents ------------------");
		employees.stream().
		map(e -> e.getDependences()).
		filter(m -> m.isPresent()). //Eliminates null values
		filter(m -> m.get().size() == 3).
		forEach(System.out::println);

		System.out.println("\n\t------------------ Find Employees with more than 2 dependents ------------------");
		employees.stream().
		map(e -> e.getDependences()).
		filter(m -> m.isPresent()). //Eliminates null values
		filter(m -> m.get().size() >=2).
		forEach(System.out::println);

		System.out.println("\n\t------------------ Find Employees age > 51 with more than 2 dependents =============");
		employees.stream().
		filter(e -> e.getAge() > 51). 
		map(e -> e.getDependences()).
		filter(m -> m.isPresent()). //Eliminates null values
		filter(m -> m.get().size() >=2).
		forEach(System.out::println);

	}

	private static void carLookupExample() {
		Car car1 = new Car();
		car1.setMilage(50);
		car1.setRejoNumber("5000");

		Car car2 = new Car();
		car2.setMilage(3020);
		car2.setRejoNumber("33002");

		Owners owneer = new Owners();
		owneer.setName("First Owner");

		Owners owneer2 = new Owners();
		owneer2.setName("Second Owner");

		car2.setOwner(Arrays.asList(owneer,owneer2));

		List<Car> valueList = new ArrayList<>();
		valueList.add(car1);
		valueList.add(car2);


		System.out.println("\n\t------------------ Data List ------------------");
		System.out.println(car1);
		System.out.println(car2);


		System.out.println("\n\t------------------  Brand New Cars ------------------");
		valueList.stream().
		filter(CollectorsMain::isBrandNew).
		forEach(System.out::print);

		System.out.println("\n\t------------------  Single Owner ------------------");
		valueList.stream().
		filter(c -> (c.getOwner() != null && c.getOwner().size() == 2)).
		forEach(System.out::print);
	}

	private static void fileLookupExample() {
		Path filePath = FileSystems.getDefault().getPath("C:\\temp");

		System.out.println(filePath);

		try {

			Predicate<Path> isHomeAvailable = path -> {
				try {
					return Files.list(path).map(p -> p.getFileName().toFile()).filter(f -> f.getName().equals("home")).count() == 1;

				} catch (IOException e) {
					e.printStackTrace();
				}
				return false;
			};

			System.out.println("\n\t------------------  Listing folder names as numbers and having sub folder as home ------------------");
			Files.list(filePath).
			filter(Files::isDirectory).
			filter(p -> isInteger(p.toFile().getName())).
			filter(isHomeAvailable).
			map(p -> p.getFileName().toFile().getName()).
			forEach(System.out::println);


		} catch (Exception e) {
			e.printStackTrace();
		}


		System.out.println("\n\t------------------  Testing Accumilator to find max value ------------------");
		List<Integer> accumilatingValueList = new ArrayList<>();
		accumilatingValueList.add(50);
		accumilatingValueList.add(30);
		accumilatingValueList.add(200);

		Stream<Integer> stream = accumilatingValueList.stream();
		Integer accmilatedValue = stream.reduce(Math::max).orElse(null);

		System.out.println("Reduce (Accumilator) : " + accmilatedValue);
	}

	static boolean isBrandNew(Car car) {
		return !Optional.ofNullable(car.getOwner()).isPresent();
	}

	private static boolean isInteger(String strNumber) {
		try {
			Integer.parseInt(strNumber);
			return true;
		} catch (NumberFormatException e) {}
		return false;
	}

}
