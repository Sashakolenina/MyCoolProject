package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        System.out.println("Таблица создана");

        userService.saveUser("Александра", "Гущина", (byte) 25);
        System.out.println("Пользователь Александра добавлен");

        userService.saveUser("Марина", "Маринина", (byte) 25);
        System.out.println("Пользователь Марина добавлен");

        List<User> users = userService.getAllUsers();
        System.out.println("Список пользователей:");
        for (User user : users) {
            System.out.println(user);}

            userService.removeUserById(2);
            System.out.println("\nПользователь с id=2 удален");


            userService.cleanUsersTable();
            System.out.println("Таблица пользователей очищена");


            userService.dropUsersTable();
            System.out.println("Таблица пользователей удалена");

    }
}


// реализуйте алгоритм здесь


