package Merge;

import java.util.Comparator;

public class SortedByEmployeeId_Eva implements Comparator<Evaluation> {
    public int compare(Evaluation evaluation1, Evaluation evaluation2){

        return evaluation1.getEmployee_id()-evaluation2.getEmployee_id();
    }
}
