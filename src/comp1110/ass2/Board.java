package comp1110.ass2;

import javafx.util.Pair;

import java.sql.SQLOutput;

import static comp1110.ass2.Rug.stringToAbbreviatedRug;
//import static comp1110.ass2.Rug.rugToString;

public class Board {
    public final static int BOARD_WIDTH = 7; // The width of the board (left to right)
    public final static int BOARD_HEIGHT = 7; // The height of the board (top to bottom)
    public Square[][] boardMatrix;

    public Board(Square[][] boardMatrix) {
        this.boardMatrix = boardMatrix;
    }

    void calcScore (Player player) {
        //calculates a player's score given a board
    }
    Player calcWinner (Player player[]) {
        //returns the winning player on a board
        return null;
    }

    public static Board stringToBoard(String inputString) throws Exception {
        Square[][] boardMatrix = new Square[7][7];
        int squareIndex = 1;
        for (int x = 0;x<BOARD_WIDTH;x++){
            for (int y = 0; y<BOARD_HEIGHT ; y++){
                if (x==0 | y==0 | x==6 | y==6){
                    boardMatrix[x][y] = new Square(true,new Pair<>(x,y),getRug(squareIndex, inputString));}
                else {
                    boardMatrix[x][y] = new Square(false,new Pair<>(x,y),getRug(squareIndex, inputString));
                    }
                squareIndex++;//index means each square has the rug from string
            }
            }
        return (new Board(boardMatrix));
    }

    public static Rug getRug(int n, String inputString) throws Exception {
        String rugString;
        rugString = inputString.substring((1 + 3*(n-1)),(4 + 3*(n-1)));
        return stringToAbbreviatedRug(rugString);
    }


//    /**
//     * for testing
//     * @param args
//     * @throws Exception
//     */
//    public static void main(String[] args) throws Exception{
//        System.out.println("stringToBoard test:");
//        String input1 = "Bn00p02c04r00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00";
//        String input2 = "Bn00p02c04r00n00";
//        System.out.println("String: " + input2);
//        System.out.println("Rug1 string: " + input2.substring(1,4));
//        Rug rug1 = getRug(1,input2);
//        System.out.println("Rug:" + rug1);
//        //issue is in stringToBoard() -> java.lang.StringIndexOutOfBoundsException: begin 148, end 151, length 148
//
//
//        Board board = stringToBoard(input1);
//        System.out.println(board.boardMatrix[6][6].position);
//    }



    }



