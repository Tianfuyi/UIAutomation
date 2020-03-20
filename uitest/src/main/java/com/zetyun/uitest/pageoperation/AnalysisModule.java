package com.zetyun.uitest.pageoperation;

import com.zetyun.data.DataParse;
import com.zetyun.datatemplate.elementtemplate.ElementTemplate;
import com.zetyun.driver.log.LogWriter;
import com.zetyun.uitest.pageoperation.common.Search;
import com.zetyun.uitest.utility.DynamicElementUtil;
import com.zetyun.uitest.utility.ElementUtil;
import com.zetyun.uitest.utility.JsonUtil;
import com.zetyun.uitest.utility.ToolKit;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Map;

public class AnalysisModule {



    /**
     * 点击模块名称 链接到详情页
     * @param driver
     * @param name
     * @throws Exception
     * {"modulename":"TestforAutomation1"}
     */
    private void clickAnalysisModulename(WebDriver driver,String name) throws Exception{
        LogWriter.info(AnalysisModule.class," 点击模块名称 ");
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        String modulename = ElementTemplate.getValues(filepath, "Analysis").get("模块首页").get("分析模块名称");
      ElementUtil.findElement(driver, modulename).click();

            }


    /**
     * 点击测试按钮
     * @param driver
     * @throws Exception
     */
    public void clickDebugBtn(WebDriver driver) throws Exception {
        LogWriter.info(AnalysisModule.class," 点击测试按钮 ");
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        String debugBtn = ElementTemplate.getValues(filepath, "Analysis").get("编码").get("调试按钮");
        ElementUtil.findElement(driver, debugBtn).click();
    }


    /**
     * 验证控制台 输出内容
     * @param driver
     * @throws Exception
     *
     * {"consolemsg":""}
     */
    public void verifyConsoleMsg(WebDriver driver,String data) throws Exception {
        LogWriter.info(AnalysisModule.class," 验证控制台输出内容 ");
        Map map= new JsonUtil().jsonToMaps(data);
        String datas = (String) map.get("consolemsg");// 模块名称
        String datasp[]=datas.split("#");

        DynamicElementUtil p= new DynamicElementUtil(driver);
        ToolKit.waitForWebElementVisible(driver,p.consoleText(datas),180);
        for(String s:datasp){
            Assert.assertTrue(p.consoleText(s).isDisplayed());
        }

    }


    /** 点击测试按钮
     * @param driver
     *
     *
     */
    public void clickTestBtn(WebDriver driver) throws Exception {
        LogWriter.info(AnalysisModule.class," 点击测试按钮 ");
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        String testBtn = ElementTemplate.getValues(filepath, "Analysis").get("编码").get("测试按钮");
        ElementUtil.findElement(driver, testBtn).click();
    }

    /**
     * 点击测试按钮直接执行测试
     * @param driver
     * @throws Exception
     */
    public void test(WebDriver driver) throws Exception {
        clickTestBtn(driver);
    }

    /**
     * 点击测试 （本地上传文件）
     * @param driver
     *
     *     {"datas":default/ c:/aa.text}
     *
     */
    public void testWithUpload(WebDriver driver,String data ) throws Exception {
        Map map= new JsonUtil().jsonToMaps(data);
        String path=(String) map.get("datas");
        clickTestBtn(driver);
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "Analysis");
        Map<String, String> map1 = elementConfig.get("编码");
        String chooseInputFile = map1.get("请选择输入文件");
        ElementUtil.findElement(driver, chooseInputFile).click();   // 点击请输入文件
        ElementUtil.findElement(driver,map1.get("更多输入文件")).click(); // 点击更多输入文件
        if(path.equals("default")){
            String filepaths = DataParse.GetProperties("TestUploadPath");
            ToolKit.upload(driver,filepaths); // 默认路径  Template\\testupload.txt
        }else {
            ToolKit.upload(driver,path);  // 场景自定义路径
        }

        ElementUtil.findElement(driver, map1.get("输入文件确定")).click();
        ElementUtil.findElement(driver, map1.get("测试确定")).click();
    }

    /**
     * 点击测试 （选择系统文件文件）
     * @param driver
     * @throws Exception
     * {"datas":"a.txt"}
     */
    public void testWithSysFile(WebDriver driver,String data ) throws Exception {
        LogWriter.info(AnalysisModule.class," 测试  ");
        Map map= new JsonUtil().jsonToMaps(data);
        String path=(String) map.get("data");

        clickTestBtn(driver);
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "Analysis");
        Map<String, String> map1 = elementConfig.get("编码");

        ElementUtil.findElement(driver, map1.get("请选择输入文件")).click();   // 点击请输入文件
        ElementUtil.findElement(driver, map1.get("其他共享目录")).click();   // 点击其他共享目录

        DynamicElementUtil pp= new DynamicElementUtil(driver);
        pp.sysFileChoose(path).click();
        ElementUtil.findElement(driver, map1.get("输入文件确定")).click();
        ElementUtil.findElement(driver, map1.get("测试确定")).click();

    }

    /**
     * @param driver
     * @param data
     * @throws Exception
     * {"版本":"",
     * "描述":""}
     */
    public void release(WebDriver driver,String data) throws Exception {
        LogWriter.info(AnalysisModule.class," 发布  ");
       // clickTestBtn(driver);
        Map map= new JsonUtil().jsonToMaps(data);
        String  disc =  map.get("描述").toString();//
        String version=map.get("版本").toString();//
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "Analysis");
        Map<String, String> map1 = elementConfig.get("编码");
       try {
           ElementUtil.findElement(driver, map1.get("发布")).click();   // 点击发布按钮
       }catch (Exception e){
           ToolKit.JavaScriptClick(driver,ElementUtil.findElement(driver, map1.get("发布")));
       }
        if(!(version==null)&&!version.equals("")) {
            WebElement ev = ElementUtil.findElement(driver, map1.get("发布版本号"));
            ev.clear();
            ev.sendKeys(version);
        }
        if(!(disc==null)&&!disc.equals("")) {
            WebElement ed = ElementUtil.findElement(driver, map1.get("发布描述"));
            ed.clear();
            ed.sendKeys(disc);
        }

        WebElement ele =ElementUtil.findElement(driver, map1.get("发布提交"));
        ToolKit.waitForWebElementClickable(driver,ele);
        ToolKit.highlightWebElement(driver,ele);
        try {
            ele.click();   // 点击提交按钮
        }catch (Exception e){
            ToolKit.JavaScriptClick(driver,ele);
        }
    }

    /**
     * 通过查询 验证发布成功
     * @param driver
     * @throws Exception
     * "releasecheck":{"模块名称":"分析模块","查询类型":"普通搜索","查询数据":"AutoTest1"}
     */
    public void checkRelease(WebDriver driver,String data) throws Exception {
        LogWriter.info(AnalysisModule.class," 验证发布成功  ");
        Map map= new JsonUtil().jsonToMaps(data);
       // String  datas = map.get("releasecheck").toString();
        ToolKit.wait(2);
        new Search().search(driver,data);
        ToolKit.wait(2);
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "Analysis");
        Map<String, String> map1 = elementConfig.get("编码");
        ToolKit.waitForWebElementVisible(driver,ElementUtil.findElements(driver,map1.get("发布成功"),180).get(0),180);
        Assert.assertTrue(ElementUtil.findElements(driver,map1.get("发布成功")).get(0).isDisplayed());
    }

    /**
     * 点击 控制台 清空按钮
     * @param driver
     * @throws Exception
     */
    public void clickClearConsole(WebDriver driver) throws Exception {
        LogWriter.info(AnalysisModule.class," 点击清空  ");
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        String clear = ElementTemplate.getValues(filepath, "Analysis").get("编码").get("清空");
        ElementUtil.findElement(driver, clear).click();
    }


    /**
     * 点击 notebook 编辑
     * @param driver
     * @throws Exception
     */
    public void clicknotebook(WebDriver driver) throws Exception {
        LogWriter.info(AnalysisModule.class," 点击 notebook 编辑 ");
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        String clear = ElementTemplate.getValues(filepath, "Analysis").get("编码").get("notebook编辑");
        ElementUtil.findElement(driver, clear).click();
    }

    /**
     * 验证notebook 显示字符
     * @param driver
     * @param data
     * @throws Exception
    {"checktext":"print(123)"}
     */
    public void checkNoteShowText(WebDriver driver,String data) throws Exception {
        LogWriter.info(AnalysisModule.class," 验证notebook 显示字符 ");
        Map map= new JsonUtil().jsonToMaps(data);
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        String clear = ElementTemplate.getValues(filepath, "Analysis").get("编码").get("notebookShowText");
        String textShows= ElementUtil.findElement(driver, clear).getText();
        String text=map.get("checktext").toString();
        Assert.assertTrue(text.contains(text));
    }

    /**
     * notebook 点击 返回
     * @param driver
     * @throws Exception
     */
    public void clicknotebookBack(WebDriver driver) throws Exception {
        LogWriter.info(AnalysisModule.class," notebook点击返回  ");
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        String clear = ElementTemplate.getValues(filepath, "Analysis").get("编码").get("notebook返回");
        ElementUtil.findElement(driver, clear).click();

    }



    /**
     * @param driver
     * @throws Exception
     * 点击历史版本
     */
    public  void clickHistory(WebDriver driver) throws Exception {
        LogWriter.info(AnalysisModule.class," 点击历史版本  ");
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        String history = ElementTemplate.getValues(filepath, "Analysis").get("编码").get("历史版本");
        ElementUtil.findElement(driver, history).click();

    }
    /**
     * @param driver
     * @throws Exception
     * 点击历史版本设置
     */
    public  void clickHistroyParamConfigBtn(WebDriver driver) throws Exception {
        LogWriter.info(AnalysisModule.class," 历史版本页面 点击参数设置按钮 ");
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        String config = ElementTemplate.getValues(filepath, "Analysis").get("编码").get("历史版本参数设置");
        ElementUtil.findElement(driver, config).click();
    }

    /**
     *
     *验证 历史版本 数据
     * @param driver
     * @throws Exception
     *
     * {"参数名称":"param1",
     * "参数允许值":"任意值"}
     */

    public  void checkHistory(WebDriver driver,String data) throws Exception {
        LogWriter.info(AnalysisModule.class," 证 历史版本 数据 ");
        Map maps= new JsonUtil().jsonToMaps(data);
        String paramName=maps.get("import").toString();
        String paramAllow=maps.get("").toString();

        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "Analysis");
        Map<String, String> map = elementConfig.get("编码");

        clickHistory(driver); // 点击历史版本
        clickHistroyParamConfigBtn(driver); // 点击参数设置
        String paramrow = map.get("历史版本参数行");

        String paramList = ElementUtil.findElements(driver, paramrow).get(0).getText();
        Assert.assertTrue(paramList.contains(paramName)&paramList.contains(paramAllow));  // 判断最新版本参数名称  参数限制 显示正确
        ElementUtil.findElements(driver,map.get("关闭按钮")).get(1).click();  // 关闭 历史参数设置显示
        ElementUtil.findElement(driver,map.get("版本选择下拉框")).click();// 点击版本选择下拉框
        ElementUtil.findElements(driver,map.get("版本")).get(1).click();// 选择最底下得版本（0.1第一版）
        ElementUtil.findElement(driver,map.get("版本删除")).click();  // 删除版本 0.2
        ElementUtil.findElement(driver,map.get("版本删除确定按钮")).click(); // 确定删除

        Assert.assertTrue(!ElementUtil.findElement(driver,map.get("版本选择下拉框")).getText().contains("0.2")); // 判断0.2版本已被删除
    }

    /**
     * 导入功能
     * @param driver
     * @throws Exception
    {"文件名":"",
    "新名称":""}
     */
    public void imports(WebDriver driver, String data) throws Exception {
        LogWriter.info(AnalysisModule.class," 导入 ");
       Map maps=new JsonUtil().jsonToMaps(data);
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "Analysis");
        Map<String, String> map = elementConfig.get("编码");

        String path = DataParse.GetProperties("DataModuleUploadpath");
        String file = path + maps.get("文件名");
        DynamicElementUtil DynamicElementUtil = new DynamicElementUtil(driver);
        DynamicElementUtil.uploadFile().sendKeys(file);
        WebElement ele =ElementUtil.findElement(driver,map.get("导入新名称"));

        ele.clear();
        ele.sendKeys(maps.get("新名称").toString());
        ElementUtil.findElement(driver,map.get("导入描述")).click();

        ElementUtil.findElement(driver,map.get("导入提交按钮")).click();
    }
}
