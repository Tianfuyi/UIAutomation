package com.zetyun.uitest.abstractbusiness;

import com.zetyun.uitest.pageoperation.DataModule;
import org.openqa.selenium.WebDriver;

/**
 * 数据模块
 */
public class ActionDataModule {
    /**模块详情查看
     * @param driver
     * @throws Exception
     * 依赖Action.verifmoduledeatail
     */
    public static void checkView(WebDriver driver) throws Exception {
        new DataModule().checkView(driver);
    }

    /**模块详情下载
     * @param driver
     * @throws Exception
     * 依赖Action.verifmoduledeatail
     */
    public static void checkDownload(WebDriver driver) throws  Exception{
        new DataModule().checkDownload(driver);
    }
}
