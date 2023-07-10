package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;


public class Main {
    private static final UserService userService = new UserServiceImpl();

    public static void main(String[] args) throws SQLException {
        userService.createUsersTable();
        userService.saveUser("Лев", "Толстой", (byte) 82);
        userService.saveUser("Федор", "Достаевский", (byte) 59);
        userService.saveUser("Антон", "Чехов", (byte) 44);
        userService.saveUser("Николай", "Гоголь", (byte) 42);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.connectionClose();
    }
}
