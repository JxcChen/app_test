package com.appium.page;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;

import java.time.Duration;

public class AddAndEditMemberPage extends BasePage {

    public AddAndEditMemberPage(AndroidDriver driver) {
        super(driver);
    }

    // 输入姓名
    private By username = MobileBy.id("b4t");
    // 输入手机
    private By phone = MobileBy.id("fow");
    // 保存
    private MobileBy save = new MobileBy.ByAndroidUIAutomator("new UiSelector().text(\"保存\")");
    private MobileBy departmentSetting = new MobileBy.ByAndroidUIAutomator("new UiSelector().text(\"设置部门\")");
    // 设置部门内的确定按钮
    private MobileBy setDepartmentConfirmBtn = new MobileBy.ByAndroidUIAutomator("new UiSelector().text(\"确定(1)\")");
    private By telephone = new By.ByXPath("//*[@text='座机　']/following-sibling::android.widget.EditText");
    // 删除成员
    private MobileBy deleteMember = new MobileBy.ByAndroidUIAutomator("new UiSelector().text(\"删除成员\")");
    private MobileBy deleteMemberConfirmBtn = new MobileBy.ByAndroidUIAutomator("new UiSelector().text(\"确定\")");



    /**
     * 输入名字
     * @param name
     */
    public void sendUsername(String name){
        sendKey(username,name);
    }

    public void sendPhone(String phoneNum){
        sendKey(phone,phoneNum);
    }


    /**
     * 输入座机
     * @param telephoneNum 座机号码
     */
    public void sendTelephone(String telephoneNum){
        sendKey(telephone,telephoneNum);
    }

    /**
     * 设置部门   暂时没有新部门  直接点确定
     */
    public void setDepartment(){
        clickElement(departmentSetting);
        clickElement(setDepartmentConfirmBtn);
    }

    /**
     * 点击保存
     */
    public void save(){
        clickElement(save);
    }

    /**
     * 在编辑也点击删除成员并点击确定
     */
    public void deleteMember(){
        clickElement(deleteMember);
        clickElement(deleteMemberConfirmBtn);
    }



}
