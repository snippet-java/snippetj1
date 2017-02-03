public class Snippet{      
  public static void main(String args[]) {
    String[][] studentsWithGrade;
    studentsWithGrade = new String[3][2];
    
    //Student1
    studentsWithGrade[0][0] = "John";
    studentsWithGrade[0][1] = "A";  
    //Student2
    studentsWithGrade[1][0] = "Linda";
    studentsWithGrade[1][1] = "A";  
    //Student3    
    studentsWithGrade[2][0] = "Sam";
    studentsWithGrade[2][1] = "B";  
    
    System.out.println("Number of students with grade 'A' are : " +findNoOfAGrades(studentsWithGrade));
  }
  
  public static int findNoOfAGrades(String[][] studentsWithGrade) {
    int noOfAGrades = 0;
      //Iterate over the array studentsWithGrade
    for(_____ ) {
    //Check if the grade of this student is A
      if(____) {
        noOfAGrades++;
      }
    }    
    return noOfAGrades;    
  }
}
