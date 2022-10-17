/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pebblesgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;            
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

/**
 *
 * @author Balla Viktória
 */
public class BoardGUI {
    
    private Board board;
    private JButton[][] buttons;
    private JPanel boardPanel;
    private JLabel turnLabel;
    private int turn = 1;
    private boolean waitForSignal = false;
    private String dir = "";
    
    private int xCoord, yCoord;

    private Random random = new Random();
    private int maxClickNum;

    /**
     * constructor
     * @param boardSize - size of board
     * @param maxClicks - max number of clicks
     */
    public BoardGUI(int boardSize, int maxClicks) {
        maxClickNum = maxClicks;
        board = new Board(boardSize);
        board.generatePebbles(1);
        board.generatePebbles(2);
        System.out.println("number of black pebbles: " + board.countPebbles(1));
        System.out.println("number of white pebbles: " + board.countPebbles(2));
        board.printBoard();
        turn = 1;
        
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(board.getBoardSize(), board.getBoardSize()));
        buttons = new JButton[board.getBoardSize()][board.getBoardSize()];
        
        for (int i = 0; i < board.getBoardSize(); i++) {
            for (int j = 0; j < board.getBoardSize(); j++) {
                JButton button = new JButton();
                button.addActionListener(new ButtonListener(i, j));
                button.addKeyListener(new CustomKeyListener());
                button.setPreferredSize(new Dimension(80, 80));
                button.setBorder(new LineBorder(Color.BLACK));
                buttons[i][j] = button;
                if (board.getField(j, i).getPebble() == 0 || board.getField(j, i).getPebble() == 2) {
                    button.setEnabled(false);                    
                }
                if (board.getField(j, i).getPebble() != 0) {
                    button.setText("" + board.getField(j, i).getPebble());                 
                }

                boardPanel.add(button);
                
                turnLabel = new JLabel("Current turn: 1");
                turnLabel.setHorizontalAlignment(JLabel.RIGHT);
                turnLabel.setText("Current turn: " + turn + ", remaining clicks: " + maxClickNum);
            }
        }
    }

    /**
     * get turn label
     * @return turnLabel - turn label
     */
    public JLabel getTurnLabel() {
        return turnLabel;
    }

    /**
     * get board panel
     * @return boardPanel - board panel
     */
    public JPanel getBoardPanel() {
        return boardPanel;
    }
    
    /**
     * draws red frame over nearby tiles
     * @param x - x
     * @param y - y
     */
    public void getNearby(int x, int y) {
        buttons[x][y].setBorder(new LineBorder(Color.RED));
        if (x > 0) buttons[x-1][y].setBorder(new LineBorder(Color.RED));
        if (y > 0) buttons[x][y-1].setBorder(new LineBorder(Color.RED));
        if (x < buttons.length-1) buttons[x+1][y].setBorder(new LineBorder(Color.RED));
        if (y < buttons.length-1) buttons[x][y+1].setBorder(new LineBorder(Color.RED));
        waitForSignal = true;
    }

    /**
     * resets selected borders
     */
    public void resetBorders() {
        for (int i = 0; i < board.getBoardSize(); i++) {
            for (int j = 0; j < board.getBoardSize(); j++) {
                JButton button = buttons[i][j];
                button.setBorder(new LineBorder(Color.BLACK));
                boardPanel.add(button);               
            }
        }
    }
    
    /**
     * Changes turns, refreshes table
     */
    public void refresh() {
        if (turn == 1) {
            turn = 2;
        } else turn = 1;
        
        maxClickNum--;
        if (maxClickNum == 0) {
            int w = getWinner();
            JFrame jFrame = new JFrame();
            JOptionPane.showMessageDialog(jFrame, "The winner is player " + w);
        }
        System.out.println("Current turn: " + turn + ", remaining clicks: " + maxClickNum);
        turnLabel.setText("Current turn: " + turn + ", remaining clicks: " + maxClickNum);
        
        for (int i = 0; i < board.getBoardSize(); i++) {
            for (int j = 0; j < board.getBoardSize(); j++) {
                JButton button = buttons[i][j];
                if (board.getField(j, i).getPebble() != turn) {
                    button.setEnabled(false);
                } else button.setEnabled(true);
                if (board.getField(j, i).getPebble() != 0) {
                    button.setText("" + board.getField(j, i).getPebble());                 
                } else button.setText("");

                boardPanel.add(button);          
            }
        }
    }

    /**
     * buttonListener
     */
    class ButtonListener implements ActionListener {
        private int x, y;

        public ButtonListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {            
            xCoord = this.x;
            yCoord = this.y;
            System.out.println("coordinates: " + y + ";" + x);
            resetBorders();
            getNearby(x, y);
            System.out.println("accessed tile: " + board.getField(y, x).getPebble());
        }
    }
    
    /**
     * calls pebble shifting method
     */
    public void shifter() {
        if (waitForSignal == true) board.shiftPebbles(yCoord, xCoord, dir);
    }
    
    /**
     * gets game winner
     * @return win - number of winner
     */
    public int getWinner() {
        int blackPebbles = board.countPebbles(1);
        int whitePebbles = board.countPebbles(2);
        int win = 0;
        if (blackPebbles == whitePebbles) {
            System.out.println("draw!");
            win = 0;
        } else if (blackPebbles > whitePebbles) {
            System.out.println("black won!");
            win = 1;
        } else {
            System.out.println("white won!");
            win = 2;
        }
        return win;
    }
    
    /**
     * keyListener
     */
    class CustomKeyListener implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e){                                          
            int key = e.getKeyCode();                                                
            switch (key) {
                case KeyEvent.VK_RIGHT -> {
                    if (waitForSignal == true) {
                        System.out.println("RIGHT");
                        dir = "RIGHT";
                        resetBorders();
                        shifter();
                        waitForSignal = false;
                        refresh();
                    }
                }
                case KeyEvent.VK_LEFT -> {
                    if (waitForSignal == true) {
                        System.out.println("LEFT");
                        dir = "LEFT";
                        resetBorders();
                        shifter();
                        waitForSignal = false;
                        refresh();
                    }
                }
                case KeyEvent.VK_UP -> {
                    if (waitForSignal == true) {
                        System.out.println("UP");
                        dir = "UP";
                        resetBorders();
                        shifter();
                        waitForSignal = false;
                        refresh();
                    }
                }
                case KeyEvent.VK_DOWN -> {
                    if (waitForSignal == true) {
                        System.out.println("DOWN");
                        dir = "DOWN";
                        resetBorders();
                        shifter();
                        waitForSignal = false;
                        refresh();
                    }
                }
                default -> {
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
            int key = e.getKeyCode();                                                
            switch (key) {
                case KeyEvent.VK_RIGHT -> {
                    if (waitForSignal == true) {
                        System.out.println("RIGHT");
                        dir = "RIGHT";
                        resetBorders();
                        shifter();
                        waitForSignal = false;
                        refresh();
                    }
                }
                case KeyEvent.VK_LEFT -> {
                    if (waitForSignal == true) {
                        System.out.println("LEFT");
                        dir = "LEFT";
                        resetBorders();
                        shifter();
                        waitForSignal = false;
                        refresh();
                    }
                }
                case KeyEvent.VK_UP -> {
                    if (waitForSignal == true) {
                        System.out.println("UP");
                        dir = "UP";
                        resetBorders();
                        shifter();
                        waitForSignal = false;
                        refresh();
                    }
                }
                case KeyEvent.VK_DOWN -> {
                    if (waitForSignal == true) {
                        System.out.println("DOWNS");
                        dir = "DOWN";
                        resetBorders();
                        shifter();
                        waitForSignal = false;
                        refresh();
                    }
                }
                default -> {
                }
            }
        }
    }
}