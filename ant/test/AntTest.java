
package ant;

//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
//import org.junit.Before;

/**
 *
 * @author hjia
 */
public class AntTest {

//private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    public AntTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

//    @Before
//    public void setUpStream() {
//        System.setOut(new PrintStream(outContent));
//    }
//
//    @After
//    public void cleanUpStreams() {
//        System.setOut(null);
//    }

    @After
    public void tearDown() {
    }

   

    /**
     * Test of move method, of class Ant.
     * This contains several test cases.
     */
    @Test
    public void testMove() {
        
      //The first test case would be w WWWW wwww
      //In this case, the state woulndn't matter.
      //An ant on the highway.
      
        int steps = 4;
        ArrayList<String> dnaList = new ArrayList<>();
        dnaList.add("w WWWW wwww");
        String expectedOut="w WWWW wwww\n# 0 0\n\nw WWWW wwww\n# -1 0\n\n"
                + "w WWWW wwww\n# -2 0\n\nw WWWW wwww\n# -3 0\n\n";
        assertEquals(expectedOut, Ant.move(steps,dnaList));
        System.out.println("hightway ant test passed");
        //The second case is for testing the ant behaves as we expected 
        // when the state changes.
        // We need to test it mainly because we need to make sure two things:
        // 1. The ant can extract the right DNA in a tile with a state.
        // 2. The LinkedList like data structure does allow us to track
        // the state of a tile by using getPrev().getColor.
        steps = 12;
        dnaList.clear();
        dnaList.add("w ESWN bbbb");
        dnaList.add("b WNES wwww");
        expectedOut="w ESWN bbbb\nb WNES wwww\n# 0 0\n\n"
                + "w ESWN bbbb\nb WNES wwww\n# 1 0\n\n"
                + "w ESWN bbbb\nb WNES wwww\n# 1 -1\n\n"
                + "w ESWN bbbb\nb WNES wwww\n# 0 -1\n\n"
                + "w ESWN bbbb\nb WNES wwww\n# 0 0\n\n"
                + "w ESWN bbbb\nb WNES wwww\n# -1 0\n\n"
                + "w ESWN bbbb\nb WNES wwww\n# -1 1\n\n"
                + "w ESWN bbbb\nb WNES wwww\n# 0 1\n\n"
                + "w ESWN bbbb\nb WNES wwww\n# 0 0\n\n"
                + "w ESWN bbbb\nb WNES wwww\n# -1 0\n\n"
                + "w ESWN bbbb\nb WNES wwww\n# -1 -1\n\n"
                +"w ESWN bbbb\nb WNES wwww\n# -2 -1\n\n";
        assertEquals(expectedOut, Ant.move(steps,dnaList));
        System.out.println("2 states, 2 DNA, 14 steps passed ");
  
    }

    /**
     * Test of printScenario method, of class Ant.
     * This just tests if printScenario returns the right output when 
     * the parameters are certainly right.
     */
    @Test
    public void testPrintScenario() {
        ArrayList<String> dnaList = new ArrayList<>();
        dnaList.add("w ESWN bbbb");
        dnaList.add("b ESWN wwww");
        Tile currentTile1 = new Tile("w");
        currentTile1.setX(2);
        currentTile1.setY(3);
        String expResult = "w ESWN bbbb\nb ESWN wwww\n# " + currentTile1.getX()
                + " " + currentTile1.getY() + "\n\n";
        assertEquals(expResult, Ant.printScenario(dnaList, currentTile1));
      System.out.println("printScenario() test passed");

    }

}
