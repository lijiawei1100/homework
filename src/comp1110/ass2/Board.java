package comp1110.ass2;
import javafx.util.Pair;
import static comp1110.ass2.Player.getColorName;
import static comp1110.ass2.Rug.stringToAbbreviatedRug;

public class Board {
    /**
     * AUTHORSHIP:
     *
     * initialize the board_width and the board_height
     * use a binary array to store squares
     *
     * the class was written by both group members:
     * stringToBoard and getRug were written by Benjamin Campbell
     * boardToString was written by Jiawei Li
     * we both checked and debugged each other's code
     *
     * @author <u7531534><Jiawei Li>/ <u7471333><Benjamin Campbell>
     */
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

    public static Board stringToBoard(String inputString)  {
        Square[][] boardMatrix = new Square[7][7];
        int squareIndex = 1;
        for (int x = 0;x<BOARD_WIDTH;x++){
            for (int y = 0; y<BOARD_HEIGHT ; y++){
                boardMatrix[x][y] = new Square(new Pair<>(x,y),getRug(squareIndex, inputString));
                squareIndex++;//index means each square has the rug from string
            }
        }
        return new Board(boardMatrix);
    }

    //to return a abbreviated rug in each iteration when building squares on the board.
    public static Rug getRug(int n, String inputString){
        String rugString;
        rugString = inputString.substring((1 + 3*(n-1)),(4 + 3*(n-1)));
        return stringToAbbreviatedRug(rugString);
    }

    //input a board and output a string
    public static String boardToString(Board board) {
        String boardString = "B";
        for (int x = 0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_HEIGHT; y++) {
                Square square = board.getBoardMatrix()[x][y];
                String occupiedRugString = "";
                //for each sqaure, if the occupiedRug is null, then return "n00", else return the abbreviated rug.
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
}