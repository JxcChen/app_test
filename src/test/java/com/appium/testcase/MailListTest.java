package com.appium.testcase;

import com.appium.page.MailListPage;
import com.appium.page.MainPage;
import io.appium.java_client.android.Activity;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MailListTest {
    public static MainPage mainPage;
    public static MailListPage mailListPage;
    @BeforeAll
    public static void setup(){
        mainPage = new MainPage();
        mailListPage = mainPage.getMailListPage();
        // todo:清除数据
    }

    @AfterAll
    public static void teardown(){
        mainPage.quit();
    }

    @BeforeEach
    public void pageReset(){
        // 重置页面
        mailListPage.pageReset();
    }

    // todo:添加成员测试
    @ParameterizedTest
    @MethodSource("addMemberData")
    @Order(1)
    void addMemberTest(String name,String phone){
        MailListPage successPage = mailListPage.addMemberSuccess(name, phone);
        String mesg = successPage.searchAndGetSearchResult(name);
        assertTrue(mesg.contains(name));
    }

    // todo:搜索测试
    @ParameterizedTest
    @MethodSource("searchData")
    @Order(2)
    void searchTest(String searchMessage,String exceptResult){
        assertTrue(mailListPage.searchAndGetSearchResult(searchMessage).contains(exceptResult));
    }

    // todo:搜索并编辑编辑人员
    @Test
    @Order(4)
    void searchAndEditMemTest(){
        MailListPage editSuccessPage = mailListPage.searchAndEditMember("王力宏", "25555577");
        assertEquals("25555577",editSuccessPage.getEditResult());
    }

    // todo:删除人员
    @ParameterizedTest
    @MethodSource("deleteMemberData")
    @Order(3)
    void deleteMemTest(String name){
        MailListPage deleteSuccessPage = mailListPage.deleteMember(name);
        String mesg = deleteSuccessPage.searchAndGetSearchResult(name);
        assertTrue(mesg.contains("无搜索结果"));
    }


    // todo:添加部门
    @Test
    @Order(5)
    void addDepartment(){
        assertTrue(mailListPage.addDepartmentSuccess("产品部").searchAndGetSearchResult("产品部").contains("产品部"));
    }

    // todo:删除部门
    @Test
    @Order(6)
    void deleteDepartment(){
        assertTrue(mailListPage.deleteDepartment("产品部").searchAndGetSearchResult("产品部").contains("无搜索结果"));
    }



    // 添加成员使用的数据
    public static Stream<Arguments> addMemberData() {
        return Stream.of(
                Arguments.arguments("周杰伦", "12121221211"),
                Arguments.arguments("林俊杰", "12121221212"),
                Arguments.arguments("王力宏", "12121221213"));
    }

    // 删除成员使用的数据
    public static Stream<Arguments> deleteMemberData() {
        return Stream.of(
                Arguments.arguments("周杰伦"),
                Arguments.arguments("林俊杰"),
                Arguments.arguments("王力宏"));
    }

    // 搜索测试数据
    public static Stream<Arguments> searchData(){
        return Stream.of(
                Arguments.arguments("周","周杰伦"),
                Arguments.arguments("周杰","周杰伦"),
                Arguments.arguments("杰伦","周杰伦"),
                Arguments.arguments("周杰伦","周杰伦"),
                Arguments.arguments("周伦","无搜索结果"),
                Arguments.arguments("周劫伦","无搜索结果")
        );
    }

}
