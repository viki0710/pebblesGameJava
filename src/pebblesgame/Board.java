/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pebblesgame;

import java.util.Random;

/**
 *
 * @author Balla Viktória
 */
public class Board {
    private Field[][] board;
    private final int boardSize;

    /**
     * constructor
     * @param boardSize - board 
     */
    public Board(int boardSize) {
        this.boardSize = boardSize;
        board = new Field[this.boardSize][this.boardSize];
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                board[i][j] = new Field();
            }
        }
    }
    
    /**
     * return tile at certain coordinate
     * @param x - x
     * @param y - y
     * @return board[x][y]
     */
    public Field getField(int x, int y) {
        return board[x][y];
    }
    
    /**
     * set field
     * @param x - x
     * @param y - y
     * @param f - field
     */
    public void setField(int x, int y, Field f) {
        this.board[x][y] = f;
    }

    /**
     * get board size
     * @return boardSize - size of board
     */
    public int getBoardSize() {
        return boardSize;
    }
    
    /**
     * Generates a set of pebbles of a certain colour.
     * Gives random x y coordinates, if empty, makes pebble.
     * Continues until pebble number equals board size.
     * @param colour - colour of pebble being generated
     */
    public void generatePebbles(int colour) {
        int i = 0;
        while (i < boardSize) {
            Random random = new Random();
            int x = random.nextInt(boardSize);   
            int y = random.nextInt(boardSize);   
            if (this.board[x][y].getPebble() == 0){
                this.board[x][y].setPebble(colour);
                i++;
            }
        }
    }
    
    /**
     * It counts the number of a given pebble
     * @param colour - colour of pebble being counted
     * @return c - number of pebbles
     */
    public int countPebbles(int colour) {
        int c = 0;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (this.board[i][j].getPebble() == colour) c++;
            }
        }
        return c;
    }
    
    /**
     * prints out the board on console
     */
    public void printBoard() {
        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++) {
                System.out.print(getField(x, y).getPebble());
            }
            System.out.print("\n");
        }
    }
 
    /**
     * Shifts the pebbles by one in the given direction
     * @param x - pebble x-coordinate
     * @param y - pebble y-coordinate
     * @param dir - direction for shifting
     */
    public void shiftPebbles(int x, int y, String dir) {
        int[] tempPebbles = new int[boardSize];
        System.out.println("Field accessed: " + board[x][y].getPebble());
        
        switch (dir) {
            case "UP" ->  {
                if (y == 0) {
                    System.out.println("Cannot shift up, on upper edge of board");
                } else {
                    System.out.println("tempPebbles:");
                    for (int i = 0; i < boardSize; i++) {
                        tempPebbles[i] = board[x][i].getPebble();
                        System.out.print(tempPebbles[i] + " ");
                    }
                    System.out.println();
                    
                    int zero = 0;
                    for (int i = 0; i < y; i++) {
                        if (tempPebbles[i] == 0) zero = i;
                    }
                    for (int i = zero; i < y; i++) {
                        tempPebbles[i] = tempPebbles[i+1];
                    }
                    tempPebbles[y] = 0;
                    
                    for (int i = 0; i < boardSize; i++) {
                        System.out.print(tempPebbles[i] + " ");
                        board[x][i].setPebble(tempPebbles[i]);
                    }
                    System.out.println();
                }
            }
            case "DOWN" ->  {
                if (y == boardSize-1) {
                    System.out.println("Cannot shift down, on bottom edge of board");
                } else {
                    System.out.println("tempPebbles:");
                    for (int i = 0; i < boardSize; i++) {
                        tempPebbles[i] = board[x][i].getPebble();
                        System.out.print(tempPebbles[i] + " ");
                    }
                    System.out.println();
                    
                    int zero = boardSize-1;
                    for (int i = boardSize-1; i > y; i--) {
                        if (tempPebbles[i] == 0) zero = i;
                    }
                    for (int i = zero; i > y; i--) {
                        tempPebbles[i] = tempPebbles[i-1];
                    }
                    tempPebbles[y] = 0;
                    
                    for (int i = 0; i < boardSize; i++) {
                        System.out.print(tempPebbles[i] + " ");
                        board[x][i].setPebble(tempPebbles[i]);
                    }
                    System.out.println();                    
                }
            }
            case "LEFT" ->  {
                if (x == 0) {
                    System.out.println("Cannot shift left, on left edge of board");
                } else {
                    System.out.println("tempPebbles:");
                    for (int i = 0; i < boardSize; i++) {
                        tempPebbles[i] = board[i][y].getPebble();
                        System.out.print(tempPebbles[i] + " ");
                    }
                    System.out.println();
                    
                    int zero = 0;
                    for (int i = 0; i < x; i++) {
                        if (tempPebbles[i] == 0) zero = i;
                    }
                    for (int i = zero; i < x; i++) {
                        tempPebbles[i] = tempPebbles[i+1];
                    }
                    tempPebbles[x] = 0;
                    
                    for (int i = 0; i < boardSize; i++) {
                        System.out.print(tempPebbles[i] + " ");
                        board[i][y].setPebble(tempPebbles[i]);
                    }
                    System.out.println();                    
                }
            }
            case "RIGHT" ->  {
                if (x == boardSize-1) {
                    System.out.println("Cannot shift right, on right edge of board");
                } else {
                    System.out.println("tempPebbles:");
                    for (int i = 0; i < boardSize; i++) {
                        tempPebbles[i] = board[i][y].getPebble();
                        System.out.print(tempPebbles[i] + " ");
                    }
                    System.out.println();
                    
                    int zero = boardSize-1;
                    for (int i = boardSize-1; i > x; i--) {
                        if (tempPebbles[i] == 0) zero = i;
                    }                    
                    for (int i = zero; i > x; i--) {
                        tempPebbles[i] = tempPebbles[i-1];
                    }
                    tempPebbles[x] = 0;
                    
                    for (int i = 0; i < boardSize; i++) {
                        System.out.print(tempPebbles[i] + " ");
                        board[i][y].setPebble(tempPebbles[i]);
                    }
                    System.out.println();                    
                }
            }
            default ->  {
            }
        }
    }
}
