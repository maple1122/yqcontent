package comment;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2022/3/23 14:57
 */
public class CommentManageTest {

    @Test(priority = 1)//发布
    public void testPublish() throws InterruptedException {
        CommentManage.publish();
    }

    @Test(priority = 2)//关闭
    public void testClose() throws InterruptedException {
        CommentManage.close();
    }

    @Test(priority = 3)//屏蔽
    public void testShiled() throws InterruptedException {
        CommentManage.shiled();
    }

    @BeforeMethod
    public void testStart(Method method) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: "
                + method.getName());
    }

    @AfterMethod
    public void testEnd(Method method){
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<< Test End!\n");
    }
}