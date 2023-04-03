package com.atguigu.api.preparedstatement;

import org.junit.Test;

import java.sql.*;

public class PSInsert {
    @Test
    public void psInsert() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:13306/atguigu", "root", "123456");

        String sql = "insert into t_user(account,password,nickname) value(?,?,?);";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        long start = System.currentTimeMillis();

        for (int i = 1; i <= 10000; i++) {
            preparedStatement.setObject(1, "insert_ac " + i);
            preparedStatement.setObject(2, "insert_ps " + i);
            preparedStatement.setObject(3, "insert_na " + i);

            preparedStatement.executeUpdate();
        }

        long end = System.currentTimeMillis();

        //8125
        System.out.println("time = " + (end - start));

        preparedStatement.close();
        connection.close();
    }

    @Test
    public void psBatchInsert() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:13306/atguigu?rewriteBatchedStatements=true",
                "root", "123456");

        String sql = "insert into t_user(account,password,nickname) values(?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        long start = System.currentTimeMillis();

        for (int i = 1; i <= 10000; i++) {
            preparedStatement.setObject(1, "b_in_ac " + i);
            preparedStatement.setObject(2, "b_in_ps " + i);
            preparedStatement.setObject(3, "b_in_na " + i);

            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();

        long end = System.currentTimeMillis();

        //299
        System.out.println("time = " + (end - start));

        preparedStatement.close();
        connection.close();
    }

    @Test
    public void psReKey() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:13306/atguigu", "root", "123456");

        String sql = "insert into t_user(account,password,nickname) value(?,?,?);";

        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setObject(1, "ac");
        preparedStatement.setObject(2, "insert_ps");
        preparedStatement.setObject(3, "insert_na" );

        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        int anInt = resultSet.getInt(1);
        System.out.println(anInt);

        preparedStatement.close();
        connection.close();
    }
}
