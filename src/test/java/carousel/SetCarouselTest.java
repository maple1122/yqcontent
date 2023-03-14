package carousel;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2022/3/22 16:18
 */
public class SetCarouselTest {

    @Test//设置栏目轮播、取消栏目轮播
    public void testSetCarousel() throws InterruptedException {
//        SetCarousel.setCarousel(true);
//        SetCarousel.setCarousel(false);
        SetCarousel.setCarousel2(true);
        SetCarousel.setCarousel2(false);
    }

    @BeforeMethod
    public void testStart(Method method) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: "
                + method.getName());
    }

    @AfterMethod
    public void testEnd(Method method){
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<< Test End!\n");
    }
}