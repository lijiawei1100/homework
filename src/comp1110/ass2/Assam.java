package comp1110.ass2;

import javafx.util.Pair;

import java.security.PrivateKey;

public class Assam {
    /**initialize the statuses of Assam,including angle,direction,position and the current Assam's state.
     * angle(0,90,180,270)
    * direction: we have four direction(north,east,south,west),but we can use angle(0,90,180.270)
     * position: we need to initialize a square Intpair[] for it
     * Assam's state: unchangeed or changed*/

    private int angle;
    private int direction;
    private String currentAssam;
    private Square Square;

    /**to return Assam's direction */
    int getAssamDirection(){
        // return direction;
        return 0;
    }
    /**to return Assam's square position*/
    Pair<Integer,Integer> getAssamPositon(){
        // return Square.position;
        return null;
    }

}
