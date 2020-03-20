package com.zetyun.driver.mqmessage;

public class RabbitMQServerConfig {
    private String IP = "";
    private int PORT = 0;
    private String USER = "admin";
    private String PASSWORD = "admin";
    private String VirtualHost = "/datacanvas";
    //private long Timeout = 600000;
    private long Timeout = 6000;

    public RabbitMQServerConfig(String ip, int port){
        this.IP = ip;
        this.PORT = port;
    }

    public RabbitMQServerConfig(String ip, int port, String user, String passwd, String virtualhost, long timeout){
        this.IP = ip;
        this.PORT = port;
        this.USER = user;
        this.PASSWORD = passwd;
        this.VirtualHost = virtualhost;
        this.Timeout = timeout;
    }

    public String getIP() {
        return IP;
    }

    public int getPORT() {
        return PORT;
    }

    public String getUSER() {
        return USER;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getVirtualHost() {
        return VirtualHost;
    }

    public long getTimeout() {
        return Timeout;
    }
}
