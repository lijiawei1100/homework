package comp1110.ass2;

import javafx.util.Pair;

import java.security.PrivateKey;

public class Assam {
    /**
     * initialize the statuses of Assam,including angle,direction,position and the current Assam's state.
     * angle(0,90,180,270)
     * direction: we have four direction(north,east,south,west),but we can use angle(0,90,180.270)
     * position: we need to initialize a square Intpair[] for it
     * Assam's state: unchangeed or changed
     */

    private int angle;
    private int direction;
    private static Pair<Integer,Integer> position;
    private String currentAssam;
    private Square Square;

    Assam(int angle,Square square){
        this.angle = angle;
        position = square.position;
    }

    /**
     * initialize current state of assam
     */
    public static Assam ASSAM = new Assam(0, new Square(false,new Pair<>(3,4),"n00"));


    /**to return Assam's direction*/
    public char getAssamDirection(){
        // return direction;
        if (angle == 0) return 'N';
        if (angle == 90) return 'E';
        if (angle == 180) return 'S';
        if (angle == 270) return 'W';
        return 'O';
    }

    public static String assamToString(Assam ASSAM) {
        return "A"+ ASSAM.position.getKey() + ASSAM.position.getValue().toString() + ASSAM.getAssamDirection();
    }

    public static void main(String[] args) {
        System.out.println(assamToString(ASSAM));
    }
}
