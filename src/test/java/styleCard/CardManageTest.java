package styleCard;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

/**
 * @author wufeng
 * @date 2022/3/3 15:18
 */
public class CardManageTest {

    @Test(priority = 1)//签发
    public void testIssue() throws InterruptedException {
        CardManage.issue();
    }

    @Test(priority = 2)//编辑
    public void testEdit() throws InterruptedException {
        CardManage.edit();
    }

    @Test(priority = 3)//删除
    public void testDelete() throws InterruptedException {
        CardManage.delete();
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