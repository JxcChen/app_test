package com.appium.page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MainPage extends BasePage {

    public MainPage(){
        this.initDriver();
    }

    private void initDriver(){
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName","Android");
            capabilities.setCapability("deviceName","mate40");
            capabilities.setCapability("appPackage","com.tencent.wework");
            capabilities.setCapability("appActivity",".launch.LaunchSplashActivity");
            capabilities.setCapability("noReset",true);
            capabilities.setCapability("automationName","UiAutomator2");
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public MailListPage getMailListPage(){
        return new IndexPage(driver).gotoMailListPage();
    }

}
