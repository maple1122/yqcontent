package topic;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2022/3/11 14:13
 */
public class ChannelManageTest {

    @Test(priority = 1)//新建专题栏目，1：普通样式，手动添加稿件；2：普通样式，来源于频道；3：竖视频样式，手动添加稿件；4：竖视频样式，来源于频道
    public void testAddChannel() throws InterruptedException {
        ChannelManage.addChannel(1);
        ChannelManage.addChannel(2);
//        ChannelManage.addChannel(3);
//        ChannelManage.addChannel(4);
    }

    @Test(priority = 2)//手动添加稿件
    public void testAddArticle() throws InterruptedException {
        ChannelManage.addArticle();
    }

    @Test(priority = 3)//删除手动添加的稿件
    public void testDelArticle() throws InterruptedException {
        ChannelManage.delArticle();
    }

    @Test(priority = 4)//删除专题栏目
    public void testDelChannel() throws InterruptedException {
        ChannelManage.delChannel();
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