package comp1110.ass2.gui;

import comp1110.ass2.Assam;
import comp1110.ass2.Board;
import comp1110.ass2.Player;
import comp1110.ass2.Square;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import java.util.ArrayList;

import java.awt.*;
import java.lang.reflect.Array;

public class Viewer extends Application {

    private static final int VIEWER_WIDTH = 1200;
    private static final int VIEWER_HEIGHT = 700;

    private final Group root = new Group();
    private final Group controls = new Group();
    private TextField boardTextField;


    /**
     * Draw a placement in the window, removing any previously drawn placements
     *
     * @param state an array of two strings, representing the current game state
     */
    void displayState(String state) throws Exception {
        // FIXME Task 5: implement the simple state viewer
//        Line baseline = new Line();//Example...
//        baseline.setStartX(0.0);
//        baseline.setStartY(0.0);
//        baseline.setEndX(VIEWER_WIDTH);
//        baseline.setEndY(VIEWER_HEIGHT);
//        root.getChildren().addAll(baseline);
        //get the objects from the string.....
//        int playerStringLength = 0;
//        String remainingString = state;
//        for (int i=0; i<4; i++) {
//            if (remainingString.charAt(0) == 'A') break;
//        }

        //get players (colour, money, rugs) using stringToPlayer until you reach 'A'
        Player[] players = new Player[4];
        int b = 0;
        for(int i =0; i<state.length();i++){
            if(state.charAt(i) == 'P'){
                players[b] = Player.stringToPlayer(state.substring(i, i + 8));
                b++;
            }
        }

        //get Assam (location, orientation) using stringToAssam
        String assamString =null;
        for(int i=0;i<state.length();i++){
            if(state.charAt(i) == 'A'){
                assamString = state.substring(i,i+4);
            }
        }
        Assam assam = Assam.stringToAssam(assamString);

        //get board (squares - pos, rug) using stringToBoard

        String boardString = null;
        for(int i=0;i< state.length();i++){
            if(state.charAt(i) == 'B'){
                boardString = state.substring(i,i+148);
            }
        }
        Board board = Board.stringToBoard(boardString);



        Player player1Placeholder = new Player(Color.RED, 5,15,Boolean.TRUE);
        Player player2Placeholder = new Player(Color.CYAN, 6,14,Boolean.FALSE);
        Assam assamPlaceholder = new Assam(90, new Pair<> (4,6));

        VBox playerInfo = new VBox();
        playerInfo.setLayoutX(650);
        playerInfo.setLayoutY(20);
        for (int i=1; i<5; i++) {
            //Player ithPlayer = ;
        }
        String player1colour = player1Placeholder.colour.toString();
        String player1money = String.valueOf(player1Placeholder.money);
        String player1rugs = String.valueOf(player1Placeholder.rugsNumber);
        Text player1info = new Text("Player 1: " + player1colour + "\nRemaining Dirhams: " + player1money + "\nRemaining Rugs: " + player1rugs);
        player1info.setFont(Font.font(30));
        playerInfo.getChildren().add(player1info);

        controls.getChildren().add(playerInfo);

        //test board: Pc04106iPy04706iPp00406iPr02806iA00WBy11y11p14p14y07c07y01r00c11c11p16y17y17y10p17y19r11c01c01n00n00p17y19c15n00r17r13n00r06c13r05r05r17r13y04y18y20n00n00c02r16r08y18y20y02y02c09r16r08@2

        GridPane gridPane = new GridPane();
        gridPane.setLayoutX(20);
        gridPane.setLayoutY(20);
        for (int x=0; x<7; x++){
            for (int y=0; y<7 ; y++){
                //get square at that position
                Square thisSquare = board.boardMatrix[x][y];
                //create rectangle with square rug colour
                Rectangle drawSquare = new Rectangle(85,85);
                drawSquare.setStroke(Color.BLACK);
                if (thisSquare.occupiedRug == null) {
                    drawSquare.setFill(Color.LIGHTGREY);
                } else {
                    Color rugColour = thisSquare.occupiedRug.colour;
                    drawSquare.setFill(rugColour);
                }
                //add rectangle to grid
                gridPane.add(drawSquare, x, y, 1, 1);
            }
        }
        controls.getChildren().add(gridPane);


        //objects should be in the format ()
        //then display the game state based on the objects
    }
//    private void clearStateDisplay() {
//        // Remove any previously displayed elements from the root or controls Group.
//        // This can be done by removing all child nodes.
//        root.getChildren().clear();
//        controls.getChildren().clear();
//    }


    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label boardLabel = new Label("Game State:");
        boardTextField = new TextField();
        boardTextField.setPrefWidth(800);
        Button button = new Button("Refresh");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    displayState(boardTextField.getText());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(boardLabel,
                boardTextField, button);
        hb.setSpacing(10);
        hb.setLayoutX(50);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Marrakech Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().add(controls);

        makeControls();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
