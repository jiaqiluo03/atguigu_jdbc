package com.atguigu.api.preparedstatement;

import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PSCURDPart {
    @Test
    public void testInsert() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:13306/atguigu?user=root&password=123456");

        String sql = "insert into t_user(account,password,nickname) values(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"test");
        preparedStatement.setString(2,"test");
        preparedStatement.setString(3,"二狗子");
        int rows = preparedStatement.executeUpdate();
        if(rows > 0) {
            System.out.println("插入成功");
        }else {
            System.out.println("插入失败");
        }
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testSelect() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:13306/atguigu?user=root&password=123456");
//        String sql = "select account, password, nickname from t_user where account = ? and password = ? ;";
        String sql = "select id, account, password, nickname from t_user;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setObject(1,"root");
//        preparedStatement.setObject(2,"123456");

        ResultSet resultSet = preparedStatement.executeQuery();

        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        ArrayList<Map> list = new ArrayList<>();

        while (resultSet.next()) {
            Map map = new HashMap<>();

            for (int i = 1; i <= columnCount; i++) {
                map.put(metaData.getColumnLabel(i),resultSet.getObject(i));
            }
            list.add(map);
        }
        System.out.println(list);
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testUpdate() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:13306/atguigu?user=root&password=123456");
        String sql = "update t_user set nickname =? where id =?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"三狗子");
        preparedStatement.setInt(2,3);
        int i = preparedStatement.executeUpdate();
        if(i > 0){
            System.out.println("修改成功");
        }else {
            System.out.println("修改失败");
        }
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testDelete() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:13306/atguigu", "root", "123456");
        String sql = "delete from t_user where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,3);
        int i = preparedStatement.executeUpdate();
        if(i > 0){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
        preparedStatement.close();
        connection.close();
    }
}
