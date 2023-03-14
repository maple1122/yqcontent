package content;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2023/3/10 15:17
 */
public class PopNewsTest {

    @Test
    public void testAddPopNews() throws InterruptedException {
        PopNews.addPopNews();
    }

    @Test
    public void testEditPopNews() throws InterruptedException {
        PopNews.editPopNews();
    }

    @Test
    public void testDeletePopNews() throws InterruptedException {
        PopNews.deletePopNews();
    }

    @Test
    public void testTurnOnOrOff() throws InterruptedException {
        PopNews.turnOnOrOff();
    }

    @BeforeMethod
    public void testStart(Method method) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: "
                + method.getName());
    }

    @AfterMethod
    public void testEnd(Method method) {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<< Test End!\n");
    }
}