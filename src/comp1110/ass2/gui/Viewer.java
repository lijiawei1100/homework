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
import javafx.scene.image.ImageView;
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
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;
import javafx.scene.image.Image;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Array;

import static comp1110.ass2.Marrakech.*;
import static comp1110.ass2.Player.getColorName;

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
                case "2 Players" : initialGameString ="Py03015iPp03015iA33NBn00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00";break;
                case "3 Players" : initialGameString = "Py03015iPp03015iPr03015iA33NBn00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00";break;
                case "4 Players" : initialGameString = "Py03015iPp03015iPr03015iPc03015iA33NBn00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00";break;
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
            this.controls.getChildren().clear();
            playerSelectionWindow();
            //            thisGame.playerSelectionWindow();
            //            root.getChildren().add(controls);
        });
        vBox.getChildren().add(restartGame);
        vBox.setLayoutX(950);
        vBox.setLayoutY(620);
        controls.getChildren().add(vBox);
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
                String colour = getColorName(ithPlayer.getColour());
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
                thisGame.playerPaidIndex = occupiedRug.getPlayerIndex();
                Player playerToPay = thisGame.players[thisGame.playerPaidIndex];
                thisGame.currentPaymentAmount = getPaymentAmount(thisGame.gameToString());
                if (thisGame.currentPlayer == playerToPay) {
                    thisGame.currentPaymentAmount = 0; //a player does not need to play themselves
                } else {
                    thisGame.currentPlayer.playerPays(thisGame.currentPaymentAmount);
                    playerToPay.playerIsPaid(thisGame.currentPaymentAmount);
                }
            } //else thisGame.currentPaymentAmount = 0;
            thisGame.moveToNextPhase(); //move to phase 3
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
            if (thisGame.currentPaymentAmount == 0) {
                payment = new Text("Player " + Integer.toString(thisGame.currentPlayerIndex + 1) + " pays no one");
            } else {
                payment = new Text("Player " + (thisGame.currentPlayerIndex + 1) + " pays " + thisGame.currentPaymentAmount +
                        "\ndirhams to Player " + (thisGame.playerPaidIndex + 1));
            }
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
            thisGame.rugHbox = buildImage(2);
            thisGame.rugPlaceIsHorizontal = Boolean.TRUE;
            controls.getChildren().clear();
            makeControls();
        });
        verticalRug.setOnAction(event -> {
            thisGame.rugHbox = buildImage(1);
            thisGame.rugPlaceIsHorizontal = Boolean.FALSE;
            controls.getChildren().clear();
            makeControls();
        });
        if (thisGame.gamePhase == 3) {
            horizontalRug.setDisable(false);
            verticalRug.setDisable(false);
        } else {
            horizontalRug.setDisable(true);
            verticalRug.setDisable(true);
        }

        //todo add mouse click functionality when in stage 3. when clicked, check if position is valid and then:
        // - place rug and end turn
        // - or add error message "invalid placement"
        // after this add hover functionality and only show when placement is valid!

        //create invisible GridPane for squares
        if (thisGame.gamePhase == 3) {
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
                    squareImage.setStroke(Color.LIGHTBLUE);
                    invisibleGrid.add(squareImage, x, y, 1, 1);

                    int finalX = x;
                    int finalY = y;
                    squareImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            System.out.println("Mouse clicked inside rectangle with index: "+ finalX + finalY);
                            System.out.println(thisSquare);
                            //make two positions - one for first, one for second spot - conditional on button
                            Pair<Integer, Integer> firstPosition = new Pair<>(finalX,finalY);
                            Pair<Integer, Integer> secondPosition;
                            if (thisGame.rugPlaceIsHorizontal) {
                                secondPosition = new Pair<>(finalX + 1,finalY);
                            } else {
                                secondPosition = new Pair<>(finalX,finalY + 1);
                            }
                            System.out.println("potential rug placement: "+firstPosition.getKey()+firstPosition.getValue()+" "+secondPosition.getKey()+secondPosition.getValue());
                            Rug.RugWithPosition potentialRug = new Rug.RugWithPosition(thisGame.currentPlayer.getColour(), (15 - thisGame.currentPlayer.getRugsNumber()),firstPosition,secondPosition);
                            //check if potential rug is valid
//                            if (isRugValid(thisGame.gameToString(), potentialRug.))
                            //check if potential rug placement is valid
                            //remove one rug from player rug count
                        }
                    });
                }
            }
            controls.getChildren().add(invisibleGrid);
        }

//        Image image1 = new Image("file:///C:/Users/user/Desktop/x.jpg");



        Label messageLabel = new Label(" Hover over a square to see a message");
        root.setOnMouseMoved(event -> {
            double mouseX = event.getSceneX();
            double mouseY = event.getSceneY();
            String message = getMessageBasedOnMousePosition(mouseX, mouseY);
            messageLabel.setText(message);
        });

//
//        imageView.setOnMouseClicked(event -> {
//            if (event.getClickCount() == 1) {
//                System.out.println("Image Clicked!");
//            }
//        });
        VBox vBox = new VBox();
        vBox.getChildren().add(phase3);
        vBox.getChildren().add(messageLabel);
        vBox.getChildren().add(rugDirection);
        vBox.getChildren().add(verticalRug);
        vBox.getChildren().add(horizontalRug);
        vBox.getChildren().add(thisGame.rugHbox);
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

        String rugString = getColorName(thisGame.currentPlayer.getColour()).toLowerCase().charAt(0) + rugId + String.valueOf(x) + String.valueOf(y) + String.valueOf(x) + String.valueOf(y + 1);
        if (x<7 & x>=0 & y<7 & y>=0) {
            if (isRugValid(thisGame.gameToString(), rugString) & isPlacementValid(thisGame.gameToString(),rugString)) {
                return "You can place the rug in this square." + rugString;
            } else return "You can not place the rug in this square." + rugString;
        }
        return null;
    }

    //designed three state for choosing the rug
    static HBox buildImage(int a){
        Image image =new javafx.scene.image.Image("C:\\Users\\Lenovo\\IdeaProjects\\comp1110-ass2\\assets\\rug0.png");
        Image image1 =new javafx.scene.image.Image("C:\\Users\\Lenovo\\IdeaProjects\\comp1110-ass2\\assets\\rug1.png");
        Image image2 =new javafx.scene.image.Image("C:\\Users\\Lenovo\\IdeaProjects\\comp1110-ass2\\assets\\rug2.png");
        Image image3 =new javafx.scene.image.Image("C:\\Users\\Lenovo\\IdeaProjects\\comp1110-ass2\\assets\\rug3.png");
        ImageView imageView = new ImageView(image);
        ImageView imageView1 = new ImageView(image1);
        ImageView imageView2 = new ImageView(image2);
        ImageView imageView3 = new ImageView(image3);
        HBox hBox = new HBox();
        hBox.setSpacing(30);
        if(a==0){
            imageView.setFitHeight(80);
            imageView.setFitWidth(50);
            imageView1.setFitHeight(50);
            imageView1.setFitWidth(80);
            hBox.getChildren().add(imageView);
            hBox.getChildren().add(imageView1);
        } else if (a==1) {
            imageView2.setFitHeight(80);
            imageView2.setFitWidth(50);
            imageView1.setFitHeight(50);
            imageView1.setFitWidth(80);
            hBox.getChildren().add(imageView2);
            hBox.getChildren().add(imageView1);
        }else {
            imageView.setFitHeight(80);
            imageView.setFitWidth(50);
            imageView3.setFitHeight(50);
            imageView3.setFitWidth(80);
            hBox.getChildren().add(imageView);
            hBox.getChildren().add(imageView3);
        }
        return hBox;
    }

    void winner(){
        char a = getWinner(thisGame.gameToString());
        String message = new String();
        if(a == 't'){
            message = "It's a tile. Let's start over";
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
        if(message!=null){
            VBox vBox = new VBox();
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
        createPhase1();
        createPhase2();
        createPhase3();
        winner();
        restart();
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
          Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
          root.getChildren().add(controls);
          primaryStage.setScene(scene);
          primaryStage.show();
    }
}
