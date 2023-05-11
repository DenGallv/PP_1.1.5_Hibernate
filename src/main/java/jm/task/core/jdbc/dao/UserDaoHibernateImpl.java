package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            String SQL = new StringBuilder()
                    .append("CREATE TABLE IF NOT EXISTS`kata_db`.`user` (\n")
                    .append("  `id` BIGINT NOT NULL AUTO_INCREMENT,\n")
                    .append("  `name` VARCHAR(100) NOT NULL,\n")
                    .append("  `lastname` VARCHAR(100) NOT NULL,\n")
                    .append("  `age` TINYINT NOT NULL,\n")
                    .append("  PRIMARY KEY (`id`));").toString();

            Query query = session.createSQLQuery(SQL);
            query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession();) {
            transaction = session.beginTransaction();

            String SQL = "DROP TABLE IF EXISTS user";

            Query query = session.createSQLQuery(SQL);
            query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession();) {
            transaction = session.beginTransaction();

            session.save(new User(name, lastName, age));

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession();) {
            transaction = session.beginTransaction();

            session.delete(session.get(User.class, id));

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = null;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession();) {
            transaction = session.beginTransaction();

            userList = session.createQuery("FROM User").getResultList();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession();) {
            transaction = session.beginTransaction();

            String stringQuery = "DELETE FROM User";
            Query query = session.createQuery(stringQuery);
            query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
