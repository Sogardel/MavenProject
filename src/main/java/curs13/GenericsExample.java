package curs13;


public class GenericsExample {

	public static void main(String[] args) {

		printDetails(123, "123");
		printDetails("123", true);
		//printDetails(250.5);
		//printDetails('n');
	}

	public static <T> void printDetails(T obj, T obj2) {
		System.out.println("Obiectul meu este :" + obj);
		System.out.println("Data type-ul obiectului este  :" + obj.getClass().getName());
	}
	
	
	
/*	
	public static void printDetails(String text) {
		System.out.println("obiectul meu este :" + text.getClass());
		
	}	
	
	public static void printDetails(Integer  num) {
		System.out.println("obiectul meu este :" + num.getClass());
		
	} 
	*/
 
	
	
	
}
