package comp1110.ass2;

import comp1110.ass2.gui.Game;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
class GameTest {
    @Test
    public void checkMoveToNextPlayer() throws Exception {
        String gameString = "Py02901iPp03001iA33NBn00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00";
        Game testGame = Game.stringToGame(gameString);
        Player player1 = testGame.getCurrentPlayer();
        testGame.moveToNextPlayer();
        Player player2 = testGame.getCurrentPlayer();
        assertNotEquals((player1.getColour()).equals(player2.getColour()),"colour is "+player1.getColour()+"is same as "+player2.getColour());
    }
    @Test
    public void checkmoveToNextPhase() throws Exception {
        String gameString = "Py02901iPp03001iA33NBn00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00";
        Game testGame = Game.stringToGame(gameString);
        int phase1 = testGame.getCurrentPhase();
        testGame.moveToNextPhase();
        int phase2 = testGame.getCurrentPhase();
        assertNotEquals((Integer.toString(phase1)).equals(Integer.toString(phase2)),"phase "+phase1+" should be different to "+phase2);
    }
    //todo tests for gameToString, stringToGame
    @Test
    public void checkgameToString() throws Exception {
        Square[][] boardMatrix = new Square[7][7];
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 7; y++) {
                boardMatrix[x][y] = new Square(new Pair<>(x,y),null);
            }
        }
        Board testBoard = new Board(boardMatrix);
        Assam testAssam = new Assam(90,new Pair<>(0, 1));
        Player testPlayer1 = new Player(Color.RED,10,13,Boolean.TRUE);
        Player testPlayer2 = new Player(Color.CYAN,9,5,Boolean.TRUE);
        Player[] players = {testPlayer1,testPlayer2};
        String gameString = "Pr01013iPc00905iA01EBn00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00";
        Game testGame = new Game(players,testBoard,testAssam);
        assertTrue((testGame.gameToString()).equals(gameString),"output "+testGame.gameToString()+" should equal "+gameString);
    }
    @Test
    public void checkStringToGame() throws Exception {
        Square[][] boardMatrix = new Square[7][7];
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 7; y++) {
                boardMatrix[x][y] = new Square(new Pair<>(x,y),null);
            }
        }
        boardMatrix[0][0] = new Square(new Pair<>(0,0), new Rug(Color.RED,1));
        Board testBoard = new Board(boardMatrix);
        Assam testAssam = new Assam(90,new Pair<>(0, 1));
        Player testPlayer1 = new Player(Color.RED,10,13,Boolean.TRUE);
        Player testPlayer2 = new Player(Color.CYAN,9,5,Boolean.TRUE);
        Player[] players = {testPlayer1,testPlayer2};
        String gameString = "Pr01013iPc00905iA01EBr01n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00";
        Game testGame = new Game(players,testBoard,testAssam);
        Game testGameFromString = Game.stringToGame(gameString);
        Assertions.assertEquals(testGame.getAssam().getAssamX(),testGameFromString.getAssam().getAssamX(),"assams do not match");
        Assertions.assertEquals(testGame.getAssam().getAssamY(),testGameFromString.getAssam().getAssamY(),"assams do not match");
        Assertions.assertEquals(testGame.getAssam().getAngle(),testGameFromString.getAssam().getAngle(),"assams do not match");
        Assertions.assertEquals(testGame.getBoard().getBoardMatrix()[0][0].occupiedRug.getColour(),testGameFromString.getBoard().getBoardMatrix()[0][0].occupiedRug.getColour(),"boards do not match");
    }
}