public class StringFunctions {	
	public static void main(String args[]) {
		String firstName = "John";
		System.out.println("firstName is "+firstName); 
		String lastName = " Smith";
        System.out.println("lastName is "+lastName); 
        System.out.print("Concat using concat() function, full name is:");
		System.out.println(firstName.concat(lastName)); 	
		System.out.print("Concat using + operator, full name is:");
		System.out.println(firstName+lastName); 
	}
}
