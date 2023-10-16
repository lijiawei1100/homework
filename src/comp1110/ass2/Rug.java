package comp1110.ass2;

import javafx.scene.paint.Color;
import javafx.util.Pair;

import static comp1110.ass2.Player.getColorName;

/**
 * AUTHORSHIP:
 *
 * RugWithPosition class was written by Jiawei Li
 * rugWithPositionToString and stringToAbbreviatedRug were written by Benjamin Campbell
 * we both checked and debugged each other's code
 *
 * @author <u7531534><Jiawei Li>/ <u7471333><Benjamin Campbell>
 */

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

    //created RugWithPosition class to help complete the task10,and this class is a subclass of rug.
    public static class RugWithPosition extends Rug {
        Pair<Integer, Integer> p1;
        Pair<Integer, Integer> p2;

        //use constructor here to initialize the basic elements
        public RugWithPosition(Color colour, int id, Pair<Integer, Integer> firstPosition, Pair<Integer, Integer> secondPosition) {
            super(colour, id);
            this.p1 = firstPosition;
            this.p2 = secondPosition;
        }

        //to return a RugWithPosition object after reading a string ( like "r120102")
        public static RugWithPosition stringToRugWithPosition(String rugString) {
            Color colour;
            char colourChar = rugString.charAt(0);
            //first check the color
            if (colourChar == 'c') {
                colour = Color.CYAN;
            } else if (colourChar == 'y') {
                colour = Color.YELLOW;
            } else if (colourChar == 'r') {
                colour = Color.RED;
            } else if (colourChar == 'p') {
                colour = Color.PURPLE;
            } else return null;
            //check the rest elements
            String idString = rugString.substring(1, 3);
            Pair<Integer, Integer> firstPosition = new Pair<Integer, Integer>(Integer.parseInt(rugString.substring(3, 4)), Integer.parseInt(rugString.substring(4, 5)));
            Pair<Integer, Integer> secondPosition = new Pair<Integer, Integer>(Integer.parseInt(rugString.substring(5, 6)), Integer.parseInt(rugString.substring(6, 7)));
            return new RugWithPosition(colour, Integer.parseInt(idString), firstPosition, secondPosition);
        }

        public Pair<Integer, Integer> getP1() {
            return this.p1;
        }

        public Pair<Integer, Integer> getP2() {
            return this.p2;
        }
    }

    public Color getColour() {
        return this.colour;
    }

    public int getId() {
        return this.id;
    }

    //since we store each player in a certain position within an array, we can mark each player based on their rug's color
    public int getPlayerIndex() {
        if (this.getColour() == Color.YELLOW) {
            return 0;
        } else if (this.getColour() == Color.PURPLE) {
            return 1;
        } else if (this.getColour() == Color.RED) {
            return 2;
        } else {
            return 3;
        }
    }


    //return a abbreviated rug class by reading a string (like "r01" )
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
        }
        String idString = inputString.substring(1, 3);
        return (new Rug(colour, Integer.parseInt(idString)));
    }

    //express the textual form of a RugWithPosition object
    public static String rugWithPositionToString(RugWithPosition rug) {
        String colourString;
        String idString;
        String position1;
        String position2;
        //create colour String
        if (rug.getColour() == Color.YELLOW) {
            colourString = "y";
        } else if (rug.getColour() == Color.PURPLE) {
            colourString = "p";
        } else if (rug.getColour() == Color.RED) {
            colourString = "r";
        } else {
            colourString = "c";
        }
        //create idString
        if (rug.getId() < 10) {
            idString = "0" + (rug.getId());
        } else {
            idString = String.valueOf(rug.getId());
        }
        //create position1 and position2
        position1 = String.valueOf((rug.p1.getKey())) + String.valueOf((rug.p1.getValue()));
        position2 = String.valueOf((rug.p2.getKey())) + String.valueOf((rug.p2.getValue()));
        return (colourString + idString + position1 + position2);
    }
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