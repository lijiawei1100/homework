package comp1110.ass2;

import comp1110.ass2.gui.Game;
import javafx.scene.paint.Color;
import java.util.*;
import static comp1110.ass2.Player.getColorName;
import static comp1110.ass2.Rug.*;

public class Marrakech {
    /**
     * AUTHORSHIP
     * isRugValid,isGameOver,rotateAssam,isPlacementValid,getAdjacentSquares,
     * getPaymentAmount,getWinner,moveAssam and makePlacement were written by JiaWei Li
     * rollDie was written by Benjamin Campbell
     * we both checked and debugged each other's code
     *
     * @author <u7531534><Jiawei Li>/ <u7471333><Benjamin Campbell>
     */

    /**
     * Determine whether a rug String is valid.
     * For this method, you need to determine whether the rug String is valid, but do not need to determine whether it
     * can be placed on the board (you will determine that in Task 10 ). A rug is valid if and only if all the following
     * conditions apply:
     *  - The String is 7 characters long
     *  - The first character in the String corresponds to the colour character of a player present in the game
     *  - The next two characters represent a 2-digit ID number
     *  - The next 4 characters represent coordinates that are on the board
     *  - The combination of that ID number and colour is unique
     * To clarify this last point, if a rug has the same ID as a rug on the board, but a different colour to that rug,
     * then it may still be valid. Obviously multiple rugs are allowed to have the same colour as well so long as they
     * do not share an ID. So, if we already have the rug c013343 on the board, then we can have the following rugs
     *  - c023343 (Shares the colour but not the ID)
     *  - y013343 (Shares the ID but not the colour)
     * But you cannot have c014445, because this has the same colour and ID as a rug on the board already.
     * @param gameString A String representing the current state of the game as per the README
     * @param rug A String representing the rug you are checking
     * @return true if the rug is valid, and false otherwise.
     */
    public static boolean isRugValid(String gameString, String rug) {
        // FIXME: Task 4
         //1.to see if the string is 7 characters long
        if(rug.length()!=7) return false;
        //2.to see if the first character in the String corresponds to the color in the game
        List list = new ArrayList();
        list.add('r');
        list.add('y');
        list.add('c');
        list.add('p');
        if (list.contains(rug.charAt(0)) == false) return false;
        //3. to see if the next two characters are digits
        if (!Character.isDigit(rug.charAt(1))|!Character.isDigit(rug.charAt(2))) return false;
        //4. to see if the next four characters are digits and on the board. To see if the position of the two squares are next to each other.
        boolean boolean_1 = true;
        boolean boolean_2 = false;
        boolean boolean_3 = false;

        for (int i = 0;i<4;i++) {
            if (!Character.isDigit(rug.charAt(i+3))) {
                boolean_1 = false;
            }
        } //check whether last four characters are digits

        if ((((int)rug.charAt(3) == (int)rug.charAt(5)) & (Math.abs((int)rug.charAt(4)-(int)rug.charAt(6)) == 1))
                | (((int)rug.charAt(4) == (int)rug.charAt(6)) & (Math.abs((int)rug.charAt(3)-(int)rug.charAt(5)) == 1))) {
            boolean_2 = true;
        } //check whether the coordinates are next to each other

        if (Character.getNumericValue(rug.charAt(3))<7 & Character.getNumericValue(rug.charAt(4))<7
                & Character.getNumericValue(rug.charAt(5))<7 & Character.getNumericValue(rug.charAt(6))<7) {
            boolean_3 = true;
        } //check whether the coordinates are on the board

        if ((boolean_1 & (boolean_2) & (boolean_3)) ==false) return false;

        //5. to see if the same rug is on the board
        List list_1 = new ArrayList();
        for(int j =0;j<gameString.length();j++) {
            if(gameString.charAt(j) == 'B') {
                for (int i=0;i<49 ;i++){
                    list_1.add(gameString.substring(i * 3 + j+1, i * 3 + j+4));
                    if (list_1.contains(rug.substring(0, 3)) == true) return false;
                }
            }
        }
        return true;
    }

    /**
     * Roll the special Marrakech die and return the result.
     * Note that the die in Marrakech is not a regular 6-sided die, since there
     * are no faces that show 5 or 6, and instead 2 faces that show 2 and 3. That
     * is, of the 6 faces
     *  - One shows 1
     *  - Two show 2
     *  - Two show 3
     *  - One shows 4
     * As such, in order to get full marks for this task, you will need to implement
     * a die where the distribution of results from 1 to 4 is not even, with a 2 or 3
     * being twice as likely to be returned as a 1 or 4.
     * @return The result of the roll of the die meeting the criteria above
     */
    public static int rollDie() {
        Random rand = new Random();
        int randomNum = rand.nextInt(6) + 1; // return a random integer between 1 and 6
        int MarrakechDie;
        if (randomNum == 1) {
            MarrakechDie = 1;
        } else if (randomNum == 2 || randomNum == 3) {
            MarrakechDie = 2;
        } else if (randomNum == 4 || randomNum == 5) {
            MarrakechDie = 3;
        } else {
            MarrakechDie = 4; //maps the random number to different sides of a Marrakech Die
        }
        return MarrakechDie;
    }

    /**
     * Determine whether a game of Marrakech is over
     * Recall from the README that a game of Marrakech is over if a Player is about to enter the rotation phase of their
     * turn, but no longer has any rugs. Note that we do not encode in the game state String whose turn it is, so you
     * will have to think about how to use the information we do encode to determine whether a game is over or not.
     * @param currentGame A String representation of the current state of the game.
     * @return true if the game is over, or false otherwise.
     */
    public static boolean isGameOver(String currentGame) {
        // FIXME: Task 8
        Game game = Game.stringToGame(currentGame);
        int count=0;
        int countNull=0;
        //since the stringToGame method only create the players who are still existing, we can count the remaining players to see if game is over.
        for (Player i : game.getPlayers()){
            if (i==null) {
                countNull+=1;
            } else if (!i.getIsPlaying()) countNull+=1;
        }
        if (countNull==4) return true;//if all the players are not existing,obviously game is over.
        //otherwise, check remaining players.
        //if one of the players is out of game,and the rugsNumber is 0,then game is over. the string is like "Pr12000o"
        //count the people who have no rug and is still in the game. the string is like"Pr12000i". we need all players have same ending substring like "00i"
        for (Player i : game.getPlayers()){
            if(i!=null){
                //if (i.getIsPlaying() == false & i.getRugsNumber() == 0) return true;
                if (i.getIsPlaying() == true & i.getRugsNumber() == 0) count += 1;
            }
        }
        if (count == game.getPlayers().length-countNull) return true;
        return false;
    }

    /**
     * Implement Assam's rotation.
     * Recall that Assam may only be rotated left or right, or left alone -- he cannot be rotated a full 180 degrees.
     * For example, if he is currently facing North (towards the top of the board), then he could be rotated to face
     * East or West, but not South. Assam can also only be rotated in 90 degree increments.
     * If the requested rotation is illegal, you should return Assam's current state unchanged.
     * @param currentAssam A String representing Assam's current state
     * @param rotation The requested rotation, in degrees. This degree reading is relative to the direction Assam
     *                 is currently facing, so a value of 0 for this argument will keep Assam facing in his
     *                 current orientation, 90 would be turning him to the right, etc.
     * @return A String representing Assam's state after the rotation, or the input currentAssam if the requested
     * rotation is illegal.
     */
    public static String rotateAssam(String currentAssam, int rotation) {
        // FIXME: Task 9
        // created a new arraylist consisting of 4 valid angle for assam's rotation
        ArrayList<Integer> angle = new ArrayList<Integer>();
        angle.add(0);
        angle.add(90);
        angle.add(180);
        angle.add(270);
        int currentAngle = 0;
        Assam assam = Assam.stringToAssam(currentAssam);
        //if angle not in the list and the rotation is 180,then return the original string.
        if(!angle.contains(rotation)|rotation==180) return currentAssam;
        //calculate the angle
        currentAngle = (assam.getAngle()+rotation)%360 ;
        //check the angle and return a string with orientation
        switch (currentAngle){
            case 0 :return (currentAssam.substring(0,3)+"N");
            case 90 :return (currentAssam.substring(0,3)+"E");
            case 180 :return (currentAssam.substring(0,3)+"S");
            case 270 :return (currentAssam.substring(0,3)+"W");
        }
        return null;
    }

    /**
     * Determine whether a potential new placement is valid (i.e that it describes a legal way to place a rug).
     * There are a number of rules which apply to potential new placements, which are detailed in the README but to
     * reiterate here:
     *   1. A new rug must have one edge adjacent to Assam (not counting diagonals)
     *   2. A new rug must not completely cover another rug. It is legal to partially cover an already placed rug, but
     *      the new rug must not cover the entirety of another rug that's already on the board.
     * @param gameState A game string representing the current state of the game
     * @param rug A rug string representing the candidate rug which you must check the validity of.
     * @return true if the placement is valid, and false otherwise.
     */
    public static boolean isPlacementValid(String gameState, String rug) {
        // FIXME: Task 10
        //create game object using string
        Game game = Game.stringToGame(gameState);
        Assam assam =game.getAssam();
        Board board = game.getBoard();
        //need two Boolean to judge the final state.
        // First one is for the position, to check whether the rug has a proper position
        //the second one is for the rug, you can not place a rug on a entire rug.
        boolean positionBoolean = false;
        boolean rugBoolean = true;
        //create the rugWithPosition
        if(isRugValid(gameState,rug)) {
            Rug.RugWithPosition rugWithPosition = RugWithPosition.stringToRugWithPosition(rug);

            //each position of rug should not equal to assam's positon
            //only one of the rug's positions is next to the assam. like (1,1) next to (1,2) or (2,1) next to (1,1);
            //as long as it satisfies the condition, will return true.
            if (!rugWithPosition.p1.equals(assam.getAssamPosition()) & !rugWithPosition.p2.equals(assam.getAssamPosition())) {
                if ((Math.abs(rugWithPosition.p1.getKey() - assam.getAssamX()) == 1 & rugWithPosition.p1.getValue() == assam.getAssamY())
                        | (Math.abs(rugWithPosition.p1.getValue() - assam.getAssamY()) == 1 & rugWithPosition.p1.getKey() == assam.getAssamX())
                        | (Math.abs(rugWithPosition.p2.getKey() - assam.getAssamX()) == 1 & rugWithPosition.p2.getValue() == assam.getAssamY())
                        | (Math.abs(rugWithPosition.p2.getValue() - assam.getAssamY()) == 1 & rugWithPosition.p2.getKey() == assam.getAssamX())) {
                    positionBoolean = true;
                }
            }

            // returns condition 1. A new rug must have one edge adjacent to Assam (not counting diagonals)

            //check if the position we want to place is not null;
            //when it's not null,compare whether two places have same color and ID, which represent it is an entire rug. if there is not an entire rug, then return true
            if (board.getBoardMatrix()[rugWithPosition.p1.getKey()][rugWithPosition.p1.getValue()].occupiedRug != null &
                    board.getBoardMatrix()[rugWithPosition.p2.getKey()][rugWithPosition.p2.getValue()].occupiedRug != null) {
                Rug occupiedRug1 = board.getBoardMatrix()[rugWithPosition.p1.getKey()][rugWithPosition.p1.getValue()].occupiedRug;
                Rug occupiedRug2 = board.getBoardMatrix()[rugWithPosition.p2.getKey()][rugWithPosition.p2.getValue()].occupiedRug;
                if ((occupiedRug1.getColour() == occupiedRug2.getColour()) & (occupiedRug1.getId() == occupiedRug2.getId())) {
                    rugBoolean = false;
                }
            }
            //return the combination of two situation
            return (positionBoolean & rugBoolean);
        }
        return false;
    }




   //created for task 11
   public static List<Square> getAdjacentSquares (Board board, Square currentSquare){
        List<Square> squaresList = new ArrayList<>();
        int x = currentSquare.position.getKey();
        int y = currentSquare.position.getValue();
        Square[][] boardMatrix = board.getBoardMatrix();
        //to find the neighbour according to the square's position
        if(x<6 & x>0 & y<6 & y>0){
             squaresList.add(boardMatrix[x-1][y]);
             squaresList.add(boardMatrix[x+1][y]);
             squaresList.add(boardMatrix[x][y-1]);
             squaresList.add(boardMatrix[x][y+1]);
        }
        else if (x==0){
            squaresList.add(boardMatrix[x+1][y]);
            if(y==0) {squaresList.add(boardMatrix[x][y+1]);}
            else if (y==6)  {squaresList.add(boardMatrix[x][y-1]);}
            else {squaresList.add(boardMatrix[x][y+1]);squaresList.add(boardMatrix[x][y-1]);}
        }
        else if (x==6) {
            squaresList.add(boardMatrix[x-1][y]);
            if(y==0){squaresList.add(boardMatrix[x][y+1]);}
            else if (y==6) {squaresList.add(boardMatrix[x][y-1]);}
            else {squaresList.add(boardMatrix[x][y+1]);squaresList.add(boardMatrix[x][y-1]);}
        }
        else if (y==0){
            squaresList.add(boardMatrix[x][y+1]);
            if(x==0){squaresList.add(boardMatrix[x+1][y]);}
            else if (x==6) {squaresList.add(boardMatrix[x-1][y]);}
            else {squaresList.add(boardMatrix[x+1][y]);squaresList.add(boardMatrix[x-1][y]);}
        }
        else if(y==6){
            squaresList.add(boardMatrix[x][y-1]);
            if(x==0){squaresList.add(boardMatrix[x+1][y]);}
            else if (x==6) {squaresList.add(boardMatrix[x-1][y]);}
            else {squaresList.add(boardMatrix[x+1][y]);squaresList.add(boardMatrix[x-1][y]);}
        }
       return squaresList;
    }

    public static int getPayment(Assam assam,Board board,Square square,boolean[][] visited,int connectedCount){
        Color payColor = board.getBoardMatrix()[assam.getAssamX()][assam.getAssamY()].occupiedRug.getColour();
        int x = square.position.getKey();
        int y = square.position.getValue();
        //if the square has been visited, then stop and return
        if(visited[x][y]) return connectedCount;
        //if not, mark that it has been visited
        visited[x][y] = true;
        //if the square has the same color as the assam, increase the connectedCount;
        if(square.occupiedRug!=null && square.occupiedRug.getColour()==payColor){
            connectedCount++;
        }else return connectedCount;
        //use recursion here to travel through all it's neighbour
        for (Square neighbor: getAdjacentSquares(board,square)){
            connectedCount =getPayment(assam,board,neighbor,visited,connectedCount);
        }
        return connectedCount;
    }

    /**
     * Determine the amount of payment required should another player land on a square.
     * For this method, you may assume that Assam has just landed on the square he is currently placed on, and that
     * the player who last moved Assam is not the player who owns the rug landed on (if there is a rug on his current
     * square). Recall that the payment owed to the owner of the rug is equal to the number of connected squares showing
     * on the board that are of that colour. Similarly to the placement rules, two squares are only connected if they
     * share an entire edge -- diagonals do not count.
     * @param gameString A String representation of the current state of the game.
     * @return The amount of payment due, as an integer.
     */
    public static int getPaymentAmount(String gameString) {
        //some attempts for this task,for temporary
        boolean[][] visited =new boolean[7][7];
        int connectedCount = 0;
        Game game = Game.stringToGame(gameString);
        Assam assam = game.getAssam();
        Board board = game.getBoard();
        Square square = board.getBoardMatrix()[assam.getAssamX()][assam.getAssamY()];
        connectedCount = getPayment(assam,board,square,visited,connectedCount);
        return connectedCount;
        // FIXME: Task 11
    }

    /**
     * Determine the winner of a game of Marrakech.
     * For this task, you will be provided with a game state string and have to return a char representing the colour
     * of the winner of the game. So for example if the cyan player is the winner, then you return 'c', if the red
     * player is the winner return 'r', etc...
     * If the game is not yet over, then you should return 'n'.
     * If the game is over, but is a tie, then you should return 't'.
     * Recall that a player's total score is the sum of their number of dirhams and the number of squares showing on the
     * board that are of their colour, and that a player who is out of the game cannot win. If multiple players have the
     * same total score, the player with the largest number of dirhams wins. If multiple players have the same total
     * score and number of dirhams, then the game is a tie.
     * @param gameState A String representation of the current state of the game
     * @return A char representing the winner of the game as described above.
     */
    public static char getWinner(String gameState) {
        // FIXME: Task 12
        Game game = Game.stringToGame(gameState);
        Board board = game.getBoard();
        Player[] players = game.getPlayers();
        //use map to match totalScore and players
        Map mapTotalScore = new HashMap();
        //use map to match rugScore and players
        Map mapDirhamsScore = new HashMap();
        //use arrayList to sort the map according to the score
        ArrayList<Map.Entry<String, Integer>> listTotalScore = null;
        ArrayList<Map.Entry<String, Integer>> listDirhamsScore = null;
        ArrayList<Integer> remainingListDirhamsScoreIndex = new ArrayList<>();
        ArrayList<Integer> listTotalScoreIndex = new ArrayList<>();
        //check the gamestate,if it's over,then compare the score
        ArrayList<Map.Entry<String, Integer>> remainingListDirhamsScore = null;
        if (isGameOver(gameState)) {
            //for each existing players, use for loop to calculate their rugs on board and store it in rugsScore
            for (Player player : players) {
                if(player !=null){
                    int rugsOnBoardNumber = 0;
                    int dirhams;
                    for (int i = 0; i < 7; i++) {
                        for (int j = 0; j < 7; j++) {
                            if (board.getBoardMatrix()[i][j].occupiedRug != null) {
                                if (board.getBoardMatrix()[i][j].occupiedRug.getColour() == player.getColour())
                                    rugsOnBoardNumber += 1;
                            }
                        }
                    }
                    dirhams = player.getMoney();
                    // match players and the score using map
                    mapTotalScore.put(getColorName(player.getColour()), (rugsOnBoardNumber + dirhams));
                    mapDirhamsScore.put(getColorName(player.getColour()), dirhams);
                }
            }
            //a map is hard to sort, so turn it into an arraylist
            listTotalScore = new ArrayList<>(mapTotalScore.entrySet());
            listDirhamsScore = new ArrayList<>(mapDirhamsScore.entrySet());

            //sort list
            Collections.sort(listTotalScore, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o1.getValue() - o2.getValue();
                }
            });


            //traverse from the last one to the first one, since the largest number is at the end.
            for (int i = mapTotalScore.size() - 1; i >= 0; i--) {
                if (i != 0) {
                    //to compare numbers: if they are equal, then add the index and continue.if not,add the index and then break.
                    if (!listTotalScore.get(i - 1).getValue().equals(listTotalScore.get(i).getValue())) {
                        listTotalScoreIndex.add(i);
                        break;
                    } else listTotalScoreIndex.add(i);
                    //when the i==0,it means all numbers are equal,so we need to add all indexes.
                } else if (i == 0) {
                    listTotalScoreIndex.add(i);
                }
            }

            //to see if there is only one index for the largest number.if there exists more than two indexes,we should compare the rugScore.
            if (listTotalScoreIndex.size() != 1) {
                //store those remaining players who have the same largest totalScore.
                remainingListDirhamsScore = new ArrayList();
                for (int e : listTotalScoreIndex) {
                    for (int k = 0; k < listDirhamsScore.size(); k++) {
                        if (listTotalScore.get(e).getKey() == listDirhamsScore.get(k).getKey()) {
                            remainingListDirhamsScore.add(listDirhamsScore.get(k));
                        }
                    }
                }

                //sort the remaining list here.
                Collections.sort(remainingListDirhamsScore, new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                        return o1.getValue() - o2.getValue();
                    }
                });
                //use the same way to compare the rugScore
                for (int i = remainingListDirhamsScore.size() - 1; i >= 0; i--) {
                    if (i != 0) {
                        if (!remainingListDirhamsScore.get(i - 1).getValue().equals(remainingListDirhamsScore.get(i).getValue())) {
                            remainingListDirhamsScoreIndex.add(i);
                            break;
                        } else remainingListDirhamsScoreIndex.add(i);
                    } else if (i == 0) {
                        remainingListDirhamsScoreIndex.add(i);
                    }
                }
                if (remainingListDirhamsScoreIndex.size() == 1)
                    return Character.toLowerCase(remainingListDirhamsScore.get(remainingListDirhamsScoreIndex.get(0)).getKey().charAt(0));
                else  return  't';
            }
            else
                return (Character.toLowerCase(listTotalScore.get(listTotalScoreIndex.get(0)).getKey().charAt(0)));
        } else return 'n';
    }

    /**
     * Implement Assam's movement.
     * Assam moves a number of squares equal to the die result, provided to you by the argument dieResult. Assam moves
     * in the direction he is currently facing. If part of Assam's movement results in him leaving the board, he moves
     * according to the tracks diagrammed in the assignment README, which should be studied carefully before attempting
     * this task. For this task, you are not required to do any checking that the die result is sensible, nor whether
     * the current Assam string is sensible either -- you may assume that both of these are valid.
     * @param currentAssam A string representation of Assam's current state.
     * @param dieResult The result of the die, which determines the number of squares Assam will move.
     * @return A String representing Assam's state after the movement.
     */
    public static String moveAssam(String currentAssam, int dieResult){
        // FIXME: Task 13
        //get assam's positions and direction
        int xPosition = Integer.parseInt(currentAssam.substring(1,2));
        int yPosition = Integer.parseInt(currentAssam.substring(2,3));
        String currentDirecton = currentAssam.substring(3,4);

        for(int i=0;i<dieResult;i++) {
            //when assam is not on the edge and not facing out, use the normal way to move assam
            if (((xPosition < 6) & (xPosition > 0) & (yPosition < 6) & (yPosition >0))
                    | (!(yPosition==6 & currentDirecton.equals("S")))&(!(xPosition==6 & currentDirecton.equals("E")))&(!(xPosition==0 & currentDirecton.equals("W")))&(!(yPosition==0 & currentDirecton.equals("N"))))
            {
                switch (currentDirecton) {
                    case "N":
                        yPosition -= 1;
                        break;
                    case "E":
                        xPosition += 1;
                        break;
                    case "S":
                        yPosition += 1;
                        break;
                    case "W":
                        xPosition -= 1;
                        break;
                }
            }
            //otherwise, when it's on the edge, we need to move assam according to his position
            else{
                if(yPosition==6 & xPosition!=0 & xPosition%2==1 & currentDirecton.equals("S")){ xPosition+=1; currentDirecton="N";}
                else if(yPosition==6 & xPosition!=0 & xPosition%2==0 & currentDirecton.equals("S")) {xPosition-=1; currentDirecton="N";}
                else if(xPosition==6 & yPosition!=0 & yPosition%2==1 & currentDirecton.equals("E")) {yPosition+=1; currentDirecton="W";}
                else if(xPosition==6 & yPosition!=0 & yPosition%2==0 & currentDirecton.equals("E")) {yPosition-=1; currentDirecton="W";}
                else if(xPosition!=6 & yPosition==0 & xPosition%2==1 & currentDirecton.equals("N")) {xPosition-=1; currentDirecton="S";}
                else if(xPosition!=6 & yPosition==0 & xPosition%2==0 & currentDirecton.equals("N")) {xPosition+=1; currentDirecton="S";}
                else if(xPosition==0 & yPosition!=6 & yPosition%2==1 & currentDirecton.equals("W")) {yPosition-=1; currentDirecton="E";}
                else if(xPosition==0 & yPosition!=6 & yPosition%2==0 & currentDirecton.equals("W")) {yPosition+=1; currentDirecton="E";}
                else if(xPosition==0 & yPosition==6 & currentDirecton.equals("S")){currentDirecton ="E";}
                else if(xPosition==0 & yPosition==6 & currentDirecton.equals("W")){currentDirecton ="N";}
                else if(xPosition==6 & yPosition==0 & currentDirecton.equals("E")){currentDirecton ="S";}
                else if(xPosition==6 & yPosition==0 & currentDirecton.equals("N")){currentDirecton ="W";}
                }
            }
        return "A"+ xPosition + yPosition +currentDirecton;
    }

    /**
     * Place a rug on the board
     * This method can be assumed to be called after Assam has been rotated and moved, i.e in the placement phase of
     * a turn. A rug may only be placed if it meets the conditions listed in the isPlacementValid task. If the rug
     * placement is valid, then you should return a new game string representing the board after the placement has
     * been completed. If the placement is invalid, then you should return the existing game unchanged.
     * @param currentGame A String representation of the current state of the game.
     * @param rug A String representation of the rug that is to be placed.
     * @return A new game string representing the game following the successful placement of this rug if it is valid,
     * or the input currentGame unchanged otherwise.
     */
    public static String makePlacement(String currentGame, String rug) {
        // FIXME: Task 14

        Game game = Game.stringToGame(currentGame);
        RugWithPosition entireRug = RugWithPosition.stringToRugWithPosition(rug);
        //check if the placement and the rug are valid
        if (isRugValid(currentGame, rug)) {
            if (isPlacementValid(currentGame, rug)) {
                for (int i = 0; i < currentGame.length(); i++) {
                    if ((currentGame.substring(i, i + 1).equals("P")) & (currentGame.substring(i + 1, i + 2).equals(rug.substring(0, 1)))) {
                        String newRugsNumber = "";
                        Integer currentRugsnumber = Integer.parseInt(currentGame.substring(i + 5, i + 7)) - 1;
                        //transfer Integer to String, if it's less than 10 ,then add a "0" before the string.
                        if (Integer.parseInt(currentGame.substring(i + 5, i + 7)) - 1 >= 10) {
                            newRugsNumber = String.valueOf(currentRugsnumber);
                        } else newRugsNumber = "0" + String.valueOf(currentRugsnumber);
                        currentGame = currentGame.substring(0, i + 5) + newRugsNumber + currentGame.substring(i + 7);
                    }
                    //change the currentGame after place a rug
                    if (currentGame.charAt(i) == 'B') {
                        currentGame = currentGame.substring(0, i + 3 * (7 * entireRug.p1.getKey() + entireRug.p1.getValue()) + 1) + rug.substring(0, 3) + currentGame.substring(i + 3 * (7 * entireRug.p1.getKey() + entireRug.p1.getValue()) + 4);
                        currentGame = currentGame.substring(0, i + 3 * (7 * entireRug.p2.getKey() + entireRug.p2.getValue()) + 1) + rug.substring(0, 3) + currentGame.substring(i + 3 * (7 * entireRug.p2.getKey() + entireRug.p2.getValue()) + 4);
                        break;
                    }
                }
                return currentGame;
            }
            //if rug is not valid or placement is not valid,return the unchanged gameString.
        }
        return currentGame;
    }

}
