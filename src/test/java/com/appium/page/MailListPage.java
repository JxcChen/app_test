package com.appium.page;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.net.MalformedURLException;

public class MailListPage extends BasePage {


    public MailListPage(AndroidDriver driver) {
        super(driver);
    }
    // 搜索按钮
    private By searchBtn = new By.ById("i6n");
    // 搜索输入框
    private MobileBy searchInput =  new MobileBy.ByAndroidUIAutomator("new UiSelector().text(\"搜索\")");
    // 添加成员按钮
    private MobileBy addMemberBtn =  new MobileBy.ByAndroidUIAutomator("new UiSelector().text(\"添加成员\")");
    // 手动添加按钮
    private By manualAddMemberBtn = new By.ById("cox");
    // 名片右上角的人员信息控件/三个小圆点
    private By  messageBtn =  new By.ById("i6d");
    // 编辑人员按钮
    private MobileBy editMemberBtn = new MobileBy.ByAndroidUIAutomator("new UiSelector().text(\"编辑成员\")");
    // 个人信息中座机号码控件
    private By telephoneMessage = new By.ById("b5m");
    // 搜索结果页面
    private By searchResult = new By.ById("gqx");
    // 选择添加成员方式页面的回退按钮
    private By goBackBtn = new By.ById("i63");
    /**
     * 添加成员
     * @param name 姓名
     * @param phone 手机
     * @return
     */
    public MailListPage addMemberSuccess(String name,String phone){
        // 点击添加成员
        clickElement(addMemberBtn);
        // 点击手动添加
        clickElement(manualAddMemberBtn);
        // 在添加成员页面输入数据
        AddAndEditMemberPage addMemberPage = new AddAndEditMemberPage(driver);
        addMemberPage.sendUsername(name);
        addMemberPage.sendPhone(phone);
        addMemberPage.setDepartment();
        addMemberPage.save();
        clickElement(goBackBtn);
        return this;
    }

    /**
     * 搜索成员
     * @param userMessage 成员信息
     * @return 搜索结果页面
     */
    public MailListPage searchMember(String userMessage){
        // 点击搜索按钮
        clickElement(searchBtn);
        // 在搜索框中输入内容
        sendKey(searchInput,userMessage);
        return this;
    }

    /**
     * 搜索并编辑人员 暂时只编辑座机
     * @param username 成员名
     * @param telephone 座机号
     * @return
     */
    public MailListPage searchAndEditMember(String username,String telephone){
        searchMember(username);
        // 点击搜索结果
        clickElement(MobileBy.xpath("//*[@resource-id='com.tencent.wework:id/gqx']/descendant::*[@text='"+username+"']"));
        clickElement(messageBtn);
        // 点击编辑人员按钮
        clickElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"编辑成员\")"));
        // 在编辑人员页面 设置座机和部门后点击确定
        AddAndEditMemberPage editMemberPage = new AddAndEditMemberPage(driver);
        editMemberPage.sendTelephone(telephone);
        editMemberPage.setDepartment();
        editMemberPage.save();
        return this;
    }

    public String getEditResult(){
        return waitElementVisible(telephoneMessage).getText();
    }


    public MailListPage deleteMember(String memberName){
        // 在通讯录中点击相关成员
        clickElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\""+memberName+"\")"));
        // 点击右上角
        clickElement(messageBtn);
        // 点击编辑成员
        clickElement(editMemberBtn);
        AddAndEditMemberPage editMemberPage = new AddAndEditMemberPage(driver);
        // 向下滑动
        swipeDown();
        editMemberPage.deleteMember();
        return this;
    }

    public String getSearchResult(String userMessage) throws InterruptedException {
        MailListPage mailListPage = searchMember(userMessage);
        Thread.sleep(2000);
        return driver.getPageSource();
    }


}
