package topic;

import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * @author wufeng
 * @date 2022/3/3 17:11
 */
public class TopicManage extends LoginPortal {

    static WebDriver driver;

    //专题签发
    public static void push() throws InterruptedException {
        Search.searchAutoTest(driver);

        if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@class='topic-contanier clearfix']/li"))) {//校验是否有数据
            List<WebElement> topics = driver.findElements(By.xpath("//ul[@class='topic-contanier clearfix']/li"));//获取auto数据list
            topics.get(0).findElement(By.xpath("div[@class='operate-area']/a[@class='push']")).click();//点击签发
            Thread.sleep(1500);
            CommonMethod.getTestTree(driver, "undefined_1_ul", "测试test");//选择签发频道
            Thread.sleep(500);
            driver.findElement(By.className("layui-layer-btn0")).click();//确定签发
            Thread.sleep(500);
            if (CommonMethod.isJudgingElement(driver, By.className("layui-layer-btn0"))) {
                driver.findElement(By.className("layui-layer-btn1")).click();
                System.out.println("签发失败，取消！");
            }
            System.out.println("~~~ push()，专题签发，执行成功 ~~~");
        } else System.out.println("没有auto测试专题");
        Thread.sleep(3000);
    }

    //发布
    public static void publish() throws InterruptedException {
        Search.searchAutoTest(driver);//获取自动化测试专题

        if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@class='topic-contanier clearfix']/li"))) {//校验是否有数据
            List<WebElement> topics = driver.findElements(By.xpath("//ul[@class='topic-contanier clearfix']/li"));//获取auto数据list
            Boolean isPublish = false;
            for (int i = 0; i < topics.size(); i++) {
                if (CommonMethod.isJudgingElement(topics.get(i), By.xpath("div[@class='operate-area']/a[@class='publish  ']"))) {//校验是否可发布
                    topics.get(i).findElement(By.xpath("div[@class='operate-area']/a[@class='publish  ']")).click();//点击发布
                    System.out.println("~~~ publish()，专题发布，执行成功 ~~~");
                    isPublish = true;
                    break;
                }
            }
            if (!isPublish) System.out.println("没有待签发的测试专题");
        } else System.out.println("没有auto自动化测试专题");
        Thread.sleep(3000);
    }

    //下线
    public static void offline() throws InterruptedException {
        Search.searchAutoTest(driver);//获取自动化测试专题

        if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@class='topic-contanier clearfix']/li"))) {//校验是否有数据
            List<WebElement> topics = driver.findElements(By.xpath("//ul[@class='topic-contanier clearfix']/li"));//获取auto数据list
            Boolean isOffLine = false;
            for (int i = 1; i < topics.size() + 1; i++) {//对所有自动化测试专题进行下线
                if (CommonMethod.isJudgingElement(driver, (By.xpath("//ul[@class='topic-contanier clearfix']/li[" + i + "]/div[@class='operate-area']/a[@class='offline ']")))) {//校验是否可下线
                    driver.findElement(By.xpath("//ul[@class='topic-contanier clearfix']/li[" + i + "]/div[@class='operate-area']/a[@class='offline ']")).click();//点击发布
                    isOffLine = true;
                    Thread.sleep(3000);
                }
            }
            if (isOffLine) System.out.println("~~~ offline()，专题下线，执行成功 ~~~");
            else System.out.println("没有可下线的测试专题");
        } else System.out.println("没有auto自动化测试专题");
        Thread.sleep(3000);
    }

    //编辑
    public static void edit() throws InterruptedException {
        Search.searchAutoTest(driver);//获取自动化测试专题

        if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@class='topic-contanier clearfix']/li"))) {//校验是否有数据
            List<WebElement> topics = driver.findElements(By.xpath("//ul[@class='topic-contanier clearfix']/li"));//获取auto数据list
            topics.get(0).findElement(By.xpath("div[@class='operate-area']/a[@class='more-btn']")).click();//点击更多
            Thread.sleep(500);
            topics.get(0).findElement(By.xpath("div[@class='operate-area']/ul/li[1]/a")).click();//点击编辑
            Thread.sleep(1000);
            for (int i = 1; i < 20; i++) {
                if (CommonMethod.isJudgingElement(driver, By.id("layui-layer-iframe" + i))) {//获取当前iframe有效id
                    driver.switchTo().frame("layui-layer-iframe" + i);//切换到编辑页iframe
                    break;
                }
            }
            Thread.sleep(500);
            String name1 = driver.findElement(By.name("title")).getAttribute("value");//获取原title值
            driver.findElement(By.name("title")).clear();//清空标题
            driver.findElement(By.name("title")).sendKeys("update-" + name1);//录入新标题
            driver.findElement(By.id("saveBtn")).click();//点击保存
            Thread.sleep(3000);
            if (CommonMethod.isJudgingElement(driver, By.id("saveBtn"))) Thread.sleep(3000);//判断是否已处理完
            driver.switchTo().defaultContent();//退出编辑iframe，返回到默认页面
            System.out.println("~~~ edit()，专题编辑，执行成功 ~~~");
        } else System.out.println("没有auto测试专题");
        Thread.sleep(3000);
    }

    //删除
    public static void delete() throws InterruptedException {
        Search.searchAutoTest(driver);//获取自动化测试专题

        if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@class='topic-contanier clearfix']/li"))) {//校验是否有数据
            List<WebElement> topics = driver.findElements(By.xpath("//ul[@class='topic-contanier clearfix']/li"));//获取auto数据list
            Boolean isDelete = false;
            for (int i = topics.size(); i > 0; i--) {//所有专题进行删除
                if (!CommonMethod.isJudgingElement(driver, By.xpath("//ul[@class='topic-contanier clearfix']/li[" + i + "]/div[@class='operate-area']/a[@class='offline ']"))) {//校验是否可删除
                    driver.findElement(By.xpath("//ul[@class='topic-contanier clearfix']/li[" + i + "]/div[@class='operate-area']/a[@class='more-btn']")).click();//点击更多
                    Thread.sleep(200);
                    driver.findElement(By.xpath("//ul[@class='topic-contanier clearfix']/li[" + i + "]/div[@class='operate-area']/ul/li[2]/a")).click();//点击删除
                    Thread.sleep(500);
                    driver.findElement(By.className("layui-layer-btn0")).click();//确定删除
                    isDelete = true;
                    Thread.sleep(3000);
                }
            }
            if (isDelete) System.out.println("~~~ delete()，专题删除，执行成功 ~~~");
            else System.out.println("没有可删除的测试专题");
        } else System.out.println("没有auto自动化测试专题");
        Thread.sleep(3000);
    }

    //排序
    public static void sort() throws InterruptedException {
        Search.searchAutoTest(driver);

        if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@class='topic-contanier clearfix']/li"))) {//校验是否有数据
            List<WebElement> topics = driver.findElements(By.xpath("//ul[@class='topic-contanier clearfix']/li"));//获取auto数据list
            topics.get(topics.size() - 1).findElement(By.xpath("div[@class='operate-area']/a[@class='more-btn']")).click();//点击更多
            Thread.sleep(200);
            topics.get(topics.size() - 1).findElement(By.xpath("div[@class='operate-area']/ul/li[3]/a")).click();//点击排序
            Thread.sleep(500);
            driver.findElement(By.xpath("//div[@id='sortNum']/div[2]/div/input")).sendKeys("1");//录入排序值
            driver.findElement(By.className("layui-layer-btn0")).click();//点击确定
            System.out.println("~~~ sort()，专题排序，执行成功 ~~~");
        } else System.out.println("没有auto自动化测试专题");
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
            CommonMethod.changeMenu(driver, "专题");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
