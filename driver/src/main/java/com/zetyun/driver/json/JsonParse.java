package com.zetyun.driver.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.zetyun.driver.log.LogWriter;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParse {
    private Map<String, List<String>> valueMap = new HashMap<String, List<String>>();
    List<String> arrayMap = new ArrayList<>();

    /**
     * 判断字符串是否为Json
     * @param content
     * @return
     */
    public boolean isJson(String content){
        try {
            JSONObject.parseObject(content);
            LogWriter.debug(JsonParse.class, String.format("isJson Method execute success"));
            return  true;
        } catch (Exception ex) {
            LogWriter.error(JsonParse.class, String.format("isJson Method execute exception: [%s]", ex.getMessage()));
            return false;
        }
    }

    /**
     * 获取报文中某个节点的值
     *
     * @param jsonStr
     * @param name
     * @return
     */
    public List<String> getValue(String jsonStr, String name) {
        List<String> valueList = null;

        try {
            covertToString(jsonStr);
            LogWriter.debug(JsonParse.class, "begin to convert string json to value map");

            valueList = valueMap.get(name);
            LogWriter.debug(JsonParse.class, "begin to convert string json to value map");
        } catch (Exception ex) {
            valueList = null;
            LogWriter.error(JsonParse.class, String.format("get value exception: [%s]", ex.getMessage()));
        } finally {
            valueMap.clear();
            LogWriter.debug(JsonParse.class, "clean value map content success");
        }

        return valueList;
    }

    /**
     * 返回json字符串中所有符合array类型的字符
     * @param jsonStr
     * @return
     */
    public List<String> getArray(String jsonStr){
        try {
            arrayMap.clear();
            LogWriter.debug(JsonParse.class, "release array list success");
            convertToArray(jsonStr);
        }
        catch (Exception ex) {
            LogWriter.error(JsonParse.class, String.format("get value exception: [%s]", ex.getMessage()));
        }

        LogWriter.debug(JsonParse.class, "return array list success");
        return arrayMap;
    }

    /**
     * 遍历json串，把结果放在valueMap中
     *
     * @param jsonStr
     */
    private void covertToString(String jsonStr) {
        try {
            // Deal with null jsonStr.
            if (jsonStr.equalsIgnoreCase("[null]")) {
                LogWriter.error(JsonParse.class, "Input json str is null string");
                return;
            }

            Map<String, String> jsonMap = new HashMap<String, String>();

            // 数组类型和map类型分开处理
            if (jsonStr.startsWith("[")) {
                JSONArray jsonArray = JSON.parseArray(jsonStr);
                LogWriter.debug(JsonParse.class, String.format("get json array form [%s] success", jsonStr));

                if (CollectionUtils.isNotEmpty(jsonArray)) {
                    for (int i = 0; i < jsonArray.size(); i++) {
                        String objStr = jsonArray.get(i).toString();
                        LogWriter.debug(JsonParse.class, String.format("get json string [%s] form json array success", objStr));
                        covertToString(objStr);
                    }
                }
            } else if (jsonStr.startsWith("{")) {
                jsonMap = JSON.parseObject(jsonStr, new TypeReference<Map<String, String>>() {});
                LogWriter.debug(JsonParse.class, String.format("get json map form [%s] success", jsonStr));

                for (Map.Entry<String, String> entry : jsonMap.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    List<String> valueList;

                    // 如果valueMap中已经包含某个key了，就获取valueList，往里面加value
                    // 如果不包含就创建一个valueList
                    if (valueMap.containsKey(key)) {
                        valueList = valueMap.get(key);
                    } else {
                        valueList = new ArrayList<>();
                    }
                    valueList.add(value);
                    LogWriter.debug(JsonParse.class, String.format("find key = [%s] value = [%s]", key, value));

                    // 数组和map需要进一步递归处理，如果只是单值，直接放到valueMap中即可
                    if (value.startsWith("[") || value.startsWith("{")) {
                        covertToString(value);
                    } else {
                        LogWriter.debug(JsonParse.class, String.format("set key = [%s] to value map", key));
                        valueMap.put(key, valueList);
                    }
                }
            }
        }catch (Exception ex){
            LogWriter.error(JsonParse.class, String.format("[%s] convert to value map failed, exception: [%s]", jsonStr, ex.getMessage()));
        }
    }

    /**
     * 遍历json串，把结果放在ArrayList中
     *
     * @param jsonStr
     */
    private void convertToArray(String jsonStr) {
        try {
            // Deal with null jsonStr.
            if (jsonStr.equalsIgnoreCase("[null]")) {
                LogWriter.error(JsonParse.class, "Input json str is null string");
                return;
            }

            Map<String, String> jsonMap = new HashMap<String, String>();

            // 数组类型和map类型分开处理
            if (jsonStr.startsWith("[")) {
                JSONArray jsonArray = JSON.parseArray(jsonStr);
                LogWriter.debug(JsonParse.class, String.format("get json array form [%s] success", jsonStr));

                if (CollectionUtils.isNotEmpty(jsonArray)) {
                    for (int i = 0; i < jsonArray.size(); i++) {
                        String objStr = jsonArray.get(i).toString();
                        LogWriter.debug(JsonParse.class, String.format("get json string [%s] form json array success", objStr));

                        //String regex = "\\[(\"\\w+\",)+\"\\w+\"\\]";
                        String regex = "\\[((\"\\w+\"|\"\\w+\\.\\w+\"|\"(\\s*\\w+)+\"),*)+\\]";
                        Pattern p = Pattern.compile(regex);
                        Matcher m = p.matcher(objStr);

                        while (m.find()) {
                            arrayMap.add(m.group());
                        }
                    }
                }
            } else if (jsonStr.startsWith("{")) {
                jsonMap = JSON.parseObject(jsonStr, new TypeReference<Map<String, String>>() {});
                LogWriter.debug(JsonParse.class, String.format("get json array form [%s] success", jsonStr));

                for (Map.Entry<String, String> entry : jsonMap.entrySet()) {
                    String value = entry.getValue();

                    // 数组和map需要进一步递归处理
                    if (value.startsWith("[") || value.startsWith("{")) {
                        convertToArray(value);
                        LogWriter.debug(JsonParse.class, "find a json start with [||}, json = " + value);
                    }
                }
            }
        }catch (Exception ex){
            LogWriter.error(JsonParse.class, String.format("[%s] convert to value array failed, exception: [%s]", jsonStr, ex.getMessage()));
        }
    }
}