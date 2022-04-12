package ru.learnup.qa.db.exmpl;

import ru.learnup.qa.db.exmpl.model.GoodEntity;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        DbHelper dbHelper = new DbHelper();
        Shop shop = new Shop(dbHelper);
        dbHelper.initConnection();

        System.out.println("До добавления");
        shop.printAllGoods();

        System.out.println("\nДобавляем мед...");
        shop.addGood("Мед", "Вкусный");

        System.out.println("\nПосле добавления");
        shop.printAllGoods();

//        final GoodEntity target = shop.getGood(6);
//        System.out.println("Товар, который нашли:\n" + target);
//
//        target.setName("Новое имя");
//        target.setDescription("Новое описание");
//
//        shop.updateGood(target);

//        shop.deleteGood(target);
//
//        shop.printAllGoods();
    }
}
