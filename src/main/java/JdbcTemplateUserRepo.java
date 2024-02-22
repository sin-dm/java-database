import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplateUserRepo extends Database {

    private final String sqlGetUserById = "SELECT * FROM public.users WHERE id = :id;";

    private final String sqlDeleteUserById = "DELETE FROM public.users WHERE id = ?;";

    public User getUserById(Long id) {
        return namedParameterJdbcTemplate().queryForObject(
                sqlGetUserById,
                new MapSqlParameterSource()
                        .addValue("id", id),
                new UserRowMapper());
    }

    public Integer deleteUserById(Long id) {
        return jdbcTemplate().update(sqlDeleteUserById, id);
    }

    public class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User(rs.getString("name"), rs.getString("surname"), rs.getString("phone"));
            user.setId(rs.getLong("id"));
            user.setCreatedAt(rs.getTimestamp("created_at"));
            user.setUpdatedAt(rs.getTimestamp("updated_at"));
            return user;
        }
    }

}
