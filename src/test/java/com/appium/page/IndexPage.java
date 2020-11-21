package com.appium.page;


import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;

public class IndexPage extends BasePage {

    public IndexPage(AndroidDriver driver) {
        super(driver);
    }
    // 通讯录按钮
    private MobileBy mailListBtn =  new MobileBy.ByAndroidUIAutomator("new UiSelector().text(\"通讯录\")");

    public MailListPage gotoMailListPage(){
        clickElement(mailListBtn);
        return new MailListPage(driver);
    }
}
