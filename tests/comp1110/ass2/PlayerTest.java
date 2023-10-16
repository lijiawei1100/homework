package comp1110.ass2;

import javafx.scene.paint.Color;
import javafx.util.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class PlayerTest {
    @Test
    public void checkStringToPlayer() throws Exception {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/is_PlayerFromString_valid.txt")));
        Stream<String> testLines = fr.lines();
        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");
            // For this test, we need to check all 4 fields of the Player class
            Assertions.assertEquals(Player.getColorName(Player.stringToPlayer(splitLine[0]).getColour()), splitLine[1], splitLine[5]); //check colour
            Assertions.assertEquals(Player.stringToPlayer(splitLine[0]).getMoney(), Integer.parseInt(splitLine[2]), splitLine[5]); //check dihrams
            Assertions.assertEquals(Player.stringToPlayer(splitLine[0]).getRugsNumber(), Integer.parseInt(splitLine[3]), splitLine[5]); //check rug number
            Assertions.assertEquals(Player.stringToPlayer(splitLine[0]).getIsPlaying(), Boolean.valueOf(splitLine[4]), splitLine[5]); //check is playing
        }
    }
    @Test
    public void checkPlayerToString() throws Exception {
        String testString = "Pr01013i";
        Player testPlayer = new Player(Color.RED,10,13,Boolean.TRUE);
        String testStringFromPlayer = (Player.playerToString(testPlayer));
        Assertions.assertEquals(testString,testStringFromPlayer,"current string: "+testString+" but should be: "+testStringFromPlayer);
        String testString1 = "Pr00503o";
        Player testPlayer1 = new Player(Color.RED,5,3,Boolean.FALSE);
        String testStringFromPlayer1 = (Player.playerToString(testPlayer1));
        Assertions.assertEquals(testString1,testStringFromPlayer1,"current string: "+testString1+" but should be: "+testStringFromPlayer1);
    }
}
