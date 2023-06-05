package base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author wufeng
 * @date 2022/2/7 14:54
 */
public class LoginPortal {

    public static String domain = env().get(0);
    public static String siteName = env().get(1);

    static WebDriver driver = initDriver();

    //浏览器初始化
    public static WebDriver initDriver() {
        System.setProperty("webdriver.chrome.driver", "D:\\autotest\\tools\\chromedriver.exe");//浏览器驱动

        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("ignore-certificate-errors");
        options.setAcceptInsecureCerts(true);
        driver = new ChromeDriver(options);//创建浏览器对象

        driver.manage().window().maximize();//最大化
        return driver;
    }

    //登录portal后台，滑块方式的自动滑块登录，校验码方式的需要手动录入验证码！！！
    public static void login(String username, String password) throws InterruptedException {

        driver.get(domain + "/portal/login");
        //校验是否需要登录
        if (CommonMethod.isJudgingElement(driver, By.className("loginBtn"))) {
            driver.findElement(By.name("username")).sendKeys(username);
            driver.findElement(By.name("password")).sendKeys(password);

            if (CommonMethod.isJudgingElement(driver, By.className("slide"))) {
                //手动拖动滑块
                Actions action = new Actions(driver);
                WebElement moveButton = driver.findElement(By.className("slide"));
                //移到滑块元素并悬停
                action.moveToElement(moveButton).clickAndHold(moveButton);
                action.dragAndDropBy(moveButton, 305, 0).perform();
                action.release();

                Thread.sleep(2000);
//            }
            } else {
                //验证码
                By canvasImg = By.id("canvas");
                //验证码输入框
//                By yzm = By.xpath("/html/body/div[1]/div/div/form/div[2]/input");
                By yzm = By.className("passCode");
                //JS修改验证码变为默认返回值
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("Math.random = function(){return 0}");
                driver.findElement(canvasImg).click();
                Thread.sleep(500);
                //修改后默认验证码AAAA
                driver.findElement(yzm).sendKeys("AAAA");
            }
//                Thread.sleep(8000);//验证码方式，增加等待时间，手动输入验证码
//            CommonMethod.vCode(driver);

            driver.findElement(By.className("loginBtn")).click();//点击登录
            Thread.sleep(3000);
        }
    }

    //默认wf账号登录
    public static WebDriver login() throws InterruptedException {
        String username = env().get(2);//获取用户名
        String password = env().get(3);//获取密码
        login(username, password);//登录portal
        return driver;
    }

    //获取环境配置
    public static List<String> env() {

//        String envString = "envtest";//测试环境
//        String envString = "envyanshi";//演示环境
        String envString = "envyqtest";//延庆测试环境

        Properties pro = new Properties();
        InputStream prois;

        List<String> envlist = new ArrayList<>();
        try {
            prois = new FileInputStream("application.properties");
            pro.load(new InputStreamReader(prois, "UTF-8"));

            String domain = (String) pro.getProperty(envString + ".domain");//获取域名
            String siteName = (String) pro.getProperty(envString + ".siteName");//获取站点名
            String username = (String) pro.getProperty(envString + ".username");//获取用户名
            String password = (String) pro.getProperty(envString + ".password");//获取密码

            //添加到list中
            envlist.add(domain);
            envlist.add(siteName);
            envlist.add(username);
            envlist.add(password);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return envlist;
    }

}
