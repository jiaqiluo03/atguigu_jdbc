package com.atguigu.api.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class Tool {
    @Test
    public void hardTool() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();

        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setName("root");
        druidDataSource.setPassword("123456");
        druidDataSource.setUrl("jdbc:mysql://127.0.0.1:13306/atguigu");

        Connection connection = druidDataSource.getConnection();
    }

    @Test
    public void softTool(){
        System.out.println("git test");
        System.out.println("git test2");
        System.out.println("git test3 master");
        System.out.println("git test3");
        System.out.println("push test");
        System.out.println("pull test");
    }
}
