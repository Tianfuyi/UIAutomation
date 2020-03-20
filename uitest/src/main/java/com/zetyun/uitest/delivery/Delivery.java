package com.zetyun.uitest.delivery;


import org.openqa.selenium.WebDriver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分发类
 */
public class Delivery {



    /**
     * @param driver
     * @param Method     类方法
     * @param IsHaveData Yes/No  测试数据是否有
     * @param Data       测试数据
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException    Java 类反射 通过方法 名找到类 并执行方法
     */
    public static Object deliver(WebDriver driver, String Method, String IsHaveData, String Data) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class classes = null;
      String[] actionName={"Action","ActionAnalysis","ActionAutoml","ActionAutoModelView","ActionDataApp","ActionModelRepository","ActionDataModule"};

       for(String acName:actionName){
        if( !matchCLassActions(Method,acName).equals("void")){
            classes=Class.forName(matchCLassActions(Method,acName));
            break;
        }
     }

        Object objString=null;
        //获取所有公有方法
        Method[] methodArray = classes.getDeclaredMethods();
        for (Method m : methodArray) {
            String methodName = m.getName();
            if (IsHaveData.equals("No")) {      // 无参方法
                if (methodName.equals(Method)) {
                    objString=       m.invoke(null, driver);
                    System.out.println(objString);

                }
            } else if (IsHaveData.equals("Yes")) {  // 有参方法

                String str = Data;
                String[] strs = null;
                strs = new String[]{Data};

                if (methodName.equals(Method) && m.getParameterTypes().length - 1 == strs.length&&strs.length==1) {  // 匹配方法及参数个数

                    m = classes.getMethod(Method, WebDriver.class, String.class);
                    System.out.println(m);
                    objString = m.invoke(null, driver, strs[0].toString());
                    System.out.println(objString);

                }
            }
        }
        return objString;
    }

    /**
     * 根据方法名称匹配类名称
     * @param methods
     * @param actionName
     * @return
     * @throws ClassNotFoundException
     */
    public static String matchCLassActions(String methods,String actionName) throws ClassNotFoundException {
        String classname = "void";
        Class classes = Class.forName("com.zetyun.uitest.abstractbusiness."+actionName);
        //    Method[] methodArray = classes.getMethods();
        Method[] methodArray = classes.getDeclaredMethods();
        for (Method method : methodArray) {
            String methodName = method.getName();
            if (methodName.equals(methods)) {
                classname = classes.getName();
                System.out.println(classes.getName());
            }
        }
        return classname;
    }




}