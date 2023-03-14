package topic;

import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author wufeng
 * @date 2022/3/3 17:26
 */
public class Search extends LoginPortal {

    //搜索autoTest
    public static void searchAutoTest(WebDriver driver) throws InterruptedException {
        search(driver,"autoTest");
    }

    //搜索
    public static void search(WebDriver driver,String keyword) throws InterruptedException {
        driver.findElement(By.id("keyword")).clear();
        driver.findElement(By.id("keyword")).sendKeys(keyword);
        driver.findElement(By.id("confirmSearch")).click();
        Thread.sleep(3000);
    }

}
