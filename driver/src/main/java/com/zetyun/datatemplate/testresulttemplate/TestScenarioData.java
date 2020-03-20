package com.zetyun.datatemplate.testresulttemplate;

import java.util.ArrayList;
import java.util.List;

public class TestScenarioData {
    //场景名称
    private String scenarioname;
    //场景作者
    private String scenarioauthor;
    //场景优先级
    private String scenariopriority;
    //场景概述
    private String scenariooverview;
    //场景详细描述
    private String scenariodesc;
    //场景步骤
    private List<TestStepData> testSteps;
    //场景执行结果
    private TestResult scenarioresult;

    private String ScreenPath;

    //默认静态数据
    public static String Priority1 = "Priority1";
    public static String Priority2 = "Priority2";
    public static String Priority3 = "Priority3";
    public static String Passed = "Passed";
    public static String Failed = "Failed";
    public static String Unexpected = "UnexpectedError";

    //默认构造函数
    public TestScenarioData() {
        scenarioname = "";
        scenarioauthor = "default";
        scenariopriority = Priority1;
        scenariooverview = "";
        scenariodesc = "";
        scenarioresult = TestResult.PASS;
        testSteps = new ArrayList<>();
    }

    public void setScenarioname(String scenarioname) {
        this.scenarioname = scenarioname;
    }

    public String getScenarioname() {
        return scenarioname;
    }

    public String getScenariooverview() {
        return scenariooverview;
    }

    public void setScenariooverview(String scenariooverview) {
        this.scenariooverview = scenariooverview;
    }

    public void setScenariodesc(String scenariodesc) {
        this.scenariodesc = scenariodesc;
    }

    public String getScenariodesc() {
        return scenariodesc;
    }

    public void setScenarioauthor(String scenarinauthor) {
        this.scenarioauthor = scenarinauthor;
    }

    public String getScenarioauthor() {
        return scenarioauthor;
    }



    public void setScenariopriority(String scenariopriority) {
        this.scenariopriority = scenariopriority;
    }

    public String getScenariopriority() {
        return scenariopriority;
    }

    public String getScreenPath() {
        return ScreenPath;
    }

    public void addTestStep(TestStepData testStep) {
        this.testSteps.add(testStep);
    }

    public List<TestStepData> getTestSteps() {
        return this.testSteps;
    }

    public void setScenarioResult(TestResult scenarioresult) {
        this.scenarioresult = scenarioresult;
    }

    public TestResult getScenarioResult() {
        return scenarioresult;
    }

    public void setScreenPath(String screenPath) {
        ScreenPath = screenPath;
    }
}
