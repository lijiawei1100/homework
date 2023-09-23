package comp1110.ass2;
import javafx.util.Pair;

public class Square {
    private Boolean isEdge;
    public Pair<Integer,Integer> position;
    public Rug occupiedRug;

    public Square(Boolean isEdge,Pair<Integer,Integer> position,Rug occupiedRug){
        this.isEdge = isEdge;
        this.position = position;
        this.occupiedRug = occupiedRug;
    }

    public Boolean getIsEdge(){
        return isEdge;
    }
}
