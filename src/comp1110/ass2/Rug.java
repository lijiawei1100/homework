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

    //created rugwithposition class to help complete the task10
    static class RugWithPosition extends Rug{
        Pair<Integer,Integer> p1;
        Pair<Integer,Integer> p2;
        RugWithPosition(Color colour, int id,Pair<Integer,Integer> firstPosition,Pair<Integer,Integer>secondPosition) {
            super(colour, id);
            this.p1 = firstPosition;
            this.p2 =secondPosition;
        }
        public static RugWithPosition stringToRugWithPosition(String rugString){
            Color colour = null;
            char colourChar = rugString.charAt(0);
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
            }
            String idString = rugString.substring(1, 3);
            Pair<Integer,Integer> firstPosition = new Pair<Integer,Integer>(Integer.parseInt(rugString.substring(3,4)),Integer.parseInt(rugString.substring(4,5)));
            Pair<Integer,Integer> secondPosition = new Pair<Integer,Integer>(Integer.parseInt(rugString.substring(5,6)),Integer.parseInt(rugString.substring(6,7)));
            return new RugWithPosition(colour,Integer.parseInt(idString),firstPosition,secondPosition);
        }

    }

    public Color getColour() {
        return this.colour;
    }
    public int getId() { return this.id; }

    public static Rug stringToAbbreviatedRug(String inputString) {
        Color colour = null;
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
//        }
//            else {
//            throw new Exception("colour is not a valid character");
//            for testing
//            throw new Exception(String.valueOf(colourChar));
        }
        String idString = inputString.substring(1, 3);
        return (new Rug(colour, Integer.parseInt(idString)));
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




