package com.zetyun.uitest.utility;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class JsonUtil {


    public Map jsonToMaps(String str) {
        Map json = (Map) JSONObject.parse(str);
        System.out.println("json size "+json.size());
        for (Object map: json.entrySet()){
            System.out.println(((Map.Entry)map).getKey()+"  "+((Map.Entry)map).getValue());
        }
        System.out.println(json);
        return json;
    }

    public static void main(String[] args) {
        JsonUtil j= new JsonUtil();
        String a="{\n" +
                " \"login\": {\n" +
                "  \"username\": \"apsadmin@TEST.COM\",\n" +
                "  \"password\": \"Server2008!\"\n" +
                " }\n" +
                "}";
         Map m= j.jsonToMaps(a);
         String b=m.get("login").toString();
        System.out.println(b);
    }


}
