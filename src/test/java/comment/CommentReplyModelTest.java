package comment;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * 回复模板管理，仅延庆项目可用！！！！
 * @date 2023/3/9 16:52
 */
public class CommentReplyModelTest {

    @Test
    public void testAddModel() throws InterruptedException {
        CommentReplyModel.addModel();
    }

    @Test
    public void testEditModel() throws InterruptedException {
        CommentReplyModel.editModel();
    }

    @Test
    public void testDeleteModel() throws InterruptedException {
        CommentReplyModel.deleteModel();
    }

    @Test
    public void testTurnOnOrOff() throws InterruptedException {
        CommentReplyModel.turnOnOrOff();
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