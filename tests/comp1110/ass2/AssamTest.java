package comp1110.ass2;

import javafx.scene.paint.Color;
import javafx.util.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
class AssamTest {


    @Test
    public  void checkAssamOrientation() throws Exception{
        String[] inputString = {"A25W","A13N","A24E"};
        for(String string: inputString) {
            Assam assam = Assam.stringToAssam(string);
            String angle;
            String subString = string.substring(3,4);
            if (assam.getAngle() == 0) {
                angle = "N";
            } else if (assam.getAngle() == 90) {
                angle = "E";
            } else if(assam.getAngle() == 180) {
                angle = "S";
            } else if(assam.getAngle() == 270) {
                angle = "W";
                assertTrue(angle.equals(subString),"orientation is "+subString+" now is " +angle);
            }
        }
    }
    @Test
    public  void checkAssamPosition() throws Exception{
        String[] inputString = {"A25W","A13N","A24E"};
        for(String string: inputString) {
            Assam assam = Assam.stringToAssam(string);
            String xPositionString = string.substring(1,2);
            String yPositionString = string.substring(2,3);
            int xPosition = assam.getAssamX();
            int yPosition = assam.getAssamY();
            assertTrue(Integer.toString(xPosition).equals(xPositionString),"xPosition is "+xPositionString+"now is "+xPosition);
            assertTrue(Integer.toString(yPosition).equals(yPositionString),"yPosition is "+yPositionString+"now is "+yPosition);
            }
        }
    @Test
    public void checkAssamToString() throws Exception {
        String testString = "A01E";
        Assam testAssam = new Assam(90,new Pair<>(0, 1));
        String testStringFromAssam = (Assam.assamToString(testAssam));
        Assertions.assertEquals(testString,testStringFromAssam,"current string: "+testAssam+" but should be: "+testStringFromAssam);
        String testString1 = "A54W";
        Assam testAssam1 = new Assam(270,new Pair<>(5, 4));
        String testStringFromAssam1 = (Assam.assamToString(testAssam1));
        Assertions.assertEquals(testString1,testStringFromAssam1,"current string: "+testAssam1+" but should be: "+testStringFromAssam1);
    }
}