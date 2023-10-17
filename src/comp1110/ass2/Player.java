package comp1110.ass2;

import javafx.scene.paint.Color;

public class Player{

    /**
     * AUTHORSHIP:
     *
     * colour represents different players. we have four players in this game,so we have cyan,yellow,red,purple as their colors
     * money represents the remaining dirhams
     * rugsNumber represents the remaining numbers of rugs, it should be a two-digit number. 00 when there is no rugs. 15 is the starting number.
     * gameState represents if players are in the game or not. 'p' represents they are in the game, 'o' represents out of the game.
     *
     * stringToPlayer was written by Benjamin Campbell
     * playerToString was written by Jiawei Li
     * we both checked and debugged each other's code
     *
     * @author <u7531534><Jiawei Li>/ <u7471333><Benjamin Campbell>
     */
    private Color colour;
    private int money;
    private int rugsNumber;
    private Boolean isPlaying;

    public Player(Color colour, int money, int rugsNumber, Boolean isPlaying){
        this.colour = colour;
        this.money = money;
        this.rugsNumber = rugsNumber;
        this.isPlaying = isPlaying;
    }

    public Color getColour() {
        return this.colour;
    }

    public int getMoney() {
        return this.money;
    }

    public int getRugsNumber() {
        return this.rugsNumber;
    }

    public Boolean getIsPlaying() { return this.isPlaying; }

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

    //Since we can't return the color as a string directly from the javafx function, we need to create a method to do that.
    public static String getColorName(Color color) {
        if (color.equals(Color.RED)) {
            return "Red";
        } else if (color.equals(Color.CYAN)) {
            return "Cyan";
        } else if (color.equals(Color.YELLOW)) {
            return "Yellow";
        } else if (color.equals(Color.PURPLE)) {
            return "Purple";
        }
         else {
            return "Unknown";
        }}

    public void playerPays(int payment) {
        if(payment > money) money = 0;
        else  money = money - payment;
    }

    public static int getActualPayAmount(int payment, int a){
        if(payment>a) return a;
        else return  payment;
    }

    public void playerIsPaid(int payment) {
       money = money+payment;
    }

    public void playerOut(){
        this.isPlaying = false;
    }

    public void minusRug() {
        rugsNumber = rugsNumber - 1;
    }

    //input a player output a string
    public static String playerToString(Player player){
        String moneyString;
        String rugsNumberString;
        String isPlayingString;
        String playerString;
        //Since the rugsNumber's type is int, so we need add "0" in front of the int to generate a string
        if(player.rugsNumber<10){
            rugsNumberString = "0" + String.valueOf(player.rugsNumber);
        }else{
            rugsNumberString = String.valueOf(player.getRugsNumber());
        }
        //different cases to add "0";
        if (player.money<100 & player.money>=10){
            moneyString = "0"+String.valueOf(player.money);
        } else if(player.money<10){moneyString ="00" +String.valueOf(player.money);
        } else{
            moneyString = String.valueOf(player.money);
        }
        if(player.isPlaying == true){isPlayingString = "i";}
        else{isPlayingString = "o";}
        playerString = "P" + getColorName(player.colour).toLowerCase().charAt(0)+moneyString+rugsNumberString+isPlayingString;
        return playerString;
    }


//    /**
//     * testing code here.
//     * @param args
//     * @throws Exception
//     */
//    public static void main(String[] args) throws Exception {
//        String input1 = "Pr12345i";
//        String input2 = "Pc00415o";
//        Player player1 = stringToPlayer(input1);
//        Player player2 = stringToPlayer(input2);
//        System.out.println("String 1: " + input1);
//        System.out.println("Player1 colour: " + player1.colour);
//        System.out.println("Player1 money: " + player1.money);
//        System.out.println("Player1 rugs: " + player1.rugsNumber);
//        System.out.println("Player1 is playing: " + player1.isPlaying);
//        System.out.println("String 2: " + input2);
//        System.out.println("Player2 colour: " + player2.colour);
//        System.out.println("Player2 money: " + player2.money);
//        System.out.println("Player2 rugs: " + player2.rugsNumber);
//        System.out.println("Player2 is playing: " + player2.isPlaying);
//        System.out.println(plyaerToString(player2));
//    }

}
