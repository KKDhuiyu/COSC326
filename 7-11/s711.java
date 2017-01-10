import java.text.DecimalFormat;
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
        DecimalFormat df = new DecimalFormat("0.00");
        List<Integer> list = findDivs();
        int size= list.size();
         for (int ai=0; ai<size; ai++) {
           a=list.get(ai);
            for (int bi=list.indexOf(list.get(ai)); bi<size; bi++) {
              b=list.get(bi);
                for (int ci=list.indexOf(list.get(bi)); ci<size; ci++) {
                c=list.get(ci);
                    d = 711-a-b-c;
                    if ( a*b*c*d==cents && a+b+c+d==s711 && d>=c){
                        System.out.println("$"+df.format((double)s711/100)+" = $" + 
                                           df.format((double)a/100) + " + $" +
                                           df.format((double)b/100)+ " + $"+ 
                                           df.format((double)c/100) +" + $"+ 
                                           df.format((double)d/100));
                    }
                }
            }
        }
    }
    
    public static List<Integer> findDivs(){
      List<Integer> list = new LinkedList<Integer>();
      for( int i=1; i<s711-1; i++){
        if(cents%i==0){
          list.add(i);
        }
      }
      return list;
    }
    
}


  
