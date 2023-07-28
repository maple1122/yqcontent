package content;

import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * @author wufeng
 * @date 2023/3/10 11:15
 */
public class PopNews extends LoginPortal {

    static WebDriver driver;

    //新增首屏浮窗
    public static void addPopNews() throws InterruptedException {

        if (CommonMethod.isJudgingElement(driver, By.cssSelector("div.send-channel.bullet-news-list.layui-form.send-channel-close")))
            driver.findElement(By.id("ejectNews")).click();//点击首屏浮窗
        Thread.sleep(1000);

        driver.findElement(By.id("openBulletSelect")).click();//点击新增
        Thread.sleep(500);
        driver.findElement(By.className("float-window-select-btn")).click();//点击选择稿件
        Thread.sleep(2000);
        List<WebElement> list1, list2;
        list1 = driver.findElements(By.xpath("//ul[@id='ejectZtree_1_ul']/li"));//一级频道
        boolean hasTestChannel = false;
        for (int i = 0; i < list1.size(); i++) {//遍历一级频道
            if (CommonMethod.isJudgingElement(list1.get(i), By.xpath("./ul/li"))) {//如果一级频道下有二级频道
                list2 = list1.get(i).findElements(By.xpath("./ul/li"));//获取二级频道
                for (int j = 0; j < list2.size(); j++) {//遍历二级频道
                    if (list2.get(j).findElement(By.xpath("./a/span[2]")).getText().contains("测试test")) {//找到二级频道中名称为“测试test”的
                        list2.get(j).click();//点击测试test频道
                        hasTestChannel = true;//打标找到了测试test频道
                        Thread.sleep(100);
                        break;
                    }
                }
            }
            Thread.sleep(100);
            if (hasTestChannel) break;
        }
        Thread.sleep(1000);
        if (hasTestChannel) {//如果有测试test频道
            if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='ulOfList']/li"))) {//判断是否有稿件列表
                List<WebElement> articles = driver.findElements(By.xpath("//ul[@id='ulOfList']/li"));//获取稿件列表
                articles.get(0).click();//点击第一稿件
                Thread.sleep(500);
                driver.findElement(By.id("btn-select")).click();//点击选择
                Thread.sleep(500);
                driver.findElement(By.xpath("//form[@id='formFw']/div[6]/div/input")).sendKeys("-autoTest");//标题增加“-autoTest”
                Thread.sleep(200);
                driver.findElement(By.xpath("//div[@class='float-img float-img9-16']/a[1]")).click();//点击选择封面图
                CommonMethod.getImg(driver);//在线资源库增加封面图
                Thread.sleep(1000);
                driver.findElement(By.xpath("//form[@id='formFw']/div[last()]/div/div[1]/input")).click();//点击开始时间
                Thread.sleep(200);
                driver.findElement(By.cssSelector("i.layui-icon.laydate-icon.laydate-next-m")).click();//点击下个月icon
                Thread.sleep(100);
                driver.findElement(By.className("laydate-btns-confirm")).click();//点击日历控件的确定
                Thread.sleep(200);
                driver.findElement(By.xpath("//form[@id='formFw']/div[last()]/div/div[2]/input")).click();//点击结束时间
                Thread.sleep(200);
                driver.findElement(By.cssSelector("i.layui-icon.laydate-icon.laydate-next-m")).click();//点击下个月icon
                Thread.sleep(200);
                driver.findElement(By.cssSelector("i.layui-icon.laydate-icon.laydate-next-m")).click();//再次点击下个月icon，结束时间需要晚于开始时间
                Thread.sleep(100);
                driver.findElement(By.className("laydate-btns-confirm")).click();//点击日历控件的确定
                Thread.sleep(500);
                driver.findElement(By.className("layui-layer-btn0")).click();//点击确定新增
                Thread.sleep(1000);
                System.out.println("~~~ addPopNews()，新建首屏浮窗，执行成功 ~~~");
            } else System.out.println("测试test频道下没有稿件");
        } else System.out.println("没找到测试频道");
        Thread.sleep(2000);
        if (CommonMethod.isJudgingElement(driver, By.xpath("//div[@class='layui-layer layui-layer-page']/span/a"))) {//校验首页浮窗图层是否还是打开的
//            driver.findElement(By.cssSelector("i.cbtn.bullet-btn-no.btn-no.close")).click();//关闭首页浮窗图层
            driver.findElement(By.xpath("//div[@class='layui-layer layui-layer-page']/span/a")).click();
            Thread.sleep(500);
            driver.findElement(By.className("layui-layer-btn1")).click();
        }
        Thread.sleep(3000);
    }

    //修改
    public static void editPopNews() throws InterruptedException {

        searchPopNews();//搜索autoTest数据
        if (!CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='xlist']/li"))) {//没找到autoTest数据
            addPopNews();//新增autoTest数据
            searchPopNews();//再次搜索autotest数据
        }
        if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='xlist']/li"))) {//没找到autoTest数据
            List<WebElement> news = driver.findElements(By.xpath("//ul[@id='xlist']/li"));//获取首页浮窗数据list
            Actions action = new Actions(driver);
            action.moveToElement(news.get(0)).perform();//鼠标悬浮第一条数据
            Thread.sleep(100);
            news.get(0).findElement(By.xpath("./div/div[2]/div[2]/a[1]")).click();//点击第一条数据的修改
            Thread.sleep(500);
            driver.findElement(By.xpath("//form[@id='formFw']/div[6]/div/input")).sendKeys("-update" + System.currentTimeMillis());//修改名称
            Thread.sleep(200);
            driver.findElement(By.className("layui-layer-btn0")).click();//点击确定修改
            System.out.println("~~~ editPopNews()，编辑首屏浮窗，执行成功 ~~~");
            Thread.sleep(2000);
            if (CommonMethod.isJudgingElement(driver, By.xpath("//div[@class='layui-layer layui-layer-page']/span/a"))) {//校验首页浮窗图层是否还是打开的
//            driver.findElement(By.cssSelector("i.cbtn.bullet-btn-no.btn-no.close")).click();//关闭首页浮窗图层
                driver.findElement(By.xpath("//div[@class='layui-layer layui-layer-page']/span/a")).click();
                Thread.sleep(500);
                driver.findElement(By.className("layui-layer-btn1")).click();
            }
        } else System.out.println("没有可编辑的浮窗数据");
        Thread.sleep(3000);
    }

    //删除
    public static void deletePopNews() throws InterruptedException {

        searchPopNews();//搜索autoTest数据
        if (!CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='xlist']/li"))) {//没找到autoTest数据
            addPopNews();//新增autoTest数据
            searchPopNews();//再次搜索autotest数据
        }
        if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='xlist']/li"))) {//没找到autoTest数据
            List<WebElement> news = driver.findElements(By.xpath("//ul[@id='xlist']/li"));//获取首页浮窗数据list
            Actions action = new Actions(driver);
            action.moveToElement(news.get(0)).perform();//鼠标悬浮第一条数据
            Thread.sleep(100);
            news.get(0).findElement(By.xpath("./div/div[2]/div[2]/a[2]")).click();//点击第一条数据的删除
            Thread.sleep(200);
            driver.findElement(By.className("layui-layer-btn0")).click();//确定删除
            System.out.println("~~~ deletePopNews()，删除首屏浮窗，执行成功 ~~~");
            Thread.sleep(2000);
            if (CommonMethod.isJudgingElement(driver, By.xpath("//div[@class='layui-layer layui-layer-page']/span/a"))) {//校验首页浮窗图层是否还是打开的
//            driver.findElement(By.cssSelector("i.cbtn.bullet-btn-no.btn-no.close")).click();//关闭首页浮窗图层
                driver.findElement(By.xpath("//div[@class='layui-layer layui-layer-page']/span/a")).click();
                Thread.sleep(500);
                driver.findElement(By.className("layui-layer-btn1")).click();
            }
        } else System.out.println("没有可删除的测试浮窗数据");
        Thread.sleep(3000);
    }

    //开启关闭首屏浮窗
    public static void turnOnOrOff() throws InterruptedException {

        searchPopNews();//搜索autoTest数据
        if (!CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='xlist']/li"))) {//没找到autoTest数据
            addPopNews();//新增autoTest数据
            searchPopNews();//再次搜索autotest数据
        }
        if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='xlist']/li"))) {//没找到autoTest数据
            List<WebElement> news = driver.findElements(By.xpath("//ul[@id='xlist']/li"));//获取首页浮窗数据list
            Actions action = new Actions(driver);
            action.moveToElement(news.get(0)).perform();//鼠标悬浮第一条数据
            boolean opened = false;
            Thread.sleep(100);
            if (news.get(0).findElement(By.xpath("./div/div[2]/div[2]/div")).getAttribute("class").contains("onswitch"))//校验是否是已开启的
                opened = true;//已开启打标
            news.get(0).findElement(By.xpath("./div/div[2]/div[2]/div")).click();//点击第一条数据的开启关闭
            if (opened) System.out.println("~~~ turnOnOrOff()，关闭首屏浮窗，执行成功 ~~~");//如果原为已开启则操作后关闭
            else System.out.println("~~~ turnOnOrOff()，开启首屏浮窗，执行成功 ~~~");//如果原为未开启则操作后开启
            Thread.sleep(2000);
            if (CommonMethod.isJudgingElement(driver, By.xpath("//div[@class='layui-layer layui-layer-page']/span/a"))) {//校验首页浮窗图层是否还是打开的
//            driver.findElement(By.cssSelector("i.cbtn.bullet-btn-no.btn-no.close")).click();//关闭首页浮窗图层
                driver.findElement(By.xpath("//div[@class='layui-layer layui-layer-page']/span/a")).click();
                Thread.sleep(500);
                driver.findElement(By.className("layui-layer-btn1")).click();
            }
        } else System.out.println("没有可开启或关闭的测试浮窗数据");
        Thread.sleep(3000);
    }

    //搜索
    private static void searchPopNews() throws InterruptedException {
        if (CommonMethod.isJudgingElement(driver, By.cssSelector("div.send-channel.bullet-news-list.layui-form.send-channel-close")))
            driver.findElement(By.id("ejectNews")).click();//点击首屏浮窗
        Thread.sleep(2000);

        driver.findElement(By.xpath("//form[@id='formNewsList']/div[@class='layui-unselect layui-form-select']/div")).click();//点击样式类型筛选框
        Thread.sleep(100);
        driver.findElement(By.xpath("//div[@class='layui-unselect layui-form-select layui-form-selected']/dl/dd[2]")).click();//点击全部
        Thread.sleep(100);
        driver.findElement(By.xpath("//div[@class='fr hxj-search']/input")).clear();//清空关键词输入框
        driver.findElement(By.xpath("//div[@class='fr hxj-search']/input")).sendKeys("autoTest");//录入搜索关键词
        Thread.sleep(100);
        driver.findElement(By.cssSelector("i.fa.fa-search.hxj-search-btn")).click();//点击搜索
        Thread.sleep(2000);
    }

    //初始化登录
    static {
        try {
            driver = login();//调起浏览器，进行portal账号登录
            for (int i = 0; i < 3; i++) {//登录异常可以重试三次
                if (!CommonMethod.isJudgingElement(driver, By.tagName("header"))) {//校验是否还未打开功能页面，如新闻管理页面
                    if (CommonMethod.isJudgingElement(driver, By.className("loginBtn")))
                        driver = login();//校验当前页面是否还有登录框，如还未登录成功则再次登录
                    driver.get(domain + "/content/content/list/init");//直接跳转到新闻管理
                    Thread.sleep(3000);
                    if (!CommonMethod.isJudgingElement(driver, By.className("fold-pack"))) {//如果未跳转到应用页面（新闻管理）
                        CommonMethod.jumpModule(driver, "新闻管理");//通过点击新闻管理，打开切换到新标签页方式访问
                        Thread.sleep(2000);
                    }
                } else break;
            }
            Thread.sleep(2000);
            //校验是否是目标租户站点应用（主要针对一个租户多个站点应用的情况）
            if (!driver.findElement(By.xpath("//div[@class='nav-right']/ul/li/a")).getText().contains(siteName)) {//校验当前机构应用非期望机构应用
                Actions action = new Actions(driver);
                action.moveToElement(driver.findElement(By.className("nav-right"))).perform();//光标悬浮机构应用显示出机构应用列表
                Thread.sleep(500);
                driver.findElement(By.linkText(siteName)).click();//找到目标机构应用并点击切换
                Thread.sleep(3000);
            }

            if (CommonMethod.isJudgingElement(driver, By.cssSelector("div.send-channel.bullet-news-list.layui-form.send-channel-close")))
                driver.findElement(By.id("ejectNews")).click();//点击首屏浮窗
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
