package com.zetyun.uitest.pageoperation;

import com.zetyun.data.DataParse;
import com.zetyun.datatemplate.elementtemplate.ElementTemplate;
import com.zetyun.driver.log.LogWriter;
import com.zetyun.uitest.utility.DynamicElementUtil;
import com.zetyun.uitest.utility.ElementUtil;
import com.zetyun.uitest.utility.JsonUtil;
import com.zetyun.uitest.utility.ToolKit;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据应用
 */
public class DataApplication {
    /**
     * 任务状态校验
     *  等待时间单位为分钟
     * @param driver
     * @param data
     * @throws Exception
    {"状态":"运行/已终止/失败/成功",
    "等待时间":"1200"}
     */
    public void checkDataAppTaskStatus(WebDriver driver, String data) throws Exception {
        LogWriter.info(DataApplication.class,"任务状态校验");
        Map map= new JsonUtil().jsonToMaps(data);
        String  taskName =  map.get("状态").toString();
        String  time =  map.get("等待时间").toString();
        int waittime = Integer.parseInt(time);
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "DataApp");
        Map<String, String> map1 = elementConfig.get("状态");
        String status= map1.get(taskName);
        ToolKit.waitForWebElementVisible(driver,ElementUtil.findElement(driver,status,waittime),waittime);
        Assert.assertTrue(ElementUtil.findElement(driver,status).isDisplayed());


    }

    /**
     * 选择左侧模块类型
     * @param driver
     * @param name
     * @throws Exception
     */
    private void clickMoudletab(WebDriver driver,String name) throws Exception {
    new DynamicElementUtil(driver).getmoduleTab(name).click();
    }

    /**
     * 查询模块名称
     * @param driver
     * @param data
     * @throws Exception
     {
    "模块类型":"分析模块",
    "模块名称":""
    }
     */
    public void searchMoule(WebDriver driver,String data) throws Exception{
        LogWriter.info(DataApplication.class,"查询模块");
        Map map= new JsonUtil().jsonToMaps(data);
        String  moduletype =  map.get("模块类型").toString();
        String  modulename =  map.get("模块名称").toString();
        clickMoudletab(driver,moduletype);  // 选择模块
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "DataApp");
        Map<String, String> map1 = elementConfig.get("工作流");
        WebElement element= ElementUtil.findElement(driver, map1.get("模块查询输入框"));
        element.clear();
        element.sendKeys(modulename);

        List<WebElement> list = ElementUtil.findElements(driver,map1.get("模块名称"));
        for(WebElement e:list){
            Assert.assertTrue(e.getText().toLowerCase().contains(modulename.toLowerCase())); // 遍历查询结果符合查询条件
        }

    }

    /**
     * 验证模块名称
     * 验证查看数据
     * @param driver
     * @param data
     * @throws Exception
    {"数据模块名称":""}
     */
    public void checkDataModuledetail(WebDriver driver,String data) throws Exception{
        LogWriter.info(DataApplication.class,"验证 数据模块详情及查看数据");
        Map map= new JsonUtil().jsonToMaps(data);
       String moduename = map.get("数据模块名称").toString();
        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.getDataModuleByNameUnderEdit(moduename).click(); // 点击

        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "DataApp");
        Map<String, String> map1 = elementConfig.get("工作流");
        String name = ElementUtil.findElement(driver,map1.get("模块详情名称")).getText();// 模块详情显示模块名称
        Assert.assertTrue(name.contains(moduename));

        ElementUtil.findElement(driver,map1.get("查看数据")).click();  // 点击查看数据
        Assert.assertTrue(ElementUtil.findElement(driver,map1.get("数据预览"),180).isDisplayed());// 数据预览显示数据
    }


    /**
     * @param driver
     * @param data
    {"验证":"复制/编辑/权限"}
     */
    public void checkMore(WebDriver driver,String data) throws IOException {
        LogWriter.info(DataApplication.class,"更多功能验证"+data);
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "DataApp");
        Map<String, String> map1 = elementConfig.get("工作流");
        Map map= new JsonUtil().jsonToMaps(data);
       String name= map.get("验证").toString();
       ElementUtil.findElement(driver,map1.get("更多")).click();
       switch (name){
           case "复制":
               ElementUtil.findElement(driver,map1.get("复制应用")).click();
               Assert.assertTrue(ElementUtil.findElement(driver,map1.get("应用名称输入框")).isDisplayed());
               break;

           case "编辑":
               ElementUtil.findElement(driver,map1.get("编辑应用")).click();
               Assert.assertTrue(ElementUtil.findElement(driver,map1.get("应用名称输入框")).isDisplayed());
               break;

           case "权限":
               ElementUtil.findElement(driver,map1.get("权限设置")).click();
               Assert.assertTrue(ElementUtil.findElement(driver,map1.get("权限设置面板")).isDisplayed());
               break;

       }

    }
    /**
     * 点击工作流页面运行按钮
     * @param driver
     * @param data
    {
    "clickDataAppRun": {
    "运行名称": "autotest123456",
    "运行描述": "autoTest"
    }
    }
     * @throws Exception
     */
    public String clickDataAppRunOnce(WebDriver driver,String data) throws Exception{

        Map map= new JsonUtil().jsonToMaps(data);
        String  runName =  map.get("运行名称").toString();
        String  runDesc =  map.get("运行描述").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String runBtn = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("运行");
        String nameStr = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("运行名称");
        String nameClean = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("名称清空");
        String descStr = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("运行描述");
        String handStr = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("手动模式");
        String runSubmit = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("运行执行");

        ElementUtil.findElement(driver,runBtn).click();
        if (!runName.isEmpty()){
            ElementUtil.findElement(driver,nameClean).click();
            ElementUtil.findElement(driver,nameStr).sendKeys(runName);
        }
        if (!runDesc.isEmpty()){
            ElementUtil.findElement(driver,descStr).sendKeys(runDesc);
        }

        ElementUtil.findElement(driver,handStr).click();
        String nameStrEnd = ElementUtil.findElement(driver,nameStr).getAttribute("value");
        ElementUtil.findElement(driver,runSubmit).click();
        return  nameStrEnd;

    }
    /**
     * 点击工作流页面运行按钮
     * @param driver
     * @param data
    {

    "clickDataAppRunOnce": {
    "运行名称": "",
    "运行描述": "autoTest"
    }
    }
     * @throws Exception
     */
    public void DataAppRunOnce(WebDriver driver,String data)throws Exception{
        String appName = clickDataAppRunOnce(driver,data);

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String runVerify = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("运行验证");
        String runres = ElementUtil.findElement(driver,runVerify).getText();
        Assert.assertTrue(runres.contains(appName));
    }
    /**
     * 点击工作流页面运行按钮
     * @param driver
     * @param data
    {
    "clickDataAppRunLoop": {
    "运行名称": "",
    "运行描述": "autoTest",
    "计划周期": "1",
    "开始日期": "0",
    "过期时间": "一直运行/完成1次/0"
    }
    }
     * @throws Exception
     */
    public Map<String, String> clickDataAppRunLoop(WebDriver driver, String data) throws Exception{
        Map<String, String> resMap = new HashMap<String, String>();


        Map map= new JsonUtil().jsonToMaps(data);
        String  runName =  map.get("运行名称").toString();
        String  runDesc =  map.get("运行描述").toString();
        String  planTime =  map.get("计划周期").toString();
        String  beginTime =  map.get("开始日期").toString();
        String  endTime =  map.get("过期时间").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String runBtn = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("运行");
        String nameStr = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("运行名称");
        String nameClean = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("名称清空");
        String descStr = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("运行描述");
        String loopStr = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("定期运行");
        String planStr = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("计划周期");
        String beginStr = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("开始日期");
        String calendarStr = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("日历选择时间");
        String calConfirm = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("确定时间");
        String runStr = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("一直运行");
        String finishStr = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("完成几次");
        String finishTimes = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("完成几次");
        String endStr = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("过期时间");
        String endTimeStr = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("过期日期");
        String runSubmit = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("运行执行");

        ElementUtil.findElement(driver,runBtn).click();
        if (!runName.isEmpty()){
            ElementUtil.findElement(driver,nameClean).click();
            ElementUtil.findElement(driver,nameStr).sendKeys(runName);
        }
        if (!runDesc.isEmpty()){
            ElementUtil.findElement(driver,descStr).sendKeys(runDesc);
        }

        ElementUtil.findElement(driver,loopStr).click();
        //计划周期，每日几次
        ElementUtil.findElement(driver,planStr).sendKeys(planTime);
        //开始日期，选择第二天
        ElementUtil.findElement(driver,beginStr).click();
        int i = Integer.valueOf(beginTime);
        ElementUtil.findElements(driver,calendarStr).get(i).click();
        ElementUtil.findElement(driver,calConfirm).click();
        //过期日期
        if (endTime.equals("一直运行")){
            ElementUtil.findElement(driver,runStr).click();
        }
        if (endTime.contains("完成")){
            ElementUtil.findElement(driver,finishStr).click();
            String regEx="[^0-9]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(endTime);
            String times =  m.replaceAll("").trim();
            ElementUtil.findElement(driver,finishTimes).sendKeys(times);
        }
        if (endTime.isEmpty()){
            ElementUtil.findElement(driver,endStr).click();
            ElementUtil.findElements(driver,calendarStr).get(0).click();
            ElementUtil.findElement(driver,calConfirm).click();

        }
        String startTimeT = ElementUtil.findElement(driver,beginStr).getAttribute("value");
        String endTimeT = ElementUtil.findElement(driver,endTimeStr).getAttribute("value");
        String nameT = ElementUtil.findElement(driver,nameStr).getAttribute("value");
        resMap.put("appName",nameT);
        resMap.put("startTime",startTimeT);
        resMap.put("endTime",endTimeT);


        ElementUtil.findElement(driver,runSubmit).click();

        return resMap;

    }
    /**
     * 点击工作流页面运行按钮-周期运行
     * @param driver
     * @param data
    {
    "clickDataAppRunLoop": {
    "运行名称": "autotest123456",
    "运行描述": "autoTest",
    "计划周期": "1",
    "开始日期": "0",
    "过期时间": "一直运行/完成1次/0"
    }
    }
     * @throws Exception
     */
    public void DataAppRunLoop(WebDriver driver,String data)throws Exception{
        Map<String, String> resValue = clickDataAppRunLoop(driver,data);

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String runVerify = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("运行验证");
        String startVerify = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("开始时间验证");
        String endVerify = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("结束时间验证");
        String appName = resValue.get("appName");
        String startTime = resValue.get("startTime");
        String endTime = resValue.get("endTime");
        String runNameStr = ElementUtil.findElement(driver,runVerify).getText();
        String startTimeStr = ElementUtil.findElement(driver,startVerify).getText();
        String endTimeStr = ElementUtil.findElement(driver,endVerify).getText();

        boolean a1 = runNameStr.contains(appName);
        boolean a2 = startTimeStr.equals(startTime);
        boolean a3 = endTimeStr.contains(endTime);
        Assert.assertTrue(a1&&a2&&a3);


    }
    /**
     * 点击工作流页面历史任务按钮
     * @param driver
    {
    "clickDataAppTaskHistory": {
    "工作流名称": "test_0001_dtype"
    }
    }
     * @throws Exception
     */
    public void clickDataAppTaskHistory(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  runName =  map.get("工作流名称").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String runHisBtn = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("运行历史");
        String taskHisBtn = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("历史任务");
        String planHisVerify = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("历史验证");
        ElementUtil.findElement(driver,runHisBtn).click();
        ElementUtil.findElement(driver,taskHisBtn).click();
        String taskName = ElementUtil.findElements(driver,planHisVerify).get(0).getText();
        Assert.assertTrue(taskName.equals(runName));


    }
    /**
     * 点击工作流页面历史计划按钮
     * @param driver
    {
    "clickDataAppPlanHistory": {
    "工作流名称": "test_0001_dtype"
    }
    }
     * @throws Exception
     */
    public void clickDataAppPlanHistory(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  runName =  map.get("工作流名称").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String runHisBtn = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("运行历史");
        String planHisBtn = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("历史计划");
        String planHisVerify = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("历史验证");
        ElementUtil.findElement(driver,runHisBtn).click();
        ElementUtil.findElement(driver,planHisBtn).click();
        String taskName = ElementUtil.findElements(driver,planHisVerify).get(0).getText();
        Assert.assertTrue(taskName.equals(runName));


    }

    /**
     * 点击工作流页面模型评估按钮
     * @param driver
     * @param data
    {
    "clickDataAppModelEva": {
    "工作流名称": "test_0001_dtype"
    }
    }
     * @throws Exception
     */
    public void clickDataAppModelEva(WebDriver driver,String data) throws Exception{
        boolean finded = false;
        Map map= new JsonUtil().jsonToMaps(data);
        String  runName =  map.get("工作流名称").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String modelEva = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("模型评估");
        String modelVerify = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("面包屑导航");

        ElementUtil.findElement(driver,modelEva).click();
        List<WebElement> es = ElementUtil.findElements(driver,modelVerify);
        for (int i = 0;i<es.size();i++){
            String verify = es.get(i).getText();
            if (verify.contains(runName)){
                finded = true;
                break;
            }
        }
        Assert.assertTrue(finded);


    }
    /**
     * 点击工作流页面历史版本按钮
     * @param driver
     * @throws Exception
     */
    public void clickDataAppVerHistory(WebDriver driver) throws Exception{
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String verHistory = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("历史版本");
        String hisHead = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("历史版本表头");
        String hisClose = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("历史版本关闭");
        ElementUtil.findElement(driver,verHistory).click();
        Assert.assertTrue(ElementUtil.findElement(driver,hisHead).isDisplayed());
        ElementUtil.findElement(driver,hisClose).click();

    }
    /**
     * 删除指定版本
     * @param driver
    {
    "delDataAppVer": {
    "版本号": "0.60"
    }
    }
     * @throws Exception
     */
    public void delDataAppVer(WebDriver driver,String data)throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  verNum =  map.get("版本号").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String hisVer = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("历史版本");
        String delConfirm = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("删除确定");
        String hisClose = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("历史版本关闭");

        ElementUtil.findElement(driver,hisVer).click();

        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.getDelVerRadio(verNum).click();
        pp.getDelVerBtn(verNum).click();
        ElementUtil.findElement(driver,delConfirm).click();

        ElementUtil.findElement(driver,hisClose).click();

    }
    /**
     * 切换指定版本
     * @param driver
    {
    "switchDataAppVer": {
    "版本号": "0.60"
    }
    }
     * @throws Exception
     */
    public void switchDataAppVer(WebDriver driver,String data)throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  verNum =  map.get("版本号").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String hisClose = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("历史版本关闭");


        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.getDelVerRadio(verNum).click();
        ElementUtil.findElement(driver,hisClose).click();

    }
    /**
     * 点击工作流页面保存按钮
     * @param driver
     *
     * @throws Exception
     */
    public void clickDataAppSave(WebDriver driver) throws Exception{
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String saveBtn = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("保存");

        ElementUtil.findElement(driver,saveBtn).click();


    }
    /**
     * 点击工作流页面提交版本按钮
     * @param driver
     * @throws Exception
     */
    public String clickDataAppVerCommit(WebDriver driver) throws Exception{
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String saveBtn = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("提交版本");
        String versionInfo = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("提交版本");
        String saveConfirm = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("提交确定");

        ElementUtil.findElement(driver,saveBtn).click();
        String verInfo = ElementUtil.findElement(driver,versionInfo).getText();
        ElementUtil.findElement(driver,saveBtn).click();
        return verInfo;

    }
    /**
     * 提交并验证版本信息
     * @param driver
     * @throws Exception
     */
    public void checkVersion(WebDriver driver) throws Exception{

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String versionList = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("版本验证");
        String verInfo = clickDataAppVerCommit(driver);
        clickDataAppVerHistory(driver);
        String verVarify = ElementUtil.findElements(driver,versionList).get(0).getText();
        Assert.assertTrue(verInfo.equals(verVarify));

    }
    /**
     * 添加数据模块注释
     * @param driver
    {
    "addDataModuleComment": {
    "模块名称": "ML_SampleData",
    "注释": "这是个数据模块"
    }
    }
     * @throws Exception
     */
    public void addDataModuleComment(WebDriver driver,String data) throws Exception{

        Map map= new JsonUtil().jsonToMaps(data);
        String  moduleName =  map.get("模块名称").toString();
        String  commentInfo =  map.get("注释").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String comment = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("注释");

        DynamicElementUtil pp = new DynamicElementUtil(driver);
        WebElement ele =pp.getDataModuleByNameUnderEdit(moduleName);
        ToolKit.waitForWebElementClickable(driver,ele);
        ToolKit.highlightWebElement(driver,ele);
        ToolKit.JavaScriptClick(driver,ele);


        ElementUtil.findElement(driver,comment).sendKeys(commentInfo);
        clickDataAppSave(driver);

    }
    /**
     * 验证数据模块注释
     * @param driver
    {
    "checkDataModuleComment": {
    "模块名称": "ML_SampleData",
    "注释": "这是个数据模块"
    }
    }
     * @throws Exception
     */
    public void checkDataModuleComment(WebDriver driver,String data)throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  moduleName =  map.get("模块名称").toString();
        String  commentInfo =  map.get("注释").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String comment = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("注释");

        DynamicElementUtil pp = new DynamicElementUtil(driver);
        WebElement ele =pp.getDataModuleByNameUnderEdit(moduleName);
        ToolKit.waitForWebElementClickable(driver,ele);
        ToolKit.highlightWebElement(driver,ele);
        ToolKit.JavaScriptClick(driver,ele);

        String com = ElementUtil.findElement(driver,comment).getText();
        Assert.assertTrue(commentInfo.equals(com));

    }
    /**
     * 添加分析模块注释
     * @param driver
    {
    "addAnalysisModuleComment": {
    "模块名称":"ML_SampleData",
    "注释":"这是个分析模块"
    }
    }
     * @throws Exception
     */
    public void addAnalysisModuleComment(WebDriver driver,String data) throws Exception{

        Map map= new JsonUtil().jsonToMaps(data);
        String  moduleName =  map.get("模块名称").toString();
        String  commentInfo =  map.get("注释").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String comment = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("注释");

        DynamicElementUtil pp = new DynamicElementUtil(driver);
        WebElement ele =pp.getAnalysisModuleByNameUnderEdit(moduleName);
        ToolKit.waitForWebElementClickable(driver,ele);
        ToolKit.highlightWebElement(driver,ele);
        ToolKit.JavaScriptClick(driver,ele);
        ElementUtil.findElement(driver,comment).sendKeys(commentInfo);
        clickDataAppSave(driver);

    }
    /**
     * 验证分析模块注释
     * @param driver
    {
    "checkAnalysisModuleComment": {
    "模块名称":"ML_SampleData",
    "注释":"这是个分析模块"
    }
    }
     * @throws Exception
     */
    public void checkAnalysisModuleComment(WebDriver driver,String data)throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  moduleName =  map.get("模块名称").toString();
        String  commentInfo =  map.get("注释").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String comment = ElementTemplate.getValues(elementSelector, "DataApp").get("点击").get("注释");

        DynamicElementUtil pp = new DynamicElementUtil(driver);
        WebElement ele =pp.getAnalysisModuleByNameUnderEdit(moduleName);
        ToolKit.waitForWebElementClickable(driver,ele);
        ToolKit.highlightWebElement(driver,ele);
        ToolKit.JavaScriptClick(driver,ele);

        String com = ElementUtil.findElement(driver,comment).getText();
        Assert.assertTrue(commentInfo.equals(com));

    }
}
