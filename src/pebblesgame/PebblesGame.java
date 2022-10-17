/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pebblesgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Balla Viktória
 */
public class PebblesGame {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException - IO exception
     */
    public static void main(String[] args) throws IOException {
        gameGUI gui = new gameGUI();
        BufferedReader reader = new BufferedReader(
        new InputStreamReader(System.in));
        
        /*Board b = new Board(4);
        b.generatePebbles(1);
        b.generatePebbles(2);
        
        while (true) {
            System.out.println("********************************************");
            b.printBoard();
            
            System.out.println("x:");
            int x = Integer.parseInt(reader.readLine());
            System.out.println("y:");
            int y = Integer.parseInt(reader.readLine());
            System.out.println("direction:");
            String dir = reader.readLine();
            System.out.println("You entered x: " + x + " y: " + y + " dir: " + dir);
            
            b.shiftPebbles(x, y, dir);
        }*/
    }
    
}
