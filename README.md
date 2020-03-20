# java webdriver 关键字驱动自动化测试
###   行业内许多公司认为UI自动化收益较低 不能发现新的问题产出低，但在项目迭代回归场景下的确能大大减少重复验证，且效率高。

###   框架结构


###   目前传统UI自动化框架会遇到两个问题：
       1在项目中维护用例，如遇到场景复杂，测试分支多的情况下，导致用例臃肿，增加维护成本。
       2.专业度相对高 必须会代码才能维护用例
       本测试框架使用关键字驱动的方式，根据实际业务操作在excel中组装测试用例，使用人员只要对关键字使用方式熟悉即可维护原有用例在一定程度上解决以上两个问题 （本框架只是一个思路，后期还有诸多需要完善的地方 ），并且框架平台化有扩展性，方便用例复用。


##   1.项目概述
###   使用目前较为流行的UI自动化测试工具Webdriver
###  语言选择java， Excel 管理页面元素，
###  关键字驱动Excel 管理测试用例 组织测试用例行为
###  测试报告使用ExtentX测试报告的生成,集中管理测试结果 界面更加简洁

##   2.使用说明：
###      2.1 程序主入口配置相关


###      2.2 执行本地执行:run UISomkingTest.java
             Jenkins 执行：jenkins 配置maven 命令test 执行 
###      2.3 配置文件：
           Resource/framework.properties
#基础配置文件相对路径
UIBasicConfigPath=/Template/BasicConfigTemplate.xlsx

#Element模板相对路径
UIElementSelectorTemplate=/Template/ElementSelectorTemplate.xlsx
ModelRepositoryElementsTemplate=/Template/ElementSelectorTemplate.xlsx
DataAppElementsTemplate=/Template/DataAppElementsTemplate.xlsx

#场景驱动模板相对路径
UISceneDataTemplate=/Template/UIScenceDataTemplate.xlsx
ModelRepositoryDataTemplate=/Template/ModelRepositoryDataTemplate.xlsx
DataAppDataTemplate=/Template/DataAppDataTemplate.xlsx

#测试默认上传文件路径
TestUploadPath=/Template/testupload.txt

#上传文件路径
DataModuleUploadfile=/Template/DatamoduleUpload.txt

#上传文件夹
DataModuleUploadpath=/Template/

#下载文件路径
DataModuleDownloaddfile=/Download
#分析模块中代码编辑文件路径
CodeFilePathPython = /Template/filesForAnalysis/testFileForPython.txt
CodeFilePathR = /Template/filesForAnalysis/testFileForR.txt
#安装路径
PackagePath =/Template/PackagePath
###      2.4 业务封装：
Abstractbusiness文件下
###      2.5 子业务封装：
Pageopreation 文件下

###     3 元素加载


###     4 用例维护


测试报告：

