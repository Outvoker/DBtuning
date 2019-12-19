package Merge;
import java.util.Comparator;
public class SortedByOrderId_order implements Comparator<Competition> {
    public int compare(Competition comp1, Competition comp2){
        return comp1.getComp_id() - comp2.getComp_id();
    }
}

