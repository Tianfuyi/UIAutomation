package com.zetyun.uitest.pageoperation;

import com.zetyun.data.DataParse;
import com.zetyun.datatemplate.elementtemplate.ElementTemplate;
import com.zetyun.driver.log.LogWriter;
import com.zetyun.uitest.pageoperation.common.Search;
import com.zetyun.uitest.pageoperation.common.Table;
import com.zetyun.uitest.utility.DynamicElementUtil;
import com.zetyun.uitest.utility.ElementUtil;
import com.zetyun.uitest.utility.JsonUtil;
import com.zetyun.uitest.utility.ToolKit;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AutoModelView {

    /**
     * 点击切换模型任务名称（页面跳转只任务列表）
     * @param driver
     * @param data
    {
    "clickTaskName": {
    "任务名称": "test_0001_dtype_3"
    }
    }
     */
    public void clickTaskName(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  taskName =  map.get("任务名称").toString();
        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.getTitleElement(taskName).click();
    }

    /**
     * 获取任务名称
     * @param driver
     * @return
     */
    public List<WebElement> getTaskName(WebDriver driver) throws Exception {
        try{
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String name = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("训练任务名称");
        List list=ElementUtil.findElements(driver,name);
        return list;
        }catch (Exception e){
            LogWriter.error(AutoModelView.class,"任务名称加载失败");
            return null;
        }
    }
    /**
     * 点击切换模型任务栏（右侧显示内容会切换）
     * @param driver
     * @param data
    {
    "clickTaskTab": {
    "任务名称": "test_0001_dtype_3"
    }
    }
     */
    public void clickTaskTab(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  taskName =  map.get("任务名称").toString();
        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.getTaskTab(taskName).click();
    }
    /**
     * chart图形中算法点击
     * @param driver
     * @param data
    {
    "clickAlgInChart": {
    "算法名称": "XGBoost"
    }
    }
     */
    public void clickAlgInChart(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  algName =  map.get("算法名称").toString();
        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.getAlgInChart(algName).click();
    }
    /**
     * 点击切换模型任务栏（右侧显示内容会切换）
     * @param driver
     * @param data
    {
    "checkModelChart": {
    "任务名称": "test_0001_dtype_3"
    }
    }
     */
    public void checkModelChart(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  taskName =  map.get("任务名称").toString();
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String chartName = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("模型图示名称");

        String chartNameVarify = ElementUtil.findElement(driver,chartName).getText();
        Assert.assertTrue(chartNameVarify.equals(taskName));

    }

    /**
     * 验证自助建模XGBoost存在
     * @param driver
     * @param data
    {
    "checkViewPageX": {
    "模型名称": "test_0001_dtype"
    }
    }
     */
    public void checkViewPageX(WebDriver driver,String data)throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  modelNameStr =  map.get("模型名称").toString();
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String algList = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("算法名称列表");
        Search sr = new Search();
        sr.search(driver,modelNameStr);
        boolean finded = false;
        List<WebElement> algEls = ElementUtil.findElements(driver,algList);
        for (int i =0;i<algEls.size();i++){
            if (algEls.get(i).getText().equals("XGBoost")){
                finded = true;
                break;
            }

        }
        Assert.assertTrue(finded);
    }
    /**
     * 验证自助建模GradientBoostingTree存在
     * @param driver
     * @param data
    {
    "checkViewPageG": {
    "模型名称": "test_0001_dtype"
    }
    }
     */
    public void checkViewPageG(WebDriver driver,String data)throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  modelNameStr =  map.get("模型名称").toString();
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String algList = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("算法名称列表");
        Search sr = new Search();
        sr.search(driver,modelNameStr);
        boolean finded = false;
        List<WebElement> algEls = ElementUtil.findElements(driver,algList);
        for (int i =0;i<algEls.size();i++){
            if (algEls.get(i).getText().equals("GradientBoostingTree")){
                finded = true;
                break;
            }

        }
        Assert.assertTrue(finded);
    }
    /**
     * 验证自助建模RandomForest存在
     * @param driver
     * @param data
    {
    "checkViewPageR": {
    "模型名称": "test_0001_dtype"
    }
    }
     *
     */
    public void checkViewPageR(WebDriver driver,String data)throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  modelNameStr =  map.get("模型名称").toString();
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String algList = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("算法名称列表");
        Search sr = new Search();
        sr.search(driver,modelNameStr);
        boolean finded = false;
        List<WebElement> algEls = ElementUtil.findElements(driver,algList);
        for (int i =0;i<algEls.size();i++){
            if (algEls.get(i).getText().equals("RandomForest")){
                finded = true;
                break;
            }

        }
        Assert.assertTrue(finded);
    }
    /**
     * 选择模型评估参数，右上角的下拉框
     * @param driver
     * @param data
    {
    "chooseEvaParam": {
    "评估参数": "F1"
    }
    }
     */
    public void chooseEvaParam(WebDriver driver,String data)throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  evaStr =  map.get("评估参数").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String evaPullBtn = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("选择评估参数");
        ElementUtil.findElement(driver,evaPullBtn).click();
        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.ModuleType(evaStr).click();
    }
    /**
     * 选择模型评估参数，右上角的下拉框
     * @param driver
     * @param data
    {
    "checkEvaParam": {
    "评估参数": "F1"
    }
    }
     */
    public void checkEvaParam(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  evaStr =  map.get("评估参数").toString();
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String evaPullBtn = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("选择评估参数");
        String evaStrVarify = ElementUtil.findElement(driver,evaPullBtn).getText();
        Assert.assertTrue(evaStrVarify.equals(evaStr));
    }
    /**
     * 按列表的形式展示模型结果
     * @param driver
     * @param data
    {
    "showTableResult": {
    "任务名称": "test_0001_dtype_4"
    }
    }
     */
    public void showTableResult(WebDriver driver,String data) throws Exception{
        boolean finded = false;
        Map map= new JsonUtil().jsonToMaps(data);
        String  modelName =  map.get("任务名称").toString();
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String tableShow = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("列表显示");
        String tableVarify = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("归属名称");
        ElementUtil.findElement(driver,tableShow).click();
        List<WebElement> es = ElementUtil.findElements(driver,tableVarify);
        for (int i = 0;i<es.size();i++){
            finded = es.get(i).getText().contains(modelName);
            break;
        }
        Assert.assertTrue(finded);

    }
    /**
     * 按图表的形式展示模型结果
     * @param driver
     * @param data
    {
    "returnToChart": {
    "任务名称": "test_0001_dtype_4"
    }
    }
     */
    public void returnToChart(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  modelName =  map.get("任务名称").toString();
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String chartShow = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("图表显示");
        ElementUtil.findElement(driver,chartShow).click();

        //验证
        checkModelChart(driver,modelName);



    }
    /**
     * 算法图形右侧查看操作
     * @param driver
     * @param data
    {
    "viewInLeft": {
    "算法名称": "XGBoost",
    "操作": "查看"
    }
    }
     */
    public void viewInLeft(WebDriver driver,String data)throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  algName =  map.get("任务名称").toString();
        String  operaName =  map.get("操作").toString();

        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.getBtnByAlgName(algName,operaName);

    }
    /**
     * 算法图形右侧查看日志操作
     * @param driver
     * @param data
    {
    "logInfoInLeft": {
    "算法名称": "XGBoost",
    "操作": "日志"
    }
    }
     */

    public void logInfoInLeft(WebDriver driver,String data)throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  algName =  map.get("任务名称").toString();
        String  operaName =  map.get("操作").toString();

        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.getBtnByAlgName(algName,operaName);

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String logClose = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("查看日志关闭按钮");
        ElementUtil.findElement(driver,logClose).click();

    }
    /**
     * 算法名称参数校验
     * @param driver
     * @param data
    {
    "checkParamValue": {
    "模型名称": "test_0001_dtype_4",
    "算法名称": "XGBoost"
    }
    }
     *
     */
    public void checkParamValue(WebDriver driver,String data) throws Exception{
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String leftParam = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("左参数");
        String rightValue = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("右变量");

        List<WebElement> esL = ElementUtil.findElements(driver,leftParam);
        List<WebElement> esR = ElementUtil.findElements(driver,rightValue);

        Assert.assertTrue(esL.size()>1&&esR.size()>1);

    }

    /**
     * 发布到模型仓库
     * @param driver
     * @param data
    {
    "releaseToStore": {
    "评估条件": "EVS",
    "操作符": "≥",
    "条件值": "0"
    }
    }
     *
     */

    public void releaseToStore(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  evaStr =  map.get("评估条件").toString();
        String  operator =  map.get("操作符").toString();
        String  evaVal =  map.get("条件值").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String releaseBtn = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("发布到模型仓库");
        String releasePll = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("发布界面下拉框");
        String releaseCt = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("条件值");
        String releaseVe = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("查看");
        String releaseClose = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("查看关闭");
        String releaseSubmit = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("提交");
        String updateInfo = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("更新完成");
        String updateClose = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("关闭更新进度");

        ElementUtil.findElement(driver,releaseBtn).click();
        List<WebElement> es = ElementUtil.findElements(driver,releasePll);
        es.get(0).click();
        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.getPullElement(evaStr).click();
        if (!operator.isEmpty()){
            if (operator.equals("≥")||operator.equals("≤")){
                es.get(1).click();
                pp.getPullElement(operator).click();
            }
        }

        ElementUtil.findElement(driver,releaseCt).sendKeys(evaVal);
        ElementUtil.findElement(driver,releaseVe).click();
        ElementUtil.findElement(driver,releaseClose).click();
        ElementUtil.findElement(driver,releaseSubmit).click();
        WebElement eu = ElementUtil.findElement(driver,updateInfo);
        ToolKit.waitForWebElementVisible(driver,eu,180);
        ElementUtil.findElement(driver,updateClose).click();

    }
    /**
     * 生成数据应用
     * @param driver
     * @param data
    {
    "generateDataApp": {
    "数据应用名称": "test20180627",
    "数据应用描述": "模型生成数据应用",
    "标签": "",
    "公开类型": "私有"
    }
    }
     *#数据应用名称:默认为空时， 数据应用名称为 模型名称 + "-副本"
     * #标签,默认空时没有标签
     * #公开类型:默认空时为公开
     */
    public void generateDataApp(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  appNameStr =  map.get("数据应用名称").toString();
        String  appDescStr =  map.get("数据应用描述").toString();
        String  appTagStr =  map.get("标签").toString();
        String  appPrivStr =  map.get("公开类型").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String appBtn = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("生成数据应用");
        String appName = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("数据应用名称");
        String appDesc = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("数据应用描述");
        String appTag = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("应用标签");
        String appPriv = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("私有");
        String appOpen = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("公开");
        String appConfirm = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("确定");


        ElementUtil.findElement(driver,appBtn).click();
        if (!appNameStr.isEmpty()){
            ElementUtil.findElement(driver,appName).sendKeys(appNameStr);
        }else {
            appNameStr = ElementUtil.findElement(driver,appName).getText();
        }
        if (!appDescStr.isEmpty()){
            ElementUtil.findElement(driver,appDesc).sendKeys(appDescStr);
        }
        if (!appTagStr.isEmpty()){
            ElementUtil.findElement(driver,appTag).click();
            DynamicElementUtil pp = new DynamicElementUtil(driver);
            pp.ModuleType(appTagStr).click();
        }
        if (!appPrivStr.isEmpty()){

            if (appPrivStr.equals("私有")){
                ElementUtil.findElement(driver, appPriv).click();
            }else if (appPrivStr.equals("公开")){
                ElementUtil.findElement(driver, appOpen).click();

            }
        }
        ElementUtil.findElement(driver,appConfirm).click();
        //验证页面是否跳转成功
        Table tb = new Table();
        tb.checkPageInfo(driver,appNameStr);

    }
    /**
     * 算法名称点击校验
     * @param driver
     * @param data
    {
    "clickAlgorithm": {
    "模型名称": "test_0001_dtype_4",
    "算法名称": "XGBoost"
    }
    }
     *
     */
    public void clickAlgorithm(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  modelNameStr =  map.get("模型名称").toString();
        String  algNameStr =  map.get("算法名称").toString();
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String algList = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("算法名称列表");
        Search sr = new Search();
        sr.search(driver,modelNameStr);
        List<WebElement> algEls = ElementUtil.findElements(driver,algList);
        for (int i =0;i<algEls.size();i++){
            if (algEls.get(i).getText().equals(algNameStr)){
                algEls.get(i).click();
                break;
            }
        }

    }
    /**
     * 概要中算法名称验证
     * @param driver
     * @param data
    {
    "checkAlgorithmName": {
    "算法名称": "XGBoost"
    }
    }
     *
     */
    public void checkAlgorithmName(WebDriver driver,String data)throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  algNameStr =  map.get("算法名称").toString();
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String algNameVarify = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("算法名称验证");
        String algNameVar = ElementUtil.findElement(driver,algNameVarify).getText();
        Assert.assertTrue(algNameVar.contains(algNameStr));

    }

    /**
     * 特征变量重要性
     * @param driver

     *
     */
    public void checkAlgorithmFeature(WebDriver driver)throws Exception{
        LogWriter.info(AutoModelView.class,"验证特征变量重要性");
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");

        String menu = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("特征变量重要性选项");
        String result = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("特征变量重要性信息");

        WebElement elemenu= ElementUtil.findElement(driver,menu);
        ToolKit.JavaScriptClick(driver,elemenu);

        WebElement eleresult= ElementUtil.findElement(driver,result);
        Assert.assertTrue(eleresult.isDisplayed());


    }

    /**
     * 评估指标
     * @param driver
     */
    public void checkevaluation(WebDriver driver) throws IOException {
        LogWriter.info(AutoModelView.class,"验证评估指标");
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String result = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("模型评估指标");
        String menu = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("模型评估指标选项");

        WebElement eleresult=ElementUtil.findElement(driver,result);
        List<WebElement> elemenu =ElementUtil.findElements(driver,menu);
        ToolKit.JavaScriptClick(driver,eleresult);
        Assert.assertTrue(elemenu.size()>0);

    }




    /**
     * ROC
     * @param driver
     */
    public void checROC(WebDriver driver) throws IOException {
        LogWriter.info(AutoModelView.class,"验证ROC");
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");

        String menu = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("ROC曲线选项");
        String result = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("ROC显示");

        WebElement elemenu= ElementUtil.findElement(driver,menu);
        ToolKit.JavaScriptClick(driver,elemenu);

        WebElement eleresult= ElementUtil.findElement(driver,result);
       Assert.assertTrue(eleresult.isDisplayed());

    }

    /**
     * 混淆矩阵
     * @param driver
     */
    public void obfuscationatrix(WebDriver driver) throws IOException {
        LogWriter.info(AutoModelView.class,"验证混淆矩阵");
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");

        String menu = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("混淆矩阵选项");
        String result = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("混淆矩阵显示");

        WebElement elemenu= ElementUtil.findElement(driver,menu);
        ToolKit.JavaScriptClick(driver,elemenu);

        WebElement eleresult= ElementUtil.findElement(driver,result);
        Assert.assertTrue(eleresult.isDisplayed());

    }

    /**
     * 训练详情信息
     * @param driver
     */
    public void traindetailmessage(WebDriver driver) throws IOException {
        LogWriter.info(AutoModelView.class,"验证训练详情信息");
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");

        String menu = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("训练详细信息选项");
        String result = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("模型参数信息");

        WebElement elemenu= ElementUtil.findElement(driver,menu);
        ToolKit.JavaScriptClick(driver,elemenu);

        WebElement eleresult= ElementUtil.findElement(driver,result);
        Assert.assertTrue(eleresult.isDisplayed());

    }

    /**
     * 算法参数校验
     * @param driver
     * @param data
    {
    "checkALgPara": {
    "模型名称": "test_0001_dtype_4",
    "算法名称": "XGBoost"
    }
    }
     *
     *
     */
    public void clickALgPara(WebDriver driver,String data) throws Exception{

    }
    /**
     * 算法删除
     * @param driver
     * @param data
    {
    "delAlgorithm": {
    "模型名称": "test_0001_dtype_4"
    }
    }
     *
     */
    public void delAlgorithm(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  modelNameStr =  map.get("模型名称").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String algDelBtn = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("算法删除按钮");
        String algDelConfirm = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("删除确认");
        String algDelVarify = ElementTemplate.getValues(elementSelector, "AutoModel").get("查看").get("删除确认");

        Search sr = new Search();
        sr.search(driver,modelNameStr);
        Thread.sleep(2000);
        ElementUtil.findElement(driver,algDelBtn).click();
        ElementUtil.findElement(driver,algDelConfirm).click();
        sr.search(driver,modelNameStr);
        String varifyStr = ElementUtil.findElement(driver,algDelVarify).getText();
        Assert.assertTrue(varifyStr.equals("暂无数据"));

    }
    //table中的菜单操作

}
