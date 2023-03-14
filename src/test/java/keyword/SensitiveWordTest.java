package keyword;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2022/3/24 11:46
 */
public class SensitiveWordTest {

    @Test(priority = 1)//添加敏感词
    public void testAddSensitiveWord() throws InterruptedException {
        SensitiveWord.addSensitiveWord();
    }

    @Test(priority = 2)//编辑敏感词
    public void testEditSensitiveWord() throws InterruptedException {
        SensitiveWord.editSensitiveWord();
    }

    @Test(priority = 3)//删除敏感词
    public void testDeleteSensitiveWord() throws InterruptedException {
        SensitiveWord.deleteSensitiveWord();
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