package comp1110.ass2;
import javafx.util.Pair;

public class Square {
    private Boolean isEdge;
    public Pair<Integer,Integer> position;

    public Square(Boolean isEdge,Pair<Integer,Integer> position){
        this.isEdge = isEdge;
        this.position = position;
    }


}
