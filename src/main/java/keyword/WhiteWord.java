package keyword;

import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * @author wufeng
 * @date 2022/3/23 17:08
 */
public class WhiteWord extends LoginPortal {

    static WebDriver driver;

    //添加白名单
    public static void addWhiteWord() throws InterruptedException {
        driver.findElement(By.id("searchName")).clear();//清空搜索关键词
        driver.findElement(By.id("searchName")).sendKeys("autoTest");//录入搜索关键词
        driver.findElement(By.id("confirmSearch")).click();//点击搜索
        Thread.sleep(1000);
        Boolean canAdd = true;

        if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@id='keywordListTable']/tbody/tr"))) {//校验是否有数据
            List<WebElement> listTr = driver.findElements(By.xpath("//table[@id='keywordListTable']/tbody/tr"));//获取数据list
            for (int i = 0; i < listTr.size(); i++) {//校验是否已经存在“autoTest”白名单，如已存在，则不可再添加
                if (listTr.get(i).findElement(By.xpath("td[3]/span")).getText().equalsIgnoreCase("autoTest")) {
                    canAdd = false;
                    break;
                }
            }
        }
        if (canAdd) {//可以添加
            driver.findElement(By.id("whiteEdit")).click();//点击添加白名单
            Thread.sleep(500);
            driver.findElement(By.id("popKeywordName")).sendKeys("autoTest");//录入白名单autoTest
            Thread.sleep(500);
            driver.findElement(By.className("layui-layer-btn0")).click();//点击确定
            System.out.println("~~~ addWhiteWord()，添加白名单，执行成功 ~~~");
        } else System.out.println("已经存在autoTest敏感词，无法重复添加");
        for (int i = 0; i < 10; i++) {
            Thread.sleep(3000);
            if (!CommonMethod.isJudgingElement(driver, By.className("layui-layer-btn0")))
                break;
        }
    }

    //编辑白名单
    public static void editWhiteWord() throws InterruptedException {
        driver.findElement(By.id("searchName")).clear();//清空搜索关键词
        driver.findElement(By.id("searchName")).sendKeys("autoTest");//录入搜索关键词
        driver.findElement(By.id("confirmSearch")).click();//点击搜索
        Thread.sleep(1000);
        if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@id='keywordListTable']/tbody/tr"))) {//校验是否有数据
            List<WebElement> listTr = driver.findElements(By.xpath("//table[@id='keywordListTable']/tbody/tr"));//数据list
            listTr.get(0).findElement(By.xpath("td[5]/a")).click();//第一个数据的编辑
            Thread.sleep(500);
            driver.findElement(By.id("popKeywordName")).clear();//清空白名单
            driver.findElement(By.id("popKeywordName")).sendKeys("autoTest" + System.currentTimeMillis());//录入编辑后的白名单
            Thread.sleep(500);
            driver.findElement(By.className("layui-layer-btn0")).click();//点击保存
            System.out.println("~~~ editWhiteWord()，编辑白名单，执行成功 ~~~");
        } else System.out.println("没有自动化auto测试数据");
        for (int i = 0; i < 10; i++) {
            Thread.sleep(3000);
            if (!CommonMethod.isJudgingElement(driver, By.className("layui-layer-btn0")))
                break;
        }
    }

    //删除白名单
    public static void deleteWhiteWord() throws InterruptedException {
        driver.findElement(By.id("searchName")).clear();//清空搜索关键词
        driver.findElement(By.id("searchName")).sendKeys("autoTest");//录入搜索关键词
        driver.findElement(By.id("confirmSearch")).click();//点击搜索
        Thread.sleep(2000);
        if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@id='keywordListTable']/tbody/tr"))) {//校验是否有数据
            driver.findElement(By.xpath("//table[@id='keywordListTable']/thead/tr/th/input")).click();//数据列表
            Thread.sleep(200);
            driver.findElement(By.id("keywordDel")).click();//点击删除
            Thread.sleep(200);
            driver.findElement(By.className("layui-layer-btn0")).click();//点击确定
            System.out.println("~~~ deleteWhiteWord()，删除白名单，执行成功 ~~~");
        } else System.out.println("没有自动化auto测试数据");
        for (int i = 0; i < 10; i++) {
            Thread.sleep(3000);
            if (!CommonMethod.isJudgingElement(driver, By.className("layui-layer-btn0")))
                break;
        }
    }

    //初始化登录
    static {
        try {
            driver = login();
            for (int i = 0; i < 3; i++) {
                if (!CommonMethod.isJudgingElement(driver, By.tagName("header"))) {
                    if (CommonMethod.isJudgingElement(driver, By.className("loginBtn"))) driver = login();
                    driver.get(domain + "/content/keyword/manageKeyword");
                    Thread.sleep(3000);
                    if (!CommonMethod.isJudgingElement(driver, By.className("fold-pack"))) {
                        CommonMethod.jumpModule(driver, "新闻管理");
                        Thread.sleep(2000);
                        driver.get(domain + "/content/keyword/manageKeyword");
                    }
                    driver.findElement(By.xpath("//div[@class='list-center']/a[3]")).click();
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
