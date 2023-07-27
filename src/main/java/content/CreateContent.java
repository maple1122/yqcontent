package content;

import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author wufeng
 * @date 2022/2/7 14:58
 */
public class CreateContent extends LoginPortal {

    static WebDriver driver;

    //新建文稿
    public static void createArticle() throws InterruptedException {

        CommonMethod.getTestTree(driver);//切换到测试频道

        String winHandleBefore = driver.getWindowHandle();
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.cssSelector("a.opt-btn.layui-btn"))).perform();//光标悬浮新建按钮
        Thread.sleep(500);
        driver.findElement(By.xpath("//dl[@id='addType']/dd[1]")).click();//点击新建文章

        CommonMethod.swichWindow(driver);

        Thread.sleep(1000);
        CommonMethod.acceptAlert(driver);
        Thread.sleep(3000);

        driver.findElement(By.name("title")).sendKeys("autoTest-CreateArticle" + System.currentTimeMillis());//录入标题
        driver.switchTo().frame("ueditor_0");//切换到编辑框iframe
        Thread.sleep(200);
        driver.findElement(By.tagName("p")).sendKeys("这是稿件内容~~~~~");//编辑框录入内容
        Thread.sleep(200);
        driver.switchTo().defaultContent();//切换到默认的页面
        Thread.sleep(200);
        if (CommonMethod.isJudgingElement(driver, By.cssSelector("div.edui-box.edui-button.edui-for-searcherrorcode.edui-default"))) {//如果有智能校验，进行智能校验，部分项目新增文稿必须进行智能校验
            driver.findElement(By.cssSelector("div.edui-box.edui-button.edui-for-searcherrorcode.edui-default")).click();//点击智能校验
            for (int i = 0; i < 3; i++) {
                Thread.sleep(2000);
                if (CommonMethod.isJudgingElement(driver, By.id("errorInfo"))) {
                    Thread.sleep(200);
                    driver.findElement(By.cssSelector("span.right-box-close.layui-icon.layui-icon-close")).click();
                    Thread.sleep(500);
                    break;
                }
            }
        }
        driver.findElement(By.xpath("//form[@id='attributeInfo']/div[@class='content-item']/div[@class='content-item-body item-no-border']/div[4]/div/div[last()]/i")).click();//排版方式点击文本
        Thread.sleep(500);
        driver.findElement(By.id("saveAndCloseBtn")).click();//点击保存
        System.out.println("~~~ createArticle()，新建文稿，执行成功 ~~~");
        Thread.sleep(3000);

        driver.switchTo().window(winHandleBefore);

        Thread.sleep(2000);
    }

    //新建图集稿件
    public static void createImage() throws InterruptedException {

        CommonMethod.getTestTree(driver);//切换到测试频道
        String winHandleBefore = driver.getWindowHandle();

        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.cssSelector("a.opt-btn.layui-btn"))).perform();//光标悬浮新建按钮
        Thread.sleep(500);
        driver.findElement(By.xpath("//dl[@id='addType']/dd[2]")).click();//点击新建图集

        //切换标签页
        CommonMethod.swichWindow(driver);

        Thread.sleep(1000);
        CommonMethod.acceptAlert(driver);
        Thread.sleep(3000);

        driver.findElement(By.name("title")).sendKeys("autoTestCreateImage" + System.currentTimeMillis());//录入标题
        Thread.sleep(500);
        driver.findElement(By.id("onlinePic")).click();//点击在线资源库
        Thread.sleep(500);
        driver.switchTo().frame("layui-layer-iframe1");//切换到素材库图层
        CommonMethod.getPic(driver);//选择素材库图片
        Thread.sleep(1000);
        driver.switchTo().defaultContent();//切换到默认页面
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id='attributeInfo']/div[1]/div[2]/div[5]/div/div/div[1]/a[1]")).click();//点击列表图上传
        Thread.sleep(500);
        CommonMethod.getImg(driver);//素材库选择列表图
        Thread.sleep(500);

        driver.findElement(By.id("saveAndCloseBtn")).click();//点击保存
        System.out.println("~~~ createImage()，新建图集稿，执行成功 ~~~");
        Thread.sleep(3000);

        driver.switchTo().window(winHandleBefore);
        Thread.sleep(2000);
    }

    //新建视频稿件
    public static void createVideo() throws InterruptedException {

        CommonMethod.getTestTree(driver);//切换到测试频道
        String winHandleBefore = driver.getWindowHandle();

        WebDriverWait w = new WebDriverWait(driver, 10);
        Actions actions = new Actions(driver);

        actions.moveToElement(driver.findElement(By.cssSelector("a.opt-btn.layui-btn"))).perform();//光标悬浮新建按钮
        Thread.sleep(500);
        driver.findElement(By.xpath("//dl[@id='addType']/dd[3]")).click();//点击新建视频稿件

        //切换标签页
        CommonMethod.swichWindow(driver);

        Thread.sleep(1000);
        CommonMethod.acceptAlert(driver);
        Thread.sleep(3000);

        driver.findElement(By.name("title")).sendKeys("autoTestCreateVideo" + System.currentTimeMillis());//录入标题
        Thread.sleep(500);

        driver.findElement(By.cssSelector("div.up-video-online.up-video-tool")).click();//点击在线资源库

        Thread.sleep(500);
        driver.switchTo().frame("layui-layer-iframe1");//切换到素材库图层
        CommonMethod.getPic(driver);//选择素材库视频
        Thread.sleep(2000);
        driver.switchTo().defaultContent();//切换到默认页面

        Thread.sleep(2000);
        if (!CommonMethod.isJudgingElement(driver, By.className("sy-tools_setImg"))) Thread.sleep(2000);
        driver.findElement(By.className("sy-tools_setImg")).click();//视频封面设置为列表图
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("button.cropSet-button.ok.save")).click();//确定设置为列表图
        Thread.sleep(2000);

        driver.findElement(By.id("saveAndCloseBtn")).click();//点击保存
        System.out.println("~~~ createVideo()，新建视频稿，执行成功 ~~~");
        Thread.sleep(3000);

        driver.switchTo().window(winHandleBefore);
        Thread.sleep(2000);
    }

    //新建音频稿件
    public static void createAudio() throws InterruptedException {

        CommonMethod.getTestTree(driver);//切换到测试频道
        String winHandleBefore = driver.getWindowHandle();

        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.cssSelector("a.opt-btn.layui-btn"))).perform();//光标悬浮新建按钮
        Thread.sleep(500);
        driver.findElement(By.xpath("//dl[@id='addType']/dd[4]")).click();//点击新建音频稿件

        //切换标签页
        CommonMethod.swichWindow(driver);

        Thread.sleep(1000);
        CommonMethod.acceptAlert(driver);
        Thread.sleep(3000);

        driver.findElement(By.name("title")).sendKeys("autoTestCreateVideo" + System.currentTimeMillis());//录入标题
        Thread.sleep(200);

        driver.findElement(By.cssSelector("div.up-video-online.up-video-tool")).click();//点击在线资源库

        Thread.sleep(500);
        driver.switchTo().frame("layui-layer-iframe1");//切换到素材库图层
        CommonMethod.getAudio(driver);//选择素材库音频
        Thread.sleep(2000);
        driver.switchTo().defaultContent();//切换到默认页面
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id='attributeInfo']/div[1]/div[2]/div[5]/div/div/div[1]/a[1]")).click();//点击列表图上传
        Thread.sleep(500);
        CommonMethod.getImg(driver);//素材库选择列表图
        Thread.sleep(1000);

        driver.findElement(By.id("saveAndCloseBtn")).click();//点击保存
        System.out.println("~~~ createAudio()，新建音频稿，执行成功 ~~~");
        Thread.sleep(3000);

        driver.switchTo().window(winHandleBefore);
        Thread.sleep(2000);
    }

    //初始化登录
    static {
        try {
            driver = login();//初始化浏览器
            for (int i = 0; i < 3; i++) {
                if (!CommonMethod.isJudgingElement(driver, By.tagName("header"))) {//校验是否没有header（header是portal页面中的标签）
                    if (CommonMethod.isJudgingElement(driver, By.className("loginBtn")))
                        driver = login();//是否需要登录，需要登录则登录
                    driver.get(domain + "/content/content/list/init");//url直接跳转到新闻管理链接
                    Thread.sleep(3000);
                    if (!CommonMethod.isJudgingElement(driver, By.className("fold-pack"))) {//是否未跳转到新闻管理页面成功
                        CommonMethod.jumpModule(driver, "新闻管理");//点击新闻管理切换到新标签页
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
