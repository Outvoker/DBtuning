package Merge;

import java.util.Comparator;

public class SortedByEmployeeId_Deli implements Comparator<Delivery> {

        public int compare(Delivery delivery1, Delivery delivery2){
            return delivery1.getEmployee_id() - delivery2.getEmployee_id();
        }
}
