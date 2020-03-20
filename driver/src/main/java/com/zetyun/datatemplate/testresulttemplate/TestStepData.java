package com.zetyun.datatemplate.testresulttemplate;

import java.util.Map;

/**
 * @author Created by Bing
 */
public class TestStepData {
    // Step name
    private String stepName;

    // Step details
    private String stepDetails;

    // Step result
    private TestResult stepResult;

    // Step MQ message
    private String stepMessage;

    // Step HTTP response
    private String stepResponse;

    // Step AssertConditions
    private Map<String, String> stepAssertConditions;

    // Step data
    private String stepData;

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public String getStepName() {
        return this.stepName;
    }

    public void setStepDetails(String stepDetails) {
        this.stepDetails = stepDetails;
    }

    public String getStepDetails() {
        return this.stepDetails;
    }

    public void setStepResult(TestResult testResult) {
        this.stepResult = testResult;
    }

    public TestResult getStepResult() {
        return this.stepResult;
    }

    public void setStepMessage(String stepMessage) {
        this.stepMessage = stepMessage;
    }

    public String getStepMessage() {
        return this.stepMessage;
    }

    public void setStepResponse(String response) {
        this.stepResponse = response;
    }

    public String getStepResponse() {
        return this.stepResponse;
    }

    public void setStepAssertConditions(Map<String, String> assertConditions) {
        this.stepAssertConditions = assertConditions;
    }

    public Map<String, String> getStepAssertConditions() {
        return this.stepAssertConditions;
    }

    public void setStepData(String stepData) {
        this.stepData = stepData;
    }

    public String getStepData() {
        return this.stepData;
    }
}
