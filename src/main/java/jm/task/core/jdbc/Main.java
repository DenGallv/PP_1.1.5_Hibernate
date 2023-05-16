package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//         реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("A", "A", (byte) 1);
        userService.saveUser("B", "B", (byte) 2);
        userService.saveUser("C", "C", (byte) 3);
        userService.saveUser("D", "D", (byte) 4);

        List<User> userList = userService.getAllUsers();
        for (User user : userList) {
            System.out.println(user);
        }

        userService.cleanUsersTable();

        userService.dropUsersTable();

        Util.getSessionFactory().close();
    }
}
