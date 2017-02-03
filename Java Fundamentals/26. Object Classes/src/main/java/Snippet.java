public class Snippet{ 
  //Instance variables
  String firstName;
  String lastName;
  int marks;

  //Constructor
  Student(String first,String last,int score) {
      firstName = first;
      lastName = last;
      marks = score;
  }

  public String getFullName() {
    return firstName +" "+lastName;
  }

public static void main(String args[]) {
  Student student1 = new Student("John","Smith",90);
  System.out.println("Full name of student1 is "+student1.getFullName());
  Student student2 = new Student("Linda","Smith",40);
  System.out.println("Full name of student2 is "+student2.getFullName());
  }
}
