
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Huiyu Jia, Jason Zhao, Celine Ahkit, Sam Grant, Jordan McRae
 */
public class WiFiAccessPoints {

    /**
     * @param args the command line arguments
     */


    public static void main(String[] args) {
        String fileName = args[0];
        String line;
        int lineCount = 0;
        ArrayList<Double> east = new ArrayList<>();
        ArrayList<Double> north = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                
                    String[] nums = line.split("\\s+");
                    east.add(Double.valueOf(nums[0]));
                    north.add(Double.valueOf(nums[1]));
                
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (IOException e) {
        }
       
double nc2=findMinNCTwo(east, north);
double nc3= findMinNCThree(east, north);
//System.out.println("NC2: "+ nc2+" \n"+"NC3:  "+nc3);
if(nc3==0){
    System.out.println("The maximum radius of the circle "
                + "should be less than " + nc2);
}else if(nc2==0){
     System.out.println("The maximum radius of the circle "
                + "should be less than " + nc3);
}else if(nc2>nc3 && east.size()<=13){
     System.out.println("The maximum radius of the circle "
                + "should be less than " + nc2);
}else{
        System.out.println("The maximum radius of the circle "
                + "should be less than " + Math.min(nc2, nc3));
}
    }

    public void exceptionHandler(ArrayList<Double> e, ArrayList<Double> n) {
        if (e.size() < 12) {
            System.err.println("Infinite. Access points value smaller than 12");
            return;
        } else if (e.size() != n.size()) {
            System.err.println("Invalied input file");
            return;
        }
    }

    /**
     * The core concepts of this algorithm are listed below. 1. Finding the
     * maximum radius among those circles that contain 11 points is to find the
     * minimum radius among those circles that contain 12 points. 2. The minimum
     * "radiuses" will be found in a circle that "just" contains 12 pints, which
     * means there are at least two points on the circle's boundary.
     * Mathematically speaking, the min radius will be found under this
     * circumstance, where there are points a=(x1,y1) and b=(x2,y2). and there
     * is a circle centered at point c=((x1+x2)/2, (y1,+y2)/2), and the circle's
     * radius is sqrt((x1-x2)^2 + (y1-y2)^2), there are 12 points in such a
     * circle. 3. keep updating the minimum radius. 4. speed up by filtering
     * points has longer distance.
     *
     * @param east the east coordinators array
     * @param north the north coordinators array
     * @return the minimum radius among those circles that contain 12 points.
     */
    public static double findMinNCTwo(ArrayList<Double> east, ArrayList<Double> north) {
        int len = east.size();
        double minDis = 0.0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                double distance = distance(
                        east.get(i), north.get(i),
                        east.get(j), north.get(j));
                if (minDis != 0 && minDis < distance) { // 3. filter

                } else {
                    int pointsInCircle = 2;
                    double[] center = centerNCTwo(
                            east.get(i), north.get(i),
                            east.get(j), north.get(j));
                    for (int k = 0; k < len; k++) {
                        if (k != i && k != j) { // if not those two points
                            double tempD = distance(
                                    east.get(k), north.get(k),
                                    center[0], center[1]);
                            if (tempD <distance / 2) {
                                pointsInCircle++;
                            }
                        }
                    }
                    if (pointsInCircle == 12) { // contain just 12 points
                        //update min distance
                        minDis = distance;
                    }
                }
            }
        }

        return minDis/2;
    }

    /**
     * distance between two points.
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    /**
     * center point of two points.
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public static double[] centerNCTwo(double x1, double y1, double x2, double y2) {
        double[] temp = new double[2];
        temp[0] = (x1 + x2) / 2;
        temp[1] = (y1 + y2) / 2;
        return temp;
    }

    /**
     * circle center of three known points on the circle.
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public static double findMinNCThree(ArrayList<Double> east, ArrayList<Double> north) {
        int len = east.size();
        double minDis = 0.0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    Point p1 = new Point(east.get(i), north.get(i));
                    Point p2 = new Point(east.get(j), north.get(j));
                    Point p3 = new Point(east.get(k), north.get(k));
                    Circle c = circleFromPoints(p1, p2, p3);
                    Point center = c.center;
                    Double radius = c.radius;
                    if (minDis != 0 && minDis < radius) { // 3. filter

                    } else {
                        int pointsInCircle = 3;
                        for (int l = 0; l < len; l++) {
                            if (k != i && k != j && l != k) { // if not those two points
                                double tempD = distance(
                                        east.get(l), north.get(l),
                                        center.x, center.y);
                                if (tempD <=radius) {
                                    pointsInCircle++;
                                }
                            }
                        }
                        if (pointsInCircle == 12) { // contain just 12 points
                            //update min distance
                            minDis = radius;
                        }
                    }
                }
            }
        }

            // if(p2.x==p1.x && p2.x)
            return minDis;

        }
    

    public static Circle circleFromPoints(final Point p1, final Point p2, final Point p3) {
        final double offset = Math.pow(p2.x, 2) + Math.pow(p2.y, 2);
        final double bc = (Math.pow(p1.x, 2) + Math.pow(p1.y, 2) - offset) / 2.0;
        final double cd = (offset - Math.pow(p3.x, 2) - Math.pow(p3.y, 2)) / 2.0;
        final double det = (p1.x - p2.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p2.y);

       

        final double idet = 1 / det;

        final double centerx = (bc * (p2.y - p3.y) - cd * (p1.y - p2.y)) * idet;
        final double centery = (cd * (p1.x - p2.x) - bc * (p2.x - p3.x)) * idet;
        final double radius
                = Math.sqrt(Math.pow(p2.x - centerx, 2) + Math.pow(p2.y - centery, 2));

        return new Circle(new Point(centerx, centery), radius);
    }

    static class Circle {

        final Point center;
        final double radius;

        public Circle(Point center, double radius) {
            this.center = center;
            this.radius = radius;
        }

        @Override
        public String toString() {
            return new StringBuilder().append("Center= ").append(center).append(", r=").append(radius).toString();
        }
    }

    static class Point {

        final double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }

    }
}
