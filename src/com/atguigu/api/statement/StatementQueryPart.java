package com.atguigu.api.statement;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class StatementQueryPart {
    public static void main(String[] args) throws SQLException {
        //1.注册驱动
        DriverManager.registerDriver(new Driver());
        //2.获取链接
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:13306/atguigu","root","123456");
        //3.创建statement
        Statement statement = connection.createStatement();
        //4.发送sql语句，并获取返回结果
        String sql = "select * from t_user;";
        ResultSet resultSet = statement.executeQuery(sql);
        //5.进行结果集解析
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String account = resultSet.getString("account");
            String password = resultSet.getString("PASSWORD");
            String nickname = resultSet.getString("nickname");
            System.out.println(id + "--" + account + "--" + password + "--" + nickname);
        }
        //6.资源关闭
        resultSet.close();
        statement.close();
        connection.close();
    }
}
