package comp1110.ass2;

import javafx.util.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)

class SquareTest {
    @Test
    public  void checkSquareState() throws Exception{
        Rug occupiedRug = Rug.stringToAbbreviatedRug("r01");
        Square square = new Square(true, new Pair<>(0,5),occupiedRug);
        assertTrue(square.getIsEdge(),"is not on a edge");
        assertTrue(square.position.getKey() == 0,"xPosition is wrong" );
        assertTrue(square.position.getValue() == 5,"yPosition is wrong" );


    }
}