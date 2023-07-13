package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    private static SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = getSessionFactory().openSession();) {
            Transaction transaction = session.beginTransaction();
            String sqlCreate = "CREATE TABLE IF NOT EXISTS users" +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastname VARCHAR(255), age TINYINT);";
            Query query = session.createSQLQuery(sqlCreate).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = getSessionFactory().openSession();) {
            Transaction transaction = session.beginTransaction();
            String sqlDrop = "DROP TABLE IF EXISTS users";
            Query query = session.createSQLQuery(sqlDrop).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = getSessionFactory().openSession();) {
            Transaction transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = getSessionFactory().openSession();) {
            Transaction transaction = session.beginTransaction();
            session.remove(session.get(User.class, id));
            transaction.commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = getSessionFactory().openSession();) {
            Transaction transaction = session.beginTransaction();
            return session.createQuery("select u from User u", User.class).list();
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = getSessionFactory().openSession();) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("delete from User u").executeUpdate();
            transaction.commit();
        }
    }
}
