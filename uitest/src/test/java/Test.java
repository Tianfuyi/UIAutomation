import com.zetyun.data.DataParse;
import com.zetyun.datatemplate.elementtemplate.ElementTemplate;
import com.zetyun.uitest.pageoperation.AutoModel;
import com.zetyun.uitest.pageoperation.common.InitBrowser;
import com.zetyun.uitest.pageoperation.common.Menu;
import com.zetyun.uitest.pageoperation.common.Search;
import com.zetyun.uitest.pageoperation.common.User;
import com.zetyun.uitest.abstractbusiness.ActionAnalysis;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class Test {
    /**
     *
     */
  //  @org.testng.annotations.Test
    public static void main(String args[]) throws Exception {

        WebDriver driver = null;
        driver=  InitBrowser.openBroser(driver,"http:/IP");
        new User().login(driver,"{\"username\":\"\",\"password\":\"\"}");  // user 类
        Menu m = new Menu("分析应用");
        m.selectR(driver);
       Search search = new Search();
      String result=search.checkSearch(driver,"{\"模块名称\":\"分析模块\"}");
        System.out.println(result);
    }

    
    public static void readMenu(){
        try {
            String elementSelectorTemplate = DataParse.GetProperties("ElementSelectorTemplate");
            String filepath = System.getProperty("user.dir") + elementSelectorTemplate;

            Map<String, Map<String, String>> elementConfig = ElementTemplate.getValues(filepath, "Menu");

            for (Map.Entry<String, Map<String, String>> entry:elementConfig.entrySet()) {
                if(entry.getKey().contains("概览")){

                }
                System.out.println("    Header = " + entry.getKey());
                System.out.println("    Child");
                for (Map.Entry<String, String> child:entry.getValue().entrySet()) {
                    System.out.println("        key = " + child.getKey() + "    value = " + child.getValue());
                }
            }
        }catch (Exception ex){}
    }


}
