package com.zetyun.datatemplate.testresulttemplate;

/**
 * @author Created by Bing
 */
public class ExtentReportsServerConfig {
    private String ip;
    private int dbPort;
    private int webPort;

    public ExtentReportsServerConfig(String ip, int dbPort, int webPort) {
        this.ip = ip;
        this.dbPort = dbPort;
        this.webPort = webPort;
    }

    public String getIP() {
        return this.ip;
    }

    public int getDbPort() {
        return this.dbPort;
    }

    public int getWebPort() {
        return this.webPort;
    }
}
