package content;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

/**
 * @author wufeng
 * @date 2022/2/7 15:06
 */
public class CreateContentTest {

    @Test(priority = 1)//创建文稿
    public void testCreateArticle() throws InterruptedException {
        CreateContent.createArticle();
//        CreateContent.createArticle();
    }

    @Test(priority = 2)//创建图集稿件——列表图添加失败，暂不执行
    public void testCreateImage() throws InterruptedException {
        CreateContent.createImage();
    }

    @Test(priority = 3)//创建视频稿件
    public void testCreateVideo() throws InterruptedException {
        CreateContent.createVideo();
    }

    @Test(priority = 4)//创建音频稿件——列表图添加失败，暂不执行
    public void testCreateAudio() throws InterruptedException {
        CreateContent.createAudio();
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