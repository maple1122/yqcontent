package content;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

/**
 * @author wufeng
 * @date 2022/2/23 11:39
 */
public class FinishedContentTest {

    @Test(priority = 1)//稿件下线
    public void testUnPublish() throws InterruptedException {
        FinishedContent.unpublish();
    }

    @Test(priority = 2)//关联
    public void testRelate() throws InterruptedException {
        FinishedContent.relate();
    }

    @Test(priority = 3)//关联并排序,如果type值非1-4，则默认为1（1：排序、2：置顶、3：冻结、4：上轮播）
    public void testRelateAndSort() throws InterruptedException {
//        FinishedContent.relateAndSort(1);
//        FinishedContent.relateAndSort(2);
        FinishedContent.relateAndSort(3);
//        FinishedContent.relateAndSort(4);
    }

    @Test(priority = 4)//取消关联
    public void testUnRelate() throws InterruptedException {
        FinishedContent.unRelate();
    }

    @Test(priority = 5)//置顶
    public void testTop() throws InterruptedException {
        FinishedContent.top();
    }

    @Test(priority =6)//置顶设置
    public void testSetTop() throws InterruptedException {
        FinishedContent.setTop();
    }

    @Test(priority = 7)//取消置顶
    public void testCancelTop() throws InterruptedException {
        FinishedContent.cancelTop();
    }

    @Test(priority = 8)//冻结
    public void testFreeze() throws InterruptedException {
        FinishedContent.freeze();
    }

    @Test(priority = 9)//取消冻结
    public void testCancelFreeze() throws InterruptedException {
        FinishedContent.cancelFreeze();
    }

//    @Test()//取消搜索测试稿件关联
//    public void testUnRelateAll() throws InterruptedException {
//        FinishedContent.unRelateAll();
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
