package comp1110.ass2;

import javafx.util.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)

class SquareTest {
    @Test
    public void checkSquareValid() throws Exception{
        Rug occupiedRug = Rug.stringToAbbreviatedRug("r01");
        Square square = new Square(true, new Pair<>(0,5),occupiedRug);
        assertTrue(square.isSquareValid(square),"is not on a edge");
    }
}