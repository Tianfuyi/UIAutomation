package com.zetyun.uitest.pageoperation.common;

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

import java.util.List;
import java.util.Map;

public class Table {

    /**
     * 点击列表右侧操作符：删除
     * @param driver
     */
    public void clicktableDel(WebDriver driver) throws Exception{
        LogWriter.info(Table.class,"删除");
        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.clickableElement(driver,"删除").click();
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String confirm = ElementTemplate.getValues(elementSelector, "Table").get("删除").get("确定");
        ElementUtil.findElement(driver,confirm).click();

    }
    /**
     * 点击列表右侧操作符：工作流
     * @param driver
     */
    public void clicktableWorkFlow(WebDriver driver) throws Exception{
        LogWriter.info(Table.class,"点击工作流");
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.clickableElement(driver,"工作流").click();
        String codeVarify = ElementTemplate.getValues(elementSelector, "Table").get("分析模块").get("历史版本");
        WebElement eVar = ElementUtil.findElement(driver,codeVarify);
        ToolKit.waitForWebElementVisible(driver,eVar,3);
        Assert.assertTrue(eVar.isDisplayed());

    }
    /**
     * 点击列表右侧操作符：复制
     * @param driver
     * @param data
    {
    "clicktableCopy": {
    "模块名称": "数据模块"
    }
    }
     */
    public void clicktableCopy(WebDriver driver,String data) throws Exception{
        LogWriter.info(Table.class,"点击复制");
        Map map= new JsonUtil().jsonToMaps(data);
        String  moduleName =  map.get("模块名称").toString();// 模块名称

        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.clickableElement(driver,"复制").click();
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String confirm = ElementTemplate.getValues(elementSelector, "Table").get(moduleName).get("复制确定");
        WebElement er = ElementUtil.findElement(driver,confirm);
        ToolKit.setScroll(driver,er);
        er.click();


    }

    /**
     * 点击列表右侧操作符：工作流，复制，删除，编辑，授权，运行,查看
     * @param driver
     * @param data
    {
    "clickTableEdit": {
    "模块名称": "数据模块",
    "新描述": "更新描述"
    }
    }
     */
    public void clickTableEdit(WebDriver driver,String data) throws Exception{
        LogWriter.info(Table.class,"点击编辑");
        Map map= new JsonUtil().jsonToMaps(data);
        String  moduleName =  map.get("模块名称").toString();// 模块名称
        String newDesc = map.get("新描述").toString();

        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.clickableElement(driver,"编辑").click();
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String desc = ElementTemplate.getValues(elementSelector, "Table").get(moduleName).get("描述");
        String confirm = ElementTemplate.getValues(elementSelector, "Table").get(moduleName).get("编辑确定");
        ElementUtil.findElement(driver,desc).clear();
        ElementUtil.findElement(driver,desc).sendKeys(newDesc);
        WebElement e = ElementUtil.findElement(driver,confirm);
        ToolKit.setScroll(driver,e);
        e.click();

    }
    /*
     * @param driver
     * @param data
    {
    "checkDesc": {
    "模块名称": "数据模块",
    "新描述": "更新描述"
    }
    }

     */
    public void checkDesc(WebDriver driver,String data)throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  moduleName =  map.get("模块名称").toString();// 模块名称
        String newDesc = map.get("新描述").toString();

        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.clickableElement(driver,"编辑").click();
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String desc = ElementTemplate.getValues(elementSelector, "Table").get(moduleName).get("描述");
        String confirm = ElementTemplate.getValues(elementSelector, "Table").get(moduleName).get("编辑确定");
        String varifyStr = ElementUtil.findElement(driver,desc).getText();
        Assert.assertTrue(varifyStr.equals(newDesc));
        WebElement e = ElementUtil.findElement(driver,confirm);
        ToolKit.setScroll(driver,e);
        e.click();


    }
    /**
     * 点击列表右侧操作符：授权
     * @param driver
     * @param data
    {
    "addTableAuth": {
    "用户名": "abc@TEST.COM",
    "授权": "读取"
    }
    }
     */
    public void addTableAuth(WebDriver driver,String data) throws Exception{
        LogWriter.info(Table.class,"添加授权");
        Map map= new JsonUtil().jsonToMaps(data);
        String  userName =  map.get("用户名").toString();
        String auths = map.get("授权").toString();

        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.clickableElement(driver,"授权").click();
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String addAuth = ElementTemplate.getValues(elementSelector, "Table").get("授权").get("添加");
        String confirm = ElementTemplate.getValues(elementSelector, "Table").get("授权").get("确定");
        String closeAuth = ElementTemplate.getValues(elementSelector, "Table").get("授权").get("关闭");
        ElementUtil.findElement(driver,addAuth).click();
        pp.addAuth(driver,userName,auths);
        ElementUtil.findElement(driver,confirm).click();
        WebElement e = ElementUtil.findElement(driver,closeAuth);
        Thread.sleep(2000);
        e.click();

        //driver.findElement(By.xpath("//*[@class='ant-modal-mask']/..//*[@class='ant-modal-close']")).click();
    }
    /**
     * @param driver webdriver
     * @param data
    {
    "delAuthApp": {
    "删除授权": "读取"
    }
    }
     * @throws Exception
     * 删除授权
     */
    public void delAuthApp(WebDriver driver, String data) throws Exception {
        LogWriter.info(Table.class,"删除授权");
        Map map= new JsonUtil().jsonToMaps(data);
        String  auths =  map.get("删除授权").toString();

        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.clickableElement(driver,"授权").click();
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String delRead = ElementTemplate.getValues(elementSelector, "Table").get("授权").get("删除读取");
        String delEdit = ElementTemplate.getValues(elementSelector, "Table").get("授权").get("删除编辑");
        String delRun = ElementTemplate.getValues(elementSelector, "Table").get("授权").get("删除运行");
        String confirm = ElementTemplate.getValues(elementSelector, "Table").get("删除").get("确定");
        String closeAuth = ElementTemplate.getValues(elementSelector, "Table").get("授权").get("关闭");
        switch (auths){
            case "读取":
                ElementUtil.findElement(driver,delRead).click();
                ElementUtil.findElement(driver,confirm).click();
                break;
            case "编辑":
                ElementUtil.findElement(driver,delEdit).click();
                ElementUtil.findElement(driver,confirm).click();
                break;
            case "运行":
                ElementUtil.findElement(driver,delRun).click();
                ElementUtil.findElement(driver,confirm).click();
                break;

        }
        Thread.sleep(3000);
        ElementUtil.findElement(driver,closeAuth).click();

    }
    /**
     * 点击列表右侧操作符：工作流，复制，删除，编辑，授权，运行,查看
     * @param driver
     */
    public void clicktableRun(WebDriver driver) throws Exception{
        LogWriter.info(Table.class,"点击运行");
        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.clickableElement(driver,"运行").click();

    }
    /**
     * 点击列表右侧操作符：编码
     * @param driver
     */
    public void clicktableCode(WebDriver driver) throws Exception{
        LogWriter.info(Table.class,"点击编码");
        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.clickableElement(driver,"编码").click();
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String codeVarify = ElementTemplate.getValues(elementSelector, "Table").get("分析模块").get("历史版本");
        WebElement eVar = ElementUtil.findElement(driver,codeVarify);
        ToolKit.waitForWebElementVisible(driver,eVar,3);
        Assert.assertTrue(eVar.isDisplayed());

    }
    /**
     * 点击列表右侧操作符：查看
     * @param driver
     * @param data 模块名称
    {
    "clicktableView": {
    "模块类型": "数据模块"
    }
    }
     */
    public void clicktableView(WebDriver driver,String data) throws Exception{
        LogWriter.info(Table.class,"点击查看");
        Map map= new JsonUtil().jsonToMaps(data);
        String  moduleName =  map.get("模块类型").toString();// 模块类型

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String viewVarify = ElementTemplate.getValues(elementSelector, "Table").get(moduleName).get("查看验证");
        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.clickableElement(driver,"查看").click();
        WebElement eVar = ElementUtil.findElement(driver,viewVarify);
        ToolKit.waitForWebElementVisible(driver,eVar,2);
        Assert.assertTrue(eVar.isDisplayed());

    }
    /**
     * 点击列表右侧操作符：查看
     * @param driver
     * @param data 模块名称
    {
    "checkPageInfo": {
    "模块名称": " test_0001_dtype"
    }
    }
     */
    public void checkPageInfo(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  moduleName =  map.get("模块名称").toString();// 模块名称

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String viewVarify = ElementTemplate.getValues(elementSelector, "Table").get("验证").get("页面验证");

        WebElement ev = ElementUtil.findElement(driver,viewVarify);
        ToolKit.waitForWebElementVisible(driver,ev,180);
        String nameStr = ev.getText();
        Assert.assertTrue(nameStr.contains(moduleName));
    }
    /**
     * 点击列表右侧操作符：导出
     * @param driver
     */
    public void clicktableExport(WebDriver driver) throws Exception{
        LogWriter.info(Table.class,"点击导出");
        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.clickableElement(driver,"导出").click();

    }
    /**
     * 点击列表右侧操作符：暂停
     * @param driver
     */
    public void clicktableStop(WebDriver driver) throws Exception{
        LogWriter.info(Table.class,"点击暂停");
        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.clickableElement(driver,"暂停").click();

    }
    /**
     * 点击列表右侧操作符：设计
     * @param driver
     */
    public void clicktableDesign(WebDriver driver) throws Exception{
        LogWriter.info(Table.class,"点击设计");
        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.clickableElement(driver,"设计").click();

    }
    /**
     * 点击列表右侧操作符：编辑用户组
     * @param driver
     */
    public void clicktableEditUser(WebDriver driver) throws Exception{
        LogWriter.info(Table.class,"点击编辑用户");
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String editGroup = ElementTemplate.getValues(elementSelector, "Table").get("用户列表").get("编辑用户组");
        String confirmG = ElementTemplate.getValues(elementSelector, "Table").get("用户列表").get("组确定");


        ElementUtil.findElement(driver,editGroup).click();
        ElementUtil.findElement(driver,confirmG).click();

    }
    /**
     * 点击列表右侧操作符：编辑用户资源
     * @param driver
     */
    public void clicktableEditRes(WebDriver driver) throws Exception{
        LogWriter.info(Table.class,"点击编辑资源");
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String editRes = ElementTemplate.getValues(elementSelector, "Table").get("用户列表").get("编辑资源池");
        String confirmR = ElementTemplate.getValues(elementSelector, "Table").get("用户列表").get("资源确定");

        ElementUtil.findElement(driver,editRes).click();
        ElementUtil.findElement(driver,confirmR).click();
    }


    /**
     * @param driver webdriver
     * @param data
    {
    "checkAuth": {
    "验证权限": "编辑"
    }
    }
     * @throws Exception
     * 验证模块是否有编辑,删除，运行权限
     */
    public boolean checkAuth(WebDriver driver, String data) throws Exception{
        boolean auth = false;
        LogWriter.info(Table.class,"验证权限");
        Map map= new JsonUtil().jsonToMaps(data);
        String  auths =  map.get("验证权限").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String readAuth = ElementTemplate.getValues(elementSelector, "Table").get("验证权限").get("编辑权限");
        String delAuth = ElementTemplate.getValues(elementSelector, "Table").get("验证权限").get("删除权限");
        String runAuth = ElementTemplate.getValues(elementSelector, "Table").get("验证权限").get("运行权限");
        switch (auths){
            case "编辑":
                String a = ElementUtil.findElement(driver,readAuth).getAttribute("class");
                auth = a.isEmpty();
                return auth;
            case "删除":
                String b = ElementUtil.findElement(driver,delAuth).getAttribute("class");
                auth = b.isEmpty();
                return auth;
            case "运行":
                String c = ElementUtil.findElement(driver,runAuth).getAttribute("class");
                auth = c.isEmpty();
                return auth;
        }
        return auth;

    }


    /**
     * @param driver webdriver
     * @param data
    {
    "addAppCom": {
    "评论内容": "这是一条评论"
    }
    }
     * @throws Exception
     * 对模块添加评论 （已验证）
     */
    public void addAppCom(WebDriver driver,String data) throws Exception {
        LogWriter.info(Table.class,"评论");
        Map map= new JsonUtil().jsonToMaps(data);
        String  comments =  map.get("评论内容").toString();
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String comTab = ElementTemplate.getValues(elementSelector, "Table").get("评论").get("评论tab");
        String comInput = ElementTemplate.getValues(elementSelector, "Table").get("评论").get("评论框");
        String comfirm = ElementTemplate.getValues(elementSelector, "Table").get("评论").get("发布");
        ElementUtil.findElement(driver,comTab).click();
        ElementUtil.findElement(driver,comInput).clear();
        ElementUtil.findElement(driver,comInput).sendKeys(comments);
        ElementUtil.findElement(driver,comfirm).click();
    }
    /**
     * @param driver webdriver
     * @param data
    {
    "checkAppCom": {
    "评论内容": "这是一条评论"
    }
    }
     * @throws Exception
     * 验证评论是否正确,正确返回true，错误返回false，（已验证）
     */
    public boolean checkAppCom(WebDriver driver,String data) throws Exception {
        LogWriter.info(Table.class,"验证评论");
        Map map= new JsonUtil().jsonToMaps(data);
        String  comments =  map.get("评论内容").toString();
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String comP = ElementTemplate.getValues(elementSelector, "Table").get("评论").get("评论人");
        String comQ = ElementTemplate.getValues(elementSelector, "Table").get("评论").get("评论内容");
        String userName = ElementTemplate.getValues(elementSelector, "Table").get("评论").get("用户名");
        List<WebElement> elementsP = ElementUtil.findElements(driver,comP);
        List<WebElement> elementsQ = ElementUtil.findElements(driver,comQ);
        String varifyName = ElementUtil.findElement(driver,userName).getText();
        boolean finded = false;
        for(int i =0;i<elementsP.size();i++){
            if (elementsP.get(i).getText().contains(varifyName)&&elementsQ.get(i).getText().contains(comments)){
                finded = true;
                break;
            }
        }
        return finded;
    }

    /**
     * @param driver webdriver
     * @throws Exception
     * 验证列表中的行数和统计个数是否正确（已验证）
     */
    public boolean checkTableCount(WebDriver driver) throws Exception{
        LogWriter.info(Table.class,"统计行数");

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String result = ElementTemplate.getValues(elementSelector, "Table").get("统计信息").get("统计结果");
        String tableRow = ElementTemplate.getValues(elementSelector, "Table").get("统计信息").get("列表行");
        //查找列表中行数
        List<WebElement> elements = ElementUtil.findElements(driver,tableRow);
        String appres = ElementUtil.findElement(driver,result).getText();
        if (appres.contains(String.valueOf(elements.size()))){

            return true;

        }else{
            return false;
        }
    }

    /**
     * 点击列表中模块名称
     * @param driver
     * @param data 模块名称
    {
    "clickModuleName": {
    "模块名称": "数据模块"
    }
    }
     */
    public void clickModuleName(WebDriver driver, String data) {
        Map map= new JsonUtil().jsonToMaps(data);
        String  moduleName =  map.get("模块名称").toString();
        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.clickModuleName(driver,moduleName).click();

    }
    /**
     * @param driver webdriver
     * @param data 排序方式
    {
    "sortByDesc": {
    "排序方式": "模块名称"
    }
    }
     * @throws Exception
     * 列表排序 倒序
     */

    public void sortByDesc(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  sortBy =  map.get("排序方式").toString();
        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.sortByDesc(driver,sortBy).click();
    }
    /**
     * @param driver webdriver
     * @param data 排序方式
    {
    "sortByDesc": {
    "排序方式": "模块名称"
    }
    }
     * @throws Exception
     * 列表排序 正序
     */
    public void sortByAsc(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String sortBy =  map.get("排序方式").toString();
        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.sortByAsc(driver,sortBy).click();
    }
    /**
     * @param driver webdriver
     * @param data 模块名称
    {
    "模块名称": "TEST2018"
    }

     * @throws Exception
     * 查找列表中模块的名字，不包含指定字符串即为错误
     */
    public void checkModuleName(WebDriver driver,String data)throws Exception{

        boolean finded = true;
        Map map= new JsonUtil().jsonToMaps(data);
        String moduleNameStr =  map.get("模块名称").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String moduleName = ElementTemplate.getValues(elementSelector, "Table").get("查询验证").get("模块名称");
        List<WebElement> es = ElementUtil.findElements(driver,moduleName);
        for (int i =0;i<es.size();i++){
            String tmp = es.get(i).getText();
            if(!tmp.contains(moduleNameStr)){
                if(!tmp.isEmpty()){
                    finded = false;
                    break;
                }

            }
        }

        Assert.assertTrue(finded);

    }
    /**
     * @param driver webdriver
     * @param data 创建人
    {
    "checkModuleCreator": {
    "创建人": "wangcaiyun"
    }
    }
     * @throws Exception
     * 查找列表中模块的名字，不包含指定字符串即为错误
     */
    public void checkModuleCreator(WebDriver driver,String data) throws Exception{
        boolean finded = true;
        Map map= new JsonUtil().jsonToMaps(data);
        String moduleCreatorStr =  map.get("创建人").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String moduleCreator = ElementTemplate.getValues(elementSelector, "Table").get("查询验证").get("创建人");
        List<WebElement> es = ElementUtil.findElements(driver,moduleCreator);
        for (int i =0;i<es.size();i++){
            if (!es.get(i).getText().equals(moduleCreatorStr)){
                finded = false;
                break;
            }
        }
        Assert.assertTrue(finded);

    }
    /**
     * @param driver webdriver
     * @param data 创建类型
    {
    "checkModuleType": {
    "类型": "python"
    }
    }
     * @throws Exception
     * 查找列表中模块的类型，和指定的不匹配即为错误
     */
    public void checkModuleType(WebDriver driver,String data) throws Exception{
        boolean finded = true;
        Map map= new JsonUtil().jsonToMaps(data);
        String moduleTypeStr =  map.get("类型").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String moduleType = ElementTemplate.getValues(elementSelector, "Table").get("查询验证").get("创建类型");
        List<WebElement> es = ElementUtil.findElements(driver,moduleType);
        for (int i =0;i<es.size();i++){
            if (!es.get(i).getText().equals(moduleTypeStr)){
                finded = false;
                break;
            }
        }
        Assert.assertTrue(finded);

    }
    /**
     * @param driver webdriver
     * @param data 发布状态
    {
    "checkModuleStatus": {
    "发布状态": "未发布"
    }
    }
     * @throws Exception
     * 查找列表中模块的类型，和指定的不匹配即为错误
     */
    public void checkModuleStatus(WebDriver driver,String data) throws Exception{
        boolean finded = true;
        Map map= new JsonUtil().jsonToMaps(data);
        String moduleStatusStr =  map.get("类型").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String moduleStatus = ElementTemplate.getValues(elementSelector, "Table").get("查询验证").get("发布状态");
        List<WebElement> es = ElementUtil.findElements(driver,moduleStatus);
        for (int i =0;i<es.size();i++){
            if (!es.get(i).getText().equals(moduleStatusStr)){
                finded = false;
                break;
            }
        }
        Assert.assertTrue(finded);

    }

}
