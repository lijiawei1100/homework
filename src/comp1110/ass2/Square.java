package comp1110.ass2;
import comp1110.ass2.gui.Game;
import javafx.util.Pair;

import java.util.List;

import static comp1110.ass2.Marrakech.getAdjacentSquares;
import static comp1110.ass2.Marrakech.getPaymentAmount;
import static comp1110.ass2.Player.getColorName;

/**
 * AUTHORSHIP:
 * code was written by both group members
 */

public class Square {
    private final Boolean isEdge;

    public Pair<Integer,Integer> position;
    public Rug occupiedRug;

    public Square(Boolean isEdge,Pair<Integer,Integer> position,Rug occupiedRug){
        this.isEdge = isEdge;
        this.position = position;
        this.occupiedRug = occupiedRug;
    }

    public Boolean isSquareValid(Square square){
        if(square.position.getKey() ==0 | square.position.getKey()==6 |square.position.getValue()==0|square.position.getValue()==6){
            if(square.isEdge.equals(true)) return true;
        }
        return false;
    }

    public void setOccupiedRug(Rug.RugWithPosition rug) {
        this.occupiedRug = rug;
    };


    public Boolean getIsEdge(){
        return isEdge;
    }


    //test for task 11
//    public static void main(String[] args) {
//        //'4'
//        String gameString = "Pc02612iPy03013iPp03213iPr02813iA15NBn00n00n00n00n00n00n00n00r01c01c02c02r00r00n00r01n00n00n00r02p01n00n00n00n00y01r02p01n00n00n00n00n00y00y00n00n00n00c00n00n00n00n00n00n00c00n00n00n00";
//        Game game = Game.stringToGame(gameString);
//        Assam assam = game.getAssam();
//        Board board = game.getBoard();
//        Square currentSquare = board.getBoardMatrix()[assam.getAssamX()][assam.getAssamY()];
//        List<Square> squareList = getAdjacentSquares(gameString,currentSquare);
//        for(Square i:squareList){
//            System.out.println(String.valueOf(i.position.getKey())+String.valueOf(i.position.getValue()));
//        }
//        System.out.println(getPaymentAmount(gameString));
//    }
}
