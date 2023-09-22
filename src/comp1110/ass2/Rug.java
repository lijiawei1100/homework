package comp1110.ass2;

import javafx.scene.paint.Color;
import javafx.util.Pair;

public class Rug {
    private Color colour;
    private final int id;
    /*
    changed id type to integer instead of string - makes it easier to keep track of how many
    rugs have been placed and make for better object-oriented programming
    */

    Rug(Color colour, int id) {
        this.colour = colour;
        this.id = id;
    }

    public Color getColour() {
        return this.colour;
    }

    public static Rug stringToAbbreviatedRug(String inputString) throws Exception {
        Color colour;
        char colourChar = inputString.charAt(0);
        if (colourChar == 'n') {
           return null;
        } else if (colourChar == 'c') {
            colour = Color.CYAN;
        } else if (colourChar == 'y') {
            colour = Color.YELLOW;
        } else if (colourChar == 'r') {
            colour = Color.RED;
        } else if (colourChar == 'p') {
            colour = Color.PURPLE;
        } else {
            throw new Exception("colour is not a valid character");
//            for testing
//            throw new Exception(String.valueOf(colourChar));
        }
        int id;
        String idString = inputString.substring(1, 3);
        id = Integer.parseInt(idString);
        return (new Rug(colour, id));
    }

//    /**
//     * for testing
//     * @param args
//     * @throws Exception
//     */
//    public static void main(String[] args) throws Exception {
//        String input1 = "n00";
//        String input2 = "p04";
//        Rug rug2 = stringToAbbreviatedRug(input2);
//        System.out.println("Rug1 string: " + input1);
//        System.out.println("Rug1 is null");
//        System.out.println("Rug2 string: " + input2);
//        System.out.println("Rug2 colour: " + rug2.colour.toString());
//        System.out.println("Rug2 id: " + rug2.id);
//    }
}




