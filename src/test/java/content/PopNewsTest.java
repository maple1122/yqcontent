package content;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * 首屏浮窗测试
 * @date 2023/3/10 15:17
 */
public class PopNewsTest {

    @Test(priority = 1)//添加首屏浮窗
    public void testAddPopNews() throws InterruptedException {
        PopNews.addPopNews();
    }

    @Test(priority = 2)//编辑浮窗广告
    public void testEditPopNews() throws InterruptedException {
        PopNews.editPopNews();
    }

    @Test(priority = 4)//删除浮窗广告
    public void testDeletePopNews() throws InterruptedException {
        PopNews.deletePopNews();
    }

    @Test(priority = 3)//关闭浮窗广告
    public void testTurnOnOrOff() throws InterruptedException {
        PopNews.turnOnOrOff();
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