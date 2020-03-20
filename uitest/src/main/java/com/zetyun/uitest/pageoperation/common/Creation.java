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

import java.io.IOException;
import java.util.Map;

public class Creation {


    /**
     *
     * @param driver driver
     * @param moduleName 模块名称
     * @throws Exception excetion
     * {}
     */
    private void clickCreate(WebDriver driver,String moduleName) throws Exception {
        LogWriter.info(Creation.class,moduleName+" 点击新建按钮");
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        String creatBtn = ElementTemplate.getValues(filepath, "Create").get(moduleName).get("新建");
        WebElement creatButton = ElementUtil.findElement(driver, creatBtn);
      //  creatButton.click();
        ToolKit.JavaScriptClick(driver,creatButton);
    }

    /**
     * @param driver
     * @param moduleName 模块名称
     * @return 模块名称
     * @throws IOException
     */
    public String clickCreateBtn(WebDriver driver,String moduleName) throws Exception {
        clickCreate(driver,moduleName);
        return moduleName;
    }

    /**
     * 分析模块
     * @param driver
     * @param data
     * @throws Exception
     */
    public void creatAnalysis(WebDriver driver ,String data) throws Exception {
        LogWriter.info(Creation.class,data);
        Map map= new JsonUtil().jsonToMaps(data);
        String type= (String) map.get("类型");
        String name= (String) map.get("名称");
        String image= (String) map.get("镜像");
        String discription= (String) map.get("描述");
        String tag= (String) map.get("标签");
        String opentype= (String) map.get("公开类型");

        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "Create");
        Map<String, String> map1 = elementConfig.get("分析模块");

        DynamicElementUtil pp = new DynamicElementUtil(driver);
        WebElement types = ElementUtil.findElement(driver, map1.get("类型"));
        ToolKit.waitForWebElementClickable(driver,types);
        types.click();       // 类型
        try {
            pp.dropDownList(type).click();
        }catch (Exception e){
            try {
                pp.dropDownList(ToolKit.toLowerCaseFirstOne(type)).click();  // 首字母转换成小写
            }catch (Exception e1){
                pp.dropDownList(ToolKit.toUpperCaseFirstOne(type)).click(); // 首字母转化成大写
            }
        }
        WebElement names = ElementUtil.findElement(driver, map1.get("分析模块名称"));
        names.clear();
        names.sendKeys(name);

        WebElement images = ElementUtil.findElement(driver, map1.get("镜像"));
        images.click();
        pp.dropDownList(image).click();

            if(!(discription==null)&&!discription.equals("")){
            WebElement discriptions = ElementUtil.findElement(driver, map1.get("描述"));
            discriptions.clear();
            discriptions.sendKeys(discription);
        }
        if(!tag.equals("")&tag!=null) {
            WebElement tags = ElementUtil.findElement(driver, map1.get("添加标签"));
            tags.click();
            pp.tagDropDownList(tag).click();
           // names.click();  // 点击名称 使焦点移开收起选项
        }

            if(!(opentype==null)&&!opentype.equals("")){// 私有公开
            WebElement opentypes = ElementUtil.findElement(driver, map1.get(opentype));
            opentypes.click();
        }
        WebElement submit = ElementUtil.findElement(driver, map1.get("提交"));
        submit.click();
        ToolKit.wait(3);
        String getName =ElementUtil.findElement(driver, map1.get("模块名称验证")).getText();   // 新建成功模块名称显示
        Assert.assertTrue(getName.contains(name));

    }


    /**
     * 数据应用新建
     * @param driver
     * @param data
     * @throws Exception
     */
    public void createDataApp(WebDriver driver,String data) throws Exception {

        Map map= new JsonUtil().jsonToMaps(data);
        String name= (String) map.get("名称");
        String discription= (String) map.get("描述");
        String tag= (String) map.get("标签");
        String opentype= (String) map.get("公开类型");

        LogWriter.info(Creation.class,"新建数据应用 应用名称:"+name+" 描述:"+discription+" 标签:"+tag+" 公开类型:"+opentype);
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "Create");
        Map<String, String> map1 = elementConfig.get("数据应用");

        WebElement names = ElementUtil.findElement(driver, map1.get("应用名称"));
        names.clear();
        names.sendKeys(name);


            if(!(discription==null)&&!discription.equals("")){
            WebElement discriptions = ElementUtil.findElement(driver, map1.get("描述"));
            discriptions.clear();
            discriptions.sendKeys(discription);
        }

            if(!(opentype==null)&&!opentype.equals("")) {
            WebElement tags = ElementUtil.findElement(driver, map1.get("标签"));
            ToolKit.waitForWebElementClickable(driver,tags);
            tags.click();
            DynamicElementUtil pp = new DynamicElementUtil(driver);
                ToolKit.waitForWebElementClickable(driver,pp.tagDropDownList(tag));
            pp.tagDropDownList(tag).click();
            names.click();  // 点击名称 使焦点移开收起选项
        }

            if(!(opentype==null)&&!opentype.equals("")) {
            WebElement opentypes = ElementUtil.findElement(driver, map1.get(opentype));
            opentypes.click();
        }
        WebElement submit = ElementUtil.findElement(driver, map1.get("确定"));
        submit.click();
    }


    /**
     * 数据模块
     * @param driver
     * @param data
     * @throws Exception
     * {
     * "类型":"本地文件",
     * "数据模块名称":"TestForAutomationDappModule",
     * "描述":"This is a test",
     * "标签":"数据用途-训练",
     * "公开类型":"",
     * "HDFS":"",
     * "HIVE":"",
     * "文件类型":"文本文件TXT",
     * "列分隔符":"逗号",
     * "行分隔符":"换行",
     * "编码格式":"UTF-8",
     * "文件首行字段名":"开启"
     * }
     */
    public void createDataModule(WebDriver driver,String data) throws Exception {
        Map map= new JsonUtil().jsonToMaps(data);
        String type  =(String) map.get("类型");
        String name =(String) map.get("数据模块名称");
        String discription =(String) map.get("描述");
        String tag =(String) map.get("添加标签");
        String opentype =(String) map.get("公开类型");
        String   fileType =(String) map.get("文件类型");
        String  colDelimit =(String) map.get("列分隔符");
        String rowDelimit =(String) map.get("行分隔符");
        String codingFormat=(String) map.get("编码格式");
        String  firstFieldName=(String) map.get("文件首行字段名");

        LogWriter.info(Creation.class,data);
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "Create");
        Map<String, String> map1 = elementConfig.get("数据模块");
        DynamicElementUtil pp = new DynamicElementUtil(driver);
        WebElement types = ElementUtil.findElement(driver, map1.get("类型"));
        types.click();
        pp.dropDownList(type).click();

        WebElement names = ElementUtil.findElement(driver, map1.get("数据模块名称"));
        names.clear();
        names.sendKeys(name);
        if(!(discription==null)&&!discription.equals("")) {
            WebElement discriptions = ElementUtil.findElement(driver, map1.get("描述"));
            discriptions.clear();
            discriptions.sendKeys(discription);
        }
        if(!(tag==null)&&!tag.equals("")) {
            WebElement tags = ElementUtil.findElement(driver, map1.get("添加标签"));
            tags.click();
            pp.tagDropDownList(tag).click();
           names.click();  // 点击名称 使焦点移开收起选项
        }

            if(!(opentype==null)&&!opentype.equals("")) {// 私有公开
            WebElement opentypes = ElementUtil.findElement(driver, map1.get(opentype));
            opentypes.click();
        }

        if(type.contains("本地文件")){  // 如类型是本地文件
            pp.uploadFile().sendKeys(DataParse.GetProperties("DataModuleUploadfile"));
            ToolKit.waitForWebElementVisible(driver,ElementUtil.findElement(driver, map1.get("上传文件显示")));

        }else if (type.contains("HDFS")) { // 如类型是HDFS
                WebElement hdfs = ElementUtil.findElement(driver, map1.get("HDFS"));
                hdfs.clear();
                hdfs.sendKeys(map1.get("HDFS"));

        } else if (type.contains("HIVE")) { // 如类型是HIVE
                WebElement hive = ElementUtil.findElement(driver, map1.get("HIVE"));
                hive.clear();
                hive.sendKeys(map1.get("HIVE"));

        }

        if(!(fileType==null)&&!fileType.equals("")){
            ElementUtil.findElement(driver, map1.get("文件类型")).click();
           for(WebElement e:pp.tagDropDownListcontains()){
               if(e.getText().contains(fileType)){
                   e.click();
               }
           }
        }
        if(!(colDelimit==null)&&!colDelimit.equals("")) {
            WebElement colDelimits = ElementUtil.findElement(driver, map1.get("列分隔符"));
                ToolKit.setScroll(driver,colDelimits);
                ToolKit.waitForWebElementClickable(driver,colDelimits);

            colDelimits.click();
            pp.dropDownList(colDelimit).click();
        }
        ToolKit.wait(5);
        if(!(rowDelimit==null)&&!rowDelimit.equals("")) {
            WebElement rowDelimits = ElementUtil.findElement(driver, map1.get("行分隔符"));
                ToolKit.waitForWebElementClickable(driver,rowDelimits);
            rowDelimits.click();
            ToolKit.JavaScriptClick(driver,pp.dropDownList(rowDelimit));

        }

        if(!(codingFormat==null)&&!codingFormat.equals("")) {
            WebElement codingFormats = ElementUtil.findElement(driver, map1.get("编码格式"));
            codingFormats.click();
            pp.dropDownList(codingFormat).click();
        }


        if(!(firstFieldName==null)&&!firstFieldName.equals("")) {// 文件首航字段名 开启/关闭
            WebElement opentypes = ElementUtil.findElement(driver, map1.get(firstFieldName));
            opentypes.click();
        }
        ElementUtil.findElement(driver, map1.get("预览")).click(); // 点击预览

        ToolKit.waitForWebElementVisible(driver,ElementUtil.findElement(driver, map1.get("预览信息")),180);
        Assert.assertTrue(ElementUtil.findElement(driver, map1.get("预览信息")).isDisplayed());  // 预览信息 显示
        ElementUtil.findElement(driver, map1.get("提交")).click(); // 点击提交
        Assert.assertTrue(ElementUtil.findElement(driver, map1.get("数据模块名称校验")).isDisplayed());  // 验证数据模块名称 显示

    }


    /**新建自动建模
     * @param driver
     * @param data
     * {
     * "自动建模名称":"TestForAutoDautoml"
     * "描述":"This is a test"
     * "标签":"应用场景-内部测试"
     * "模型类型":"二分类/多分类/回归"
     * "选择数据模块":"TestForAutomationDappModule"
     * "选择目标列":"1"
     * }
     *
     */
    public void createDataModuleeAutoml(WebDriver driver,String data) throws Exception {
        Map map= new JsonUtil().jsonToMaps(data);
        String name  =(String) map.get("自动建模名称");
        String disc  =(String) map.get("描述");
        String tag  =(String) map.get("标签");
        String moltype  =(String) map.get("模型类型");
        String datamodule  =(String) map.get("选择数据模块");
        String targetcow  =(String) map.get("选择目标列");

        DynamicElementUtil pp = new DynamicElementUtil(driver);
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "Create");
        Map<String, String> map1 = elementConfig.get("自动建模");
        WebElement  elename= ElementUtil.findElement(driver,map1.get("自动建模名称"));
        elename.clear();
        elename.sendKeys(name);
        if(!(disc==null)&&!disc.equals("")) {
            WebElement eledisc = ElementUtil.findElement(driver, map1.get("描述"));
            eledisc.clear();
            eledisc.sendKeys(disc);
        }
        if(!tag.equals("")&tag!=null) {

            WebElement tags=ElementUtil.findElement(driver, map1.get("标签"));
            ToolKit.waitForWebElementClickable(driver,tags);
            tags.click();
            pp.tagDropDownDatamotagdule(tag).click();
            ElementUtil.findElement(driver,map1.get("描述字符")).click();  // 点击页面描述字符元素 使焦点移开收起选项
        }
        ElementUtil.findElement(driver, map1.get("模型类型")).click();
        ToolKit.waitForWebElementClickable(driver,pp.dropDownList(moltype));
        pp.dropDownList(moltype).click();

        ElementUtil.findElement(driver, map1.get("选择数据模块")).click();
        ElementUtil.findElement(driver,map1.get("选择数据模块输入框")).sendKeys(datamodule);
       ToolKit.setScroll(driver,pp.DropDownDatamodule(datamodule));
        ToolKit.JavaScriptClick(driver,pp.DropDownDatamodule(datamodule));



        ElementUtil.findElement(driver, map1.get("选择目标列")).click();
       ToolKit.JavaScriptClick(driver,pp.dropDownList(targetcow));
        ElementUtil.findElement(driver, map1.get("确定按钮")).click();

        Assert.assertTrue(ElementUtil.findElements(driver,map1.get("算法名称行")).size()>=3); // 跳转页面显示算法至少3个
        Assert.assertTrue(driver.getPageSource().contains(name));
    }

    public void verifyCreateDataModule(WebDriver driver){

    }

}
