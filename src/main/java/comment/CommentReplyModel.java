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
 * 回复模板设置
 * @date 2023/3/9 16:48
 */
public class CommentReplyModel extends LoginPortal {
    static WebDriver driver;

    //新建回复模板
    public static void addModel() throws InterruptedException {
        driver.findElement(By.id("addTpl")).click();//点击新建回复模板
        Thread.sleep(500);
        driver.findElement(By.id("tplTitle")).sendKeys("autoTest");//录入标题
        driver.findElement(By.id("tplContent")).sendKeys("autoTest回复模板内容" + System.currentTimeMillis());//录入内容
        Thread.sleep(100);
        driver.findElement(By.className("layui-layer-btn0")).click();//点击确定
        System.out.println("~~~ addModel()，新增回复模板，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //编辑回复模板
    public static void editModel() throws InterruptedException {
        WebElement model = getAutoModel();//获取autoTest测试模板
        if (model != null) {
            model.findElement(By.xpath("./td[5]/a[1]")).click();//点击编辑
            Thread.sleep(500);
            driver.findElement(By.xpath("//form[@id='editTplForm']/div[2]/div/textarea")).sendKeys("-update" + System.currentTimeMillis());//编辑回复内容
            Thread.sleep(200);
            driver.findElement(By.className("layui-layer-btn0")).click();//点击确定
            System.out.println("~~~ editModel()，编辑回复模板，执行成功 ~~~");
        } else System.out.println("~~~ 没找到autoTest回复模板 ~~~");
        Thread.sleep(3000);
    }

    //删除回复模板
    public static void deleteModel() throws InterruptedException {
        WebElement model = getAutoModel();//获取autoTest测试模板
        if (model != null) {
            model.findElement(By.xpath("./td[5]/a[2]")).click();//点击删除
            Thread.sleep(500);
            driver.findElement(By.className("layui-layer-btn0")).click();//点击确定
            System.out.println("~~~ deleteModel()，删除回复模板，执行成功 ~~~");
        } else System.out.println("~~~ 没找到autoTest回复模板 ~~~");
        Thread.sleep(3000);
    }

    //开启关闭回复模板
    public static void turnOnOrOff() throws InterruptedException {
        if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@id='infoTabel']/tbody/tr"))) {//判断是否有模板数据
            List<WebElement> trs = driver.findElements(By.xpath("//table[@id='infoTabel']/tbody/tr"));//获取数据list
            boolean haschecked = false;//是否有已开启的模板
            for (int i = 0; i < trs.size(); i++) {//循环遍历模板数据
                if (trs.get(i).findElement(By.xpath("./td[4]/span")).getAttribute("class").contains("checked")) {//获取到已开启的模板
                    haschecked = true;//打标有已开启模板
                    trs.get(i).findElement(By.xpath("./td[4]/span")).click();//点击关闭
                    System.out.println("~~~ turnOnOrOff()，关闭回复模板，执行成功 ~~~");
                    break;
                }
            }
            if (!haschecked) {//没有已开启的模板
                trs.get(0).findElement(By.xpath("./td[4]/span")).click();//点击开启
                System.out.println("~~~ turnOnOrOff()，开启回复模板，执行成功 ~~~");
            }
        } else System.out.println("~~~ 回复模板没有数据 ~~~");
        Thread.sleep(3000);
    }

    //查询autoTest模板
    private static WebElement getAutoModel() throws InterruptedException {
        if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@id='infoTabel']/tbody/tr"))) {//校验是否有数据
            List<WebElement> trs = driver.findElements(By.xpath("//table[@id='infoTabel']/tbody/tr"));//获取数据列表
            for (int i = 0; i < trs.size(); i++) {//循环遍历模板列表
                if (trs.get(i).findElement(By.xpath("./td[2]")).getText().contains("auto")) {//校验是否有auto关键词
                    return trs.get(i);//返回名称有auto关键词的模板
                }
            }
        } else {//模板列表没有数据
            addModel();//添加测试回复模板
            List<WebElement> trs = driver.findElements(By.xpath("//table[@id='infoTabel']/tbody/tr"));//重新活动列表数据
            for (int i = 0; i < trs.size(); i++) {//循环遍历模板数据
                if (trs.get(i).findElement(By.xpath("./td[2]")).getText().contains("auto")) {//获取到auto名称的数据
                    return trs.get(i);//返回auto回复模板
                }
            }
        }
        return null;
    }

    //初始化登录
    static {
        try {
            driver = login();//调起浏览器，进行portal账号登录
            for (int i = 0; i < 3; i++) {//登录异常可以重试三次
                if (!CommonMethod.isJudgingElement(driver, By.tagName("header"))) {//校验是否还未打开功能页面，如新闻管理页面
                    if (CommonMethod.isJudgingElement(driver, By.className("loginBtn"))) driver = login();//校验当前页面是否还有登录框，如还未登录成功则再次登录
                    driver.get(domain + "/content/cmsContentCommentTpl/tpl");//跳转到回复模板设置页面，有些环境（如基线）不允许直接跳转，需要先点击新闻管理
                    Thread.sleep(3000);
                    if (!CommonMethod.isJudgingElement(driver, By.className("fold-pack"))) {//校验上一步get链接是否未跳转成功，仍然停在portal默认页面
                        CommonMethod.jumpModule(driver, "新闻管理");//点击新闻管理，切换到新的新闻管理标签页
                        Thread.sleep(2000);
                        driver.get(domain + "/content/cmsContentCommentTpl/tpl");//跳转到回复模板设置页
                    }
                    Thread.sleep(2000);
                } else break;
            }

            //校验是否是目标租户站点应用（主要针对一个租户多个站点应用的情况）
            if (!driver.findElement(By.xpath("//div[@class='nav-right']/ul/li/a")).getText().contains(siteName)) {//校验当前机构应用非期望机构应用
                Actions action = new Actions(driver);
                action.moveToElement(driver.findElement(By.className("nav-right"))).perform();//光标悬浮机构应用显示出机构应用列表
                Thread.sleep(500);
                driver.findElement(By.linkText(siteName)).click();//找到目标机构应用并点击切换
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
