    /*
     *incomplete class. But basic functions are working fine. 1+ 2*3 needs to be fixed.
     */
    package arithmetic;

    import java.util.*;

    /**
     *
     * @author huiyu jia
     */
    public class Arithmetic {
        private static LinkedList<Integer> nums= new LinkedList<Integer>();
        private static final char[] ARI= {'+', '*'};

        /**
         * @param args the command line arguments
         */
        public static void main(String[] args) {
            // Scanner sc = new Scanner(System.in);
            // System.out.println("Enter the first scenario pls:");

            // while(sc.hasNextInt()){
            //     nums.add(sc.nextInt()); 
           //  }  

             printInOrder();
        }


        public static void printInOrder(){
            
            BST bst= new BST(0,100);
            bst.add(-55);
            bst.add(0);
            bst.search(0);
           
        }
        private static class BST{
            private int level,value,result; //value is root
            private BST left,right;
            private String stringPrint;

            public BST(int level,int value){
                this.level=level;
                this.value=value;
                if(level==0){
                    this.result=value;
                    this.stringPrint=""+value;
                }
                left=null;
                right=null;
            }
            public BST(int level,int value, int result,String s){
                this.level=level;
                this.value=value;
                this.result= result;
                this.stringPrint=s+ value;
                left=null;
                right=null;
            }

            public void add(int v){
                if(level==0 && result==0 && value==0 ){
                    value=v;
                    result=v;
                    return;
                }
                if(left==null){
                    left= new BST(level++,v,(result+v),this.stringPrint+" + "); // root is 1.
                }else{
                    left.add(v);
                }
                if(right==null){
                    right = new BST(level++,v,(result*v),this.stringPrint+" * ");
                }else{
                    right.add(v);
                }
         }
            public String search(int wantedResult){
           
                if(left!=null && right !=null){
                    this.left.search(wantedResult);
                    this.right.search(wantedResult);
                }else{
                    
                    if(result==wantedResult){  
                        System.out.println(this.stringPrint);
                        
                        return stringPrint;
                    }
                }
                   
                return "impossible";
                   
            }
            public String toString(){
                return value+" "+result+" "+stringPrint;
            }
        }
    }
