package Merge;

import java.util.Comparator;

public class SortedByStudentId implements Comparator<Student> {
    public int compare(Student user1, Student user2){
        return user1.getU_id() - user2.getU_id();
    }
}
