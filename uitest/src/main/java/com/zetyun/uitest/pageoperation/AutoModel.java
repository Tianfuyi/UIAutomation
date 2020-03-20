package com.zetyun.uitest.pageoperation;

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

import java.util.List;
import java.util.Map;

public class AutoModel {

    /**
     * @param driver
     * @param data
     * @throws Exception
     * 基本信息 特征 模型评估资源设置
     * {"设置项":"特征处理/目标"}
     */
    public void chooseOption(WebDriver driver,String data) throws Exception {
        LogWriter.info(AutoModel.class,"");
        Map map= new JsonUtil().jsonToMaps(data);
        String  datas = map.get("设置项").toString();

        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.eleWithTextName(datas).click();

    }

    /** 点击训练
     * @param driver
     * @throws Exception
     */
    public void clickTraining(WebDriver driver) throws Exception {
        LogWriter.info(AutoModel.class,"点击训练");
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "Automl");
        Map<String, String> map1 = elementConfig.get("设计");
        ToolKit.waitForWebElementClickable(driver, ElementUtil.findElement(driver,map1.get("训练")));
        ElementUtil.findElement(driver,map1.get("训练")).click();

    }

    /** 点击产看历史结果
     * @param driver
     * @throws Exception
     */
    public void clickCheckHistory(WebDriver driver) throws Exception {
        LogWriter.info(AutoModel.class,"点击产看历史结果");
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "Automl");
        Map<String, String> map1 = elementConfig.get("设计");
        ToolKit.waitForWebElementClickable(driver, ElementUtil.findElement(driver,map1.get("查看历史结果")));
        ElementUtil.findElement(driver,map1.get("查看历史结果")).click();

    }


    /**
     * 基本信息目标
     * @param driver
     * @param data
     * @throws Exception
     * {"预测类型":"多分类",
     * "目标列":"y"}
     */
    public void target(WebDriver driver,String data) throws Exception {
        LogWriter.info(AutoModel.class,"基本信息目标");
        Map map= new JsonUtil().jsonToMaps(data);
        String  type = map.get("预测类型").toString();
        String targetrow= map.get("目标列").toString();

        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "Automl");
        Map<String, String> map1 = elementConfig.get("设计");
       ElementUtil.findElements(driver,map1.get("下拉框")).get(0).click();
        DynamicElementUtil pp = new DynamicElementUtil(driver);
        pp.tagDropDownautoml(type).click();

        ElementUtil.findElements(driver,map1.get("下拉框")).get(1).click();
        pp.tagDropDownautoml(targetrow).click();
    }

    /**
     * 训练/测试集
     * @param driver
     * @param data
     * @throws Exception
     *
     * {
     *     "抽样方法":"选择前N行",
     *      "行数":"10001",
     *       "训练数据集的比例":"0.800",
     *        "随机种子":"1377"
     *
     * }
     */
    public void trainAndtestSet(WebDriver driver,String data) throws Exception {
        LogWriter.info(AutoModel.class,"训练/测试集");
        Map map= new JsonUtil().jsonToMaps(data);
        String  type = map.get("抽样方法").toString();
        String targetrowdata= map.get("行数").toString();
        String setpersent= map.get("训练数据集的比例").toString();
        String random= map.get("随机种子").toString();

        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "Automl");
        Map<String, String> map1 = elementConfig.get("设计");
        if(!(type==null)&&!type.equals("")) {
            ElementUtil.findElements(driver, map1.get("下拉框")).get(0).click();
            DynamicElementUtil pp = new DynamicElementUtil(driver);
            pp.tagDropDownautoml(type).click();
        }
        if(!(targetrowdata==null)&&!targetrowdata.equals("")) {
            WebElement rowcount = ElementUtil.findElement(driver, map1.get("行数"));
            rowcount.clear();
            rowcount.sendKeys(targetrowdata);
        }
        if(!(setpersent==null)&&!setpersent.equals("")) {
            WebElement rowcount = ElementUtil.findElement(driver, map1.get("训练数据集的比例"));
            rowcount.clear();
            rowcount.sendKeys(setpersent);
        }

        if(!(random==null)&&!random.equals("")) {
            WebElement rowcount = ElementUtil.findElement(driver, map1.get("随机种子"));
            rowcount.clear();
            rowcount.sendKeys(random);
        }

    }

    /**
     * 特征处理
     * @param driver
     * @param data
     * @throws Exception
     *
     * {
     *     "名称":"job",
     *     "状态":"关"
     * }
     */
    public void featureProcess(WebDriver driver,String data) throws Exception {
        LogWriter.info(AutoModel.class,"特征处理");
        Map map= new JsonUtil().jsonToMaps(data);
        String  name = map.get("名称").toString();
        String status= map.get("状态").toString();
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "Automl");
        Map<String, String> map1 = elementConfig.get("设计");
        WebElement searhInput =  ElementUtil.findElement(driver,map1.get("特征处理搜索框"));
        searhInput.clear();
        searhInput.sendKeys(name);
        searhInput.sendKeys(Keys.ENTER);
        WebElement statusOpr =  ElementUtil.findElement(driver,map1.get("特征处理开关"));
        if (statusOpr.getAttribute("class").contains("checked")){   // 状态为开
            if(status.contains("关")){
                statusOpr.click();
            }
        }else if(!statusOpr.getAttribute("class").contains("checked")){  // 状态为关
            if(status.contains("开")){
                statusOpr.click();
            }
        }
    }

    /**
     * 算法
     * @param driver
     * @param data
     * @throws Exception
     * {
     *     "算法名称":"KNN",
     *     "状态","开"
     * }
     */
    public void algorithms(WebDriver driver,String data) throws Exception {
        LogWriter.info(AutoModel.class,"算法");
        Map map= new JsonUtil().jsonToMaps(data);
        String  name = map.get("算法名称").toString();
        String status= map.get("状态").toString();

       WebElement ele= new DynamicElementUtil(driver).algorithmstatus(name);   // 算法对应开关
        if (ele.getAttribute("class").contains("checked")){    //  状态为开
            if(status.contains("关")){
                ele.click();
            }
        }else if(!ele.getAttribute("class").contains("checked")){  // 状态为关
            if(status.contains("开")){
                ele.click();
            }
        }
    }

    /**
     * 超参调优
     * @param driver
     * @param data
     *
    {"随机网络搜索":"否"，
    "最大迭代次数":"20",
    "最大搜索时间":"10",
    "并发数":"0",
    "交叉验证":"K-Fold",
    "分割数":"10",
    }
     */

   public void paramTuning(WebDriver driver,String data) throws Exception {
       LogWriter.info(AutoModel.class,"超参调优");
       Map map= new JsonUtil().jsonToMaps(data);
       String  search = map.get("随机网络搜索").toString();
       String  numOfIter = map.get("最大迭代次数").toString();
       String searchtime= map.get("最大搜索时间").toString();
       String concurtime= map.get("并发数").toString();
       String check= map.get("交叉验证").toString();
       String cuttime= map.get("分割数").toString();

       String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
       Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "Automl");
       Map<String, String> map1 = elementConfig.get("设计");

       if(!(search==null)&&!search.equals("")) {

           WebElement ele5=ElementUtil.findElement(driver,map1.get("单选框状态"));
           String isChoose =ElementUtil.findElement(driver,map1.get("单选框状态")).getAttribute("class"); // 单选框状态
           if(isChoose.contains("checkbox-checked")){ // 被勾选状态
               if(search.contains("否")){
                   ele5.click();

               }
           }else if(!isChoose.contains("checkbox-checked")){// 未被勾选状态
               if(search.contains("是")){
                   ele5.click();
               }
           }
       }
       if(!(numOfIter==null)&&!numOfIter.equals("")) {
           WebElement ele =ElementUtil.findElement(driver,map1.get("最大迭代次数"));
           ele.clear();
           ele.sendKeys(numOfIter);
       }
       if(!(searchtime==null)&&!searchtime.equals("")) {
           WebElement ele1 =ElementUtil.findElement(driver,map1.get("最大搜索时间"));
           ele1.clear();
           ele1.sendKeys(searchtime);
       }
       if(!(concurtime==null)&&!concurtime.equals("")) {
           WebElement ele2 =ElementUtil.findElement(driver,map1.get("并发数"));
           ele2.clear();
           ele2.sendKeys(concurtime);
       }
       if(!(check==null)&&!check.equals("")) {
           ElementUtil.findElements(driver,map1.get("下拉框")).get(0).click();
           DynamicElementUtil pp = new DynamicElementUtil(driver);
           pp.tagDropDownautoml(check).click();
       }
       if(!(cuttime==null)&&!cuttime.equals("")) {
           WebElement ele4 =ElementUtil.findElement(driver,map1.get("分割数"));
           ele4.clear();
           ele4.sendKeys(cuttime);
       }
   }

    /**
     * 评估方法
     * @param driver
     * @param data
     * @throws Exception
     *
     * {"评估方法":"Recall"}
     */
    public void evaluation(WebDriver driver,String data) throws Exception {
        LogWriter.info(AutoModel.class,"评估方法");
        Map map = new JsonUtil().jsonToMaps(data);
        String method=map.get("评估方法").toString();
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "Automl");
        Map<String, String> map1 = elementConfig.get("设计");

        if(!(method==null)&&!method.equals("")) {
            ElementUtil.findElements(driver,map1.get("下拉框")).get(0).click();
            DynamicElementUtil pp = new DynamicElementUtil(driver);
            pp.tagDropDownautoml(method).click();
        }
    }

    /**
     * 资源配置
     * @param driver
     * @param data
     * @throws Exception
     *
    {"特征CPU":"2",
    "特征MEM":"2",
    "算法CPU":"2",
    "算法MEM":"2",
    "评估CPU":"2",
    "评估MEM":"2",
    }
     */
    public void resourceConfig(WebDriver driver,String data) throws  Exception{
        LogWriter.info(AutoModel.class,"资源配置");
        Map map = new JsonUtil().jsonToMaps(data);
        String charactorcpu = map.get("特征CPU").toString();
        String charactormem = map.get("特征MEM").toString();
        String algorithmcpu = map.get("算法CPU").toString();
        String algorithmmem = map.get("算法MEM").toString();
        String assessmentcpu = map.get("评估CPU").toString();
        String assessmentmem = map.get("评估MEM").toString();

        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "Automl");
        String input=elementConfig.get("设计").get("资源配置");
        List<WebElement> list =ElementUtil.findElements(driver,input);

        if(!(charactorcpu==null)&&!charactorcpu.equals("")) {
           list.get(0).clear();
           list.get(0).sendKeys(charactorcpu);
        }
        if(!(charactormem==null)&&!charactormem.equals("")) {
            list.get(1).clear();
            list.get(1).sendKeys(charactormem);
        }
        if(!(algorithmcpu==null)&&!algorithmcpu.equals("")) {
            list.get(2).clear();
            list.get(2).sendKeys(algorithmcpu);
        }
        if(!(algorithmmem==null)&&!algorithmmem.equals("")) {
            list.get(3).clear();
            list.get(3).sendKeys(algorithmmem);
        }
        if(!(assessmentcpu==null)&&!assessmentcpu.equals("")) {
            list.get(4).clear();
            list.get(4).sendKeys(assessmentcpu);
        }
        if(!(assessmentmem==null)&&!assessmentmem.equals("")) {
            list.get(5).clear();
            list.get(5).sendKeys(assessmentmem);
        }
    }

}
