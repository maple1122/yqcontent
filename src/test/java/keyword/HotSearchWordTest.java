package keyword;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2022/3/24 14:03
 */
public class HotSearchWordTest {

    @Test(priority = 1)//新建热搜词
    public void testAddHotSearchWord() throws InterruptedException {
        HotSearchWord.addHotSearchWord();
    }

    @Test(priority = 2)//编辑热搜词
    public void testEditHotSearchWord() throws InterruptedException {
        HotSearchWord.editHotSearchWord();
    }

    @Test(priority = 3)//删除热搜词
    public void testDeleteHotSearchWord() throws InterruptedException {
        HotSearchWord.deleteHotSearchWord();
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