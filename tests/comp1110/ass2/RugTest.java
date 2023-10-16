package comp1110.ass2;

import javafx.scene.paint.Color;
import javafx.util.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import javax.print.DocFlavor;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)

class RugTest {
    @Test
    public void checkRugWithPositionToString() throws Exception{
        String testString = "r010123";
        Rug.RugWithPosition testRug = new Rug.RugWithPosition(Color.RED,01,new Pair<>(0, 1), new Pair<>(2, 3));
        String testStringFromRug = Rug.rugWithPositionToString(testRug);
        Assertions.assertEquals(testString,testStringFromRug,"current string: "+testString+" but should be: "+testStringFromRug);
    }
    @Test
    public void checkStringToRugWithPosition() throws Exception{
        Rug.RugWithPosition testRug = Rug.RugWithPosition.stringToRugWithPosition("r010123");
        Rug.RugWithPosition testRugfromString = new Rug.RugWithPosition(Color.RED,01,new Pair<>(0, 1), new Pair<>(2, 3));
        Assertions.assertEquals(testRug.getColour(),testRugfromString.getColour(),"colours do not match");
        Assertions.assertEquals(testRug.getId(),testRugfromString.getId(),"ids do not match");
        Assertions.assertEquals(testRug.getP1(),testRugfromString.getP1(),"position 1s do not match");
        Assertions.assertEquals(testRug.getP2(),testRugfromString.getP2(),"position 2s do not match");
    }
    @Test
    public void checkStringToAbbreviatedRug() throws Exception{
        String testString = "r01";
        Rug testRug = new Rug(Color.RED, 1);
        Rug testRugfromString = Rug.stringToAbbreviatedRug(testString);
        Assertions.assertEquals(testRug.getColour(),testRugfromString.getColour(),"colours do not match");
        Assertions.assertEquals(testRug.getId(),testRugfromString.getId(),"ids do not match");
    }
}