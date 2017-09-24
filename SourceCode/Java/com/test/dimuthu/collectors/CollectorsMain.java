package com.test.dimuthu.collectors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import com.test.dimuthu.model.Employee;
import com.test.dimuthu.model.Person;

public class CollectorsMain {

	public static void main(String[] args) {
		
		/*List<String> givenList = Arrays.asList("a", "bb", "ccc", "dd");
		
		List<String> resultStreemList = givenList.stream().collect(toList());
		
		System.out.println("givenList: " + givenList);
		System.out.println("resultStreemList: " + resultStreemList);
		
		Set<String> resultStreemSet = givenList.stream().collect(toSet());
		System.out.println("resultStreemSet: " + resultStreemSet);
		
		List<String> resultCollection = givenList.stream().collect(toCollection(LinkedList::new));
		System.out.println("resultCollection: " + resultCollection);
		
		Map<String, Integer> resultMap = givenList.stream().collect(toMap(Function.identity(), String::length));
		System.out.println("resultMap: " + resultMap);*/
		
		
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
		
		System.out.println("============= Find employees age above 40 =============");
		employees.stream().
		filter(e -> e.getAge() > 40).
		forEach(System.out::println);
		
		System.out.println("\n============= Find Employees with no dependents =============");
		employees.stream().
		filter(e -> ((!e.getDependences().isPresent()) || e.getDependences().get().isEmpty())).
		forEach(System.out::println);
		
		System.out.println("\n============= Find Employees with 1 dependents =============");
		employees.stream().
		map(e -> e.getDependences()).
		filter(m -> m.isPresent()). //Eliminates null values
		filter(m -> m.get().size() == 1).
		forEach(System.out::println);
		
		System.out.println("\n============= Find Employees with 2 dependents =============");
		employees.stream().
		map(e -> e.getDependences()).
		filter(m -> m.isPresent()). //Eliminates null values
		filter(m -> m.get().size() == 2).
		forEach(System.out::println);
		
		System.out.println("\n============= Find Employees with 3 dependents =============");
		employees.stream().
		map(e -> e.getDependences()).
		filter(m -> m.isPresent()). //Eliminates null values
		filter(m -> m.get().size() == 3).
		forEach(System.out::println);
		
		System.out.println("\n============= Find Employees with more than 2 dependents =============");
		employees.stream().
		map(e -> e.getDependences()).
		filter(m -> m.isPresent()). //Eliminates null values
		filter(m -> m.get().size() >=2).
		forEach(System.out::println);
		
		System.out.println("\n============= Find Employees age > 51 with more than 2 dependents =============");
		employees.stream().
		filter(e -> e.getAge() > 51). 
		map(e -> e.getDependences()).
		filter(m -> m.isPresent()). //Eliminates null values
		filter(m -> m.get().size() >=2).
		forEach(System.out::println);
		
		

	}

}
