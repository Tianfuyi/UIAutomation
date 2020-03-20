package com.zetyun.uitest.pageoperation.common;

import com.zetyun.data.DataParse;
import com.zetyun.datatemplate.elementtemplate.ElementTemplate;
import com.zetyun.driver.log.LogWriter;
import com.zetyun.uitest.utility.ElementUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.Map;

public class Menu {
    private String defaultMenu;

    public Menu(String menu)  {
        defaultMenu = menu;

    }

    /**
     * 选择横向菜单
     * @param driver
     * @return
     */
    public boolean selectR(WebDriver driver){
        LogWriter.info(Menu.class,"选择 菜单" +defaultMenu);
        try {
            String elementSelectorTemplate = DataParse.GetProperties("UIElementSelectorTemplate");
            String filepath =  elementSelectorTemplate;

            Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "Menu");
            String condition = ElementTemplate.getValues(filepath, "Menu").get("菜单").get(defaultMenu);

            WebElement menu = ElementUtil.findElement(driver, condition);
            menu.click();

            return true;
        }catch (Exception ex){
            LogWriter.error(Menu.class,"选择 菜单" +defaultMenu + "失败");
            return false; }
    }

    /**
     * 选择纵向导航
     * @param driver
     * @param navi
     */
    public  void selectC( WebDriver driver,String navi) throws IOException {
        LogWriter.info(Menu.class,"***点击导航 "+navi+" ***");

            String elementSelectorTemplate = DataParse.GetProperties("UIElementSelectorTemplate");
            String filepath =  elementSelectorTemplate;

            String menuname = ElementTemplate.getValues(filepath, "Menu").get(defaultMenu).get(navi);  // 菜单名称
            ElementUtil.findElement(driver,menuname).click();
            Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "Menu");
    }

    public static void main(String[] args) throws IOException {
        WebDriver driver=null;
       // Menu m = new Menu(driver,"分析应用");
   //     m.selectR();
     //   m.selectC("分析模块");
    }

}
