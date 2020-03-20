package com.zetyun.driver.jdbc;

import com.zetyun.driver.log.LogWriter;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class PostgreSQL {
    private String dbURL = "";
    private String dbUser = "";
    private String dbPasswd = "";
    private Connection conn = null;

    /**
     * 默认构造函数
     * @param url
     * @param user
     * @param passwd
     */
    public PostgreSQL(String url, String user, String passwd){
        this.dbURL = url;
        this.dbUser = user;
        this.dbPasswd = passwd;

        try {
            Properties parameters = new Properties();
            parameters.put("user", this.dbUser);
            parameters.put("password", this.dbPasswd);
            conn = DriverManager.getConnection(this.dbURL, parameters);

            LogWriter.debug(PostgreSQL.class.getClass(), "Open database success");
        }catch (SQLException ex){
            LogWriter.error(PostgreSQL.class.getClass(), ex.getMessage());
            conn = null;
        }
    }

    /**
     * 获取数据库中某一列的所有值
     * @param sql
     * @param columnname
     * @return
     */
    public Map<Integer, String> getValues(String sql, String columnname){
        Statement stmt = null;
        ResultSet rs = null;
        Map<Integer, String> values = new LinkedHashMap<>();

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            int i = 0;
            while(rs.next()){
                values.put(i, rs.getObject(rs.findColumn(columnname)).toString());
                i++;
            }
        }catch (SQLException ex){
            LogWriter.error(PostgreSQL.class.getClass(), ex.getMessage());
            values.clear();
        }

        return values;
    }

    /**
     * 更新数据库
     * @param sql
     * @return
     */
    public int setValues(String sql){
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
            return stmt.executeUpdate(sql);
        }catch (SQLException ex){
            LogWriter.error(PostgreSQL.class.getClass(), ex.getMessage());
            return -1;
        }
    }

    public void close(){
        try {
            if (conn != null) {
                conn.close();
            }
        }catch (SQLException ex){
            LogWriter.error(PostgreSQL.class.getClass(), ex.getMessage());
        }
    }
}
