package com.zetyun.driver.string;

import com.zetyun.driver.log.LogWriter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringParse {
    Map<String, TransferModel> stringTransferModelMap = new LinkedHashMap<>();

    public StringParse() {
    }

    public StringParse(List<TransferModel> transferModels) {
        if (transferModels == null ||
                transferModels.size() == 0) {
            LogWriter.debug(StringParse.class, "Input transferModels is null");
            return;
        }

        for (TransferModel transferModel : transferModels) {
            stringTransferModelMap.put(transferModel.getKey(), transferModel);
        }
    }

    /**
     * 初始化transfer列表
     *
     * @param transfer
     * @return
     */
    public boolean init(String transfer) {
        try {
            if (transfer.isEmpty()) {
                LogWriter.debug(StringParse.class, "Input message is null");
                return false;
            }

            List<String> stringList = new ArrayList<>();
            String regex = "\\[\\w+,\\w+,\\w+\\]";

            LogWriter.debug(StringParse.class, "Begin use regex to match transfer[" + transfer + "]");
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(transfer);

            while (m.find()) {
                stringList.add(m.group());
                LogWriter.debug(StringParse.class, "find a match result, value = " + m.group());
            }

            for (String msg : stringList) {
                msg = msg.replace("[", "");
                msg = msg.replace("]", "");

                String[] s_list = msg.split(",");

                TransferModel tfm = new TransferModel();
                tfm.setKey(s_list[0]);
                tfm.setFrom(s_list[1]);
                tfm.setTo(s_list[2]);
                tfm.setValue("");

                LogWriter.debug(StringParse.class, "add a transfer model to list success");
                stringTransferModelMap.put(s_list[0], tfm);
            }

            return true;
        } catch (Exception ex) {
            LogWriter.error(StringParse.class, "StringParse init method exception, value = " + ex.getMessage());
            stringTransferModelMap.clear();
            return false;
        }
    }

    /**
     * 设置key的value
     *
     * @param key
     * @param value
     * @return
     */
    public boolean setValue(String key, String value) {
        try {
            if (key.isEmpty() || value.isEmpty()) {
                LogWriter.debug(StringParse.class, "Input message is null");
                return false;
            }

            if (stringTransferModelMap.containsKey(key)) {
                TransferModel tfm = stringTransferModelMap.get(key);
                tfm.setValue(value);

                LogWriter.debug(StringParse.class, "replace value success, new value = " + value);
                stringTransferModelMap.put(key, tfm);

                return true;
            } else {
                LogWriter.debug(StringParse.class, "Can not find key in transfer map");
                return false;
            }
        } catch (Exception ex) {
            LogWriter.error(StringParse.class, "StringParse setValue method exception, value = " + ex.getMessage());
            return false;
        }
    }

    /**
     * 从指定的字符串中替换已有的key
     *
     * @param message
     * @return
     */
    public String replace(String message) {
        String transfer = message;
        try {

            // Get [@Key]s from input.
            List<String> keys = new ArrayList<>();

            // If contains [@Key], it will match Key.
            String keyPattern = "((?<=\\[@)[^\\]]+(?=\\]))";

            LogWriter.debug(StringParse.class, "Begin use regex to match transfer[" + message + "]");
            Pattern p = Pattern.compile(keyPattern);
            Matcher m = p.matcher(message);
            while (m.find()) {
                keys.add(m.group());
                LogWriter.debug(StringParse.class, "find a match result, value = " + m.group());
            }

            if (keys.size() > 0) {
                for (String key : keys) {
                    transfer = transfer.replaceAll("\\[@" + key + "\\]", stringTransferModelMap.get(key).getValue());
                }
            }

            // Always return transfer
            return transfer;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return transfer;
        }
    }

    /**
     * 获取Transfer map
     *
     * @return
     */
    public Map<String, TransferModel> getStringTransferModelMap() {
        return stringTransferModelMap;
    }
}
