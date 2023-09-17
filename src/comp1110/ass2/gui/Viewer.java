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
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.awt.*;

public class Viewer extends Application {

    private static final int VIEWER_WIDTH = 1200;
    private static final int VIEWER_HEIGHT = 600;

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
        int playerStringLength = 0;
        String remainingString = state;
        for (int i=0; i<4; i++) {
            if (remainingString.charAt(0) == 'A') break;
        }

        //get players (colour, money, rugs) using stringToPlayer until you reach 'A'
        try {
            Player player1 = Player.stringToPlayer(state.substring(0,8));
        } catch (Exception e) {
                throw new RuntimeException(e);
        }
        try {
            Player player2 = Player.stringToPlayer(state.substring(8,16));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            Player player3 = Player.stringToPlayer(state.substring(16,24));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            Player player4 = Player.stringToPlayer(state.substring(24,32));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //get Assam (location, orientation) using stringToAssam

        try {
            Assam assam = Assam.stringToAssam(state.substring(32,36));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //get board (squares - pos, rug) using stringToBoard

        Player player1Placeholder = new Player("red", 5,15,Boolean.TRUE);
        Player player2Placeholder = new Player("cyan", 6,14,Boolean.FALSE);
        Assam assamPlaceholder = new Assam(90, new Pair<> (4,6));

        Board boardPlaceHolder = Board.stringToBoard
                ("Bn00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00" +
                        "n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00");

        GridPane gridPane = new GridPane();
        for (int x=0; x<7; x++){
            for (int y=0; y<7 ; y++){
                //get square at that position
                Square thisSquare = boardPlaceHolder.boardMatrix[x][y];
                //create rectangle with square rug colour
                Rectangle drawSquare = new Rectangle(90,90);
                Color rugColour = thisSquare.occupiedRug.colour;
                if (thisSquare.occupiedRug == null) {
                    drawSquare.setFill(Color.LIGHTGREY);
                } else {
                    drawSquare.setFill(rugColour);
                }
                //add rectangle to grid
                gridPane.add(drawSquare, x, y, 1, 1);
            }
        }
        gridPane.setLayoutX(50);
        gridPane.setLayoutY(50);
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
