package Merge;

import java.util.Comparator;

public class SortedByOrderId_eva implements Comparator<Evaluation> {
    public int compare(Evaluation evaluation1, Evaluation evaluation2){

        return evaluation1.getOrder_id()-evaluation2.getOrder_id();
    }
}
