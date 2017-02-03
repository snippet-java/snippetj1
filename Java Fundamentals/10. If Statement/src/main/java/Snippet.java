public class Snippet{
  public static void main(String args[]) {
        //Test if x is positive
    int x = 10;
    if (x > 0) {
        System.out.println(x+" is positive");
    }
      
    //If first character of the name is vowel , An is appended to the name and printed. If not(else) ,
    //'name' is printed alone. 
    String name = "apple";
    //If condition checks if the first character of the 'name' is an vowel.
    if ("AaEeIiOoUu".indexOf(name.charAt(0)) > -1) {
        System.out.println("An "+name);
    } else {
        System.out.println(name);
    }
  }
}
