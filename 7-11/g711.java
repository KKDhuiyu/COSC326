import java.text.DecimalFormat;
public class g711{
  private static int g711= 711;
  private static int cents= g711*1000000;
  private static int a,b,c,d;
  public static void main(String[] args){
      final long startTime = System.currentTimeMillis();
      for(int i=100; i< 999 ; i++){
        g711=i;

        findTheSolution();
      }
      final long endTime = System.currentTimeMillis();
      System.out.println("Total execution time: " + (endTime - startTime) );
}
  public static void findTheSolution(){
    DecimalFormat df = new DecimalFormat("0.00");
    int count=0;
   
    for (a=1; a<g711; a++) {
       if(count <1){
      for (b=a; b<g711-a; b++) {  
         if(count <1){
        for (c=b; c<g711-a-b; c++) {
           if(count <1){
          d = g711-a-b-c;
          if( d>=c && count <1){
          if (a*b*c*d==cents && a+b+c+d==g711 ){
            System.out.println("$"+df.format((double)g711/100)+" = $" + df.format((double)a/100) + " + $" + 
                               df.format((double)b/100)+ " + $"+ df.format((double)c/100) +" + $"+ df.format((double)d/100));
            count++;
          }
        }
      }
        }
         }
      }
    }
  }
    }
   // if(count!= 0){System.out.println(count+" solution(s)");}
}

  

  