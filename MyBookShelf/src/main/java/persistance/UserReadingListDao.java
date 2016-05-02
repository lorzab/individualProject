package persistance;

import entity.UserReadingList;

import java.util.List;

/**
 * Created by Lora on 4/24/16.
 */
public interface UserReadingListDao {

    public List<UserReadingList> getAllUserReadingList();
    public void updateUserReadingList(UserReadingList userList);
    public void deleteUserReadingList(UserReadingList userList);
    public int addUserReadingList(UserReadingList userList);
}
