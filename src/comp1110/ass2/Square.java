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

    public Boolean isSquareValid(Square square){
        if(square.position.getKey() ==0 | square.position.getKey()==6 |square.position.getValue()==0|square.position.getValue()==6){
            if(square.isEdge.equals(true)) return true;
        }
        return false;

    }

    public Boolean getIsEdge(){
        return isEdge;
    }
}
