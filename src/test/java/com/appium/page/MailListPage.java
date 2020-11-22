package com.appium.page;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.params.provider.Arguments;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.stream.Stream;

public class MailListPage extends BasePage {


    public MailListPage(AndroidDriver driver) {
        super(driver);
    }

    /*
    公用定位符
     */
    private By goBackBtn = new By.ById("i63");
    // 通讯录右上角进入管理通讯录按钮
    private By goToManageBtn = new By.ById("i6i");
    private MobileBy confirmBtn = new MobileBy.ByAndroidUIAutomator("new UiSelector().text(\"确定\")");

     /*
     搜索相关定位符
      */
    // 搜索按钮
    private By searchBtn = new By.ById("i6n");
    // 搜索输入框
    private MobileBy searchInput =  new MobileBy.ByAndroidUIAutomator("new UiSelector().text(\"搜索\")");
    // 搜索结果框
    private By searchResult = new By.ByXPath("//android.widget.TextView");

    /*
    添加成员相关定位符
     */
    private MobileBy addMemberBtn =  new MobileBy.ByAndroidUIAutomator("new UiSelector().text(\"添加成员\")");
    // 手动添加按钮
    private By manualAddMemberBtn = new By.ById("cox");

    /*
    编辑人员相关定位符
     */
    // 名片右上角的人员信息控件/三个小圆点
    private By  messageBtn =  new By.ById("i6d");
    private MobileBy editMemberBtn = new MobileBy.ByAndroidUIAutomator("new UiSelector().text(\"编辑成员\")");
    // 个人信息中座机号码控件
    private By telephoneMessage = new By.ById("b5m");

    /*
    部门管理相关定位符
     */
    private MobileBy addChildDepartmentBtn = new MobileBy.ByAndroidUIAutomator("new UiSelector().text(\"添加子部门\")");
    private MobileBy departmentNameInput = new MobileBy.ByAndroidUIAutomator("new UiSelector().text(\"请输入部门名称\")");
    private MobileBy extraManageBtn = new MobileBy.ByAndroidUIAutomator("new UiSelector().text(\"更多管理\")");
    private MobileBy deleteDepartmentBtn = new MobileBy.ByAndroidUIAutomator("new UiSelector().text(\"删除部门\")");
    // 右上角的退出按钮
    private By quitManageBtn = new By.ById("i6d");


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

    /**
     * 获取修改成员后的结果信息
     * @return
     */
    public String getEditResult(){
        String result = null;
        try {
            Thread.sleep(3000);
            result = waitElementVisible(telephoneMessage).getText();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 删除成员
     * @param memberName 成员名字
     * @return 通讯录页面
     */
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


    /**
     * 搜索部门并获取结果
     * @param searchMessage 搜索信息
     * @return 搜索结果
     */
    public String searchAndGetSearchResult(String searchMessage){
        MailListPage mailListPage = searchMember(searchMessage);
        StringBuilder result = new StringBuilder();
        try {
            Thread.sleep(3000);
            // 1、获取到搜索结果框中的所有文searchResult本控件
            // 2、再遍历获取到每个文本控件中文本 并存放到变量中返回
            driver.findElements(searchResult).forEach(element -> {
                result.append(((WebElement)element).getText());
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result.toString();
    }


    /**
     * 添加部门
     * @param departmentName 部门名字
     * @return 通讯录页面
     */
    public MailListPage addDepartmentSuccess(String departmentName){
        clickElement(goToManageBtn);
        clickElement(addChildDepartmentBtn);
        sendKey(departmentNameInput,departmentName);
        clickElement(confirmBtn);
        clickElement(quitManageBtn);
        return this;
    }

    /**
     * 删除部门
     * @param departmentName 被删除的部门名
     * @return 删除后的页面
     */
    public MailListPage deleteDepartment(String departmentName){
        clickElement(new MobileBy.ByAndroidUIAutomator("new UiSelector().text(\""+departmentName+"\")"));
        clickElement(goToManageBtn);
        clickElement(extraManageBtn);
        clickElement(deleteDepartmentBtn);
        clickElement(confirmBtn);
        clickElement(quitManageBtn);
        return this;

    }


    /**
     * 将页面退回被测页面
     */
    public void pageReset() {
            String pageSource = driver.getPageSource();
            while (!pageSource.contains("工作台")){
                clickElement(goBackBtn);
                pageSource = driver.getPageSource();
            }
        }





}
