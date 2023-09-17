package comp1110.ass2.gui;

import comp1110.ass2.Assam;
import comp1110.ass2.Board;
import comp1110.ass2.Player;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static comp1110.ass2.Assam.ASSAM;
import static comp1110.ass2.Assam.assamToString;
//import static comp1110.ass2.Board.boardToString;
import static comp1110.ass2.Player.*;

public class Game extends Application {

    private final Group root = new Group();
    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 700;

    public Player[] players;


    /**
     * combine the players strings、Assam string and Board String
     * @param args
     */
    public static void main(String[] args) {
//        System.out.println(gameToString(new Player[]{CYAN, YELLOW, RED, PURPLE},ASSAM,new Board()));
    }

    @Override
    public void start(Stage stage) throws Exception {
        // FIXME Task 7 and 15
        Scene scene = new Scene(this.root, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }


}
