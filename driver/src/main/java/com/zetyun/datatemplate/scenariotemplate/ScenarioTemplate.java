package com.zetyun.datatemplate.scenariotemplate;

import java.util.LinkedHashMap;
import java.util.Map;

public class ScenarioTemplate {
    private String scenarioname;
    private String author;
    private String priority;
    private String scenariooverview;
    private String scenariodescription;
    //key 表示是step序号，value表示step template
    private Map<String, StepTemplate> stepTemplateMap;

    public ScenarioTemplate(){
        scenarioname = "";
        author = "";
        priority = "";
        scenariodescription = "";
        stepTemplateMap = new LinkedHashMap<>();
    }

    public String getScenarioname() {
        return scenarioname;
    }

    public void setScenarioname(String scenarioname) {
        this.scenarioname = scenarioname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getScenariooverview() {
        return scenariooverview;
    }

    public void setScenariooverview(String scenariooverview) {
        this.scenariooverview = scenariooverview;
    }

    public String getScenariodescription() {
        return scenariodescription;
    }

    public void setScenariodescription(String scenariodescription) {
        this.scenariodescription = scenariodescription;
    }

    public Map<String, StepTemplate> getStepTemplateMap() {
        return stepTemplateMap;
    }

    public void setStepTemplateMap(String stepnum, StepTemplate stepTemplate) {
        this.stepTemplateMap.put(stepnum, stepTemplate);
    }
}
