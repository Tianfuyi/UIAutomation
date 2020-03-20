package com.zetyun.uitest.pageoperation;

import com.zetyun.data.DataParse;
import com.zetyun.datatemplate.elementtemplate.ElementTemplate;
import com.zetyun.driver.log.LogWriter;
import com.zetyun.uitest.pageoperation.common.Table;
import com.zetyun.uitest.utility.DynamicElementUtil;
import com.zetyun.uitest.utility.ElementUtil;
import com.zetyun.uitest.utility.JsonUtil;
import com.zetyun.uitest.utility.ToolKit;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

public class AnalysisModuleLeft {

    /**
     * 验证容器状态，启动为true，其他状态为false
     * @param driver
     */
    public boolean checkDockerStat(WebDriver driver) throws Exception{
        boolean running = false;
        LogWriter.info(Table.class,"验证容器是否启动");
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String dockerInfo = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("容器状态");
        String info = ElementUtil.findElement(driver,dockerInfo).getText();
        if(info.equals("正常")){
            running = true;
        }else
        {
            running = false;
        }
        return running;
    }

    /**
     * 启动容器
     * @param driver
     */
    public void startDocker(WebDriver driver) throws Exception{

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String dockerInfo = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("容器状态");
        String dockerPull = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("容器下拉");
        String dockerOn = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("启动");
        String dockerFinish = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("启动完毕");
        String info = ElementUtil.findElement(driver,dockerInfo).getText();
        if(info.equals("未启动")){
            ToolKit.waitForWebElementClickable(driver,ElementUtil.findElement(driver,dockerPull));
           ToolKit.JavaScriptClick(driver,ElementUtil.findElement(driver,dockerPull));
            ToolKit.waitForWebElementClickable(driver,ElementUtil.findElement(driver,dockerOn));
            ToolKit.JavaScriptClick(driver, ElementUtil.findElement(driver,dockerOn));
            ToolKit.waitForWebElementVisible(driver,ElementUtil.findElement(driver,dockerFinish,180),180);
            //添加启动验证
            Assert.assertTrue(checkDockerStat(driver));
        }

    }
    /**
     * 启动或停止容器
     * @param driver
     */
    public void stopDocker(WebDriver driver) throws Exception{

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String dockerInfo = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("容器状态");
        String dockerPull = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("容器下拉");
        String dockerOff = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("停止");
        String dockerExit = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("未启动");
        String info = ElementUtil.findElement(driver,dockerInfo).getText();
        if(info.equals("正常")){
            ToolKit.waitForWebElementClickable(driver,ElementUtil.findElement(driver,dockerPull));
            ToolKit.JavaScriptClick(driver,ElementUtil.findElement(driver,dockerPull));
            ToolKit.waitForWebElementClickable(driver,ElementUtil.findElement(driver,dockerOff));
            ToolKit.JavaScriptClick(driver, ElementUtil.findElement(driver,dockerOff));
            ToolKit.waitForWebElementVisible(driver,ElementUtil.findElement(driver,dockerExit,180),180);
            //添加停止验证
            Assert.assertTrue(!checkDockerStat(driver));
        }

    }

    /**
     * 添加文件或文件夹
     * @param driver
     * @param data
    {
    "addFile": {
    "文件名称": "test.py",
    "文件类型": "添加文件/添加文件夹"
    }
    }
     *
     */
    public void addFile(WebDriver driver,String data) throws Exception{

        Map map= new JsonUtil().jsonToMaps(data);
        String  fileName =  map.get("文件名称").toString();
        String fileType = map.get("文件类型").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String fileTree = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("文件树");
        String fileInput = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("新建文件输入框");

        ToolKit.rightMouseClick(driver,ElementUtil.findElement(driver,fileTree));
        DynamicElementUtil pp = new DynamicElementUtil(driver);
        ToolKit.JavaScriptClick(driver, pp.addFile(fileType));
        ElementUtil.findElements(driver,fileInput).get(0).sendKeys(fileName);
        ElementUtil.findElements(driver,fileInput).get(0).sendKeys(Keys.ENTER);

//        String fileTreeV = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("文件名称");
//        List<WebElement> es = ElementUtil.findElements(driver,fileTreeV);
//        Thread.sleep(3000);
//        boolean finded = false;
//        for (int i = 0;i<es.size();i++){
//            String fileTreeName = es.get(i).getText();
//            finded = fileTreeName.equals(fileName);
//            if (finded){
//                break;
//            }
//        }
//        Assert.assertTrue(finded);

    }
    /**
     * 验证文件树中是否含有名称为filename的文件或文件夹
     * @param driver
     * @param data
    {
    "checkFileTree": {
    "文件名称": "test.py"
    }
    }
     */
    public void checkFileTree(WebDriver driver,String data) throws Exception{
        boolean finded = false;
        Map map= new JsonUtil().jsonToMaps(data);
        String  fileName =  map.get("文件名称").toString();
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String fileTree = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("文件名称");
        List<WebElement> es = ElementUtil.findElements(driver,fileTree);
        for (int i = 0;i<es.size();i++){
            String fileTreeName = es.get(i).getText();
            finded = fileTreeName.equals(fileName);
            if (finded){
                break;
            }
        }
        Assert.assertTrue(finded);
    }

    /**删除文件树文件
     * @param driver
     * @param data
     * @throws Exception
 {"文件名称": "test1.py"}
     *
     */
    public void deleteFileTree(WebDriver driver,String data) throws Exception{
        LogWriter.info(AnalysisModuleLeft.class,"删除文件"+data);
        Map map= new JsonUtil().jsonToMaps(data);
        String  fileName =  map.get("文件名称").toString();
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String fileTree = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("文件名称");
        String deleteBtn =ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("文件树删除");
        List<WebElement> es = ElementUtil.findElements(driver,fileTree);
        for(WebElement e:es){
            if(e.getText().contains(fileName)){
                ToolKit.rightMouseClick(driver,e);  // 右键文件
                ElementUtil.findElement(driver,deleteBtn).click();   // 删除
            }
        }
        ToolKit.wait(2);
        Assert.assertTrue(!driver.getPageSource().contains(fileName));
    }

    /**文件树文件重命名
     * @param driver
     * @param data
     * @throws Exception
     * {"文件名称":"test.py","修改名称":"test1.py"}
     *
     */
    public void renameFileTree(WebDriver driver,String data) throws Exception{
        LogWriter.info(AnalysisModuleLeft.class,"重命名文件为" +data);
        Map map= new JsonUtil().jsonToMaps(data);
        String  fileName =  map.get("文件名称").toString();
        String newName=map.get("修改名称").toString();
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String fileTree = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("文件名称");
        String rename =ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("文件树重命名");
        String fileInput = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("新建文件输入框");
        List<WebElement> es = ElementUtil.findElements(driver,fileTree);
        for(WebElement e:es){
            if(e.getText().contains(fileName)){
                ToolKit.rightMouseClick(driver,e);  // 右键文件
                ElementUtil.findElement(driver,rename).click();   // 点击重命名
                ElementUtil.findElement(driver,fileInput).sendKeys(newName);
                ElementUtil.findElement(driver,fileInput).sendKeys(Keys.ENTER);
            }
        }

        ToolKit.wait(2);
        Assert.assertTrue(driver.getPageSource().contains(newName));
    }
    /**
     * 添加py文件内容并保存
     * @param driver
     * @param data
    {
    "addPyCodeSave": {
    "文件名称": "test.py",
    "代码类型":"All/WithoutInput/CDH"
    }
    }
     */
    public void addPyCodeSave(WebDriver driver, String data) throws Exception{

        Map map= new JsonUtil().jsonToMaps(data);
        String  fileName =  map.get("文件名称").toString();
        String fileType = map.get("代码类型").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String codeContent = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("代码输入框");
        String icon=ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("未保存"); // 维保村图标
        String codeClose = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("代码关闭");
        String makesure = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("保存确定");
        DynamicElementUtil pp = new DynamicElementUtil(driver);
        Actions action = new Actions(driver);
        action.doubleClick(pp.getTreeFile(fileName)).perform();
        //读取文件内容
        StringBuffer strB = new StringBuffer("");
        //FileReader reader = new FileReader("/Users/zhangfei/Desktop/copy1.txt");
        String filePath = DataParse.GetProperties("CodeFilePathPython");
        switch (fileType){
            case "All":
                 filePath = DataParse.GetProperties("CodeFilePathPython");
                 break;
            case "WithoutInput":
                 filePath = DataParse.GetProperties("CodeFilePathPythonWithoutInput");
                 break;
            case "CDH":
                 filePath = DataParse.GetProperties("CodeFilePathPythonCdh");
                 break;

        }
        FileReader reader = new FileReader(filePath);
        BufferedReader br = new BufferedReader(reader);

        String str = null;

        while((str = br.readLine()) != null) {
            strB.append(str + "\r\n");
        }

        br.close();
        reader.close();


        WebElement e1 = ElementUtil.findElement(driver,codeContent);
        e1.sendKeys(strB);
        ToolKit.mouseOver(driver,ElementUtil.findElement(driver,icon));
        ElementUtil.findElement(driver,codeClose).click();
        WebElement ele =ElementUtil.findElement(driver,makesure);
        ToolKit.highlightWebElement(driver,ele);
        ToolKit.waitForWebElementClickable(driver,ele);
        ele.click();

    }

    /**
     * 添加R文件内容并保存
     * @param driver
     * @param data
    {
    "addRCodeSave": {
    "文件名称": "test.R",
    "代码类型":"All/WithoutInput/CDH"
    }
    }
     */
    public void addRCodeSave(WebDriver driver, String data) throws Exception{

        Map map= new JsonUtil().jsonToMaps(data);
        String  fileName =  map.get("文件名称").toString();
        String fileType = map.get("代码类型").toString();


        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String codeContent = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("代码输入框");
        String codeClose = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("代码关闭");
        DynamicElementUtil pp = new DynamicElementUtil(driver);
        Actions action = new Actions(driver);
        action.doubleClick(pp.getTreeFile(fileName)).perform();
        //读取文件内容
        StringBuffer strB = new StringBuffer("");
        //FileReader reader = new FileReader("/Users/zhangfei/Desktop/copy1.txt");
        String filePath = DataParse.GetProperties("CodeFilePathR");
        switch (fileType){
            case "All":
                filePath = DataParse.GetProperties("CodeFilePathR");
                break;
            case "WithoutInput":
                filePath = DataParse.GetProperties("CodeFilePathRWithoutInput");
                break;
            case "CDH":
                filePath = DataParse.GetProperties("CodeFilePathRCdh");
                break;

        }

        FileReader reader = new FileReader(filePath);
        BufferedReader br = new BufferedReader(reader);

        String str = null;

        while((str = br.readLine()) != null) {
            strB.append(str + "\r\n");
        }

        br.close();
        reader.close();


        WebElement e1 = ElementUtil.findElement(driver,codeContent);
        e1.sendKeys(strB);
        e1.sendKeys(Keys.CONTROL,"s");
        e1.sendKeys(Keys.COMMAND,"s");
        ElementUtil.findElement(driver,codeClose).click();


    }

    /**
     * 选择入口文件
     * @param driver
     * @param data
    {
    "chooseRunFile": {
    "入口文件名称": "test.py"
    }
    }
     */
    public void chooseRunFile(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  fileName =  map.get("入口文件名称").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String enterFile = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("入口文件");
        ElementUtil.findElement(driver,enterFile).click();
        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.chooseFile(fileName).click();
        //验证文件是否选中
        String enterName = ElementUtil.findElement(driver,enterFile).getText();
        Assert.assertTrue(enterName.equals(fileName));
    }
    /**
     * 添加参数
     * @param driver
     * @param data
    {
    "addParam": {
    "参数名称": "param1",
    "参数描述": "数据路径",
    "参数默认值": "123"
    }
    }
     */
    public void addParam(WebDriver driver,String data) throws Exception{

        Map map= new JsonUtil().jsonToMaps(data);
        String  paraNameStr =  map.get("参数名称").toString();
        String  paraDesStr =  map.get("参数描述").toString();
        String  paraDefaultStr =  map.get("参数默认值").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String paraManager = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("参数管理");
        String newPara = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("创建参数");
        String paraName = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("参数名");
        String paraType = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("参数类型");
        String paraDes = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("参数描述");
        String paraString = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("string");
        String paraDefault = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("参数默认值");
        String paraSubmit = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("参数提交");
        String paraClose = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("关闭参数设置");
        ElementUtil.findElement(driver,paraManager).click();

        WebElement ef = ElementUtil.findElement(driver,newPara);
        ToolKit.waitForWebElementVisible(driver,ef,3);
        ToolKit.JavaScriptClick(driver,ef);

        ElementUtil.findElement(driver,paraName).clear();
        ElementUtil.findElement(driver,paraName).sendKeys(paraNameStr);

        ElementUtil.findElement(driver,paraType).click();
        ElementUtil.findElement(driver,paraString).click();

        ElementUtil.findElement(driver,paraDes).clear();
        ElementUtil.findElement(driver,paraDes).sendKeys(paraDesStr);

        ElementUtil.findElement(driver,paraDefault).clear();
        ElementUtil.findElement(driver,paraDefault).sendKeys(paraDefaultStr);

        ElementUtil.findElement(driver,paraSubmit).click();

        WebElement econf = ElementUtil.findElement(driver,paraClose);
        ToolKit.JavaScriptClick(driver,econf);


    }
    /**
     * 根据参数名进行验证，验证参数是否存在
     * @param driver
     * @param data
    {
    "checkParam": {
    "参数名称": "param1"
    }
    }
     */
    public void checkParam(WebDriver driver,String data)throws Exception{
        boolean finded = false;
        Map map= new JsonUtil().jsonToMaps(data);
        String  paraName =  map.get("参数名称").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String paraManager = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("参数管理");
        String paraSearch = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("参数搜索");
        String paraVarify = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("参数验证");
        String paraClose = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("关闭参数设置");
        WebElement ele =ElementUtil.findElement(driver,paraManager);
        ToolKit.waitForWebElementClickable(driver,ele);
        ToolKit.JavaScriptClick(driver,ele);
        ElementUtil.findElement(driver,paraSearch).sendKeys(paraName);
        ElementUtil.findElement(driver,paraSearch).sendKeys(Keys.ENTER);
        List<WebElement> es = ElementUtil.findElements(driver,paraVarify);
        ToolKit.wait(2);
        for (int i = 0;i<es.size();i++){
            String paraStr = es.get(i).getText();
            finded = paraStr.equals(paraName);
            if (finded){
                break;
            }

        }
        WebElement econf = ElementUtil.findElement(driver,paraClose);
        ToolKit.waitForWebElementClickable(driver,econf);
        econf.click();
        Assert.assertTrue(finded);

    }
    /**
     * 编辑参数
     * @param driver
     * @param data
    {
    "editParam": {
    "参数名称": "param1",
    "clickTableEdit": {
    "模块名称": "参数管理",
    "新描述": "修改后参数的描述"
    }
    }
    }
     */
    public void editParam(WebDriver driver,String data) throws Exception{

        Map map= new JsonUtil().jsonToMaps(data);
        String  modifyStr =  map.get("clickTableEdit").toString();
        String  paraName =  map.get("参数名称").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String paraManager = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("参数管理");
        String paraSearch = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("参数搜索");
        String paraClose = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("关闭参数设置");
        ElementUtil.findElement(driver,paraManager).click();
        ElementUtil.findElement(driver,paraSearch).sendKeys(paraName);
        ElementUtil.findElement(driver,paraSearch).sendKeys(Keys.ENTER);
        Table table = new Table();
        table.clickTableEdit(driver,modifyStr);

        WebElement econf = ElementUtil.findElement(driver,paraClose);
        ToolKit.waitForWebElementClickable(driver,econf,2);
        econf.click();

    }
    /**
     * 删除参数
     * @param driver
     * @param data
    {
    "delParam": {
    "参数名称": "param1"
    }
    }
     */
    public void delParam(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  paraName =  map.get("参数名称").toString();


        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String paraManager = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("参数管理");
        String paraSearch = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("参数搜索");
        String paraClose = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("关闭参数设置");
        ElementUtil.findElement(driver,paraManager).click();
        ElementUtil.findElement(driver,paraSearch).sendKeys(paraName);
        ElementUtil.findElement(driver,paraSearch).sendKeys(Keys.ENTER);
        Table table = new Table();
        table.clicktableDel(driver);
        WebElement econf = ElementUtil.findElement(driver,paraClose);
        ToolKit.waitForWebElementClickable(driver,econf,2);
        econf.click();


    }
    /**
     * 添加输入，每次添加一个输入
     * @param driver
     * @param data
    {
    "addInput": {
    "输入名称": "input1",
    "输入类型": "pmml"
    }
    }
     */
    public void addInput(WebDriver driver,String data) throws Exception{

        Map map= new JsonUtil().jsonToMaps(data);
        String  inputNameStr =  map.get("输入名称").toString();
        String  inputTypeStr =  map.get("输入类型").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String putManager = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("输入输出管理");
        String inputBtn = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("添加输入");
        String inputField = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("输入输出框");
        String helpBtn = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("辅助按钮");
        String typeField = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("类型框");
        String boxField = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("界面框");
        String submitBtn = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("输入提交");

        ElementUtil.findElement(driver,putManager).click();

        WebElement ew = ElementUtil.findElement(driver,inputBtn);
        ToolKit.JavaScriptClick(driver,ew);
        ElementUtil.findElement(driver,inputField).sendKeys(inputNameStr);
        ElementUtil.findElement(driver,typeField).sendKeys(inputTypeStr);
        //提交按钮选不中
        ElementUtil.findElements(driver,helpBtn).get(0).click();
        WebElement es = ElementUtil.findElement(driver,submitBtn);
        ToolKit.waitForWebElementVisible(driver,es,30);
        ToolKit.JavaScriptClick(driver,es);
    }
    /**
     * 验证putName的输入或输出的名称是否存在
     * @param driver
     * @param data
    {
    "checkInput": {
    "输入输出名称": "input1"
    }
    }
     */
    public void checkInput(WebDriver driver,String data)throws Exception{
        boolean finded = false;
        Map map= new JsonUtil().jsonToMaps(data);
        String  putName =  map.get("输入输出名称").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String putManager = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("输入输出管理");
        String helpBtn = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("辅助按钮");
        String submitBtn = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("输入提交");

        ElementUtil.findElement(driver,putManager).click();
        ToolKit.wait(2);
        List<WebElement> es = ElementUtil.findElements(driver,helpBtn);
        for (int i = 0;i<es.size();i++){
            String putStr = es.get(i).getAttribute("value");
            finded = putStr.equals(putName);
            if(finded){
                break;
            }
        }
        Assert.assertTrue(finded);
        WebElement esSub = ElementUtil.findElement(driver,submitBtn);
        ToolKit.JavaScriptClick(driver,esSub);

    }
    /**
     * 编辑输入
     * @param driver
     * @param data
    {
    "editInput": {
    "输入名称": "input1",
    "新的输入名称": "input2",
    "新的输入类型": "any"
    }
    }
     */
    public void editInput(WebDriver driver,String data) throws Exception{

        Map map= new JsonUtil().jsonToMaps(data);
        String  paraName =  map.get("输入名称").toString();
        String  nameNewStr =  map.get("新的输入名称").toString();
        String  typeNewStr =  map.get("新的输入类型").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String putManager = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("输入输出管理");
        String inputBtn = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("添加输入");
        String inputField = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("输入输出框");
        String typeField = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("类型框");
        String helpBtn = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("辅助按钮");
        String delConf = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("删除确认");
        String boxField = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("界面框");
        String submitBtn = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("输入提交");
        ElementUtil.findElement(driver,putManager).click();
        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.getParaDelBtn(paraName).click();
        ElementUtil.findElement(driver,delConf).click();

        ElementUtil.findElement(driver,inputBtn).click();
        ElementUtil.findElement(driver,inputField).sendKeys(nameNewStr);
        ElementUtil.findElement(driver,typeField).sendKeys(typeNewStr);
        ElementUtil.findElements(driver,helpBtn).get(0).click();
        ElementUtil.findElement(driver,submitBtn).click();


    }
    /**
     * 删除输入或输出
     * @param driver
     * @param data
    {
    "delput": {
    "输入输出名称": "input1"
    }
    }
     */
    public void delput(WebDriver driver,String data) throws Exception{

        Map map= new JsonUtil().jsonToMaps(data);
        String  paraName =  map.get("输入输出名称").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String putManager = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("输入输出管理");
        String submitBtn = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("输入提交");
        String delConf = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("删除确认");
        ElementUtil.findElement(driver, putManager).click();
        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.getParaDelBtn(paraName).click();
        ElementUtil.findElement(driver,delConf).click();
        ElementUtil.findElement(driver,submitBtn).click();


    }
    /**
     * 添加输出,每次添加一个输出
     * @param driver
     * @param data
    {
    "addOutput": {
    "输出名称": "output1",
    "输出类型": "any"
    }
    }
     */
    public void addOutput(WebDriver driver,String data) throws Exception{

        Map map= new JsonUtil().jsonToMaps(data);
        String  outNameStr =  map.get("输出名称").toString();
        String  outTypeStr =  map.get("输出类型").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String putManager = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("输入输出管理");
        String outputBtn = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("添加输出");
        String inputField = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("输入输出框");
        String typeField = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("类型框");
        String helpBtn = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("辅助按钮");
        String boxField = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("界面框");
        String submitBtn = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("输入提交");

        ElementUtil.findElement(driver, putManager).click();

        WebElement ew = ElementUtil.findElement(driver,outputBtn);
        ToolKit.JavaScriptClick(driver,ew);

        ElementUtil.findElement(driver,inputField).sendKeys(outNameStr);
        ElementUtil.findElement(driver,typeField).sendKeys(outTypeStr);

        ElementUtil.findElements(driver,helpBtn).get(0).click();
        WebElement es = ElementUtil.findElement(driver,submitBtn);
        ToolKit.JavaScriptClick(driver,es);
        ToolKit.wait(2);


    }
    /**
     * 编辑输出
     * @param driver
     * @param data
    {
    "editOutput": {
    "输出名称": "output1",
    "新的输出名称": "output2",
    "新的输出类型": "pmml"
    }
    }
     */
    public void editOutput(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  paraName =  map.get("输出名称").toString();
        String  nameNewStr =  map.get("新的输出名称").toString();
        String  typeNewStr =  map.get("新的输出类型").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String putManager = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("输入输出管理");
        String outputBtn = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("添加输出");
        String inputField = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("输入输出框");
        String typeField = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("类型框");
        String helpBtn = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("辅助按钮");
        String submitBtn = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("输入提交");
        String delConf = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("删除确认");
        WebElement ep = ElementUtil.findElement(driver, putManager);
        try {
            ToolKit.waitForWebElementVisible(driver,ep,5);
            ep.click();
        }catch (Exception e){
            LogWriter.error(AnalysisModuleLeft.class,"点击输入输出失败");
            e.printStackTrace();
        }
        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.getParaDelBtn(paraName).click();
        ElementUtil.findElement(driver,delConf).click();

        ElementUtil.findElement(driver,outputBtn).click();
        ElementUtil.findElement(driver,inputField).sendKeys(nameNewStr);
        ElementUtil.findElement(driver,typeField).sendKeys(typeNewStr);
        ElementUtil.findElements(driver,helpBtn).get(0).click();
        ElementUtil.findElement(driver,submitBtn).click();

    }

    /**
     * 本地包安装
     * @param driver
     * @param data
    {
    "installLocalPack": {
    "安装包名称": "sklearn2pmml-master.zip"
    }
    }
     */
    public void installLocalPack(WebDriver driver,String data) throws Exception{

        Map map= new JsonUtil().jsonToMaps(data);
        String  repopackName =  map.get("安装包名称").toString();
        String  repoPackPath = DataParse.GetProperties("PackagePath");
        String  packPathStr = repoPackPath +"/" + repopackName;

        startDocker(driver);

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String packManager = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("包管理");
        String packPath = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("上传路径");
        String packVarify = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("安装包验证");
        String packClose = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("安装关闭");

        ElementUtil.findElement(driver,packManager).click();
        ElementUtil.findElement(driver,packPath,60).sendKeys(packPathStr);

        ToolKit.waitForTextElement(driver,"安装成功");

        String varifyStr = ElementUtil.findElement(driver,packVarify).getText();
        Assert.assertTrue(varifyStr.contains("安装成功"));
        ElementUtil.findElement(driver,packClose).click();

    }


    /**
     * repo包安装
     * @param driver
     * @param data
    {
    "installRepoPack": {
    "安装包名称": "/Users/zhangfei/Desktop/sklearn2pmml-master.zip"
    }
    }
     */
    public void installRepoPack(WebDriver driver,String data) throws Exception{

        Map map= new JsonUtil().jsonToMaps(data);
        String  packName =  map.get("安装包名称").toString();

        startDocker(driver);

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String packManager = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("包管理");
        String repoInstall = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("仓库已有安装包");
        String packSearch = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("包管理查询框");
        String installBtn = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("安装按钮");
        String packVarify = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("安装确认");
        String waitFor = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("安装等待");
        String packClose = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("安装关闭");
        String clean = ElementTemplate.getValues(elementSelector, "Analysis").get("编码左").get("查询清空");

        ElementUtil.findElement(driver,packManager).click();
        WebElement repo=ElementUtil.findElement(driver,repoInstall);
        ToolKit.waitForWebElementClickable(driver,repo);
        try {
            repo.click();
        }catch (Exception e){
            ToolKit.JavaScriptClick(driver,repo);
        }
        WebElement search = ElementUtil.findElement(driver,packSearch,60);
        ToolKit.highlightWebElement(driver,search);
        ToolKit.wait(5);
        search.sendKeys(packName);
        ElementUtil.findElement(driver,packSearch).sendKeys(Keys.ENTER);
        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.getRepoPackage(packName);
        WebElement install = ElementUtil.findElements(driver,installBtn).get(0);
        ToolKit.highlightWebElement(driver,install);
        ToolKit.JavaScriptClick(driver,install);
        WebElement e1 = ElementUtil.findElement(driver,waitFor,120);
        ToolKit.waitForWebElementVisible(driver,e1,120);
        //超过2分钟未安装成功，判断为失败
        String varifyStr = ElementUtil.findElements(driver,packVarify).get(0).getText();
        Assert.assertTrue(varifyStr.contains("安装成功"));
        ElementUtil.findElement(driver,packClose).click();


    }

}
