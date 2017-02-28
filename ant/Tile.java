/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ant;

/**
 *
 * @author hjia
 */
public class Tile {

    private int x, y;
    private String color;
    private Tile previousTile;

    public Tile(String color) {
        this.color = color;
        this.x = 0;
        this.y = 0;
        this.previousTile = null;
    }

    public Tile(String color, Tile prev) {
        this.color = prev.getColor();
        this.x = prev.getX();
        this.y = prev.getY();
        this.previousTile = prev;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String getColor() {
        return this.color;
    }

    public Tile getPrev() {
        return this.previousTile;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        // debug purpose
        return "(" + getX() + "," + getY() + ")  state:" + getColor()/*+"prev: "
                  +getPrev().toString()*/;
    }
}
