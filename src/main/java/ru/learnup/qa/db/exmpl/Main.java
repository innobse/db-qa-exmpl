package ru.learnup.qa.db.exmpl;

import ru.learnup.qa.db.exmpl.model.GoodEntity;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        DbHelper dbHelper = new DbHelper();
        Shop shop = new Shop(dbHelper);
        dbHelper.initConnection();

        shop.printAllGoodsByCategoryId(2);
    }
}
