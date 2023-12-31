package comp1110.ass2.gui;
import comp1110.ass2.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.*;
import javafx.scene.shape.Polygon;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;
import java.lang.reflect.Array;
import static comp1110.ass2.Marrakech.*;
import static comp1110.ass2.Player.getActualPayAmount;
import static comp1110.ass2.Player.getColorName;


public class Viewer extends Application {
    /**
     * AUTHORSHIP:
     *
     * displayState,createPhase1/2/3 were all written by Benjamin Campbell (Jiawei li help with a small modification)
     * playerSelectionWindow and getMessageBasedOnMousePosition were written by Jiawei Li
     * Work together to complete the winner() method
     * Jiawei Li tried to use hbox to display image but failed.
     *
     * @author <u7531534><Jiawei Li>/ <u7471333><Benjamin Campbell>
     *
     */

    private static final int VIEWER_WIDTH = 1200;
    private static final int VIEWER_HEIGHT = 700;

    private final Group root = new Group();
    private final Group controls = new Group();
    private Game thisGame;
    public javafx.scene.Group getRoot() {
        return root;
    }
    public Group getControls(){
        return controls;
    }

    // SelectionWindow to choose number of players
    public void playerSelectionWindow(){
        ChoiceBox<String> playerSelectionBox = new ChoiceBox<>();
        playerSelectionBox.getItems().addAll( "2 Players", "3 Players", "4 Players");
        playerSelectionBox.setValue("2 Players");
        // Create a button to start the game
        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> {
            String initialGameString = "";
            String selectedOption = playerSelectionBox.getValue();
            switch (selectedOption){
                //change initial game string based on chosen number of players
                case "2 Players" : initialGameString ="Py03015iPp03015iA33NBn00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00";break;
                //yellow, purple
                case "3 Players" : initialGameString = "Py03015iPp03015iPr03015iA33NBn00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00";break;
                //yellow, purple, red
                case "4 Players" : initialGameString = "Py03015iPp03015iPr03015iPc03015iA33NBn00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00";break;
                //yellow, purple, red, cyan
            }
            this.controls.getChildren().clear();
            try {
                //create new game based on the selected players' number
                thisGame = Game.stringToGame(initialGameString);
                //build a new viewer
                makeControls();
                restart();
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

    void restart(){
        VBox vBox = new VBox();
        Button restartGame = new Button("Restart");
        restartGame.setOnAction(event -> {
            //after clicking, clear all screens and switch to the player selection screen.
            this.controls.getChildren().clear();
            playerSelectionWindow();
        });
        vBox.getChildren().add(restartGame);
        vBox.setLayoutX(950);
        vBox.setLayoutY(620);
        controls.getChildren().add(vBox);
    }

    /**
     * Draw a placement in the window, removing any previously drawn placements
     * @param state an array of two strings, representing the current game state
     */
    void displayState(String state) throws Exception {
        // FIXME Task 5: implement the simple state viewer

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
                String colour = getColorName(ithPlayer.getColour());
                String money = String.valueOf(ithPlayer.getMoney());
                String rugs = String.valueOf(ithPlayer.getRugsNumber());
                if(ithPlayer.getIsPlaying() ==true) {
                    Text playerInfoText = new Text("Player " + i + ": " + colour + "\nRemaining Dirhams: " + money + "\nRemaining Rugs: " + rugs + "\n");
                    playerInfoText.setFont(Font.font(25));
                    playerInfo.getChildren().add(playerInfoText);
                }
                else {
                    Text playerInfoText = new Text("Player " + i + ": " + colour + " (out)\nRemaining Dirhams: " + money + "\nRemaining Rugs: " + rugs + "\n");
                    playerInfoText.setFont(Font.font(25));
                    playerInfo.getChildren().add(playerInfoText);
                }
            }
        }

        //draw boxes indicating assam movement options
        StackPane stackPane = new StackPane();

        //top rectangles
        for (int i=0;i<3;i++) {
            Rectangle square1 = new Rectangle(85,15);
            square1.setTranslateX(35.5 + 20 + 42.5 + 172*i);
            square1.setTranslateY(5);
            stackPane.getChildren().addAll(square1);
        }
        Rectangle square1 = new Rectangle(42.5+15,15);
        square1.setTranslateX(22 + 20 + 42.5 + 172*3);
        square1.setTranslateY(5);
        stackPane.getChildren().addAll(square1);
        //bottom rectangles
        for (int i=0;i<3;i++) {
            Rectangle square2 = new Rectangle(85,15);
            square2.setTranslateX(35.5 + 20 + 42.5 + 86 + 172*i);
            square2.setTranslateY(20 + 86*7);
            stackPane.getChildren().addAll(square2);
        }
        Rectangle square2 = new Rectangle(42.5+15,15);
        square2.setTranslateX(7 + 20);
        square2.setTranslateY(20 + 86*7);
        stackPane.getChildren().addAll(square2);
        //left rectangles
        for (int i=0;i<3;i++) {
            Rectangle square3 = new Rectangle(15,85);
            square3.setTranslateY(35.5 + 20 + 42.5+ 172*i);
            square3.setTranslateX(5);
            stackPane.getChildren().addAll(square3);
        }
        Rectangle square3 = new Rectangle(15,42.5+15);
        square3.setTranslateY(22 + 20 + 42.5+ 172*3);
        square3.setTranslateX(5);
        stackPane.getChildren().addAll(square3);
        //right rectangles
        for (int i=0;i<3;i++) {
            Rectangle square4 = new Rectangle(15,85);
            square4.setTranslateY(35.5 + 20 + 42.5 + 86 + 172*i);
            square4.setTranslateX(20 + 86*7);
            stackPane.getChildren().addAll(square4);
        }
        Rectangle square4 = new Rectangle(15,42.5+15);
        square4.setTranslateX(20 + 86*7);
        square4.setTranslateY(7 + 20);
        stackPane.getChildren().addAll(square4);

        stackPane.setLayoutX(-35.5);
        stackPane.setLayoutY(-35.5);


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
        controls.getChildren().add(stackPane);
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

    public void createPhase2(){
        Text rollNumber = new Text("Your number: " + thisGame.currentDiceRoll);
        //todo: hide below text and reveal if Assam landed... (Get Assam landing square)...GET DONE SOON
        final int[] tmp = new int[2];

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
        //make the buttons clickable depending on the game phase
        if (thisGame.gamePhase == 1) {
            roll.setDisable(false);
        } else {
            roll.setDisable(true);
        }

        Button moveAssam = new Button("moveAssam");
        moveAssam.setOnAction(event -> {
            //move assam using the dice roll
            thisGame.assam = Assam.stringToAssam(Marrakech.moveAssam(Assam.assamToString(thisGame.assam), thisGame.currentDiceRoll));
            //payment process:
            //1.get the occupied rug
            Rug occupiedRug = thisGame.board.getBoardMatrix()[thisGame.assam.getAssamX()][thisGame.assam.getAssamY()].occupiedRug;
            if (occupiedRug != null) {
                //if occupied rug exists pay that player
                thisGame.playerDoPay = true;
                thisGame.playerPaidIndex = occupiedRug.getPlayerIndex();
                Player playerToPay = thisGame.players[thisGame.playerPaidIndex];
                thisGame.currentPaymentAmount = getPaymentAmount(thisGame.gameToString());
                if (thisGame.currentPlayer == playerToPay) {
                    thisGame.currentPaymentAmount = 0; //a player does not need to play themselves
                } else {
                    if(playerToPay.getIsPlaying()) {
                        //need to fix how to pay as much as the player can when the payment is above the money
                        tmp[0] = thisGame.currentPlayer.getMoney();
                        thisGame.currentPlayer.playerPays(thisGame.currentPaymentAmount);
                        thisGame.actualPaymentAmount = getActualPayAmount(thisGame.currentPaymentAmount, tmp[0]);
                        playerToPay.playerIsPaid(thisGame.actualPaymentAmount);
                    }
                    else thisGame.playerDoPay=false;
                }
            } //else thisGame.currentPaymentAmount = 0;
            thisGame.moveToNextPhase(); //move to phase 3
            updateStatus();
            controls.getChildren().clear();
            makeControls();
        });
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

        Text payment = new Text();
        if (thisGame.gamePhase>=3){
              if (thisGame.currentPaymentAmount!=0 & thisGame.playerDoPay) {
                payment = new Text("Player " + (thisGame.currentPlayerIndex + 1) + " pays " + thisGame.actualPaymentAmount+
                        "\ndirhams to Player " + (thisGame.playerPaidIndex + 1));
              }
              else {payment = new Text("Player " + Integer.toString(thisGame.currentPlayerIndex + 1) + " pays no one");}
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
    public void updateStatus(){
        if (thisGame.currentPlayer.getMoney() == 0) {
            thisGame.currentPlayer.playerOut();
            thisGame.skip=true;
        }
        winner();
    }

    public void createPhase3(){
        Text phase3 = new Text("Phase 3: ");
        phase3.setFont(Font.font(25));
        String rugDirectionText;
        if (thisGame.rugPlaceIsHorizontal) {
            rugDirectionText = "horizontal";
        } else {
            rugDirectionText = "vertical";
        }
        Text rugDirection = new Text("You are placing a "+ rugDirectionText +" rug");
        Button horizontalRug = new Button("Place a horizontal rug:");
        Button verticalRug  = new Button("Place a vertical rug:");
        horizontalRug.setOnAction(event -> {
//            thisGame.rugHbox = buildImage(2);
            thisGame.rugPlaceIsHorizontal = Boolean.TRUE;
            controls.getChildren().clear();
            makeControls();
        });
        verticalRug.setOnAction(event -> {
//            thisGame.rugHbox = buildImage(1);
            thisGame.rugPlaceIsHorizontal = Boolean.FALSE;
            controls.getChildren().clear();
            makeControls();
        });
        if (thisGame.gamePhase == 3 & thisGame.currentPlayer.getIsPlaying()) {
            horizontalRug.setDisable(false);
            verticalRug.setDisable(false);
        } else {
            horizontalRug.setDisable(true);
            verticalRug.setDisable(true);
        }


        //create invisible GridPane for squares
        if (thisGame.gamePhase == 3 & !thisGame.skip) {
            GridPane invisibleGrid = new GridPane();
            invisibleGrid.setLayoutX(20);
            invisibleGrid.setLayoutY(20);
            for (int x=0; x<7; x++){
                for (int y=0; y<7 ; y++){
                    //get square at that position
                    Square thisSquare = thisGame.board.getBoardMatrix()[x][y];
                    //create rectangle with square rug colour
                    Rectangle squareImage = new Rectangle(85,85);
                    squareImage.setFill(Color.web("0x0000FF",0.0));
                    squareImage.setStroke(Color.BLACK);
                    invisibleGrid.add(squareImage, x, y, 1, 1);

                    int finalX = x;
                    int finalY = y;

                    //make two positions - one for first, one for second spot - conditional on button
                    Pair<Integer, Integer> firstPosition = new Pair<>(finalX,finalY);
                    Pair<Integer, Integer> secondPosition;
                    if (thisGame.rugPlaceIsHorizontal) {
                        if (finalX != 6) {
                            secondPosition = new Pair<>(finalX + 1,finalY);
                            Square square2 = thisGame.board.getBoardMatrix()[x+1][y];
                        } else secondPosition = null;
                    } else {
                        if (finalY != 6) {
                            secondPosition = new Pair<>(finalX,finalY + 1);
                            Square square2 = thisGame.board.getBoardMatrix()[x][y+1];
                        } else secondPosition = null;
                    }
                    Rug.RugWithPosition potentialRug = new Rug.RugWithPosition(thisGame.currentPlayer.getColour(), (15 - thisGame.currentPlayer.getRugsNumber()),firstPosition,secondPosition);

                    squareImage.setOnMouseEntered(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            //first check if the cursor position and rug placement is okay
                            // to judge if it is the vertical rug
                            if ((thisGame.rugPlaceIsHorizontal == false & finalY != 6)) {
                                if (isRugValid(thisGame.gameToString(), Rug.RugWithPosition.rugWithPositionToString(potentialRug))) {
                                    //if potential rug placement is valid then outline the square with the right colour, if not then outline white
                                    if (isPlacementValid(thisGame.gameToString(), Rug.RugWithPosition.rugWithPositionToString(potentialRug))) {
                                        squareImage.setStroke(potentialRug.getColour());
                                        //Iterate over the existing nodes and make it possible to display the player color when placed
                                        for (javafx.scene.Node node : invisibleGrid.getChildren()) {
                                            if (GridPane.getRowIndex(node) == GridPane.getRowIndex(squareImage) + 1 && GridPane.getColumnIndex(node) == GridPane.getColumnIndex(squareImage)) {
                                                Rectangle rectangle = (Rectangle) node;
                                                rectangle.setStroke(potentialRug.getColour());
                                            }
                                        }
                                        //Iterates over existing nodes and makes them white when they cannot be placed.
                                    } else {
                                        squareImage.setStroke(Color.WHITE);
                                        for (javafx.scene.Node node : invisibleGrid.getChildren()) {
                                            if (GridPane.getRowIndex(node) == GridPane.getRowIndex(squareImage) + 1 && GridPane.getColumnIndex(node) == GridPane.getColumnIndex(squareImage)) {
                                                Rectangle rectangle = (Rectangle) node;
                                                rectangle.setStroke(Color.WHITE);
                                            }
                                        }
                                    }
                                }
                                // to judge if it is the horizontal rug
                            } else if (thisGame.rugPlaceIsHorizontal & finalX != 6) {
                                if (isRugValid(thisGame.gameToString(), Rug.RugWithPosition.rugWithPositionToString(potentialRug))) {
                                    //if potential rug placement is valid then outline the square with the right colour, if not then outline white
                                    if (isPlacementValid(thisGame.gameToString(), Rug.RugWithPosition.rugWithPositionToString(potentialRug))) {
                                        squareImage.setStroke(potentialRug.getColour());
                                        //Iterate over the existing nodes and make it possible to display the player color when placed
                                        for (javafx.scene.Node node : invisibleGrid.getChildren()) {
                                            if (GridPane.getRowIndex(node) == GridPane.getRowIndex(squareImage) && GridPane.getColumnIndex(node) == GridPane.getColumnIndex(squareImage) + 1) {
                                                Rectangle rectangle = (Rectangle) node;
                                                rectangle.setStroke(potentialRug.getColour());
                                            }
                                        }
                                        //Iterates over existing nodes and makes them white when they cannot be placed.
                                    } else {
                                        squareImage.setStroke(Color.WHITE);
                                        for (javafx.scene.Node node : invisibleGrid.getChildren()) {
                                            if (GridPane.getRowIndex(node) == GridPane.getRowIndex(squareImage) && GridPane.getColumnIndex(node) == GridPane.getColumnIndex(squareImage) + 1) {
                                                Rectangle rectangle = (Rectangle) node;
                                                rectangle.setStroke(Color.WHITE);
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    });
                    squareImage.setOnMouseExited(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            squareImage.setStroke(Color.BLACK);
                            //When it is vertical, let the corresponding square return to the black border.
                            if (thisGame.rugPlaceIsHorizontal==false & finalY != 6){
                                for (javafx.scene.Node node : invisibleGrid.getChildren()) {
                                   if (GridPane.getRowIndex(node) == GridPane.getRowIndex(squareImage) + 1 && GridPane.getColumnIndex(node) == GridPane.getColumnIndex(squareImage)) {
                                        Rectangle rectangle = (Rectangle) node;
                                        rectangle.setStroke(Color.BLACK);
                                   }
                                }
                            }
                            //When it is horizontal, let the corresponding square return to the black border.
                            else if (thisGame.rugPlaceIsHorizontal & finalX != 6) {
                                for (javafx.scene.Node node : invisibleGrid.getChildren()) {
                                    if (GridPane.getRowIndex(node) == GridPane.getRowIndex(squareImage) && GridPane.getColumnIndex(node) == GridPane.getColumnIndex(squareImage) + 1) {
                                        Rectangle rectangle = (Rectangle) node;
                                        rectangle.setStroke(Color.BLACK);
                                    }
                                }
                            }
                        }
                    });

                    squareImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            //first check if the cursor position and rug placement is okay
                            if ((thisGame.rugPlaceIsHorizontal & finalX != 6) | (thisGame.rugPlaceIsHorizontal==false & finalY != 6)) {
                                if (isRugValid(thisGame.gameToString(),Rug.RugWithPosition.rugWithPositionToString(potentialRug))) {
                                    //if potential rug placement is valid then outline the square with the right colour, if not then outline white
                                    if (isPlacementValid(thisGame.gameToString(),Rug.RugWithPosition.rugWithPositionToString(potentialRug))) {
                                        //place rug on both squares
                                        thisSquare.setOccupiedRug(potentialRug);
                                        thisGame.board.getBoardMatrix()[potentialRug.getP2().getKey()][potentialRug.getP2().getValue()].occupiedRug= potentialRug;
                                        //remove one rug from player rug count
                                        thisGame.currentPlayer.minusRug();
                                        thisGame.moveToNextPhase(); //move back to phase 1
                                        controls.getChildren().clear();
//                                        thisGame.rugHbox = buildImage(0);
                                        makeControls();
                                    }
                                    //if placement is not valid do nothing
                                }
                            }
                        }
                    });
                }
            }
            controls.getChildren().add(invisibleGrid);
        }


        Label messageLabel = new Label(" Hover over a square to see a message"+"\n\n");
        root.setOnMouseMoved(event -> {
            if (thisGame.gamePhase == 3 ) {
                double mouseX = event.getSceneX();
                double mouseY = event.getSceneY();
                String message = getMessageBasedOnMousePosition(mouseX, mouseY);
                messageLabel.setText(message);
            }
        });
        //use remain button to skip when the player is out of game
        Label messageLabel1 = new Label("Press remain button to continue"+"\n\n");
        Button remain = new Button("remain");
        remain.setOnAction(event ->{
            thisGame.skip=false;
            thisGame.moveToNextPhase();
            controls.getChildren().clear();
            makeControls();});

        VBox vBox = new VBox();
        vBox.getChildren().add(phase3);
        if(!thisGame.skip) {
            vBox.getChildren().add(messageLabel);}
        if(!thisGame.skip & thisGame.gamePhase ==3){
            vBox.getChildren().add(rugDirection);}
        vBox.getChildren().add(verticalRug);
        vBox.getChildren().add(horizontalRug);
        if(thisGame.skip) {
            vBox.getChildren().add(messageLabel1);
            vBox.getChildren().add(remain);}
//        vBox.getChildren().add(thisGame.rugHbox);
        vBox.setLayoutX(950);
        vBox.setLayoutY(370);
        vBox.setSpacing(10);
        controls.getChildren().add(vBox);


    }

    //return a string by mouse over position,checking if the rug and placement are valid.
    private String getMessageBasedOnMousePosition(double mouseX, double mouseY) {
        // Example: Determine the message based on the mouse position
        int x = ((int) mouseX - 20) / 86;
        int y = ((int) mouseY - 20) / 86;
        String rugId;
        int rugsNumber = thisGame.currentPlayer.getRugsNumber();
        if(15-rugsNumber<10) {rugId = "0"+ String.valueOf(15-rugsNumber);}
        else{ rugId = String.valueOf(15-rugsNumber);}
        if(!thisGame.rugPlaceIsHorizontal) {
            String rugString = getColorName(thisGame.currentPlayer.getColour()).toLowerCase().charAt(0) + rugId + String.valueOf(x) + String.valueOf(y) + String.valueOf(x) + String.valueOf(y + 1);
            if (x < 7 & x >= 0 & y < 7 & y >= 0) {
                if (isRugValid(thisGame.gameToString(), rugString) & isPlacementValid(thisGame.gameToString(), rugString)) {
                    return "You can place the rug in this square." +"\nrugString is"+ rugString;
                } else return "You can not place the rug in this square."+"\nrugString is" + rugString;
            }
        }
        else {
            String rugString = getColorName(thisGame.currentPlayer.getColour()).toLowerCase().charAt(0) + rugId + String.valueOf(x) + String.valueOf(y) + String.valueOf(x+1) + String.valueOf(y);
            if (x < 7 & x >= 0 & y < 7 & y >= 0) {
                if (isRugValid(thisGame.gameToString(), rugString) & isPlacementValid(thisGame.gameToString(), rugString)) {
                    return "You can place the rug in this square."+"\nrugString is" + rugString;
                } else return "You can not place the rug in this square."+"\nrugString is" + rugString;
            }
        }
        return "\n\n";
    }

    //designed three state for choosing the rug, but unfortunately it has functional conflicts.So I'll leave the code here to indicate the method I've tried
//    static HBox buildImage(int a){
//        Image image =new javafx.scene.image.Image("file:./assets/rug0.png");
//        Image image1 =new javafx.scene.image.Image("file:./assets/rug1.png");
//        Image image2 =new javafx.scene.image.Image("file:./assets/rug2.png");
//        Image image3 =new javafx.scene.image.Image("file:./assets/rug3.png");
//        ImageView imageView = new ImageView(image);
//        ImageView imageView1 = new ImageView(image1);
//        ImageView imageView2 = new ImageView(image2);
//        ImageView imageView3 = new ImageView(image3);
//        HBox hBox = new HBox();
//        hBox.setSpacing(30);
//        if(a==0){
//            imageView.setFitHeight(80);
//            imageView.setFitWidth(50);
//            imageView1.setFitHeight(50);
//            imageView1.setFitWidth(80);
//            hBox.getChildren().add(imageView);
//            hBox.getChildren().add(imageView1);
//        } else if (a==1) {
//            imageView2.setFitHeight(80);
//            imageView2.setFitWidth(50);
//            imageView1.setFitHeight(50);
//            imageView1.setFitWidth(80);
//            hBox.getChildren().add(imageView2);
//            hBox.getChildren().add(imageView1);
//        }else {
//            imageView.setFitHeight(80);
//            imageView.setFitWidth(50);
//            imageView3.setFitHeight(50);
//            imageView3.setFitWidth(80);
//            hBox.getChildren().add(imageView);
//            hBox.getChildren().add(imageView3);
//        }
//        return hBox;
//    }

    void winner(){
        char a = getWinner(thisGame.gameToString());
        String message = new String();
        if (a != 'n') {
            if(a == 't'){
                message = "It's a tie. Let's start over";
            }
            else if(a == 'r'){
                message = "Player 3 won the game";
            } else if (a=='c') {
                message = "Player 4 won the game";
            } else if (a == 'y') {
                message = "Player 1 won the game";
            } else if (a == 'p') {
                message = "Player 2 won the game";
            }
            //end the game
            thisGame.gamePhase=10;
            //show the message
            VBox vBox = new VBox();
            vBox.setLayoutX(1000);
            vBox.setLayoutY(600);
            Text winnerInfo = new Text(message);
            vBox.getChildren().add(winnerInfo);
            controls.getChildren().add(vBox);
        }
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
        winner();
        createPhase1();
        createPhase2();
        createPhase3();
        restart();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
          primaryStage.setTitle("Marrakech Viewer");
          //testing gameState
          //In our design rug placed ids are in order, so if you want to test any state here,
          // please make sure that the rugs that have been placed are in order,
          // otherwise the game won't be able to play,but it won't be a problem during normal gameplay.
          // The instructions are just to make you use the viewer better.
          thisGame = Game.stringToGame("Pc00306iPy00306iPp00306iPr00306`iA63WBy99n00n00y98p99c99r99y99r98c16y97c17c17r99n00y96c16y95y94y93n00c98c98y92y95y91y93n00y90c97y89y88y91p98n00n00p97r87y88n00p98y87n00p97n00n00n00p17p17");
          Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
          root.getChildren().add(controls);
          makeControls();
          primaryStage.setScene(scene);
          primaryStage.show();
    }
}
