package styleCard;

import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * @author wufeng
 * @date 2022/3/3 14:27
 */
public class Search extends LoginPortal {

    static WebDriver driver;

    //搜索标题包含autoTest的样式卡
    public static void searchAutoTest() throws InterruptedException {
        search("autoTest");
    }

    //传关键词，搜索样式卡
    public static void search(String keyword) throws InterruptedException {
        driver.findElement(By.cssSelector("input.layui-input.searchInput")).clear();//清空搜索关键词
        driver.findElement(By.cssSelector("input.layui-input.searchInput")).sendKeys(keyword);//录入搜索关键词
        driver.findElement(By.id("searchBtn")).click();//点击搜索
        Thread.sleep(3000);
    }

    //初始化登录
    static {
        try {
            driver = login();
            for (int i = 0; i < 3; i++) {
                if (!CommonMethod.isJudgingElement(driver, By.tagName("header"))) {
                    if (CommonMethod.isJudgingElement(driver, By.className("loginBtn"))) driver = login();
                    driver.get(domain + "/content/content/list/init");
                    Thread.sleep(3000);
                    if (!CommonMethod.isJudgingElement(driver, By.className("fold-pack"))) {
                        CommonMethod.jumpModule(driver, "新闻管理");
                        Thread.sleep(2000);
                    }
                } else break;
            }
            if (!driver.findElement(By.xpath("//div[@class='nav-right']/ul/li/a")).getText().contains(siteName)) {
                Actions action = new Actions(driver);
                action.moveToElement(driver.findElement(By.className("nav-right"))).perform();
                Thread.sleep(500);
                driver.findElement(By.linkText(siteName)).click();
                Thread.sleep(2000);
            } else {
                driver.navigate().refresh();
                Thread.sleep(2000);
            }
            CommonMethod.changeMenu(driver, "内容样式卡");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
