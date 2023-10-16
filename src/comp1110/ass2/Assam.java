package comp1110.ass2;

import javafx.util.Pair;


public class Assam {
    /**
     * initialize the statuses of Assam,including angle,direction,position and the current Assam's state.
     * angle(0,90,180,270)
     * direction: we have four direction(north,east,south,west),but we can use angle(0,90,180.270)
     * position: we need to initialize a square Intpair[] for it
     * Assam's state: unchanged or changed
     *
     * AUTHORSHIP:
     * the class was written by both group members:
     * stringToAssam was written by Benjamin Campbell
     * assamToString was written by Jiawei Li
     * we both checked and debugged each other's code
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

    /**
     * testing code here
      * @param args
     * @throws Exception
     */

    //code to test stringToAssam
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
//        System.out.println(assamToString(assam2));
//    }
}
