/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ant;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hjia
 */
public class TileTest {

    public TileTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getPrev method, of class Tile.
     */
    @Test
    public void testGetPrev() {
        Tile instance1 = new Tile("w");
        Tile instance2 = new Tile("b",instance1);
        String expResult = instance1.getColor();
        String result = instance2.getPrev().getColor();
        assertEquals(expResult, result);
        System.out.println("LinkedList-like data structure works");
    }
}
