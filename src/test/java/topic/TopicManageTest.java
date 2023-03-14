package topic;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2022/3/4 14:11
 */
public class TopicManageTest {

    @Test(priority = 3)//专题签发
    public void testPush() throws InterruptedException {
        TopicManage.push();
    }

    @Test(priority = 1)//专题发布
    public void testPublish() throws InterruptedException {
        TopicManage.publish();
    }

    @Test(priority = 4)//专题下线
    public void testOffline() throws InterruptedException {
        TopicManage.offline();
    }

    @Test(priority = 2)//专题编辑
    public void testEdit() throws InterruptedException {
        TopicManage.edit();
    }

    @Test(priority = 5)//专题排序
    public void testSort() throws InterruptedException {
        TopicManage.sort();
    }

//    @Test(priority = 6)//专题删除
//    public void testDelete() throws InterruptedException {
//        TopicManage.delete();
//    }

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