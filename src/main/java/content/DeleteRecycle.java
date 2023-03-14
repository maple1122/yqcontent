package content;

import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * @author wufeng
 * @date 2022/3/22 14:57
 */
public class DeleteRecycle extends LoginPortal {

    static WebDriver driver;

    //清空回收站中的auto数据
    public static void deleteRecycle() throws InterruptedException {
        driver.findElement(By.id("keyword")).sendKeys("autoTest");//搜索autoTest数据
        driver.findElement(By.id("confirmSearch")).click();//点击搜索
        for (int r = 0; r < 3; r++) {
            Thread.sleep(2000);
            if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@id='infoTabel']/tbody/tr"))) break;
        }
        if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@id='infoTabel']/tbody/tr"))) {//校验是否有返回数据
            driver.findElement(By.id("checkAll")).click();//点击全选
            Thread.sleep(200);
            driver.findElement(By.id("del")).click();//点击删除
            driver.findElement(By.className("layui-layer-btn0")).click();//确定删除
            Thread.sleep(5000);
            System.out.println("~~~ deleteRecycle()，清除回收站autoTest数据，执行成功 ~~~");
        } else System.out.println("没有autoTest数据");
    }

    //初始化登录
    static {
        try {
            driver = login();
            for (int i = 0; i < 3; i++) {
                if (!CommonMethod.isJudgingElement(driver, By.tagName("header"))) {
                    if (CommonMethod.isJudgingElement(driver, By.className("loginBtn"))) driver = login();
                    driver.get(domain + "/content/contenRecycle/recycleContent");
                    Thread.sleep(3000);
                    if (!CommonMethod.isJudgingElement(driver, By.className("fold-pack"))) {
                        CommonMethod.jumpModule(driver, "新闻管理");
                        driver.get(domain + "/content/contenRecycle/recycleContent");
                        Thread.sleep(2000);
                    }
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
