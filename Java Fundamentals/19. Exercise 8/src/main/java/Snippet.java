  import java.util.Arrays;
  //Reverse the elements in the array var and place in the array reverseVar
  class Snippet{
    public static void main(String[] args) {
      System.out.println("Declare and initialize an array");
      int var[]=new int[5];
      for(int i = 0 ; i < var.length ; i ++ )
      {
        var[i] = i; 
      }  
      System.out.println(Arrays.toString(var));
      
      int reverseVar[] = new int[5];
      for(int i = 0 ; i < _____ ; i ++)
      {
        //Corresponding element form end in the var array
        reverseVar[i] = var[____-i-1];
      }      
      System.out.println(Arrays.toString(reverseVar));
    }
  }
