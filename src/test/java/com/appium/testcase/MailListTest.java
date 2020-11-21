package com.appium.testcase;

import com.appium.page.MailListPage;
import com.appium.page.MainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MailListTest {

    // todo:添加成员测试
    @Test
    void addMemberTest(){
        MainPage mainPage = new MainPage();
        MailListPage mailListPage = mainPage.getMailListPage();
        MailListPage successPage = mailListPage.addMemberSuccess("周杰伦", "13252426512");
        try {
            String mesg = successPage.getSearchResult("周杰");
            Assertions.assertTrue(mesg.contains("周杰伦"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // todo:搜索并编辑编辑人员
    @Test
    void searchAndEditMemTest(){
        MainPage mainPage = new MainPage();
        MailListPage mailListPage = mainPage.getMailListPage();
        MailListPage editSuccessPage = mailListPage.searchAndEditMember("王力宏", "25555555");
        Assertions.assertEquals("25555555",editSuccessPage.getEditResult());
    }

    // todo:删除人员
    @Test
    void deleteMemTest(){
        MainPage mainPage = new MainPage();
        MailListPage mailListPage = mainPage.getMailListPage();
        MailListPage deleteSuccessPage = mailListPage.deleteMember("周杰伦");
        try {
            String mesg = deleteSuccessPage.getSearchResult("周杰伦");
            Assertions.assertTrue(mesg.contains("无搜索结果"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
