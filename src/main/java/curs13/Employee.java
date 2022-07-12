package curs13;

public class Employee {
	
	String name;
	int salary;
	
	
	public Employee(String nume, int salary) {
		setName(nume);
		setSalary(salary);
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	
	

}
