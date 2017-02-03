public class ForDemo {
  public static void main(String args[]) {
    //Example : This is easier to read
    for (int i = 0; i <= 10; i++) {
    System.out.println(i);
    }
      
    //Print stars
    int numRows = 10;      

   //Printing the 'numRows' rows
   for(int currentRow = 1 ; currentRow <= numRows ; currentRow++){
    // * is  appended to the intiable stars for every iteration
    String stars = "";    
    //The number of stars in each row is same the the row number
    for(int numStars = 1 ; numStars <= currentRow ; numStars++) {
      stars = stars + '*';
    }
    
    //to print new line
     System.out.println(stars);
  }
 }
}