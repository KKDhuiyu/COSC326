
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author hjia
 */
public class WiFiAccessPoints {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String fileName = "TrickyAccessPoints1.txt";
        String line;
        boolean isAccessPointsFile = false;
        int lineCount = 0;
        ArrayList<Double> east = new ArrayList<>();
        ArrayList<Double> north = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                lineCount++;
                if (lineCount == 1) {
                    if (line.equals("Access point sites")) {
                        isAccessPointsFile = true;
                    }
                } else if (isAccessPointsFile) {
                    String[] nums = line.split(" ");
                    east.add(Double.valueOf(nums[0]));
                    north.add(Double.valueOf(nums[1]));
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (IOException e) {
        }
        System.out.println(east.toString());
        System.out.println(north.toString());
        System.out.println(findMin(east,north));
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
    public static double findMin(ArrayList<Double> east, ArrayList<Double> north) {
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
                    double[] center = center(
                            east.get(i), north.get(i),
                            east.get(j), north.get(j));
                    for (int k = 0; k < len; k++) {
                        if (k != i && k != j) { // if not those two points
                            double tempD = distance(
                                    east.get(k), north.get(k),
                                    center[0], center[1]);
                            if (tempD < distance / 2) {
                                pointsInCircle++;
                            }
                        }
                    }
                    if(pointsInCircle==12){ // contain just 12 points
                        //update min distance
                        minDis = distance;
                    }
                }
            }
        }

        return minDis;
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
    public static double[] center(double x1, double y1, double x2, double y2) {
        double[] temp = new double[2];
        temp[0] = (x1 + x2) / 2;
        temp[1] = (y1 + y2) / 2;
        return temp;
    }

}
