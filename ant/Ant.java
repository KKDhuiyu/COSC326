/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ant;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author hjia
 */
public class Ant {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<String> dnaList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int steps = 100;
        while (sc.hasNextLine()) {
            String dna = sc.nextLine();
            if ("".equals(dna)) {
                break;
            } else if (dna.substring(0, 1).equals("#")); else {
                dnaList.add(dna);
            }
        }

        move(steps, dnaList);

    }

    public static void move(int steps, ArrayList<String> dnaList) {
        String defaultState = dnaList.get(0).substring(0, 1);
        String lastMove = "N";
        Tile tile = new Tile(defaultState);
        System.out.println(tile.toString()); //for testing purpose
        for (int i = 0; i < steps; i++) {

            String dna = "";
            for (int j = 0; j < dnaList.size(); j++) {

                if (dnaList.get(j).substring(0, 1).equals(tile.getColor())) {
                    dna = dnaList.get(j);

                }
            }
            String directions = dna.substring(2, 6);
            String stateLeave = dna.substring(7, 11);
            // System.out.println(directions);
            int index = defineNextMove(lastMove, directions);

            String nextMove = directions.charAt(index) + "";

            String stateChange = stateLeave.charAt(index) + "";
            //  System.out.println(index+"!!!!!"+ nextMove);
            lastMove = nextMove;
            tile = new Tile(defaultState, tile);
            tile.getPrev().setColor(stateChange);
            System.out.println("heading: " + nextMove);
            switch (nextMove) {
                case "E":
                    tile.setX(tile.getX() + 1);
                    break;
                case "W":
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

    public static int defineNextMove(String lastMove, String directions) {

        if (lastMove.equals("N")) {
            return 0;
        } else if (lastMove.equals("E")) {
            return 1;
        } else if (lastMove.equals("S")) {
            return 2;
        } else {
            return 3;
        }

    }

    /* 
    The output for a scenario is to echo the input (except comments), followed
    by a line consisting of a # character, followed by a space, then the 
    x-coordinate of the ant’s final position, another space, and the
    y-coordinate of the ant’s final position. The output of each distinct
    scenario should be separated from the next by a single empty line. 
    An empty line contains no characters. The last line of the output 
    must not be followed by an empty line unless that line was echoed 
    from the input.
     */
    public static void printScenario(ArrayList<String> dnaList, Tile currentTile) {
        for (int i = 0; i < dnaList.size(); i++) {
            System.out.println(dnaList.get(i));
        }
        String coordinate = "# " + currentTile.getX() + " "
                + currentTile.getY() + " Color: " + currentTile.getColor();
        System.out.println(coordinate);
        System.out.println();
    }

    /**
     * private class DNA {
     *
     * private char currentState; private char[] directions; private char[]
     * stateLeave;
     *
     * public DNA(char currentState, char[] directions, char[] stateLeave) {
     * this.currentState = currentState; this.directions = directions;
     * this.stateLeave = stateLeave; }
     *
     * }
     */
}
