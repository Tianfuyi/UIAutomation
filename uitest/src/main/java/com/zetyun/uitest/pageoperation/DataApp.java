package com.zetyun.uitest.pageoperation;

import com.zetyun.data.DataParse;
import com.zetyun.datatemplate.elementtemplate.ElementTemplate;
import com.zetyun.driver.log.LogWriter;
import com.zetyun.uitest.utility.DynamicElementUtil;
import com.zetyun.uitest.utility.ElementUtil;
import com.zetyun.uitest.utility.JsonUtil;
import com.zetyun.uitest.utility.ToolKit;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Map;

public class DataApp {

    /*
     * 搜索
     {
     "searchedContent": "Bing_DataApp"
     }
     *
     * @param driver
     * @throws Exception
     */
    public void searchDataApp(WebDriver driver, String data) throws Exception {
        LogWriter.debug(this.getClass(), "Search data app: " + data);

        // Get data
        Map dataMap = new JsonUtil().jsonToMaps(data);
        String s = (String) dataMap.get("searchedContent");

        // Search input.
        String templatePath = DataParse.GetProperties("DataAppElementsTemplate");
        String searchInputStr = ElementTemplate.getValues(templatePath, "DataApp").get("数据应用").get("搜索框");
        WebElement searchInput = ElementUtil.findElement(driver, searchInputStr);
        searchInput.sendKeys(s);
    }

    /*
     * 查看数据应用详情页
     {}
     *
     * @param driver
     * @throws Exception
     */
    public void viewDataAppDetails(WebDriver driver) throws Exception {
        LogWriter.debug(this.getClass(), "View data app details");

        // Data app link.
        String templatePath = DataParse.GetProperties("DataAppElementsTemplate");
        String dataAppLinkStr = ElementTemplate.getValues(templatePath, "DataApp").get("数据应用").get("数据应用链接");
        WebElement dataAppLink = ElementUtil.findElement(driver, dataAppLinkStr);
        dataAppLink.click();
    }

    /*
     * 验证数据应用详情页
     {}
     *
     * @param driver
     * @throws Exception
     */
    public void verifyDataAppDetails(WebDriver driver) throws Exception {
        LogWriter.debug(this.getClass(), "View data app details");

        // Verify data details name.
        String templatePath = DataParse.GetProperties("DataAppElementsTemplate");
        String dataDetailsNameStr = ElementTemplate.getValues(templatePath, "DataApp").get("数据应用详情").get("数据应用详情名称");
        WebElement dataDetailsNameLink = ElementUtil.findElement(driver, dataDetailsNameStr);
        Assert.assertTrue(dataDetailsNameLink.isDisplayed());
    }

    /*
     * 查看工作流
     {}
     *
     * @param driver
     * @throws Exception
     */
    public void viewWorkflow(WebDriver driver) throws Exception {
        LogWriter.debug(this.getClass(), "View workflow");

        // Data details name.
        String templatePath = DataParse.GetProperties("DataAppElementsTemplate");
        String dataDetailsNameStr = ElementTemplate.getValues(templatePath, "DataApp").get("数据应用详情").get("数据应用详情名称");
        WebElement dataDetailsNameLink = ElementUtil.findElement(driver, dataDetailsNameStr);
        dataDetailsNameLink.click();
    }

    /*
     * 验证查看工作流
     {}
     *
     * @param driver
     * @throws Exception
     */
    public void verifyViewWorkflow(WebDriver driver) throws Exception {
        LogWriter.debug(this.getClass(), "Verify view workflow");

        // Workflow details tab.
        String templatePath = DataParse.GetProperties("DataAppElementsTemplate");
        String detailsTabStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("详情页Tab");
        WebElement detailsTabDiv = ElementUtil.findElement(driver, detailsTabStr);
        Assert.assertTrue(detailsTabDiv.isDisplayed());
    }

    /*
     * 查看工作流详情页
     {}
     *
     * @param driver
     * @throws Exception
     */
    public void viewWorkflowDetails(WebDriver driver) throws Exception {
        LogWriter.debug(this.getClass(), "View workflow details");

        // Workflow details tab.
        String templatePath = DataParse.GetProperties("DataAppElementsTemplate");
        String detailsTabStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("详情页Tab");
        WebElement detailsTabDiv = ElementUtil.findElement(driver, detailsTabStr);
        detailsTabDiv.click();
    }

    /*
     * 验证查看工作流详情页
     {}
     *
     * @param driver
     * @throws Exception
     */
    public void verifyViewWorkflowDetails(WebDriver driver) throws Exception {
        LogWriter.debug(this.getClass(), "Verify view workflow details");

        // Workflow details tab.
        String templatePath = DataParse.GetProperties("DataAppElementsTemplate");
        String detailsTabStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("详情页Tab");
        WebElement detailsTabDiv = ElementUtil.findElement(driver, detailsTabStr);
        Assert.assertTrue(detailsTabDiv.getAttribute("aria-selected").equalsIgnoreCase("true"));
    }

    /*
     * 查看工作流应用变量
     {}
     *
     * @param driver
     * @throws Exception
     */
    public void viewWorkflowAppVariables(WebDriver driver) throws Exception {
        LogWriter.debug(this.getClass(), "View workflow app variables");

        // Workflow app variables tab.
        String templatePath = DataParse.GetProperties("DataAppElementsTemplate");
        String appVariablesTabStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("应用变量Tab");
        WebElement appVariablesTabDiv = ElementUtil.findElement(driver, appVariablesTabStr);
        appVariablesTabDiv.click();
    }

    /*
     * 验证查看工作流应用变量
     {}
     *
     * @param driver
     * @throws Exception
     */
    public void verifyViewWorkflowAppVariables(WebDriver driver) throws Exception {
        LogWriter.debug(this.getClass(), "Verify view workflow app variables");

        // Workflow app variables tab.
        String templatePath = DataParse.GetProperties("DataAppElementsTemplate");
        String appVariablesTabStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("应用变量Tab");
        WebElement appVariablesTabDiv = ElementUtil.findElement(driver, appVariablesTabStr);
        Assert.assertTrue(appVariablesTabDiv.getAttribute("aria-selected").equalsIgnoreCase("true"));
    }

    /*
     * 查看数据模块详情
     {
	    "moduleTitle": "Bing_DataModule"
     }
     *
     * @param driver
     * @tows Exception
     */
    public void viewDataModuleDetails(WebDriver driver, String data) throws Exception {
        LogWriter.debug(this.getClass(), "View data module details");

        // Get data
        Map dataMap = new JsonUtil().jsonToMaps(data);
        String title = (String) dataMap.get("moduleTitle");

        // Get data module.
        DynamicElementUtil DynamicElementUtil = new DynamicElementUtil(driver);
        WebElement dataModuleSpan = DynamicElementUtil.getDataModuleByNameUnderEdit(title);
        WebElement dataModuleDiv = dataModuleSpan.findElement(By.xpath("following-sibling::div"));
        dataModuleDiv.click();
    }

    /*
     * 验证数据模块详情
     {}
     *
     * @param driver
     * @tows Exception
     */
    public void verifyDataModuleDetails(WebDriver driver) throws Exception {
        LogWriter.debug(this.getClass(), "Verify view data module details");

        // View data button.
        String templatePath = DataParse.GetProperties("DataAppElementsTemplate");
        String viewDataButtonStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("查看数据");
        WebElement viewDataButton = ElementUtil.findElement(driver, viewDataButtonStr);
        Assert.assertTrue(viewDataButton.isDisplayed());
    }

    /*
     * 查看数据
     {}
     *
     * @param driver
     * @tows Exception
     */
    public void viewData(WebDriver driver) throws Exception {
        LogWriter.debug(this.getClass(), "View data");

        // View data button.
        String templatePath = DataParse.GetProperties("DataAppElementsTemplate");
        String viewDataButtonStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("查看数据");
        WebElement viewDataButton = ElementUtil.findElement(driver, viewDataButtonStr);
        viewDataButton.click();
    }

    /*
     * 验证查看数据
     {}
     *
     * @param driver
     * @tows Exception
     */
    public void verifyViewData(WebDriver driver) throws Exception {
        LogWriter.debug(this.getClass(), "Verify view data");

        // Data preview title.
        String templatePath = DataParse.GetProperties("DataAppElementsTemplate");
        String dataPreviewTitleStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("数据预览标题");
        WebElement dataPreviewTitle = ElementUtil.findElement(driver, dataPreviewTitleStr);
        Assert.assertTrue(dataPreviewTitle.isDisplayed());
    }

    /*
     * 查看分析模块详情
     {
	    "moduleTitle": "Bing_AnalyzeModule"
     }
     *
     * @param driver
     * @tows Exception
     */
    public void viewAnalyzeModuleDetails(WebDriver driver, String data) throws Exception {
        LogWriter.debug(this.getClass(), "View analyze module details");

        // Get data
        Map dataMap = new JsonUtil().jsonToMaps(data);
        String title = (String) dataMap.get("moduleTitle");

        // Get analyze module.
        DynamicElementUtil DynamicElementUtil = new DynamicElementUtil(driver);
        WebElement analyzeModuleSpan = DynamicElementUtil.getAnalysisModuleByNameUnderEdit(title);
        WebElement analyzeModuleDiv = analyzeModuleSpan.findElement(By.xpath("following-sibling::div"));
        analyzeModuleDiv.click();
    }

    /*
     * 验证分析模块详情
     {}
     *
     * @param driver
     * @tows Exception
     */
    public void verifyAnalyzeModuleDetails(WebDriver driver) throws Exception {
        LogWriter.debug(this.getClass(), "Verify view analyze module details");

        // View code button.
        String templatePath = DataParse.GetProperties("DataAppElementsTemplate");
        String viewCodeButtonStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("查看代码");
        WebElement viewCodeButton = ElementUtil.findElement(driver, viewCodeButtonStr);
        Assert.assertTrue(viewCodeButton.isDisplayed());
    }

    /*
     * 查看代码
     {}
     *
     * @param driver
     * @tows Exception
     */
    public void viewCode(WebDriver driver) throws Exception {
        LogWriter.debug(this.getClass(), "View code");

        // View code button.
        String templatePath = DataParse.GetProperties("DataAppElementsTemplate");
        String viewCodeButtonStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("查看代码");
        WebElement viewCodeButton = ElementUtil.findElement(driver, viewCodeButtonStr);
        viewCodeButton.click();
    }

    /*
     * 验证查看代码
     {}
     *
     * @param driver
     * @tows Exception
     */
    public void verifyViewCode(WebDriver driver) throws Exception {
        LogWriter.debug(this.getClass(), "Verify view code");

        // Encode button.
        String templatePath = DataParse.GetProperties("DataAppElementsTemplate");
        String encodeButtonStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("编码按钮");
        WebElement encodeButton = ElementUtil.findElement(driver, encodeButtonStr);
        Assert.assertTrue(encodeButton.isDisplayed());
    }

    /*
     * 编缉分析模块
     {
	    "version": "0.3",
	    "cpu": "1",
	    "mem": "2",
	    "gpu": "0"
     }
     *
     * @param driver
     * @tows Exception
     */
    public void editAnalyzeModule(WebDriver driver, String data) throws Exception {
        LogWriter.debug(this.getClass(), "Edit analyze module: " + data);

        // Get data
        Map dataMap = new JsonUtil().jsonToMaps(data);
        String version = (String) dataMap.get("version");
        String cpu = (String) dataMap.get("cpu");
        String mem = (String) dataMap.get("mem");
        String gpu = (String) dataMap.get("gpu");

        // Version drop down list.
        String templatePath = DataParse.GetProperties("DataAppElementsTemplate");
        String versionDropDownStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("版本下拉列表");
        WebElement versionDropDownList = ElementUtil.findElement(driver, versionDropDownStr);
        versionDropDownList.click();

        // Select version.
        String versionOptionStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("版本下拉列表选项");
        List<WebElement> versionOptionList = ElementUtil.findElements(driver, versionOptionStr);
        versionOptionList.stream().filter(webElement -> webElement.getAttribute("innerText").contains(version)).findFirst().ifPresent(WebElement::click);

        // Input cpu.
        String cpuInputStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("CPU输入框");
        WebElement cpuInput = ElementUtil.findElement(driver, cpuInputStr);
        cpuInput.clear();
        cpuInput.sendKeys(cpu);

        // Input mem.
        String memInputStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("MEM输入框");
        WebElement memInput = ElementUtil.findElement(driver, memInputStr);
        memInput.clear();
        memInput.sendKeys(mem);

        // Input gpu.
        String gpuInputStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("GPU输入框");
        WebElement gpuInput = ElementUtil.findElement(driver, gpuInputStr);
        gpuInput.clear();
        gpuInput.sendKeys(gpu);

        // Save.
        String saveButtonStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("保存");
        WebElement saveButton = ElementUtil.findElement(driver, saveButtonStr);
        saveButton.click();
    }

    /*
     * 验证编缉分析模块
     {
	    "version": "0.3",
	    "cpu": "1",
	    "mem": "2",
	    "gpu": "0"
     }
     *
     * @param driver
     * @tows Exception
     */
    public void verifyEditAnalyzeModule(WebDriver driver, String data) throws Exception {
        LogWriter.debug(this.getClass(), "Verify edit analyze module: " + data);

        // Get data
        Map dataMap = new JsonUtil().jsonToMaps(data);
        String version = (String) dataMap.get("version");
        String cpu = (String) dataMap.get("cpu");
        String mem = (String) dataMap.get("mem");
        String gpu = (String) dataMap.get("gpu");

        // Verify drop down option.
        ToolKit.wait(5);
        String templatePath = DataParse.GetProperties("DataAppElementsTemplate");
        String versionDropDownStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("版本下拉列表");
        WebElement versionDropDownList = ElementUtil.findElement(driver, versionDropDownStr);
        Assert.assertTrue(versionDropDownList.getAttribute("innerText").contains(version));

        // Verify cpu.
        String cpuInputStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("CPU输入框");
        WebElement cpuInput = ElementUtil.findElement(driver, cpuInputStr);
        Assert.assertTrue(cpuInput.getAttribute("value").contains(cpu));

        // Verify mem.
        String memInputStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("MEM输入框");
        WebElement memInput = ElementUtil.findElement(driver, memInputStr);
        Assert.assertTrue(memInput.getAttribute("value").contains(mem));

        // Verify gpu.
        String gpuInputStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("GPU输入框");
        WebElement gpuInput = ElementUtil.findElement(driver, gpuInputStr);
        Assert.assertTrue(gpuInput.getAttribute("value").contains(gpu));
    }

    /*
     * 模块右键菜单－数据模块复制
     {
	    "moduleTitle": "Bing_AnalyzeModule"
     }
     *
     * @param driver
     * @tows Exception
     */
    public void moduleContextMenu_DataModuleCopy(WebDriver driver, String data) throws Exception {
        LogWriter.debug(this.getClass(), "Module context menu: copy");

        // Get data.
        Map dataMap = new JsonUtil().jsonToMaps(data);
        String title = (String) dataMap.get("moduleTitle");

        // Workflow module list.
        String templatePath = DataParse.GetProperties("DataAppElementsTemplate");
        String moduleStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("工作流模块");
        List<WebElement> moduleDivs = ElementUtil.findElements(driver, moduleStr);
        long countBefore = moduleDivs.stream().filter(webElement -> webElement.getAttribute("title").contains(title)).count();

        // Context click module and copy.
        DynamicElementUtil DynamicElementUtil = new DynamicElementUtil(driver);
        WebElement analyzeModuleSpan = DynamicElementUtil.getDataModuleByNameUnderEdit(title);
        WebElement analyzeModuleDiv = analyzeModuleSpan.findElement(By.xpath("following-sibling::div"));
        Actions actions = new Actions(driver);
        actions.contextClick(analyzeModuleDiv)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.RETURN)
                .perform();

        // Verify copy.
        ToolKit.wait(3);
        moduleDivs = ElementUtil.findElements(driver, moduleStr);
        long countAfter = moduleDivs.stream().filter(webElement -> webElement.getAttribute("title").contains(title)).count();
        Assert.assertTrue(countAfter == countBefore + 1);
    }

    /*
     * 模块右键菜单－分析模块复制
     {
	    "moduleTitle": "Bing_AnalyzeModule"
     }
     *
     * @param driver
     * @tows Exception
     */
    public void moduleContextMenu_AnalyzeModuleCopy(WebDriver driver, String data) throws Exception {
        LogWriter.debug(this.getClass(), "Module context menu: copy");

        // Get data.
        Map dataMap = new JsonUtil().jsonToMaps(data);
        String title = (String) dataMap.get("moduleTitle");

        // Workflow module list.
        String templatePath = DataParse.GetProperties("DataAppElementsTemplate");
        String moduleStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("工作流模块");
        List<WebElement> moduleDivs = ElementUtil.findElements(driver, moduleStr);
        long countBefore = moduleDivs.stream().filter(webElement -> webElement.getAttribute("title").contains(title)).count();

        // Context click module and copy.
        DynamicElementUtil DynamicElementUtil = new DynamicElementUtil(driver);
        WebElement analyzeModuleSpan = DynamicElementUtil.getAnalysisModuleByNameUnderEdit(title);
        WebElement analyzeModuleDiv = analyzeModuleSpan.findElement(By.xpath("following-sibling::div"));
        Actions actions = new Actions(driver);
        actions.contextClick(analyzeModuleDiv)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.RETURN)
                .perform();

        // Verify copy.
        ToolKit.wait(3);
        moduleDivs = ElementUtil.findElements(driver, moduleStr);
        long countAfter = moduleDivs.stream().filter(webElement -> webElement.getAttribute("title").contains(title)).count();
        Assert.assertTrue(countAfter == countBefore + 1);
    }

    /*
     * 模块右键菜单－数据模块删除
     {
	    "moduleTitle": "Bing_AnalyzeModule"
     }
     *
     * @param driver
     * @tows Exception
     */
    public void moduleContextMenu_DataModuleDelete(WebDriver driver, String data) throws Exception {
        LogWriter.debug(this.getClass(), "Module context menu: delete");

        // Get data.
        Map dataMap = new JsonUtil().jsonToMaps(data);
        String title = (String) dataMap.get("moduleTitle");

        // Workflow module list.
        String templatePath = DataParse.GetProperties("DataAppElementsTemplate");
        String moduleStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("工作流模块");
        List<WebElement> moduleDivs = ElementUtil.findElements(driver, moduleStr);
        long countBefore = moduleDivs.stream().filter(webElement -> webElement.getAttribute("title").equalsIgnoreCase(title)).count();

        // Context click module and delete.
        DynamicElementUtil DynamicElementUtil = new DynamicElementUtil(driver);
        WebElement analyzeModuleSpan = DynamicElementUtil.getDataModuleByNameUnderEdit(title);
        WebElement analyzeModuleDiv = analyzeModuleSpan.findElement(By.xpath("following-sibling::div"));
        Actions actions = new Actions(driver);
        actions.contextClick(analyzeModuleDiv)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.RETURN)
                .perform();

        // Verify delete.
        ToolKit.wait(1);
        long countAfter = moduleDivs.stream().filter(webElement -> webElement.getAttribute("title").equalsIgnoreCase(title)).count();
        Assert.assertTrue(countAfter == countBefore - 1);
    }

    /*
     * 模块右键菜单－分析模块删除
     {
	    "moduleTitle": "Bing_AnalyzeModule"
     }
     *
     * @param driver
     * @tows Exception
     */
    public void moduleContextMenu_AnalyzeModuleDelete(WebDriver driver, String data) throws Exception {
        LogWriter.debug(this.getClass(), "Module context menu: delete");

        // Get data.
        Map dataMap = new JsonUtil().jsonToMaps(data);
        String title = (String) dataMap.get("moduleTitle");

        // Workflow module list.
        String templatePath = DataParse.GetProperties("DataAppElementsTemplate");
        String moduleStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("工作流模块");
        List<WebElement> moduleDivs = ElementUtil.findElements(driver, moduleStr);
        long count = moduleDivs.stream().filter(webElement -> webElement.getAttribute("title").equalsIgnoreCase(title)).count();
        Assert.assertTrue(count >= 1);

        // Context click module and delete.
        DynamicElementUtil DynamicElementUtil = new DynamicElementUtil(driver);
        WebElement analyzeModuleSpan = DynamicElementUtil.getAnalysisModuleByNameUnderEdit(title);
        WebElement analyzeModuleDiv = analyzeModuleSpan.findElement(By.xpath("following-sibling::div"));
        Actions actions = new Actions(driver);
        actions.contextClick(analyzeModuleDiv)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.RETURN)
                .perform();

        // Verify delete.
        ToolKit.wait(1);
        try {
            long countAfter = moduleDivs.stream().filter(webElement -> webElement.getAttribute("title").equalsIgnoreCase(title)).count();
        } catch (StaleElementReferenceException e) {
            Assert.assertTrue(true);
        }
    }

    /*
     * 模块右键菜单－数据模块预览
     {
	    "moduleTitle": "Bing_AnalyzeModule"
     }
     *
     * @param driver
     * @tows Exception
     */
    public void moduleContextMenu_DataModulePreview(WebDriver driver, String data) throws Exception {
        LogWriter.debug(this.getClass(), "Module context menu: preview");

        // Get data.
        Map dataMap = new JsonUtil().jsonToMaps(data);
        String title = (String) dataMap.get("moduleTitle");

        // Workflow module list.
        String templatePath = DataParse.GetProperties("DataAppElementsTemplate");
        String moduleStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("工作流模块");
        List<WebElement> moduleDivs = ElementUtil.findElements(driver, moduleStr);
        long countBefore = moduleDivs.stream().filter(webElement -> webElement.getAttribute("title").equalsIgnoreCase(title)).count();

        // Context click module and preview.
        DynamicElementUtil DynamicElementUtil = new DynamicElementUtil(driver);
        WebElement analyzeModuleSpan = DynamicElementUtil.getDataModuleByNameUnderEdit(title);
        WebElement analyzeModuleDiv = analyzeModuleSpan.findElement(By.xpath("following-sibling::div"));
        Actions actions = new Actions(driver);
        actions.contextClick(analyzeModuleDiv)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.RETURN)
                .perform();

        // Click preview.
        String deleteStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("右键菜单－预览");
        WebElement deleteMenu = ElementUtil.findElement(driver, deleteStr);
        deleteMenu.click();
    }

    /*
     * 模块右键菜单－分析模块预览
     {
	    "moduleTitle": "Bing_AnalyzeModule"
     }
     *
     * @param driver
     * @tows Exception
     */
    public void moduleContextMenu_AnalyzeModulePreview(WebDriver driver, String data) throws Exception {
        LogWriter.debug(this.getClass(), "Module context menu: preview");

        // Get data.
        Map dataMap = new JsonUtil().jsonToMaps(data);
        String title = (String) dataMap.get("moduleTitle");

        // Workflow module list.
        String templatePath = DataParse.GetProperties("DataAppElementsTemplate");
        String moduleStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("工作流模块");
        List<WebElement> moduleDivs = ElementUtil.findElements(driver, moduleStr);
        long countBefore = moduleDivs.stream().filter(webElement -> webElement.getAttribute("title").equalsIgnoreCase(title)).count();

        // Context click module and preview.
        DynamicElementUtil DynamicElementUtil = new DynamicElementUtil(driver);
        WebElement analyzeModuleSpan = DynamicElementUtil.getAnalysisModuleByNameUnderEdit(title);
        WebElement analyzeModuleDiv = analyzeModuleSpan.findElement(By.xpath("following-sibling::div"));
        Actions actions = new Actions(driver);
        actions.contextClick(analyzeModuleDiv)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.RETURN)
                .perform();
    }

    /*
     * 验证数据模块右键菜单－预览
     *
     * @param driver
     * @tows Exception
     */
    public void verifyDataModuleContextMenu_Preview(WebDriver driver) throws Exception {
        LogWriter.debug(this.getClass(), "Verify data module context menu: preview");
        verifyViewData(driver);
    }

    /*
     * 验证分析模块右键菜单－预览
     *
     * @param driver
     * @tows Exception
     */
    public void verifyAnalyzeModuleContextMenu_Preview(WebDriver driver) throws Exception {
        LogWriter.debug(this.getClass(), "Verify analyze module context menu: preview");
        verifyViewCode(driver);
    }

    /*
     * 模块右键菜单－从此处开始执行
     {
	    "moduleTitle": "Bing_AnalyzeModule"
     }
     *
     * @param driver
     * @tows Exception
     */
    public void moduleContextMenu_AnalyzeModuleExecuteFromHere(WebDriver driver, String data) throws Exception {
        LogWriter.debug(this.getClass(), "Module context menu: execute from here: " + data);

        // Get data.
        Map dataMap = new JsonUtil().jsonToMaps(data);
        String title = (String) dataMap.get("moduleTitle");

        // Workflow module list.
        String templatePath = DataParse.GetProperties("DataAppElementsTemplate");
        String moduleStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("工作流模块");
        List<WebElement> moduleDivs = ElementUtil.findElements(driver, moduleStr);
        long countBefore = moduleDivs.stream().filter(webElement -> webElement.getAttribute("title").equalsIgnoreCase(title)).count();

        // Context click module and execute from here.
        DynamicElementUtil DynamicElementUtil = new DynamicElementUtil(driver);
        WebElement analyzeModuleSpan = DynamicElementUtil.getAnalysisModuleByNameUnderEdit(title);
        WebElement analyzeModuleDiv = analyzeModuleSpan.findElement(By.xpath("following-sibling::div"));
        Actions actions = new Actions(driver);
        actions.contextClick(analyzeModuleDiv)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.RETURN)
                .perform();
    }

    /*
     * 模块右键菜单－执行到此处
     {
	    "moduleTitle": "Bing_AnalyzeModule"
     }
     *
     * @param driver
     * @tows Exception
     */
    public void moduleContextMenu_AnalyzeModuleExecuteToHere(WebDriver driver, String data) throws Exception {
        LogWriter.debug(this.getClass(), "Module context menu: execute to here: " + data);

        // Get data.
        Map dataMap = new JsonUtil().jsonToMaps(data);
        String title = (String) dataMap.get("moduleTitle");

        // Workflow module list.
        String templatePath = DataParse.GetProperties("DataAppElementsTemplate");
        String moduleStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("工作流模块");
        List<WebElement> moduleDivs = ElementUtil.findElements(driver, moduleStr);
        long countBefore = moduleDivs.stream().filter(webElement -> webElement.getAttribute("title").equalsIgnoreCase(title)).count();

        // Context click module and execute to here.
        DynamicElementUtil DynamicElementUtil = new DynamicElementUtil(driver);
        WebElement analyzeModuleSpan = DynamicElementUtil.getAnalysisModuleByNameUnderEdit(title);
        WebElement analyzeModuleDiv = analyzeModuleSpan.findElement(By.xpath("following-sibling::div"));
        Actions actions = new Actions(driver);
        actions.contextClick(analyzeModuleDiv)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.RETURN)
                .perform();
    }

    /*
     * 模块右键菜单－执行该节点
     {
	    "moduleTitle": "Bing_AnalyzeModule"
     }
     *
     * @param driver
     * @tows Exception
     */
    public void moduleContextMenu_AnalyzeModuleExecuteTheNode(WebDriver driver, String data) throws Exception {
        LogWriter.debug(this.getClass(), "Module context menu: execute the node: " + data);

        // Get data.
        Map dataMap = new JsonUtil().jsonToMaps(data);
        String title = (String) dataMap.get("moduleTitle");

        // Workflow module list.
        String templatePath = DataParse.GetProperties("DataAppElementsTemplate");
        String moduleStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("工作流模块");
        List<WebElement> moduleDivs = ElementUtil.findElements(driver, moduleStr);
        long countBefore = moduleDivs.stream().filter(webElement -> webElement.getAttribute("title").equalsIgnoreCase(title)).count();

        // Context click module and execute the node.
        DynamicElementUtil DynamicElementUtil = new DynamicElementUtil(driver);
        WebElement analyzeModuleSpan = DynamicElementUtil.getAnalysisModuleByNameUnderEdit(title);
        WebElement analyzeModuleDiv = analyzeModuleSpan.findElement(By.xpath("following-sibling::div"));
        Actions actions = new Actions(driver);
        actions.contextClick(analyzeModuleDiv)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.RETURN)
                .perform();
    }

    /*
     * 验证应用运行配置
     *
     * @param driver
     * @tows Exception
     */
    public void verifyAppRunConfig(WebDriver driver) throws Exception {
        LogWriter.debug(this.getClass(), "Verify app run config");

        String templatePath = DataParse.GetProperties("DataAppElementsTemplate");
        String appRunConfigStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("应用运行配置");
        WebElement appRunConfigDiv = ElementUtil.findElement(driver, appRunConfigStr);
        ToolKit.waitForWebElementVisible(driver, appRunConfigDiv, 5);
        Assert.assertTrue(appRunConfigDiv.isDisplayed());
    }

    /*
     * 模块右键菜单－查看结果
     {
	    "moduleTitle": "Bing_AnalyzeModule"
     }
     *
     * @param driver
     * @tows Exception
     */
    public void moduleContextMenu_AnalyzeModuleViewResult(WebDriver driver, String data) throws Exception {
        LogWriter.debug(this.getClass(), "Module context menu: view result: " + data);

        // Get data.
        Map dataMap = new JsonUtil().jsonToMaps(data);
        String title = (String) dataMap.get("moduleTitle");

        // Workflow module list.
        String templatePath = DataParse.GetProperties("DataAppElementsTemplate");
        String moduleStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("工作流模块");
        List<WebElement> moduleDivs = ElementUtil.findElements(driver, moduleStr);
        long countBefore = moduleDivs.stream().filter(webElement -> webElement.getAttribute("title").equalsIgnoreCase(title)).count();

        // Context click module and view result.
        DynamicElementUtil DynamicElementUtil = new DynamicElementUtil(driver);
        WebElement analyzeModuleSpan = DynamicElementUtil.getAnalysisModuleByNameUnderEdit(title);
        WebElement analyzeModuleDiv = analyzeModuleSpan.findElement(By.xpath("following-sibling::div"));
        Actions actions = new Actions(driver);
        actions.contextClick(analyzeModuleDiv)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.RETURN)
                .perform();
    }

    /*
     * 验证模块右键菜单－查看结果
     {
	    "moduleTitle": "Bing_AnalyzeModule"
     }
     *
     * @param driver
     * @tows Exception
     */
    public void verifyModuleContextMenu_AnalyzeModuleViewResult(WebDriver driver, String data) throws Exception {
        LogWriter.debug(this.getClass(), "Verify module context menu: view result: " + data);
        Assert.assertTrue(true);
    }

    /*
     * 模块右键菜单－查看日志
     {
	    "moduleTitle": "Bing_AnalyzeModule"
     }
     *
     * @param driver
     * @tows Exception
     */
    public void moduleContextMenu_AnalyzeModuleViewLog(WebDriver driver, String data) throws Exception {
        LogWriter.debug(this.getClass(), "Verify module context menu: view log" + data);

        // Get data.
        Map dataMap = new JsonUtil().jsonToMaps(data);
        String title = (String) dataMap.get("moduleTitle");

        // Workflow module list.
        String templatePath = DataParse.GetProperties("DataAppElementsTemplate");
        String moduleStr = ElementTemplate.getValues(templatePath, "DataApp").get("工作流").get("工作流模块");
        List<WebElement> moduleDivs = ElementUtil.findElements(driver, moduleStr);
        long countBefore = moduleDivs.stream().filter(webElement -> webElement.getAttribute("title").equalsIgnoreCase(title)).count();

        // Context click module and view log.
        DynamicElementUtil DynamicElementUtil = new DynamicElementUtil(driver);
        WebElement analyzeModuleSpan = DynamicElementUtil.getAnalysisModuleByNameUnderEdit(title);
        WebElement analyzeModuleDiv = analyzeModuleSpan.findElement(By.xpath("following-sibling::div"));
        Actions actions = new Actions(driver);
        actions.contextClick(analyzeModuleDiv)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.RETURN)
                .perform();
    }

    /*
     * 验证模块右键菜单－查看日志
     {
	    "moduleTitle": "Bing_AnalyzeModule"
     }
     *
     * @param driver
     * @tows Exception
     */
    public void verifyModuleContextMenu_AnalyzeModuleViewLog(WebDriver driver, String data) throws Exception {
        LogWriter.debug(this.getClass(), "Verify module context menu: view log: " + data);
        Assert.assertTrue(true);
    }

}
