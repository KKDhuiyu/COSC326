import java.text.DecimalFormat;
import java.util.*;
public class g711{
    private static int g711= 711;
    private static int cents= g711*1000000;
    private static int a,b,c,d;
    private static int[] solutionCount = new int[900];
    public static void main(String[] args){
        final long startTime = System.currentTimeMillis();
        for(int i=100; i< 999 ; i++){
            g711=i;
            countSolutions();
       
        }
        for(int i=0; i<900;i++){
          if(solutionCount[i]==1){
            g711=i+100;
            printSolution();
          }
        }
        System.out.println(solutionCount[889]);
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) );
    }
    public static void countSolutions(){
        DecimalFormat df = new DecimalFormat("0.00");
        List<Integer> list = findDivs();
        int size= list.size();
        for (int ai=0; ai<size; ai++) {
            a=list.get(ai);
            for (int bi=list.indexOf(list.get(ai)); bi<size; bi++) {
                b=list.get(bi);
                for (int ci=list.indexOf(list.get(bi)); ci<size; ci++) {
                    c=list.get(ci);
                    d = g711-a-b-c;
                    if( d>=c){
                        if (a*b*c*d==cents && a+b+c+d==g711 ){
                            solutionCount[g711-100]++;
                        }
                    }
                }
            }
        }
    }
    public static void printSolution(){
         DecimalFormat df = new DecimalFormat("0.00");
        List<Integer> list = findDivs();
        int size= list.size();
        for (int ai=0; ai<size; ai++) {
            a=list.get(ai);
            for (int bi=list.indexOf(list.get(ai)); bi<size; bi++) {
                b=list.get(bi);
                for (int ci=list.indexOf(list.get(bi)); ci<size; ci++) {
                    c=list.get(ci);
                    d = g711-a-b-c;
                    if( d>=c){
                        if (a*b*c*d==cents && a+b+c+d==g711 ){
                             System.out.println("$"+df.format((double)g711/100)+" = $" +
                                                       df.format((double)a/100) + " + $" +
                                                       df.format((double)b/100)+ " + $"+
                                                       df.format((double)c/100) +" + $"+
                                                       df.format((double)d/100));
                                }
                        }
                    }
                }
            }
        }
        
    
    
    public static List<Integer> findDivs(){
        List<Integer> list = new LinkedList<Integer>();
        for( int i=1; i<g711-1; i++){
            if(cents%i==0){
                list.add(i);
            }
        }
        return list;
    }
    
}



