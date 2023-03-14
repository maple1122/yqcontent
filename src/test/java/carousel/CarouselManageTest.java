package carousel;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2022/3/23 10:51
 */
public class CarouselManageTest {

    @Test(priority = 1)//上轮播
    public void testOpenCarouse() throws InterruptedException {
        SetCarousel.setCarousel(true);
        CarouselManage.OpenCarouse();
    }

    @Test(priority = 2)//下轮播
    public void testCloseCarouse() throws InterruptedException {
        CarouselManage.CloseCarouse();
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