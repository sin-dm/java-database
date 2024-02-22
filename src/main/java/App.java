import java.util.List;

public class App {

    public static void main(String[] args) {
        UserRepo userRepo = new UserRepo();
//        List<User> users = userRepo.getAllUsers();
//        System.out.println("Размер списка " + users.size());
//        User user = new User("TEST", "TESTOV", "TEST");
//        user = userRepo.createUser(user);
//        System.out.println("ИД пользователя " + user.getId());
//        System.out.println("Создан пользователя " + user.getCreatedAt());
//        System.out.println("Обновлен пользователь " + user.getUpdatedAt());
        JdbcTemplateUserRepo jdbcTemplateUserRepo = new JdbcTemplateUserRepo();
        User user = jdbcTemplateUserRepo.getUserById(2000L);
        System.out.println("ИД пользователя " + user.getId());
        System.out.println("Создан пользователя " + user.getCreatedAt());
        System.out.println("Обновлен пользователь " + user.getUpdatedAt());
        Integer count = jdbcTemplateUserRepo.deleteUserById(2000L);
        System.out.println("Удалено пользователей: " + count);
    }
}
