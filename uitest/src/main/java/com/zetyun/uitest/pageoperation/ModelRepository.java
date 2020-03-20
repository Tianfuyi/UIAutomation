package com.zetyun.uitest.pageoperation;

import com.zetyun.data.DataParse;
import com.zetyun.datatemplate.elementtemplate.ElementTemplate;
import com.zetyun.uitest.utility.*;
import com.zetyun.uitest.utility.ElementUtil;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

public class ModelRepository {

    /*
     * 移动模型到未通过
     {}
     *
     * @param driver
     * @throws Exception
     */
    public static void moveToNotPassed(WebDriver driver) throws Exception {

        // Get passed model count
        int passedCount = getPassedModelCount(driver);

        // Get not passed model count
        int notPassedCount = getNotPassedModelCount(driver);

        // Select model checkbox
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String checkBoxStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("模型仓库").get("选择服务复选框");
        WebElement checkBox = ElementUtil.findElement(driver, checkBoxStr);
        checkBox.click();

        // Move to not passed
        String buttonStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("模型仓库").get("移到未通过按钮");
        WebElement button = ElementUtil.findElement(driver, buttonStr);
        ToolKit.waitForWebElementVisible(driver, button, 10);
        button.click();

        // Get passed model count
        int currentPassedCount = getPassedModelCount(driver);

        // Get not passed model count
        int currentNotPassedCount = getNotPassedModelCount(driver);

        // Verify move to not passed
        Assert.assertEquals(passedCount - 1, currentPassedCount);
        Assert.assertEquals(notPassedCount + 1, currentNotPassedCount);
    }

    /*
     * 移动模型到通过
     {}
     *
     * @param driver
     * @throws Exception
     */
    public static void moveToPassed(WebDriver driver) throws Exception {

        // Get passed model count
        int passedCount = getPassedModelCount(driver);

        // Get not passed model count
        int notPassedCount = getNotPassedModelCount(driver);

        // Select model checkbox
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String checkBoxStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("模型仓库").get("选择服务复选框");
        WebElement checkBox = ElementUtil.findElement(driver, checkBoxStr);
        checkBox.click();

        // Move to not passed
        String buttonStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("模型仓库").get("移到通过按钮");
        WebElement button = ElementUtil.findElement(driver, buttonStr);
        ToolKit.waitForWebElementVisible(driver, button, 10);
        button.click();

        // Get passed model count
        int currentPassedCount = getPassedModelCount(driver);

        // Get not passed model count
        int currentNotPassedCount = getNotPassedModelCount(driver);

        // Verify move to not passed
        Assert.assertEquals(passedCount + 1, currentPassedCount);
        Assert.assertEquals(notPassedCount - 1, currentNotPassedCount);
    }

    /*
     * 查看服务
     {}
     *
     * @param driver
     * @throws Exception
     */
    public static void viewService(WebDriver driver) throws Exception {

        // View link
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String viewStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("模型仓库").get("查看");
        WebElement viewLink = ElementUtil.findElement(driver, viewStr);
        viewLink.click();
    }

    /*
     * 编缉服务
     {
     "name": "编缉",
     "description": "中文描述",
     "tag": "多分类"
     }
     *
     * @param driver
     * @throws Exception
     */
    public static String editService(WebDriver driver, String data) throws Exception {
        more(driver);

        // Get data
        Map dataMap = new JsonUtil().jsonToMaps(data);
        String name = (String) dataMap.get("name");
        String description = (String) dataMap.get("description");
        String tag = (String) dataMap.get("tag");

        // Edit link
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String editStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("模型仓库").get("编缉");
        WebElement editLink = ElementUtil.findElement(driver, editStr);
        editLink.click();

        // Name
        String nameStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("编缉模型").get("编缉模型－名称");
        WebElement nameInput = ElementUtil.findElement(driver, nameStr);
        nameInput.sendKeys(name);

        // Description
        String descriptionStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("编缉模型").get("编缉模型－描述");
        WebElement descriptionInput = ElementUtil.findElement(driver, descriptionStr);
        descriptionInput.sendKeys(description);

        // Tag
        String addTagStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("编缉模型").get("编缉模型－添加标签");
        WebElement addTagDiv = ElementUtil.findElement(driver, addTagStr);
        addTagDiv.click();

        // Add tag
        String tagStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("编缉模型").get("编缉模型－标签");
        List<WebElement> tags = ElementUtil.findElements(driver, tagStr);
        for (WebElement element : tags) {
            if (element.getAttribute("innerText").contains(tag)) {
                element.click();
            }
        }
        name = nameInput.getAttribute("value");

        // Submit.
        String submitButton = ElementTemplate.getValues(templatePath, "ModelRepository").get("编缉模型").get("编缉模型－提交按钮");
        ElementUtil.findElement(driver, submitButton).click();

        return name;
    }

    /*
     * 移到未通过（从更多中）
     {}
     *
     * @param driver
     * @return
     * @throws Exception
     */
    public static void moveToNotPassedFromMore(WebDriver driver) throws Exception {

        // Get passed model count
        int passedCount = getPassedModelCount(driver);

        // Get not passed model count
        int notPassedCount = getNotPassedModelCount(driver);

        more(driver);

        // Move to not passed
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String notPassedStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("模型仓库").get("移到未通过菜单");
        WebElement moveToNotPassedLink = ElementUtil.findElement(driver, notPassedStr);
        moveToNotPassedLink.click();

        // Get passed model count
        int currentPassedCount = getPassedModelCount(driver);

        // Get not passed model count
        int currentNotPassedCount = getNotPassedModelCount(driver);

        // Verify move to not passed
        Assert.assertEquals(passedCount - 1, currentPassedCount);
        Assert.assertEquals(notPassedCount + 1, currentNotPassedCount);
    }

    /*
     * 移到通过（从更多中）
     {}
     *
     * @param driver
     * @return
     * @throws Exception
     */
    public static void moveToPassedFromMore(WebDriver driver) throws Exception {

        // Get passed model count
        int passedCount = getPassedModelCount(driver);

        // Get not passed model count
        int notPassedCount = getNotPassedModelCount(driver);

        more(driver);

        // Move to passed
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String passedStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("模型仓库").get("移到通过菜单");
        WebElement moveToPassedLink = ElementUtil.findElement(driver, passedStr);
        moveToPassedLink.click();

        // Get passed model count
        int currentPassedCount = getPassedModelCount(driver);

        // Get not passed model count
        int currentNotPassedCount = getNotPassedModelCount(driver);

        // Verify move to not passed
        Assert.assertEquals(passedCount + 1, currentPassedCount);
        Assert.assertEquals(notPassedCount - 1, currentNotPassedCount);
    }

    /**
     * Verify not passed tab.
     *
     * @param driver
     * @throws Exception
     */
    public static void verifyNotPassedTab(WebDriver driver) throws Exception {
        Assert.assertTrue(driver.getCurrentUrl().endsWith("notpassmodel"));
    }

    /**
     * Get passed model count.
     *
     * @param driver
     * @return
     * @throws Exception
     */
    public static int getPassedModelCount(WebDriver driver) throws Exception {

        // Passed model count.
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String passedModel = ElementTemplate.getValues(templatePath, "ModelRepository").get("模型仓库").get("已通过模型个数");
        WebElement model = ElementUtil.findElement(driver, passedModel);
        String str = model.getText();
        return Integer.parseInt(str.substring(str.lastIndexOf("(") + 1, str.lastIndexOf(")")));
    }

    /**
     * Get pending model count.
     *
     * @param driver
     * @return
     * @throws Exception
     */
    public static int getPendingModelCount(WebDriver driver) throws Exception {

        // Pending model count.
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String passedTab = ElementTemplate.getValues(templatePath, "ModelRepository").get("模型仓库").get("待审核模型个数");
        String str = ElementUtil.findElement(driver, passedTab).getText();
        return Integer.parseInt(str.substring(str.lastIndexOf("(") + 1, str.lastIndexOf(")")));
    }

    /**
     * Get not passed model count.
     *
     * @param driver
     * @return
     * @throws Exception
     */
    public static int getNotPassedModelCount(WebDriver driver) throws Exception {

        // Pending model count.
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String passedTab = ElementTemplate.getValues(templatePath, "ModelRepository").get("模型仓库").get("未通过模型个数");
        String str = ElementUtil.findElement(driver, passedTab).getText();
        return Integer.parseInt(str.substring(str.lastIndexOf("(") + 1, str.lastIndexOf(")")));
    }

    /**
     * Verify view service.
     *
     * @param driver
     * @throws Exception
     */
    public static void verifyViewService(WebDriver driver) throws Exception {
        Assert.assertTrue(driver.getCurrentUrl().contains("/result/"));
    }

    /*
     * 更多
     {}
     *
     * @param driver
     * @throws Exception
     */
    public static void more(WebDriver driver) throws Exception {

        // More link
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String viewStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("模型仓库").get("更多");
        WebElement viewLink = ElementUtil.findElements(driver, viewStr).get(0);
        viewLink.click();
    }

    /**
     * Verify importModel successful.
     *
     * @param driver
     * @throws Exception
     */
    public void verifyImportModel(WebDriver driver) throws Exception {

        // Verify input name exists.
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String nameInput = ElementTemplate.getValues(templatePath, "ModelRepository").get("新建导入模块").get("名称");
        ToolKit.waitForWebElementVisible(driver, ElementUtil.findElement(driver, nameInput), 180);
        Assert.assertTrue(ElementUtil.findElement(driver, nameInput).isDisplayed());
    }

    /**
     * Verify model name exists.
     *
     * @param driver
     * @param modelName
     * @throws Exception
     */
    public void verifyNewImportModel(WebDriver driver, String modelName) throws Exception {

        // Model table.
        // Input name.
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String modelTableDefinition = ElementTemplate.getValues(templatePath, "ModelRepository").get("模型仓库").get("模型表");
        WebElement modelTable = ElementUtil.findElement(driver, modelTableDefinition);
        Assert.assertTrue(modelTable.getText().contains(modelName));
    }

    /**
     * Verify passed model tab.
     *
     * @param driver
     * @throws Exception
     */
    public void verifyViewPassedModel(WebDriver driver) throws Exception {
        Assert.assertTrue(driver.getCurrentUrl().endsWith("passedmodel") || driver.getCurrentUrl().endsWith("list"));
    }

    /**
     * Verify pending model tab.
     *
     * @param driver
     * @throws Exception
     */
    public void verifyViewPendingModel(WebDriver driver) throws Exception {
        Assert.assertTrue(driver.getCurrentUrl().endsWith("toauditmodel"));
    }

    /*
     * 点击导入按钮
     {}
     *
     * @param driver
     * @throws Exception
     */
    public void clickImportButton(WebDriver driver) throws Exception {
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String importButton = ElementTemplate.getValues(templatePath, "ModelRepository").get("模型仓库").get("导入");
        ElementUtil.findElement(driver, importButton).click();
    }

    /*
     * 导入模型
     {
     "file": "\Upload\RandomForest.zip"
     }
     *
     * @param driver
     * @param data
     */
    public void importModel(WebDriver driver, String data) {
        Map dataMap = new JsonUtil().jsonToMaps(data);
        String filePath = (String) dataMap.get("file");
        String userDir = System.getProperty("user.dir");
        if (!userDir.contains("uitest")) {
            userDir = userDir + "\\uitest";
        }
        String file = userDir + filePath;

        // Import model.
        DynamicElementUtil DynamicElementUtil = new DynamicElementUtil(driver);
        DynamicElementUtil.uploadFile().sendKeys(file);
    }

    /*
     * 新建导入模型
     {
     "name": "回归1521-__TIMESTAMP__",
     "description": "中文描述"
     }
     *
     * @param driver
     * @param data
     * @throws Exception
     */
    public String newImportModel(WebDriver driver, String data) throws Exception {
        Map dataMap = new JsonUtil().jsonToMaps(data);
        String name = (String) dataMap.get("name");
        name = DateUtil.replaceTimestamp(name);
        String description = (String) dataMap.get("description");

        // Input name.
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String nameInput = ElementTemplate.getValues(templatePath, "ModelRepository").get("新建导入模块").get("名称");
        WebElement nameInputElement = ElementUtil.findElement(driver, nameInput);
        nameInputElement.sendKeys(name);

        // Input description.
        String descriptionTextArea = ElementTemplate.getValues(templatePath, "ModelRepository").get("新建导入模块").get("描述");
        ElementUtil.findElement(driver, descriptionTextArea).sendKeys(description);
        name = nameInputElement.getAttribute("value");

        // Submit.
        String submitButton = ElementTemplate.getValues(templatePath, "ModelRepository").get("新建导入模块").get("提交按钮");
        ElementUtil.findElement(driver, submitButton).click();

        return name;
    }

    /*
     * 查看已通过模型
     {}
     *
     * @param driver
     * @throws Exception
     */
    public void viewPassedModel(WebDriver driver) throws Exception {

        // Passed model tab.
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String passedTab = ElementTemplate.getValues(templatePath, "ModelRepository").get("模型仓库").get("已通过模型");
        ElementUtil.findElement(driver, passedTab).click();
    }

    /**
     * Verify model name exists.
     *
     * @param driver
     * @param modelName
     * @throws Exception
     */
    public void verifyEditService(WebDriver driver, String modelName) throws Exception {

        // Model table.
        // Input name.
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String modelTableDefinition = ElementTemplate.getValues(templatePath, "ModelRepository").get("模型仓库").get("模型表");
        WebElement modelTable = ElementUtil.findElement(driver, modelTableDefinition);
        Assert.assertTrue(modelTable.getText().contains(modelName));
    }

    /*
     * 查看待审核模型
     {}
     *
     * @param driver
     * @throws Exception
     */
    public void viewPendingModel(WebDriver driver) throws Exception {

        // Pending model tab.
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String passedTab = ElementTemplate.getValues(templatePath, "ModelRepository").get("模型仓库").get("待审核模型");
        ElementUtil.findElement(driver, passedTab).click();
    }

    /*
     * 查看未通过模型
     {}
     *
     * @param driver
     * @throws Exception
     */
    public void viewNotPassedModel(WebDriver driver) throws Exception {

        // Not passed model tab.
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String passedTab = ElementTemplate.getValues(templatePath, "ModelRepository").get("模型仓库").get("未通过模型");
        ElementUtil.findElement(driver, passedTab).click();
    }

    /*
     * 导出模型
     {}
     *
     * @param driver
     * @throws IOException
     * @throws InterruptedException
     */
    public void exportModel(WebDriver driver) throws Exception {

        // Model name list
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String modelNameStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("模型名称");
        List<WebElement> modelNameList = ElementUtil.findElements(driver, modelNameStr);
        String downloadDir = DataParse.GetProperties("DataModuleDownloaddfile");

        clickCheckbox(driver);

        // Export button
        String exportStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("导出");
        WebElement exportButton = ElementUtil.findElement(driver, exportStr);
        exportButton.click();

        // Verify model export successfully.
        for (WebElement element : modelNameList) {
            String name = element.getText() + ".zip";
            String file = ToolKit.getFileFullName(downloadDir, name);
            Assert.assertTrue(!file.equals(""));
        }
    }

    /*
     * 导出全部模型
     {}
     *
     * @param driver
     * @throws Exception
     */
    public void exportAllModels(WebDriver driver) throws Exception {

        // Model name list
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String modelNameStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("模型名称");
        List<WebElement> modelNameList = ElementUtil.findElements(driver, modelNameStr);
        String downloadDir = DataParse.GetProperties("DataModuleDownloaddfile");

        clickAllCheckboxes(driver);

        // Export button
        String exportStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("导出");
        WebElement exportButton = ElementUtil.findElement(driver, exportStr);
        exportButton.click();

        // Verify model export successfully.
        ToolKit.wait(10);
        for (WebElement element : modelNameList) {
            String name = element.getText() + ".zip";
            String file = ToolKit.getFileFullName(downloadDir, name);
            Assert.assertTrue(!file.equals(""));
        }
    }

    /*
     * 点击复选框
     {}
     *
     * @param driver
     * @throws Exception
     */
    public void clickCheckbox(WebDriver driver) throws Exception {

        // Check model checkbox
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String checkBoxStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("选择模型复选框");
        WebElement checkBox = ElementUtil.findElement(driver, checkBoxStr);
        checkBox.click();
    }

    /*
     * 点击全选复选框
     {}
     *
     * @param driver
     * @throws Exception
     */
    public void clickAllCheckboxes(WebDriver driver) throws Exception {

        // Check all model checkbox
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String checkBoxStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("全选复选框");
        WebElement checkBox = ElementUtil.findElement(driver, checkBoxStr);
        checkBox.click();
    }

    /*
     * 搜索
     {
     "searchedContent": "test0623_1340_duofenlei"
     }
     *
     * @param driver
     * @throws Exception
     */
    public void search(WebDriver driver, String data) throws Exception {

        // Get data
        Map dataMap = new JsonUtil().jsonToMaps(data);
        String s = (String) dataMap.get("searchedContent");

        // Search input.
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String searchInputStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("模型仓库").get("普通搜索");
        WebElement searchInput = ElementUtil.findElement(driver, searchInputStr);
        searchInput.sendKeys(s);
    }

    /*
     * 查看API
     {}
     *
     * @param driver
     * @throws Exception
     */
    public void viewAPI(WebDriver driver) throws Exception {

        // API button.
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String apiStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("查看API");
        WebElement apiButton = ElementUtil.findElement(driver, apiStr);
        apiButton.click();
    }

    /**
     * 验证查看API
     *
     * @param driver
     * @throws Exception
     */
    public void verifyViewAPI(WebDriver driver) throws Exception {

        // API button.
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String debugStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("调试按钮");
        WebElement debugButton = ElementUtil.findElement(driver, debugStr);
        Assert.assertTrue(debugButton.isDisplayed());
    }

    /*
     * 调试
     {}
     *
     * @param driver
     * @throws Exception
     */
    public void debug(WebDriver driver) throws Exception {

        // Debug button.
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String debugStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("调试按钮");
        WebElement debugButton = ElementUtil.findElement(driver, debugStr);
        debugButton.click();
    }

    /**
     * 验证调试
     *
     * @param driver
     * @throws Exception
     */
    public void verifyDebug(WebDriver driver) throws Exception {

        // Debug result.
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String debugResultStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("调试结果");
        ToolKit.wait(5);
        WebElement debugResultDiv = ElementUtil.findElement(driver, debugResultStr);
        String debugResult = debugResultDiv.getAttribute("innerText");
        Assert.assertTrue(debugResult.contains("\"status\":0"));
    }

    /*
     * 查看日志
     {}
     *
     * @param driver
     * @throws Exception
     */
    public void viewLog(WebDriver driver) throws Exception {
        more(driver);

        // View log.
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String logStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("日志");
        WebElement logLink = ElementUtil.findElement(driver, logStr);
        logLink.click();
    }

    /**
     * 验证查看日志
     *
     * @param driver
     * @throws Exception
     */
    public void verifyViewLog(WebDriver driver) throws Exception {

        // View log dialog.
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String viewLogStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("查看日志对话框");
        WebElement viewLogDialogTitle = ElementUtil.findElement(driver, viewLogStr);
        Assert.assertTrue(viewLogDialogTitle.isDisplayed());
    }

    /*
     * 部署
     {
     "minController": "1",
     "maxController": "1",
     "mem": "1",
     "cpu": "1",
     "methods": [
     "REST",
     "MQ",
     "BATCH"
     ]
     }
     *
     * @param driver
     * @throws Exception
     */
    public void deploy(WebDriver driver, String data) throws Exception {

        // Get data
        Map dataMap = new JsonUtil().jsonToMaps(data);
        String minController = (String) dataMap.get("minController");
        String maxController = (String) dataMap.get("maxController");
        String mem = (String) dataMap.get("mem");
        String cpu = (String) dataMap.get("cpu");
        List<String> methods = (List<String>) dataMap.get("methods");

        more(driver);

        // Deploy link.
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String deployStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("部署");
        WebElement deployLink = ElementUtil.findElement(driver, deployStr);
        deployLink.click();

        // Resources config.
        String minControllerStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("最小容器值");
        WebElement minControllerInput = ElementUtil.findElement(driver, minControllerStr);
        minControllerInput.sendKeys(minController);

        String maxControllerStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("最大容器值");
        WebElement maxControllerInput = ElementUtil.findElement(driver, maxControllerStr);
        maxControllerInput.sendKeys(maxController);

        String memStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("MEM");
        WebElement memInput = ElementUtil.findElement(driver, memStr);
        memInput.sendKeys(mem);

        String cpuStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("CPU");
        WebElement cpuInput = ElementUtil.findElement(driver, cpuStr);
        cpuInput.sendKeys(cpu);

        // Next step.
        String nextStepStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("下一步按钮");
        WebElement nextStepButton = ElementUtil.findElement(driver, nextStepStr);
        nextStepButton.click();

        // Rest checkbox.
        String restStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("同步调用复选框");
        WebElement restCheckbox = ElementUtil.findElement(driver, restStr);
        restCheckbox.click();

        // MQ checkbox.
        String mqStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("异步调用复选框");
        WebElement mqCheckbox = ElementUtil.findElement(driver, mqStr);

        // Batch checkbox.
        String batchStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("批处理复选框");
        WebElement batchCheckbox = ElementUtil.findElement(driver, batchStr);

        // Service methods.
        for (String m : methods) {
            switch (m) {
                case "REST":
                    restCheckbox.click();
                    break;
                case "MQ":
                    mqCheckbox.click();
                    break;
                case "BATCH":
                    batchCheckbox.click();
                    break;
            }
        }

        // Deploy button.
        String deployButtonStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("部署按钮");
        WebElement deployButton = ElementUtil.findElement(driver, deployButtonStr);
        deployButton.click();
    }

    /**
     * 验证部署
     *
     * @param driver
     * @throws Exception
     */
    public void verifyDeploy(WebDriver driver) throws Exception {

        // Verify deploy status.
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String deployCompleteStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("部署完成");
        WebElement deployCompleteSpan = ElementUtil.findElement(driver, deployCompleteStr);
        ToolKit.waitForWebElementVisible(driver, deployCompleteSpan, 30);
        Assert.assertTrue(deployCompleteSpan.isDisplayed());
    }

    /*
     * 上线
     {}
     *
     * @param driver
     * @throws Exception
     */
    public void online(WebDriver driver) throws Exception {
        more(driver);

        // Online link.
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String onlineStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("上线");
        WebElement onlineLink = ElementUtil.findElement(driver, onlineStr);
        onlineLink.click();
    }

    /**
     * 验证上线
     *
     * @param driver
     * @throws Exception
     */
    public void verifyOnline(WebDriver driver) throws Exception {

        // Verify online status.
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String onlineCompleteStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("在线中");
        WebElement onlineCompleteSpan = ElementUtil.findElement(driver, onlineCompleteStr);
        ToolKit.waitForWebElementVisible(driver, onlineCompleteSpan, 30);
        Assert.assertTrue(onlineCompleteSpan.isDisplayed());
    }

    /*
     * 下线
     {}
     *
     * @param driver
     * @throws Exception
     */
    public void offline(WebDriver driver) throws Exception {
        more(driver);

        // Offline link.
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String offlineStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("下线");
        WebElement offlineLink = ElementUtil.findElement(driver, offlineStr);
        offlineLink.click();
    }

    /**
     * 验证下线
     *
     * @param driver
     * @throws Exception
     */
    public void verifyOffline(WebDriver driver) throws Exception {

        // Verify offline status.
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String offlineCompleteStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("部署完成");
        WebElement offlineCompleteSpan = ElementUtil.findElement(driver, offlineCompleteStr);
        ToolKit.waitForWebElementVisible(driver, offlineCompleteSpan, 30);
        Assert.assertTrue(offlineCompleteSpan.isDisplayed());
    }

    /*
     * 取消部署
     {}
     *
     * @param driver
     * @throws Exception
     */
    public void undeploy(WebDriver driver) throws Exception {
        more(driver);

        // Undeploy link.
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String undeployStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("取消部署");
        WebElement undeployLink = ElementUtil.findElement(driver, undeployStr);
        undeployLink.click();
    }

    /**
     * 验证取消部署
     *
     * @param driver
     * @throws Exception
     */
    public void verifyUndeploy(WebDriver driver) throws Exception {

        // Verify offline status.
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String undeployCompleteStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("未部署");
        WebElement undeployCompleteSpan = ElementUtil.findElement(driver, undeployCompleteStr);
        ToolKit.waitForWebElementVisible(driver, undeployCompleteSpan, 30);
        Assert.assertTrue(undeployCompleteSpan.isDisplayed());
    }

    /*
     * 列设置
     {
     "columns": [
     "Accuracy",
     "F1",
     "Fbeta",
     "Log Loss",
     "MAE",
     "EVS",
     "MSE"
     ]
     }
     *
     * @param driver
     * @throws Exception
     */
    public void setColumns(WebDriver driver, String data) throws Exception {

        // Get data
        Map dataMap = new JsonUtil().jsonToMaps(data);
        List<String> columns = (List<String>) dataMap.get("columns");

        // Set columns button.
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String setColumnButtonStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("列设置按钮");
        WebElement setColumnButtonButton = ElementUtil.findElement(driver, setColumnButtonStr);
        setColumnButtonButton.click();

        // Accuracy checkbox.
        String accuracyStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("Accuracy复选框");
        WebElement accuracyCheckbox = ElementUtil.findElement(driver, accuracyStr);
        accuracyCheckbox.click();

        // F1 checkbox.
        String f1Str = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("F1复选框");
        WebElement f1Checkbox = ElementUtil.findElement(driver, f1Str);
        f1Checkbox.click();

        // Fbeta checkbox.
        String fbetaStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("Fbeta复选框");
        WebElement fbetaCheckbox = ElementUtil.findElement(driver, fbetaStr);
        fbetaCheckbox.click();

        // Log loss checkbox.
        String logLossStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("LogLoss复选框");
        WebElement logLossCheckbox = ElementUtil.findElement(driver, logLossStr);
        logLossCheckbox.click();

        // MAE checkbox.
        String maeStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("MAE复选框");
        WebElement maeCheckbox = ElementUtil.findElement(driver, maeStr);
        maeCheckbox.click();

        // EVS checkbox.
        String evsStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("EVS复选框");
        WebElement evsCheckbox = ElementUtil.findElement(driver, evsStr);
        evsCheckbox.click();

        // MSE checkbox.
        String mseStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("MSE复选框");
        WebElement mseCheckbox = ElementUtil.findElement(driver, mseStr);

        // Check the selected columns.
        for (String c : columns) {
            switch (c) {
                case "Accuracy":
                    accuracyCheckbox.click();
                    break;
                case "F1":
                    f1Checkbox.click();
                    break;
                case "Fbeta":
                    fbetaCheckbox.click();
                    break;
                case "Log Loss":
                    logLossCheckbox.click();
                    break;
                case "MAE":
                    maeCheckbox.click();
                    break;
                case "EVS":
                    evsCheckbox.click();
                    break;
                case "MSE":
                    mseCheckbox.click();
                    break;
            }
        }

        // Submit.
        String submitStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("提交按钮");
        WebElement submitButton = ElementUtil.findElement(driver, submitStr);
        submitButton.click();
    }

    /*
     * 验证列设置
     {
     "columns": [
     "Accuracy",
     "F1",
     "Fbeta",
     "Log Loss",
     "MAE",
     "EVS",
     "MSE"
     ]
     }
     *
     * @param driver
     * @throws Exception
     */
    public void verifySetColumns(WebDriver driver, String data) throws Exception {

        // Get data
        Map dataMap = new JsonUtil().jsonToMaps(data);
        List<String> columns = (List<String>) dataMap.get("columns");

        // Verify columns.
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");

        // Verify the selected columns.
        for (String c : columns) {
            switch (c) {
                case "Accuracy":
                    // Accuracy header.
                    String accuracyStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("Accuracy表头");
                    WebElement accuracySpan = ElementUtil.findElement(driver, accuracyStr);
                    ToolKit.waitForWebElementVisible(driver, accuracySpan, 3);
                    Assert.assertTrue(accuracySpan.isDisplayed());
                    break;
                case "F1":
                    // F1 header.
                    String f1Str = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("F1表头");
                    WebElement f1Span = ElementUtil.findElement(driver, f1Str);
                    ToolKit.waitForWebElementVisible(driver, f1Span, 3);
                    Assert.assertTrue(f1Span.isDisplayed());
                    break;
                case "Fbeta":
                    // Fbeta header.
                    String fbetaStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("Fbeta表头");
                    WebElement fbetaSpan = ElementUtil.findElement(driver, fbetaStr);
                    ToolKit.waitForWebElementVisible(driver, fbetaSpan, 3);
                    Assert.assertTrue(fbetaSpan.isDisplayed());
                    break;
                case "Log Loss":
                    // Log Loss header.
                    String logLossStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("LogLoss表头");
                    WebElement logLossSpan = ElementUtil.findElement(driver, logLossStr);
                    ToolKit.waitForWebElementVisible(driver, logLossSpan, 3);
                    Assert.assertTrue(logLossSpan.isDisplayed());
                    break;
                case "MAE":
                    // MAE header.
                    String maeStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("MAE表头");
                    WebElement maeSpan = ElementUtil.findElement(driver, maeStr);
                    ToolKit.waitForWebElementVisible(driver, maeSpan, 3);
                    Assert.assertTrue(maeSpan.isDisplayed());
                    break;
                case "EVS":
                    // EVS header.
                    String evsStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("EVS表头");
                    WebElement evsSpan = ElementUtil.findElement(driver, evsStr);
                    ToolKit.waitForWebElementVisible(driver, evsSpan, 3);
                    Assert.assertTrue(evsSpan.isDisplayed());
                    break;
                case "MSE":
                    // MSE header.
                    String mseStr = ElementTemplate.getValues(templatePath, "ModelRepository").get("查看结果").get("MSE表头");
                    WebElement mseSpan = ElementUtil.findElement(driver, mseStr);
                    ToolKit.waitForWebElementVisible(driver, mseSpan, 3);
                    Assert.assertTrue(mseSpan.isDisplayed());
                    break;
            }
        }
    }

    /*
     * Verify model table contains model.
     {
     "modelName": "回归1521-1530092744980"
     }
     *
     * @param driver
     * @param data
     * @throws Exception
     */
    public void verifyModelTableContainsModel(WebDriver driver, String data) throws Exception {

        // Get data
        Map dataMap = new JsonUtil().jsonToMaps(data);
        String modelName = (String) dataMap.get("modelName");

        // Model table.
        String templatePath = DataParse.GetProperties("ModelRepositoryElementsTemplate");
        String modelTableDefinition = ElementTemplate.getValues(templatePath, "ModelRepository").get("模型仓库").get("模型表");
        WebElement modelTable = ElementUtil.findElement(driver, modelTableDefinition);
        Assert.assertTrue(modelTable.getText().contains(modelName));
    }

}
