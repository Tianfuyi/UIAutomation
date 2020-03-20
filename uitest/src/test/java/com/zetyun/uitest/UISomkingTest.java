package com.zetyun.uitest;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Somking test
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        monochrome = true,
       // format = {"junit:reports/junit/junit.xml", "html:reports/html", "pretty"},      //指定cucumber输出文件格式
        features = {"classpath:Features"},                                              //指定一组feature文件
        //features = {"classpath:Features/testcase.feature"},                           //指定某个具体的feature文件
        glue = {"com.zetyun.uitest.steps"},                                            //指定场景Step文件路径
        tags = "@WebUISomkingTest")
public class UISomkingTest {

}
