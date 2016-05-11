package persistance;

import entity.ReviewList;
import entity.UserReadingList;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Lora on 5/8/16.
 */
public class UserReadingListWithHibernateTest {

    @Before
    public void setup() {

        UserReadingListDaoWithHibernate dao = new UserReadingListDaoWithHibernate();
        int insertReadingListId = 0;

        //create book for test
        UserReadingList getAllReadingList = new UserReadingList();
        getAllReadingList.setReading_id(0);
        getAllReadingList.setBook_id(82);
        getAllReadingList.setUser_id(3);
        getAllReadingList.setWish_list(0);
        getAllReadingList.setDate_added(dao.getCurrentDate());
        insertReadingListId = dao.addUserReadingList(getAllReadingList);
    }

    @Test
    public void testGetAllReadingList() throws Exception {

        UserReadingListDao dao = new UserReadingListDaoWithHibernate();
        List<UserReadingList> readingList = dao.getAllUserReadingList();

        assertTrue("There is the wrong amount in the list", readingList.size() > 0);
    }

    @Test
    public void testUpdateReadingList() throws Exception {

        UserReadingListDaoWithHibernate dao = new UserReadingListDaoWithHibernate();
        UserReadingList readingList = new UserReadingList(2, 2, 81, 1, dao.getCurrentDate());

        dao.updateUserReadingList(readingList);
        assertEquals("This is the wrong reading list", 1, readingList.getWish_list());
    }

    /*@Test
    public void testDeleteReadingList() throws Exception {

        UserReadingListDao dao = new UserReadingListDaoWithHibernate();
        UserReadingList readingList = new UserReadingList();
        int sizeBefore;
        int sizeAfter;
        readingList.setReading_id(2);
        sizeBefore = dao.getAllUserReadingList().size();
        dao.deleteUserReadingList(readingList);
        sizeAfter = dao.getAllUserReadingList().size();

        assertTrue("The reading list was not deleted", sizeBefore > sizeAfter);
    }*/

    @Test
    public void testAddReadingList() throws Exception {

        UserReadingListDaoWithHibernate dao = new UserReadingListDaoWithHibernate();
        UserReadingList readingList = new UserReadingList();

        int insertReadingListId = 0;

        //create readinglist to add
        readingList.setUser_id(3);
        readingList.setBook_id(83);
        readingList.setWish_list(1);
        readingList.setDate_added(dao.getCurrentDate());

        insertReadingListId = dao.addUserReadingList(readingList);

        assertTrue("Insert failed", insertReadingListId > 0);
    }
}
