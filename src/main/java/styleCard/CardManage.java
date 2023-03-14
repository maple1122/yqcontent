package styleCard;

import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * @author wufeng
 * @date 2022/3/3 14:34
 */
public class CardManage extends LoginPortal {

    static WebDriver driver;

    //媒体号内容样式卡签发
    public static void issue() throws InterruptedException {
        Search.searchAutoTest();//获取自动化数据

        if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='contentstyle']/li"))) {//校验是否有返回结果
            List<WebElement> cards = driver.findElements(By.xpath("//ul[@id='contentstyle']/li"));
            cards.get(0).findElement(By.xpath("div[4]/span[1]")).click();//点击签发
            for (int i = 0; i < 5; i++) {
                Thread.sleep(500);
                if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='publishChannelList_1_ul']/li"))) break;
            }
            //选择关联频道
            List<WebElement> list1, list2;
            Boolean selected = false;
            list1 = driver.findElements(By.xpath("//ul[@id='publishChannelList_1_ul']/li"));//第一层
            for (int i1 = 0; i1 < list1.size(); i1++) {
                list2 = list1.get(i1).findElements(By.xpath("ul/li"));//第二层
                for (int i2 = 0; i2 < list2.size(); i2++) {
                    if (list2.get(i2).findElement(By.tagName("a")).getAttribute("title").equals("测试test")) {
                        list2.get(i2).findElement(By.xpath("span[2]")).click();
                        selected = true;
                        break;
                    }
                }
                if (selected) break;
            }
            Thread.sleep(1000);
            if (selected) {
                driver.findElement(By.cssSelector("button.layui-btn.confirm")).click();//点击签发
                System.out.println("~~~ issue()，内容样式卡签发，执行成功 ~~~");
            } else {
                driver.findElement(By.cssSelector("button.layui-btn.cancel")).click();//点击取消
                System.out.println("没找到测试频道");
            }
        } else System.out.println("未找到auto测试数据");
        Thread.sleep(3000);
    }

    //编辑
    public static void edit() throws InterruptedException {
        Search.searchAutoTest();//获取自动化数据

        if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='contentstyle']/li"))) {//校验是否有返回结果
            List<WebElement> cards = driver.findElements(By.xpath("//ul[@id='contentstyle']/li"));//样式卡list
            cards.get(0).findElement(By.xpath("div[4]/span[2]")).click();//点击编辑
            Thread.sleep(1000);
            driver.findElement(By.xpath("//div[@class='layui-tab-item layui-show']/form/div/div/input")).clear();//清空标题编辑框
            driver.findElement(By.xpath("//div[@class='layui-tab-item layui-show']/form/div/div/input")).sendKeys("autoTest内容样式卡-update-" + System.currentTimeMillis());//录入编辑后的标题
            Thread.sleep(500);
            driver.findElement(By.className("layui-layer-btn0")).click();//点击确定保存
            System.out.println("~~~ edit()，内容样式卡编辑，执行成功 ~~~");
        } else System.out.println("没有可编辑的测试样式卡");
        Thread.sleep(3000);
    }

    //删除
    public static void delete() throws InterruptedException {
        Search.searchAutoTest();//获取自动化数据

        if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='contentstyle']/li"))) {//校验是否有返回结果
            List<WebElement> cards = driver.findElements(By.xpath("//ul[@id='contentstyle']/li"));//样式卡list
            int num = 0;
            for (int i = cards.size(); i > 0; i--) {
                if (driver.findElement(By.xpath("//ul[@id='contentstyle']/li[" + i + "]/div[1]/h5")).getText().contains("auto")) {
                    driver.findElement(By.xpath("//ul[@id='contentstyle']/li[" + i + "]/div[4]/span[3]")).click();//点击删除
                    Thread.sleep(500);
                    driver.findElement(By.className("layui-layer-btn0")).click();//确定删除
                    num++;
                    Thread.sleep(3000);
                }
            }
            if (num > 0) {
                System.out.println("~~~ delete()，内容样式卡删除，执行成功。共删除auto测试数据 " + num + " 条 ~~~");
            } else System.out.println("没有可删除的测试样式卡");
        }
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
