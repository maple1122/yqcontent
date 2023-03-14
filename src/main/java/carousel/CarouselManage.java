package carousel;

import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * @author wufeng
 * @date 2022/3/22 15:59
 */
public class CarouselManage extends LoginPortal {

    static WebDriver driver;

    //上轮播
    public static void OpenCarouse() throws InterruptedException {
        CommonMethod.getTestTree(driver);//切换到测试频道
        if (!CommonMethod.isJudgingElement(driver, By.xpath("//table[@id='infoTabel']/tbody/tr"))) {//校验是否数据为空
            SetCarousel.setCarousel(true);//在新闻管理中设置栏目轮播，添加轮播数据
            driver.get("http://app.test.pdmiryun.com/content/carousel/jump");//返回到轮播页面
            Thread.sleep(2000);
            CommonMethod.getTestTree(driver);//切换到测试频道
        }
        List<WebElement> listTr = driver.findElements(By.xpath("//table[@id='infoTabel']/tbody/tr"));//获取数据列表
        for (int i = 0; i < listTr.size(); i++) {//循环所有数据
            if (!CommonMethod.isJudgingElement(listTr.get(i), By.xpath("td[1]/input[@checked='true']")))//校验是否已开启轮播
                listTr.get(i).findElement(By.xpath("td/input")).click();//未开启的进行开启
            Thread.sleep(500);
        }
        driver.findElement(By.id("sureBtn")).click();//点击确定
        System.out.println("~~~ OpenCarouse()，上轮播，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //下轮播
    public static void CloseCarouse() throws InterruptedException {
        CommonMethod.getTestTree(driver);//切换到测试频道
        if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@id='infoTabel']/tbody/tr"))) {//校验是否有数据
            List<WebElement> listTr = driver.findElements(By.xpath("//table[@id='infoTabel']/tbody/tr"));//数据list
            for (int i = 0; i < listTr.size(); i++) {//循环全部数据
                if (CommonMethod.isJudgingElement(listTr.get(i), By.xpath("td[1]/input[@checked='true']")))//校验是否未开启
                    listTr.get(i).findElement(By.xpath("td/input")).click();//开启的打勾
                Thread.sleep(500);
            }
            driver.findElement(By.id("sureBtn")).click();//确定
            System.out.println("~~~ CloseCarouse()，下轮播，执行成功 ~~~");
        } else System.out.println("没有开启栏目轮播的数据");
        Thread.sleep(3000);
    }

    //初始化登录
    static {
        try {
            driver = login();
            for (int i = 0; i < 3; i++) {
                if (!CommonMethod.isJudgingElement(driver, By.tagName("header"))) {
                    if (CommonMethod.isJudgingElement(driver, By.className("loginBtn"))) driver = login();
                    driver.get(domain + "/content/carousel/jump");
                    Thread.sleep(3000);
                    if (!CommonMethod.isJudgingElement(driver, By.className("fold-pack"))) {
                        CommonMethod.jumpModule(driver, "新闻管理");
                        Thread.sleep(2000);
                        driver.get(domain + "/content/carousel/jump");
                    }
                    Thread.sleep(2000);
                } else break;
            }

            if (!driver.findElement(By.xpath("//div[@class='nav-right']/ul/li/a")).getText().contains(siteName)) {
                Actions action = new Actions(driver);
                action.moveToElement(driver.findElement(By.className("nav-right"))).perform();
                Thread.sleep(500);
                driver.findElement(By.linkText(siteName)).click();
                Thread.sleep(3000);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
