public class Snippet{
	public static void main(String args[]) {		
		//Calling isEven function.
		System.out.println(isEven(5));
		System.out.println(isEven(10));
	}
	
	//Function to check if the provided number is even or not
	public static boolean isEven(int num) {		
		if(num%2 == 0) {
			return true;
		} else {
			return false;
		}		
	}
}
