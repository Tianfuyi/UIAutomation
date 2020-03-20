package com.zetyun.uitest.pageoperation.common;

import com.alibaba.fastjson.JSON;
import com.zetyun.data.DataParse;
import com.zetyun.datatemplate.elementtemplate.ElementTemplate;
import com.zetyun.driver.log.LogWriter;
import com.zetyun.uitest.utility.DynamicElementUtil;
import com.zetyun.uitest.utility.ElementUtil;
import com.zetyun.uitest.utility.JsonUtil;
import com.zetyun.uitest.utility.ToolKit;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Search {

    /**
     * @param driver
     * @param data
     * @throws Exception
     *
     *     {"模块名称":"自动建模/数据应用/数据模块/分析模块/任务列表/预定计划/快捷入口/消息中心/用户列表/用户分组/用户角色/日志审计/标签管理",
     *     "查询类型":"普通搜索/类型搜索/状态搜索/创建人搜索/标签搜索",
     *     "查询数据","TestForAuto1"
     *     }
     */
    public void search(WebDriver driver, String data) throws Exception {
        Map map= new JsonUtil().jsonToMaps(data);
        String  navi =  map.get("模块名称").toString();// 模块名称
        String searchtype=map.get("查询类型").toString(); //
        String datas=map.get("查询数据").toString(); //

        LogWriter.info(Search.class, "查询 ");
        String elementSelectorTemplate = DataParse.GetProperties("UIElementSelectorTemplate");
        String filepath = elementSelectorTemplate;

        Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "Menu");
        String condition = ElementTemplate.getValues(filepath, "Search").get(navi).get(searchtype);
        WebElement searchInput = ElementUtil.findElement(driver, condition);
        DynamicElementUtil pp = new DynamicElementUtil(driver);
        switch (searchtype) {
            case "普通搜索":
                searchInput.clear();
                searchInput.sendKeys(datas);
                searchInput.sendKeys(Keys.ENTER);
                break;
            case "类型搜索":
                searchInput.click();
                pp.ModuleType(datas).click();
                break;
            case "状态搜索":
                searchInput.click();
                pp.ModuleType(datas).click();
                break;
            case "创建人搜索":
                searchInput.click();
                pp.ModuleType(datas).click();
                break;
            case "标签搜索":
                searchInput.click();
                pp.ModuleType(datas).click();
                String searhSubmit = ElementTemplate.getValues(filepath, "Search").get(navi).get("标签搜索提交");
                WebElement searhSubmits = ElementUtil.findElement(driver, searhSubmit);
                searhSubmits.click();
                break;
        }
        ToolKit.wait(3);
    }


    /**
     * @param data
     * @param driver
     * @throws IOException
      {"模块名称":"分析模块"}
     */
    public String checkSearch(WebDriver driver,String data) throws IOException {
        Map map= new JsonUtil().jsonToMaps(data);
        String  navi =  map.get("模块名称").toString();// 模块名称
        String result=null;
        String elementSelectorTemplate = DataParse.GetProperties("UIElementSelectorTemplate");
        Pagelist pl = new Pagelist();
        int itemSize=pl.getItemSize(driver,data);  // 页面信息显示数量
        String str = ElementTemplate.getValues(elementSelectorTemplate, "Table").get("统计信息").get("统计结果").trim();
        String totalNu=  ElementUtil.findElement(driver, str).getText();// 信息总数量
        int totalNum=ToolKit.getIntFromString(totalNu);   // 总数
        if(navi.equals("分析模块")){

            Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(elementSelectorTemplate, "Analysis");
             Map map1=elementConfig.get("模块首页");
            String modulename=  ElementUtil.findElement(driver, (String) map1.get("分析模块名称")).getText();
            String moduletype=ElementUtil.findElement(driver,(String)map1.get("模块类型")).getText();
            String edition=ElementUtil.findElement(driver,(String)map1.get("分析模块版本")).getText();
            String status=ElementUtil.findElement(driver,(String)map1.get("发布状态")).getText();

            Map<String,String> resultMap = new HashMap<String,String>();
            resultMap.put("modulename",modulename);   // 模块名称
            resultMap.put("moduletype",moduletype); // 模块类型
            resultMap.put("edition",edition);// 版本
            resultMap.put("status",status);// 状态
            resultMap.put("itemSize",Integer.toString(itemSize)); // 显示行数
            resultMap.put("totalNum",Integer.toString(totalNum));// 模块总数
            System.out.println(resultMap);
            result=JSON.toJSONString(resultMap);
        }else if(navi.equals("数据应用")){
            Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(elementSelectorTemplate, "DataApp");
            Map map1=elementConfig.get("模块首页");
            String modulename=  ElementUtil.findElement(driver, (String) map1.get("数据应用名称")).getText();
            String runtimes=ElementUtil.findElement(driver,(String)map1.get("运行次数")).getText();
            String edition=ElementUtil.findElement(driver,(String)map1.get("数据模块版本")).getText();
            String creator=ElementUtil.findElement(driver,(String)map1.get("创建人")).getText();

            Map<String,String> resultMap = new HashMap<String,String>();
            resultMap.put("modulename",modulename);   // 模块名称
            resultMap.put("runtimes",runtimes); // 运行次数
            resultMap.put("edition",edition);// 版本
            resultMap.put("creator",creator);// 创建人
            resultMap.put("itemSize",Integer.toString(itemSize)); // 显示行数
            resultMap.put("totalNum",Integer.toString(totalNum));// 模块总数
            System.out.println(resultMap);
            result=JSON.toJSONString(resultMap);
        }else if(navi.equals("数据模块")){
            Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(elementSelectorTemplate, "DataModule");
            Map map1=elementConfig.get("模块首页");
            String modulename=  ElementUtil.findElement(driver, (String) map1.get("数据模块名称")).getText();
            String type=ElementUtil.findElement(driver,(String)map1.get("类型")).getText();
            String quotes=ElementUtil.findElement(driver,(String)map1.get("引用次数")).getText();
            String creator=ElementUtil.findElement(driver,(String)map1.get("创建人")).getText();

            Map<String,String> resultMap = new HashMap<String,String>();
            resultMap.put("modulename",modulename);   // 模块名称
            resultMap.put("type",type); // 类型
            resultMap.put("quotes",quotes);// 引用次数
            resultMap.put("creator",creator);// 创建人
            resultMap.put("itemSize",Integer.toString(itemSize)); // 显示行数
            resultMap.put("totalNum",Integer.toString(totalNum));// 模块总数
            System.out.println(resultMap);
            result=JSON.toJSONString(resultMap);
        }else if(navi.equals("自动建模")){
            Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(elementSelectorTemplate, "Automl");
            Map map1=elementConfig.get("模块首页");
            String modulename=  ElementUtil.findElement(driver, (String) map1.get("自动建模模块名称")).getText();
            String dataModule=ElementUtil.findElement(driver,(String)map1.get("关联数据模块")).getText();
            String creator=ElementUtil.findElement(driver,(String)map1.get("创建人")).getText();

            Map<String,String> resultMap = new HashMap<String,String>();
            resultMap.put("modulename",modulename);   // 模块名称
            resultMap.put("dataModule",dataModule); // 关联数据模块
            resultMap.put("creator",creator);// 创建人
            resultMap.put("itemSize",Integer.toString(itemSize)); // 显示行数
            resultMap.put("totalNum",Integer.toString(totalNum));// 模块总数
            System.out.println(resultMap);
            result=JSON.toJSONString(resultMap);
        }else if(navi.equals("模型仓库")){
            Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(elementSelectorTemplate, "ModelRepository");
            Map map1=elementConfig.get("模块首页");
            String serviceName=  ElementUtil.findElement(driver, (String) map1.get("服务名称")).getText();
            String type=ElementUtil.findElement(driver,(String)map1.get("类型")).getText();
            String belong=ElementUtil.findElement(driver,(String)map1.get("归属模块")).getText();
            String creator=ElementUtil.findElement(driver,(String)map1.get("创建人")).getText();
            String relasesModule=ElementUtil.findElement(driver,(String)map1.get("已上线模型")).getText();

            Map<String,String> resultMap = new HashMap<String,String>();
            resultMap.put("serviceName",serviceName);   // 服务名称
            resultMap.put("type",type); // 类型
            resultMap.put("belong",belong);// 归属模块
            resultMap.put("creator",creator);// 创建人
            resultMap.put("relasesModule",relasesModule);//已上线模型
            resultMap.put("itemSize",Integer.toString(itemSize)); // 显示行数
            resultMap.put("totalNum",Integer.toString(totalNum));// 模块总数
            System.out.println(resultMap);
            result=JSON.toJSONString(resultMap);
        }
        return result;
    }


    public static void main(String[] args) throws IOException {
        Search search = new Search();
    //    search.checkSearch("{\"模块名称\":\"分析模块\"}");
    }
}







