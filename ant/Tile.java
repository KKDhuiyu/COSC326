/*
 * This Class provides the data structure that works like a LinkedList.
 * so that we can track back to the previous tiles to see if the coordinate 
 * has been visited before.
 */
package ant;

/**
 * This Class provides a data structure like a LinkedList that stores.
 * information about each tile the ant has passed.
 *
 * @author Huiyu Jia, Jason Zhao
 */
public class Tile {

    private int x, y; // coordinate (x,y).
    private String color; // the state of the tile.
    private Tile previousTile; // LinkedList structure.

    /**
     * This is a constructor that initializes the data field and set the.
     * previous Tile to null.
     *
     * @param color the default color that is taken from the first line of the
     * DNA list.
     */
    public Tile(String color) {
        this.color = color;
        this.x = 0;
        this.y = 0;
        this.previousTile = null;
    }

    /**
     * This is a constructor that initializes a Tile based on its previous tile.
     * and the color passed as a parameter.
     *
     * @param color that is from the related DNA lines.
     * @param prev its previous tile.
     */
    public Tile(String color, Tile prev) {
        this.color = prev.getColor();
        this.x = prev.getX();
        this.y = prev.getY();
        this.previousTile = prev;
    }

    /**
     * Get the x coordinate from the tile object.
     *
     * @return its x coordinate.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Get the y coordinate from the tile object.
     *
     * @return its y coordinate.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Get the state from the tile object.
     *
     * @return its current state.
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Get its previous tile from the tile object.
     *
     * @return its previous tile.
     */
    public Tile getPrev() {
        return this.previousTile;
    }

    /**
     * Set the X in the data field to the parameter x.
     *
     * @param x the new value of the X in the data field
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set the y in the data field to the parameter y.
     *
     * @param y the new value of the y in the data field
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Set the color in the data field to the parameter color.
     *
     * @param color the new value of the color in the data field
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * This is a override toString method for testing purpose.
     *
     * @return testing outputs.
     */
    @Override
    public String toString() {
        // debug purpose
        return "(" + getX() + "," + getY() + ")  state:" + getColor()/*+"prev: "
                  +getPrev().toString()*/;
    }
}
