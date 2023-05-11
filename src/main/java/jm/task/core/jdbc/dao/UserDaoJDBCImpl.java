package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {

            String SQL = new StringBuilder()
                    .append("CREATE TABLE IF NOT EXISTS`kata_db`.`user` (\n")
                    .append("  `id` BIGINT NOT NULL AUTO_INCREMENT,\n")
                    .append("  `name` VARCHAR(100) NOT NULL,\n")
                    .append("  `lastname` VARCHAR(100) NOT NULL,\n")
                    .append("  `age` TINYINT NOT NULL,\n")
                    .append("  PRIMARY KEY (`id`));").toString();

            statement.executeUpdate(SQL);
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {

            String SQL = "DROP TABLE IF EXISTS user";

            statement.executeUpdate(SQL);
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = connection.createStatement()) {

            String SQL = new StringBuilder()
                    .append("INSERT INTO user(name,lastname,age) ")
                    .append("VALUES ('").append(name).append("', '")
                    .append(lastName).append("', ")
                    .append(age).append(")").toString();

            statement.executeUpdate(SQL);
            connection.commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (Exception e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = connection.createStatement()) {

            String SQL = "DELETE FROM user WHERE id = " + id + "";

            statement.executeUpdate(SQL);
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {

            String SQL = "SELECT * FROM user";

            ResultSet resultSet = statement.executeQuery(SQL);
            connection.commit();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {

            String SQL = "TRUNCATE user";

            statement.executeUpdate(SQL);
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
