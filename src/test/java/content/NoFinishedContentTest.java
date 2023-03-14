package content;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

/**
 * @author wufeng
 * @date 2022/2/23 11:27
 */
public class NoFinishedContentTest {


    @Test(priority = 1)//编辑测试稿件
    public void testEditContent() throws InterruptedException {
        NoFinishedContent.editArticle();
    }

    @Test(priority = 7)//删除测试稿件
    public void testDelete() throws InterruptedException {
        NoFinishedContent.delete();
    }

    @Test(priority = 2)//提交测试稿件
    public void testSubmit() throws InterruptedException {
        NoFinishedContent.submit();
    }

    @Test(priority = 3)//审核测试稿件
    public void testPublish() throws InterruptedException {
        NoFinishedContent.publish();
    }

    @Test(priority = 5)//审核-转审测试稿件
    public void testPublish2() throws InterruptedException {
        NoFinishedContent.cancelTimePublish();
        NoFinishedContent.submit();
        NoFinishedContent.publish2();
    }

    @Test(priority = 4)//审核-退回测试稿件
    public void testPublish3() throws InterruptedException {
        NoFinishedContent.submit();
        NoFinishedContent.publish3();
    }

    @Test(priority = 6)//审核并排序,如果type值非1-4，则默认为1（1：排序、2：置顶、3：冻结、4：上轮播）
    public void testPublishAndSort() throws InterruptedException {
        NoFinishedContent.publishAndSort(1);
        NoFinishedContent.publishAndSort(2);
        NoFinishedContent.publishAndSort(3);
        NoFinishedContent.publishAndSort(4);
    }

    @Test(priority = 7)//定时发布
    public void testTimePublish() throws InterruptedException {
        NoFinishedContent.timePublish();
    }

    @Test(priority = 8)//取消定时发布
    public void testCancelTimePublish() throws InterruptedException {
        NoFinishedContent.cancelTimePublish();
    }

    @Test(priority = 9)//复制稿件
    public void testCopy() throws InterruptedException {
        NoFinishedContent.copy();
    }

    @Test(priority = 10)//转移稿件
    public void testMove() throws InterruptedException {
        NoFinishedContent.move();
    }

    @Test(priority = 11)//取消关联
    public void testUnrelate() throws InterruptedException {
        NoFinishedContent.unrelate();
    }

//    @Test(priority = 12)//清除XX频道下的全部测试数据
//    public void delAllTestDatas() throws InterruptedException {
//        NoFinishedContent.deleteAllTestDatas("数据验证");
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