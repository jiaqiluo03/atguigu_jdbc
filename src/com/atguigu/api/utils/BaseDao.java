package com.atguigu.api.utils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

public abstract class BaseDao {
    public static int update(String sql, Object... args) throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
        }
        int i = preparedStatement.executeUpdate();
        preparedStatement.close();
        if (connection.getAutoCommit()) {
            JDBCUtil.freeConnection();
        }
        return i;
    }

    public static <T> ArrayList<T> query(Class<T> type, String sql, Object... args) throws Exception{
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        if(args != null && args.length > 0){
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i +1, args[i]);
            }
        }

        ArrayList<T> list = new ArrayList<>();

        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (resultSet.next()){
            T t = type.newInstance();
            for (int i = 1; i <= columnCount; i++) {
                Object value = resultSet.getObject(i);
                String columnName = metaData.getColumnLabel(i);
                Field field = type.getDeclaredField(columnName);
                field.setAccessible(true);
                field.set(t,value);
            }
            list.add(t);
        }
        resultSet.close();
        preparedStatement.close();
        if(connection.getAutoCommit()){
            JDBCUtil.freeConnection();
        }
        return list;
    }
}
