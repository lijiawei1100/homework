package comp1110.ass2;

import javafx.util.Pair;


public class Assam {
    /**
     * AUTHORSHIP:
     *
     * initialize the statuses of Assam,including angle,direction,position and the current Assam's state.
     * angle(0,90,180,270)
     * direction: we have four direction(north,east,south,west),but we can use angle(0,90,180.270)
     * position: we need to initialize a square Intpair[] for it
     * Assam's state: unchanged or changed
     *
     * the class was written by both group members:
     * stringToAssam was written by Benjamin Campbell
     * assamToString was written by Jiawei Li
     * we both checked and debugged each other's code
     *
     * @author <u7531534><Jiawei Li>/ <u7471333><Benjamin Campbell>
     */

    private int angle;
    private Pair<Integer,Integer> position;

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

    //input a string and output an assam
    public static Assam stringToAssam(String inputString) {
        int inputAngle = 0;
        char angleChar = inputString.charAt(3);
        if (angleChar == 'N') {
            inputAngle = 0;
        } else if (angleChar == 'E') {
            inputAngle = 90;
        } else if(angleChar == 'S') {
            inputAngle = 180;
        } else if(angleChar == 'W') {
            inputAngle = 270;
        }

        Pair<Integer,Integer> location;
        char charX = inputString.charAt(1);
        char charY = inputString.charAt(2);
        Integer intX = Integer.parseInt(String.valueOf(charX));
        Integer intY = Integer.parseInt(String.valueOf(charY));
        location = new Pair<>(intX,intY);
        return (new Assam(inputAngle,location));
    }

    //input an assam and output a string
    public static String assamToString(Assam assam){
        String direction = "";
        String assamString;
        switch (assam.getAngle()){
            case 0 :direction="N";break;
            case 90 : direction = "E";break;
            case 180 : direction ="S";break;
            case 270 : direction ="W";break;
        }
        assamString = "A" + assam.getAssamX().toString() + assam.getAssamY().toString() + direction;
        return assamString;
    }
}
