package com.zetyun.uitest.pageoperation.common;

import com.zetyun.driver.log.LogWriter;
import com.zetyun.uitest.utility.ToolKit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;

public class InitBrowser {
    public static String dirName;
    public static WebDriver openBroser(WebDriver driver,String url) throws Exception {
        try {
            String path= System.getProperty("user.dir");
            if(!path.contains("uitest")){
                path=path+"\\uitest";
            }
            dirName = path+ "\\Download";
            ToolKit.cleanDownloadFolder(dirName);;// 清空文件夹
            HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);  // 禁止弹出下载窗口
            chromePrefs.put("download.default_directory", dirName);
            ChromeOptions options = new ChromeOptions();
            HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
            options.setExperimentalOption("prefs", chromePrefs);
            options.addArguments("disable-infobars");  //
            DesiredCapabilities cap = DesiredCapabilities.chrome();
            cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
            cap.setCapability(ChromeOptions.CAPABILITY, options);


            String chromeDriver = path + "/WebDriver/chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", chromeDriver);
            driver = new ChromeDriver(cap);
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();  // 删除cookie

            driver.get(url);  // 访问网站

            return driver;
        }catch (Exception e){
            LogWriter.error(InitBrowser.class,"场景初始化失败！");
            driver.quit();
            return null;
        }
    }
}
