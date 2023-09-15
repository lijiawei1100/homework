package comp1110.ass2;

import javafx.util.Pair;

public class Rug {
    private String colour;
    private Square[] squares ;
    private String id;

    Rug(String colour,String id, Square[] squares){
        this.colour = colour;
        this.id = id;
        this.squares = squares;
    }

    /**
     * initialize new rug
     */
    public static Rug RUG_01 = new Rug("red","00", new Square[]{new Square(false, new Pair<>(3, 4),"n00"), new Square(true, new Pair<>(4, 5),"n00")});

    /**
     *A Game string is the concatenation of one player string for each player, followed by one Assam string, followed by one board string.
     * @param rug : created rugs
     * @return :return the information of rugs by using string
     */
    public static String rugToString(Rug rug) {
        return (rug.colour.charAt(0) +""+ rug.id + rug.squares[0].position.getKey()+rug.squares[0].position.getValue().toString()
            +rug.squares[1].position.getKey()+rug.squares[1].position.getValue().toString());
    }

    public static void main(String[] args) {
        System.out.println(rugToString(RUG_01));
    }
}
