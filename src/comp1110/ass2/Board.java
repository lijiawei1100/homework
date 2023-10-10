package comp1110.ass2;

import comp1110.ass2.gui.Game;
import gittest.B;
import javafx.util.Pair;

import static comp1110.ass2.Player.getColorName;
import static comp1110.ass2.Rug.stringToAbbreviatedRug;

public class Board {
    public final static int BOARD_WIDTH = 7; // The width of the board (left to right)
    public final static int BOARD_HEIGHT = 7; // The height of the board (top to bottom)
    private
    Square[][] boardMatrix;

    public Board(Square[][] boardMatrix) {
        this.boardMatrix = boardMatrix;
    }

    public Square[][] getBoardMatrix() {
        return this.boardMatrix;
    }

    void calcScore (Player player) {
        //calculates a player's score given a board
    }
    Player calcWinner (Player player[]) {
        //returns the winning player on a board
        return null;
    }

    public static Board stringToBoard(String inputString)  {
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

    public static Rug getRug(int n, String inputString){
        String rugString;
        rugString = inputString.substring((1 + 3*(n-1)),(4 + 3*(n-1)));
        return stringToAbbreviatedRug(rugString);
    }

    public static String boardToString(Board board) {
        String boardString = "B";
        for (int x = 0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_HEIGHT; y++) {
                Square square = board.getBoardMatrix()[x][y];
                String occupiedRugString = "";
                if (square.occupiedRug == null) {
                    occupiedRugString = "n00";
                } else {

                    if (square.occupiedRug.getId() < 10) {
                        occupiedRugString = getColorName(square.occupiedRug.getColour()).toLowerCase().substring(0, 1) + "0" + String.valueOf(square.occupiedRug.getId());
                    } else {
                        occupiedRugString = getColorName(square.occupiedRug.getColour()).toLowerCase().substring(0, 1) + String.valueOf(square.occupiedRug.getId());
                    }
                }
                boardString += occupiedRugString;
            }
        }
        return boardString;
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
//        String input3 = "Py04706iPp00406iPr02806iA33NBy11y11p14p14y07c07y01r00c11c11p16y17y17y10p17y19r11c01c01n00n00p17y19c15n00r17r13n00r06c13r05r05r17r13y04y18y20n00n00c02r16r08y18y20y02y02c09r16r08";
//        Game game = Game.stringToGame(input3);
//        System.out.println("String: " + input2);
//        System.out.println("Rug1 string: " + input2.substring(1,4));
//        Rug rug1 = getRug(1,input2);
//        System.out.println("Rug:" + rug1);
//        //issue is in stringToBoard() -> java.lang.StringIndexOutOfBoundsException: begin 148, end 151, length 148
//        Board board = stringToBoard(input1);
//        System.out.println(board.boardMatrix[6][6].position);
//        System.out.println(boardToString(board));
//        System.out.println(game.gameToString(game));
//
//
//    }

    }

