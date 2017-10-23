package be.howest.jwt.o3.data;

import be.howest.jwt.o3.ex.DBException;
import be.howest.jwt.o3.genre.Movie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hayk
 */
public class MovieRepository extends AbstractRepository {
    
    private static final String SQL = "SELECT * FROM movie";
    private static final String SQL_READ = SQL + " WHERE id = ?";
    private static final String SQL_FIND_BY_YEAR = SQL + " WHERE year = ?";
    
    public Movie read(long id) {
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
    
    public List<Movie> findByYear(int year) {
        List<Movie> entities = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_YEAR)) {
            statement.setInt(1, year);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    entities.add(build(resultSet));
                }
            }
        } catch (SQLException ex) {
            throw new DBException(ex);
        }
        return entities;
    }
 
    private Movie build(ResultSet resultSet) throws SQLException {
        return new Movie(
                resultSet.getLong("id"),
                resultSet.getString("title"),
                resultSet.getInt("year"),
                resultSet.getInt("rating")
        );
    }
}
