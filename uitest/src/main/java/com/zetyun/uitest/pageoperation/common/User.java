package com.zetyun.uitest.pageoperation.common;

import com.zetyun.data.DataParse;
import com.zetyun.datatemplate.elementtemplate.ElementTemplate;
import com.zetyun.driver.log.LogWriter;
import com.zetyun.uitest.utility.DynamicElementUtil;
import com.zetyun.uitest.utility.ElementUtil;
import com.zetyun.uitest.utility.JsonUtil;
import com.zetyun.uitest.utility.ToolKit;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class User {


    /**
     * 登陆操作
     * @param driver
     * @param data
     *   {"username": "apsadmin@TEST.COM",
     *   "password": "Server2008!"
    }
     */
    public void login(WebDriver driver,String data){
        Map map= new JsonUtil().jsonToMaps(data);
        String  username= (String) map.get("username");
        String  pwd= (String) map.get("password");

        LogWriter.info(User.class," 输入用户名密码登陆 ");
        try {
            String elementSelectorTemplate = DataParse.GetProperties("UIElementSelectorTemplate");
            String filepath =  elementSelectorTemplate;

            String uname = ElementTemplate.getValues(filepath, "User").get("登陆").get("用户名");

            ElementUtil.findElement(driver,uname).clear();
            ElementUtil.findElement(driver,uname).sendKeys(username);

            String password = ElementTemplate.getValues(filepath, "User").get("登陆").get("密码");
            ElementUtil.findElement(driver,password).clear();
            ElementUtil.findElement(driver,password).sendKeys(pwd);

            String submit = ElementTemplate.getValues(filepath, "User").get("登陆").get("登陆按钮");
            ElementUtil.findElement(driver,submit).click();

            String condition = ElementTemplate.getValues(filepath, "Menu").get("菜单").get("展开导航");
            WebElement menu = ElementUtil.findElement(driver, condition);
            menu.click();

        }catch (Exception ex){
            LogWriter.error(User.class,"登陆失败");
        }
    }

    /**   验证登陆成功
     * @param driver
     */
    public void verifyLogin(WebDriver driver,String data){
        Map map= new JsonUtil().jsonToMaps(data);
        String  username= (String) map.get("username");
        LogWriter.info(User.class," 验证登陆成功 ");
        try {
            String elementSelectorTemplate = DataParse.GetProperties("UIElementSelectorTemplate");
            String filepath =  elementSelectorTemplate;
            String loginUname = ElementTemplate.getValues(filepath, "User").get("登陆").get("登陆后用户名");
            String getfromPage=  ElementUtil.findElement(driver,loginUname).getText();

            Assert.assertTrue(getfromPage.contains(username));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * 登出操作
     * @param driver
     */
    public void logout(WebDriver driver) throws Exception{
        LogWriter.debug(User.class,"退出系统");


        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String userEmail = ElementTemplate.getValues(elementSelector, "User").get("退出").get("登录邮箱");
        String logout = ElementTemplate.getValues(elementSelector, "User").get("退出").get("退出");
        //添加滚动，显示上侧导航栏
        ToolKit.setScroll(driver,ElementUtil.findElement(driver,userEmail));
        ElementUtil.findElement(driver,userEmail).click();
        ElementUtil.findElement(driver,logout).click();

    }
    /**
     * 新建角色
     * @param driver
     * @param data
    {
    "createUserRole": {
    "角色名称": "admin",
    "角色描述": "管理员权限",
    "角色权限": "读数据模块,读分析模块"
    }
    }
     */
    public void createUserRole(WebDriver driver, String data) throws Exception{
        LogWriter.debug(User.class,"新建角色");
        Map map= new JsonUtil().jsonToMaps(data);
        String roleName =  map.get("角色名称").toString();
        String desc =  map.get("角色描述").toString();
        String roleModule =  map.get("角色权限").toString();

        String[] strs=null;
        if(!roleModule.contains(",")){
            strs= new String[]{roleModule};
        }else {
            strs = roleModule.split(",");
        }
        List<String> roleModules= Arrays.asList(strs);



        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String newRolebtn = ElementTemplate.getValues(elementSelector, "User").get("角色").get("新建角色");
        String name = ElementTemplate.getValues(elementSelector, "User").get("角色").get("名称");
        String describ = ElementTemplate.getValues(elementSelector, "User").get("角色").get("描述");
        String confirm = ElementTemplate.getValues(elementSelector, "User").get("角色").get("确定");

        ElementUtil.findElement(driver,newRolebtn).click();
        ElementUtil.findElement(driver,name).clear();
        ElementUtil.findElement(driver,name).sendKeys(roleName);
        ElementUtil.findElement(driver,describ).clear();
        ElementUtil.findElement(driver,describ).sendKeys(desc);
        DynamicElementUtil pp = new DynamicElementUtil(driver);
        for(String s :roleModules){
            switch (s){
                case "读数据模块":
                    pp.roleCheckBox("数据模块","").click();
                    break;
                case "读分析模块":
                    pp.roleCheckBox("分析模块","").click();
                    break;
                case "读数据应用":
                    pp.roleCheckBox("数据应用","").click();
                    break;
                case "用户管理":
                    pp.roleCheckBox("用户管理","").click();
                    break;
                case "标签管理":
                    pp.roleCheckBox("标签管理","").click();
                    break;
                case "日志审计":
                    pp.roleCheckBox("日志审计","").click();
                    break;
                case "模型仓库":
                    pp.roleCheckBox("模型仓库","").click();
                    break;
                case "自助分析":
                    pp.roleCheckBox("自助分析","").click();
                    break;
                case "创建数据模块":
                    pp.roleCheckBox("数据模块","创建").click();
                    break;
                case "创建分析模块":
                    pp.roleCheckBox("分析模块","创建").click();
                    break;
                case "创建数据应用":
                    pp.roleCheckBox("数据应用","创建").click();
                    break;

            }

        }
        ElementUtil.findElement(driver,confirm).click();

    }
    /**
     * 编辑角色
     * @param driver
     * @param data
    {
    "roleEdit": {
    "新角色描述": "管理员权限",
    "角色权限": "读数据模块,读分析模块"
    }
    }
     *
     */
    public void roleEdit(WebDriver driver,String data) throws Exception{
        LogWriter.debug(User.class,"编辑角色");

        Map map= new JsonUtil().jsonToMaps(data);
        String newDesc =  map.get("新角色描述").toString();
        String roleModule =  map.get("角色权限").toString();

        String[] strs=null;
        if(!roleModule.contains(",")){
            strs= new String[]{roleModule};
        }else {
            strs = roleModule.split(",");
        }
        List<String> roleModules= Arrays.asList(strs);


        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String roleEdit = ElementTemplate.getValues(elementSelector, "User").get("角色").get("编辑");
        String describ = ElementTemplate.getValues(elementSelector, "User").get("角色").get("描述");
        String confirm = ElementTemplate.getValues(elementSelector, "User").get("角色").get("确定");
        ElementUtil.findElement(driver,roleEdit).click();
        ElementUtil.findElement(driver,describ).clear();
        ElementUtil.findElement(driver,describ).sendKeys(newDesc);

        DynamicElementUtil pp = new DynamicElementUtil(driver);
        for(String s :roleModules){
            switch (s){
                case "读数据模块":
                    pp.roleCheckBox("数据模块","").click();
                case "读分析模块":
                    pp.roleCheckBox("分析模块","").click();
                case "读数据应用":
                    pp.roleCheckBox("数据应用","").click();
                case "用户管理":
                    pp.roleCheckBox("用户管理","").click();
                case "标签管理":
                    pp.roleCheckBox("标签管理","").click();
                case "日志审计":
                    pp.roleCheckBox("日志审计","").click();
                case "模型仓库":
                    pp.roleCheckBox("模型仓库","").click();
                case "自助分析":
                    pp.roleCheckBox("自助分析","").click();
                case "创建数据模块":
                    pp.roleCheckBox("数据模块","创建").click();
                case "创建分析模块":
                    pp.roleCheckBox("分析模块","创建").click();
                case "创建数据应用":
                    pp.roleCheckBox("数据应用","创建").click();
                    break;

            }

        }
        ElementUtil.findElement(driver,confirm).click();

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
    }
    }
     */
    public void createUserGroup(WebDriver driver, String data) throws Exception{
        LogWriter.debug(User.class,"新建用户分组");
        Map map= new JsonUtil().jsonToMaps(data);
        String groupName =  map.get("用户组名").toString();
        String desc =  map.get("描述").toString();
        String usernames =  map.get("用户名").toString();
        String roleNames =  map.get("角色权限").toString();

        String[] strs=null;
        if(!usernames.contains(",")){
            strs= new String[]{usernames};
        }else {
            strs = usernames.split(",");
        }
        List<String> username= Arrays.asList(strs);

        String[] strs1=null;
        if(!roleNames.contains(",")){
            strs= new String[]{roleNames};
        }else {
            strs = roleNames.split(",");
        }
        List<String> roleName= Arrays.asList(strs);


        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String newGroup = ElementTemplate.getValues(elementSelector, "User").get("用户组").get("新建用户组");
        String name = ElementTemplate.getValues(elementSelector, "User").get("用户组").get("名称");
        String describ = ElementTemplate.getValues(elementSelector, "User").get("用户组").get("描述");
        String confirm = ElementTemplate.getValues(elementSelector, "User").get("用户组").get("确定");

        ElementUtil.findElement(driver,newGroup).click();
        ElementUtil.findElement(driver,name).clear();
        ElementUtil.findElement(driver,name).sendKeys(groupName);
        ElementUtil.findElement(driver,describ).clear();
        ElementUtil.findElement(driver,describ).sendKeys(desc);

        DynamicElementUtil pp = new DynamicElementUtil(driver);


        //选择用户
        if(username.size()>0) {
            for (String s : username) {
                pp.roleCheckBox(s, "").click();

            }
        }
        //选择角色
        if (roleName.size()>0) {
            for (String t : roleName) {
                pp.roleCheckBox(t, "").click();

            }
        }

        ElementUtil.findElement(driver,confirm).click();


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
    }
    }
     */
    public void createUser(WebDriver driver, String data) throws Exception{

        LogWriter.debug(User.class,"新建用户");
        Map map= new JsonUtil().jsonToMaps(data);
        String username =  map.get("用户名").toString();
        String emailAdd =  map.get("邮箱").toString();
        String passWdStr =  map.get("密码").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String newUser = ElementTemplate.getValues(elementSelector, "User").get("用户").get("新建用户");
        String name = ElementTemplate.getValues(elementSelector, "User").get("用户").get("名称");
        String email = ElementTemplate.getValues(elementSelector, "User").get("用户").get("邮箱");
        String passwd = ElementTemplate.getValues(elementSelector, "User").get("用户").get("密码");
        String repasswd = ElementTemplate.getValues(elementSelector, "User").get("用户").get("确认密码");
        String confirm = ElementTemplate.getValues(elementSelector, "User").get("用户").get("确定");
        final String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

        ElementUtil.findElement(driver,newUser).click();
        ElementUtil.findElement(driver,name).clear();
        ElementUtil.findElement(driver,name).sendKeys(username);
        if(emailAdd.matches(pattern1)) {
            ElementUtil.findElement(driver, email).clear();
            ElementUtil.findElement(driver, email).sendKeys(emailAdd);
        }else
        {
            LogWriter.error(User.class,"邮箱格式错误");
        }
        ElementUtil.findElement(driver,passwd).clear();
        ElementUtil.findElement(driver,passwd).sendKeys(passWdStr);
        ElementUtil.findElement(driver,repasswd).clear();
        ElementUtil.findElement(driver,repasswd).sendKeys(passWdStr);

        ElementUtil.findElement(driver,confirm).click();

    }


    /**
     * 修改用户邮箱
     * @param driver
     * @param data
    {
    "modifyEmail": {
    "邮箱地址": "wangcaiyun@TEST.COM",
    "头像路径": "/Users/zhangfei/Desktop/cop.png"
    }
    }
     */
    public void modifyEmail(WebDriver driver, String data) throws Exception{
        LogWriter.debug(User.class,"修改用户邮箱");
        Map map= new JsonUtil().jsonToMaps(data);
        String emailAddNew =  map.get("邮箱地址").toString();
        String pngPath =  map.get("头像路径").toString();

        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String newEmail = ElementTemplate.getValues(elementSelector, "User").get("个人资料").get("邮箱");
        String upLoad = ElementTemplate.getValues(elementSelector, "User").get("个人资料").get("点击上传");
        String pngList = ElementTemplate.getValues(elementSelector, "User").get("个人资料").get("图片列表");
        String saveBtn = ElementTemplate.getValues(elementSelector, "User").get("个人资料").get("保存资料");
        //验证邮箱格式
        final String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        if(emailAddNew.matches(pattern1))
        {

            ElementUtil.findElement(driver,newEmail).clear();
            ElementUtil.findElement(driver,newEmail).sendKeys(emailAddNew);
        }
        else
        {
            LogWriter.error(User.class,"邮箱格式错误");
        }
        if(!pngPath.isEmpty()){
            ElementUtil.findElement(driver,upLoad).sendKeys(pngPath);

        }else {
            ElementUtil.findElements(driver,pngList).get(0).click();
        }
        ElementUtil.findElement(driver,saveBtn).click();




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
    public void modifyPasswd(WebDriver driver, String data) throws Exception{
        LogWriter.debug(User.class,"修改用户密码");

        Map map= new JsonUtil().jsonToMaps(data);
        String passwd =  map.get("新密码").toString();


        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String mPasswd = ElementTemplate.getValues(elementSelector, "User").get("个人资料").get("修改密码");
        String newPasswd = ElementTemplate.getValues(elementSelector, "User").get("个人资料").get("新密码");
        String newRePasswd = ElementTemplate.getValues(elementSelector, "User").get("个人资料").get("确认密码");
        String confirm = ElementTemplate.getValues(elementSelector, "User").get("个人资料").get("保存密码");
        String reconfirm = ElementTemplate.getValues(elementSelector, "User").get("个人资料").get("确定");

        ElementUtil.findElement(driver,mPasswd).click();
        ElementUtil.findElement(driver,newPasswd).clear();
        ElementUtil.findElement(driver,newPasswd).sendKeys(passwd);
        ElementUtil.findElement(driver,newRePasswd).clear();
        ElementUtil.findElement(driver,newRePasswd).sendKeys(passwd);
        ElementUtil.findElement(driver,confirm).click();
        ElementUtil.findElement(driver,reconfirm).click();


    }
    /**
     * 查找列表中是否包含待查找用户，用户角色，用户分组
     * @param driver
     * @param data
    {
    "findTable": {
    "待查名称": "wangcaiyun"
    }
    }
     */
    public void findTable(WebDriver driver,String data) throws Exception{
        Map map= new JsonUtil().jsonToMaps(data);
        String nameInfo =  map.get("待查名称").toString();
        boolean finded = false;


        String elementSelector = DataParse.GetProperties("UIElementSelectorTemplate");
        String listFind = ElementTemplate.getValues(elementSelector, "User").get("用户查找").get("列表查找");

        List<WebElement> es = ElementUtil.findElements(driver,listFind);
        for (int i = 0;i<es.size();i++){
            finded = es.get(i).getText().equals(nameInfo);
            if(finded){
                break;
            }
        }
        Assert.assertTrue(finded);
    }

    public static void main(String[] args) throws Exception {
        WebDriver driver = null;

        driver=  InitBrowser.openBroser(driver,"http://192.168.20.194");
         new User().login(driver,"{\"username\":\"apsadmin@TEST.COM\",\"password\":\"Server2008!\"}");
        Menu m = new Menu("分析应用");
        m.selectR(driver);
        m.selectC(driver,"分析模块");
        Search s = new Search();
      //  s.search(driver,"分析模块","标签搜索","数据预处理");
        Creation creation = new Creation();
//        creation.creatAnalysis(driver,"python","TestForAutomationD","registry.aps.datacanvas.com:5000/anaconda_python",
//                "This is a test","可视化","私有");



    }


}
