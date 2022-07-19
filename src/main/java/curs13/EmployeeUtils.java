package curs13;

public class EmployeeUtils<T extends Employee> {
	
	// <T> --> fol orice data type
	// <T extends Number> //int, long, double, float --! string, boolean
	// <T extedns Employee> -->
	
 //Developer
 //Manager
  
	T employee;
	
	public EmployeeUtils(T employee) {
		this.employee =  employee;
	}
	
	public int getSalary() {
		return employee.getSalary();
	}
	
	public String getNume() {
		return employee.getName();
	}
	
	public boolean isSalaryEqual(EmployeeUtils<?> otherEmployee) {
		
		if(employee.getSalary() == otherEmployee.getSalary()) {
			return true;
		}
		return false;
	}
	
}
