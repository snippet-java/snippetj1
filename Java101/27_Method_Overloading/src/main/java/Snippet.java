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

	//No parameters
    public String getFullName() {
      return firstName +" "+lastName;
    }
  
    //One String parameter
  	public String getFullName(String message) {
      return message+" "+firstName +" "+lastName;
    }

  public static void main(String args[]) {
		Student student1 = new Student("John","Smith",90);
		System.out.println(student1.getFullName());
		System.out.println(student1.getFullName("Hello"));
	}
}
