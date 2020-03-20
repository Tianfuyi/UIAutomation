package com.zetyun.uitest.pageoperation;

import com.zetyun.data.DataParse;
import com.zetyun.datatemplate.elementtemplate.ElementTemplate;
import com.zetyun.driver.log.LogWriter;
import com.zetyun.uitest.utility.ElementUtil;
import com.zetyun.uitest.utility.ToolKit;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Map;

/**
 * 数据模块
 */
public class DataModule {

    /**
     * 模块详情查看
     * @param driver
     * @throws IOException
     */
    public void checkView(WebDriver driver) throws IOException {
        LogWriter.info(DataModule.class,"模块详情查看验证");
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "DataModule");
        Map<String, String> map1 = elementConfig.get("模块详情");
        ElementUtil.findElement(driver,map1.get("查看按钮")).click();
        Assert.assertTrue(ElementUtil.findElement(driver,map1.get("数据预览"),180).isDisplayed());
    }

    /**模块详情下载
     * @param driver
     * @throws Exception
     */
    public void checkDownload(WebDriver driver) throws Exception {
        LogWriter.info(DataModule.class,"模块详情下载");
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "DataModule");
        Map<String, String> map1 = elementConfig.get("模块详情");
        ElementUtil.findElement(driver,map1.get("下载按钮")).click();
        ToolKit.wait(3);
        Assert.assertTrue(ToolKit.CheckFolder(DataParse.GetProperties("DataModuleDownloaddfile")));
    }


}
