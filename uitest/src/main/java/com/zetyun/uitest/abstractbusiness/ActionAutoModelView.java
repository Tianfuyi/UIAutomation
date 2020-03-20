package com.zetyun.uitest.abstractbusiness;


import com.zetyun.uitest.pageoperation.AutoModelView;
import com.zetyun.uitest.utility.JsonUtil;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class ActionAutoModelView {
    /**
     * 点击切换模型任务名称（页面跳转只任务列表）
     * @param driver
     * @param data
    {
    "clickTaskNameA": {
    "clickTaskName": {
    "任务名称": "test_0001_dtype_3"
    }
    }
    }
     */
    public static void clickTaskNameA(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String taskName = (String)map.get("clickTaskName");
        AutoModelView amv= new AutoModelView();
        amv.clickTaskName(driver,taskName);
    }
    /**
     * 点击切换模型任务栏（右侧显示内容会切换）
     * @param driver
     * @param data
    {
    "clickTaskTabA": {
    "clickTaskTab": {
    "任务名称": "test_0001_dtype_3"
    },
    "viewInLeft": {
    "任务名称": "test_0001_dtype_3"
    }
    }
    }
     */
    public static void clickTaskTabA(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String taskTab = (String)map.get("clickTaskTab");
        String taskNameInChart = (String)map.get("viewInLeft");
        AutoModelView amv= new AutoModelView();
        amv.clickTaskTab(driver,taskTab);
        amv.checkModelChart(driver,taskNameInChart);

    }
    /**
     * 验证自助建模XGBoost存在
     * @param driver
     * @param data
    {"checkViewPageXA":{
    "checkViewPageX": {
    "模型名称": "test_0001_dtype"
    }
    }
    }
     */
    public static void checkViewPageXA(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String modelAlgName = (String)map.get("checkViewPageX");
        AutoModelView amv= new AutoModelView();
        amv.checkViewPageX(driver,modelAlgName);

    }
    /**
     * 验证自助建模XGBoost存在
     * @param driver
     * @param data
    {"checkViewPageRA":{
    "checkViewPageR": {
    "模型名称": "test_0001_dtype"
    }
    }
    }
     */
    public static void checkViewPageRA(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String modelAlgName = (String)map.get("checkViewPageR");
        AutoModelView amv= new AutoModelView();
        amv.checkViewPageR(driver,modelAlgName);

    }
    /**
     * 验证自助建模XGBoost存在
     * @param driver
     * @param data
    {"checkViewPageGA":{
    "checkViewPageG": {
    "模型名称": "test_0001_dtype"
    }
    }
    }
     */
    public static void checkViewPageGA(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String modelAlgName = (String)map.get("checkViewPageG");
        AutoModelView amv= new AutoModelView();
        amv.checkViewPageG(driver,modelAlgName);

    }
    /**
     * 选择模型评估参数，右上角的下拉框
     * @param driver
     * @param data
    {
    "chooseEvaParamA": {
    "chooseEvaParam": {
    "评估参数": "F1"
    },
    "checkEvaParam": {
    "评估参数": "F1"
    }
    }
    }
     */
    public static void chooseEvaParamA(WebDriver driver,String data)throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String modelParam = (String)map.get("chooseEvaParam");
        String checkParam = (String)map.get("checkEvaParam");
        AutoModelView amv = new AutoModelView();
        amv.chooseEvaParam(driver,modelParam);
        amv.checkEvaParam(driver,checkParam);
    }
    /**
     * 按列表的形式展示模型结果
     * @param driver
     * @param data
    {
    "showTableResultA": {
    "showTableResult": {
    "任务名称": "test_0001_dtype_4"
    }
    }
    }
     */
    public static void showTableResultA(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String tableResult = (String)map.get("showTableResult");
        AutoModelView amv = new AutoModelView();
        amv.showTableResult(driver,tableResult);
    }
    /**
     * 按图表的形式展示模型结果(table状态下，才能调用)
     * @param driver
     * @param data
    {
    "returnToChartA": {
    "returnToChart": {
    "任务名称": "test_0001_dtype_4"
    }
    }
    }
     */
    public static void returnToChartA(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String chartResult = (String)map.get("returnToChart");
        AutoModelView amv = new AutoModelView();
        amv.returnToChart(driver,data);

    }
    /**
     * 算法图形右侧查看操作
     * @param driver
     * @param data
    {
    "viewInLeftA": {
    "viewInLeft": {
    "算法名称": "XGBoost",
    "操作": "查看"
    }
    }
    }
     */
    public static void viewInLeftA(WebDriver driver,String data)throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String viewLeft = (String)map.get("viewInLeft");
        AutoModelView amv = new AutoModelView();
        amv.viewInLeft(driver,viewLeft);

    }
    /**
     * 算法图形右侧查看日志操作
     * @param driver
     * @param data
    {
    "logInfoInLeftA": {
    "logInfoInLeft": {
    "算法名称": "XGBoost",
    "操作": "日志"
    }
    }
    }
     */

    public static void logInfoInLeftA(WebDriver driver,String data)throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String logInfoLeft = (String)map.get("logInfoInLeft");
        AutoModelView amv = new AutoModelView();
        amv.logInfoInLeft(driver,logInfoLeft);
    }
    /**
     * 算法名称参数校验(右侧图表下方的参数校验)
     * @param driver
     * @param data
    {
    "checkParamValueA": {
    "checkParamValue": {
    "模型名称": "test_0001_dtype_4",
    "算法名称": "XGBoost"
    }
    }
    }
     *
     */
    public static void checkParamValueA(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String paramValue = (String)map.get("checkParamValue");
        AutoModelView amv = new AutoModelView();
        amv.checkParamValue(driver,paramValue);

    }
    /**
     * 发布到模型仓库
     * @param driver
     * @param data
    {
    "releaseToStoreA": {
    "releaseToStore": {
    "评估条件": "EVS",
    "操作符": "≥",
    "条件值": "0"
    }
    }
    }
     *
     */

    public static void releaseToStoreA(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String releaseToStore = (String)map.get("releaseToStore");
        AutoModelView amv = new AutoModelView();
        amv.releaseToStore(driver,releaseToStore);
    }
    /**
     * 生成数据应用
     * @param driver
     * @param data
    {
    "generateDataAppA": {
    "generateDataApp": {
    "数据应用名称": "test20180627",
    "数据应用描述": "模型生成数据应用",
    "标签": "",
    "公开类型": "私有"
    }
    }
    }
     *#数据应用名称:默认为空时， 数据应用名称为 模型名称 + "-副本"
     * #标签,默认空时没有标签
     * #公开类型:默认空时为公开
     */
    public static void generateDataAppA(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String generateDataApp = (String)map.get("generateDataApp");
        AutoModelView amv = new AutoModelView();
        amv.generateDataApp(driver,generateDataApp);

    }
    /**
     * 算法名称点击校验
     * @param driver
     * @param data
    {
    "clickAlgorithmA": {
    "clickAlgorithm": {
    "模型名称": "test_0001_dtype_4",
    "算法名称": "XGBoost"
    }
    }
    }
     *
     */
    public static void clickAlgorithmA(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String clickAlgorithm = (String)map.get("clickAlgorithm");
        AutoModelView amv = new AutoModelView();
        amv.clickAlgorithm(driver,clickAlgorithm);


    }
    /**
     * 算法任务删除，包含验证
     * @param driver
     * @param data
    {
    "delAlgorithmA": {
    "delAlgorithm": {
    "模型名称": "test_0001_dtype_4"
    }
    }
    }
     *
     */
    public static void delAlgorithmA(WebDriver driver,String data) throws Exception {
        Map map= new JsonUtil().jsonToMaps(data);
        String delAlgorithm = (String)map.get("delAlgorithm");
        AutoModelView amv = new AutoModelView();
        amv.delAlgorithm(driver,delAlgorithm);

    }


    /**
     * 特征变量重要性
     * @param driver
     * @throws Exception
     */
    public static void checkAlgorithmFeature(WebDriver driver) throws Exception {
        new AutoModelView().checkAlgorithmFeature(driver);
    }


    /**评估指标
     * @param driver
     * @throws Exception
     */
    public static void checkevaluation(WebDriver driver) throws Exception {
        new AutoModelView().checkevaluation(driver);
    }


    /**ROC
     * @param driver
     * @throws Exception
     */
    public static void checROC(WebDriver driver) throws Exception {
        new AutoModelView().checROC(driver);
    }

    /**混淆矩阵
     * @param driver
     * @throws Exception
     */
    public static void obfuscationatrix(WebDriver driver) throws Exception {
        new AutoModelView().obfuscationatrix(driver);
    }

    /**训练详情信息
     * @param driver
     * @throws Exception
     */
    public static void traindetailmessage(WebDriver driver) throws Exception {
        new AutoModelView().traindetailmessage(driver);
    }
}
