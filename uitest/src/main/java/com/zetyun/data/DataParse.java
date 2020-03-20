package com.zetyun.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataParse {
    /**
     * 根据配置字段属性名称，获取Value
     *
     * @param name properties文件中的name属性
     * @return String 返回字符串类型数值
     * @throws IOException
     */
    public static String GetProperties(String name) throws IOException {
        InputStream input;
        Properties properties;
        String pk = "com.zetyun.data.DataParse";
        String resoures = "/framework.properties";


        String path= System.getProperty("user.dir");
        if(!path.contains("uitest")){
            path=path+"\\uitest";
        }

        try {
            input = Class.forName(pk).getResourceAsStream(resoures);

            properties = new Properties();
            properties.load(input);

            return path+properties.getProperty(name);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        String path= System.getProperty("user.dir");
        if(!path.contains("uitest")){
            path=path+"\\uitest";
        }
        System.out.println(path);
    }
}
