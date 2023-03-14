package comment;

import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * @author wufeng
 * 评论审核
 * @date 2022/3/23 11:40
 */
public class CommentCheck extends LoginPortal {

    static WebDriver driver;

    //审核
    public static void check() throws InterruptedException {
        searchByKeyword("autoTest");
        if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@id='infoTable']/tbody/tr"))) {//校验是否取到了数据
            List<WebElement> listTr = driver.findElements(By.xpath("//table[@id='infoTable']/tbody/tr"));//数据list
            listTr.get(0).findElement(By.xpath("td[10]/a[1]")).click();//点击第一条数据的审核
            Thread.sleep(500);
            driver.findElement(By.className("layui-layer-btn0")).click();//确定审核
            System.out.println("~~~ check()，评论审核，执行通过 ~~~");
        } else System.out.println("没有评论数据");
        Thread.sleep(3000);
    }

    //屏蔽
    public static void shield() throws InterruptedException {
        searchByKeyword("autoTest");
        if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@id='infoTable']/tbody/tr"))) {//校验是否取到了数据
            List<WebElement> listTr = driver.findElements(By.xpath("//table[@id='infoTable']/tbody/tr"));//数据list
            String oper = listTr.get(0).findElement(By.xpath("td[10]/a[2]")).getText();//获取是否已屏蔽
            listTr.get(0).findElement(By.xpath("td[10]/a[2]")).click();//点击屏蔽/取消屏蔽
            Thread.sleep(500);
            if (!oper.contains("取消"))//校验是否正常状态未屏蔽
                System.out.println("~~~ shield()，评论屏蔽，执行通过 ~~~");
            else System.out.println("~~~ shield()，评论取消屏蔽，执行通过 ~~~");
        } else System.out.println("没有评论数据");
        Thread.sleep(3000);
    }

    //测试删除
    public static void delete() throws InterruptedException {
        searchByKeyword("autoTest");
        if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@id='infoTable']/tbody/tr"))) {//校验是否取到了数据
            List<WebElement> listTr = driver.findElements(By.xpath("//table[@id='infoTable']/tbody/tr"));//数据list
            listTr.get(0).findElement(By.xpath("td[10]/a[3]")).click();//点击删除
            Thread.sleep(500);
            driver.findElement(By.className("layui-layer-btn0")).click();//确定确定
            System.out.println("~~~ delete()，评论删除，执行通过 ~~~");
        } else System.out.println("没有评论数据");
        Thread.sleep(3000);
    }

    //测试测试评论数据
    public static void deleteAuto() throws InterruptedException {
        searchByKeyword("autoTest");
        if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@id='infoTable']/tbody/tr"))) {
            driver.findElement(By.xpath("//table[@id='infoTable']/thead/tr/th[1]/input")).click();//点击全选
            Thread.sleep(200);
            driver.findElement(By.id("deleteAllBtn")).click();//点击删除
            Thread.sleep(200);
            driver.findElement(By.className("layui-layer-btn0")).click();//确定删除
            System.out.println("~~~ deleteAuto()，清除auto评论数据，执行成功 ~~~");
        } else System.out.println("无autoTest测试评论数据");
        Thread.sleep(3000);
    }

    //禁言
    public static void muted() throws InterruptedException {
        searchByKeyword("autoTest");
        if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@id='infoTable']/tbody/tr"))) {//校验是否取到了数据
            List<WebElement> listTr = driver.findElements(By.xpath("//table[@id='infoTable']/tbody/tr"));//数据list
            String oper = listTr.get(0).findElement(By.xpath("td[10]/a[4]")).getText();//获取是否已禁言
            String user = listTr.get(0).findElement(By.xpath("td[4]")).getText();//被禁言用户名
            listTr.get(0).findElement(By.xpath("td[10]/a[4]")).click();//点击禁言/取消禁言
            Thread.sleep(500);
            driver.findElement(By.className("layui-layer-btn0")).click();
            if (!oper.contains("取消"))//校验是否正常状态未屏蔽
                System.out.println("~~~ muted()，用户" + user + "被禁言，执行通过 ~~~");
            else System.out.println("~~~ muted()，用户" + user + "被取消禁言，执行通过 ~~~");
        } else System.out.println("没有评论数据");
        Thread.sleep(3000);
    }

    //回复
    public static void reply() throws InterruptedException {
        searchByKeyword("autoTest");
        if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@id='infoTable']/tbody/tr"))) {//校验是否取到了数据
            List<WebElement> listTr = driver.findElements(By.xpath("//table[@id='infoTable']/tbody/tr"));//数据list
            listTr.get(0).findElement(By.xpath("td[10]/a[5]")).click();
            Thread.sleep(500);
        }
    }

    //搜索测试数据-关键词
    private static void searchByKeyword(String keyword) throws InterruptedException {
        driver.findElement(By.id("keyword")).clear();//清空搜索关键词
        driver.findElement(By.id("keyword")).sendKeys(keyword);//搜索关键词
        driver.findElement(By.id("confirmSearch")).click();//点击搜索
        Thread.sleep(2000);
    }

    //搜索测试数据-通过时间，默认搜索2020年前的数据
    private static void searchByTime() throws InterruptedException {
//        driver.findElement(By.id("begin")).clear();//清空开始时间输入框
        driver.findElement(By.id("end")).clear();//清空结束时间输入框
//        driver.findElement(By.id("begin")).sendKeys("2020-01-01");//输入查询开始时间
        driver.findElement(By.id("end")).sendKeys("2019-12-31");//输入查询结束时间
        driver.findElement(By.id("confirmSearch")).click();//点击搜索
        Thread.sleep(2000);
    }

    //初始化登录
    static {
        try {
            driver = login();
            for (int i = 0; i < 3; i++) {
                if (!CommonMethod.isJudgingElement(driver, By.tagName("header"))) {
                    if (CommonMethod.isJudgingElement(driver, By.className("loginBtn"))) driver = login();
                    driver.get(domain + "/content/cmsContentComment/commentCheck");
                    Thread.sleep(3000);
                    if (!CommonMethod.isJudgingElement(driver, By.className("fold-pack"))) {
                        CommonMethod.jumpModule(driver, "新闻管理");
                        Thread.sleep(2000);
                        driver.get(domain + "/content/cmsContentComment/commentCheck");
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
