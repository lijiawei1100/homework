package comp1110.ass2.gui;

import comp1110.ass2.*;
import gittest.B;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.*;
import javafx.scene.shape.Polygon;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;


import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;

import static comp1110.ass2.Marrakech.*;

public class Viewer extends Application {

    private static final int VIEWER_WIDTH = 1200;
    private static final int VIEWER_HEIGHT = 700;

    private final Group root = new Group();
    private final Group controls = new Group();
    private TextField boardTextField;

    private Game thisGame;
            //= Game.stringToGame("Py04706iPp00406iPr02806iA15SBy11y11p14p14y07c07y01r00c11c11p16y17y17y10p17y19r11c01c01n00n00p17y19c15n00r17r13n00r06c13r05r05r17r13y04y18y20n00n00c02r16r08y18y20y02y02c09r16r08");
//TODO FIX THIS - THIS IS WHERE GAME is found
// other string: Py04706iPp00406iPr02806iA15SBy11y11p14p14y07c07y01r00c11c11p16y17y17y10p17y19r11c01c01n00n00p17y19c15n00r17r13n00r06c13r05r05r17r13y04y18y20n00n00c02r16r08y18y20y02y02c09r16r08
    public javafx.scene.Group getRoot() {
        return root;
    }
    public Group getControls(){
        return controls;
    }

    // SelectionWindow to choose number of players
    void playerSelectionWindow(){
        ChoiceBox<String> playerSelectionBox = new ChoiceBox<>();
        playerSelectionBox.getItems().addAll( "2 Players", "3 Players", "4 Players");
        playerSelectionBox.setValue("2 Player");
        // Create a button to start the game
        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> {
            String initialGameString = "";
            String selectedOption = playerSelectionBox.getValue();
            switch (selectedOption){
                case "2 Player" : initialGameString ="Py03015iPp03015iA33NBn00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00";break;
                case "3 Players" : initialGameString = "Py03015iPp03015iPr03015iA33NBn00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00";break;
                case "4 Players" : initialGameString = "Py03015iPp03015iPr03015iPc03015iA33NBn00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00";break;
            }
            controls.getChildren().clear();
            try {
                thisGame = Game.stringToGame(initialGameString);
                makeControls();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // Create a layout for the window
        VBox layout = new VBox(10);
        layout.setLayoutX(550);
        layout.setLayoutY(300);
        layout.getChildren().addAll(playerSelectionBox, startButton);
        controls.getChildren().add(layout);
    }

    /**
     * Draw a placement in the window, removing any previously drawn placements
     *
     * @param state an array of two strings, representing the current game state
     */

    //test board: Py04706iPp00406iPr02806iA15SBy11y11p14p14y07c07y01r00c11c11p16y17y17y10p17y19r11c01c01n00n00p17y19c15n00r17r13n00r06c13r05r05r17r13y04y18y20n00n00c02r16r08y18y20y02y02c09r16r08@2


    void displayState(String state) throws Exception {
        // FIXME Task 5: implement the simple state viewer
//        javafx.scene.image.ImageView boardImageView = new ImageView(new Image("assets\\Board Image.png"));

        Game game = Game.stringToGame(state);
        Player[] players =game.getPlayers();
        Assam assam = game.getAssam();
        Board board = game.getBoard();
        //create state viewer Player info
        VBox playerInfo = new VBox();
        playerInfo.setLayoutX(650);
        playerInfo.setLayoutY(20);
        for (int i=1; i<5; i++) {
            Player ithPlayer = (Player) Array.get(players, i-1);
            if (ithPlayer != null) {
                String colour = Player.getColorName(ithPlayer.getColour());
                String money = String.valueOf(ithPlayer.getMoney());
                String rugs = String.valueOf(ithPlayer.getRugsNumber());
                Text playerInfoText = new Text("Player "+i+": " + colour + "\nRemaining Dirhams: " + money + "\nRemaining Rugs: " + rugs + "\n");
                playerInfoText.setFont(Font.font(25));
                playerInfo.getChildren().add(playerInfoText);
            }
        }
        //create state viewer for Board
        GridPane gridPane = new GridPane();
        gridPane.setLayoutX(20);
        gridPane.setLayoutY(20);
        for (int x=0; x<7; x++){
            for (int y=0; y<7 ; y++){
                //get square at that position
                Square thisSquare = board.getBoardMatrix()[x][y];
                //create rectangle with square rug colour
                Rectangle drawSquare = new Rectangle(85,85);
                drawSquare.setStroke(Color.BLACK);
                if (thisSquare.occupiedRug == null) {
                    drawSquare.setFill(Color.LIGHTGREY);
                } else {
                    Color rugColour = thisSquare.occupiedRug.getColour();
                    drawSquare.setFill(rugColour);
                }
                //add rectangle to grid
                gridPane.add(drawSquare, x, y, 1, 1);
            }
        }

        //create state viewer for Assam
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
        Rotate rotation = Rotate.rotate(assam.getAngle(), 42.5, 42.5);
        arrow.getTransforms().add(rotation);
        VBox assamPane = new VBox();
        assamPane.getChildren().add(arrow);
        //the initial position should be 36 == 20+1+15
        assamPane.setLayoutX(36 + 86 * (assam.getAssamX()));
        assamPane.setLayoutY(36 + 86 * (assam.getAssamY()));

        //add state viewers to controls
        controls.getChildren().add(gridPane);
        controls.getChildren().add(playerInfo);
        controls.getChildren().add(assamPane);
    }

    public void createPhase1(){
        Text playerTurn = new Text("Player " + (thisGame.currentPlayerIndex + 1) + "'s turn");
        playerTurn.setFont(Font.font(25));
        Text phase1 = new Text("Phase 1: ");
        phase1.setFont(Font.font(25));
        //make buttons
        Button left = new Button("rotate left");
        Button right  = new Button("rotate right");
        Button stay = new Button("stay");

        //add button functions
        left.setOnAction(event -> {
            thisGame.assam = Assam.stringToAssam(rotateAssam(Assam.assamToString(thisGame.assam), 270));
            controls.getChildren().clear();//avoid duplicate displays
            thisGame.moveToNextPhase(); //move to phase 1
            makeControls();
        });
        right.setOnAction(event -> {
            thisGame.assam = Assam.stringToAssam(rotateAssam(Assam.assamToString(thisGame.assam), 90));
            controls.getChildren().clear();////avoid duplicate displays
            thisGame.moveToNextPhase(); //move to phase 1
            makeControls();
        });
        stay.setOnAction(event -> {
            controls.getChildren().clear();////avoid duplicate displays
            thisGame.moveToNextPhase(); //move to phase 1
            makeControls();
        });

        //make the buttons clickable depending on the game phase
        if (thisGame.gamePhase == 0) {
            left.setDisable(false);
            right.setDisable(false);
            stay.setDisable(false);
        } else {
            left.setDisable(true);
            right.setDisable(true);
            stay.setDisable(true);
        }

        VBox vBox = new VBox();
        HBox hBox = new HBox();

        vBox.getChildren().add((phase1));
        vBox.getChildren().add(playerTurn);
        vBox.getChildren().add(hBox);
        vBox.setLayoutX(950);
        vBox.setLayoutY(20);
        vBox.setSpacing(5);

        hBox.getChildren().addAll(left,stay,right);
        hBox.setSpacing(5);

        controls.getChildren().add(vBox);
    }
    //test board: Py04706iPp00406iPr02806iA15SBy11y11p14p14y07c07y01r00c11c11p16y17y17y10p17y19r11c01c01n00n00p17y19c15n00r17r13n00r06c13r05r05r17r13y04y18y20n00n00c02r16r08y18y20y02y02c09r16r08

    public void createPhase2(){
        Text rollNumber = new Text("Your number: " + thisGame.currentDiceRoll);
        //todo: hide below text and reveal if Assam landed... (Get Assam landing square)...GET DONE SOON

        rollNumber.setFont(Font.font(25));
        Text phase2 = new Text("Phase 2: ");
        phase2.setFont(Font.font(25));
        Button roll = new Button("Roll");
        roll.setOnAction(event -> {
            thisGame.currentDiceRoll = rollDie();
            controls.getChildren().clear();
            thisGame.moveToNextPhase(); //move to phase 2
            makeControls();
        });
        Button moveAssam = new Button("moveAssam");
        moveAssam.setOnAction(event -> {
            //move assam using the dice roll
            thisGame.assam = Assam.stringToAssam(Marrakech.moveAssam(Assam.assamToString(thisGame.assam), thisGame.currentDiceRoll));
            //todo after this change the payment string
            controls.getChildren().clear();
            thisGame.moveToNextPhase(); //move to phase 3
            makeControls();
        });

        Text payment = new Text();
        if (thisGame.gamePhase == 3) {
            Rug occupiedRug = thisGame.board.getBoardMatrix()[thisGame.assam.getAssamX()][thisGame.assam.getAssamY()].occupiedRug;
            if (occupiedRug != null) {
                Integer playerToPayIndex = occupiedRug.getPlayerIndex();
                Player playerToPay = thisGame.players[playerToPayIndex];
                if (playerToPay != null) {
                    int pays = getPaymentAmount(thisGame.gameToString());
                    System.out.println("aaaaaaaaaaaaaaaaa");
                    if (thisGame.board.getBoardMatrix()[thisGame.assam.getAssamX()][thisGame.assam.getAssamY()].occupiedRug.getColour() == playerToPay.getColour()) {
                        payment = new Text("Player " + Integer.toString(thisGame.currentPlayerIndex + 1) + " pays no one");
                    } else {
                        payment = new Text("Player " + (thisGame.currentPlayerIndex + 1) + " pays " + pays +
                                "\ndirhams to Player " + (playerToPayIndex + 1));
                    } //todo move this to event when moveAssam is clicked
                }
                else  {payment = new Text("Player " + Integer.toString(thisGame.currentPlayerIndex + 1) + " pays no one");}
            }
            else  {payment = new Text("Player " + Integer.toString(thisGame.currentPlayerIndex + 1) + " pays no one");}
        }
        //make the buttons clickable depending on the game phase
        if (thisGame.gamePhase == 1) {
            roll.setDisable(false);
        } else {
            roll.setDisable(true);
        }
        if (thisGame.gamePhase == 2) {
            moveAssam.setDisable(false);
        } else {
            moveAssam.setDisable(true);
        }

        payment.setFont(Font.font(25));
        VBox vBox = new VBox();
        vBox.getChildren().add((phase2));
        vBox.getChildren().add(roll);
        vBox.getChildren().add(rollNumber);
        vBox.getChildren().add(moveAssam);
        vBox.getChildren().add(payment);
        vBox.setLayoutX(950);
        vBox.setLayoutY(150);
        vBox.setSpacing(5);
        controls.getChildren().add(vBox);
    }

    public void createPhase3(){
        Text phase3 = new Text("Phase 3: ");
        phase3.setFont(Font.font(25));
        Button horizontalRug = new Button("Place a horizontal rug:");
        Button verticalRug  = new Button("Place a horizontal rug:");
        Boolean isHorizontal;
        horizontalRug.setOnAction(event -> {
            thisGame.rugSetIsHorizontal = Boolean.TRUE;
            thisGame.moveToNextPhase(); //move to phase 4
            controls.getChildren().clear();
            makeControls();
        });

        VBox vBox = new VBox();
        vBox.getChildren().add((phase3));
        vBox.setLayoutX(950);
        vBox.setLayoutY(370);
        controls.getChildren().add(vBox);
    }

    /**
     * Create a basic text field for input and a refresh button.
     */
    public void makeControls() {
        try {
            displayState(thisGame.gameToString());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        createPhase1();
        createPhase2();
        createPhase3();
        //        Label boardLabel = new Label("Game State:");
//        boardTextField = new TextField();
//        boardTextField.setPrefWidth(800);
//        Button button = new Button("Refresh");
//        button.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent e) {
//                try {
//                    displayState(boardTextField.getText());
//                } catch (Exception ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
//        });
//        HBox hb = new HBox();
//        hb.getChildren().addAll(boardLabel,
//                boardTextField, button);
//        hb.setSpacing(10);
//        hb.setLayoutX(50);
//        hb.setLayoutY(VIEWER_HEIGHT - 50);
//        controls.getChildren().add(hb);
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
          primaryStage.setTitle("Marrakech Viewer");
          playerSelectionWindow();
          Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
          root.getChildren().add(controls);
          primaryStage.setScene(scene);
          primaryStage.show();
    }
}
