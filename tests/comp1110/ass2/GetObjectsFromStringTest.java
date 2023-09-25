package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class GetObjectsFromStringTest {
    @Test
    public void checkCorrectPlayerFromString() throws Exception {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/is_PlayerFromString_valid.txt")));
        Stream<String> testLines = fr.lines();
        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");
            // For this test, there's two arguments needed to the function
            Assertions.assertEquals(Player.getColorName(Player.stringToPlayer(splitLine[0]).getColour()), splitLine[1], splitLine[5]);
            Assertions.assertEquals(Player.stringToPlayer(splitLine[0]).getMoney(), splitLine[2].toString(), splitLine[5]);
            Assertions.assertEquals(Player.stringToPlayer(splitLine[0]).getRugsNumber(), splitLine[3], splitLine[5]);
            Assertions.assertEquals(Player.stringToPlayer(splitLine[0]).getIsPlaying(), splitLine[4], splitLine[5]);
            // to get from player: Player(colour,money,rugsNumber,isPlaying)
            //test text equals: colour use getColorName(), integer dirhams, integer rugsnumber, isPlaying use Bool
            //create new testdoc with strings
        }
    }
    @Test
    public void checkCorrectAssamFromString() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/is_rug_valid_offboard.txt")));
        Stream<String> testLines = fr.lines();
        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");
            // For this test, there's two arguments needed to the function
//            Assertions.assertEquals(splitLine[2], String.valueOf(Marrakech.isRugValid(splitLine[0], splitLine[1])), splitLine[3]);
        }
    }

    @Test
    public void checkCorrectBoardFromString() {
        BufferedReader fr;
        fr = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/is_rug_valid_valid.txt")));
        Stream<String> testLines = fr.lines();
        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");
            // For this test, there's two arguments needed to the function
//            Assertions.assertEquals(splitLine[2], String.valueOf(Marrakech.isRugValid(splitLine[0], splitLine[1])), splitLine[3]);
        }
    }
}
