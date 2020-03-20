package com.zetyun.datatemplate.basetemplate;

import com.zetyun.driver.excel.ExcelParse;
import com.zetyun.driver.log.LogWriter;

import java.util.LinkedHashMap;
import java.util.Map;

public class BaseTemplate {
    /**
     * 根据key获取指定excel文件中，指定sheet中对应的value
     * @param filepath
     * @param sheetname
     * @param key
     * @return
     */
    public static String getValue(String filepath, String sheetname, String key){
        try {
            //声明并初始化一个excel读取类
            ExcelParse ep = new ExcelParse(filepath);
            LogWriter.debug(BaseTemplate.class, "Create Workbook Factory success");

            //设置当前工作表格
            if (!ep.setWorkSheet(sheetname)) {
                throw new Exception(String.format("Set Work sheet failed, sheet name = %s", sheetname));
            }

            //获取excel指定表格的所有行数
            int rows = ep.getRows();
            LogWriter.debug(BaseTemplate.class, "get sheet total row number success, value = " + String.valueOf(rows));

            //遍历所有行数，确认key是否相等，如果相等，则获取value并返回
            for (int i = 0; i < rows; i++) {
                String k = ep.getCellValue(i, 0);
                if (k.equals(key)) {
                    LogWriter.debug(BaseTemplate.class, "Find " + key + " value, value = " + ep.getCellValue(i, 1));
                    return ep.getCellValue(i, 1);
                }
            }
        }catch (Exception ex){
            LogWriter.error(BaseTemplate.class, String.format("get Value method execute exception, value = %x", ex.getMessage()));
        }

        return null;
    }

    /**
     * 返回指定sheet name中的所有键值
     * @param filepath
     * @param sheetname
     * @return
     */
    public static Map<String, Map<String, String>> getValues(String filepath, String sheetname){
        try{
            Map<String, Map<String, String>> valuesMap = new LinkedHashMap<>();
            Map<String, String> stringMap = new LinkedHashMap<>();
            LogWriter.debug(BaseTemplate.class, "Initialize scenario list success");

            //声明并初始化一个excel读取类
            ExcelParse ep = new ExcelParse(filepath);
            LogWriter.debug(BaseTemplate.class, "Create Workbook Factory success");

            //设置当前工作表格
            if (!ep.setWorkSheet(sheetname)) {
                throw new Exception(String.format("Set Work sheet failed, sheet name = %s", sheetname));
            }

            //获取excel指定表格的所有行数
            int rows = ep.getRows();
            LogWriter.debug(BaseTemplate.class, "get sheet total row number success, value = " + String.valueOf(rows));

            //设置起始查询位置
            int query_r = 1;
            int query_c = 0;

            String key = "";

            while (query_r < rows){
                if(ep.isMerged(query_r, query_c)){
                    //表明是一个新的配置项，需要增加新的key
                    String value = ep.getCellValue(query_r, query_c);
                    if(!key.equals(value)){
                        if(key.equals(""))
                        {
                            key = value;
                        }
                        valuesMap.put(key, stringMap);
                        key = value;
                        stringMap = new LinkedHashMap<>();
                    }
                }else {
                    //表明是配置项的子项
                    stringMap.put(ep.getCellValue(query_r, 0), ep.getCellValue(query_r, 1));
                }

                query_r += 1;
            }

            if (stringMap.size() > 0){
                valuesMap.put(key, stringMap);
            }

            return valuesMap;
        }catch (Exception ex){
            return null;
        }
    }
}
