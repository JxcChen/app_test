package com.appium.testcase;

import com.appium.data.TestData;
import com.appium.page.MailListPage;
import com.appium.page.MainPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.provider.Arguments;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class BaseTest {
    public static MainPage mainPage;


    @AfterAll
    public static void teardown(){
        mainPage.quit();
    }



    /**
     * 获取用例数据
     * @param searchMethod 方法名
     * @return 数据
     */
    public static Stream<Arguments> getCaseData(String searchMethod){
        List<Arguments> list = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            TestData testData = mapper.readValue(new File("src\\main\\resources\\data.yaml"), TestData.class);
            if(searchMethod.equals("search") || searchMethod.equals("addMember")){
                testData.method.forEach((key,value) -> {
                    if(key.equals(searchMethod)){
                        value.forEach((param1,param2) ->{
                            Arguments arguments = arguments(param1, param2);
                            list.add(arguments);
                        });
                    }
                });
            }else if(searchMethod.equals("deleteMember")){
                testData.deleteMember.forEach(memberName -> {
                    list.add(arguments(memberName));
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list.stream();
    }
}
