package curs12;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestExample2 {
	
	@Test(invocationCount = 4)
	public void runmMultipleTimesWithTestNg() {
		
		System.out.println(" testng print");
		
	}
	
	@RunMultipleTimes(times=3)
	@Test
	public void runMultipleTimesWithCustomAnnonation() {
		
		System.out.println("custom print");

	}
	
	@BeforeClass
	public void setUp() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		TestExample2 testObj = new TestExample2();
		
		for(Method metod : testObj.getClass().getDeclaredMethods()) {
		
			if(metod.isAnnotationPresent(RunMultipleTimes.class)) {
				
				RunMultipleTimes annotation =  metod.getAnnotation(RunMultipleTimes.class);
				
				for(int i =0; i<annotation.times(); i++) {
					
					metod.invoke(testObj);
				}
				
			}
			
			
		}
		
		
	}

}
