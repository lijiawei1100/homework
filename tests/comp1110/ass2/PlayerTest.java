package comp1110.ass2;

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
    public void checkCorrectPlayerFromString() throws Exception {
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
}
