package be.howest.jwt.o3.data;

import be.howest.jwt.o3.ex.DBException;
import be.howest.jwt.o3.genre.Genre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hayk
 */
public class GenreRepository extends AbstractRepository {

    private static final String SQL = "SELECT * FROM genre";
    private static final String SQL_READ = SQL + " WHERE id = ?";
    private static final String SQL_FIND_ALL = SQL + " ORDER BY name";
    
    public Genre read(long id) {
        try(Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_READ)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return build(resultSet);
                }
            }
        } catch (SQLException ex) {
            throw new DBException(ex);
        }
        return null;
    }
    
    public List<Genre> findAll() {
        List<Genre> entities = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL)) {
            while (resultSet.next()) {
                entities.add(build(resultSet));
            }
        } catch (SQLException ex) {
            throw new DBException(ex);
        }
        return entities;
    }
    
    private Genre build(ResultSet resultSet) throws SQLException {
        return new Genre(
                resultSet.getLong("id"),
                resultSet.getString("name")
        );
    }

}
