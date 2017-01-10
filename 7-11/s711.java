import java.util.*;
public class s711{
  private static int s711= 711;
  private static int cents= s711*1000000;
  private static int a,b,c,d;
  public static void main(String[] args){
      final long startTime = System.currentTimeMillis();
      findTheSolution();
      final long endTime = System.currentTimeMillis();
      System.out.println("Total execution time: " + (endTime - startTime) );
}
  public static void findTheSolution(){
    for (a=1; a<711; a++) {
      for (b=a; b<711-a; b++) {    
        for (c=b; c<711-a-b; c++) {
          d = 711-a-b-c;
          if (a*b*c*d==cents && a+b+c+d==s711 && d>=c){
            System.out.println("solution: ");
            System.out.print(a+" ");
              System.out.print(b+" ");
              System.out.print(c+" ");
              System.out.print(d+" ");   
        }
      }
    }
  }
}
}
  

  