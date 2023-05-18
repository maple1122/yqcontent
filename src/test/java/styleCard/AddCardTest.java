package styleCard;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2022/3/2 16:07
 */
public class AddCardTest {

    @Test(priority = 1)//添加快讯内容样式卡。type：1：普通样式；2，卡片样式
    public void testAddFlash() throws InterruptedException {
        AddCard.addFlash();//为空或非2时，创建普通样式
//        AddCard.addFlash(2);//类型参数为2时，创建卡片样式
    }

    @Test(priority = 2)//添加横视频内容样式卡
    public void testAddVideoH() throws InterruptedException {
        AddCard.addVideoH();
    }

    @Test(priority = 3)//添加竖视频内容样式卡
    public void testAddVideoS() throws InterruptedException {
        AddCard.addVideoS();
    }

    @Test(priority = 4)//添加媒体号内容样式卡
    public void testAddMedia() throws InterruptedException {
        AddCard.addMedia();
    }

    @Test(priority = 5)//添加专题内容样式卡。type：1，文本；2，图文（左右滑动）；3，图文（上下滚动）
    public void testAddSubject() throws InterruptedException {
        AddCard.addSubject(1);
        AddCard.addSubject(2);
//        AddCard.addSubject(3);
    }

    @Test(priority = 6)//添加服务内容样式卡。type：1，普通样式；2：卡片样式。set：1，单行；2，双行
    public void testAddService() throws InterruptedException {
        AddCard.addService(1,2);
//        AddCard.addService(2,1);
    }

//    @Test(priority = 7)//添加频道内容样式卡
//    public void testAddChannel() throws InterruptedException {
//        AddCard.addChannel(1);
//        AddCard.addChannel(2);
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