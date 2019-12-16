package Merge;
import java.util.Comparator;
public class SortedByUserId implements Comparator<Orders>{
    public int compare(Orders order1, Orders order2){

        return order1.getUser_id() - order2.getUser_id();
    }
}
