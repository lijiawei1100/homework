package comp1110.ass2.gui;

import comp1110.ass2.Assam;
import comp1110.ass2.Board;
import comp1110.ass2.Player;
import gittest.C;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.text.View;
import java.sql.SQLOutput;
import java.util.*;

import static comp1110.ass2.Assam.assamToString;
import static comp1110.ass2.Board.boardToString;
import static comp1110.ass2.Marrakech.isGameOver;
import static comp1110.ass2.Player.getColorName;
import static comp1110.ass2.Player.playerToString;
import static javafx.stage.Stage.*;

public class Game extends Application {
    /**
     * AUTHORSHIP:
     *
     * use a unary array to store each player
     * use gamePhase to control the ongoing phrase
     * use rugPlaceIsHorizontal to determine whether the player is placing a vertical rug or a horizontal rug
     *
     * moveToNextPlayer and moveToNextPhrase were written by Benjamin Campbell
     * stringToGame and gameToString were written by Jiawei Li
     *
     * @author <u7531534><Jiawei Li>/ <u7471333><Benjamin Campbell>
     */
    private Group root = new Group();
    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 700;

    Player[] players;
    Board board;
    Assam assam;
    Player currentPlayer;
    Player playerPaid;
    int playerPaidIndex;
    int currentPlayerIndex;
    int gamePhase;
    int currentDiceRoll;
    int currentPaymentAmount;
    int actualPaymentAmount;
    boolean playerDoPay;
    boolean skip;
    Boolean rugPlaceIsHorizontal = Boolean.TRUE;
//    HBox rugHbox = Viewer.buildImage(0);

    public Game() {};
    public Game(Player[] players,Board board,Assam assam){
        this.players=players;
        this.currentPlayer = players[0];
        this.currentPlayerIndex = 0;
        this.gamePhase = 0;
        this.board=board;
        this.assam=assam;
        this.playerDoPay =true;
    }

    //switch to the next player
    public void moveToNextPlayer() {
        int nPlayers = 0;
        for(Player i:players) {
            //skip the null and generate the rest players
            if (i != null) {
                nPlayers++;
            }
        }
        currentPlayerIndex = (currentPlayerIndex + 1) % nPlayers;
        currentPlayer = players[currentPlayerIndex];
        if(!currentPlayer.getIsPlaying()) moveToNextPlayer();
    }

    public void moveToNextPhase() {
        //there are a total of three game phrase.Each time after finishing a stage operation will automatically
        // go to the next stage, when the stage more than 3, automatically switch to the next player
        gamePhase = (gamePhase + 1) % 4;
        if (gamePhase==0) {
            moveToNextPlayer(); //move to next player
            currentDiceRoll = 0;
            currentPaymentAmount = 0;
        }
    }


    public String gameToString (){
        String gameString = "";
        Player[] players = this.players;
        Board board = this.board;
        Assam assam = this.assam;
        for(Player i:players){
            //skip the null and generate the rest players
            if(i !=null){
            gameString+=playerToString(i);}
        }
        gameString += assamToString(assam);
        gameString += boardToString(board);
        return gameString;
    }
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    public int getCurrentPhase() {
        return gamePhase;
    }


    //created stringToGame, so we can converge those creating methods which are in the viewer
    public static Game stringToGame(String gameString) {
        Player[] players = new Player[4];
        int b = 0;
        //the maximum index for the playerString is 32
        for (int i = 0; i < 32; i++) {
            if (gameString.charAt(i) == 'P') {
                // when the string is Pr00012o,he is out of game, since the money become 0;
               // if (!(gameString.substring(i+2, i + 5).equals("000") & gameString.substring(i+7,i+8).equals("o"))) {
                    Color color =null;
                    int money;
                    int rugsNumber;
                    boolean isPlaying = false;
                    switch (gameString.charAt(i+1)){
                        case 'r': color =Color.RED;break;
                        case 'y': color = Color.YELLOW;break;
                        case 'c': color = Color.CYAN;break;
                        case 'p': color = Color.PURPLE;
                    }
                    money = Integer.parseInt(gameString.substring(i+2,i+5));
                    rugsNumber = Integer.parseInt(gameString.substring(i+5,i+7));
                    switch (gameString.charAt(i+7)){
                        case 'o': isPlaying = false;break;
                        case 'i': isPlaying = true;
                    }
                    switch (gameString.charAt(i+1)){
                        case 'y' : b=0;break;
                        case 'p' : b=1;break;
                        case 'r' : b=2;break;
                        case 'c' : b=3;break;
                    }
                    players[b] = new Player(color,money,rugsNumber,isPlaying);
                    }
                }

        // get assam
        String assamString = null;
        for(int i=0;i<gameString.length();i++){
            if(gameString.charAt(i) == 'A'){
                assamString = gameString.substring(i,i+4);
            }
        }
        Assam assam = Assam.stringToAssam(assamString);

            //get board (squares - pos, rug) using stringToBoard
        String boardString = null;
        for(int i=0;i< gameString.length();i++){
            if(gameString.charAt(i) == 'B'){
                boardString = gameString.substring(i,i+148);
            }
        }
        Board board = Board.stringToBoard(boardString);
        return new Game(players,board,assam);
    }

    public Player[] getPlayers(){return players;}
    public Assam getAssam(){return assam;}
    public Board getBoard(){return board;}


    //TODO: change initial game string based on chosen number of players
    public String twoPlayerGame =
            "Py03015iPp03015iA33NBn00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00";
    //yellow, purple
    public String threePlayerGame =
            "Py03015iPp03015iPr03015iA33NBn00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00";
    //yellow, purple, red
    public String fourPlayerGame =
            "Py03015iPp03015iPr03015iPc03015iA33NBn00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00";
    //yellow, purple, red, cyan

    public String game = "Py03015iPp03015iA33NBn00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00";
// example board Py04706iPp00406iPr02806iA15SBy11y11p14p14y07c07y01r00c11c11p16y17y17y10p17y19r11c01c01n00n00p17y19c15n00r17r13n00r06c13r05r05r17r13y04y18y20n00n00c02r16r08y18y20y02y02c09r16r08


    @Override
    public void start(Stage stage) throws Exception {
        // FIXME Task 7 and 15
        stage.setTitle("Marrakech Viewer");
        Viewer viewer = new Viewer();
        //get the viewer's root so that we can use the viewer's functionality in the game as well
        this.root = viewer.getRoot();
        Scene scene = new Scene(this.root, WINDOW_WIDTH, WINDOW_HEIGHT);
        //What pops up at the beginning is the player selection screen
        viewer.playerSelectionWindow();
        root.getChildren().add(viewer.getControls());
        stage.setScene(scene);
        stage.show();
    }
}
