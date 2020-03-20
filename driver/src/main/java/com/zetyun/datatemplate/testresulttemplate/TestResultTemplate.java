package com.zetyun.datatemplate.testresulttemplate;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentXReporter;

import java.util.ArrayList;
import java.util.List;

public class TestResultTemplate {
    // ExtentReports
    ExtentXReporter xReporter;
    ExtentReports reports;
    ExtentReportsServerConfig xConfig;

    public TestResultTemplate(ExtentReportsServerConfig config) {
        this.xConfig = config;
        reports = new ExtentReports();
    }

    private List<TestScenarioData> testScenarioDataList = new ArrayList<>();

    public void addTestScenarioData(TestScenarioData testScenarioData) {
        this.testScenarioDataList.add(testScenarioData);
    }

    public void reset() {
        this.testScenarioDataList.clear();
    }

    /**
     * Export testScenarioDataList to ExtentReports server.
     */
    public String export(String projectName) {
        return export(projectName, projectName + "_TestReport");
    }

    /**
     * Export testScenarioDataList to ExtentReports server.
     *
     * @param projectName The project name
     * @param reportName  The report name
     * @return The report id.
     */
    public String export(String projectName, String reportName) {
        try {
            // Set system info.
            reports.setSystemInfo("OS", String.format("%s-%s-%s",
                    System.getProperty("os.name"),
                    System.getProperty("os.arch"),
                    System.getProperty("os.version")));

            // Set xReporter.
            xReporter = new ExtentXReporter(xConfig.getIP(), xConfig.getDbPort());
            xReporter.config().setServerUrl(String.format("http://%s:%d",
                    xConfig.getIP(),
                    xConfig.getWebPort()));
            xReporter.config().setProjectName(projectName);
            xReporter.config().setReportName(reportName);

            // Attach reporters.
            reports.attachReporter(xReporter);

            // Set each scenario.
            for (TestScenarioData testScenarioData : testScenarioDataList) {
                ExtentTest scenario = reports.createTest(testScenarioData.getScenarioname(),
                        testScenarioData.getScenariodesc());
                scenario.assignAuthor(testScenarioData.getScenarioauthor());
                scenario.assignCategory(testScenarioData.getScenariopriority());

                // Set each step.
                for (TestStepData stepData : testScenarioData.getTestSteps()) {
                    ExtentTest step = scenario.createNode(String.format("%s - %s", stepData.getStepName(), stepData.getStepDetails()));
                    switch (stepData.getStepResult()) {
                        case PASS:
                            step.pass("Data:</br>" + stepData.getStepData() + "</br></br>"
                                    + "AssertConditions:</br>" + stepData.getStepAssertConditions() + "</br></br>"
                                    + "Message:</br>" + stepData.getStepMessage() + "</br></br>"
                                    + "Response:</br>" + stepData.getStepResponse());
                            break;
                        case FAIL:
                            step.fail("Data:</br>" + stepData.getStepData() + "</br></br>"
                                    + "AssertConditions:</br>" + stepData.getStepAssertConditions() + "</br></br>"
                                    + "Message:</br>" + stepData.getStepMessage() + "</br></br>"
                                    + "Response:</br>" + stepData.getStepResponse());
                            if (testScenarioData.getScreenPath() != null) {
                                step.fail("details").addScreenCaptureFromPath(testScenarioData.getScreenPath());
                            }
                            break;
                        case EXCEPTION:
                            step.error("Data:</br>" + stepData.getStepData() + "</br></br>"
                                    + "AssertConditions:</br>" + stepData.getStepAssertConditions() + "</br></br>"
                                    + "Message:</br>" + stepData.getStepMessage() + "</br></br>"
                                    + "Response:</br>" + stepData.getStepResponse());
                            break;
                    }
                }

                // Set scenario result.
                if (testScenarioData.getScenarioResult().equals(TestScenarioData.Passed)) {
                    scenario.pass(testScenarioData.getScenariooverview());
                } else if (testScenarioData.getScenarioResult().equals(TestScenarioData.Failed)) {
                    scenario.fail(testScenarioData.getScenariooverview());
                }
            }

            // Flush reports.
            reports.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reports.flush();
        }

        return xReporter.getReportId().toString();
    }
}