package curs12;

public class TestAnnontation {

	public static void main(String[] args) {

		Tester t1 =  new Tester();
		Developer dev = new Developer();
		
		if(dev.getClass().isAnnotationPresent(MostImportant.class)) {
			
			System.out.println("This employee is very important!");
			
		}else {
			System.out.println("This employee is not that important!");
		}
		
		
		
	}

}
