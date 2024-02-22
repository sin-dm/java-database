import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepo extends Database {

    public List<User> getAllUsers() {
        String sql = " SELECT * FROM public.users;";
        try {
            List<User> users = new ArrayList<>();
            Connection conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            //ResultSet работает по строчно
            if (!rs.isClosed()) {
                while (rs.next()) {
                    User user = new User(
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getString("phone")
                    );
                    user.setId(rs.getLong("id"));
                    user.setCreatedAt(rs.getTimestamp("created_at"));
                    user.setUpdatedAt(rs.getTimestamp("updated_at"));
                    users.add(user);
                }
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось подключиться к БД:" + e.getMessage());
        }
    }

    public User createUser(User user) {
        String sql = "INSERT INTO public.users (name, surname, phone) VALUES (?,?,?) RETURNING id;";
        try {
            Connection conn = getDatasourceConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getPhone());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Long id = rs.getLong("id");
                user.setId(id);
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось подключиться к БД:" + e.getMessage());
        }
    }


}
