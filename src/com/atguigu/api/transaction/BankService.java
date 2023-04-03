package com.atguigu.api.transaction;

import com.atguigu.api.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class BankService {
    public void transfer(String addAccount, String subAccount, int money) throws SQLException {
        Connection connection = JDBCUtil.getConnection();

        int flag = 0;

        try{
            connection.setAutoCommit(false);

            BankDao bankDao = new BankDao();
            bankDao.add(addAccount,money);
            bankDao.sub(subAccount,money);
            connection.commit();
            flag = 1;
        }catch (Exception e){
            e.printStackTrace();
            connection.rollback();
        }finally {
            JDBCUtil.freeConnection();
        }

        if(flag == 1){
            System.out.println("转账成功！");
        }else{
            System.out.println("转账失败！");
        }
    }
}
