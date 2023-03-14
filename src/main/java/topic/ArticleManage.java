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
 * @date 2022/3/3 17:23
 */
public class ArticleManage extends LoginPortal {

    static WebDriver driver;

    //添加稿件
    public static void addArticles() throws InterruptedException {
        Search.searchAutoTest(driver);//搜索auto自动化测试专题
        if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@class='topic-contanier clearfix']/li"))) {//校验是否有数据
            List<WebElement> topics = driver.findElements(By.xpath("//ul[@class='topic-contanier clearfix']/li"));//获取auto数据list
            for (int i = 1; i < topics.size() + 1; i++) {
                if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@class='topic-contanier clearfix']/li[" + i + "]/div[@class='operate-area']/a[@class='manusManage ']"))) {//校验是否有“稿件管理”链接
                    driver.findElement(By.xpath("//ul[@class='topic-contanier clearfix']/li[" + i + "]/div[@class='operate-area']/a[@class='manusManage ']")).click();//点击稿件管理
                    Thread.sleep(1500);
                    for (int j = 1; j < 20; j++)
                        if (CommonMethod.isJudgingElement(driver, By.id("layui-layer-iframe" + j))) {//获取iframe有效id
                            driver.switchTo().frame("layui-layer-iframe" + j);//切换到iframe
                            break;
                        }
                    Thread.sleep(2000);
                    if (!driver.findElement(By.id("addContent")).getAttribute("style").contains("display")) {//添加按钮未隐藏
                        driver.findElement(By.id("addContent")).click();//点击添加专题新闻
                        for (int t = 0; t < 5; t++) {
                            Thread.sleep(2000);
                            if (CommonMethod.isJudgingElement(driver, By.xpath("//div[@id='relateZTree']/li"))) break;
                        }
                        //选择选择稿件的频道
                        CommonMethod.getTestTree(driver, "relateZTree_1_ul", "频道测试");//选择新闻频道
                        List<WebElement> articles = driver.findElements(By.xpath("//table[@id='cotnentList']/tbody/tr"));//获取该频道下的新闻稿件
                        int num = 5;
                        if (articles.size() < 5) num = articles.size();
                        for (int a = 0; a < num; a++) {
                            articles.get(a).findElement(By.xpath("td[1]/input")).click();//添加最新的5条数据
                        }
                        Thread.sleep(1000);
                        driver.findElement(By.id("saveRel")).click();//确定保存稿件
                        Thread.sleep(2000);
                        driver.findElement(By.xpath("//span[@class='layui-layer-setwin']/a")).click();//关闭添加新闻稿件图层
                        Thread.sleep(1000);
                        System.out.println("~~~ addArtcles()，添加稿件，执行成功 ~~~");
                        break;
                    } else {
                        driver.findElement(By.className("page-back-btn")).click();//返回到专题列表页
                        Thread.sleep(500);
                        driver.switchTo().defaultContent();
                        Thread.sleep(1000);
                    }
                }
            }
        } else System.out.println("无auto测试数据");
        if (CommonMethod.isJudgingElement(driver, By.className("page-back-btn"))) {//是否有返回按钮
            driver.findElement(By.className("page-back-btn")).click();//点击返回
            driver.switchTo().defaultContent();//退出iframe，返回到默认页
        }
        Thread.sleep(3000);
    }

    //删除稿件2
    public static void delArticles() throws InterruptedException {
        Boolean del = false;
        getActicle(true, 1);//获取可删除的数据（isconfined：是否要限制手动或来源语频道；type=1（非2的值）：手动添加稿件的）
        if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@id='infoTabel']/tbody/tr"))) {//校验是否有稿件数据
            List<WebElement> listTR = driver.findElements(By.xpath("//table[@id='infoTabel']/tbody/tr"));//获取稿件数据list
            int num = listTR.size();
            for (int r = 0; r < num; r++) {//循环删除全部
                driver.findElement(By.xpath("//table[@id='infoTabel']/tbody/tr[1]/td[9]/div/a[3]")).click();//点击稿件删除
                Thread.sleep(200);
                driver.findElement(By.className("layui-layer-btn0")).click();//确定删除
                del = true;
                Thread.sleep(2000);
            }
            System.out.println("~~~ delArticles()，删除专题稿件，执行成功。共删除稿件 " + num + " 篇 ~~~");
        }
        if (!del) System.out.println("没有可删除的稿件数据");
        driver.findElement(By.className("page-back-btn")).click();//返回到专题列表页
        driver.switchTo().defaultContent();//退出iframe，返回到默认界面
        Thread.sleep(3000);
    }

    //上轮播
    public static void onSwitch() throws InterruptedException {
        onSwitchOrCover(1);//type=1，onSwitch
    }

    //上封面
    public static void onCover() throws InterruptedException {
        onSwitchOrCover(2);//type=2，onCover
    }

    //设置轮播图
    public static void setSwitch() throws InterruptedException {
        setSwitchOrCover(1);//type=1，setSwitch
    }

    //设置轮播图
    public static void setCover() throws InterruptedException {
        setSwitchOrCover(2);//type=2，set Cover
    }

    //上轮播或上封面
    private static void onSwitchOrCover(int type) throws InterruptedException {//传2则上下轮播；传非2则上下封面
        int tdNum = 6;//默认上轮播td=6
        if (type == 2) tdNum = 7;
        getActicle(false, 0);
        if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@id='infoTabel']/tbody/tr"))) {//校验是否有数据
            List<WebElement> listTr = driver.findElements(By.xpath("//table[@id='infoTabel']/tbody/tr"));//获取稿件数据list
            int num = 3;
            if (listTr.size() < 3) num = listTr.size();//取前三个
            for (int i = 1; i < num + 1; i++) {
                driver.findElement(By.xpath("//table[@id='infoTabel']/tbody/tr[" + i + "]/td[" + tdNum + "]/div")).click();//点击上轮播/封面
                Thread.sleep(500);
                driver.findElement(By.className("layui-layer-btn0")).click();//点击确定
                Thread.sleep(2000);
            }
            if (tdNum == 7)
                System.out.println("~~~ onCover，上下封面，执行成功 ~~~");
            else
                System.out.println("~~~ onSwitch()，上/下轮播，执行成功 ~~~");
        } else System.out.println("无自动化测试数据");
        Thread.sleep(2000);
        driver.findElement(By.className("page-back-btn")).click();//返回到专题列表页
        driver.switchTo().defaultContent();//退出iframe，返回到默认界面
        Thread.sleep(3000);
    }

    //设置轮播图片、封面图片
    private static void setSwitchOrCover(int type) throws InterruptedException {
        int tabNum = 2;//默认设置轮播tab=2
        if (type == 2) tabNum = 3;
        getActicle(false, 0);//获取专题稿件列表
        driver.findElement(By.xpath("//ul[@class='layui-tab-title']/li[" + tabNum + "]")).click();//点击切换到设置轮播/设置封面tab
        Thread.sleep(2000);
        if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@id='carouselTable']/tbody/tr"))) {//校验是否有已上轮播/上封面的数据
            List<WebElement> listTr = driver.findElements(By.xpath("//table[@id='carouselTable']/tbody/tr"));//轮播/封面数据list
            for (int i = 1; i < listTr.size() + 1; i++) {
                driver.findElement(By.xpath("//table[@id='carouselTable']/tbody/tr[" + i + "]/td[2]/div/div/a[1]")).click();//点击上传图片
                Thread.sleep(1000);
                CommonMethod.getImg(driver);//在线资源库获取图片
                Thread.sleep(2000);
            }
            if (tabNum == 3) System.out.println("~~~ setCover，设置封面图，执行成功 ~~~");
            else System.out.println("~~~ setSwitch，设置轮播图，执行成功 ~~~");
        } else System.out.println("无数据可设置轮播图或封面图");
        driver.findElement(By.className("page-back-btn")).click();//返回到专题列表页
        driver.switchTo().defaultContent();//退出iframe，返回到默认界面
        Thread.sleep(3000);
    }

    //获取稿件数据，isConfined是否要限制手动或来源于频道。origin=1，来源于手动频道；origin=2，来源于频道；默认手动。type=1，普通；type=2，音频；type=3要闻
    //添加、删除稿件需要限制为手动添加稿，上轮播、封面图，不需要限制为手动添加稿件
    private static void getActicle(Boolean isConfined, int origin) throws InterruptedException {
        Search.searchAutoTest(driver);//搜索auto自动化测试专题
        if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@class='topic-contanier clearfix']/li"))) {//校验是否有数据
            List<WebElement> topics = driver.findElements(By.xpath("//ul[@class='topic-contanier clearfix']/li"));//获取auto自动化专题list
            for (int i = 1; i < topics.size() + 1; i++) {//循环找可稿件的专题
                if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@class='topic-contanier clearfix']/li[" + i + "]/div[@class='operate-area']/a[@class='manusManage ']")) && !driver.findElement(By.xpath("//ul[@class='topic-contanier clearfix']/li[" + i + "]/div[2]/p[1]")).getText().contains("要闻类型")) {//校验是否有稿件管理
                    driver.findElement(By.xpath("//ul[@class='topic-contanier clearfix']/li[" + i + "]/div[@class='operate-area']/a[@class='manusManage ']")).click();//点击稿件管理
                    Thread.sleep(1500);
                    for (int j = 1; j < 20; j++) {
                        if (CommonMethod.isJudgingElement(driver, By.id("layui-layer-iframe" + j))) {//获取iframe有效id
                            driver.switchTo().frame("layui-layer-iframe" + j);//切换到iframe
                            break;
                        }
                    }
                    Thread.sleep(500);
                    if (isConfined && origin != 2)
                        if (driver.findElement(By.id("addContent")).getAttribute("style").contains("display") || !CommonMethod.isJudgingElement(driver, By.xpath("//table[@id='infoTabel']/tbody/tr"))) {//是否不隐藏添加稿件按钮，不隐藏则为手动添加稿件的专题
                            driver.findElement(By.className("page-back-btn")).click();//返回到专题列表页
                            driver.switchTo().defaultContent();//退出iframe，返回到默认界面
                            Thread.sleep(500);
                        } else break;
                    else if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@id='infoTabel']/tbody/tr")))
                        break;
                }
                Thread.sleep(500);
            }
        }
    }

    //初始化登录
    static {
        try {
            driver = login();
            for (int i = 0; i < 3; i++) {
                if (!CommonMethod.isJudgingElement(driver, By.tagName("header"))) {//校验是否是要登录
                    if (CommonMethod.isJudgingElement(driver, By.className("loginBtn"))) driver = login();//登录
                    driver.get(domain + "/content/content/list/init");//跳转到新闻管理
                    Thread.sleep(3000);
                    if (!CommonMethod.isJudgingElement(driver, By.className("fold-pack"))) {
                        CommonMethod.jumpModule(driver, "新闻管理");
                        Thread.sleep(2000);
                    }
                } else break;
            }
            if (!driver.findElement(By.xpath("//div[@class='nav-right']/ul/li/a")).getText().contains(siteName)) {//校验是否是爱富县站点
                Actions action = new Actions(driver);
                action.moveToElement(driver.findElement(By.className("nav-right"))).perform();//光标悬浮
                Thread.sleep(500);
                driver.findElement(By.linkText(siteName)).click();//切换到爱富县站点
                Thread.sleep(2000);
            } else {
                driver.navigate().refresh();//刷新页面
                Thread.sleep(2000);
            }
            CommonMethod.changeMenu(driver, "专题");//切换到专题tab
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
