public class Snippet{
  public static void main(String args[]) {
    System.out.println("Print form 1 to 10 with while loop");
    int n = 1;
    while (n <= 10) {
        System.out.println(n);
        n = n+1;
    }
      
    System.out.println("Prtint form 1 to 10 with do while loop");
    n = 1;
    do{
        System.out.println(n);
        n = n+1;
    }while (n <= 10);
  }
}
