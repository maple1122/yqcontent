package topic;

import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * @author wufeng
 * @date 2022/3/3 16:48
 */
public class AddTopic extends LoginPortal {

    static WebDriver driver;

    //新建专题，普通类型，origin：1，无子栏目，手动添加稿件；2，无子栏目,稿件来源于频道；3，有子栏目
    public static void addTopic(int origin) throws InterruptedException {
        addTopic(origin, 1);
    }

    //新建专题，origin：1，无子栏目，手动添加稿件；2，无子栏目,稿件来源于频道；3，有子栏目。type：1，普通；2，音频；3，街乡
    public static void addTopic(int origin, int type) throws InterruptedException {
        driver.findElement(By.id("addTopic")).click();//点击新建专题
        Thread.sleep(500);
        String autoName;
        switch (origin) {//专题类型，默认为手动无子栏目
            case 1:
                autoName = "手动添加稿件";
                break;
            case 2:
                autoName = "稿件来源于频道";
                break;
            case 3:
                autoName = "有子栏目";
                break;
            default:
                autoName = "手动添加稿件";
        }
        if (type == 2) autoName += "音频类型";
        else if (type == 3) autoName = "要闻类型";
        for (int i = 1; i < 20; i++) {
            if (CommonMethod.isJudgingElement(driver, By.id("layui-layer-iframe" + i))) {//获取当前iframe有效id
                driver.switchTo().frame("layui-layer-iframe" + i);//切换到iframe
                Thread.sleep(500);
                driver.findElement(By.name("title")).sendKeys("autoTest-专题（" + autoName + "）-" + System.currentTimeMillis());//录入专题标题
                driver.findElement(By.xpath("//form[@id='formAttribute']/div[5]/div[3]/div[1]/i")).click();//选择为文本方式
                Thread.sleep(500);
                if (origin == 2) {//稿件来源于频道
                    driver.findElement(By.xpath("//form[@id='formAttribute']/div[2]/div[1]/div[1]/i")).click();//点击来源于频道
                    Thread.sleep(200);
                    driver.findElement(By.className("source-list-hint")).click();//点击关联专题频道
                    Thread.sleep(1500);
                    CommonMethod.getTestTree(driver, "undefined_1_ul", "频道测试");//设置频道
                    Thread.sleep(500);
                    driver.findElement(By.cssSelector("button.layui-btn.channel-confirm")).click();//频道确认
                    Thread.sleep(500);
                }
                if (origin == 3) {//手动，有子栏目
                    driver.findElement(By.xpath("//form[@id='formAttribute']/div[1]/div/div[1]/i")).click();//点击有子栏目
                    Thread.sleep(500);
                }
                driver.findElement(By.xpath("//form[@id='formAttribute']/div[3]/div/div[" + type + "]/i")).click();
                Thread.sleep(500);

                driver.findElement(By.id("saveBtn")).click();//点击保存
                System.out.println("~~~ addTopic()，新建专题（" + autoName + "），执行成功 ~~~");
                driver.switchTo().defaultContent();//返回到默认iframe
                break;
            }
        }
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
