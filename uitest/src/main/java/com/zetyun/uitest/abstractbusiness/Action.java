package com.zetyun.uitest.abstractbusiness;

import com.zetyun.uitest.delivery.Delivery;
import com.zetyun.uitest.pageoperation.common.*;
import com.zetyun.uitest.utility.JsonUtil;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class Action {

    /**
     * 用户登陆
     * @param driver
     * @param data
     *
     * {
     *  "login": {
     *   "username": "",
     *   "password": ""
     *  }
     * }
     */
    public static void login(WebDriver driver,String data) throws Exception{

        JsonUtil ju = new JsonUtil();
        Map map= ju.jsonToMaps(data);
        User usr= new User();
        String datas=map.get("login").toString();
        usr.login(driver,datas );  // 登陆
        usr.verifyLogin(driver,datas); // 登陆验证
    }

    /**
     * 选择模块
     * @param driver
     * @param data
    {
    "menu": "分析应用",
    "navi": "自动建模",
    }
     */
    public static void selectModule(WebDriver driver,String data) throws Exception{

        Map map= new JsonUtil().jsonToMaps(data);
        String  menu = (String) map.get("menu");
        String   navi= (String) map.get("navi");
        for (Object obj : map.keySet()) {
            System.out.println("key=" + obj + " value=" + map.get(obj));
        }
         Menu m = new Menu(menu);
             m.selectR(driver);  // 选择菜单
             m.selectC(driver,navi);// 选择导航
    }

    /**
     * @param driver
     * @param data
     * @throws Exception
     * {
     * "search":{"模块名称":"自动建模/数据应用/数据模块/分析模块/任务列表/预定计划/快捷入口/消息中心/用户列表/用户分组/用户角色/日志审计/标签管理",
     *  "查询类型":"普通搜索/类型搜索/状态搜索/创建人搜索/标签搜索",
     *  "查询数据","TestForAuto1"
 }
}
     */
    public static String search(WebDriver driver,String data) throws Exception {
        Search se = new Search();
        Map map= new JsonUtil().jsonToMaps(data);
        String datas=map.get("search").toString();
        se.search(driver,datas);
        String result=se.checkSearch(driver,datas);// 校验 返回json
        return result;
    }

    /**
     * @param driver
     * @param data
     * @throws Exception
     *
    {
    “菜单名称":"分析模块",
    "data":{
    "类型":"python",
    "名称":"TestForAutomationDPythonanaconda",
    "镜像":"registry.aps.datacanvas.com:5000/anaconda_python",
    "描述":"This is a test",
    "标签":"",
    "公开类型":"公开"
    }
    }
     *

    {
    “菜单名称":"数据应用",
    "data":{
    "名称":"TestForAutomationDDataApp",
    "描述":"This is a test",
    "标签":"数据-模型-训练",
    "公开类型":""
    }
    }

    {
    “菜单名称":"数据模块",
    "data":{
    "类型":"本地文件",
    "数据模块名称":"TestForAutomationDappModule",
    "描述":"This is a test",
    "标签":"数据用途-训练",
    "公开类型":"",
    "文件类型":"文本文件TXT",
    "列分隔符":"逗号",
    "行分隔符":"换行",
    "编码格式":"UTF-8",
    "文件首行字段名":"开启"
    }
    }
     */
    public static void create(WebDriver driver,String data)throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  modulename = (String) map.get("菜单名称");// 模块名称
        String datas=map.get("data").toString().trim();   // 数据
        Creation cr = new Creation();
        cr.clickCreateBtn(driver,modulename);  // 点击对应模块新建按钮

      switch (modulename){
          case "分析模块":
              cr.creatAnalysis(driver,datas);
              break;

          case "数据应用":
              cr.createDataApp(driver,datas);
              break;

          case "数据模块":
              cr.createDataModule(driver,datas);
              break;

          case "自动建模":
              cr.createDataModuleeAutoml(driver,datas);
              break;
      }

    }

    /**
     * 批量导出
     * @param driver
     * @param data
     * @throws Exception
     */
    public static void batchExport(WebDriver driver,String data) throws Exception{
        Pagelist pl = new Pagelist();
        pl.batchExport(driver,data);
    }

    /**
     * 导出
     * @param driver
     * @param data
     * @throws Exception
     * {"菜单":"分析模块"}
     */
    public static void export(WebDriver driver,String data) throws Exception{
        Pagelist pl = new Pagelist();
        pl.exports(driver,data);
    }

    /** 使用过滤后才能调用该方法
     * 删除
     * @param driver
     * @param data
     * @throws Exception
     */
    public static void delete(WebDriver driver,String data) throws Exception {
        Pagelist pl = new Pagelist();
        pl.delete(driver,data);

    }

    /**
     * 点击全选按钮 点击 批量删除
     * @param driver
     * @throws Exception
     */
    public static void deleteBatch(WebDriver driver) throws Exception{
        Pagelist pl = new Pagelist();
        pl.deleteBatch(driver);
    }

//用户中心操作
    /**
     * 新建角色
     * @param driver
     * @param data
    {
    "createUserRole": {
    "角色名称": "admin",
    "角色描述": "管理员权限",
    "角色权限": "读数据模块,读分析模块"
    },
    "search": {
    "模块名称": "用户角色",
    "查询类型": "普通搜索",
    "查询数据": "admin"
    },
    "findTable": {
    "待查名称": "wangcaiyun"
    }
    }
     */
    public static void createUserRoleA(WebDriver driver,String data)throws Exception{

        Map map= new JsonUtil().jsonToMaps(data);
        String  roleInfo = map.get("createUserRole").toString();
        String  varifyInfo = map.get("search").toString();
        String  tableInfo = map.get("findTable").toString();

        User usr = new User();
        usr.createUserRole(driver,roleInfo);
        Search sr = new Search();
        sr.search(driver,varifyInfo);
        usr.findTable(driver,tableInfo);
    }
    /**
     * 编辑角色
     * @param driver
    {
    "roleEdit": {
    "新角色描述": "管理员权限",
    "角色权限": "读数据模块,读分析模块"
    }
    }
     */
    public static void editUserRoleA(WebDriver driver,String data)throws Exception{

        Map map= new JsonUtil().jsonToMaps(data);
        String  roleInfo = map.get("roleEdit").toString();

        User usr = new User();
        usr.roleEdit(driver,roleInfo);

    }
    /**
     * 新建用户分组
     * @param driver
     * @param data
    {
    "createUserGroup": {
    "用户组名": "管理员权限",
    "描述": "新建用户组",
    "用户名": "wangcaiyun@TEST.COM,wangcaiyun",
    "角色权限": "读数据模块,读分析模块"
    },
    "search": {
    "模块名称": "用户角色",
    "查询类型": "普通搜索",
    "查询数据": "admin"
    },
    "findTable": {
    "待查名称": "管理组"
    }
    }
     */
    public static void createUserGroupA(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  groupInfo = map.get("createUserGroup").toString();
        String  varifyInfo = map.get("search").toString();
        String  tableInfo = map.get("findTable").toString();
        User usr = new User();
        Search sr = new Search();
        usr.createUserGroup(driver,groupInfo);
        sr.search(driver,varifyInfo);
        usr.findTable(driver,tableInfo);

    }
    /**
     * 新建用户
     * @param driver
     * @param data
    {
    "createUser": {
    "用户名": "wangcaiyun@TEST.COM",
    "邮箱": "123@123.com",
    "密码": "123456"
    },
    "search": {
    "模块名称": "用户角色",
    "查询类型": "普通搜索",
    "查询数据": "admin"
    },
    "findTable": {
    "待查名称": "wangcaiyun"
    }
    }
     */
    public static void createUserA(WebDriver driver,String data)throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  userInfo = map.get("createUser").toString();
        String  varifyInfo = map.get("search").toString();
        String  tableInfo = map.get("findTable").toString();
        User usr = new User();
        Search sr = new Search();
        usr.createUser(driver,userInfo);
        sr.search(driver,varifyInfo);
        usr.findTable(driver,tableInfo);
    }
    /**
     * 修改用户邮箱
     * @param driver
     * @param data
    {
    "modifyEmail": {
    "邮箱地址": "wangcaiyun@TEST.COM",
    "头像路径": "/../cop.png"
    }
    }
     */
    public static void modifyEmailA(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  emailInfo = map.get("modifyEmail").toString();
        User usr = new User();
        usr.modifyEmail(driver,emailInfo);
    }
    /**
     * 修改用户密码
     * @param driver
     * @param data
    {
    "modifyPasswd": {
    "新密码": "1234567"
    }
    }
     */
    public static void modifyPasswdA(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String  passwdInfo = map.get("modifyPasswd").toString();
        User usr = new User();
        usr.modifyPasswd(driver,passwdInfo);
    }
    /**
     * 登出操作
     * @param driver
     */
    public static void logoutA(WebDriver driver)throws Exception{
        User usr = new User();
        usr.logout(driver);
    }

    /**
     * 模块详情
     * @param driver
     * @param data
     * @throws Exception
    {"verifmoduledeatail":{"modulename":"TestforAutomation1"}}
     */
    public static void verifmoduledeatail(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String datas= map.get("verifmoduledeatail").toString();
        Pagelist pl = new Pagelist();
        pl.verifmoduledeatail(driver,datas);
    }

    /**
     * 历史纪录
     * @param driver
     * @param data
     * @throws Exception
    {
    "verifHistoryRecord": {
    "modulename": "AutoML_HyperparamsCV"
    },
    "checkItemswithText": {
    "showtext": "AutoML_HyperparamsCV#2.5",
    "getItems": {
    "导航名称": "分析模块"
    }
    }
    }
     */
    public static void verifHistoryRecord(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String datas=map.get("verifHistoryRecord").toString();
        String datas1=map.get("checkItemswithText").toString();
        Pagelist pl = new Pagelist();
        pl.verifHistoryRecord(driver,datas);

        pl.checkItemswithText(driver,datas1);
    }

    /**
     * 引用纪录
     * @param driver
     * @param data
     * @throws Exception
    {
    "verifyCitingRecord": {
    "modulename": "AutoML_HyperparamsCV"
    },
    "checkItemswithText": {
    "showtext": "0.1#lumk_test1736#1.2",
    "getItems": {
    "导航名称": "分析模块"
    }
    }
    }
     */
    public static void verifyCitingRecord(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String datas=map.get("verifyCitingRecord").toString();
        String datas1=map.get("checkItemswithText").toString();
        Pagelist pl = new Pagelist();
        pl.verifyCitingRecord(driver,datas);
        pl.checkItemswithText(driver,datas1);
    }

    /**
     * 点击列表右侧操作符：删除
     * @param driver
     */
    public static void clicktableDelA(WebDriver driver) throws Exception{
        Table tb = new Table();
        tb.clicktableDel(driver);

    }
    /**
     * 点击列表右侧操作符：工作流
     * @param driver
     */
    public static void clicktableWorkFlowA(WebDriver driver) throws Exception{
        Table tb = new Table();
        tb.clicktableWorkFlow(driver);

    }
    /**
     * 点击列表右侧操作符：复制
     * @param driver
     * @param data
    {
    "clicktableCopy": {
    "模块名称": "数据模块"
    }
    }
     */
    public static void clicktableCopyA(WebDriver driver,String data) throws Exception{

        Table tb = new Table();
        tb.clicktableCopy(driver,data);

    }

    /**
     * 点击列表右侧操作符：工作流，复制，删除，编辑，授权，运行,查看
     * @param driver
     * @param data
    {
    "clickTableEdit": {
    "模块名称": "数据模块",
    "新描述": "更新描述"
    }
    }
     */
    public static void clickTableEditA(WebDriver driver,String data) throws Exception{
        Table tb = new Table();
        tb.clickTableEdit(driver,data);
        tb.checkDesc(driver,data);

    }
    /**
     * 点击列表右侧操作符：授权
     * @param driver
     * @param data
    {
    "addTableAuth": {
    "用户名": "abc@TEST.COM",
    "授权": "读取"
    }
    }
     */
    public static void addTableAuthA(WebDriver driver,String data) throws Exception{
        Table tb = new Table();
        tb.addTableAuth(driver,data);
    }

    /**
     * @param driver webdriver
     * @param data
    {
    "delAuthApp": {
    "删除授权": "读取"
    }
    }
     * @throws Exception
     * 删除授权
     */
    public static void delAuthAppA(WebDriver driver, String data) throws Exception {
        Table tb = new Table();
        tb.delAuthApp(driver,data);
    }
    /**
     * 点击列表右侧操作符：运行
     * @param driver
     */
    public static void clicktableRunA(WebDriver driver) throws Exception{
        Table tb = new Table();
        tb.clicktableRun(driver);

    }
    /**
     * 点击列表右侧操作符：编码
     * @param driver
     */
    public static void clicktableCodeA(WebDriver driver) throws Exception{
        Table tb = new Table();
        tb.clicktableCode(driver);
    }
    /**
     * 点击列表右侧操作符：查看
     * @param driver
     * @param data 模块名称
    {
    "clicktableView": {
    "模块类型": "数据模块"
    }
    }
     */
    public static void clicktableViewA(WebDriver driver,String data) throws Exception{
        Table tb = new Table();
        tb.clicktableView(driver,data);

    }

    /**
     * 点击列表右侧操作符：导出
     * @param driver
     */
    public static void clicktableExportA(WebDriver driver) throws Exception{
        Table tb = new Table();
        tb.clicktableExport(driver);
    }
    /**
     * 点击列表右侧操作符：暂停
     * @param driver
     */
    public static void clicktableStopA(WebDriver driver) throws Exception{
        Table tb = new Table();
        tb.clicktableStop(driver);

    }
    /**
     * 点击列表右侧操作符：设计
     * @param driver
     */
    public static void clicktableDesignA(WebDriver driver) throws Exception{
        Table tb = new Table();
        tb.clicktableDesign(driver);

    }
    /**
     * 点击列表右侧操作符：编辑用户组
     * @param driver
     */
    public static void clicktableEditUserA(WebDriver driver) throws Exception{
        Table tb = new Table();
        tb.clicktableEditUser(driver);

    }
    /**
     * 点击列表右侧操作符：编辑用户资源
     * @param driver
     */
    public static void clicktableEditResA(WebDriver driver) throws Exception{
        Table tb = new Table();
        tb.clicktableEditRes(driver);
    }


    /**
     * @param driver webdriver
     * @param data
    {
    "checkAuth": {
    "验证权限": "编辑"
    }
    }
     * @throws Exception
     * 验证模块是否有编辑,删除，运行权限
     */
    public static void checkAuthA(WebDriver driver, String data) throws Exception{
        Table tb = new Table();
        Assert.assertTrue(tb.checkAuth(driver,data));

    }
    /**
     * @param driver webdriver
     * @param data
    {
    "addAppComA": {
    "addAppCom": {
    "评论内容": "这是一条评论"
    },
    "checkAppCom": {
    "评论内容": "这是一条评论"
    }
    }
    }
     * @throws Exception
     * 对模块添加评论 （已验证）
     */
    public static void addAppComA(WebDriver driver,String data) throws Exception {
        Map map= new JsonUtil().jsonToMaps(data);
        String  comments =  map.get("addAppCom").toString();
        String  varifyComments =  map.get("checkAppCom").toString();

        Table tb = new Table();
        tb.addAppCom(driver,comments);
        Assert.assertTrue(tb.checkAppCom(driver,varifyComments));

    }


    /**
     * @param driver webdriver
     * @throws Exception
     * 验证列表中的行数和统计个数是否正确（已验证）
     */
    public static void checkTableCountA(WebDriver driver) throws Exception{
        Table tb = new Table();
        Assert.assertTrue(tb.checkTableCount(driver));
    }

    /**
     * 点击列表中模块名称
     * @param driver
     * @param data 模块名称
    {
    "clickModuleName": {
    "模块名称": "数据模块"
    }
    }
     */
    public static void clickModuleName(WebDriver driver, String data) {
        Table tb = new Table();
        tb.clickModuleName(driver,data);
    }
    /**
     * @param driver webdriver
     * @param data 排序方式
    {
    "sortByDesc": {
    "排序方式": "模块名称"
    }
    }
     * @throws Exception
     * 列表排序 倒序
     */

    public static void sortByDescA(WebDriver driver,String data) throws Exception{
        Table tb = new Table();
        tb.sortByDesc(driver,data);
    }
    /**
     * @param driver webdriver
     * @param data 排序方式
    {
    "sortByDesc": {
    "排序方式": "模块名称"
    }
    }
     * @throws Exception
     * 列表排序 正序
     */
    public static void sortByAscA(WebDriver driver,String data) throws Exception{
        Table tb = new Table();
        tb.sortByAsc(driver,data);
    }
    /**
     * @param driver webdriver
     * @param data 模块名称

     {
    "模块名称": "TEST2018"
    }

     * @throws Exception
     * 查找列表中模块的名字，不包含指定字符串即为错误
     */
    public static void checkModuleNameA(WebDriver driver,String data)throws Exception{

        Table tb = new Table();
        tb.checkModuleName(driver,data);

    }
    /**
     * @param driver webdriver
     * @param data 创建人
    {
    "checkModuleCreator": {
    "创建人": "wangcaiyun"
    }
    }
     * @throws Exception
     * 查找列表中模块的名字，不包含指定字符串即为错误
     */
    public static void checkModuleCreatorA(WebDriver driver,String data) throws Exception{
        Table tb = new Table();
        tb.checkModuleCreator(driver,data);

    }
    public static void deleteAll(WebDriver driver,String data)throws Exception{

    }
    /**
     * @param driver webdriver
     * @param data 发布状态
    {
    "checkModuleStatus": {
    "发布状态": "未发布"
    }
    }
     * @throws Exception
     * 查找列表中模块的类型，和指定的不匹配即为错误
     */
    public static void checkModuleStatusA(WebDriver driver,String data) throws Exception{
        Table tb = new Table();
        tb.checkModuleStatus(driver,data);

    }
    /**
     * @param driver webdriver
     * @param data 创建类型
    {
    "checkModuleType": {
    "类型": "python"
    }
    }
     * @throws Exception
     * 查找列表中模块的类型，和指定的不匹配即为错误
     */
    public static void checkModuleTypeA(WebDriver driver,String data) throws Exception{
        Table tb = new Table();
        tb.checkModuleType(driver,data);

    }




}
