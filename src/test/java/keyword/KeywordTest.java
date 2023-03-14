package keyword;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2022/3/24 10:37
 */
public class KeywordTest {

    @Test(priority = 1)//新建关键词
    public void testAddKeyword() throws InterruptedException {
        Keyword.addKeyword();
    }

    @Test(priority = 2)//编辑关键词
    public void testEditKeyword() throws InterruptedException {
        Keyword.editKeyword();
    }

    @Test(priority = 3)//删除关键词
    public void testDeleteKeyword() throws InterruptedException {
        Keyword.deleteKeyword();
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