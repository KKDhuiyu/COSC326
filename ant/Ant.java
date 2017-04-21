/*
 * This is the main class for Langton's Ant.
 * The ant will take a certain sequence of steps of unit length in one of
 * the four compass directions determined by various rules (i.e. its “DNA”).
 * These rules specify the direction of the next step based on the previous
 * step,  and the state of the ant’s current position—they may also
 * specify a change in that state.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class will take inputs from STDIN as the ant's DNA. Then output the
 * moves that made by the ant in certain steps.
 *
 * @author Huiyu Jia, Jason Zhao.
 */
public class Ant {

    /**
     * @param args the command line arguments.
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // make an array list to store inputs
        BufferedReader bufferedReader;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> dnaList = new ArrayList<>();
        ArrayList<String> outputs = new ArrayList<>();
       
        // the steps the ant will move
        int steps = 20;

        String input;
        while ((input = bufferedReader.readLine()) != null) {
            if ("".equals(input)) {
                break;
            } else if (input.substring(0, 1).equals("#")); else {
                try {
                    if (Integer.valueOf(input) > 0) {
                        steps = Integer.valueOf(input);
                    }
                } catch (NumberFormatException e) {
                    dnaList.add(input);
                }

            }
            while ((input = bufferedReader.readLine()) != null) {

                if ("".equals(input)) {
                    break;
                } else if (input.substring(0, 1).equals("#")); else {
                    try {
                        if (Integer.valueOf(input) > 0) {
                            steps = Integer.valueOf(input);
                        }
                    } catch (NumberFormatException e) {
                        dnaList.add(input);
                    }

                }

            }
            if (!dnaList.isEmpty()) {
                
                outputs.add(move(steps, dnaList));
                dnaList.clear();
            }

        }
        for (int i = 0; i < outputs.size(); i++) {
            if (i != outputs.size() - 1) {
                System.out.print(outputs.get(i) + "\n");
            } else {
                System.out.print(outputs.get(i));
            }
        }

    }

    /**
     * This is a method that makes the ant "move" around the "tiles".
     *
     * @param steps the number of steps you are to follow the ant for.
     * @param dnaList the ArrayList to store the ants DNA.
     */
    public static String move(int steps, ArrayList<String> dnaList) {
         HashMap<String, String> map = new HashMap<>();  
        String result = "";
        //the first line of the DNA determines the default state
        String defaultState = dnaList.get(0).substring(0, 1);
        //this is actually the default direction the ant faces at (0,0).
        String lastMove = "N";
        Tile tile = new Tile(defaultState);
        map.put(tile.coordinator(),defaultState);
        for (int i = 0; i < steps; i++) {

            String dna = "";
            for (int j = 0; j < dnaList.size(); j++) {
                // get the coordinated DNA
                if (dnaList.get(j).substring(0, 1).equals(tile.getColor())) {
                    dna = dnaList.get(j);

                }
            }

            if (dna.equals("")) { // state not found.
                System.out.println("Error!");
                System.out.println("The states aren't consistent. The ant has"
                        + " crashed.");
                break;
            } else {
                String directions = dna.substring(2, 6);
                String stateLeft = dna.substring(7, 11);
                // call method to determine the index of the next direction
                int index = defineNextMove(lastMove, directions);
                String nextMove = directions.charAt(index) + "";
                String stateChange = stateLeft.charAt(index) + "";
                lastMove = nextMove; // tracking the directions
                
                tile = new Tile(defaultState, tile);
                // chage the color when leaving
                map.put(tile.getPrev().coordinator(),stateChange);
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
                if(map.containsKey(tile.coordinator())){
                    tile.setColor(map.get(tile.coordinator()));
                }
                if (i == steps - 1) {
                    result += printScenario(dnaList, tile, steps);
                    //prints only the last scenario.
                }

            }
        }
        return result;
    }

    /**
     * This method defines the next index in the string "directions" we should
     * look for.
     *
     * @param lastMove the last direction the ant faced.
     * @param directions the four compasses extracted from the DNA.
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
            case "W":
                return 3;
            default:
                return 0;
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
    public static String
       printScenario(ArrayList<String> dnaList, Tile currentTile, int steps) {
        String result = "";
        for (int i = 0; i < dnaList.size(); i++) {
            result += dnaList.get(i) + "\n";
        }
        result += "" + steps + "\n";
        String coordinate = "# " + currentTile.getX() + " "
                + currentTile.getY();
        result += coordinate + "\n";
        return result;
    }
}
