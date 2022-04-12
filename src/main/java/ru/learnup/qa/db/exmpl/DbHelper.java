package ru.learnup.qa.db.exmpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import ru.learnup.qa.db.exmpl.model.CategoryEntity;
import ru.learnup.qa.db.exmpl.model.GoodEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Description
 *
 * @author bse71
 * Created on 07.04.2022
 * @since
 */
public class DbHelper {

    //  Statement
    //  PreparedStatement

    private SessionFactory sessionFactory;

    public void initConnection() throws SQLException {
        ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        final Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();

        this.sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    public Collection<GoodEntity> getAllGoods() {

        try (Session session = sessionFactory.openSession()) {
            String jpql = "from GoodEntity";
            final Query<GoodEntity> query = session.createQuery(jpql, GoodEntity.class);
            return query.getResultList();
        }
    }

    public CategoryEntity getCategoryById(int id) {

        try (Session session = sessionFactory.openSession()) {
            return session.find(CategoryEntity.class, id);
        }
    }

    public GoodEntity getById(int id) {

        try (Session session = sessionFactory.openSession()) {
            return session.find(GoodEntity.class, id);
//            String jpql = "from GoodEntity good where good.id = :id";
//            final Query<GoodEntity> query = session.createQuery(jpql, GoodEntity.class);
//            query.setParameter("id", id);
//            return query.getSingleResult();
        }
    }

    public boolean addGood(GoodEntity good) {

        try (Session session = sessionFactory.openSession()) {
            final Transaction tx = session.beginTransaction();
            session.save(good);
            tx.commit();
            return true;
        }
    }

    public boolean updateGood(GoodEntity good) {

        try (Session session = sessionFactory.openSession()) {
            final Transaction tx = session.beginTransaction();
            session.saveOrUpdate(good);
            tx.commit();
            return true;
        }
    }

    public boolean deleteById(int id) {

        try (Session session = sessionFactory.openSession()) {
            final Transaction tx = session.beginTransaction();
            final Query query = session.createNamedQuery("good_deleteById");
            query.setParameter("id", id);
            final boolean result = query.executeUpdate() == 1;
            tx.commit();
            return result;
        }
    }
}
