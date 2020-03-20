package com.zetyun.uitest.steps;

import com.zetyun.data.DataParse;
import com.zetyun.datatemplate.elementtemplate.ElementTemplate;
import com.zetyun.datatemplate.scenariotemplate.ScenarioDataTemplate;
import com.zetyun.datatemplate.scenariotemplate.ScenarioTemplate;
import com.zetyun.datatemplate.scenariotemplate.StepTemplate;
import com.zetyun.datatemplate.testresulttemplate.*;
import com.zetyun.driver.compare.AssertAdapt;
import com.zetyun.driver.log.LogWriter;
import com.zetyun.driver.string.StringParse;
import com.zetyun.driver.string.TransferModel;
import com.zetyun.uitest.delivery.Delivery;
import com.zetyun.uitest.pageoperation.common.InitBrowser;
import com.zetyun.uitest.utility.ToolKit;
import cucumber.api.java.zh_cn.假如;
import cucumber.api.java.zh_cn.当;
import cucumber.api.java.zh_cn.那么;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

public class Selenium_Excute {
    //存放场景数据
    private List<ScenarioTemplate> scenarioTemplates;
    //存放场景基本配置信息
    private Map<String, Map<String, String>> scenarioConfig;
    //存放全局基本配置信息
    private Map<String, Map<String, String>> globeConfig;
    WebDriver driver ;
    String idName;
    // AssertConditions
    private Map<String, String> assertConditions;
    // Test result template
    TestResultTemplate testResultTemplate;
    // Store scenario test result data
    private TestScenarioData testScenarioData;

    // ExtentReports server config
    ExtentReportsServerConfig xConfig;

    @假如("^使用谷歌浏览器进行登陆$")
    public void initBrowser() throws Throwable {

    }

    @当("^^执行测试场景:\"([^\"]*)\"$")
    public void excuteSence(String page) throws Throwable{
        boolean scenarioFlag=true;
        String data_scennarios =DataParse.GetProperties("UISceneDataTemplate");


        Map<String, Map<String, String>> basicConfig = ScenarioDataTemplate.getValues( DataParse.GetProperties("UIBasicConfigPath"), "基本配置");
       // ExtentX Server 配置
        Map<String, String> extentReportsConfigMap = basicConfig.get("ExtentReportsServer");
        xConfig = new ExtentReportsServerConfig(
                extentReportsConfigMap.get("IP"),
                Double.valueOf(extentReportsConfigMap.get("DBPORT")).intValue(),
                Double.valueOf(extentReportsConfigMap.get("WEBPORT")).intValue());
        testResultTemplate = new TestResultTemplate(xConfig);

        boolean Scenario_flag=true;
        TestStepData testStepData ;

              // 获取场景数据信息
            scenarioTemplates = ScenarioDataTemplate.getScenarios(data_scennarios, "场景数据");

            if(scenarioTemplates.size()==0){
                Scenario_flag=false;
                Assert.assertTrue(Scenario_flag);

            }

            //获取场景基本配置信息
            for (ScenarioTemplate scenarioTemplate:scenarioTemplates) {

                try {
                    String basic_path = DataParse.GetProperties("UIBasicConfigPath");
                    String url = ElementTemplate.getValues(basic_path, "基本配置").get("Basic").get("URL");

                    driver = InitBrowser.openBroser(driver, url);
                }catch (Exception e){

                }
                    testScenarioData = new TestScenarioData();
                    testScenarioData.setScenarioauthor(scenarioTemplate.getAuthor());
                    testScenarioData.setScenariodesc(scenarioTemplate.getScenariodescription());
                    testScenarioData.setScenarioname(scenarioTemplate.getScenarioname());
                    testScenarioData.setScenariooverview(scenarioTemplate.getScenariooverview());
                    testScenarioData.setScenariopriority(scenarioTemplate.getPriority());

                    idName = scenarioTemplate.getScenarioname(); // 场景名称
                    Map<String, StepTemplate> stepTemplates = scenarioTemplate.getStepTemplateMap();
                    for (Map.Entry<String, StepTemplate> entry : stepTemplates.entrySet()) {
                        String screanPath;
                        Scenario_flag=true;
                        testStepData = new TestStepData();
                        String step=entry.getKey();
                        testStepData.setStepName(step);  //step
                        String name=entry.getValue().getMethod();
                        testStepData.setStepDetails(name);
                        testStepData.setStepData(entry.getKey());

                        String method = entry.getValue().getMethod();   // 执行方法
                        String isHaveData = entry.getValue().getIsHavaData();
                        String data = entry.getValue().getData();
                        testStepData.setStepData(data);   // 测试数据

                        LogWriter.info(Selenium_Excute.class, "***执行步骤 " + method + "***");

                        try {
                            Object returnMsg=  Delivery.deliver(driver, method, isHaveData, data);   //方法分发
                            System.out.println(returnMsg);

                            String ishaveAssert = entry.getValue().getIsHaveAssert();
                            if (ishaveAssert != null) {
                                if (ishaveAssert.equals("Yes")) {
                                assertConditions = entry.getValue().getAssertConditions();
                                    for (Map.Entry<String, String> entryAssert : assertConditions.entrySet()) {

                                        System.out.println(entryAssert.getKey());
                                        System.out.println(entryAssert.getValue());
                                        if(entryAssert.getKey().equals("UIMessage")){
                                         String expectResult=entryAssert.getValue();
                                            Scenario_flag=   new AssertAdapt().compare(expectResult,returnMsg.toString());
                                        }

                                    }
                                }
                            }
                        }catch (Exception e){
                     //     e.printStackTrace();
                            Scenario_flag=false;
                             screanPath=  ToolKit.TakeScreenShot(driver,idName);   // 失败截图
                            LogWriter.error(Selenium_Excute.class,"测试用例执行失败截图："+screanPath);
                          //  testStepData = new TestStepData();
                            testStepData.setStepResult(TestResult.FAIL); //step结果
                            testScenarioData.setScenarioResult(TestResult.FAIL);
                            driver.quit();
                            testScenarioData.setScreenPath(screanPath);
                            StringWriter sw = new StringWriter();
                            PrintWriter pw= new PrintWriter(sw);
                            e.printStackTrace(pw);
                            String msg =sw.toString();
                            System.out.println(msg);
                            testStepData.setStepMessage(msg);
                            testScenarioData.addTestStep(testStepData);
                          //  testResultTemplate.addTestScenarioData(testScenarioData);

                        }
                        if (Scenario_flag==false){
                            break;
                        }

                        testStepData.setStepResult(TestResult.PASS);  // 添加step结果
                        testScenarioData.addTestStep(testStepData);  // 添加步骤
                    }

                    if(Scenario_flag==true) {
                        driver.quit();
                    }
                    // Set scenario result
                    if (Scenario_flag) {
                        testScenarioData.setScenarioResult(TestResult.PASS);
                    } else {
                        testScenarioData.setScenarioResult(TestResult.FAIL);
                    }
                testResultTemplate.addTestScenarioData(testScenarioData);
                }
    }

    @那么("^场景执行完毕关闭浏览器$")
    public void WebDriver_Login_Verify() throws Throwable {
        LogWriter.info(Selenium_Excute.class,"***ExtentX写入执行结果***");
        testResultTemplate.export("UIAutomation");
    }



}
