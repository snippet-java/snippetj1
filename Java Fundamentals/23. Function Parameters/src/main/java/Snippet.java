public class Snippet{
  public static void main(String args[]) {    
    //Call function 'studentMarks' by providing the parameters
    studentMarks("John",60,"Maths");
    studentMarks("Linda",90,"English");
  }
  
  //'studentMarks' function accepts three parameters 
  public static void studentMarks(String studentName , int marks, String course) {
     //All the three parameters are used in this print statement.
    System.out.println( studentName + " got "+marks+" in "+course);
  }
}
