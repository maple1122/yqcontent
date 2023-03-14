package keyword;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2022/3/24 13:57
 */
public class WhiteWordTest {

    @Test(priority = 1)//新建白名单
    public void testAddWhiteWord() throws InterruptedException {
        WhiteWord.addWhiteWord();
    }

    @Test(priority = 2)//编辑白名单
    public void testEditWhiteWord() throws InterruptedException {
        WhiteWord.editWhiteWord();
    }

    @Test(priority = 3)//删除白名单
    public void testDeleteWhiteWord() throws InterruptedException {
        WhiteWord.deleteWhiteWord();
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