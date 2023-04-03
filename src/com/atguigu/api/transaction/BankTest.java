package com.atguigu.api.transaction;

import java.sql.SQLException;

public class BankTest {
    public static void main(String[] args) throws SQLException {
        BankService service = new BankService();
        service.transfer("lvdandan","ergouzi",500);
    }
}
