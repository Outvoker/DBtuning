package Merge;

import java.util.Comparator;

public class SortedByUserId_user  implements Comparator<User> {
    public int compare(User user1, User user2){
        return user1.getUser_id() - user2.getUser_id();
    }
}
