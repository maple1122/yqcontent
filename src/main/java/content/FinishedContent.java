package content;

import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static javax.management.Query.in;

/**
 * @author wufeng
 * @date 2022/2/23 16:48
 */
public class FinishedContent extends LoginPortal {
    static WebDriver driver;

    //下线
    public static void unpublish() throws InterruptedException {
        CommonMethod.getTestTree(driver);//打开测试频道
        WebElement article = CommonMethod.getTestArticle(driver, 2);//获取测试稿件
        if (article != null) {
            article.findElement(By.xpath("div/div/div/input")).click();//选择测试稿件
            Thread.sleep(200);
            driver.findElement(By.xpath("//ul[@class='opt-bar-bt clearfix']/li[@data-type='unpublish']")).click();//点击下线
            System.out.println("~~~ unpublish()，稿件下线，执行成功 ~~~");
            Thread.sleep(5000);
        } else System.out.println("没有auto的测试数据！");
    }

    //关联
    public static void relate() throws InterruptedException {
        CommonMethod.getTestTree(driver);
        WebElement article = CommonMethod.getTestArticle(driver, 2);
        if (article != null) {
            article.findElement(By.xpath("div/div/div/input")).click();//选择测试稿件
            Thread.sleep(200);
            driver.findElement(By.xpath("//ul[@class='opt-bar-bt clearfix']/li[@data-type='relate']")).click();//点击关联
            for (int i = 0; i < 5; i++) {
                Thread.sleep(2000);
                if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='undefined_1_ul']/li"))) break;
            }
            //选择关联频道
            List<WebElement> list1, list2;
            Boolean relate = false;
            list1 = driver.findElements(By.xpath("//ul[@id='undefined_1_ul']/li"));//第一层
            for (int i1 = 0; i1 < list1.size(); i1++) {
                list2 = list1.get(i1).findElements(By.xpath("ul/li"));//第二层
                for (int i2 = 0; i2 < list2.size(); i2++) {
                    if (list2.get(i2).findElement(By.tagName("a")).getAttribute("title").equals("频道测试")) {
                        list2.get(i2).findElement(By.xpath("span[2]")).click();
                        relate = true;
                        break;
                    }
                }
                if (relate) break;
            }
            Thread.sleep(1000);
            if (relate) {
                driver.findElement(By.className("layui-layer-btn0")).click();//确定
                System.out.println("~~~ relate()，关联稿件，执行成功 ~~~");
            } else {
                driver.findElement(By.className("layui-layer-btn1")).click();//取消
                System.out.println("没有找到关联目标频道（频道测试）");
            }
            Thread.sleep(3000);
        } else System.out.println("没有auto的测试数据！");
    }

    //关联并排序
    public static void relateAndSort(int type) throws InterruptedException {
        unRelateAll();//清空已关联的测试稿件
        int testSign = 0;//可冻结的稿件位置-1
        String label;
        if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@class='opt-bar-bt clearfix']/li[@data-type='sortAndRelate']"))) {
            if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='ulListArea2']/li"))) {//校验是否有数据
                List<WebElement> lists = driver.findElements(By.xpath("//ul[@id='ulListArea2']/li"));//获取稿件列表
                boolean testable = false;//是否可冻结
                for (int i = 0; i < lists.size(); i++) {//遍历每条稿件
                    List<WebElement> labels = lists.get(i).findElements(By.xpath("./div/div/div[2]/div/section/span"));//稿件的标签列表
                    for (int j = 0; j < labels.size(); j++) {//编辑稿件标签列表
                        testable = true;//初始默认为可冻结
                        label = labels.get(j).getAttribute("class");
                        if (label.contains("carousel") || label.contains("istop") || label.contains("isfreeze")) {//校验是否有轮播、置顶、冻结、上轮播标签
                            testable = false;//有以上标签则为非可冻结位
                            break;
                        }
                    }
                    if (testable) {//稿件循环了所有标签无置顶、冻结、轮播相关标签
                        testSign = i;//获取到可冻结的位置，testSign为位置-1
                        break;
                    }
                }
            }
            CommonMethod.getTestTree(driver);//切换的测试test频道
            WebElement article = CommonMethod.getTestArticle(driver, 2);//切换到成稿区
            if (article != null) {//如果稿件非空
                article.findElement(By.xpath("div/div/div/input")).click();//选择测试稿件
                Thread.sleep(200);
                driver.findElement(By.xpath("//ul[@class='opt-bar-bt clearfix']/li[@data-type='sortAndRelate']")).click();//点击关联并排序
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(2000);
                    if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='undefined_1_ul']/li")))
                        break;//循环等待到取到了频道列表
                }
                //选择关联频道
                List<WebElement> list1, list2;//定义一级、二级频道
                Boolean relate = false;
                list1 = driver.findElements(By.xpath("//ul[@id='undefined_1_ul']/li"));//第一层频道列表
                for (int i1 = 0; i1 < list1.size(); i1++) {
                    list2 = list1.get(i1).findElements(By.xpath("ul/li"));//第二层频道列表
                    for (int i2 = 0; i2 < list2.size(); i2++) {
                        if (list2.get(i2).findElement(By.tagName("a")).getAttribute("title").equals("频道测试")) {//找到测试频道
                            list2.get(i2).findElement(By.xpath("span[2]")).click();//选择测试频道
                            relate = true;
                            break;
                        }
                    }
                    if (relate) break;
                }
                Thread.sleep(1000);
                //进行排序规则设置
                if (relate) {
                    String name = "排序";
                    if (type < 1 || type > 4) type = 1;//如果type值非1-4，则默认为1（1：排序、2：置顶、3：冻结、4：上轮播）
                    driver.findElement(By.xpath("//form[@id='formSrModal']/div/div/div[" + type + "]/div")).click();//选择稿件操作类型
                    if (type == 1)
                        driver.findElement(By.xpath("//form[@id='formSrModal']/div/div/div[1]/span/input")).sendKeys("20");//默认20，不校验是否大于目标频道稿件数
                    if (type == 2) {
                        driver.findElement(By.xpath("//form[@id='formSrModal']/div/div/div[2]/span[1]/input")).sendKeys("20");//默认20，不校验是否大于目标频道稿件数
                        name = "置顶";
                    }
                    if (type == 3) {
                        driver.findElement(By.xpath("//form[@id='formSrModal']/div/div/div[3]/span/input")).sendKeys(String.valueOf(testSign + 1));//录入目标频道的可冻结位
                        name = "冻结";
                    }
                    if (type == 4) {
                        driver.findElement(By.xpath("//form[@id='formSrModal']/div/div/div[4]/span/div[1]")).click();//设置上轮播
                        name = "上轮播";
                    }
                    Thread.sleep(1000);
                    driver.findElement(By.className("layui-layer-btn0")).click();//确定
                    System.out.println("~~~ relateAndSort(" + type + ")，稿件关联并排序（" + name + "），执行成功 ~~~");
                } else {
                    driver.findElement(By.className("layui-layer-btn1")).click();//取消
                    System.out.println("没有找到关联目标频道（频道测试）");
                }
                Thread.sleep(4000);
            } else System.out.println("没有auto的测试数据！");
        } else System.out.println("当前环境没有关联并排序功能");
    }

    //取消关联
    public static void unRelate() throws InterruptedException {
        CommonMethod.getTestTree(driver, "columnTree_1_ul", "频道测试");//切换到测试频道
        WebElement article = CommonMethod.getTestArticle(driver, 2);//获取测试稿件
        Thread.sleep(2000);

        if (article != null) {
            if (CommonMethod.isJudgingElement(article, By.xpath("div/div/div[@class='article-content']/div/section/span[@class='label-style label-related']"))) {
                article.findElement(By.xpath("div/div/div[@class='left-content']/input")).click();//选中测试稿件
                Thread.sleep(200);
                driver.findElement(By.xpath("//ul[@class='opt-bar-bt clearfix']/li[@data-type='unrelate']/a")).click();//点击取消关联
                System.out.println("~~~ unrelate()，取消关联，执行成功 ~~~");
                Thread.sleep(3000);
            } else System.out.println("非关联稿件");
        } else System.out.println("没找到autoTest数据");
    }

    //取消所有关联
    public static void unRelateAll() throws InterruptedException {
        CommonMethod.getTestTree(driver, "columnTree_1_ul", "频道测试");//切换到测试频道
//        CommonMethod.searchTestArticles(driver,2);
        driver.findElement(By.xpath("//form[@id='formDemo']/ul/li[2]")).click();//勾选“关联”筛选条件
        Thread.sleep(2000);
        if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='ulListArea2']/li"))) {//校验是否有关联数据
            List<WebElement> lists = driver.findElements(By.xpath("//ul[@id='ulListArea2']/li"));//关联数据列表
            boolean checked = false;//是否有已选中测试数据
            for (int i = 0; i < lists.size(); i++) {//循环整个关联数据列表
                if (lists.get(i).findElement(By.xpath("./div/div/div[2]/div/section/a")).getText().contains("autoTest")) {//校验关联稿件名称中是否有autoTest
                    lists.get(i).findElement(By.xpath("./div/div/div[1]/input")).click();//勾选autoTest的关联稿件
                    checked = true;//已选中状态
                }
                Thread.sleep(100);
            }
            if (checked) {//有已选中测试关联数据
                driver.findElement(By.xpath("//ul[@class='opt-bar-bt clearfix']/li[@data-type='unrelate']/a")).click();//点击取消关联
                System.out.println("~~~ unrelateAll()，已取消当前页auto数据的关联 ~~~");
            } else System.out.println("没有关联的自动化测试数据");
        } else System.out.println("没有关联数据");
        driver.findElement(By.xpath("//form[@id='formDemo']/ul/li[2]")).click();
        Thread.sleep(3000);
    }

    //置顶
    public static void top() throws InterruptedException {
        CommonMethod.getTestTree(driver);//切换到测试频道
        WebElement article = CommonMethod.getTestArticle(driver, 2);//获取测试稿件
        if (article != null) {
            List<WebElement> labels = article.findElements(By.xpath("div/div/div[@class='article-content']/div/section/span"));
            String label;
            Boolean canTop = true;

            //校验稿件是否已置顶、已轮播、已冻结，不可执行置顶操作
            for (int i = 0; i < labels.size(); i++) {
                label = labels.get(i).getAttribute("class");//标签
                if (label.contains("istop") || label.contains("carousel") || label.contains("isfreeze")) {
                    System.out.println("稿件是已置顶/轮播/冻结稿件，不可执行置顶操作");
                    canTop = false;
                    break;
                }
            }
            //普通稿件进行置顶
            if (canTop) {
                article.findElement(By.xpath("div/div/div[@class='left-content']/input")).click();//选中测试稿件
                Thread.sleep(200);
                driver.findElement(By.xpath("//ul[@class='opt-bar-bt clearfix']/li[@data-type='top']/a")).click();//点击置顶
                Thread.sleep(500);
                driver.findElement(By.className("layui-layer-btn0")).click();//确定置顶
                System.out.println("~~~ top()，稿件置顶，执行成功 ~~~");
            }
            Thread.sleep(3000);
        } else System.out.println("未找到自动化测试数据");
    }

    //置顶设置
    public static void setTop() throws InterruptedException {
        CommonMethod.getTestTree(driver);//切换到测试频道
        WebElement article = CommonMethod.getTestArticle(driver, 2);//获取测试稿件
        if (article != null) {
            List<WebElement> labels = article.findElements(By.xpath("div/div/div[@class='article-content']/div/section/span"));
            String label;
            Boolean canCanel = false;

            //校验稿件是否已置顶
            for (int i = 0; i < labels.size(); i++) {
                label = labels.get(i).getAttribute("class");
                if (label.contains("istop")) {
                    article.findElement(By.xpath("div/div/div[@class='article-content']/div/section/span[@class='right-click-pack']/i[@class='btn-icon-zhiding']")).click();
                    Thread.sleep(200);
                    driver.findElement(By.id("endTime")).click();
                    Thread.sleep(200);
                    List<WebElement> trs, tds;
                    Boolean clickDate = false;
                    trs = driver.findElements(By.xpath("//div[@class='layui-laydate-content']/table/tbody/tr"));
                    for (int r = 0; r < trs.size(); r++) {
                        tds = trs.get(r).findElements(By.xpath("td"));
                        for (int d = 0; d < tds.size() - 1; d++) {
                            if (tds.get(d).getAttribute("class").contains("this")) {
                                tds.get(d + 1).click();
                                clickDate = true;
                                break;
                            }
                        }
                        if (tds.get(tds.size() - 1).getAttribute("class").contains("this")) {
                            trs.get(r + 1).findElement(By.xpath("td[1]")).click();
                            clickDate = true;
                        }
                        if (clickDate) break;
                    }
                    Thread.sleep(200);
                    if (clickDate) driver.findElement(By.className("laydate-btns-confirm")).click();
                    else driver.findElement(By.className("laydate-btns-clear")).click();
                    Thread.sleep(500);
                    driver.findElement(By.className("layui-layer-btn0")).click();//确定取消置顶
                    System.out.println("~~~ setTop()，稿件设置置顶，执行成功 ~~~");
                    canCanel = true;
                    break;
                }
            }
            //未置顶的稿件
            if (!canCanel) System.out.println("稿件未被置顶");
            Thread.sleep(3000);
        } else System.out.println("未找到自动化测试数据");
    }

    //取消置顶
    public static void cancelTop() throws InterruptedException {
        CommonMethod.getTestTree(driver);//切换到测试频道
        WebElement article = CommonMethod.getTestArticle(driver, 2);//获取测试稿件
        if (article != null) {
            List<WebElement> labels = article.findElements(By.xpath("div/div/div[@class='article-content']/div/section/span"));
            String label;
            Boolean canCanel = false;

            //校验稿件是否已置顶
            for (int i = 0; i < labels.size(); i++) {
                label = labels.get(i).getAttribute("class");
                if (label.contains("istop")) {
                    article.findElement(By.xpath("div/div/div[@class='left-content']/input")).click();//选中测试稿件
                    Thread.sleep(200);
                    driver.findElement(By.xpath("//ul[@class='opt-bar-bt clearfix']/li[@data-type='canceltop']/a")).click();//点击取消置顶
                    Thread.sleep(500);
                    driver.findElement(By.className("layui-layer-btn0")).click();//确定取消置顶
                    System.out.println("~~~ cancelTop()，稿件取消置顶，执行成功 ~~~");
                    canCanel = true;
                    break;
                }
            }
            //未置顶的稿件
            if (!canCanel) System.out.println("稿件未被置顶");
            Thread.sleep(3000);
        } else System.out.println("未找到自动化测试数据");
    }

    //冻结
    public static void freeze() throws InterruptedException {
        CommonMethod.getTestTree(driver);//切换到测试频道
        WebElement article = CommonMethod.getTestArticle(driver, 2);//获取测试稿件
        if (article != null) {
            List<WebElement> labels = article.findElements(By.xpath("div/div/div[@class='article-content']/div/section/span"));
            String label;
            Boolean canTop = true;

            //校验稿件是否已置顶、已轮播、已冻结，不可执行置顶操作
            for (int i = 0; i < labels.size(); i++) {
                label = labels.get(i).getAttribute("class");
                if (label.contains("istop") || label.contains("carousel") || label.contains("isfreeze")) {
                    System.out.println("稿件是已置顶/轮播/冻结稿件，不可执行冻结操作");
                    canTop = false;
                    break;
                }
            }
            //普通稿件进行冻结
            if (canTop) {
                article.findElement(By.xpath("div/div/div[@class='left-content']/input")).click();//选中测试稿件
                Thread.sleep(200);
                driver.findElement(By.xpath("//ul[@class='opt-bar-bt clearfix']/li[@data-type='freezeContent']/a")).click();//点击置顶
                Thread.sleep(500);
                driver.findElement(By.className("layui-layer-btn0")).click();//确定置顶
                System.out.println("~~~ freeze()，稿件冻结，执行成功 ~~~");
            }
            Thread.sleep(3000);
        } else System.out.println("未找到自动化测试数据");
    }

    //取消冻结
    public static void cancelFreeze() throws InterruptedException {
        CommonMethod.getTestTree(driver);//切换到测试频道
        WebElement article = CommonMethod.getTestArticle(driver, 2);//获取测试稿件
        if (article != null) {
            List<WebElement> labels = article.findElements(By.xpath("div/div/div[@class='article-content']/div/section/span"));
            String label;
            Boolean canCanel = false;

            //校验稿件是否已冻结
            for (int i = 0; i < labels.size(); i++) {
                label = labels.get(i).getAttribute("class");
                if (label.contains("isfreeze")) {
                    article.findElement(By.xpath("div/div/div[@class='left-content']/input")).click();//选中测试稿件
                    Thread.sleep(200);
                    driver.findElement(By.xpath("//ul[@class='opt-bar-bt clearfix']/li[@data-type='cancelFreezeContent']/a")).click();//点击取消冻结
                    Thread.sleep(500);
                    driver.findElement(By.className("layui-layer-btn0")).click();//确定取消置顶
                    System.out.println("~~~ cancelTop()，稿件取消置顶，执行成功 ~~~");
                    canCanel = true;
                    break;
                }
            }
            //未置顶的稿件
            if (!canCanel) System.out.println("稿件未被冻结");
            Thread.sleep(3000);
        } else System.out.println("未找到自动化测试数据");
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
