public class StringFunctions {	
	public static void main(String args[]) {
		String fullName="John Smith";    
		String firstName =fullName.substring(0,4);//This will fetch the sub string from the character at index 0 till 4 (4 is exclusive)
		System.out.println("firstName is "+firstName);//Print firstName 
		String lastName =fullName.substring(5);//This will fetch the sub string from the character at index 5 till the end
		System.out.println("lastName is "+lastName);//Print lastName
	}
}
