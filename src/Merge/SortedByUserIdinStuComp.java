package Merge;
import java.util.Comparator;

public class SortedByUserIdinStuComp implements Comparator<Student_Competition>{
    public int compare(Student_Competition stuComp1, Student_Competition stuComp2){

        return stuComp1.getU_id() - stuComp2.getU_id();
    }
}
