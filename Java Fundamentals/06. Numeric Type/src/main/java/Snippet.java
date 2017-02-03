import java.util.Scanner;

class TempConversion {
	public static void main(String args[]) {		   
		  System.out.println();
			int celsius  = 100;  
		  //Using operators to convert from celsius to fahrenheit
		  //To convert temperatures in degrees Celsius to Fahrenheit, multiply //by 1.8 (or 9/5) and add 32.
		  int fahrenheit = celsius  * 9 / 5 + 32;  

		  String message = celsius +" C is " + fahrenheit + " F.";  
		  System.out.println(message);

		  //To convert temperatures in degrees Fahrenheit to Celsius, subtract //32 and multiply by .5556 (or 5/9).
		  // And back to celsius to validate the answer
		   int fahrenheitToCelsius = (fahrenheit - 32) * 5 / 9;
		   message = fahrenheit +" F is " + fahrenheitToCelsius + " C.";  
		   System.out.println(message);		
	}
}
