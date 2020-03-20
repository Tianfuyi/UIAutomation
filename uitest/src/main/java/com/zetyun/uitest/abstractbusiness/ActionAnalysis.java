package com.zetyun.uitest.abstractbusiness;

import com.zetyun.uitest.pageoperation.AnalysisModule;
import com.zetyun.uitest.pageoperation.AnalysisModuleLeft;
import com.zetyun.uitest.pageoperation.common.Search;
import com.zetyun.uitest.utility.DynamicElementUtil;
import com.zetyun.uitest.utility.JsonUtil;
import com.zetyun.uitest.utility.ToolKit;
import org.apache.poi.util.ArrayUtil;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.Map;

/**
 * 分析模块
 */
public class ActionAnalysis {

    /**
     * 调试
     * @param driver
     * @throws Exception
     {
    "chooseRunFile": {"入口文件名称": "test.py"},
     "checkmsg":{"consolemsg":""}
     }
     */
    public static void debug(WebDriver driver,String data) throws Exception {
        Map map= new JsonUtil().jsonToMaps(data);
       String datas =map.get("chooseRunFile").toString();
        new AnalysisModuleLeft().chooseRunFile(driver,datas);
        AnalysisModule ana= new AnalysisModule();
        ana.clickDebugBtn(driver);
        String checkmsg = map.get("checkmsg").toString();//
        ana.verifyConsoleMsg(driver,checkmsg);

    }

    /**
     * 测试
     * @param driver
     * @param data
     * @throws Exception
     *
    {
    "执行方式": "excute/upload/system",
    "data": {
    "datas": "default/aa.text"
    },
    "checkmsg": {
    "consolemsg": ""
    },
    "chooseRunFile": {
    "入口文件名称": "test.py"
    }
    }
     */
    public static void test(WebDriver driver, String data)throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String msg =map.get("chooseRunFile").toString();
        new AnalysisModuleLeft().chooseRunFile(driver,msg);
        String  excuteType = map.get("执行方式").toString();// 测试执行方式(直接执行 本地上传 或系统文件  excute upload system)
        AnalysisModule ana= new AnalysisModule();
        String  datas = map.get("data").toString();   // 测试数据
        String checkmsg =map.get("checkmsg").toString();//
        switch (excuteType) {
            case "excute":      // 直接执行
                ana.test(driver);
                break;
            case "upload":   // 上传文件
                ana.testWithUpload(driver,datas);
                break;
            case "system":      // 使用系统文件
                ana.testWithSysFile(driver,datas);
                break;
        }
        ana.verifyConsoleMsg(driver,checkmsg);

    }

    /**
     * 发布
     * @param driver
     * @param data
     * @throws Exception
    {
    "releasedata":{ "描述":"", "版本":""},
    "验证":"是",
    "releasecheck":{"模块名称":"分析模块","查询类型":"普通搜索","查询数据":"TestForAutomationDPythonanacondaDDD"},
    "chooseRunFile":{"入口文件名称": "test.py"}
    }
     */
    public static String release(WebDriver driver,String data) throws Exception {
        AnalysisModule ana= new AnalysisModule();
        Map map= new JsonUtil().jsonToMaps(data);
        String datas =map.get("chooseRunFile").toString();
      new AnalysisModuleLeft().chooseRunFile(driver,datas);
        String  releasedata = map.get("releasedata").toString();
        ana.release(driver,releasedata);
        String needCheck=(String) map.get("验证");
        if(needCheck.equals("是")){
            String relseasecheck= map.get("releasecheck").toString();

            ana.checkRelease(driver,relseasecheck);
        }
        String result=new Search().checkSearch(driver,map.get("releasecheck").toString());// 校验 返回json
        return result;
    }

    /**
     * 导入
     * @param driver
     * @param data
     * @throws Exception
    {
    "import":"Test_import_package"
    "releasecheck":{"模块名称":"分析模块","查询类型":"普通搜索","Test_import_package"}
    }
     */
    public static void imports(WebDriver driver,String data) throws Exception{
        AnalysisModule ana = new AnalysisModule();
        Map map= new JsonUtil().jsonToMaps(data);
        String datas=map.get("import").toString();
        ana.imports(driver,datas);
        ana.checkRelease(driver,map.get("releasecheck").toString());

    }

    /** 验证历史版本 依赖查询 前面需要做发布操作
     * @param driver
     * @param data
     * @throws Exception
     */
    public static void checkHistory(WebDriver driver,String data) throws Exception{
        AnalysisModule am = new AnalysisModule();
        am.checkHistory(driver,data);
    }
    /**
     * 检查分析模块的Docker容器，正常为true，其他情况为false，
     * @param driver
     * @throws Exception
     * */
    public static boolean checkDocker(WebDriver driver) throws Exception{
        AnalysisModuleLeft aml = new AnalysisModuleLeft();
        return aml.checkDockerStat(driver);

    }
    /**
     * 启动分析模块的Docker容器
     * @param driver
     * @throws Exception
     * */
    public static void startDocker(WebDriver driver) throws Exception{
        AnalysisModuleLeft aml = new AnalysisModuleLeft();
        aml.startDocker(driver);

    }
    /**
     * 停止分析模块的Docker容器
     * @param driver
     * @throws Exception
     * */
    public static void stopDocker(WebDriver driver) throws Exception{
        AnalysisModuleLeft aml = new AnalysisModuleLeft();
        aml.stopDocker(driver);

    }
    /**
     * 在文件树上添加文件test.py,并验证文件是否添加成功
     * @param driver
     * @param data
     * @throws Exception
    {
    "addFile": {
    "文件名称": "test.py",
    "文件类型": "添加文件/添加文件夹"
    },
    "checkFileTree": {
    "文件名称": "test.py"
    }
    }
     */

    public static void addTreeFile(WebDriver driver,String data) throws Exception{
        AnalysisModuleLeft aml = new AnalysisModuleLeft();
        Map map= new JsonUtil().jsonToMaps(data);
        String  addfileData = map.get("addFile").toString();
        String  checkFileData = map.get("checkFileTree").toString();
        aml.addFile(driver,addfileData);
        ToolKit.wait(2);
        aml.checkFileTree(driver,checkFileData);

    }

    /**
     * 添加test.py的代码
     * @param driver
     * @param data
     * @throws Exception
    {
    "addPyCodeSave": {
    "文件名称": "test.py",
    "代码类型":"All/WithoutInput/CDH"
    }
    }
     */

    public static void addPyCodeToFile(WebDriver driver, String data) throws Exception{
        AnalysisModuleLeft aml = new AnalysisModuleLeft();
        Map map= new JsonUtil().jsonToMaps(data);
        String  codeSave = map.get("addPyCodeSave").toString();
        aml.addPyCodeSave(driver,codeSave);
    }

    /**
     * 添加test.R的代码
     * @param driver
     * @param data
     * @throws Exception
    {
    "addRCodeSave": {
    "文件名称": "test.R",
    "代码类型":"All/WithoutInput/CDH"
    }
    }
     */
    public static void addRCodeToFile(WebDriver driver, String data) throws Exception{
        AnalysisModuleLeft aml = new AnalysisModuleLeft();
        Map map= new JsonUtil().jsonToMaps(data);
        String  codeSave = map.get("addRCodeSave").toString();
        aml.addRCodeSave(driver,codeSave);
    }
    /**
     * 添加参数
     * @param driver
     * @param data
     * @throws Exception
    {
    "addParam": {
    "参数名称": "param1",
    "参数描述": "数据路径",
    "参数默认值": "123"
    },
    "checkParam": {
    "参数名称": "param1"
    }
    }
     */
    public static void addParam(WebDriver driver,String data) throws Exception{
        AnalysisModuleLeft aml = new AnalysisModuleLeft();
        Map map= new JsonUtil().jsonToMaps(data);
        String  addParamData = map.get("addParam").toString();
        String  checkParamData = map.get("checkParam").toString();
        aml.addParam(driver,addParamData);
        ToolKit.wait(2);
        aml.checkParam(driver,checkParamData);
    }
    /**
     * 添加输入
     * @param driver
     * @param data
     * @throws Exception
    {
    "addInput": {
    "输入名称": "input1",
    "输入类型": "pmml"
    },
    "checkInput": {
    "输入输出名称": "input1"
    }
    }
     */
    public static void addInput(WebDriver driver,String data) throws Exception{
        AnalysisModuleLeft aml = new AnalysisModuleLeft();
        Map map= new JsonUtil().jsonToMaps(data);
        String  addInputData = map.get("addInput").toString();
        String  checkInputData = map.get("checkInput").toString();
        aml.addInput(driver,addInputData);
        ToolKit.wait(2);
        aml.checkInput(driver, checkInputData);
    }
    /**
     * 添加输出
     * @param driver
     * @param data
     * @throws Exception
    {
    "addOutput": {
    "输出名称": "output1",
    "输出类型": "any"
    },
    "checkInput": {
    "输入输出名称": "input1"
    }
    }
     */
    public static void addOutput(WebDriver driver,String data) throws Exception{
        AnalysisModuleLeft aml = new AnalysisModuleLeft();
        Map map= new JsonUtil().jsonToMaps(data);
        String  addOutputData = map.get("addOutput").toString();
        String  checkOutputData = map.get("checkInput").toString();
        aml.addOutput(driver,addOutputData);
        ToolKit.wait(2);
        aml.checkInput(driver,checkOutputData);

    }
    /**
     * 添加本地安装包
     * @param driver
     * @param data
     * @throws Exception
    {
    "installLocalPack": {
    "安装包路径": "/Users/zhangfei/Desktop/sklearn2pmml-master.zip"
    }
    }
     */
    public static void installLocalPack(WebDriver driver,String data) throws Exception{
        AnalysisModuleLeft aml = new AnalysisModuleLeft();
        Map map= new JsonUtil().jsonToMaps(data);
        String  installLocal = map.get("installLocalPack").toString();
        aml.installLocalPack(driver,installLocal);

    }
    /**
     * 添加服务器中repo源安装包
     * @param driver
     * @param data
     * @throws Exception
    {
    "installRepoPack": {
    "安装包名称": "sklearn-pandas"
    }
    }
     */
    public static void installRepoPack(WebDriver driver,String data) throws Exception{
        AnalysisModuleLeft aml = new AnalysisModuleLeft();
        Map map= new JsonUtil().jsonToMaps(data);
        String  installRepo= map.get("installRepoPack").toString();
        aml.installRepoPack(driver,installRepo);

    }

    /**
     * 点击清空按钮 清空控制台消息
     * @param driver
     * @throws Exception
     */
    public static void clickClearConsole(WebDriver driver) throws  Exception{
        AnalysisModule am = new AnalysisModule();
        am.clickClearConsole(driver);
    }

    /**
     * 验证notebook 功能
     * @param driver
     * @param data
     * @throws Exception
     {
    "filename":"test.py"
    "checkNoteShowText":{"checktext":"print(123)"}
    }
     */

    public static void noteBookCheck(WebDriver driver,String data) throws Exception {
        Map map= new JsonUtil().jsonToMaps(data);
        String data1=map.get("checkNoteShowText").toString();
        String filename=map.get("filename").toString();
        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.getTreeFile(filename).click();
        AnalysisModule am = new AnalysisModule();
        am.clicknotebook(driver); //  编码页面点击notebook编辑
        am.checkNoteShowText(driver,data1);
        am.clicknotebookBack(driver);
        Assert.assertTrue(pp.getTreeFile(filename).isDisplayed()); //  test.py 文件显示即验证返回编码页面

    }


    /**删除文件树文件
     * @param driver
     * @param data
     * @throws Exception
     */
    public static void deleteFileTree(WebDriver driver,String data) throws Exception{
        AnalysisModuleLeft aml = new AnalysisModuleLeft();
        aml.deleteFileTree(driver,data);
    }

    /**文件树文件重命名
     * @param driver
     * @param data
     * @throws Exception
    {"文件名称":"test.py","修改名称":"test1.py"}
     */
    public static void renameFileTree(WebDriver driver,String data) throws Exception{
        AnalysisModuleLeft aml = new AnalysisModuleLeft();
        aml.renameFileTree(driver,data);

    }

}
