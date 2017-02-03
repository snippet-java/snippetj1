public class Snippet{
  public static void main(String args[]) {
    int number = 128;
    //Convert variable 'number' to a string
    String numberAsString = Integer.toString(number);
    System.out.println("Value of number as string is "+numberAsString);

    //Convert string numberAsString back to a number
    number = Integer.parseInt(numberAsString);
    System.out.println("Value of number is "+number);
  }
}
