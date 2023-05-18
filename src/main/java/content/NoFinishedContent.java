package content;

import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author wufeng
 * @date 2022/2/8 15:26
 */
public class NoFinishedContent extends LoginPortal {

    static WebDriver driver;

    //编辑稿件
    public static void editArticle() throws InterruptedException {

        CommonMethod.getTestTree(driver);//切换到测试频道
        WebElement article = CommonMethod.getTestArticle(driver, 1);//获取测试稿件
        Thread.sleep(2000);

        if (article != null) {
            String winHandleBefore = driver.getWindowHandle();
            Actions sj = new Actions(driver);
            sj.doubleClick(article).build().perform();  //对按钮进行双击

            Thread.sleep(500);
            CommonMethod.swichWindow(driver);//切换标签页

            Thread.sleep(1000);
            CommonMethod.acceptAlert(driver);//有弹窗则关闭弹窗
            Thread.sleep(3000);

            driver.findElement(By.name("title")).clear();
            driver.findElement(By.name("title")).sendKeys("autoTest-EditArticle" + System.currentTimeMillis());
            Thread.sleep(500);
            driver.findElement(By.id("saveAndCloseBtn")).click();//点击保存
            System.out.println("~~~ editArticle()，编辑稿件，执行成功 ~~~");
            Thread.sleep(3000);

//            driver.switchTo().window(allWindows.get(0));//返回到第一个标签页
            driver.switchTo().window(winHandleBefore);//返回到第一个标签页

        } else System.out.println("没找到autoTest数据");
    }

    //删除稿件
    public static void delete() throws InterruptedException {
        CommonMethod.getTestTree(driver);//切换到测试频道
        WebElement article = CommonMethod.getTestArticle(driver, 1);//获取测试稿件
        Thread.sleep(2000);

        if (article != null) {
            article.findElement(By.xpath("div/div/div[@class='left-content']/input")).click();//选中测试稿件
            Thread.sleep(200);
            driver.findElement(By.xpath("//ul[@class='opt-bar-bt clearfix']/li[@data-type='delete']/a")).click();//点击删除
            Thread.sleep(200);
            driver.findElement(By.className("layui-layer-btn0")).click();//点击确认删除
            for (int i = 0; i < 10; i++) {
                Thread.sleep(3000);
                if (!CommonMethod.isJudgingElement(driver, By.className("layui-layer-btn0"))) break;
            }
            System.out.println("~~~ delete()，删除稿件，执行成功 ~~~");
        } else System.out.println("没找到autoTest数据");
    }

    //删除全部测试稿件
    public static void deleteAllTestDatas(String channelName) throws InterruptedException {
        CommonMethod.getTestTree(driver, "columnTree_1_ul", channelName);//切换到测试频道
        CommonMethod.searchTestArticles(driver, 1);

        if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='ulListArea1']/li/div"))) {
            driver.findElement(By.xpath("//form[@id='formDemo']/ul/li[@class='item']/a")).click();
            Thread.sleep(200);
            driver.findElement(By.xpath("//ul[@class='opt-bar-bt clearfix']/li[@data-type='delete']/a")).click();//点击删除
            Thread.sleep(200);
            driver.findElement(By.className("layui-layer-btn0")).click();//点击确认删除
            System.out.println("~~~ deleteAllTestDatas()，清除" + channelName + "频道的全部auto测试稿件，执行成功 ~~~");
            Thread.sleep(3000);
        } else System.out.println("没找到autoTest数据");
    }

    //提交稿件
    public static void submit() throws InterruptedException {
        CommonMethod.getTestTree(driver);//切换到测试频道
        WebElement article = CommonMethod.getTestArticle(driver, 1);//获取测试稿件
        String status1, status2;

        if (article != null) {
            status1 = article.findElement(By.xpath("div/div/div[@class='article-content']/div/div/span[4]")).getText();
            status2 = article.findElement(By.xpath("div/div/div[@class='article-content']/div/div/span[5]")).getText();
            if (status1.equals("初稿") || status2.equals("已下线")) {
                article.findElement(By.xpath("div/div/div[@class='left-content']/input")).click();//选中测试稿件
                Thread.sleep(200);
                driver.findElement(By.xpath("//ul[@class='opt-bar-bt clearfix']/li[@data-type='submit']/a")).click();//点击提交
                System.out.println("~~~ submit()，提交稿件，执行成功 ~~~");
                Thread.sleep(3000);
            } else System.out.println("非初稿或已下线稿件，不可提交");
        } else System.out.println("没找到autoTest数据");
    }

    //审核稿件
    public static void publish() throws InterruptedException {
        CommonMethod.getTestTree(driver);//切换到测试频道
        WebElement article = CommonMethod.getTestArticle(driver, 1);//获取测试稿件

        if (article != null) {
            article.findElement(By.xpath("div/div/div[@class='left-content']/input")).click();//选中测试稿件
            Thread.sleep(200);
            driver.findElement(By.xpath("//ul[@class='opt-bar-bt clearfix']/li[@data-type='publish']/a")).click();//点击审核
            Thread.sleep(200);
            driver.findElement(By.cssSelector("p.content-button.confirm")).click();
            System.out.println("~~~ publish()，审核稿件，执行成功 ~~~");
            Thread.sleep(5000);
        } else System.out.println("没找到autoTest数据");
    }

    //审核稿件-转审
    public static void publish2() throws InterruptedException {
        CommonMethod.getTestTree(driver);//切换到测试频道
        WebElement article = CommonMethod.getTestArticle(driver, 1);//获取测试稿件
        String status1, status2;

        if (article != null) {
            if (!CommonMethod.isJudgingElement(article, By.xpath("div/div/div[@class='article-content']/div/section/span[@class='label-style label-time-publish']"))) {
                status1 = article.findElement(By.xpath("div/div/div[@class='article-content']/div/div/span[4]")).getText();
                status2 = article.findElement(By.xpath("div/div/div[@class='article-content']/div/div/span[5]")).getText();
                if (status1.equals("初稿") || status2.equals("已下线")) {
                    submit();
                    article = CommonMethod.getTestArticle(driver, 1);
                }
                if (!status1.equals("转审中") && !status2.equals("转审中")) {
                    article.findElement(By.xpath("div/div/div[@class='left-content']/input")).click();//选中测试稿件
                    Thread.sleep(200);
                    driver.findElement(By.xpath("//ul[@class='opt-bar-bt clearfix']/li[@data-type='publish']/a")).click();//点击审核
                    Thread.sleep(200);
                    driver.findElement(By.xpath("//form[@id='formAuditModal']/div/div/div[2]/i")).click();//点击转审
                    Thread.sleep(500);
                    driver.findElement(By.className("xm-select-label")).click();//点击选择转审人
                    Thread.sleep(200);
                    driver.findElement(By.xpath("//div[@class='xm-select-label']/input")).sendKeys("吴枫");//录入转审人关键词
                    Thread.sleep(500);
                    List<WebElement> li = driver.findElements(By.xpath("//dl[@class='xm-select-dl ']/dd"));
                    for (int i = 2; i < li.size(); i++) {
                        if (!li.get(i).getAttribute("class").contains("hide")) {
                            li.get(i).findElement(By.xpath("div/span")).click();
                            break;
                        }
                    }
                    Thread.sleep(200);
                    driver.findElement(By.cssSelector("p.content-button.confirm")).click();
                    System.out.println("~~~ publish2()，审核稿件（转审），执行成功 ~~~");
                    Thread.sleep(3000);
                } else System.out.println("稿件是转审中状态的，不允许退回");
            } else System.out.println("定时发布稿件不允许转审");
        } else System.out.println("没找到autoTest数据");
    }

    //审核稿件-退回
    public static void publish3() throws InterruptedException {
        CommonMethod.getTestTree(driver);//切换到测试频道
        WebElement article = CommonMethod.getTestArticle(driver, 1);//获取测试稿件
        String status1, status2;

        if (article != null) {
            if (!CommonMethod.isJudgingElement(article, By.xpath("div/div/div[@class='article-content']/div/section/span[@class='label-style label-time-publish']"))) {
                status1 = article.findElement(By.xpath("div/div/div[@class='article-content']/div/div/span[4]")).getText();//获取审核状态（初稿标签在第4个）
                status2 = article.findElement(By.xpath("div/div/div[@class='article-content']/div/div/span[5]")).getText();//获取已下线状态（已下线标签在第5个）
                if (status1.equals("初稿") || status2.equals("已下线")) {//校验是初稿或已下线稿件
                    submit();
//                    article = CommonMethod.getTestArticle(driver, 1);
                } else if (!status1.equals("转审中") && !status2.equals("转审中")) {//校验不是转审中稿件
                    article.findElement(By.xpath("div/div/div[@class='left-content']/input")).click();//选中测试稿件
                    Thread.sleep(200);
                    driver.findElement(By.xpath("//ul[@class='opt-bar-bt clearfix']/li[@data-type='publish']/a")).click();//点击审核
                    Thread.sleep(200);
                    driver.findElement(By.xpath("//form[@id='formAuditModal']/div/div/div[3]/i")).click();//点击转审
                    Thread.sleep(500);
                    driver.findElement(By.cssSelector("textarea.layui-textarea.audit-textarea")).sendKeys("autoTest-不通过");//录入审核不通过原因
                    Thread.sleep(200);
                    driver.findElement(By.cssSelector("p.content-button.confirm")).click();
                    System.out.println("~~~ publish3()，审核稿件（退回），执行成功 ~~~");
                    Thread.sleep(3000);
                } else System.out.println("稿件是转审中状态的，不允许退回");
            } else System.out.println("定时发布稿件不允许退回");
        } else System.out.println("没找到autoTest数据");
    }

    //审核并排序
    public static void publishAndSort(int type) throws InterruptedException {

        CommonMethod.getTestTree(driver);//切换到测试频道
        WebElement article = CommonMethod.getTestArticle(driver, 1);//获取测试稿件

        if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@class='opt-bar-bt clearfix']/li[@data-type='sortAndPublish']"))) {
            if (article != null) {
                article.findElement(By.xpath("div/div/div[@class='left-content']/input")).click();//选中测试稿件
                Thread.sleep(200);
                driver.findElement(By.xpath("//ul[@class='opt-bar-bt clearfix']/li[@data-type='sortAndPublish']/a")).click();//点击审核并排序
                Thread.sleep(200);
                String name = "排序";
                if (type < 1 || type > 4) type = 1;//如果type值非1-4，则默认为1（1：排序、2：置顶、3：冻结、4：上轮播）
                driver.findElement(By.xpath("//form[@id='formSpModal']/div[2]/div/div[" + type + "]/div")).click();//选择稿件操作类型
                if (type == 1)
                    driver.findElement(By.xpath("//form[@id='formSpModal']/div[2]/div/div[1]/span/input")).sendKeys("20");//排序到第20位
                if (type == 2) {
                    driver.findElement(By.xpath("//form[@id='formSpModal']/div[2]/div/div[2]/span[1]/input")).sendKeys("20");//置顶到第20位
                    name = "置顶";
                }
                if (type == 3) {
                    driver.findElement(By.xpath("//form[@id='formSpModal']/div[2]/div/div[3]/span/input")).sendKeys("3");//冻结到第3位
                    name = "冻结";
                }
                if (type == 4) {
                    driver.findElement(By.xpath("//form[@id='formSpModal']/div[2]/div/div[4]/span/div[1]")).click();//上轮播为是
                    name = "上轮播";
                }
                Thread.sleep(500);
                driver.findElement(By.className("layui-layer-btn0")).click();//点击确定
                System.out.println("~~~ publishAndSort(" + type + ")，稿件审核并排序（" + name + "），执行成功 ~~~");
            } else System.out.println("没有待审核的测试稿件");
        } else System.out.println("当前环境没有审核并排序功能");
        Thread.sleep(3000);
    }

    //定时发布稿件
    public static void timePublish() throws InterruptedException {
        CommonMethod.getTestTree(driver);//切换到测试频道
        WebElement article = CommonMethod.getTestArticle(driver, 1);//获取测试稿件，type为待编区稿件
        Thread.sleep(2000);
        String status1, status2;

        if (article != null) {
            status1 = article.findElement(By.xpath("div/div/div[@class='article-content']/div/div/span[4]")).getText();//获取审核状态
            status2 = article.findElement(By.xpath("div/div/div[@class='article-content']/div/div/span[5]")).getText();//获取审读状态

            if (CommonMethod.isJudgingElement(article, By.xpath("div/div/div[@class='article-content']/div/section/span[@class='label-style label-time-publish']"))) {//校验稿件是否有定时发布标签
                System.out.println("测试稿件已经定时发布");
            } else if (!status1.equals("转审中") && !status2.equals("转审中")) {//校验不是转审中的稿件
                List<WebElement> trs, tds;
                Boolean click = false;
                article.findElement(By.xpath("div/div/div[@class='left-content']/input")).click();//选中测试稿件
                Thread.sleep(200);
                driver.findElement(By.xpath("//ul[@class='opt-bar-bt clearfix']/li[@data-type='timePublish']/a")).click();//点击定时审核
                Thread.sleep(200);
                driver.findElement(By.id("publishIpt")).click();//点击定时发布
                Thread.sleep(500);

                //设置发布时间，设置为下一日
                trs = driver.findElements(By.xpath("//div[@class='layui-laydate-content']/table/tbody/tr"));//获取日历行
                for (int r = 0; r < trs.size(); r++) {
                    tds = trs.get(r).findElements(By.xpath("td"));//获取每周日期
                    for (int d = 0; d < tds.size() - 1; d++) {//在周行的第一个到倒数第二个之间循环
                        if (tds.get(d).getAttribute("class").equals("layui-this")) {//校验找到当前日期标签
                            tds.get(d + 1).click();//点击当前日期所在行的下一天
                            click = true;
                            break;
                        }
                    }
                    if (tds.get(tds.size() - 1).getAttribute("class").equals("layui-this")) {//当前日期是周行的最后一个（周六）
                        trs.get(r + 1).findElement(By.xpath("td[1]")).click();//点击下一周行的第一个
                        click = true;
                    }
                    if (click) break;
                }
                Thread.sleep(500);
                driver.findElement(By.className("laydate-btns-confirm")).click();//点击日历图层的确定
                Thread.sleep(500);
                driver.findElement(By.className("layui-layer-btn0")).click();//点击设置发布时间的确定
                System.out.println("~~~ timePublish()，定时发布稿件，执行成功 ~~~");
                Thread.sleep(3000);
            } else System.out.println("转审稿件需要指定审核人操作定时发布");
        } else System.out.println("没找到autoTest数据");
    }

    //取消定时发布
    public static void cancelTimePublish() throws InterruptedException {
        CommonMethod.getTestTree(driver);//切换到测试频道
        driver.findElement(By.id("ejectPublish")).click();//打开定时发布图层
        Thread.sleep(3000);
        if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='publishlist']/li/div"))) {//校验是否有数据
            WebElement testArticle;
            Boolean cancel = false;
            List<WebElement> lis = driver.findElements(By.xpath("//ul[@id='publishlist']/li"));//定时发布数据列表
            for (int i = 0; i < lis.size(); i++) {
                testArticle = lis.get(i);//定时发布稿件数据
                if (testArticle.findElement(By.xpath("div/p")).getText().contains("autoTest") && testArticle.findElement(By.xpath("div/span")).getAttribute("class").contains("waitpublished")) {//校验是否有待发布的auto稿件
                    Actions action = new Actions(driver);
                    action.moveToElement(testArticle).perform();//鼠标悬浮
                    Thread.sleep(500);
                    driver.findElement(By.cssSelector("i.fa.fa-trash-o")).click();//点击删除
                    Thread.sleep(500);
                    driver.findElement(By.className("layui-layer-btn0")).click();//点击确定
                    cancel = true;
                    Thread.sleep(2000);
                    break;
                }
            }
            if (cancel) System.out.println("~~~ cancelTimePublish()，取消定时发布，执行成功 ~~~");
            else System.out.println("没有待发布的定时发布auto测试稿件可取消");
        } else System.out.println("没有定时发布数据");
        driver.findElement(By.xpath("//div[@class='timePublish']/div/i")).click();//关闭定时发布图层
        Thread.sleep(3000);
    }

    //复制
    public static void copy() throws InterruptedException {
        CommonMethod.getTestTree(driver);//切换到测试频道
        WebElement article = CommonMethod.getTestArticle(driver, 1);//获取测试稿件
        Thread.sleep(3000);

        if (article != null) {
            boolean ischecked = article.findElement(By.xpath("div/div/div[@class='left-content']/input")).isSelected();
            if (!ischecked) article.findElement(By.xpath("div/div/div[@class='left-content']/input")).click();//选中测试稿件
            Thread.sleep(200);
            driver.findElement(By.xpath("//ul[@class='opt-bar-bt clearfix']/li[@data-type='copy']/a")).click();//点击复制
            for (int i = 0; i < 5; i++) {
                Thread.sleep(2000);
                if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='undefined_1_ul']/li"))) break;//循环等待出频道列表
            }

            List<WebElement> list1, list2;
            Boolean copy = false;
            list1 = driver.findElements(By.xpath("//ul[@id='undefined_1_ul']/li"));//第一层频道
            for (int i1 = 0; i1 < list1.size(); i1++) {
                list2 = list1.get(i1).findElements(By.xpath("ul/li"));//第二层频道
                for (int i2 = 0; i2 < list2.size(); i2++) {
                    if (list2.get(i2).findElement(By.tagName("a")).getAttribute("title").equals("频道测试")) {
                        list2.get(i2).findElement(By.xpath("span[2]")).click();
                        copy = true;
                        break;
                    }
                }
                if (copy) break;
            }
            Thread.sleep(1000);
            if (copy) {
                driver.findElement(By.className("layui-layer-btn0")).click();//确定
                System.out.println("~~~ copy()，复制稿件，执行成功 ~~~");
            } else {
                driver.findElement(By.className("layui-layer-btn1")).click();//取消
                System.out.println("没有找到复制目标频道（频道测试）");
            }
        } else System.out.println("没找到autoTest数据");
        Thread.sleep(3000);
    }

    //转移
    public static void move() throws InterruptedException {
        CommonMethod.getTestTree(driver);//切换到测试频道
        WebElement article = CommonMethod.getTestArticle(driver, 1);//获取测试稿件
        Thread.sleep(3000);

        if (article != null) {
            article.findElement(By.xpath("div/div/div[@class='left-content']/input")).click();//选中测试稿件
            Thread.sleep(200);
            driver.findElement(By.xpath("//ul[@class='opt-bar-bt clearfix']/li[@data-type='move']/a")).click();//点击转移
            for (int i = 0; i < 5; i++) {
                Thread.sleep(2000);
                if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='undefined_1_ul']/li"))) break;
            }
            List<WebElement> list1, list2;
            Boolean move = false;
            list1 = driver.findElements(By.xpath("//ul[@id='undefined_1_ul']/li"));//第一层list
            for (int i1 = 0; i1 < list1.size(); i1++) {
                list2 = list1.get(i1).findElements(By.xpath("ul/li"));//第二层list
                for (int i2 = 0; i2 < list2.size(); i2++) {
                    if (list2.get(i2).findElement(By.tagName("a")).getAttribute("title").equals("频道测试")) {//转移目标频道
                        list2.get(i2).findElement(By.xpath("span[2]")).click();
                        move = true;
                        break;
                    }
                }
                if (move) break;
            }
            Thread.sleep(1000);
            if (move) {
                driver.findElement(By.className("layui-layer-btn0")).click();//确定
                System.out.println("~~~ move()，移动稿件，执行成功 ~~~");
            } else {
                driver.findElement(By.className("layui-layer-btn1")).click();//取消
                System.out.println("没有找到移动目标频道（频道测试）");
            }
        } else System.out.println("没找到autoTest数据");
        Thread.sleep(3000);
    }

    //取消关联
    public static void unrelate() throws InterruptedException {
        CommonMethod.getTestTree(driver, "columnTree_1_ul", "频道测试");//切换到测试频道
        WebElement article = CommonMethod.getTestArticle(driver, 1);//获取测试稿件
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
            } else {
                driver.navigate().refresh();
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
