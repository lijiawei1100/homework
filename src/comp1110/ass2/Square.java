package comp1110.ass2;
import javafx.util.Pair;

public class Square {
    private Boolean isEdge;
    public Pair<Integer,Integer> position;
    /**
     * occupiedRugs represents the abbreviation of the rugs information ilke p12 (color:purple, rug_id:12),n00 means there is no an occupied rug
     */
    private Rug occupiedRug; // draft- have changed to rug for Object-Oriented programming.
    //will need to change

    public Square(Boolean isEdge,Pair<Integer,Integer> position,Rug occupiedRug){
        this.isEdge = isEdge;
        this.position = position;
        this.occupiedRug = occupiedRug;
    }

    public Rug getOccupiedRug() {
        return occupiedRug;
    }
}
