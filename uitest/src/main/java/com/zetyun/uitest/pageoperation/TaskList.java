package com.zetyun.uitest.pageoperation;

import com.zetyun.data.DataParse;
import com.zetyun.datatemplate.elementtemplate.ElementTemplate;
import com.zetyun.driver.log.LogWriter;
import com.zetyun.uitest.utility.ElementUtil;
import com.zetyun.uitest.utility.JsonUtil;
import com.zetyun.uitest.utility.ToolKit;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.Map;

/**
 * 任务列表
 */
public class TaskList {
    /**
     * 任务状态校验
     *  等待时间单位为分钟
     * @param driver
     * @param data
     * @throws Exception
      {"状态":"运行/已终止/失败/成功",
      "等待时间":"1200"}
     */
    public void checkTaskStatus(WebDriver driver,String data) throws Exception {

        LogWriter.info(TaskList.class,"任务状态验证"+data);
        Map map= new JsonUtil().jsonToMaps(data);
        String  taskName =  map.get("状态").toString();
        String  time =  map.get("等待时间").toString();
        int waittime = Integer.parseInt(time);
        String filepath = DataParse.GetProperties("UIElementSelectorTemplate");
        Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "TaskList");
        Map<String, String> map1 = elementConfig.get("状态");
       String status= map1.get(taskName);
       ToolKit.waitForWebElementVisible(driver,ElementUtil.findElement(driver,status,waittime),waittime);
        Assert.assertTrue(ElementUtil.findElement(driver,status).isDisplayed());


    }
}
