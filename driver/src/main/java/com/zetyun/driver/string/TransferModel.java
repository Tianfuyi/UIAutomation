package com.zetyun.driver.string;

import com.zetyun.driver.log.LogWriter;

public class TransferModel {
    private String key;
    private String from;            //从哪里获取信息
    private String to;              //往哪里设置信息
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        try {
            TransferModel tfm = (TransferModel) obj;

            if(key.equals(tfm.getKey())){
                return true;
            }else {
                return false;
            }
        }catch (Exception ex){
            LogWriter.error(TransferModel.class, "equal method execute exception, message = " + ex.getMessage());
            return false;
        }
    }
}
