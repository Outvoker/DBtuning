package Merge;
import java.util.Comparator;
public class SortedByUserId implements Comparator<Competition>{
    public int compare(Competition comp1, Competition comp2){

        return comp1.getU_id() - comp2.getU_id();
    }
}
