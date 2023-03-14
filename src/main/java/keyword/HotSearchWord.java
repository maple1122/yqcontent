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
public class HotSearchWord extends LoginPortal {

    static WebDriver driver;

    //添加热搜词
    public static void addHotSearchWord() throws InterruptedException {
        driver.findElement(By.id("searchName")).clear();//清空搜索关键词
        driver.findElement(By.id("searchName")).sendKeys("autoTest");//录入搜索关键词
        driver.findElement(By.id("confirmSearch")).click();//点击搜索
        Thread.sleep(1000);
        Boolean canAdd = true;

        if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@id='keywordListTable']/tbody/tr"))) {//校验是否有返回结果
            List<WebElement> listTr = driver.findElements(By.xpath("//table[@id='keywordListTable']/tbody/tr"));//获取数据列表
            for (int i = 0; i < listTr.size(); i++) {//校验数据中是否有“autoTest”热搜词（不区分大小写），如存在则不可重复添加
                if (listTr.get(i).findElement(By.xpath("td[3]/span")).getText().equalsIgnoreCase("autoTest")) {
                    canAdd = false;
                    break;
                }
            }
        }
        if (canAdd) {//如可添加
            driver.findElement(By.id("keywordEdit")).click();//点击添加热搜词
            Thread.sleep(500);
            driver.findElement(By.id("popKeywordName")).sendKeys("autoTest");//录入热搜词
            Thread.sleep(500);
            driver.findElement(By.className("layui-layer-btn0")).click();//点击保存
            System.out.println("~~~ addHotSearchWord()，添加热搜词，执行成功 ~~~");
        } else System.out.println("已经存在autoTest热搜词，无法重复添加");
        for (int i = 0; i < 10; i++) {
            Thread.sleep(3000);
            if (!CommonMethod.isJudgingElement(driver, By.className("layui-layer-btn0")))
                break;
        }
    }

    //编辑热搜词
    public static void editHotSearchWord() throws InterruptedException {
        driver.findElement(By.id("searchName")).clear();//清空搜索关键词
        driver.findElement(By.id("searchName")).sendKeys("autoTest");//录入搜索关键词
        driver.findElement(By.id("confirmSearch")).click();//点击搜索
        Thread.sleep(1000);
        if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@id='keywordListTable']/tbody/tr"))) {//校验是否有返回数据
            List<WebElement> listTr = driver.findElements(By.xpath("//table[@id='keywordListTable']/tbody/tr"));//获取数据列表
            listTr.get(0).findElement(By.xpath("td[5]/a")).click();//点击第一个的编辑
            Thread.sleep(500);
            driver.findElement(By.id("popKeywordName")).clear();//清空热搜词
            driver.findElement(By.id("popKeywordName")).sendKeys("autoTest" + System.currentTimeMillis());//录入修改后的热搜词
            Thread.sleep(500);
            driver.findElement(By.className("layui-layer-btn0")).click();//点击保存
            System.out.println("~~~ editHotSearchWord()，编辑热搜词，执行成功 ~~~");
        } else System.out.println("没有自动化auto测试数据");
        for (int i = 0; i < 10; i++) {
            Thread.sleep(3000);
            if (!CommonMethod.isJudgingElement(driver, By.className("layui-layer-btn0")))
                break;
        }
    }

    //删除热搜词
    public static void deleteHotSearchWord() throws InterruptedException {
        driver.findElement(By.id("searchName")).clear();//清空搜索关键词
        driver.findElement(By.id("searchName")).sendKeys("autoTest");//录入搜索关键词
        driver.findElement(By.id("confirmSearch")).click();//点击搜索
        Thread.sleep(2000);
        if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@id='keywordListTable']/tbody/tr"))) {//校验是否有返回数据
            driver.findElement(By.xpath("//table[@id='keywordListTable']/thead/tr/th/input")).click();//点击第一个词的选择
            Thread.sleep(200);
            driver.findElement(By.id("keywordDel")).click();//点击删除
            Thread.sleep(200);
            driver.findElement(By.className("layui-layer-btn0")).click();//确定删除
            System.out.println("~~~ deleteHotSearchWord()，删除热搜词，执行成功 ~~~");
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
                    driver.findElement(By.xpath("//div[@class='list-center']/a[4]")).click();
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
