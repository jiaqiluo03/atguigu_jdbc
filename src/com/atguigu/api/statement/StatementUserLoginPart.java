package com.atguigu.api.statement;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class StatementUserLoginPart {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("user");
        String user = scanner.nextLine();
        System.out.println("password");
        String password = scanner.nextLine();

//        DriverManager.registerDriver(new Driver()); //该方法会注册两次驱动 registerDriver方法注册一次，Driver.static也会注册一次

//        new Driver();
        //字符串 -> 提取到外部配置文件 切换数据库后，不用改代码
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:13306/atguigu", "root", "123456");

        /*
        Properties info = new Properties();
        info.put("user", "root");
        info.put("password", "123456");
        Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:13306/atguigu", info);

        Connection connection2 = DriverManager.getConnection("jdbc:mysql://localhost:13306/atguigu?user=root&password=123456");
        */
        Statement statement = connection.createStatement();

        String sql = "select * from t_user where account = '" + user + "'" + " and password = '" + password + "'";
        ResultSet resultSet = statement.executeQuery(sql);

//        while (resultSet.next()) {
//            int id = resultSet.getInt(1);
//            String username = resultSet.getString("account");
//            String pw = resultSet.getString("password");
//            String nickname = resultSet.getString(4);
//            System.out.println(id + "--" + username + "--" + pw + "--" + nickname);
//        }

        if(resultSet.next()) {
            System.out.println("登陆成功");
        }else {
            System.out.println("登陆失败");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
