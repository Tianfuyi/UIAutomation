package com.zetyun.uitest.abstractbusiness;

import com.zetyun.uitest.pageoperation.ModelRepository;
import org.openqa.selenium.WebDriver;

public class ActionModelRepository {

    /*
     * 导入
     {
     "file": "\Upload\RandomForest.zip"
     }
     *
     * @param driver
     * @throws Exception
     */
    public static void importModel(WebDriver driver, String data) throws Exception {
        ModelRepository modelRepository = new ModelRepository();
        modelRepository.importModel(driver, data);
        modelRepository.verifyImportModel(driver);
    }

    /*
     * 新建导入模块
     {
     "name": "-__TIMESTAMP__",
     "description": "中文描述"
     }
     *
     * @param driver
     * @param data
     * @throws Exception
     */
    public static void newImportModel(WebDriver driver, String data) throws Exception {
        ModelRepository modelRepository = new ModelRepository();
        String modelName = modelRepository.newImportModel(driver, data);
        modelRepository.verifyNewImportModel(driver, modelName);
    }

    /*
     * 查看已通过模型
     {}
     *
     * @param driver
     * @throws Exception
     */
    public static void viewPassedModel(WebDriver driver) throws Exception {
        ModelRepository modelRepository = new ModelRepository();
        modelRepository.viewPassedModel(driver);
        modelRepository.verifyViewPassedModel(driver);
    }

    /*
     * 查看待审核模型
     {}
     *
     * @param driver
     * @throws Exception
     */
    public static void viewPendingModel(WebDriver driver) throws Exception {
        ModelRepository modelRepository = new ModelRepository();
        modelRepository.viewPendingModel(driver);
        modelRepository.verifyViewPendingModel(driver);
    }

    /*
     * 查看未通过模型
     {}
     *
     * @param driver
     * @throws Exception
     */
    public static void viewNotPassedModel(WebDriver driver) throws Exception {
        ModelRepository modelRepository = new ModelRepository();
        modelRepository.viewNotPassedModel(driver);
        modelRepository.verifyNotPassedTab(driver);
    }

    /*
     * 移到模型到未通过
     {}
     *
     * @param driver
     * @throws Exception
     */
    public static void moveToNotPassed(WebDriver driver) throws Exception {
        ModelRepository modelRepository = new ModelRepository();
        modelRepository.moveToNotPassed(driver);
    }

    /*
     * 移到模型到通过
     {}
     *
     * @param driver
     * @throws Exception
     */
    public static void moveToPassed(WebDriver driver) throws Exception {
        ModelRepository modelRepository = new ModelRepository();
        modelRepository.moveToPassed(driver);
    }

    /*
     * 查看服务
     {}
     *
     * @param driver
     * @throws Exception
     */
    public static void viewService(WebDriver driver) throws Exception {
        ModelRepository modelRepository = new ModelRepository();
        modelRepository.viewService(driver);
        modelRepository.verifyViewService(driver);
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
     * @param data
     * @throws Exception
     */
    public static void editService(WebDriver driver, String data) throws Exception {
        ModelRepository modelRepository = new ModelRepository();
        String modelName = modelRepository.editService(driver, data);
        modelRepository.verifyEditService(driver, modelName);
    }

    /*
     * 移到模型到未通过（从更多中）
     {}
     *
     * @param driver
     * @throws Exception
     */
    public static void moveToNotPassedFromMore(WebDriver driver) throws Exception {
        ModelRepository modelRepository = new ModelRepository();
        modelRepository.moveToNotPassedFromMore(driver);
    }

    /*
     * 移到模型到通过（从更多中）
     {}
     *
     * @param driver
     * @throws Exception
     */
    public static void moveToPassedFromMore(WebDriver driver) throws Exception {
        ModelRepository modelRepository = new ModelRepository();
        modelRepository.moveToPassedFromMore(driver);
    }

    /*
     * 导出模型
     {}
     *
     * @param driver
     * @throws Exception
     */
    public static void exportModel(WebDriver driver) throws Exception {
        ModelRepository modelRepository = new ModelRepository();
        modelRepository.exportModel(driver);
    }

    /*
     * 导出全部模型
     {}
     *
     * @param driver
     * @throws Exception
     */
    public static void exportAllModels(WebDriver driver) throws Exception {
        ModelRepository modelRepository = new ModelRepository();
        modelRepository.exportAllModels(driver);
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
    public static void searchModelRepository(WebDriver driver, String data) throws Exception {
        ModelRepository modelRepository = new ModelRepository();
        modelRepository.search(driver, data);
    }

    /*
     * 查看API
     {}
     *
     * @param driver
     * @throws Exception
     */
    public static void viewAPI(WebDriver driver) throws Exception {
        ModelRepository modelRepository = new ModelRepository();
        modelRepository.viewAPI(driver);
        modelRepository.verifyViewAPI(driver);
    }

    /*
     * 调试
     {}
     *
     * @param driver
     * @throws Exception
     */
    public static void debugModelRepository(WebDriver driver) throws Exception {
        ModelRepository modelRepository = new ModelRepository();
        modelRepository.debug(driver);
        modelRepository.verifyDebug(driver);
    }

    /*
     * 查看日志
     {}
     *
     * @param driver
     * @throws Exception
     */
    public static void viewLog(WebDriver driver) throws Exception {
        ModelRepository modelRepository = new ModelRepository();
        modelRepository.viewLog(driver);
        modelRepository.verifyViewLog(driver);
    }

    /*
     * 部署
     {
     "minController": "1",
     "maxController": "1",
     "mem": "1",
     "cpu": "1",
     "methods": [
     REST,
     MQ,
     BATCH
     ]
     }
     *
     * @param driver
     * @throws Exception
     */
    public static void deploy(WebDriver driver, String data) throws Exception {
        ModelRepository modelRepository = new ModelRepository();
        modelRepository.deploy(driver, data);
        modelRepository.verifyDeploy(driver);
    }

    /*
     * 上线
     {}
     *
     * @param driver
     * @throws Exception
     */
    public static void online(WebDriver driver) throws Exception {
        ModelRepository modelRepository = new ModelRepository();
        modelRepository.online(driver);
        modelRepository.verifyOnline(driver);
    }

    /*
     * 下线
     {}
     *
     * @param driver
     * @throws Exception
     */
    public static void offline(WebDriver driver) throws Exception {
        ModelRepository modelRepository = new ModelRepository();
        modelRepository.offline(driver);
        modelRepository.verifyOffline(driver);
    }

    /*
     * 取消部署
     {}
     *
     * @param driver
     * @throws Exception
     */
    public static void undeploy(WebDriver driver) throws Exception {
        ModelRepository modelRepository = new ModelRepository();
        modelRepository.undeploy(driver);
        modelRepository.verifyUndeploy(driver);
    }

    /*
     * 设置列
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
    public static void setColumns(WebDriver driver, String data) throws Exception {
        ModelRepository modelRepository = new ModelRepository();
        modelRepository.setColumns(driver, data);
        modelRepository.verifySetColumns(driver, data);
    }

}
