package comp1110.ass2;
import javafx.util.Pair;

public class Square {
    private final Boolean isEdge;
    private Pair<Integer,Integer> position;
    public Rug occupiedRug;

    public Square(Boolean isEdge,Pair<Integer,Integer> position,Rug occupiedRug){
        this.isEdge = isEdge;
        this.position = position;
        this.occupiedRug = occupiedRug;
    }
}
