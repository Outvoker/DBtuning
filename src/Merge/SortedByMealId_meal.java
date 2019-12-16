package Merge;

import java.util.Comparator;

public class SortedByMealId_meal implements Comparator<Meal> {
    public int compare(Meal meal1, Meal meal2){
        return meal1.getMeal_id() - meal2.getMeal_id();
    }
}
