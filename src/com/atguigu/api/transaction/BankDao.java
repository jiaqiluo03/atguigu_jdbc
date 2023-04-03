package com.atguigu.api.transaction;

import com.atguigu.api.utils.BaseDao;
import com.atguigu.api.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class BankDao extends BaseDao{
    public int add(String account, int money) throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        String sql = "update t_bank set money = money + ? where account = ?";
        return this.update(sql, money, account);
    }

    public int sub(String account, int money) throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        String sql = "update t_bank set money = money - ? where account = ?";
        return this.update(sql, money, account);
    }
}
