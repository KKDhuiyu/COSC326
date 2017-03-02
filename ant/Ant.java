/*
 * This is the main class for Langton's Ant. 
 * The ant will take a certain sequence of steps of unit length in one of 
 * the four compass directions determined by various rules (i.e., its “DNA”). 
 * These rules specify the direction of the next step based on the previous 
 * step,  and the state of the ant’s current position—they may also 
 * specify a change in that state.
 */
package ant;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class will take inputs from STDIN as the ant's DNA. Then output the.
 * moves that made by the ant in certain steps.
 *
 * @author Huiyu Jia, Jason Zhao.
 */
public class Ant {

    /**
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        // make an array list to store inputs
        ArrayList<String> dnaList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        // the steps the ant will move
        int steps = 100;
        while (sc.hasNextLine()) { // take inputs from user till "" is inputted.
            String dna = sc.nextLine();
            if ("".equals(dna)) {
                break;
            } else if (dna.substring(0, 1).equals("#")); else {
                dnaList.add(dna);
            }
        }

        move(steps, dnaList); // call move method when finished collecting DNA.

    }

    /**
     * This is a method that makes the ant "moves" around "tiles".
     *
     * @param steps the number of steps you are to follow the ant for.
     * @param dnaList
     */
    public static void move(int steps, ArrayList<String> dnaList) {
        //the first line of the DNA determines the default state
        String defaultState = dnaList.get(0).substring(0, 1);
        //this is actually the default direction the ant faces at (0,0).
        String lastMove = "N";
        Tile tile = new Tile(defaultState);
        //  System.out.println(tile.toString()); //for testing purpose
        for (int i = 0; i < steps; i++) {

            String dna = "";
            for (int j = 0; j < dnaList.size(); j++) {
// get the coordinated DNA
                if (dnaList.get(j).substring(0, 1).equals(tile.getColor())) {
                    dna = dnaList.get(j);

                }
            }
            if (dna.equals("")) { // state not found.
                System.out.println("Error !!!!!!!!!!!!!!!!!!");
                System.out.println("The states are't consistent. The ant is"
                        + " crashed.");
                break;
            } else {
                String directions = dna.substring(2, 6);
                String stateLeft = dna.substring(7, 11);
                // System.out.println(directions);
                // call method to determine the index of the next direction
                int index = defineNextMove(lastMove, directions);

                String nextMove = directions.charAt(index) + "";

                String stateChange = stateLeft.charAt(index) + "";
                //  System.out.println(index+"!!!!!"+ nextMove);
                lastMove = nextMove; // tracking the directions
                // linked list implementation 
                tile = new Tile(defaultState, tile);
                // chage the color when leaving 
                tile.getPrev().setColor(stateChange);
                // System.out.println("heading: " + nextMove);
                switch (nextMove) {
                    case "E": // go east
                        tile.setX(tile.getX() + 1);
                        break;
                    case "W": // go west 
                        tile.setX(tile.getX() - 1);
                        break;
                    case "N":
                        tile.setY(tile.getY() + 1);
                        break;
                    case "S":
                        tile.setY(tile.getY() - 1);
                        break;
                    default:
                        break;
                }
                // check if this coordinate has been visited before.
                // if so, reset its state to what is used to be. 
                Tile previousTile = tile.getPrev();
                while (previousTile != null) {
                    if (tile.getX() == previousTile.getX()
                            && tile.getY() == previousTile.getY()) {
                        tile.setColor(previousTile.getColor());
                        break;
                    }
                    previousTile = previousTile.getPrev();
                } // now the ant doesn't run in a circle like an idiot.

                // now the ant is moving.
                printScenario(dnaList, tile);
                //now the Scenario is printed 
            }
        }
    }

    /**
     * This method defines the next index in the string "directions" we should
     * look for.
     *
     * @param lastMove the last direction the ant faced
     * @param directions the four compasses extracted from the DNA
     * @return the index.
     */
    public static int defineNextMove(String lastMove, String directions) {

        switch (lastMove) {
            case "N":
                return 0;
            case "E":
                return 1;
            case "S":
                return 2;
            default:
                return 3;
        }

    }

    /**
     * The output for a scenario is to echo the input (except comments),
     * followed by a line consisting of a # character, followed by a space, then
     * the x-coordinate of the ant’s final position, another space, and the
     * y-coordinate of the ant’s final position. The output of each distinct
     * scenario should be separated from the next by a single empty line. An
     * empty line contains no characters. The last line of the output must not
     * be followed by an empty line unless that line was echoed from the input.
     *
     * @param dnaList the user's input
     * @param currentTile the tile the ant is on.
     */
    public static void printScenario(ArrayList<String> dnaList, Tile currentTile) {
        for (int i = 0; i < dnaList.size(); i++) {
            System.out.println(dnaList.get(i));
        }
        String coordinate = "# " + currentTile.getX() + " "
                + currentTile.getY();
        System.out.println(coordinate);
        System.out.println();
    }
}
