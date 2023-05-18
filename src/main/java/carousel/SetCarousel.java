package carousel;

import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author wufeng
 * @date 2022/3/22 15:58
 */
public class SetCarousel extends LoginPortal {


    static WebDriver driver;

    //编辑新闻稿件开启/关闭栏目轮播
    public static void setCarousel(Boolean open) throws InterruptedException {
        CommonMethod.getTestTree(driver);//切换到测试频道
        WebElement article = CommonMethod.getTestArticle(driver, 2);//获取测试稿件
        Thread.sleep(2000);

        if (article != null) {
            String winHandleBefore = driver.getWindowHandle();
            Actions sj = new Actions(driver);

            sj.doubleClick(article).build().perform();//对按钮进行双击

            Thread.sleep(500);
            CommonMethod.swichWindow(driver);//切换标签页
            Thread.sleep(1000);
            CommonMethod.acceptAlert(driver);//有alert弹窗则关闭
            Thread.sleep(3000);

            if (open) {//开启栏目轮播
                driver.findElement(By.name("title")).clear();//清空标题
                driver.findElement(By.name("title")).sendKeys("autoTest-轮播" + System.currentTimeMillis());//编辑标题
                Thread.sleep(500);
                if (CommonMethod.isJudgingElement(driver, By.id("attributeInfo"))) {
                    if (!driver.findElement(By.xpath("//form[@id='attributeInfo']/div/div[2]/div[2]/div/div")).getAttribute("class").contains("checked")) {//校验是否未上栏目轮播
                        driver.findElement(By.xpath("//form[@id='attributeInfo']/div/div[2]/div[2]/div/div")).click();//点击栏目轮播
                        driver.findElement(By.xpath("//form[@id='attributeInfo']/div/div[2]/div[3]/div/div/div")).click();//点击上传图片
                        Thread.sleep(500);
                        CommonMethod.getImg(driver);//在线资源库获取图片
                        driver.findElement(By.id("saveAndCloseBtn")).click();//点击保存
                        System.out.println("~~~ setCarousel()，设置开启栏目轮播，执行成功 ~~~");
                    } else {
                        System.out.println("已开启栏目轮播");
                        driver.close();
                    }
                } else {
                    if (CommonMethod.isJudgingElement(driver, By.xpath("//form[@id='formDemo']/div[@class='layui-form-item live-type']"))) {//校验是否未上栏目轮播
                        driver.findElement(By.xpath("//form[@id='formDemo']/div[@class='layui-form-item live-type']/div/div")).click();
                        Thread.sleep(200);
                        driver.findElement(By.xpath("//form[@id='formDemo']/div[5]/div/div/div[1]/a[1]")).click();
                        Thread.sleep(500);
                        CommonMethod.getImg(driver);//在线资源库获取图片
                        driver.findElement(By.id("saveBtn")).click();//点击保存
                        System.out.println("~~~ setCarousel()，设置开启栏目轮播，执行成功 ~~~");
                    } else {
                        System.out.println("已开启栏目轮播");
                        driver.close();
                    }
                }
            } else {//关闭栏目轮播
                driver.findElement(By.name("title")).clear();//清空标题
                driver.findElement(By.name("title")).sendKeys("autoTest-Edit" + System.currentTimeMillis());//编辑标题
                Thread.sleep(500);
                if (driver.findElement(By.xpath("//form[@id='attributeInfo']/div/div[2]/div[2]/div/div")).getAttribute("class").contains("checked")) {//校验是否已勾选栏目轮播
                    driver.findElement(By.xpath("//form[@id='attributeInfo']/div/div[2]/div[2]/div/div")).click();//点击栏目轮播
                    driver.findElement(By.xpath("//form[@id='attributeInfo']/div/div[2]/div[3]/div/div/a")).click();//点击删除轮播图片
                    Thread.sleep(500);
                    driver.findElement(By.id("saveAndCloseBtn")).click();//点击保存
                    System.out.println("~~~ setCarousel()，设置关闭栏目轮播，执行成功 ~~~");
                } else {
                    System.out.println("未开启栏目轮播");
                    driver.close();
                }
            }
            Thread.sleep(1000);
            driver.switchTo().window(winHandleBefore);

        } else System.out.println("没找到autoTest数据");
        Thread.sleep(3000);
    }

    //编辑新闻稿件开启/关闭上轮播
    public static void setCarousel2(Boolean open) throws InterruptedException {
        CommonMethod.getTestTree(driver);//切换到测试频道
        WebElement article = CommonMethod.getTestArticle(driver, 2);//获取测试稿件
        Thread.sleep(2000);

        if (article != null) {
            String winHandleBefore = driver.getWindowHandle();
            Actions sj = new Actions(driver);

            sj.doubleClick(article).build().perform();//对按钮进行双击

            Thread.sleep(500);
            CommonMethod.swichWindow(driver);//切换标签页
            Thread.sleep(1000);
            CommonMethod.acceptAlert(driver);//有alert弹窗则关闭
            Thread.sleep(3000);

            if (open) {//开启栏目轮播
                driver.findElement(By.name("title")).clear();//清空标题
                driver.findElement(By.name("title")).sendKeys("autoTest-轮播" + System.currentTimeMillis());//编辑标题
                Thread.sleep(500);
                if (CommonMethod.isJudgingElement(driver, By.id("attributeInfo"))) {
                    if (!driver.findElement(By.xpath("//form[@id='attributeInfo']/div/div[2]/div[2]/div/div[2]")).getAttribute("class").contains("checked")) {//校验是否未上栏目轮播
                        driver.findElement(By.xpath("//form[@id='attributeInfo']/div/div[2]/div[2]/div/div[2]")).click();//点击栏目轮播
                        driver.findElement(By.xpath("//form[@id='attributeInfo']/div/div[2]/div[3]/div/div/div")).click();//点击上传图片
                        Thread.sleep(500);
                        CommonMethod.getImg(driver);//在线资源库获取图片
                        driver.findElement(By.id("saveAndCloseBtn")).click();//点击保存
                        System.out.println("~~~ setCarousel2()，设置开启上轮播，执行成功 ~~~");
                    } else {
                        System.out.println("已开启上轮播");
                        driver.close();
                    }
                } else {
                    if (CommonMethod.isJudgingElement(driver, By.xpath("//form[@id='formDemo']/div[@class='layui-form-item live-type']"))) {//校验是否未上轮播
                        driver.findElement(By.xpath("//form[@id='formDemo']/div[@class='layui-form-item live-type']/div/div")).click();
                        Thread.sleep(200);
                        driver.findElement(By.xpath("//form[@id='formDemo']/div[5]/div/div/div[1]/a[1]")).click();
                        Thread.sleep(500);
                        CommonMethod.getImg(driver);//在线资源库获取图片
                        driver.findElement(By.id("saveBtn")).click();//点击保存
                        System.out.println("~~~ setCarousel2()，设置开启上轮播，执行成功 ~~~");
                    } else {
                        System.out.println("已开启上轮播");
                        driver.close();
                    }
                }
            } else {//关闭上轮播
                driver.findElement(By.name("title")).clear();//清空标题
                driver.findElement(By.name("title")).sendKeys("autoTest-Edit" + System.currentTimeMillis());//编辑标题
                Thread.sleep(500);
                if (driver.findElement(By.xpath("//form[@id='attributeInfo']/div/div[2]/div[2]/div/div[2]")).getAttribute("class").contains("checked")) {//校验是否已勾选栏目轮播
                    driver.findElement(By.xpath("//form[@id='attributeInfo']/div/div[2]/div[2]/div/div[2]")).click();//点击栏目轮播
                    driver.findElement(By.xpath("//form[@id='attributeInfo']/div/div[2]/div[3]/div/div/a")).click();//点击删除轮播图片
                    Thread.sleep(500);
                    driver.findElement(By.id("saveAndCloseBtn")).click();//点击保存
                    System.out.println("~~~ setCarousel2()，设置关闭上轮播，执行成功 ~~~");
                } else {
                    System.out.println("未开启上轮播");
                    driver.close();
                }
            }
            Thread.sleep(1000);
            driver.switchTo().window(winHandleBefore);//返回到上一个标签页
        } else System.out.println("没找到autoTest数据");
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
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
