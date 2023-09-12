package comp1110.ass2;
import javafx.util.Pair;

public class Square {
    private Boolean isEdge;
    public Pair<Integer,Integer> position;
    /**
     * occupiedRugs represents the abbreviation of the rugs information ilke p12 (color:purple, rug_id:12),n00 means there is no an occupied rug
     */
    private String occupiedRug;

    public Square(Boolean isEdge,Pair<Integer,Integer> position,String occupiedRug){
        this.isEdge = isEdge;
        this.position = position;
        this.occupiedRug = occupiedRug;
    }

    public String getOccupiedRug() {
        return occupiedRug;
    }
}
