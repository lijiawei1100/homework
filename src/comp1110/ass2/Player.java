package comp1110.ass2;
public class Player{

    /**
     * colour represents different players. we have four players in this game,so we have cyan,yellow,red,purple as their colors
     * money represents the remaining dirhams
     * rugsNumber represents the remaining numbers of rugs, it should be a two-digit number. 00 when there is no rugs. 15 is the starting number.
     * gameState represents if players are in the game or not. 'p' represents they are in the game, 'o' represents out of the game.
     */
    public String colour;
    private String money;
    private int rugsNumber;
    private char gameState;

    Player(String colour, String money , int rugsNumber, char gameState){
        this.colour = colour;
        this.money = money;
        this.rugsNumber = rugsNumber;
        this.gameState = gameState;
    }

    public static Player stringToPlayer(String inputString) throws Exception {
        String colour;
        char colourChar = inputString.charAt(2);
        if (colourChar == 'c') {
            colour = "cyan";
        } if (colourChar == 'y') {
            colour = "yellow";
        } if(colourChar == 'r') {
            colour = "red";
        } if(colourChar == 'p') {
            colour = "purple";
        } else {
            throw new Exception("colour is not a valid character");
        }
        Player player = new Player(colour,);
        return (player);
    }

    /**
     * create different players and initiate their color,dirhams,rugsnumber and gamestate.
     */
    public static final Player CYAN = new Player("cyan","000",15,'i');
    public static final Player YELLOW = new Player("yellow","000",15,'i');
    public static final Player RED = new Player("red","000",15,'i');
    public static final Player PURPLE = new Player("purple","000",15,'i');

    /**
     * @param player one of the four players
     * @return return playerString to easily express their state
     */
    public static String playerToString(Player player) {
        return ("P"+ player.colour.charAt(0) + player.money + player.rugsNumber + player.gameState);
    }
    public static void main(String[] args) {
        System.out.println(playerToString(CYAN));
        System.out.println(playerToString(YELLOW));
        System.out.println(playerToString(RED));
        System.out.println(playerToString(PURPLE));
    }
}
