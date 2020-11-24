package com.appium.testcase;

import com.appium.data.TestData;
import com.appium.page.MailListPage;
import com.appium.page.MainPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.appium.java_client.android.Activity;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MailListTest extends BaseTest {
    public static MailListPage mailListPage;

    @BeforeAll
    public static void setup(){
        mainPage = new MainPage();
        mailListPage = mainPage.getMailListPage();
        // todo:清除数据
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
        assertTrue(mailListPage.addMemberSuccess(name, phone).searchAndGetSearchResult(name).contains(name));
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
        assertEquals("25555577",mailListPage.searchAndEditMember("王力宏", "25555577").getEditResult());
    }

    // todo:删除人员
    @ParameterizedTest
    @MethodSource("deleteMemberData")
    @Order(3)
    void deleteMemTest(String name){
        assertTrue(mailListPage.deleteMember(name).searchAndGetSearchResult(name).contains("无搜索结果"));
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



    // 删除成员使用的数据
    public static Stream<Arguments> deleteMemberData() {
        return getCaseData("deleteMember");
    }

    // 搜索测试数据
    public static Stream<Arguments> searchData(){
        return getCaseData("search");
    }

    // 添加成员测试数据
    public static Stream<Arguments> addMemberData(){
        return getCaseData("addMember");
    }
}
