package com.zetyun.uitest.pageoperation.common;

import com.zetyun.data.DataParse;
import com.zetyun.datatemplate.elementtemplate.ElementTemplate;
import com.zetyun.driver.log.LogWriter;
import com.zetyun.uitest.pageoperation.AnalysisModule;
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


public class Pagelist {

    /**
     * 当前页行元素
     * @param driver
     * @param data
     * @return
     * {"模块名称":"自动建模/"数据应用/数据模块/分析模块}
     */
    public List<WebElement> getItems(WebDriver driver, String data){
    LogWriter.info(Pagelist.class,"***当前页显示行 ***");
    try {
        String elementSelectorTemplate = DataParse.GetProperties("UIElementSelectorTemplate");
     //   String filepath =  elementSelectorTemplate;
        Map map= new JsonUtil().jsonToMaps(data);
        String navi  =(String) map.get("模块名称");
        String pageList = ElementTemplate.getValues(elementSelectorTemplate, "PageList").get(navi).get("显示行");  // 显示行
        List elements= ElementUtil.findElements(driver,pageList);

        return elements;
    }catch (Exception ex){
        LogWriter.error(Pagelist.class,"***当前页显示行显示失败***");
        ex.printStackTrace();
        return null;
    }
}

    /**
     * 验证 行信息显示字符
     * @param driver
     * @param data
    {"getItems":{"导航名称":"分析模块"},
     "text":"aa#bb#cc#dd"
    }
     */
public void checkItemswithText(WebDriver driver,String data) {
    LogWriter.info(Pagelist.class,"***验证 行信息显示字符 ***");
    Map map = new JsonUtil().jsonToMaps(data);
    String navi = map.get("getItems").toString();
    List<WebElement> list =getItems(driver,navi);
    String rowwithtext = list.get(0).getText();  // 获取页面行信息
    String str = map.get("showtext").toString();
    String[] strs = null;
    if (!str.contains("#")) {
        strs = new String[]{str};
    } else {
        strs = str.split("#");
    }
    for(int i=0;i<strs.length;i++){
        Assert.assertTrue(rowwithtext.contains(strs[i]));
    }
}
    /**
     * 获取当前页显示第N行
     * @param driver
     * @return
{
"菜单":"自动建模",
 data:{"导航名称":"自动建模/"数据应用/数据模块/分析模块},
 "行数":"5"
}
     */
    public WebElement getItem(WebDriver driver,String data){
        Map map= new JsonUtil().jsonToMaps(data);
        String datas  =(String) map.get("data");
        String row  =(String) map.get("行数");
    LogWriter.info(Pagelist.class,"***获取当前页显示第 "+Integer.parseInt("row")+" 行 ***");
    try {
        return getItems(driver, datas).get(Integer.parseInt("row"));
    }catch (Exception e){
        LogWriter.error(Pagelist.class,"获取当前页第"+Integer.parseInt("row")+"行失败");
        return null;
    }
}

    /**
     * 点击对应显示行对应模块名称
     * @param driver
     * @param name
     * @throws Exception
     */
    private void clickAnalysisModulename(WebDriver driver,String name) throws Exception{
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        String modulename = ElementTemplate.getValues(filepath, "PageList").get("模块首页").get("模块名称");
        ElementUtil.findElement(driver, modulename).click();

    }
    /**
     * @param driver
     * @param data
     * @throws Exception
    {"modulename":"TestforAutomation1",
    }
     *
     */
    public void verifmoduledeatail(WebDriver driver,String data) throws Exception {
        LogWriter.info(Pagelist.class,"验证模块详情名称显示"+data);
        Map map= new JsonUtil().jsonToMaps(data);
        String modulename =map.get("modulename").toString();
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        clickAnalysisModulename(driver,modulename);   // 点击模块名称
        String modulenamedetail = ElementTemplate.getValues(filepath, "Analysis").get("模块详情").get("模块名称");
        String text=ElementUtil.findElement(driver,modulenamedetail).getText();  // 详情页显示 模块名称
        Assert.assertTrue(text.contains(modulename));
    }

    /**
     * 点击对应模块名称 点击历史纪录
     * @param driver
     * @param data
     * @throws Exception
    {"modulename":"TestforAutomation1"}
     */
    public void verifHistoryRecord(WebDriver driver,String data) throws Exception {
        LogWriter.info(Pagelist.class,"验证模块详情历史纪录"+data);
        Map map= new JsonUtil().jsonToMaps(data);
        String modulename =map.get("modulename").toString();
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        clickAnalysisModulename(driver,modulename);   // 点击模块名称
        String history = ElementTemplate.getValues(filepath, "Analysis").get("模块详情").get("历史记录");
        ElementUtil.findElement(driver,history).click();

    }

    /**
     * 点击对应模块名称 点击引用纪录
     * @param driver
     * @param data
     * @throws Exception
    {"modulename":"TestforAutomation1",
    }
     */
    public void verifyCitingRecord(WebDriver driver,String data) throws Exception {
        LogWriter.info(Pagelist.class,"验证模块详情引用纪录"+data);
        Map map= new JsonUtil().jsonToMaps(data);
        String modulename =map.get("modulename").toString();
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        clickAnalysisModulename(driver,modulename);   // 点击模块名称
        String citing = ElementTemplate.getValues(filepath, "Analysis").get("模块详情").get("引用记录");
        ElementUtil.findElement(driver,citing).click();

    }


    /**
     * 获取当前页行数
     * @param driver
     * @param
     * @return
     * {"模块名称":"自动建模/"数据应用/数据模块/分析模块}
     */

    public int getItemSize(WebDriver driver,String data){
        LogWriter.info(Pagelist.class,"***获取当前页显示行数:   ***");
        try {
            return getItems(driver, data).size();
        }catch (Exception e){
            LogWriter.info(Pagelist.class,"***获取当前页显示行数失败  ***");
      return Integer.parseInt(null);
        }
    }

    /**
     * @param driver
     * @param data
     *                 {
     *                 "操作":"上一页/下一页"
     *                 }
     */
    public void paging(WebDriver driver, String data){
        Map map= new JsonUtil().jsonToMaps(data);
        String opration  =(String) map.get("操作");
        LogWriter.info(Pagelist.class,"***点击 "+opration+"***");
        try {

            String elementSelectorTemplate = DataParse.GetProperties("UIElementSelectorTemplate");
            String filepath =  elementSelectorTemplate;
            String element = ElementTemplate.getValues(filepath, "PageList").get("翻页").get(opration);  //

            ElementUtil.findElement(driver,element).click(); // 翻页点击

        }catch (Exception ex){
            LogWriter.error(Pagelist.class,"***点击 "+opration+" 失败 请检查是否首末页！*** ");
            ex.printStackTrace();

        }
    }

    /**
     * 使用过滤后才能调用该方法
     * 删除功能
     * @param driver
     * @throws Exception
     * {"名称":"AutoTest"}
     */
    public void delete(WebDriver driver,String data ) throws Exception {
        Map maps= new JsonUtil().jsonToMaps(data);
        String deleteName  =(String) maps.get("名称");
        LogWriter.info(Pagelist.class,"删除 "+deleteName);

        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "PageList");
        Map<String, String> map = elementConfig.get("删除操作");
        String deleteSure = map.get("删除确定");
        String ele = map.get("删除");

        List<WebElement> list = ElementUtil.findElements(driver,ele);
        DynamicElementUtil pp = new DynamicElementUtil(driver);
        for(WebElement e:list){
            e.click();
            if(pp.deleteSure(deleteName).isDisplayed()){   // 删除确认
                ElementUtil.findElement(driver,deleteSure).click();
            }
        }
    }


    /**
     * 点击全选框
     * @param driver
     * @return
     * @throws IOException
     */
    public Boolean selectAll(WebDriver driver) throws IOException {
        LogWriter.info(Pagelist.class,"点击全选框 ");
        try{
            String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
            Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "PageList");
            Map<String, String> map = elementConfig.get("删除操作");
            String selectall = map.get("全选");
            WebElement ele =ElementUtil.findElement(driver,selectall);
            ToolKit.JavaScriptClick(driver,ele);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    /**
     * 批量删除步骤
     * @param driver
     * @throws Exception
     */
    private void deleteBatchOpra(WebDriver driver)throws Exception{
        LogWriter.info(Pagelist.class,"批量删除");
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "PageList");
        Map<String, String> map = elementConfig.get("删除操作");
        String selectall = map.get("批量删除");
        ElementUtil.findElement(driver,selectall).click();
        ToolKit.waitForWebElementVisible(driver,ElementUtil.findElement(driver,map.get("批量删除确定提示")));
        if(ElementUtil.findElement(driver,map.get("批量删除确定提示")).isDisplayed()){
            ElementUtil.findElement(driver,map.get("批量删除确定按钮")).click();
        }
    }
    /**
     * 执行步骤 点击全选 --> 批量删除
     * 批量删除
     * @param driver
     * @throws Exception
     */
    public void deleteBatch(WebDriver driver)throws Exception{
        selectAll(driver);
        deleteBatchOpra(driver);
    }

    /**
     * 下拉选择页面显示数量
     * @param driver
     * @param number
     * @throws Exception
     */
    public Integer selectListShowNumber(WebDriver driver,String number) throws Exception {
           selectListNumber(driver,number+" 条/页");
           return Integer.parseInt(number);
    }

    /**
     * 下拉选择页面显示数量
     * @param driver
     * @param number
     * @return
     * @throws Exception
     */
    private Boolean selectListNumber(WebDriver driver,String number) throws Exception {
        LogWriter.info(Pagelist.class,"选择显示页数"+number);
        try {
            String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
            Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "PageList");
            Map<String, String> map = elementConfig.get("翻页");
            String showPageCountDropDown = map.get("显示数量");

            ElementUtil.findElement(driver, showPageCountDropDown).click(); // 点击 选择显示数量下拉框
            DynamicElementUtil pp = new DynamicElementUtil(driver);
            pp.dropDownList(number).click();
            String pageCount = ElementUtil.findElement(driver, showPageCountDropDown).getText();
            Assert.assertTrue(pageCount.contains(number));  // 下拉框显示已选数量
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 返回当前页消息条数  总消息数  每页显示数
     * @param driver
     * @param navi   导航名称
     * @return
     * @throws IOException
     */
    public Map<String, Integer>  needPaging(WebDriver driver,String navi) throws IOException {
        Map<String, Integer> testMap = new HashMap<String, Integer>();
        Pagelist p = new Pagelist();
        int listSize =p.getItems(driver,navi).size();  // 当前页数量
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        testMap.put("rowNum",listSize);  // 当前页数量
        String str = ElementTemplate.getValues(elementSelector, "Table").get("统计信息").get("统计结果").trim();
        String totalNu=  ElementUtil.findElement(driver, str).getText();// 信息总数量
        int totalNum=ToolKit.getIntFromString(totalNu);   // 总数
        testMap.put("totalNum",listSize);  // 总数量

        String modulename = ElementTemplate.getValues(elementSelector, "Table").get("统计信息").get("列表行");
        String moduleName=   ElementUtil.findElement(driver, modulename).getText(); // 模块名称

        Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(elementSelector, "PageList");
        String showNum= elementConfig.get("翻页").get("显示数量");  // 分页 每页x条信息
        String  earchPageShow= ElementUtil.findElement(driver, showNum).getText();
        int showInEachPage= ToolKit.getIntFromString(earchPageShow);  // x 条/页
        testMap.put("pagingNum",listSize);  // m每页显示多少条
        return testMap;
    }

    /**
     * 批量导出
     * @param driver
     * @throws IOException
     * @throws InterruptedException
     * {"菜单":"分析模块"}
     */
    public void batchExport(WebDriver driver,String data) throws IOException, InterruptedException {
        LogWriter.info(Pagelist.class,"批量导出"+data);
        Map maps= new JsonUtil().jsonToMaps(data);
        String navi  =(String)maps.get("菜单");
        selectAll(driver);
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String downloadDir = DataParse.GetProperties("DataModuleDownloaddfile");

        Map<String, String> map = ElementTemplate.getValues(elementSelector, "PageList").get(navi);
        List<WebElement> list= ElementUtil.findElements(driver,map.get("显示行复选名称"));  // 显示行复选名称

        ElementUtil.findElement(driver,map.get("批量导出")).click();  // 点击批量导出

        ElementUtil.findElement(driver,map.get("导出按钮")).click();  // 点击导出按钮

        int num=ToolKit.getIntFromString(ElementUtil.findElement(driver,map.get("导出结果提示")).getText());

        ElementUtil.findElement(driver,map.get("导出关闭按钮")).click();// 点击关闭按钮
        Assert.assertTrue(num==list.size()); // 导出结果与页面选中数量相同

        for(WebElement element:list){      // 取出导出模块名称
            String name=element.getText()+".zip";
            String checkfile=  ToolKit.getFileFullName(downloadDir,name);  // 验证模块下载成功
            Assert.assertTrue(!checkfile.equals(""));
        }
    }

    /**
     *  点击 返回上一页
     * @param driver
     * @throws Exception
     */
    public void backTo(WebDriver driver) throws Exception {
        LogWriter.info(AnalysisModule.class," 点击返回上一页  ");
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        String back = ElementTemplate.getValues(filepath, "Analysis").get("编码").get("返回上一页");
        ElementUtil.findElement(driver, back).click();
    }
    /**
     * @param driver
     * @param data
     * @throws Exception
     * {"菜单":"分析模块"}
     */
    public void exports(WebDriver driver,String data) throws Exception {
        LogWriter.info(Pagelist.class,"导出"+data);
        Map maps= new JsonUtil().jsonToMaps(data);
        String navi  =(String)maps.get("菜单");
        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String downloadDir = DataParse.GetProperties("DataModuleDownloaddfile");

        Map<String, String> map = ElementTemplate.getValues(elementSelector, "PageList").get(navi);
      String modulename= ElementUtil.findElement(driver,map.get("模块名称")).getText();  // 模块名称

        new Table().clicktableExport(driver);// 点击导出按钮

        ElementUtil.findElement(driver,map.get("导出按钮")).click();  // 导出页面导出按钮点击
        int num=ToolKit.getIntFromString(ElementUtil.findElement(driver,map.get("导出结果提示")).getText());
        ElementUtil.findElement(driver,map.get("导出关闭按钮")).click();// 点击关闭按钮
        Assert.assertTrue(num==1); // 导出结果与页面选中数量相同

        String name=modulename+".zip";
            String checkfile=  ToolKit.getFileFullName(downloadDir,name);  // 验证模块下载成功
            Assert.assertTrue(!checkfile.equals(""));

    }

}
