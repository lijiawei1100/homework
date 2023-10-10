package comp1110.ass2.gui;

import comp1110.ass2.Assam;
import comp1110.ass2.Board;
import comp1110.ass2.Player;
import gittest.C;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.text.View;
import java.sql.SQLOutput;
import java.util.*;

import static comp1110.ass2.Assam.assamToString;
import static comp1110.ass2.Board.boardToString;
import static comp1110.ass2.Marrakech.isGameOver;
import static comp1110.ass2.Player.getColorName;
import static comp1110.ass2.Player.plyaerToString;
import static javafx.stage.Stage.*;

public class Game extends Application {

    private Group root = new Group();
    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 700;

    Player[] players;
    Board board;
    Assam assam;

    public Game() {};

    public Game(Player[] players,Board board,Assam assam){
        this.players=players;
        this.board=board;
        this.assam=assam;
    }

    public String gameToString (Game game){
        String gameString = "";
        Player[] players = game.players;
        Board board = game.board;
        Assam assam = game.assam;
        for(Player i:players){
            if(i !=null){
            gameString+=plyaerToString(i);}
        }
        gameString += assamToString(assam);
        gameString += boardToString(board);
        return gameString;
    }

    /**
     * created stringToGame, so we can converge those creating methods which are in the viewer
     * @param gameString
     * @return
     */
    public static Game stringToGame(String gameString) {
        Player[] players = new Player[4];
        int b = 0;
        for (int i = 0; i < 32; i++) {
            if (gameString.charAt(i) == 'P') {
                if (!(gameString.substring(i+2, i + 5).equals("000") & gameString.substring(i+7,i+8).equals("o"))) {
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
                    players[b] = new Player(color,money,rugsNumber,isPlaying);
                    b++;
                    }
                }
            }

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
    public String game = "Py04706iPp00406iPr02806iA33NBy11y11p14p14y07c07y01r00c11c11p16y17y17y10p17y19r11c01c01n00n00p17y19c15n00r17r13n00r06c13r05r05r17r13y04y18y20n00n00c02r16r08y18y20y02y02c09r16r08";


    @Override
    public void start(Stage stage) throws Exception {
        // FIXME Task 7 and 15

        Game newgame = stringToGame(game);

        Viewer viewer = new Viewer();
        this.root = viewer.getRoot();
        Scene scene = new Scene(this.root, WINDOW_WIDTH, WINDOW_HEIGHT);
        root.getChildren().add(viewer.getControls());
        viewer.makeControls();
        stage.setScene(scene);
        stage.show();
    }
}
