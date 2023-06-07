package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//         реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("A", "Aaa", (byte) 1);
        userService.saveUser("B", "Bbb", (byte) 2);
        userService.saveUser("C", "Ccc", (byte) 3);
        userService.saveUser("D", "Ddd", (byte) 4);

        List<User> userList = userService.getAllUsers();
        for (User user : userList) {
            System.out.println(user);
        }

        userService.removeUserById(1);

        userService.cleanUsersTable();

        userService.dropUsersTable();

//         Close resource:
//        try {
//            Util.getConnection().close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        Util.getSessionFactory().close();
    }
}
