package comp1110.ass2;

import javafx.util.Pair;

public class Rug {
    private String colour;
//    private Square[] squares;
    private int id;
    /*
    changed id type to integer instead of string - makes it easier to keep track of how many
    rugs have been placed and make for better object-oriented programming
    */

    Rug(String colour,int id){ //removed Square[] squares
        this.colour = colour;
        this.id = id;
//        this.squares = squares;
    }

    public static Rug stringToAbbreviatedRug(String inputString) throws Exception {
        String colour;
        char colourChar = inputString.charAt(1);
        if (colourChar == 'c') {
            colour = "cyan";
        } else if (colourChar == 'y') {
            colour = "yellow";
        } else if(colourChar == 'r') {
            colour = "red";
        } else if(colourChar == 'p') {
            colour = "purple";
        } else {
            throw new Exception("colour is not a valid character");
        }
        int id;
        String idString = inputString.substring(1,3);
        id = Integer.parseInt(idString);
        return (new Rug(colour,id));
    }

    /**
     * initialize new rug
     */
    public static Rug RUG_01 = new Rug("red",0);

//    /**
//     *A Game string is the concatenation of one player string for each player, followed by one Assam string, followed by one board string.
//     * @param rug : created rugs
//     * @return :return the information of rugs by using string
//     */
//    public static String rugToString(Rug rug) {
//        return (rug.colour.charAt(0) +""+ rug.id + rug.squares[0].position.getKey()+rug.squares[0].position.getValue().toString()
//            +rug.squares[1].position.getKey()+rug.squares[1].position.getValue().toString());
//    }
    //change this to make the 'id' field always two digits
    //also add function stringToRug!!!

    public static void main(String[] args) {
//        System.out.println(rugToString(RUG_01));
    }
}
