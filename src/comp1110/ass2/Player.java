package comp1110.ass2;

import javafx.scene.paint.Color;

public class Player{

    /**
     * colour represents different players. we have four players in this game,so we have cyan,yellow,red,purple as their colors
     * money represents the remaining dirhams
     * rugsNumber represents the remaining numbers of rugs, it should be a two-digit number. 00 when there is no rugs. 15 is the starting number.
     * gameState represents if players are in the game or not. 'p' represents they are in the game, 'o' represents out of the game.
     */
    public Color colour;
    private int money;
    private int rugsNumber;
    private Boolean isPlaying;

    public Player(Color colour, int money, int rugsNumber, Boolean isPlaying){
        this.colour = colour;
        this.money = money;
        this.rugsNumber = rugsNumber;
        this.isPlaying = isPlaying;
    }

    public static Player stringToPlayer(String inputString) throws Exception {
        Color colour;
        char colourChar = inputString.charAt(1);
        if (colourChar == 'c') {
            colour = Color.CYAN;
        } else if (colourChar == 'y') {
            colour = Color.YELLOW;
        } else if(colourChar == 'r') {
            colour = Color.RED;
        } else if(colourChar == 'p') {
            colour = Color.PURPLE;
        } else {
            throw new Exception("colour is not a valid character");
        }
        int money;
        String dihramString = inputString.substring(2,5);
        money = Integer.parseInt(dihramString);
        int rugsNumber;
        String rugsString = inputString.substring(5,7);
        rugsNumber = Integer.parseInt(rugsString);
        Boolean isPlaying;
        char isPlayingChar = inputString.charAt(7);
        if (isPlayingChar == 'i') {
            isPlaying = Boolean.TRUE;
        } else if (isPlayingChar == 'o') {
            isPlaying = Boolean.FALSE;
        } else {
            throw new Exception("isPlaying not a valid character");
        }
        return (new Player(colour,money,rugsNumber,isPlaying));
    }

    /**
     * create different players and initiate their color,dirhams,rugsnumber and gamestate.
     */
    public static final Player CYAN = new Player(Color.CYAN,0,15,Boolean.TRUE);
    public static final Player YELLOW = new Player(Color.YELLOW,0,15,Boolean.TRUE);
    public static final Player RED = new Player(Color.RED,0,15,Boolean.TRUE);
    public static final Player PURPLE = new Player(Color.PURPLE,0,15,Boolean.TRUE);

    public static void main(String[] args) throws Exception {
        String input1 = "Pr12345i";
        String input2 = "Pc00415o";
        Player player1 = stringToPlayer(input1);
        Player player2 = stringToPlayer(input2);
        System.out.println("String 1: " + input1);
        System.out.println("Player1 colour: " + player1.colour);
        System.out.println("Player1 money: " + player1.money);
        System.out.println("Player1 rugs: " + player1.rugsNumber);
        System.out.println("Player1 is playing: " + player1.isPlaying);
        System.out.println("String 2: " + input2);
        System.out.println("Player2 colour: " + player2.colour);
        System.out.println("Player2 money: " + player2.money);
        System.out.println("Player2 rugs: " + player2.rugsNumber);
        System.out.println("Player2 is playing: " + player2.isPlaying);

//        for(int n = 0; n<4;n++){
//            Player[] player;
//            player[n] = (Player) stringToPlayer(input1);
//        }

    }
}
