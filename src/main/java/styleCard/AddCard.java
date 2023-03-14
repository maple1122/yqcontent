package styleCard;

import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * @author wufeng
 * @date 2022/3/2 15:58
 */
public class AddCard extends LoginPortal {
    static WebDriver driver;

    //默认创建普通样式快讯内容样式卡
    public static void addFlash() throws InterruptedException {
        addFlash(1);
    }

    //创建快讯内容样式卡
    public static void addFlash(int type) throws InterruptedException {
        driver.findElement(By.id("addCard")).click();//点击添加内容样式卡
        Thread.sleep(500);
        driver.findElement(By.xpath("//div[@id='contentBtn-list']/span[1]")).click();//添加快讯
        Thread.sleep(500);
        driver.findElement(By.xpath("//form[@data-form='form1']/div[1]/div/input")).sendKeys("autoTest-快讯内容样式卡-" + System.currentTimeMillis());//录入快讯内容样式卡标题
        if (type == 2) driver.findElement(By.xpath("//form[@data-form='form1']/div[2]/div/div[2]")).click();//参数为卡片样式
        driver.findElement(By.xpath("//form[@data-form='form1']/div[3]/div/div[1]")).click();//点击上传icon
        Thread.sleep(200);
        CommonMethod.getImg(driver);//资源库上传图片
        Thread.sleep(1000);
        driver.findElement(By.xpath("//form[@data-form='form1']/div[4]/div/input")).click();//点击内容来源
        //选择关联频道
        List<WebElement> list1, list2;
        Boolean selected = false;
        for (int i = 0; i < 5; i++) {
            Thread.sleep(2000);
            if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='channelSelect_1_ul']/li"))) break;
        }
        list1 = driver.findElements(By.xpath("//ul[@id='channelSelect_1_ul']/li[1]"));//第一层
        for (int i1 = 0; i1 < list1.size(); i1++) {
            list2 = list1.get(i1).findElements(By.xpath("ul/li"));//第二层
            for (int i2 = 0; i2 < list2.size(); i2++) {
                if (list2.get(i2).findElement(By.tagName("a")).getAttribute("title").equals("测试频道")) {
                    list2.get(i2).findElement(By.xpath("span[2]")).click();
                    selected = true;
                    break;
                }
            }
            if (selected) break;
        }
        Thread.sleep(200);
        driver.findElement(By.xpath("//div[@class='content-source modal-win']/div/button[1]")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//form[@data-form='form1']/div[5]/div/textarea")).sendKeys("autoTest-快讯-内容简介~");
        Thread.sleep(500);
        driver.findElement(By.className("layui-layer-btn0")).click();
        System.out.println("~~~ addFlash()，创建快讯内容样式卡，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //创建横视频内容样式卡
    public static void addVideoH() throws InterruptedException {
        driver.findElement(By.id("addCard")).click();//点击添加内容样式卡
        Thread.sleep(500);
        driver.findElement(By.xpath("//div[@id='contentBtn-list']/span[2]")).click();//添加横视频
        Thread.sleep(500);
        driver.findElement(By.xpath("//form[@data-form='form2']/div[1]/div/input")).sendKeys("autoTest-横视频内容样式卡-" + System.currentTimeMillis());//录入横视频内容样式卡标题
        Thread.sleep(500);
        driver.findElement(By.xpath("//form[@data-form='form2']/div[3]/div/input")).click();//点击内容来源
        //选择关联频道
        List<WebElement> list, list1, list2;
        Boolean selected = false;
        for (int i = 0; i < 5; i++) {
            Thread.sleep(2000);
            if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='channelSelect_1_ul']/li"))) break;
        }
        list = driver.findElements(By.xpath("//ul[@id='channelSelect_1_ul']/li"));//第一层
        for (int i = 0; i < list.size(); i++) {
            list1 = driver.findElements(By.xpath("//ul[@id='channelSelect_1_ul']/li[" + (i + 1) + "]"));
            for (int i1 = 0; i1 < list1.size(); i1++) {
                list2 = list1.get(i1).findElements(By.xpath("ul/li"));//第二层
                for (int i2 = 0; i2 < list2.size(); i2++) {
                    if (!list2.get(i2).findElement(By.xpath("./span[2]")).getAttribute("class").contains("disable")) {
                        list2.get(i2).findElement(By.xpath("span[2]")).click();
                        selected = true;
                        break;
                    }
                }
                if (selected) break;
            }
            if (selected) break;
        }
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='content-source modal-win']/div/button[1]")).click();
        Thread.sleep(500);
        driver.findElement(By.className("layui-layer-btn0")).click();
        System.out.println("~~~ addVideoH()，创建横视频内容样式卡，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //创建竖视频内容样式卡
    public static void addVideoS() throws InterruptedException {
        driver.findElement(By.id("addCard")).click();//点击添加内容样式卡
        Thread.sleep(500);
        driver.findElement(By.xpath("//div[@id='contentBtn-list']/span[3]")).click();//添加竖视频
        Thread.sleep(500);
        driver.findElement(By.xpath("//form[@data-form='form3']/div[1]/div/input")).sendKeys("autoTest-竖视频内容样式卡-" + System.currentTimeMillis());//录入竖视频内容样式卡标题
        Thread.sleep(500);
        driver.findElement(By.xpath("//form[@data-form='form3']/div[3]/div/input")).click();//点击内容来源
        //选择关联频道
        List<WebElement> list, list1, list2;
        Boolean selected = false;
        for (int i = 0; i < 5; i++) {
            Thread.sleep(2000);
            if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='channelSelect_1_ul']/li"))) break;
        }
        list = driver.findElements(By.xpath("//ul[@id='channelSelect_1_ul']/li"));//第一层
        for (int i = 0; i < list.size(); i++) {
            list1 = driver.findElements(By.xpath("//ul[@id='channelSelect_1_ul']/li[" + (i + 1) + "]"));
            for (int i1 = 0; i1 < list1.size(); i1++) {
                list2 = list1.get(i1).findElements(By.xpath("ul/li"));//第二层
                for (int i2 = 0; i2 < list2.size(); i2++) {
                    if (!list2.get(i2).findElement(By.xpath("./span[2]")).getAttribute("class").contains("disable")) {
                        list2.get(i2).findElement(By.xpath("span[2]")).click();
                        selected = true;
                        break;
                    }
                }
                if (selected) break;
            }
            if (selected) break;
        }
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='content-source modal-win']/div/button[1]")).click();
        Thread.sleep(500);
        driver.findElement(By.className("layui-layer-btn0")).click();
        System.out.println("~~~ addVideoS()，创建竖视频内容样式卡，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //创建媒体号样式卡
    public static void addMedia() throws InterruptedException {
        driver.findElement(By.id("addCard")).click();//点击添加内容样式卡
        Thread.sleep(500);
        driver.findElement(By.xpath("//div[@id='contentBtn-list']/span[4]")).click();//添加媒体号
        Thread.sleep(500);
        driver.findElement(By.xpath("//form[@data-form='form4']/div[1]/div/input")).sendKeys("autoTest-媒体号内容样式卡-" + System.currentTimeMillis());//录入媒体号内容样式卡标题
        Thread.sleep(500);
        driver.findElement(By.xpath("//form[@data-form='form4']/div[4]/div/div[1]")).click();//点击内容来源
        for (int i = 0; i < 5; i++) {
            Thread.sleep(2000);
            if (CommonMethod.isJudgingElement(driver, By.xpath("//div[@class='mark_1_center_in']/div"))) break;
        }
        //选择媒体号
        List<WebElement> medias = driver.findElements(By.xpath("//div[@class='mark_1_center_in']/div"));//媒体号数据list
        int num = 5;
        if (medias.size() < 5) num = medias.size();
        for (int i = 0; i < num; i++) {
            medias.get(i).findElement(By.xpath("div[1]")).click();//选择前5条数据
        }
        Thread.sleep(500);
        driver.findElement(By.className("mark_1_add")).click();//点击添加
        Thread.sleep(1000);
        driver.findElement(By.className("layui-layer-btn0")).click();//确定
        System.out.println("~~~ addMedia()，创建媒体号内容样式卡，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //创建专题样式卡，默认创建文本样式
    public static void addSubject() throws InterruptedException {
        addSubject(1);
    }

    //创建专题样式卡
    public static void addSubject(int type) throws InterruptedException {
        driver.findElement(By.id("addCard")).click();//点击添加内容样式卡
        Thread.sleep(500);
        driver.findElement(By.xpath("//div[@id='contentBtn-list']/span[5]")).click();//添加专题样式卡
        Thread.sleep(500);
        driver.findElement(By.xpath("//form[@data-form='form5']/div[1]/div/input")).sendKeys("autoTest-专题内容样式卡-" + System.currentTimeMillis());//录入专题内容样式卡标题
        Thread.sleep(500);
        if (type == 2) driver.findElement(By.xpath("//form[@data-form='form5']/div[2]/div/div[2]")).click();//创建图文（左右滑动）
        if (type == 3) driver.findElement(By.xpath("//form[@data-form='form5']/div[2]/div/div[3]")).click();//创建图文（上下滑动）
        driver.findElement(By.xpath("//form[@data-form='form5']/div[4]/div/div[1]")).click();//点击内容来源
        for (int i = 0; i < 3; i++) {
            Thread.sleep(2000);
            if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='topic-contanier']/li"))) break;//等待已经加载专题数据完毕
        }
        //选择专题
        List<WebElement> subjects = driver.findElements(By.xpath("//ul[@id='topic-contanier']/li"));//专题list
        int num = 3;
        if (subjects.size() < 3) num = subjects.size();
        for (int i = 0; i < num; i++) {
            subjects.get(i).click();//选择前3条专题
        }
        Thread.sleep(500);
        driver.findElement(By.className("mark_2_add")).click();//添加
        Thread.sleep(1000);
        driver.findElement(By.className("layui-layer-btn0")).click();//确定
        System.out.println("~~~ addSubject()，创建专题内容样式卡，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //创建服务样式卡-默认为普通样式，单行
    public static void addService() throws InterruptedException {
        addService(1, 1);
    }

    //创建服务样式卡，type：1，普通样式；2：卡片样式。set：1，单行；2，双行
    public static void addService(int type, int set) throws InterruptedException {
        driver.findElement(By.id("addCard")).click();//点击添加内容样式卡
        Thread.sleep(500);
        driver.findElement(By.xpath("//div[@id='contentBtn-list']/span[6]")).click();//添加服务样式卡
        Thread.sleep(500);
        driver.findElement(By.xpath("//form[@data-form='form6']/div[1]/div/input")).sendKeys("autoTest-服务内容样式卡-" + System.currentTimeMillis());//录入服务内容样式卡标题
        Thread.sleep(500);
        if (type == 2) driver.findElement(By.xpath("//form[@data-form='form6']/div[2]/div/div[2]")).click();//卡片样式
        if (set == 2) driver.findElement(By.xpath("//form[@data-form='form6']/div[3]/div/div[2]")).click();//双行
        driver.findElement(By.xpath("//form[@data-form='form6']/div[5]/div/div[1]")).click();//点击内容来源
        for (int i = 0; i < 5; i++) {
            Thread.sleep(2000);
            if (CommonMethod.isJudgingElement(driver, By.xpath("//div[@class='mark_1_center_in']/div"))) break;
        }
        //选择服务
        List<WebElement> services = driver.findElements(By.xpath("//div[@class='mark_1_center_in']/div"));//服务list
        int num = 5;
        if (services.size() < 5) num = services.size();
        for (int i = 0; i < num; i++) {
            services.get(i).click();//选择前5条数据
        }
        Thread.sleep(500);
        driver.findElement(By.className("mark_1_add")).click();//点击添加
        Thread.sleep(1000);
        driver.findElement(By.className("layui-layer-btn0")).click();//确定
        System.out.println("~~~ addService()，创建服务内容样式卡，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //创建频道样式卡，默认为普通
    public static void addChannel() throws InterruptedException {
        addChannel(1);
    }

    //创建频道样式卡
    public static void addChannel(int type) throws InterruptedException {
        driver.findElement(By.id("addCard")).click();//点击添加内容样式卡
        Thread.sleep(500);
        driver.findElement(By.xpath("//div[@id='contentBtn-list']/span[7]")).click();//添加频道内容样式卡
        Thread.sleep(500);
        int count = (int) (1 + Math.random() * 999);//标题中的随机数
        String name;
        if (type == 2) name = "街道" + count;
        else name = "频道" + count;
        driver.findElement(By.xpath("//form[@data-form='form7']/div[1]/div/input")).sendKeys(name);//录入频道内容样式卡标题
        Thread.sleep(500);
        if (type == 2) driver.findElement(By.xpath("//form[@data-form='form7']/div[2]/div/div[2]")).click();//街乡类型
        driver.findElement(By.xpath("//form[@data-form='form7']/div[4]/div/div[1]")).click();//点击内容来源
        for (int i = 0; i < 5; i++) {
            Thread.sleep(500);
            if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='channelSelect']/li"))) break;//校验是否打开了频道列表
        }
        List<WebElement> channels1 = driver.findElements(By.xpath("//ul[@id='channelSelect']/li/ul/li"));//频道list-一级频道
        for (int i = 0; i < channels1.size(); i++) {//遍历一级频道
            List<WebElement> channels2 = channels1.get(i).findElements(By.xpath("./ul/li"));//频道列表-二级频道
            if (channels2.size() > 0) {//校验是否存在二级频道
                if (type != 2)
                    for (int j = 0; j < channels2.size(); j++) {//遍历二级频道-普通频道
                        if (channels2.get(j).findElement(By.xpath("./a/span[2]")).getText().contains("测试"))//找到二级频道中有“测试”关键字的
                            channels2.get(j).findElement(By.xpath("./span[2]")).click();//勾选测试频道
                        Thread.sleep(100);
                    }
                else
                    for (int j = 0; j < channels2.size(); j++) {//遍历二级频道-找街乡频道
                        if (channels2.get(j).findElement(By.xpath("./span[2]")).getAttribute("class").contains("full"))//查询二级频道中可以勾选的街乡频道
                            channels2.get(j).findElement(By.xpath("./span[2]")).click();//勾选街乡频道
                        List<WebElement> channels3 = channels2.get(j).findElements(By.xpath("./ul/li"));
                        if (channels3.size() > 0) {
                            for (int n = 0; n < channels3.size(); n++) {
                                if (channels3.get(n).findElement(By.xpath("./span[2]")).getAttribute("class").contains("full"))//查询三级频道中可以勾选的街乡频道
                                    channels3.get(n).findElement(By.xpath("./span[2]")).click();//勾选街乡频道
                                Thread.sleep(100);
                            }
                        }
                        Thread.sleep(100);
                    }
            }
        }

        Thread.sleep(500);
        driver.findElement(By.xpath("//div[@class='content-source modal-win']/div/button[1]")).click();//点击添加频道
        Thread.sleep(1000);

        List<WebElement> channels = driver.findElements(By.xpath("//div[@class='layui-input-block content-dlMain content-channel']/div"));//
        for (int i = 0; i < channels.size(); i++) {
            channels.get(i).findElement(By.xpath("./div[2]")).click();
            Thread.sleep(200);
            CommonMethod.getImg(driver);//资源库上传图片
            Thread.sleep(1000);
        }
        driver.findElement(By.className("layui-layer-btn0")).click();//确定
        System.out.println("~~~ addChannel()，创建频道内容样式卡，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //初始化登录
    static {
        try {
            driver = login();
            for (int i = 0; i < 3; i++) {
                if (!CommonMethod.isJudgingElement(driver, By.tagName("header"))) {
                    if (CommonMethod.isJudgingElement(driver, By.className("loginBtn"))) driver = login();
                    driver.get(domain + "/content/styleCard/init");
                    Thread.sleep(3000);
                    if (!CommonMethod.isJudgingElement(driver, By.className("fold-pack"))) {
                        CommonMethod.jumpModule(driver, "新闻管理");
                        driver.get(domain + "/content/styleCard/init");
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
            CommonMethod.changeMenu(driver, "内容样式卡");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}