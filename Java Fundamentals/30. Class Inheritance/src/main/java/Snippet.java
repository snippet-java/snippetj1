import java.util.Arrays;

public class Snippet{
  public static void main(String args[]) {
    int[]   ints = new int[10];
    //Initialize array
    for(int i=0; i < ints.length; i++){
        ints[i] = i;
    }
    //Converted to string.
    System.out.println("***Converting int's to strings***");
    System.out.println("The string after conversion is : "+Arrays.toString(ints));
  
    int[] source = new int[10];
    
    //Initialize source array
    for(int i=0; i < source.length; i++) {
        source[i] = i;
    }
    
    System.out.println("***Copying source array to destination array***");
    System.out.println("Source array is :"+Arrays.toString(source));

    //copy to destination array
    int[] destination = Arrays.copyOf(source, source.length);
    System.out.println("destination array is :"+Arrays.toString(destination));
    
    //copy to destinationTrimmed array , but only first 5 elements
    int[] destinationTrimmed = Arrays.copyOf(source, 5);
    System.out.println("destinationTrimmed array after copying 5 elements :"+Arrays.toString(destinationTrimmed));
      
    //Convert to String an print it
    System.out.println("Original Array is: "+Arrays.toString(ints));
    //Sort the elements of array
    java.util.Arrays.sort(ints);
    System.out.println("***Sorting array elements***");
    //Convert the sorted array to String an print it
    System.out.println("Sorted Array is: "+Arrays.toString(ints));
    }
}
