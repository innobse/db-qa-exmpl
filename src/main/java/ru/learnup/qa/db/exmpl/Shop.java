package ru.learnup.qa.db.exmpl;

import ru.learnup.qa.db.exmpl.model.GoodEntity;

import java.util.Collection;

/**
 * Description
 *
 * @author bse71
 * Created on 07.04.2022
 * @since
 */
public class Shop {

    private DbHelper dbHelper;

    public Shop(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void printAllGoods() {
        Collection<GoodEntity> goods = dbHelper.getAllGoods();
        goods.forEach(System.out::println);
    }

    public void printGood(int id) {
        System.out.println(dbHelper.getById(id));
    }

    public GoodEntity getGood(int id) {
        return dbHelper.getById(id);
    }

    public boolean updateGood(GoodEntity good) {
        return dbHelper.updateGood(good);
    }

    public boolean deleteGood(GoodEntity good) {
        return dbHelper.deleteById(good.getId());
    }

    public void addGood(String name, String description) {
        dbHelper.addGood(
                new GoodEntity(name, description));
    }
}
