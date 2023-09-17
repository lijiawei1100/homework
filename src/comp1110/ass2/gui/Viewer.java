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
import javafx.scene.shape.Polygon;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.awt.geom.AffineTransform;
import javafx.util.Pair;


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

        //get players (colour, money, rugs) using stringToPlayer when you reach ""
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

        Polygon arrow = new Polygon();
        arrow.getPoints().addAll(new Double[]{
                42.5, 12.5,
                70.0, 40.0,
                55.0, 40.0,
                55.0, 70.0,
                30.0, 70.0,
                30.0, 40.0,
                15.0, 40.0, });
        arrow.setFill(Color.BLACK);
        ;
        int rotationAngle = assam.getAssamX()/90;
        AffineTransform.getQuadrantRotateInstance((int) rotationAngle,
                42.5, 42.5);
        VBox assamPane = new VBox();
        assamPane.getChildren().add(arrow);
        assamPane.setLayoutY(35 + 86 * (assam.getAssamX()));
        assamPane.setLayoutX(35 + 86 * (assam.getAssamY()));


        VBox playerInfo = new VBox();
        playerInfo.setLayoutX(650);
        playerInfo.setLayoutY(20);


        for (int i=1; i<5; i++) {
            Player ithPlayer = (Player) Array.get(players, i-1);
            if (ithPlayer != null) {
                String colour = Player.getColorName(ithPlayer.colour);
                String money = String.valueOf(ithPlayer.money);
                String rugs = String.valueOf(ithPlayer.rugsNumber);
                Text playerInfoText = new Text("Player "+i+": " + colour + "\nRemaining Dirhams: " + money + "\nRemaining Rugs: " + rugs + "\n");
                playerInfoText.setFont(Font.font(30));
                playerInfo.getChildren().add(playerInfoText);
            }
        }

        //test board: Pc04106iPy04706iPp00406iPr02806iA15WBy11y11p14p14y07c07y01r00c11c11p16y17y17y10p17y19r11c01c01n00n00p17y19c15n00r17r13n00r06c13r05r05r17r13y04y18y20n00n00c02r16r08y18y20y02y02c09r16r08@2

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
        controls.getChildren().add(playerInfo);
        controls.getChildren().add(assamPane);


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
