package com.zetyun.uitest.abstractbusiness;

import com.zetyun.uitest.pageoperation.AutoModel;
import com.zetyun.uitest.pageoperation.AutoModelView;
import com.zetyun.uitest.pageoperation.TaskList;
import com.zetyun.uitest.pageoperation.common.Table;
import com.zetyun.uitest.utility.JsonUtil;
import com.zetyun.uitest.utility.ToolKit;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.Map;

/**
 * 自动建模
 */
public class ActionAutoml {

    /**
     * 自动建模设计 点击设置项
     * @param driver
     * @param data
     * @throws Exception
     *{"name":"特征处理/目标"}
     */
    public static void chooseOption(WebDriver driver, String data) throws Exception {
        AutoModel am = new AutoModel();
        am.chooseOption(driver,data);
    }

    /**
     * 目标设置
     * @param driver
     * @param data
     * @throws Exception
     * {"设置名称":{"设置项":"目标"},
     *   "data":{"预测类型":"多分类",
     * "目标列":"y"}
     * }
     */
    public static void target(WebDriver driver,String data) throws Exception{
        AutoModel am = new AutoModel();
        Map map= new JsonUtil().jsonToMaps(data);
        String  datas = map.get("设置名称").toString();
        am.chooseOption(driver,datas);
        String  datas1 = map.get("data").toString();
        am.target(driver,datas1);
    }

    /**
     * 训练/测试集
     * @param driver
     * @param data
     * @throws Exception
     *      {"设置名称":{"设置项":"训练/测试集"},
     *         "data":{"抽样方法":"选择前N行",
     *         "行数":"10001",
     *          "训练数据集的比例":"0.800",
     *          "随机种子":"1377"
     *                }
     *       }
     */
    public static void trainAndtestSet(WebDriver driver,String data) throws Exception{
        AutoModel am = new AutoModel();
        Map map= new JsonUtil().jsonToMaps(data);
        String  datas = map.get("设置名称").toString();
        am.chooseOption(driver,datas);
        String  datas1 = map.get("data").toString();
        am.trainAndtestSet(driver,datas1);
    }


    /**
     * 特征处理
     * @param driver
     * @param data
     * @throws Exception
     *      {"设置名称":{"设置项":"特征处理"},
     *         "data":{"名称":"job",
     *          "状态":"关"
     *               }
     *       }
     */
    public static void featureProcess(WebDriver driver,String data) throws Exception{
        AutoModel am = new AutoModel();
        Map map= new JsonUtil().jsonToMaps(data);
        String  datas = map.get("设置名称").toString();
        am.chooseOption(driver,datas);
        String  datas1 = map.get("data").toString();
        am.featureProcess(driver,datas1);
    }

    /**
     * 算法
     * @param driver
     * @param data
     * @throws Exception
     *      {"设置名称":{"设置项":"算法"},
     *         "data":{"算法名称":"KNN",
     *                "状态","开"
     *                }
     *       }
     */
    public static void algorithms(WebDriver driver,String data) throws Exception{
        AutoModel am = new AutoModel();
        Map map= new JsonUtil().jsonToMaps(data);
        String  datas = map.get("设置名称").toString();
        am.chooseOption(driver,datas);
        String  datas1 = map.get("data").toString();
        am.algorithms(driver,datas1);
    }

    /**
     * 超参数调优
     * @param driver
     * @param data
     * @throws Exception
    {"设置名称":{"设置项":"超参数调优"},
       "data":{"随机网络搜索":"否"，
    "最大迭代次数":"20",
    "最大搜索时间":"10",
    "并发数":"0",
    "交叉验证":"K-Fold",
    "分割数":"10",
    }
   }
     */
    public static void paramTuning(WebDriver driver,String data) throws Exception{
        AutoModel am = new AutoModel();
        Map map= new JsonUtil().jsonToMaps(data);
        String  datas = map.get("设置名称").toString();
        am.chooseOption(driver,datas);
        String  datas1 = map.get("data").toString();
        am.paramTuning(driver,datas1);
    }

    /**
     * 评估方法
     * @param driver
     * @param data
     * @throws Exception
     *
 {"设置名称":{"设置项":"评估方法"},
  "data":{"评估方法":"Recall"}
 }
     */
    public static void evaluation(WebDriver driver,String data) throws Exception{
        AutoModel am = new AutoModel();
        Map map= new JsonUtil().jsonToMaps(data);
        String  datas = map.get("设置名称").toString();
        am.chooseOption(driver,datas);
        String  datas1 = map.get("data").toString();
        am.evaluation(driver,datas1);
    }

    /**
     * 资源配置
     * @param driver
     * @param data
     * @throws Exception
     *
    {"设置名称":{"设置项":"资源配置"},
    "data":{"特征CPU":"2",
    "特征MEM":"2",
    "算法CPU":"2",
    "算法MEM":"2",
    "评估CPU":"2",
    "评估MEM":"2",
           }
    }
     */
    public static void resourceConfig(WebDriver driver,String data) throws Exception{
        AutoModel am = new AutoModel();
        Map map= new JsonUtil().jsonToMaps(data);
        String  datas = map.get("设置名称").toString();
        am.chooseOption(driver,datas);
        String  datas1 = map.get("data").toString();
        am.resourceConfig(driver,datas1);
    }

    /**
     * 训练
     * @param driver
     * @param data
     * @throws Exception
    {"状态":"运行/已终止/失败/成功",
    "等待时间":"1200"}
     */
    public static void traning(WebDriver driver,String data) throws Exception {
        new Table().clicktableDesign(driver);  // 点击设计
        AutoModel am = new AutoModel();
        am.clickTraining(driver);  // 点击训练
        AutoModelView amv = new AutoModelView();
        amv.getTaskName(driver).get(0).click();
        ToolKit.switchWindows(driver);
        Map map= new JsonUtil().jsonToMaps(data);
        TaskList tl = new TaskList();
        tl.checkTaskStatus(driver,data);

    }

    /**
     * 验证自动建模新建 首次自动训练结果
     * @param driver
     * @param data
     * @throws Exception
     *
      {"状态":"运行/已终止/失败/成功",
      "等待时间":"1200"}
     */
    public static void checkAutoModelCreate(WebDriver driver,String data)throws Exception {
        AutoModelView amv = new AutoModelView();
        amv.getTaskName(driver).get(0).click();
        ToolKit.switchWindows(driver);
        Map map= new JsonUtil().jsonToMaps(data);
        TaskList tl = new TaskList();
        tl.checkTaskStatus(driver,data);
    }


    /**
     *
     * 查看历史纪录
     * （链接能够正确跳转）
     * @param driver
     * @param data
     * @throws Exception
     {"模型名称":""}
     */
    public static void checkHistory(WebDriver driver,String data) throws Exception {
        new Table().clicktableDesign(driver);  // 点击设计
        String  name = new JsonUtil().jsonToMaps(data).get("模型名称").toString();
        AutoModel am = new AutoModel();
        am.clickCheckHistory(driver);// 点击产看历史结果
        Assert.assertTrue(driver.getCurrentUrl().contains("detail"));
        Assert.assertTrue(driver.getCurrentUrl().contains(name));

    }
}
