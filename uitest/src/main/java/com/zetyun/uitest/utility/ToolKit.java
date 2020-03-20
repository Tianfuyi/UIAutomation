package com.zetyun.uitest.utility;


import com.zetyun.driver.log.LogWriter;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 *  工具类 元素操作 页面操作
 */
public class ToolKit {

    private static int DRIVER_WAIT_TIME = 30; // 默认元素等待时间

    /**
     * @param driver
     * @param webElement
     *  页面滚动到元素位置
     */
    public static void setScroll(WebDriver driver, WebElement webElement) {
        try {

            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].scrollIntoView(true);", webElement);
        } catch (Exception e) {
            LogWriter.info(ToolKit.class,"Fail to set the scroll.");
           // System.out.println("Fail to set the scroll.");
        }
    }

    /**
     * @param driver
     * @param element
     * 元素 通过js的方式点击
     */
    public static void JavaScriptClick(WebDriver driver, WebElement element) {
        try {

            ToolKit.waitForWebElementClickable(driver,element);
            if (element.isEnabled() && element.isDisplayed()) {
                System.out.println("Click element using JavaScript");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            } else {

                LogWriter.info(ToolKit.class,"The element can't be clicked.");
            }
        } catch (Exception e) {
            LogWriter.info(ToolKit.class,"The element can't be clicked.");

            e.printStackTrace();
        }
    }

    public static void wait(int second) throws InterruptedException {
        for(double i=0;i<second;i++) {
            Thread.sleep(1000);
        }
    }
    /**
     * @param list
     * @param string
     * 点击一组元素 含有string的元素
     */
    public static void listClick(List<WebElement> list, String string){
        for(WebElement element:list){
            if(element.getText().equals(string)){
                element.click();
            }
        }
    }
    /**
     * @param driver
     * @param e
     * 等待元素显示
     */
    public static void waitForWebElementVisible(WebDriver driver, WebElement e) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(e));
        }catch (Exception ex){
            ex.printStackTrace();
            LogWriter.info(ToolKit.class,"wait for Element falied ");
        }
    }
    /**
     * @param driver
     * @param e
     * 等待元素可点击
     */
    public static void waitForWebElementClickable(WebDriver driver, WebElement e) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.elementToBeClickable(e));
        }catch (Exception ex){
            ex.printStackTrace();
            LogWriter.info(ToolKit.class,"wait for Element falied ");
        }
    }

    /**
     * @param driver
     * @param e
     * 等待元素可点击
     */
    public static void waitForWebElementClickable(WebDriver driver, WebElement e, int second) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, second);
            wait.until(ExpectedConditions.elementToBeClickable(e));
        }catch (Exception ex){
            ex.printStackTrace();
            LogWriter.info(ToolKit.class,"wait for Element falied ");
        }
    }
    /**
     * @param driver
     * @param e
     * 等待元素显示
     */
    public static void waitForWebElementVisible(WebDriver driver, WebElement e, int second) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, second);
            wait.until(ExpectedConditions.visibilityOf(e));
        }catch (Exception ex){
            ex.printStackTrace();
            LogWriter.info(ToolKit.class,"等待元素可见失败 ");
        }
    }
    public static void waitForWebElementInvisible(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, DRIVER_WAIT_TIME);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    /**
     * 首字母转换小写
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s)
    {
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    //首字母转大写
    public static String toUpperCaseFirstOne(String s)
    {
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    public static boolean isElementInvisible(WebDriver driver, WebElement element) {
        try {
            return !element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    /**
     * @param driver
     * @param element
     * 右键元素
     */
    public static void rightMouseClick(WebDriver driver, WebElement element) {

      try{  Actions action = new Actions(driver);
          action.contextClick(element).perform();
    }catch (Exception e){
          System.out.println("right click elment failed");
          e.printStackTrace();
      }
    }

    /**
     * @param driver
     * @param element
     * @throws Exception
     * 高亮显示元素
     */
    public static void highlightWebElement(WebDriver driver, WebElement element) throws Exception {

        ToolKit.waitForWebElementVisible(driver,element,60);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
                "background: yellow; border: 2 px solid red;");
        Thread.sleep(500);

    }

    /**
     * @param driver
     * @throws InterruptedException
     * 切换到新打开的浏览器
     */
    public static void switchWindows(WebDriver driver) throws InterruptedException {
        int WindowHandles = 0;
        WindowHandles = driver.getWindowHandles().size();
        if (WindowHandles > 1) {
            // Switch to new window opened
            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle);
                driver.getCurrentUrl().contains(winHandle);
            }
        }
    }

    /**
     * @param driver
     * @return
     * 判断Alert 弹出
     */
    public static boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }

    }

    /**
     * @param driver
     * @return
     * Alert 点击确定
     */
    public static String getAlert(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        String str = alert.getText();
        alert.accept();
        return str;
    }

    /**
     * @param driver
     * 多个Alert 点击确定
     */
    public static void closeAllAlerts(WebDriver driver) {
        while (isAlertPresent(driver)) {
            driver.switchTo().alert().accept();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * @param driver
     * @param frameName
     * 切换到frame
     */
    public static  void swtichtoFrame(WebDriver driver, String frameName){
        try {
      driver.switchTo().frame(frameName);
    }catch (Exception e){
      LogWriter.error(ToolKit.class,"切换到frame失败");
    }
    }

    /**
     * @param driver
     * 从frame 切换到原有页面
     */
    public static  void swtichtoFrameefaultContent(WebDriver driver){
        try {
            driver.switchTo().defaultContent();
        }catch (Exception e){
            LogWriter.error(ToolKit.class,"切换到frame失败");
        }
    }
    /**
     * @param driver
     * @param by
     * 等待元素 等待时间
     */
    public static void waitForWebElement(WebDriver driver, By by) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.presenceOfElementLocated(by)); // e.g. //
        }catch(Exception e){
            LogWriter.error(ToolKit.class,"元素等待超时");
        }

    }

    public static void sleep(int second){
        try {
//            for(int i=0;i<second;i++){
//
//            }
            Thread.sleep(second/2);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param driver
     * @param timeout
     * 等待页面加载完成
     */
    public static void waitForPageLoad(WebDriver driver, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
    }

    /**
     * @param driver
     * @param content
     * @return
     * 等待字符显示
     */
    public static boolean isTextPresent(WebDriver driver, String content) {

        try {
            WebElement eText = driver.findElement(By.xpath("//*[contains(text(),'"+content+"')]"));
            return eText.isDisplayed();
        } catch (NoSuchElementException e) {
           // Log.log("Fail to locate the element: " + "'" + content + "' displayed failed", true);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param driver
     * @param element
     * 鼠标悬浮
     */
    public static void mouseOver(WebDriver driver, WebElement element) {
       try{
           Actions action = new Actions(driver);
           action.moveToElement(element).perform();
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    /**
     * @param driver
     * @param element
     * 鼠标点击
     */
    public static void leftClick(WebDriver driver, WebElement element){
        try{

            Actions action = new Actions(driver);action.click();// 鼠标左键在当前停留的位置做单击操作
            action.click(element);// 鼠标左键点击指定的元素
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * @param driver
     * @param text
     * @return
     * 判断含有字符的元素
     */
    public static boolean searchText(WebDriver driver, String text) {
        Pattern pattern = null;

        try {
            pattern = Pattern.compile(text);
        } catch (PatternSyntaxException e) {
            pattern = Pattern.compile(text, Pattern.LITERAL);
        }
        try {
            String allText = driver.findElement(By.tagName("html")).getText();
            String[] textArray = allText.split("\n");


            for (String T : textArray) {
                System.out.println("t=" + T);
                Matcher matcher = pattern.matcher(T);
                if (matcher.find()) {
                    return true;
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("未发现元素：" + e);
            return false;
        }
        return false;
    }

    public static void waitForTextElement(WebDriver driver,String target) throws InterruptedException {
        for (int second = 0;; second++) {
            if (second >= 60) {

                Assert.assertTrue(false);
            }
            try {
                waitForWebElement(driver,By.xpath("//*[@class='zet-arithmetic-pkg-list-left-item-status']/.."));
                String string =driver.findElement(By.xpath("//*[@class='zet-arithmetic-pkg-list-left-item-status']/..")).getText();
                if (string.contains("安装成功"))
                    break;
            } catch (Exception e) {
                assert (false);
            }
            Thread.sleep(1000);
        }
    }
    /**
     * @param dirName
     * 清空文件夹
     */
    public static void cleanDownloadFolder(String dirName) {

        File dir = new File(dirName);
        if(!dir.exists()){
            FileUtil.createDir(dirName);
        }
        String[] filelist = dir.list();

        for (int i = 0; i < filelist.length; i++) {

            File readfile = new File(dirName + "\\" + filelist[i]);
            if (!readfile.isDirectory()) {
                readfile.delete();
            } else if (readfile.isDirectory()) {
                cleanDownloadFolder(dirName + "\\" + filelist[i]);
                readfile.delete();
            }
        }

    }

    public static Boolean CheckFolder(String dirName) {

        Boolean flag=false;
        File dir = new File(dirName);
        if(!dir.exists()){
            FileUtil.createDir(dirName);
        }
        String[] filelist = dir.list();
        if(filelist.length>0){
            flag=  true;
        }
     return flag;

    }

    /**
     * @return
     * 时间戳名字
     */
public static  String createName(){
    Calendar c   =   Calendar.getInstance();//可以用set()对每个时间域单独修改

    int   month   =   c.get(Calendar.MONTH);
    int   date   =   c.get(Calendar.DATE);
    int   hour   =   c.get(Calendar.HOUR_OF_DAY);
    int   minute   =   c.get(Calendar.MINUTE);
    int   second   =   c.get(Calendar.SECOND);
    String CreateName = "TestAuto_" + month + "" + date +  +hour + + minute  + second;
    return CreateName;
}
    public static String TakeScreenShot(WebDriver driver, String caseName) throws Exception {

        Date date = new Date();
        String path= System.getProperty("user.dir");
        if(!path.contains("uitest")){
            path=path+"\\uitest";
        }
        String picDir = path + "\\ScreenShots\\" + String.valueOf(DateUtil.getYear(date))
                + "-" + String.valueOf(DateUtil.getMonth(date)) + "-" + String.valueOf(DateUtil.getDay(date));

        if (!new File(picDir).exists()) {
            FileUtil.createDir(picDir);
        }

        String filePath = picDir + "\\" + String.valueOf(DateUtil.getHour(new Date())) + "-"
                + String.valueOf(DateUtil.getMinute(new Date())) + "-" + String.valueOf(DateUtil.getSecond(new Date()))
                + "_UIAutoTest_"+caseName + ".png";

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        Thread.sleep(3000);

        // FileUtils.copyFile(screenshot, new File("screenshot.jpg"));
        FileUtils.copyFile(screenshot, new File(filePath));
        return filePath;

    }
    public static WebElement getElementByChildText(List<WebElement> elements, By childBySelector,
                                                   String value) {
        WebElement foundElement = null;
        for (WebElement element : elements) {
            if (element.findElement(childBySelector).getText().equalsIgnoreCase(value)) {
                foundElement = element;
                LogWriter.info(ToolKit.class, "子元素字符: " + value+" 未找到");
                break;
            }
        }
        if (foundElement == null) {
            throw new java.util.NoSuchElementException("子元素字符 " + value
                    + " 未找到");
        }
        return foundElement;
    }

    /**
     * 字符串过滤 返回int
     * @param str
     * @return
     */
    public static int getIntFromString(String str){

        String str2="";
        if(str != null && !"".equals(str)){
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)>=48 && str.charAt(i)<=57){
                    str2+=str.charAt(i);
                }
            }
            System.out.println(str2);
            return  Integer.parseInt(str2);
        }else {
            return Integer.parseInt(null);
        }

    }

    public static String getFileFullName(String dirName,String particalFileName) throws InterruptedException {
        String filename="";
        String filenames="";
        File dir = new File(dirName);
        System.out.println("dir: "+dir);
        String[] filelist = dir.list();

        System.out.println("There are " + filelist.length + " files in the download folder");
        for (int i=0;i<filelist.length;i++) {
            filename=filelist[i];
            if(filename.contains(particalFileName)){
                filenames=particalFileName;
                break;
            }
        }

        return filenames;
    }

    /**
     * 上传文件
     * @param driver
     * @param uploadPath
     */
    public static void upload(WebDriver driver,String uploadPath){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//input[@type='file']")));
        WebElement ele = driver.findElement(By.xpath("//input[@type='file']"));     // 该元素特殊 隐藏元素
        ele.sendKeys(uploadPath);
    }


}

