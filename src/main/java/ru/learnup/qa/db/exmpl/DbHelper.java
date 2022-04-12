package ru.learnup.qa.db.exmpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
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

    private Connection connection;
    private String dbUrl = "jdbc:postgresql://127.0.0.1:5433/shop";
    private String user = "postgres";
    private String pass = "postgres";

    public void initConnection() throws SQLException {
        this.connection = DriverManager.getConnection(dbUrl, user, pass);
    }

    public Collection<GoodEntity> getAllGoods() {

        String sql = "SELECT * FROM goods;";
        try (Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery(sql);
            List<GoodEntity> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(
                        parseGood(resultSet));
            }
            return result;
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return null;
    }

    public GoodEntity getById(int id) {

        String sql = "SELECT * FROM goods WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                return parseGood(resultSet);
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return null;
    }

    public boolean addGood(GoodEntity good) {

        String sql = "INSERT INTO goods(name, description) VALUES(?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, good.getName());
            statement.setString(2, good.getDescription());

            return statement.executeUpdate() == 1;
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return false;
    }

    public boolean updateGood(GoodEntity good) {
        String sql = "UPDATE goods SET name = ?, description = ? WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, good.getName());
            statement.setString(2, good.getDescription());
            statement.setInt(3, good.getId());

            return statement.executeUpdate() == 1;
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return false;
    }

    public boolean deleteById(int id) {
        String sql = "DELETE FROM goods WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() == 1;

        } catch (SQLException err) {
            err.printStackTrace();
        }
        return false;
    }

    private GoodEntity parseGood(ResultSet resultSet) throws SQLException {
        final int id = resultSet.getInt("id");
        final String name = resultSet.getString("name");
        final String description = resultSet.getString("description");

        return new GoodEntity(id, name, description);
    }
}
