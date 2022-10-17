/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pebblesgame;

/**
 *
 * @author Balla Viktória
 */
public class Field {
    private int pebble;

    /**
     * constructor
     */
    public Field() {
        pebble = 0;
    }

    /**
     * gets pebble
     * @return pebble - pebble
     */
    public int getPebble() {
        return pebble;
    }

    /**
     * 
     * @param p - bebble colour
     */
    public void setPebble(int p) {
        this.pebble = p;
    }
}
