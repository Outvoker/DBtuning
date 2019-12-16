package Merge;
import java.util.Comparator;
public class SortedByOrderId_order implements Comparator<Orders> {
    public int compare(Orders orders1, Orders orders2){
        return orders1.getOrder_id() - orders2.getOrder_id();
    }
}

