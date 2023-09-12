package comp1110.ass2;

import javafx.util.Pair;

import java.sql.SQLOutput;

import static comp1110.ass2.Rug.RUG_01;
import static comp1110.ass2.Rug.rugToString;

public class Board {
    // The width of the board (left to right)
    public final static int BOARD_WIDTH = 7;
    // The height of the board (top to bottom)
    public final static int BOARD_HEIGHT = 7;

    public static Square[][] boardMatrix;



    private int length;
    private int width;
    private Square square[];
    private Rug rugs[];

    /**
     * initialize a 7x7 matrix, and populate it.
     */
    public Board() {
        boardMatrix = new Square[BOARD_HEIGHT][BOARD_WIDTH];
        populateBoard();
    }

    void calcScore (Player player) {
        //calculates a player's score given a board
    }
    Player calcWinner (Player player[]) {
        //returns the winning player on a board
        return null;
    }

    /**
     * populate the Board, if the square is on edge,then isEdge is true;Since it's starting state, the occupiedRug should be "n00";
     */
    public static void populateBoard(){
        for (int x = 0;x<BOARD_WIDTH;x++){
            for (int y = 0; y<BOARD_HEIGHT ; y++){
                if (x==0 | y==0 | x==6 | y==6){
                    boardMatrix[x][y] = new Square(true,new Pair<>(x,y),"n00");}
                else {
                    boardMatrix[x][y] = new Square(false,new Pair<>(x,y),"n00");
                    }
                }
            }
        }
        
    /**
     *
     * @param board the whole board consisting with 49 squares.
     * @return: return a string to illustrate the state of the board.
     */
    public static String boardToString(Board board) {
        String boardString = "B";
        if (boardMatrix!=null){
        for (int x = 0;x<BOARD_WIDTH;x++){
            for (int y = 0; y<BOARD_HEIGHT ; y++){
                boardString = boardString + boardMatrix[x][y].getOccupiedRug();}
            }
        }
        return boardString;
    }
    
    /**
     * @param rug : created rugs
     * @return :the first three chars in the rugToString, which is the abbeiviated information of rugs
     */
    static String getAbbeiviatedRug(Rug rug){
        return rugToString(rug).substring(0,3);
    }
    /**
     *
     * @param args
     * output the board String at the end, which is consisting with 47 abbreviated rugs strings
     */
    public static void main(String[] args) {
        Board board = new Board();
        System.out.println(getAbbeiviatedRug(RUG_01));
        System.out.println(boardToString(board));
    }

}

