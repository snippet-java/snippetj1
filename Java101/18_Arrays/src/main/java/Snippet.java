public class Snippet{
	public static void main(String args[]) {
		//Declare array
		String[] studentNames;
		//Initialize
		studentNames = new String[3];
		studentNames[0] = "John";
		studentNames[1] = "Linda";
		studentNames[2] = "Joe";
		//Iterate over the array
		for(int i = 0 ; i < studentNames.length ; i++) {
			System.out.println(studentNames[i]);
		}		
	}
}
