package com.example.DemoWeb112233.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtilsAdvanced {
    //1.定义成员变量DataSource
    private static DataSource ds;

    static{
        try {
            //1.加载配置文件
            Properties pro = new Properties();
            pro.load(JdbcUtilsAdvanced.class.getResourceAsStream("druid.properties"));
            //2.获取Datasource
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //获取链接
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    //获取连接池方法
    public static DataSource getDataSource(){
        return ds;
    }

    //释放资源
    public static void close(Statement stmt,Connection con){
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(con != null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //释放资源重载
    public static void close(ResultSet rs, Statement stmt, Connection con){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(con != null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
