package Merge;
import java.util.Comparator;

public class SortedByUserIdinScore implements Comparator<Score>{
    public int compare(Score score1, Score score2){

        return score1.getU_id() - score2.getU_id();
    }
}
