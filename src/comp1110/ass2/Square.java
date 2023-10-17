package comp1110.ass2;
import javafx.util.Pair;
/**
 * AUTHORSHIP:
 *
 * code was written by both group members
 *
 * @author <u7531534><Jiawei Li>/ <u7471333><Benjamin Campbell>
 */

public class Square {

    public Pair<Integer,Integer> position;
    public Rug occupiedRug;

    public Square(Pair<Integer,Integer> position,Rug occupiedRug){
        this.position = position;
        this.occupiedRug = occupiedRug;
    }
    public void setOccupiedRug(Rug.RugWithPosition rug) {
        this.occupiedRug = rug;
    };
}
