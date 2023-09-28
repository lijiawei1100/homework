package comp1110.ass2;

import gittest.B;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
class BoardTest {

    @Test
    public void checkBoardSize() throws Exception{
        Board board = Board.stringToBoard("By02n00n00y07y07c07r06y02n00r02n00n00n00r06n00y05r07n00n00n00n00y00c00y08n00y06n00n00y00c00y08y04y06p06n00n00p04r03y04n00p06y03n00p04n00n00n00n00y03");
        assertEquals(board.getBoardMatrix()[0].length,7,"the length of the column size is wrong, now is: "+board.getBoardMatrix()[0].length+" expected is 7");
        assertEquals(board.getBoardMatrix().length,7,"the length of the row size is wrong now is "+board.getBoardMatrix().length+ "expected is 7");
    }
    @Test
    public  void checkSquaresOnBoard() throws Exception {
        String inputString = "By02n00n00y07y07c07r06y02n00r02n00n00n00r06n00y05r07n00n00n00n00y00c00y08n00y06n00n00y00c00y08y04y06p06n00n00p04r03y04n00p06y03n00p04n00n00n00n00y03";
        Board board = Board.stringToBoard(inputString);
        for(int i=0;i<board.getBoardMatrix().length;i++){
            for(int j=0;j<board.getBoardMatrix().length;j++){
                assertTrue((board.getBoardMatrix()[i][j] instanceof Square),"boardMatrix["+i+"]["+j+"] is not a square");
            }
        }

    }
    @Test
    public void checkOccupiedRugsOnBoard() throws Exception {
        String inputString = "By02n00n00y07y07c07r06y02n00r02n00n00n00r06n00y05r07n00n00n00n00y00c00y08n00y06n00n00y00c00y08y04y06p06n00n00p04r03y04n00p06y03n00p04n00n00n00n00y03";
        Board board = Board.stringToBoard(inputString);
        for(int i=0;i<board.getBoardMatrix().length;i++){
            for(int j=0;j<board.getBoardMatrix().length;j++){
                String colourChar = "n";
                String idString = "00";
                if(board.getBoardMatrix()[i][j].occupiedRug != null) {
                    Color colour = board.getBoardMatrix()[i][j].occupiedRug.getColour();
                    if (colour.equals(Color.CYAN)) {
                        colourChar = "c";
                    } else if (colour.equals(Color.YELLOW) ) {
                        colourChar = "y";
                    } else if (colour.equals(Color.RED) ) {
                        colourChar = "r";
                    } else if (colour.equals(Color.PURPLE) ) {
                        colourChar = "p";
                    }
                    idString = String.valueOf(board.getBoardMatrix()[i][j].occupiedRug.getId());
                }
                String subString = inputString.substring(1+3*j+21*i,4+3*j+21*i);
                if(idString.length()==1){
                    idString = "0" + idString;
                }

                assertTrue(((colourChar+idString).equals(subString)),"substring is "+subString+" test string is "+colourChar+idString);

    }}}

}