package comp1110.ass2;

import javafx.util.Pair;

public class Rug {
    private String colour;
    private Square[] squares ;
    private int id;
    /*
    changed id type to integer instead of string - makes it easier to keep track of how many
    rugs have been placed and make for better object-oriented programming
    */

    Rug(String colour,int id, Square[] squares){ //maybe Square[2]?
        this.colour = colour;
        this.id = id;
        this.squares = squares;
    }

    /**
     * initialize new rug
     */
    public static Rug RUG_01 = new Rug("red",0, new Square[]{new Square(false, new Pair<>(3, 4),null), new Square(true, new Pair<>(4, 5),null)});

    /**
     *A Game string is the concatenation of one player string for each player, followed by one Assam string, followed by one board string.
     * @param rug : created rugs
     * @return :return the information of rugs by using string
     */
    public static String rugToString(Rug rug) {
        return (rug.colour.charAt(0) +""+ rug.id + rug.squares[0].position.getKey()+rug.squares[0].position.getValue().toString()
            +rug.squares[1].position.getKey()+rug.squares[1].position.getValue().toString());
    }
    //change this to make the 'id' field always two digits
    //also add function stringToRug!!!

    public static void main(String[] args) {
        System.out.println(rugToString(RUG_01));
    }
}
