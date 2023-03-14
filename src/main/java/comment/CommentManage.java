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
 * 稿件评论管理
 * @date 2022/3/23 11:41
 */
public class CommentManage extends LoginPortal {

    static WebDriver driver;

    //发表评论
    public static void publish() throws InterruptedException {
        search();//选择测试频道并搜索测试数据
        if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@id='infoTabel']/tbody/tr"))) {//校验是否有数据
            List<WebElement> listTr = driver.findElements(By.xpath("//table[@id='infoTabel']/tbody/tr"));//获取数据list
            listTr.get(0).findElement(By.xpath("td[6]/a[1]")).click();//点击发表
            Thread.sleep(500);
            driver.findElement(By.cssSelector("input.layui-input.layui-unselect")).click();//点击虚拟发表人
            Thread.sleep(1000);
            List<WebElement> users = driver.findElements(By.xpath("//dl[@class='layui-anim layui-anim-upbit']/dd"));//虚拟用户列表
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getText().equals("autoTestwf")) {//选择用户昵称autoTestwf
                    users.get(i).click();//选择该虚拟用户
                    break;
                }
            }
            Thread.sleep(200);
            driver.findElement(By.name("desc")).clear();
            driver.findElement(By.name("desc")).sendKeys("autoTest-这是后台发表的评论-" + System.currentTimeMillis());//录入评论内容
            Thread.sleep(200);
            driver.findElement(By.className("layui-layer-btn0")).click();//点击发表
            System.out.println("~~~ publish()，发表评论，执行成功 ~~~");
        } else System.out.println("没有自动化测试稿件");
        Thread.sleep(3000);
    }

    //关闭评论
    public static void close() throws InterruptedException {
        search();//选择测试频道并搜索测试数据
        if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@id='infoTabel']/tbody/tr"))) {//校验是否有数据
            Boolean isClosed=false;
            List<WebElement> listTr = driver.findElements(By.xpath("//table[@id='infoTabel']/tbody/tr"));//数据列表list
                isClosed=listTr.get(0).findElement(By.xpath("td[6]/a[2]")).getText().contains("取消");//状态是否已关闭
                listTr.get(0).findElement(By.xpath("td[6]/a[2]")).click();//点击关闭/取消关闭
                if (!isClosed)System.out.println("~~~ close()，关闭评论，执行成功 ~~~");
                else System.out.println("~~~ close()，取消关闭评论，执行成功 ~~~");
        }else System.out.println("没有auto测试数据");
        Thread.sleep(3000);
    }

    //屏蔽评论
    public static void shiled() throws InterruptedException {
        search();//选择测试频道并搜索测试数据
        if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@id='infoTabel']/tbody/tr"))) {//校验是否有数据
            Boolean isClosed=false;
            List<WebElement> listTr = driver.findElements(By.xpath("//table[@id='infoTabel']/tbody/tr"));//获取数据列表
            isClosed=listTr.get(0).findElement(By.xpath("td[6]/a[3]")).getText().contains("取消");//状态是否已关闭
            listTr.get(0).findElement(By.xpath("td[6]/a[3]")).click();//点击关闭/取消关闭
            if (!isClosed)System.out.println("~~~ shiled()，屏蔽评论，执行成功 ~~~");
            else System.out.println("~~~ shiled()，取消屏蔽评论，执行成功 ~~~");
        }else System.out.println("没有auto测试数据");
        Thread.sleep(3000);
    }

    //搜索
    private static void search() throws InterruptedException {
        CommonMethod.getTestTree(driver);//选择测试频道
        driver.findElement(By.id("keyword")).clear();//清空关键词
        driver.findElement(By.id("keyword")).sendKeys("autoTest");//录入关键词
        driver.findElement(By.id("confirmSearch")).click();//搜索
        Thread.sleep(1000);
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
//                    driver.findElement(By.xpath("//div[@class='list-center']/a[2]")).click();
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
