public class Snippet{  
  public static void main(String args[]) {
    String firstName = "John";

    System.out.println("firstName is "+firstName); 
    String lastName = " Smith";

    System.out.println("lastName is "+lastName); 
    System.out.print("Concat using concat()"
                    +"function, full name is:");

    System.out.println(firstName.concat(lastName));   
    System.out.print("Concat using + operator,"
                     +"full name is:");
    System.out.println(firstName+lastName); 
      
    System.out.println(firstName + " is " + 
                       firstName .length() + " characters long"); 
    firstName  = "Linda";
    System.out.println(firstName + " is " + 
                       firstName .length() + " characters long");  
      
    System.out.println("The first character of "+
                      firstName+" is "+firstName.charAt(0)); 
    System.out.println("The fourth character of "+
                      firstName+" is "+firstName.charAt(3));
      
    System.out.println("First occurrence of the" 
                      +"letter 'J' is at "+firstName.indexOf('J'));
    System.out.println("First occurrence of the"
                      +" letter 'a' is at "+firstName.indexOf('a'));
      
    String fullName="John Smith"; 
    System.out.println("fullName is "+fullName);    
    String replacedName =fullName.replace("John","Linda");
    System.out.println("Name after replacing is "+replacedName);
      
    firstName =fullName.substring(0,4);//This will fetch the sub string from the character at index 0 till 4 (4 is exclusive)
    System.out.println("firstName is "+firstName);//Print firstName 
    lastName =fullName.substring(5);//This will fetch the sub string from the character at index 5 till the end
    System.out.println("lastName is "+lastName);//Print lastName  
  }
}
