package com.atguigu.api.preparedstatement;

import java.sql.*;
import java.util.Scanner;

public class PSUserLoginPart {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("account:");
        String account = scanner.nextLine();
        System.out.println("password:");
        String password = scanner.nextLine();

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:13306/atguigu", "root", "123456");

        String sql = "select * from t_user where account = ? and password = ? ;";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setObject(1, account);
        preparedStatement.setObject(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()) {
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
