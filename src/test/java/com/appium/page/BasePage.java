package com.appium.page;

import com.appium.common.Constant;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    public AndroidDriver driver;
    public BasePage(AndroidDriver driver){
        this.driver = driver;
    }
    public BasePage(){}

    /**
     * 等待元素可见
     * @param locator 目标元素定位器
     * @return 目标元素
     */
    public WebElement waitElementVisible(By locator){
        WebDriverWait wait = new WebDriverWait(driver, Constant.WAIT_TIME);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * 等待元素可被点击
     * @param locator 目标元素定位器
     * @return 目标元素
     */
    public WebElement waitElementClickble(By locator){
        WebDriverWait wait = new WebDriverWait(driver,Constant.WAIT_TIME);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * 点击元素
     * @param locator 目标元素定位器
     */
    public void clickElement(By locator){
        waitElementClickble(locator).click();
    }

    /**
     * 对元素输入内容
     * @param locator 目标元素定位器
     * @param key 输入的内容
     */
    public void sendKey(By locator, String key){
        WebElement webElement = waitElementVisible(locator);
        webElement.clear();
        webElement.sendKeys(key);
    }

    public String getElementText(By locator){ return waitElementVisible(locator).getAttribute("text"); }

    /**
     * 向下滚动
     */
    public void swipeDown(){
        // 获取页面尺寸
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        // 1.创建touchAction对象
        TouchAction touchAction = new TouchAction(driver);
        // 2.创建Duration对象 单位为毫秒
        Duration duration = Duration.ofMillis(600);

        // 3.添加滑动操作
        touchAction
                .press(PointOption.point(width/2,height/2)) // 按住开始坐标
                .waitAction(WaitOptions.waitOptions(duration)) // 设置执行时长
                .moveTo(PointOption.point(width/2,height/5)) //移动到达的位置
                .release() // 放开鼠标
                .perform(); // 执行操作
    }

}
