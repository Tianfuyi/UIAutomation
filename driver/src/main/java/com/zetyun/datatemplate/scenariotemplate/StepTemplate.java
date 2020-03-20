package com.zetyun.datatemplate.scenariotemplate;

import java.util.LinkedHashMap;
import java.util.Map;

public class StepTemplate {
    //Basic Information
    private String method;
    private String isHavaData;
    private String data;
    //Message Information
    private String isHaveMQMessage;
    //Transfer
    private String isHaveTransfer;
    private String transfer;
    //Assert
    private String isHaveAssert;
    private Map<String, String> assertConditions;

    public StepTemplate(){
        method = "";
        isHavaData = "";
        data = "";
        isHaveMQMessage = "";
        isHaveTransfer = "";
        transfer = "";
        isHaveAssert = "";
        assertConditions = new LinkedHashMap<>();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getIsHavaData() {
        return isHavaData;
    }

    public void setIsHavaData(String isHavaData) {
        this.isHavaData = isHavaData;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getIsHaveMQMessage() {
        return isHaveMQMessage;
    }

    public void setIsHaveMQMessage(String isHaveMQMessage) {
        this.isHaveMQMessage = isHaveMQMessage;
    }

    public String getIsHaveTransfer() {
        return isHaveTransfer;
    }

    public void setIsHaveTransfer(String isHaveTransfer) {
        this.isHaveTransfer = isHaveTransfer;
    }

    public String getTransfer() {
        return transfer;
    }

    public void setTransfer(String transfer) {
        this.transfer = transfer;
    }

    public String getIsHaveAssert() {
        return isHaveAssert;
    }

    public void setIsHaveAssert(String isHaveAssert) {
        this.isHaveAssert = isHaveAssert;
    }

    public Map<String, String> getAssertConditions() {
        return assertConditions;
    }

    public void setAssertConditions(String scope, String value){
        assertConditions.put(scope, value);
    }
}
