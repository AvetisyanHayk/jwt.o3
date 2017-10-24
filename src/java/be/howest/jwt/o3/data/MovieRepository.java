package be.howest.jwt.o3.data;

import be.howest.jwt.o3.ex.DBException;
import be.howest.jwt.o3.genre.Movie;
import be.howest.jwt.o3.pagination.Page;
import be.howest.jwt.o3.pagination.PageSort;
import be.howest.jwt.o3.pagination.SortOrder;
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
public class MovieRepository extends AbstractRepository {

    private static final String SQL = "SELECT * FROM movie";
    private static final String SQL_READ = SQL + " WHERE id = ?";
    private static final String SQL_FIND_BY_YEAR = SQL + " WHERE year = ?";
    private static final String SQL_FIND_DISTINCT_YEARS = "SELECT DISTINCT year FROM movie"
            + " ORDER BY YEAR";
    public static final PageSort PAGE_SORT = new PageSort();
    static {
        PAGE_SORT.add("title", 2);
        PAGE_SORT.add("year", 3, SortOrder.DESC);
        PAGE_SORT.add("year", 2);
    }
    
    private static final String PAGE_SQL =
            "SELECT *, (SELECT count(*) FROM movie{filter}) as entitycount"
            + " FROM movie{filter}{page}";
    
    public Page<Movie> findAll(Page<Movie> page, FilterMap filterMap) {
        if (page == null) {
            page = new Page<>();
        }
        page.setPageSort(PAGE_SORT);
        String sql = PAGE_SQL.replace("{filter}", filterMap.toString())
                .replace("{page}", page.toString());
        try (Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                page.setTotalEntities(resultSet.getInt("entitycount"));
                page.add(build(resultSet));
            }
            while(resultSet.next()) {
                page.add(build(resultSet));
            }
            return page;
        } catch (SQLException ex) {
            throw new DBException(ex);
        }
    }

    public Movie read(long id) {
        try (Connection connection = dataSource.getConnection();
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
        try (Connection connection = dataSource.getConnection();
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

    public List<Integer> findDistinctYears() {
        List<Integer> years = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL_FIND_DISTINCT_YEARS)) {
            while (resultSet.next()) {
                years.add(resultSet.getInt("year"));
            }
        } catch (SQLException ex) {
            throw new DBException(ex);
        }
        return years;
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
