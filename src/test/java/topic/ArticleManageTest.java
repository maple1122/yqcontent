package topic;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2022/3/4 9:42
 */
public class ArticleManageTest {

    @Test(priority = 1)//添加稿件
    public void testAddArticles() throws InterruptedException {
        ArticleManage.addArticles();
    }

    @Test(priority = 6)//删除稿件
    public void testDelArticles() throws InterruptedException {
        ArticleManage.delArticles();
    }

    @Test(priority = 2)//上/下轮播
    public void testOnswitch() throws InterruptedException {
        ArticleManage.onSwitch();
    }

    @Test(priority = 3)//上/下封面
    public void testOnCover() throws InterruptedException {
        ArticleManage.onCover();
    }

    @Test(priority = 4)//设置轮播图
    public void testSetSwitch() throws InterruptedException {
        ArticleManage.setSwitch();
    }

    @Test(priority = 5)//设置封面图
    public void testSetCover() throws InterruptedException {
        ArticleManage.setCover();
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