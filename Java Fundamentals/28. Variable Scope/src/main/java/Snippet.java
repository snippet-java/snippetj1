public class Snippet{
  //Variable totalNoOfStudents has Class level scope
  //It can be accessed in any static method of the class
  static int totalNoOfStudents = 60;  
  public static void main(String[] args) {
    System.out.println("value of totalNoOfStudents is "+totalNoOfStudents);
    
  //Variable firstName has the method level scope
  //This variable can be accessed form anywhere in the method 
  String firstName = "John";
  System.out.println(firstName);
    for (int x = 0; x < 5; x++) {
      //Variable x has block level scope
      //It can only be accessed in this for
        System.out.println(x);
        System.out.println(firstName);
    }
  }

}
