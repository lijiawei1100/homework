package comp1110.ass2;
public class Player{

    /**
     * colour represents different players. we have four players in this game,so we have cyan,yellow,red,purple as their colors
     * money represents the remaining dirhams
     * rugsNumber represents the remaining numbers of rugs, it should be a two-digit number. 00 when there is no rugs. 15 is the starting number.
     * gameState represents if players are in the game or not. 'p' represents they are in the game, 'o' represents out of the game.
     */
    public String colour;
    private int money;
    private int rugsNumber;
    private Boolean isPlaying;

    Player(String colour, int money , int rugsNumber, Boolean isPlaying){
        this.colour = colour;
        this.money = money;
        this.rugsNumber = rugsNumber;
        this.isPlaying = isPlaying;
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
        int money;
        String dihramString = inputString.substring(3,5);
        money = Integer.parseInt(dihramString);
        int rugsNumber;
        String rugsString = inputString.substring(6,7);
        rugsNumber = Integer.parseInt(rugsString);
        Boolean isPlaying;
        char isPlayingChar = inputString.charAt(8);
        if (isPlayingChar == 'i') {
            isPlaying = Boolean.FALSE;
        } if (isPlayingChar == 'y') {
            isPlaying = Boolean.TRUE;
        } else {
            throw new Exception("isPlaying not a valid character");
        }
        Player player = new Player(colour,money,rugsNumber,isPlaying);
        return (player);
    }

    /**
     * create different players and initiate their color,dirhams,rugsnumber and gamestate.
     */
    public static final Player CYAN = new Player("cyan",0,15,Boolean.TRUE);
    public static final Player YELLOW = new Player("yellow",0,15,Boolean.TRUE);
    public static final Player RED = new Player("red",0,15,Boolean.TRUE);
    public static final Player PURPLE = new Player("purple",0,15,Boolean.TRUE);

    /**
     * @param player one of the four players
     * @return return playerString to easily express their state
     */
    public static String playerToString(Player player) {
        return ("P"+ player.colour.charAt(0) + player.money + player.rugsNumber + player.isPlaying);
    }
    public static void main(String[] args) {
        System.out.println(playerToString(CYAN));
        System.out.println(playerToString(YELLOW));
        System.out.println(playerToString(RED));
        System.out.println(playerToString(PURPLE));
    }
}
