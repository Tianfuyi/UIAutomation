package com.zetyun.datatemplate.scenariotemplate;

import com.zetyun.datatemplate.basetemplate.BaseTemplate;
import com.zetyun.driver.excel.ExcelParse;
import com.zetyun.driver.log.LogWriter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ScenarioDataTemplate extends BaseTemplate {
    /**
     * 获取指定场景名称的场景内容
     *
     * @param filepath
     * @param sheetname
     * @param scenarioname
     * @return
     */
    public static ScenarioTemplate getScenario(String filepath, String sheetname, String scenarioname) {
        try {
            LogWriter.debug(ScenarioDataTemplate.class, "Begin to parse excel");
            List<ScenarioTemplate> scenarioTemplates = getScenarios(filepath, sheetname);
            LogWriter.debug(ScenarioDataTemplate.class, "Get scenario templates form excel success");

            if (scenarioTemplates.size() != 0) {
                for (ScenarioTemplate scenarioTemplate :
                        scenarioTemplates) {
                    if (scenarioTemplate.getScenarioname().equals(scenarioname)) {
                        LogWriter.debug(ScenarioDataTemplate.class, "Find scenario by " + scenarioname);
                        return scenarioTemplate;
                    }
                }

                LogWriter.debug(ScenarioDataTemplate.class, "Don't find scenario, name = " + scenarioname);
                return null;
            } else {
                LogWriter.debug(ScenarioDataTemplate.class, "Don't find scenario, name = " + scenarioname);
                return null;
            }
        } catch (Exception ex) {
            LogWriter.error(ScenarioDataTemplate.class, String.format("get Scenario method execute exception, value = %x", ex.getMessage()));
            return null;
        }
    }

    /**
     * 获取指定sheet name中所有的场景集合
     *
     * @param filepath
     * @param sheetname
     * @return
     */
    public static List<ScenarioTemplate> getScenarios(String filepath, String sheetname) {
        try {
            if (filepath.isEmpty() || sheetname.isEmpty()) {
                return null;
            }

            //声明场景集合
            List<ScenarioTemplate> scenarioTemplates = new ArrayList<>();
            LogWriter.debug(ScenarioDataTemplate.class, "Initialize scenario list success");

            //声明并初始化一个excel文件
            ExcelParse ep = new ExcelParse(filepath);
            LogWriter.debug(ScenarioDataTemplate.class, "Create Workbook Factory success");

            //设置当前工作表格
            if (!ep.setWorkSheet(sheetname)) {
                throw new Exception(String.format("Set Work sheet failed, sheet name = %s", sheetname));
            }

            //获取当前sheet可用总行数
            int rows = ep.getRows();
            LogWriter.debug(ScenarioDataTemplate.class, "get sheet total row number success, value = " + String.valueOf(rows));

            //设置起始查询位置
            int query_r = 2;
            int query_c = 0;

            //开始查询数据
            while (query_r < rows) {
                if (ep.isMerged(query_r, query_c)) {
                    //声明一个场景
                    ScenarioTemplate scenarioTemplate = new ScenarioTemplate();
                    LogWriter.debug(ScenarioDataTemplate.class, "Begin to get scenario information");

                    //获取该Merge相关信息
                    int start_r = ep.getCellStartRow(query_r, query_c);
                    int end_r = ep.getCellEndRow(query_r, query_c);
                    LogWriter.debug(ScenarioDataTemplate.class, "Get Merge basic information success");

                    //获取场景名称
                    scenarioTemplate.setScenarioname(ep.getCellValue(query_r, query_c));
                    LogWriter.debug(ScenarioDataTemplate.class, "Get scenario name success, value = " + ep.getCellValue(query_r, query_c));

                    //获取作者名称
                    query_c += 1;
                    scenarioTemplate.setAuthor(ep.getCellValue(query_r, query_c));
                    LogWriter.debug(ScenarioDataTemplate.class, "Get scenario author success, value = " + ep.getCellValue(query_r, query_c));

                    //获取优先级
                    query_c += 1;
                    scenarioTemplate.setPriority(ep.getCellValue(query_r, query_c));
                    LogWriter.debug(ScenarioDataTemplate.class, "Get scenario priority success, value = " + ep.getCellValue(query_r, query_c));

                    //获取场景概述
                    query_c += 1;
                    scenarioTemplate.setScenariooverview(ep.getCellValue(query_r, query_c));
                    LogWriter.debug(ScenarioDataTemplate.class, "Get scenario overview success, value = " + ep.getCellValue(query_r, query_c));

                    //获取场景详细描述
                    query_c += 1;
                    scenarioTemplate.setScenariodescription(ep.getCellValue(query_r, query_c));
                    LogWriter.debug(ScenarioDataTemplate.class, "Get scenario description success, value = " + ep.getCellValue(query_r, query_c));

                    //获取场景详细步骤
                    query_c += 1;
                    Map<String, StepTemplate> stepTemplateMap = getSteps(ep, start_r, end_r, query_c);
                    if (stepTemplateMap != null) {
                        //设置步骤
                        for (Map.Entry<String, StepTemplate> entry : stepTemplateMap.entrySet()) {
                            scenarioTemplate.setStepTemplateMap(entry.getKey(), entry.getValue());
                            LogWriter.debug(ScenarioDataTemplate.class, "Add a StepTemplate to map");
                        }
                    } else {
                        LogWriter.debug(ScenarioDataTemplate.class, "Do not find StepTemplate");
                    }

                    scenarioTemplates.add(scenarioTemplate);

                    query_r = end_r + 1;
                    query_c = 0;
                } else {
                    query_r += 1;
                }
            }

            return scenarioTemplates;
        } catch (Exception ex) {
            LogWriter.error(ScenarioDataTemplate.class, String.format("get Scenarios method execute exception, value = %s", ex.getMessage()));
            return null;
        }
    }

    /**
     * 获取步骤信息
     *
     * @param ep
     * @param start_r
     * @param end_r
     * @param start_c
     * @return
     */
    private static Map<String, StepTemplate> getSteps(ExcelParse ep, int start_r, int end_r, int start_c) {
        try {
            Map<String, StepTemplate> stepTemplateMap = new LinkedHashMap<>();

            //获取查询的总行数
            int rows = end_r - start_r + 1;

            //获取起始查询位置
            int query_r = start_r;
            int query_c = start_c;

            LogWriter.debug(ScenarioDataTemplate.class, "Begin to parse com.zetyun.apitest.steps");

            //对数据进行遍历
            while (query_r < rows + start_r) {
                if (ep.isMerged(query_r, query_c)) {
                    int start_row = ep.getCellStartRow(query_r, query_c);
                    int end_row = ep.getCellEndRow(query_r, query_c);
                    int end_column = ep.getCellEndColumn(query_r, query_c);
                    LogWriter.debug(ScenarioDataTemplate.class, "Get Merge basic information success");

                    //获取Step
                    String step = ep.getCellValue(query_r, query_c);
                    LogWriter.debug(ScenarioDataTemplate.class, "Get Step value success, value = " + step);

                    //获取步骤信息
                    StepTemplate stepTemplate = getStep(ep, start_row, end_row, end_column + 1);
                    LogWriter.debug(ScenarioDataTemplate.class, "Get StepTemplate value success");

                    //添加一个步骤
                    stepTemplateMap.put(step, stepTemplate);
                    LogWriter.debug(ScenarioDataTemplate.class, "Set StepTemplate success");

                    //增加遍历的起始行数
                    query_r = end_row + 1;
                } else {
                    query_r += 1;
                }
            }

            return stepTemplateMap;
        } catch (Exception ex) {
            LogWriter.error(ScenarioDataTemplate.class, String.format("get Steps method execute exception, value = %x", ex.getMessage()));
            return null;
        }
    }

    /**
     * 获取具体的步骤
     *
     * @param ep
     * @param start_r
     * @param end_r
     * @param start_c
     * @return
     */
    private static StepTemplate getStep(ExcelParse ep, int start_r, int end_r, int start_c) {
        try {
            StepTemplate st = new StepTemplate();
            LogWriter.debug(ScenarioDataTemplate.class, "Begin to parse a single step");

            for (int i = start_r; i <= end_r; i++) {
                String key = ep.getCellValue(i, start_c);
                if (key == null) {
                    continue;
                }

                switch (key) {
                    case "Method":
                        st.setMethod(ep.getCellValue(i, start_c + 1));
                        LogWriter.debug(ScenarioDataTemplate.class, "find method, value = " + st.getMethod());
                        break;
                    case "IsHaveData":
                        st.setIsHavaData(ep.getCellValue(i, start_c + 1));
                        LogWriter.debug(ScenarioDataTemplate.class, "find IsHaveData, value = " + st.getIsHavaData());
                        break;
                    case "Data":
                        st.setData(ep.getCellValue(i, start_c + 1));
                        LogWriter.debug(ScenarioDataTemplate.class, "find Data, value = " + st.getData());
                        break;
                    case "IsHaveMQMessage":
                        st.setIsHaveMQMessage(ep.getCellValue(i, start_c + 1));
                        LogWriter.debug(ScenarioDataTemplate.class, "find IsHaveMQMessage, value = " + st.getIsHaveMQMessage());
                        break;
                    case "IsHaveTransfer":
                        st.setIsHaveTransfer(ep.getCellValue(i, start_c + 1));
                        LogWriter.debug(ScenarioDataTemplate.class, "find IsHaveTransfer, value = " + st.getIsHaveTransfer());
                        break;
                    case "Transfer":
                        st.setTransfer(ep.getCellValue(i, start_c + 1));
                        LogWriter.debug(ScenarioDataTemplate.class, "find Transfer, value = " + st.getTransfer());
                        break;
                    case "IsHaveAssert":
                        st.setIsHaveAssert(ep.getCellValue(i, start_c + 1));
                        LogWriter.debug(ScenarioDataTemplate.class, "find IsHaveAssert, value = " + st.getIsHaveAssert());
                        break;
                    case "AssertConditions":
                        st.setAssertConditions(ep.getCellValue(i, start_c + 1), ep.getCellValue(i, start_c + 2));
                        LogWriter.debug(ScenarioDataTemplate.class, "find AssertConditions, value = [scope=" + ep.getCellValue(i, start_c + 1) +
                                "][value=" + ep.getCellValue(i, start_c + 2) + "]");
                        break;
                }
            }

            LogWriter.debug(ScenarioDataTemplate.class, "Parse a single step success");
            return st;
        } catch (Exception ex) {
            LogWriter.debug(ScenarioDataTemplate.class, "Parse a single step exception, value = " + ex.getMessage());
            return null;
        }
    }
}
