package com.zetyun.uitest.utility;

import com.zetyun.driver.log.LogWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DynamicElementUtil {

    private WebElement element = null;

    private WebDriver driver;

    public DynamicElementUtil(WebDriver driver) {
        this.driver = driver;
    }


    public WebElement ModuleType(String string) throws Exception {
        try{

            ToolKit.waitForWebElement(driver, By.xpath("//*[@unselectable='on'][text()='"+string+"']"));
            element = driver.findElement(By.xpath("//*[@unselectable='on'][text()='"+string+"']"));
            return element;
        } catch (Exception e) {
            LogWriter.error(DynamicElementUtil.class,
                    "选择类型失败");
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 普通下拉项
     * @param string
     * @return
     * @throws Exception
     */
    public WebElement dropDownList(String string) throws Exception {
        try{

           // ToolKit.setScroll(driver,driver.findElement(By.xpath("//*[contains(@class,'ant-select-dropdown ant-select-dropdown')][not(contains(@class,'hidden'))]//*[@unselectable='on'][text()='"+string+"']")));
           ToolKit.waitForWebElement(driver, By.xpath("//*[contains(@class,'ant-select-dropdown ant-select-dropdown')][not(contains(@class,'hidden'))]//*[@unselectable='on'][text()='"+string+"']"));
            element = driver.findElement(By.xpath("//*[contains(@class,'ant-select-dropdown ant-select-dropdown')][not(contains(@class,'hidden'))]//*[@unselectable='on'][text()='"+string+"']"));
            return element;
        } catch (Exception e) {
            LogWriter.error(DynamicElementUtil.class,
                    "下拉框选择 "+string+" 失败");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 添加标签下拉项
     * @param string
     * @return
     * @throws Exception
     */
    public WebElement tagDropDownList(String string) throws Exception {
        try{

            ToolKit.waitForWebElement(driver, By.xpath("//*[contains(@class,'ant-select-dropdown ant-select-dropdown--multiple ant-select-dropdown-placement')][not(contains(@class,'hidden'))]//*[@unselectable='on'][text()='"+string+"']"));
            element = driver.findElement(By.xpath("//*[contains(@class,'ant-select-dropdown ant-select-dropdown--multiple ant-select-dropdown-placement')][not(contains(@class,'hidden'))]//*[@unselectable='on'][text()='"+string+"']"));
            return element;
        } catch (Exception e) {
            LogWriter.error(DynamicElementUtil.class,
                    "下拉框选择 "+string+" 失败");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 数据模块 文件类型 下拉框中选择项
     * @return
     * @throws Exception
     */
    public List<WebElement> tagDropDownListcontains() throws Exception {
        try{

            ToolKit.waitForWebElement(driver, By.xpath("//*[contains(@class,'ant-select-dropdown-menu-item')]"));
           List element = driver.findElements(By.xpath("//*[contains(@class,'ant-select-dropdown-menu-item')]"));
            return element;
        } catch (Exception e) {
            LogWriter.error(DynamicElementUtil.class,
                    "下拉框选择 失败");
            e.printStackTrace();
            return null;
        }
    }
    /**
     *  标签 下拉框中选择项
     * @return
     * @throws Exception
     */
    public WebElement tagDropDownautoml(String string) throws Exception {
        try{

            ToolKit.waitForWebElement(driver, By.xpath("//*[contains(@class,'ant-select-dropdown-menu-item')][contains(text(),'"+string+"')]"));
             element = driver.findElement(By.xpath("//*[contains(@class,'ant-select-dropdown-menu-item')][contains(text(),'"+string+"')]"));
            return element;
        } catch (Exception e) {
            LogWriter.error(DynamicElementUtil.class,
                    "下拉框选择 失败");
            e.printStackTrace();
            return null;
        }
    }
    public WebElement tagDropDownDatamotagdule(String string) throws Exception {
        try{

            ToolKit.waitForWebElement(driver, By.xpath("//*[contains(@class,'ant-select-dropdown-menu-item')]/span[contains(text(),'"+string+"')]/.."));
            element = driver.findElement(By.xpath("//*[contains(@class,'ant-select-dropdown-menu-item')]/span[contains(text(),'"+string+"')]/.."));
            return element;
        } catch (Exception e) {
            LogWriter.error(DynamicElementUtil.class,
                    "下拉框选择 失败");
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 数据模块 选择数据模块 下拉框中选择项
     * @return
     * @throws Exception
     */
    public WebElement DropDownDatamodule(String string) throws Exception {
        try{

            ToolKit.waitForWebElement(driver, By.xpath("//*[@name='"+string+"']"));
            element = driver.findElement(By.xpath("//*[@name='"+string+"']"));
            return element;
        } catch (Exception e) {
            LogWriter.error(DynamicElementUtil.class,
                    "下拉框选择 失败");
            e.printStackTrace();
            return null;
        }
    }
    //*[@name='bank_data_csv']
    /**
     * @return
     * 上传文件隐藏的元素
     */
    public WebElement uploadFile(){
        try{
            ToolKit.waitForWebElement(driver, By.xpath("//input[@type='file']"));
            element = driver.findElement(By.xpath("//input[@type='file']"));
            return element;
        } catch (Exception e) {
            LogWriter.error(DynamicElementUtil.class,
                    "上传文件路径元素加载失败");
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 删除 确认信息
     * @param DelModuleName
     * @return
     * @throws Exception
     */
    public WebElement deleteSure(String DelModuleName) throws Exception {
        try{

            ToolKit.waitForWebElement(driver, By.xpath("//*[contains(text(),'你确定删除"+DelModuleName+"')]"));
            element = driver.findElement(By.xpath("//*[contains(text(),'你确定删除"+DelModuleName+"')]"));
            return element;
        } catch (Exception e) {
            LogWriter.error(DynamicElementUtil.class,
                    "删除模块不正确");
            e.printStackTrace();
            return null;
        }
    }
    /**
     * @param boxName 复选框名称
     *  @param create  复选框名称，空的是时候为读取，非空为创建
     * 用户中心复选框勾选
     */
    public WebElement roleCheckBox(String boxName,String create) throws Exception{
        try {
            if(create.isEmpty()){
                element = driver.findElement(By.xpath("//*[@class='ant-checkbox-group']//*[text()='"+boxName+"']"));

            }else{

                element = driver.findElement(By.xpath("//*[@class='ant-checkbox-group']//*[text()='"+boxName+"']/../..//*[text()='创建']"));
            }
            return element;

        }catch (Exception e){
            LogWriter.error(DynamicElementUtil.class,"新建角色选择模块权限失败");
            e.printStackTrace();
            return null;
        }
    }
    /**
     * @param driver webdriver
     * @param sortBy 排序依据
     * @throws Exception
     * 列表排序 倒序
     */

    public WebElement sortByDesc(WebDriver driver,String sortBy) throws Exception{
        //逆序
        try {
            ToolKit.waitForWebElement(driver,By.xpath("//*[@class='zet-module-list-sort'][text()='"+sortBy+"']/..//*[@class='ant-table-column-sorter']//*[@title='↓']"));
            element= driver.findElement(By.xpath("//*[@class='zet-module-list-sort'][text()='"+sortBy+"']/..//*[@class='ant-table-column-sorter']//*[@title='↓']"));
        }catch (Exception e){
            LogWriter.error(DynamicElementUtil.class,"获取倒序排序元素失败");
            e.printStackTrace();
        }

        return element;
    }
    /**
     * @param driver webdriver
     * @param sortBy 排序依据
     * @throws Exception
     * 列表排序 正序
     */
    public WebElement sortByAsc(WebDriver driver,String sortBy) throws Exception{
        //正序
        try {
            ToolKit.waitForWebElement(driver,By.xpath("//*[@class='zet-module-list-sort'][text()='"+sortBy+"']/..//*[@class='ant-table-column-sorter']//*[@title='↑']"));
            element = driver.findElement(By.xpath("//*[@class='zet-module-list-sort'][text()='"+sortBy+"']/..//*[@class='ant-table-column-sorter']//*[@title='↑']"));
        }catch (Exception e){
            LogWriter.error(DynamicElementUtil.class,"获取正序排序元素失败");
            e.printStackTrace();
        }
        return element;


    }
    /**
     * @param driver webdriver
     * @param elementName 操作名称  复制，删除，工作流，查看等
     * @throws Exception
     * 按钮点击
     */
    public WebElement clickableElement(WebDriver driver, String elementName) throws Exception{

        try {
            ToolKit.waitForWebElement(driver,By.xpath("//*[@class='ant-table-tbody']//*[text()='"+elementName+"']"));
            element = driver.findElement(By.xpath("//*[@class='ant-table-tbody']//*[text()='"+elementName+"']"));
            Thread.sleep(3000);
        }catch (Exception e){
            LogWriter.error(DynamicElementUtil.class,"获取列表操作按钮失败");
            e.printStackTrace();
        }
        return element;


    }
    /**
     * @param driver webdriver
     * @param moduleName 模块名称
     * @throws Exception
     * 点击模块名称
     */
    public WebElement clickModuleName(WebDriver driver, String moduleName) {

        try {
            ToolKit.waitForWebElement(driver,By.xpath("//*[@class='zet-module-list-link'][text()='"+moduleName+"']"));
            element = driver.findElement(By.xpath("//*[@class='zet-module-list-link'][text()='"+moduleName+"']"));
        }catch (Exception e){
            LogWriter.error(DynamicElementUtil.class,"获取模块名称失败");
            e.printStackTrace();
        }
        return element;

    }
    /**
     * @param driver webdriver
     * @param userName 被授权用户名称
     * @param  auths 授权名称
     * @throws Exception
     * 勾选授权页面中添加页面的用户名和权限
     */
    public void addAuth(WebDriver driver, String userName, String auths) throws Exception{
        try {
            ToolKit.waitForWebElement(driver,By.xpath("//*[@id='"+userName+"']"));
            driver.findElement(By.xpath("//*[@id='"+userName+"']")).click();
            ToolKit.setScroll(driver,driver.findElement(By.xpath("//*[@class='ant-checkbox-group-item ant-checkbox-wrapper']//*[text()='读取']")));
            driver.findElement(By.xpath("//*[@class='ant-checkbox-group-item ant-checkbox-wrapper']//*[text()='"+auths+"']")).click();

        }catch (Exception e){
            LogWriter.error(DynamicElementUtil.class,"获取用户名或权限失败");
            e.printStackTrace();

        }
    }

    /**
     * 编码页面控制台 输入信息
     * @param string
     * @return
     * @throws Exception
     */
    public WebElement consoleText(String string) throws Exception {
        try{

            ToolKit.waitForWebElement(driver, By.xpath("//*[@id='editor-console']//*[contains(text(),'"+string+"')]"));
            element = driver.findElement(By.xpath("//*[@id='editor-console']//*[contains(text(),'"+string+"')]"));
            return element;
        } catch (Exception e) {
            LogWriter.error(DynamicElementUtil.class,
                    "控制台信息显示失败");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 测试 选择共享目录文件
     * @param filename
     * @return
     */
    public WebElement sysFileChoose(String filename){
        try {
            ToolKit.waitForWebElement(driver, By.xpath("//*[@class='ant-tree']//span[@title='" + filename + "']"));
            element = driver.findElement(By.xpath("//*[@class='ant-tree']//span[@title='" + filename + "']"));
            return element;
        }catch(Exception e){
                LogWriter.error(DynamicElementUtil.class,
                "选择共享目录文件"+filename+"失败");
        e.printStackTrace();
        return null;
    }
    }
    /**
     * @param fileName 入口文件的名称
     * @throws Exception
     * 选择入口文件
     */
    public WebElement chooseFile(String fileName) throws Exception{
        try{
            ToolKit.waitForWebElement(driver,By.xpath("//*[@class='clearMaigin'][text()='"+fileName+"']"));
            element = driver.findElement(By.xpath("//*[@class='clearMaigin'][text()='"+fileName+"']"));
        }catch (Exception e){
            LogWriter.error(DynamicElementUtil.class,"获取入口文件失败");
            e.printStackTrace();
        }
        return element;
    }
    /**
     * @param fileType 添加文件，添加文件夹
     * @throws Exception
     * 添加文件或添加文件夹
     */
    public WebElement addFile(String fileType) throws Exception{
        try {
            ToolKit.waitForWebElement(driver,By.xpath("//*[@class='react-contextmenu-item'][text()='"+fileType+"']"));
            element = driver.findElement(By.xpath("//*[@class='react-contextmenu-item'][text()='"+fileType+"']"));
        }catch (Exception e){
            LogWriter.error(DynamicElementUtil.class,"获取文件树添加文件失败");
            e.printStackTrace();
        }
        return element;

    }
    /**
     * @param fileName  文件名
     * @throws Exception
     * 获取文件树中指定名字的文件
     */
    public WebElement getTreeFile(String fileName) throws Exception{
        try {
            ToolKit.waitForWebElement(driver,By.xpath("//*[@class='ace-tree-item-name'][text()='"+fileName+"']"));
            element = driver.findElement(By.xpath("//*[@class='ace-tree-item-name'][text()='"+fileName+"']"));

        }catch (Exception e){
            LogWriter.error(DynamicElementUtil.class,"获取文件树文件失败");
            e.printStackTrace();
        }
        return element;
    }
    /**
     * @param inputName  输入或输出的名称
     * @throws Exception
     * 获取输入输出右侧的删除按钮
     */
    public WebElement getParaDelBtn(String inputName)throws Exception{
        try {
            ToolKit.waitForWebElement(driver,By.xpath("//*[@value='"+inputName+"']/..//*[@class='icon-cut']"));
            element =  driver.findElement(By.xpath("//*[@value='"+inputName+"']/..//*[@class='icon-cut']"));
        }catch (Exception e){
            LogWriter.error(DynamicElementUtil.class,"获取输入输出删除按钮失败");
            e.printStackTrace();
        }
        return element;
    }

    /**
     * 包含text字符得元素
     * @param text
     * @return
     * @throws Exception
     */
    public WebElement eleWithTextName(String text)throws Exception{
        try {
            ToolKit.waitForWebElement(driver,By.xpath("//*[contains(text(),'"+text+"')]"));
            element =  driver.findElement(By.xpath("//*[contains(text(),'"+text+"')]"));
        }catch (Exception e){
            LogWriter.error(DynamicElementUtil.class,"获取元素: "+text+" 失败");
            e.printStackTrace();
        }
        return element;
    }

    /**
     * 自动建模算法选择开关
     * @param string
     * @return
     */
    public WebElement algorithmstatus(String string){
        try{
            ToolKit.waitForWebElement(driver,By.xpath("//*[contains(@class,'ant-menu-item zet-content-right-menulist-item')]//span[contains(text(),'"+string+"')]/..//*[contains(@class,'zet-content-right-menulist-item-right ant-switch-small')]"));
            element =  driver.findElement(By.xpath("//*[contains(@class,'ant-menu-item zet-content-right-menulist-item')]//span[contains(text(),'"+string+"')]/..//*[contains(@class,'zet-content-right-menulist-item-right ant-switch-small')]"));
            return element;
        }catch (Exception e){
            LogWriter.error(DynamicElementUtil.class,"获取元素: "+string+" 失败");
            e.printStackTrace();
            return null;
        }
    }
    /**
     * @param paraName  输入或输出的名称
     * @throws Exception
     * 获取发布到模型仓库下拉框元素
     */

    public WebElement getPullElement(String paraName)throws Exception {
        try {
            ToolKit.waitForWebElement(driver,By.xpath("//*[@unselectable='on'][text()='"+paraName+"']"));
            element = driver.findElement(By.xpath("//*[@unselectable='on'][text()='"+paraName+"']"));
        }catch (Exception e){
            LogWriter.error(DynamicElementUtil.class,"获取下拉框失败");
            e.printStackTrace();
        }
        return element;
    }
    /**
     * @param algName  算法名称
     * @param btnName   按钮名
     * @throws Exception
     * 获取算法右侧的查看和日志按钮
     */
    public WebElement getBtnByAlgName(String algName,String btnName) throws Exception{
        try {
            ToolKit.waitForWebElement(driver,By.xpath("//*[@class='listdetail-title-left']//*[text()='"+algName+"']/../../../../..//*[text()='"+btnName+"']"));
            element = driver.findElement(By.xpath("//*[@class='listdetail-title-left']//*[text()='"+algName+"']/../../../../..//*[text()='"+btnName+"']"));
        }catch (Exception e){
            LogWriter.error(DynamicElementUtil.class,"获取右侧查看和日志按钮失败");
            e.printStackTrace();
        }
        return element;
    }
    /**
     * @param titleName  模型对应的任务名称
     * @throws Exception
     * 获取左侧任务名称的元素
     */
    public WebElement getTitleElement(String titleName) throws Exception {
        try {
            ToolKit.waitForWebElement(driver,By.xpath("//*[@title='"+titleName+"']"));
            element = driver.findElement(By.xpath("//*[@title='"+titleName+"']"));

        }catch (Exception e){
            LogWriter.error(DynamicElementUtil.class,"获取title失败");
            e.printStackTrace();
        }
        return element;
    }
    /**
     * @param taskName  输入或输出的名称
     * @throws Exception
     * 获取左侧列表中任务列表tab行的元素
     */
    public WebElement getTaskTab(String taskName)throws Exception {
        try {
            ToolKit.waitForWebElement(driver,By.xpath("//*[text()='"+taskName+"']/../../..//*[@class='ant-col-20']"));
            element = driver.findElement(By.xpath("//*[text()='"+taskName+"']/../../..//*[@class='ant-col-20']"));
        }catch (Exception e){
            LogWriter.error(DynamicElementUtil.class,"获取模型tab失败");
            e.printStackTrace();

        }
        return element;
    }
    /**
     * @param algName  输入或输出的名称
     * @throws Exception
     * 获取右上图片上的算法名称按钮
     */

    public WebElement getAlgInChart(String algName)throws Exception {
        try {
            ToolKit.waitForWebElement(driver,By.xpath("//*[@title='"+algName+"'][@class='g2-legend-text zet-legend-style']"));
            element = driver.findElement(By.xpath("//*[@title='"+algName+"'][@class='g2-legend-text zet-legend-style']"));
        }catch (Exception e){
            LogWriter.error(DynamicElementUtil.class,"获取chart中算法名称失败");
            e.printStackTrace();

        }
        return element;
    }

    /**
     * 数据应用分析模块/数据模块 列表选择
     * @param name
     * @return
     * @throws Exception
     */
    public WebElement getmoduleTab(String name) throws Exception{
        try {
            ToolKit.waitForWebElement(driver,By.xpath("//*[@class='ant-tabs-nav ant-tabs-nav-animated']//div[@role][text()='"+name+"']"));
            element = driver.findElement(By.xpath("//*[@class='ant-tabs-nav ant-tabs-nav-animated']//div[@role][text()='"+name+"']"));
        }catch (Exception e){
            LogWriter.error(DynamicElementUtil.class,"获取模块元素失败");
            e.printStackTrace();

        }
        return element;
    }

    /**
     * 工作流 编辑板 下分析模块
     * @param name
     * @return
     * @throws Exception
     */
    public WebElement getDataModuleByNameUnderEdit(String name ) throws Exception{
        try {
            ToolKit.waitForWebElement(driver,By.xpath("//*[@id='zet-main-center-c']//*[contains(@class,'zet-module-item-data')]//*[@class='wm-name'][text()='"+name+"']"));
            element = driver.findElement(By.xpath("//*[@id='zet-main-center-c']//*[contains(@class,'zet-module-item-data')]//*[@class='wm-name'][text()='"+name+"']"));
        }catch (Exception e){
            LogWriter.error(DynamicElementUtil.class,"获取模块元素失败");
            e.printStackTrace();

        }
        return element;
    }

    /**
     * 工作流 编辑板 下分析模块
     * @param name
     * @return
     * @throws Exception
     */
    public WebElement getAnalysisModuleByNameUnderEdit(String name ) throws Exception{
        try {
            ToolKit.waitForWebElement(driver,By.xpath("//*[@id='zet-main-center-c']//*[contains(@class,'zet-module-item-analysis')]//*[@class='wm-name'][text()='"+name+"']"));
            element = driver.findElement(By.xpath("//*[@id='zet-main-center-c']//*[contains(@class,'zet-module-item-analysis')]//*[@class='wm-name'][text()='"+name+"']"));
        }catch (Exception e){
            LogWriter.error(DynamicElementUtil.class,"获取分析模块元素失败");
            e.printStackTrace();

        }
        return element;
    }
    /**
     * 获取历史版本radio
     * @param name
     * @return
     * @throws Exception
     */
    public WebElement getDelVerRadio(String name) throws Exception{
        try {
            ToolKit.waitForWebElement(driver,By.xpath("//*[text()='"+name+"']/..//*[@class='ant-radio-input']"));
            element = driver.findElement(By.xpath("//*[text()='"+name+"']/..//*[@class='ant-radio-input']"));
        }catch (Exception e){
            LogWriter.error(DynamicElementUtil.class,"获取版本按钮失败");
            e.printStackTrace();

        }
        return element;
    }
    /**
     * 获取历史版本删除
     * @param name
     * @return
     * @throws Exception
     */
    public WebElement getDelVerBtn(String name)throws Exception {
        try {
            ToolKit.waitForWebElement(driver,By.xpath("//*[text()='"+name+"']/..//*[text()='删除']"));
            element = driver.findElement(By.xpath("//*[text()='"+name+"']/..//*[text()='删除']"));
        }catch (Exception e){
            LogWriter.error(DynamicElementUtil.class,"获取版本删除按钮失败");
            e.printStackTrace();

        }
        return element;
    }
    /**
     * 获取搜索列表元素
     * @param name
     * @return
     * @throws Exception
     */
    public WebElement getRepoPackage(String name) {
        try {
            ToolKit.waitForWebElement(driver,By.xpath("//*[@class='zet-arithmetic-pkg-search']/..//*[@class='zet-arithmetic-pkg-list-left']//*[@title='"+name+"']"));
            element = driver.findElement(By.xpath("//*[@class='zet-arithmetic-pkg-search']/..//*[@class='zet-arithmetic-pkg-list-left']//*[@title='"+name+"']"));
        }catch (Exception e){
            LogWriter.error(DynamicElementUtil.class,"获取安装成功失败");
            e.printStackTrace();

        }
        return element;
    }
}
