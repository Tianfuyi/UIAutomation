package com.zetyun.uitest.abstractbusiness;

import com.zetyun.uitest.pageoperation.DataApp;
import com.zetyun.uitest.pageoperation.DataApplication;
import com.zetyun.uitest.pageoperation.common.Pagelist;
import com.zetyun.uitest.pageoperation.common.Table;
import com.zetyun.uitest.utility.JsonUtil;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.Map;

/**
 * 数据应用
 */
public class ActionDataApp {

    /*
     * @param driver
     * @param data
     * @throws Exception
     {"导航名称":"数据模块"}
     */
    public static void returnDataAppPage(WebDriver driver,String data) throws Exception {
        new Table().clicktableWorkFlow(driver);// 点击工作流
        Pagelist pl=new Pagelist();
        pl.backTo(driver); // 点击返回上一页
        Assert.assertTrue(pl.getItems(driver,data).get(0).isDisplayed());
    }

    /**
     * 需要预设环境
     * 验证模块名称
     *  验证查看数据
     * @param driver
     * @param data
     * @throws Exception
     * {"数据模块名称":"aps_das_responsedsfasd"}
     */
     public static void checkDataModuledetail(WebDriver driver,String data) throws Exception{
         new Table().clicktableWorkFlow(driver);// 点击工作流
         DataApplication da = new DataApplication();
         da.checkDataModuledetail(driver,data); //
     }

    /**
     *
     * @param driver
     * @param data
     * @throws Exception
    {
    "模块类型":"分析模块/数据模块",
    "模块名称":"bank"
    }
     */
     public static void searchMoule(WebDriver driver,String data) throws Exception{
         new Table().clicktableWorkFlow(driver);// 点击工作流
         Map map= new JsonUtil().jsonToMaps(data);
         String  datas =  map.get("searchMoule").toString();
         DataApplication da = new DataApplication();
         da.searchMoule(driver,datas); //查询模块名称验证
     }


    /**
     * 验证更多下拉框 设置项正常显示
     * @param driver
     * @param data
     * @throws Exception
    {"checkMore":{"验证":"复制/编辑/权限"}}
     */
     public static  void checkMore(WebDriver driver,String data) throws Exception{
         new Table().clicktableWorkFlow(driver);// 点击工作流
         DataApplication da = new DataApplication();
         Map map= new JsonUtil().jsonToMaps(data);
         String  datas =  map.get("checkMore").toString();
         da.checkMore(driver,datas);
     }

    /*
     * 搜索数据模块
     {
     "searchedContent": "Bing_DataApp"
     }
     *
     * @param driver
     * @throws Exception
     */
    public static void searchDataApp(WebDriver driver, String data) throws Exception {
        DataApp dataApp = new DataApp();
        dataApp.searchDataApp(driver, data);
    }

    /*
     * 查看数据应用详情页
     {}
     *
     * @param driver
     * @throws Exception
     */
    public static void viewDataAppDetails(WebDriver driver) throws Exception {
        DataApp dataApp = new DataApp();
        dataApp.viewDataAppDetails(driver);
        dataApp.verifyDataAppDetails(driver);
    }

    /*
     * 查看工作流
     {}
     *
     * @param driver
     * @throws Exception
     */
    public static void viewWorkflow(WebDriver driver) throws Exception {
        DataApp dataApp = new DataApp();
        dataApp.viewWorkflow(driver);
        dataApp.verifyViewWorkflow(driver);
    }

    /*
     * 查看工作流详情页
     {}
     *
     * @param driver
     * @throws Exception
     */
    public static void viewWorkflowDetails(WebDriver driver) throws Exception {
        DataApp dataApp = new DataApp();
        dataApp.viewWorkflowDetails(driver);
        dataApp.verifyViewWorkflowDetails(driver);
    }

    /*
     * 查看工作流应用变量
     {}
     *
     * @param driver
     * @throws Exception
     */
    public static void viewWorkflowAppVariables(WebDriver driver) throws Exception {
        DataApp dataApp = new DataApp();
        dataApp.viewWorkflowAppVariables(driver);
        dataApp.verifyViewWorkflowAppVariables(driver);
    }

    /*
     * 查看数据模块详情
     {
	    "moduleTitle": "Bing_DataModule"
     }
     *
     * @param driver
     * @tows Exception
     */
    public static void viewDataModuleDetails(WebDriver driver, String data) throws Exception {
        DataApp dataApp = new DataApp();
        dataApp.viewDataModuleDetails(driver, data);
        dataApp.verifyDataModuleDetails(driver);
    }

    /*
     * 查看数据
     {}
     *
     * @param driver
     * @tows Exception
     */
    public static void viewDataModuleData(WebDriver driver) throws Exception {
        DataApp dataApp = new DataApp();
        dataApp.viewData(driver);
        dataApp.verifyViewData(driver);
    }

    /*
     * 查看分析模块详情
     {
	    "moduleTitle": "Bing_AnalyzeModule"
     }
     *
     * @param driver
     * @tows Exception
     */
    public static void viewAnalyzeModuleDetails(WebDriver driver, String data) throws Exception {
        DataApp dataApp = new DataApp();
        dataApp.viewAnalyzeModuleDetails(driver, data);
        dataApp.verifyAnalyzeModuleDetails(driver);
    }

    /*
     * 查看分析模块代码
     {}
     *
     * @param driver
     * @tows Exception
     */
    public static void viewAnalyzeModuleCode(WebDriver driver) throws Exception {
        DataApp dataApp = new DataApp();
        dataApp.viewCode(driver);
        dataApp.verifyViewCode(driver);
    }

    /*
     * 编缉分析模块
     {
	    "version": "0.3",
	    "cpu": "1",
	    "mem": "2",
	    "gpu": "0"
     }
     *
     * @param driver
     * @tows Exception
     */
    public static void editAnalyzeModule(WebDriver driver, String data) throws Exception {
        DataApp dataApp = new DataApp();
        dataApp.editAnalyzeModule(driver, data);
        dataApp.verifyEditAnalyzeModule(driver, data);
    }

    /*
     * 模块右键菜单－数据模块复制
     {
	    "moduleTitle": "Bing_DataModule"
     }
     *
     * @param driver
     * @tows Exception
     */
    public static void moduleContextMenu_DataModuleCopy(WebDriver driver, String data) throws Exception {
        DataApp dataApp = new DataApp();
        dataApp.moduleContextMenu_DataModuleCopy(driver, data);
    }

    /*
     * 模块右键菜单－分析模块复制
     {
	    "moduleTitle": "Bing_AnalyzeModule"
     }
     *
     * @param driver
     * @tows Exception
     */
    public static void moduleContextMenu_AnalyzeModuleCopy(WebDriver driver, String data) throws Exception {
        DataApp dataApp = new DataApp();
        dataApp.moduleContextMenu_AnalyzeModuleCopy(driver, data);
    }

    /*
     * 模块右键菜单-数据模块删除
     {
	    "moduleTitle": "Bing_AnalyzeModule"
     }
     *
     * @param driver
     * @tows Exception
     */
    public static void moduleContextMenu_DataModuleDelete(WebDriver driver, String data) throws Exception {
        DataApp dataApp = new DataApp();
        dataApp.moduleContextMenu_DataModuleDelete(driver, data);
    }

    /*
     * 模块右键菜单－分析模块删除
     {
	    "moduleTitle": "Bing_AnalyzeModule"
     }
     *
     * @param driver
     * @tows Exception
     */
    public static void moduleContextMenu_AnalyzeModuleDelete(WebDriver driver, String data) throws Exception {
        DataApp dataApp = new DataApp();
        dataApp.moduleContextMenu_AnalyzeModuleDelete(driver, data);
    }

    /*
     * 模块右键菜单－数据模块预览
     {
	    "moduleTitle": "Bing_AnalyzeModule"
     }
     *
     * @param driver
     * @tows Exception
     */
    public static void moduleContextMenu_DataModulePreview(WebDriver driver, String data) throws Exception {
        DataApp dataApp = new DataApp();
        dataApp.moduleContextMenu_DataModulePreview(driver, data);
        dataApp.verifyDataModuleContextMenu_Preview(driver);
    }

    /*
     * 模块右键菜单－分析模块预览
     {
	    "moduleTitle": "Bing_AnalyzeModule"
     }
     *
     * @param driver
     * @tows Exception
     */
    public static void moduleContextMenu_AnalyzeModulePreview(WebDriver driver, String data) throws Exception {
        DataApp dataApp = new DataApp();
        dataApp.moduleContextMenu_AnalyzeModulePreview(driver, data);
        dataApp.verifyAnalyzeModuleContextMenu_Preview(driver);
    }

    /*
     * 模块右键菜单－分析模块从此处开始执行
     {
	    "moduleTitle": "Bing_AnalyzeModule"
     }
     *
     * @param driver
     * @tows Exception
     */
    public static void moduleContextMenu_AnalyzeModuleExecuteFromHere(WebDriver driver, String data) throws Exception {
        DataApp dataApp = new DataApp();
        dataApp.moduleContextMenu_AnalyzeModuleExecuteFromHere(driver, data);
        dataApp.verifyAppRunConfig(driver);
    }

    /*
     * 模块右键菜单－分析模块执行到此处
     {
	    "moduleTitle": "Bing_AnalyzeModule"
     }
     *
     * @param driver
     * @tows Exception
     */
    public static void moduleContextMenu_AnalyzeModuleExecuteToHere(WebDriver driver, String data) throws Exception {
        DataApp dataApp = new DataApp();
        dataApp.moduleContextMenu_AnalyzeModuleExecuteToHere(driver, data);
        dataApp.verifyAppRunConfig(driver);
    }

    /*
     * 模块右键菜单－分析模块执行该节点
     {
	    "moduleTitle": "Bing_AnalyzeModule"
     }
     *
     * @param driver
     * @tows Exception
     */
    public static void moduleContextMenu_AnalyzeModuleExecuteTheNode(WebDriver driver, String data) throws Exception {
        DataApp dataApp = new DataApp();
        dataApp.moduleContextMenu_AnalyzeModuleExecuteTheNode(driver, data);
        dataApp.verifyAppRunConfig(driver);
    }

    /*
     * 模块右键菜单－分析模块查看结果
     {
	    "moduleTitle": "Bing_AnalyzeModule"
     }
     *
     * @param driver
     * @tows Exception
     */
    public static void moduleContextMenu_AnalyzeModuleViewResult(WebDriver driver, String data) throws Exception {
        DataApp dataApp = new DataApp();
        dataApp.moduleContextMenu_AnalyzeModuleViewResult(driver, data);
        dataApp.verifyModuleContextMenu_AnalyzeModuleViewResult(driver, data);
    }

    /*
     * 模块右键菜单－分析模块查看日志
     {
	    "moduleTitle": "Bing_AnalyzeModule"
     }
     *
     * @param driver
     * @tows Exception
     */
    public static void moduleContextMenu_AnalyzeModuleViewLog(WebDriver driver, String data) throws Exception {
        DataApp dataApp = new DataApp();
        dataApp.moduleContextMenu_AnalyzeModuleViewLog(driver, data);
        dataApp.verifyModuleContextMenu_AnalyzeModuleViewLog(driver, data);
    }
    /**
     * 点击工作流页面运行按钮并验证
     * @param driver
     * @param data
    { "DataAppRunOnce"：{
    "clickDataAppRun": {
    "运行名称": "autotest123456",
    "运行描述": "autoTest"
    }
    }
    }
     * @throws Exception
     */
    public static void DataAppRunOnceA(WebDriver driver,String data)throws Exception{
        DataApplication dp = new DataApplication();
        dp.DataAppRunOnce(driver,data);

    }

    public static void DataAppRunLoopA(WebDriver driver,String data)throws Exception{
        DataApplication dp = new DataApplication();
        dp.DataAppRunLoop(driver,data);

    }
    /**
     * 点击工作流页面历史任务按钮
     * @param driver
    {
    "工作流名称":"test_0001_dtype"
    }
     * @throws Exception
     */
    public static void clickDataAppTaskHistoryA(WebDriver driver,String data) throws Exception{
        DataApplication dp = new DataApplication();
        dp.clickDataAppTaskHistory(driver,data);

    }
    /**
     * 点击工作流页面历史计划按钮
     * @param driver
    {
    "工作流名称":"test_0001_dtype"
    }
     * @throws Exception
     */
    public static void clickDataAppPlanHistoryA(WebDriver driver,String data) throws Exception{
        DataApplication dp = new DataApplication();
        dp.clickDataAppPlanHistory(driver,data);

    }

    /**
     * 点击工作流页面模型评估按钮
     * @param driver
     * @param data
    {
    "工作流名称":"test_0001_dtype"
    }
     * @throws Exception
     */
    public static void clickDataAppModelEvaA(WebDriver driver,String data) throws Exception{
        DataApplication dp = new DataApplication();
        dp.clickDataAppModelEva(driver,data);

    }
    /**
     * 点击工作流页面历史版本按钮
     * @param driver
     * @throws Exception
     */
    public static void clickDataAppVerHistoryA(WebDriver driver) throws Exception{
        DataApplication dp = new DataApplication();
        dp.clickDataAppVerHistory(driver);
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
    public static void delDataAppVerA(WebDriver driver,String data)throws Exception{
        DataApplication dp = new DataApplication();
        dp.delDataAppVer(driver,data);

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
    public static void switchDataAppVerA(WebDriver driver,String data)throws Exception{
        DataApplication dp = new DataApplication();
        dp.switchDataAppVer(driver,data);

    }
    /**
     * 点击工作流页面保存按钮
     * @param driver
     *
     * @throws Exception
     */
    public static void clickDataAppSaveA(WebDriver driver) throws Exception{
        DataApplication dp = new DataApplication();
        dp.clickDataAppSave(driver);


    }

    /**
     * 提交并验证版本信息
     * @param driver
     * @throws Exception
     */
    public static void checkVersionA(WebDriver driver) throws Exception{

        DataApplication dp = new DataApplication();
        dp.checkVersion(driver);

    }
    /**
     * 添加数据模块注释
     * @param driver
    {
    "模块名称":"ML_SampleData",
    "注释":"这是个数据模块"
    }
     * @throws Exception
     */
    public static void addDataModuleCommentA(WebDriver driver,String data) throws Exception{

        DataApplication dp = new DataApplication();
        dp.addDataModuleComment(driver,data);


    }
    /**
     * 验证数据模块注释
     * @param driver
    {
    "模块名称":"ML_SampleData",
    "注释":"这是个数据模块"
    }
     * @throws Exception
     */
    public static void checkDataModuleCommentA(WebDriver driver,String data)throws Exception{
        DataApplication dp = new DataApplication();
        dp.checkDataModuleComment(driver,data);


    }
    /**
     * 添加分析模块注释
     * @param driver
    {
    "模块名称":"ML_SampleData",
    "注释":"这是个分析模块"
    }
     * @throws Exception
     */
    public static void addAnalysisModuleCommentA(WebDriver driver,String data) throws Exception{

        DataApplication dp = new DataApplication();
        dp.addAnalysisModuleComment(driver,data);


    }
    /**
     * 验证分析模块注释
     * @param driver
    {
    "模块名称":"ML_SampleData",
    "注释":"这是个分析模块"
    }
     * @throws Exception
     */
    public static void checkAnalysisModuleCommentA(WebDriver driver,String data)throws Exception{
        DataApplication dp = new DataApplication();
        dp.checkAnalysisModuleComment(driver,data);

    }

}
