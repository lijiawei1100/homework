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

import static comp1110.ass2.Marrakech.isGameOver;
import static comp1110.ass2.Player.getColorName;
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

    public Game(){};

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



    @Override
    public void start(Stage stage) throws Exception {
        // FIXME Task 7 and 15
        Viewer viewer = new Viewer();
        this.root = viewer.getRoot();
        Scene scene = new Scene(this.root, WINDOW_WIDTH, WINDOW_HEIGHT);
        root.getChildren().add(viewer.getControls());
        viewer.makeControls();
        stage.setScene(scene);
        stage.show();
    }
}
