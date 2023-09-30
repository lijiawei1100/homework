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
    private Pair<Integer,Integer> position;
    private Square Square;

    public Assam(int angle, Pair<Integer, Integer> position){
        this.angle = angle;
        this.position = position;
    }

    public Integer getAssamX() {
        return this.position.getKey();
    }
    public Integer getAssamY() {
        return this.position.getValue();
    }
    public Pair<Integer,Integer> getAssamPosition(){return this.position;}


    /**to return Assam's direction*/
    public int getAngle(){
        return this.angle;
    }

    public static Assam stringToAssam(String inputString) {
        int angle = 0;
        char angleChar = inputString.charAt(3);
        if (angleChar == 'N') {
            angle = 0;
        } else if (angleChar == 'E') {
            angle = 90;
        } else if(angleChar == 'S') {
            angle = 180;
        } else if(angleChar == 'W') {
            angle = 270;
        }
//            else {
//            throw new Exception("direction is not a valid character");
//        }
        Pair<Integer,Integer> location;
        char charX = inputString.charAt(1);
        char charY = inputString.charAt(2);
        Integer intX = Integer.parseInt(String.valueOf(charX));
        Integer intY = Integer.parseInt(String.valueOf(charY));
        location = new Pair<>(intX,intY);
        return (new Assam(angle,location));
    }

//    /**
//     * testing code here
//      * @param args
//     * @throws Exception
//     */
//    public static void main(String[] args) throws Exception {
//        String input1 = "A04E";
//        String input2 = "A53S";
//        Assam assam1 = stringToAssam(input1);
//        Assam assam2 = stringToAssam(input2);
//        System.out.println("String 1: " + input1);
//        System.out.println("Assam1 direction: " + assam1.angle);
//        System.out.println("Assam1 position: " + assam1.position.getKey()+assam1.position.getValue());
//        System.out.println("String 2: " + input2);
//        System.out.println("Assam2 direction: " + assam2.angle);
//        System.out.println("Assam2 position: " + assam2.position);
//    }
}
