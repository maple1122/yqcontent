package comment;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

/**
 * @author wufeng
 * @date 2022/3/23 14:06
 */
public class CommentCheckTest {

    @Test(priority = 1)//评论审核
    public void testCheck() throws InterruptedException {
        CommentCheck.check();
    }

    @Test(priority = 2)//评论屏蔽
    public void testShield() throws InterruptedException {
        CommentCheck.shield();
        CommentCheck.shield();
    }

    @Test(priority = 3)//禁言、取消禁言
    public void testMuted() throws InterruptedException {
        CommentCheck.muted();
    }

    @Test(priority = 5)//删除评论
    public void testDelete() throws InterruptedException {
        CommentCheck.delete();
    }

    @Test(priority = 4)//回复评论
    public void testReply() throws InterruptedException {
        CommentCheck.reply();
    }
//
//    @Test(priority = 5)//删除所有评论数据
//    public void testDeleteAuto() throws InterruptedException {
//        CommentCheck.deleteAuto();
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