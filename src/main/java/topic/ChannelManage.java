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
 * @date 2022/3/3 17:24
 */
public class ChannelManage extends LoginPortal {

    static WebDriver driver;

    //频道管理-新增栏目
    public static void addChannel(int type) throws InterruptedException {

        int listSize = 0;
        String desc;
        switch (type) {//添加频道类型
            case 1:
                desc = "普通样式-手动添加稿件";
                break;
            case 2:
                desc = "普通样式-稿件来源于频道";
                break;
            case 3:
                desc = "竖视频样式-手动添加稿件";
                break;
            case 4:
                desc = "竖视频样式-稿件来源于频道";
                break;
            default:
                desc = "默认，普通样式-手动添加稿件";
        }
        if (openChannelManage()) {//打开频道管理页面
            for (int j = 1; j < 20; j++) {
                if (CommonMethod.isJudgingElement(driver, By.id("layui-layer-iframe" + j))) {//获取iframe有效id
                    driver.switchTo().frame("layui-layer-iframe" + j);//切换到iframe
                    break;
                }
            }
            Thread.sleep(500);

            if (getList() != null) listSize = getList().size();//获取当前栏目数量
            Thread.sleep(500);
            driver.findElement(By.id("add")).click();//点击新建专题栏目
            Thread.sleep(2000);
            driver.findElement(By.xpath("//form[@id='addGroup']/div[2]/div/input[2]")).sendKeys("autoTest-栏目" + (listSize + 1));//录入标题
            if (!CommonMethod.isJudgingElement(driver, By.xpath("//form[@id='addGroup']/div[3]/div/textarea"))) {
                if (type == 2) {//普通样式，来源于频道
//                if (CommonMethod.isJudgingElement(driver, By.xpath("//form[@id='addGroup']/div[4]/div/input"))) {
                    driver.findElement(By.xpath("//form[@id='addGroup']/div[4]/div/input[1]")).click();//选择来源于频道
                    Thread.sleep(200);
                    driver.findElement(By.className("source-list-hint")).click();//点击关联专题频道
                    Thread.sleep(1500);
                    CommonMethod.getTestTree(driver, "undefined_1_ul", "频道测试");//选择“频道测试”进行关联
                    driver.findElement(By.cssSelector("button.layui-btn.channel-confirm")).click();//点击确定
//                }
                }
                if (type == 3) //竖视频样式，手动添加稿件
//                if (CommonMethod.isJudgingElement(driver, By.xpath("//form[@id='addGroup']/div[3]/div/input")))
                    driver.findElement(By.xpath("//form[@id='addGroup']/div[3]/div/input[2]")).click();//选择竖视频样式
//            }
                if (type == 4) {//竖视频样式，来源于频道
//                if (CommonMethod.isJudgingElement(driver, By.xpath("//form[@id='addGroup']/div[3]/div/input")) && CommonMethod.isJudgingElement(driver, By.xpath("//form[@id='addGroup']/div[4]/div/input"))) {
                    driver.findElement(By.xpath("//form[@id='addGroup']/div[3]/div/input[2]")).click();//选择竖视频样式
                    driver.findElement(By.xpath("//form[@id='addGroup']/div[4]/div/input[1]")).click();//选择来源于频道
                    Thread.sleep(200);
                    driver.findElement(By.className("source-list-hint")).click();//点击关联专题频道
                    Thread.sleep(1500);
                    CommonMethod.getTestTree(driver, "undefined_1_ul", "竖视频");//选择“竖视频”进行关联
                    driver.findElement(By.cssSelector("button.layui-btn.channel-confirm")).click();//点击确定
//                }
                }

                Thread.sleep(1000);
//            if (CommonMethod.isJudgingElement(driver, By.xpath("//form[@id='addGroup']/div[6]/div/textarea")))
                driver.findElement(By.xpath("//form[@id='addGroup']/div[6]/div/textarea")).sendKeys("autoTest-" + desc);//录入简介
            } else {
                desc = "手动子栏目描述" + System.currentTimeMillis();
                driver.findElement(By.xpath("//form[@id='addGroup']/div[3]/div/textarea")).sendKeys("autoTest-" + desc);//录入简介
            }
            Thread.sleep(2000);
            driver.findElement(By.className("layui-layer-btn0")).click();//点击确定
            for (int i = 0; i < 3; i++) {
                Thread.sleep(3000);
                if (CommonMethod.isJudgingElement(driver, By.className("page-back-btn")))//校验是否有“返回”
                    driver.findElement(By.className("page-back-btn")).click();//点击返回
            }
            Thread.sleep(1000);
            driver.switchTo().defaultContent();
            System.out.println("~~~ addChannel()，新建专题栏目(" + desc + ")，执行成功 ~~~");
        } else System.out.println("无有子栏目的测试专题");
        Thread.sleep(3000);
    }

    //频道内添加稿件
    public static void addArticle() throws InterruptedException {
        if (openChannelManage()) {//打开频道管理
            for (int j = 1; j < 20; j++) {
                if (CommonMethod.isJudgingElement(driver, By.id("layui-layer-iframe" + j))) {//获取iframe有效id
                    driver.switchTo().frame("layui-layer-iframe" + j);//切换到子栏目列表iframe
                    break;
                }
            }
            Thread.sleep(1000);
            if (getList() == null) addChannel(driver);//如果没有子栏目则先添加子栏目
            for (int i = 0; i < getList().size(); i++) {
                if (getList().get(i).findElement(By.xpath("td[3]")).getText().contains("手动")) {
                    getList().get(i).findElement(By.xpath("td[5]/a[1]")).click();//点击稿件管理
                    Thread.sleep(1000);
                    for (int k = 100001; k < 100020; k++) {
                        if (CommonMethod.isJudgingElement(driver, By.id("layui-layer-iframe" + k))) {//获取iframe有效id
                            driver.switchTo().frame("layui-layer-iframe" + k);//切换到稿件列表iframe
                            break;
                        }
                    }
                    Thread.sleep(500);
                    driver.findElement(By.id("addContent")).click();//点击添加稿件
                    Thread.sleep(1500);
                    for (int n = 0; n < 3; n++) {
                        if (!CommonMethod.isJudgingElement(driver, By.xpath("//table[@id='cotnentList']/tbody/tr")))//校验是否有数据返回，重复校验等待3次
                            Thread.sleep(1500);
                        else break;
                    }
                    List<WebElement> trs = driver.findElements(By.xpath("//table[@id='cotnentList']/tbody/tr"));//稿件数据list
                    int num = trs.size();
                    if (num > 3) num = 3;
                    for (int r = 0; r < num; r++) {
                        trs.get(r).findElement(By.xpath("./td[1]/input")).click();//选择最新的3条
                    }
                    Thread.sleep(500);
                    driver.findElement(By.id("saveRel")).click();//点击选为专题新闻
                    Thread.sleep(2000);
                    driver.findElement(By.cssSelector("a.layui-layer-ico.layui-layer-close.layui-layer-close1")).click();//点击关闭当前界面
                    Thread.sleep(1000);
                    driver.findElement(By.className("page-back-btn")).click();//点击返回，返回到子栏目列表页
                    driver.switchTo().parentFrame();//返回到上一级iframe，子栏目列表
                    System.out.println("~~~ addArticle()，添加稿件，执行成功 ~~~");
                    Thread.sleep(1000);
                    break;
                }
            }
            Thread.sleep(1000);
            driver.findElement(By.className("page-back-btn")).click();//返回到专题列表页
            driver.switchTo().defaultContent();//退出iframe，返回到默认界面
        } else System.out.println("无有子栏目的测试专题");
        Thread.sleep(3000);
    }

    //频道内删除稿件
    public static void delArticle() throws InterruptedException {
        Boolean del = false;//是否已删除
        if (openChannelManage()) {//打开频道管理
            for (int j = 1; j < 20; j++) {
                if (CommonMethod.isJudgingElement(driver, By.id("layui-layer-iframe" + j))) {//获取iframe有效id
                    driver.switchTo().frame("layui-layer-iframe" + j);//切换到子栏目列表iframe
                    break;
                }
            }
            Thread.sleep(1000);
            for (int i = 0; i < getList().size(); i++) {
                if (getList().get(i).findElement(By.xpath("td[3]")).getText().contains("手动")) {//校验是否是手动添加稿件
                    getList().get(i).findElement(By.xpath("td[5]/a[1]")).click();//打开稿件列表
                    Thread.sleep(1000);
                    for (int k = 100001; k < 100020; k++) {
                        if (CommonMethod.isJudgingElement(driver, By.id("layui-layer-iframe" + k))) {//获取iframe有效id
                            driver.switchTo().frame("layui-layer-iframe" + k);//切换到稿件管理iframe
                            break;
                        }
                    }
                    Thread.sleep(1000);
                    if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@id='infoTabel']/tbody/tr"))) {//校验是否有稿件数据
                        List<WebElement> trs = driver.findElements(By.xpath("//table[@id='infoTabel']/tbody/tr"));//稿件list
                        for (int r = trs.size(); r > 0; r--) {
                            driver.findElement(By.xpath("//table[@id='infoTabel']/tbody/tr[" + r + "]/td[9]/div/a[3]")).click();//点击删除
                            Thread.sleep(500);
                            driver.findElement(By.className("layui-layer-btn0")).click();//确定删除
                            Thread.sleep(2000);
                            del = true;
                        }
                    }
                    driver.findElement(By.className("page-back-btn")).click();//返回到栏目列表
                    driver.switchTo().parentFrame();//返回到栏目iframe
                    Thread.sleep(1000);
                }
                if (del) {
                    System.out.println("~~~ delArticle()，删除稿件，执行成功 ~~~");
                    break;
                }
            }
            driver.findElement(By.className("page-back-btn")).click();//返回到专题列表页
            driver.switchTo().defaultContent();//退出iframe，返回到默认页面
            if (!del) System.out.println("测试专题没有稿件可删除");
        } else System.out.println("无有子栏目的测试专题");
        Thread.sleep(3000);
    }

    //频道管理-删除栏目
    public static void delChannel() throws InterruptedException {
        Boolean del = false;
        String str = null;
        if (openChannelManage()) {//打开频道管理
            for (int j = 1; j < 20; j++) {
                if (CommonMethod.isJudgingElement(driver, By.id("layui-layer-iframe" + j))) {//获取iframe有效id
                    driver.switchTo().frame("layui-layer-iframe" + j);//切换到iframe
                    break;
                }
            }
            Thread.sleep(1000);
            if (getList() == null) addChannel(driver);
            for (int i = getList().size() - 1; i > -1; i--) {//倒序删除
                getList().get(i).findElement(By.xpath("td[5]/a[3]")).click();//点击删除
                Thread.sleep(200);
                driver.findElement(By.className("layui-layer-btn0")).click();//确定删除
                Thread.sleep(1000);
                del = true;
            }
            if (getList() == null) str = "栏目已全部删除";//全部已删除
            else str = "部分未删除，可能因为栏目中有稿件";//部分未删除
        }
        if (del) System.out.println("~~~ delChannel()，删除子栏目，执行成功。" + str + " ~~~");
        else System.out.println("无有子栏目的测试专题");
        Thread.sleep(3000);
    }

    //获取栏目列表
    public static List<WebElement> getList() {
        List<WebElement> list = null;
        if (CommonMethod.isJudgingElement(driver, By.xpath("//table[@id='infoTabel']/tbody/tr"))) {
            list = driver.findElements(By.xpath("//table[@id='infoTabel']/tbody/tr"));
        }
        return list;
    }

    //打开频道管理
    public static Boolean openChannelManage() throws InterruptedException {
        Search.searchAutoTest(driver);
        Boolean opened = false;
        if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@class='topic-contanier clearfix']/li"))) {
            List<WebElement> topics = driver.findElements(By.xpath("//ul[@class='topic-contanier clearfix']/li"));//获取auto数据list
            for (int i = 0; i < topics.size(); i++) {
                if (CommonMethod.isJudgingElement(topics.get(i), By.xpath("div[@class='operate-area']/a[@class='channelManage ']"))) {//校验是否有“频道管理”链接
                    topics.get(i).findElement(By.xpath("div[@class='operate-area']/a[@class='channelManage ']")).click();
                    Thread.sleep(500);
                    opened = true;
                    break;
                }
            }
        }
        return opened;
    }

    //添加手动栏目
    private static void addChannel(WebDriver driver) throws InterruptedException {
        driver.findElement(By.className("page-back-btn")).click();//返回到专题列表
        driver.switchTo().defaultContent();
        Thread.sleep(1000);
        addChannel(1);//新建手动添加稿件的栏目
        openChannelManage();
        for (int i = 0; i < 20; i++) {
            if (CommonMethod.isJudgingElement(driver, By.id("layui-layer-iframe" + i))) {
                driver.switchTo().frame("layui-layer-iframe" + i);//切换到iframe
                break;
            }
        }
        Thread.sleep(1000);
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
