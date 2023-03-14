package topic;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2022/3/3 17:06
 */
public class AddTopicTest {

    @Test
    public void testAddTopic() throws InterruptedException {
//        AddTopic.addTopic(1);//无子栏目，手动添加稿件
//        AddTopic.addTopic(2);//无子栏目,稿件来源于频道
//        AddTopic.addTopic(3);//有子栏目
        AddTopic.addTopic(1,2);//音频类型
        AddTopic.addTopic(1,3);//要闻类型
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