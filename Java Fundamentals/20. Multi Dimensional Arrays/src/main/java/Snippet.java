public class Snippet{
  public static void main(String args[]) {
    String[ ][ ] studentFullName = new String[3][3];
    
    studentFullName[0][0] = "James";
    studentFullName[0][1] = "Smith";    
    
    studentFullName[1][0] = "Maria";
    studentFullName[1][1] = "Garcia";    
    
    studentFullName[2][0] = "Michael" ;
    studentFullName[2][1] = "Johnson" ;
    
    //Printing all the first names.    
    System.out.println("First names are:")
    for(int i = 0 ; i < studentFullName.length ; i++) {
      System.out.println(studentFullName[i][0]);
    }    

    //Printing all the last names.    
    System.out.println("Last names are:")
    for(int i = 0 ; i < studentFullName.length ; i++) {
      System.out.println(studentFullName[i][1]);
    }
  }
}
