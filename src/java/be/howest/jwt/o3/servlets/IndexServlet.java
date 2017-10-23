package be.howest.jwt.o3.servlets;

import be.howest.jwt.o3.data.GenreRepository;
import be.howest.jwt.o3.data.MovieRepository;
import be.howest.jwt.o3.genre.Genre;
import be.howest.jwt.o3.genre.Movie;
import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Hayk
 */
@WebServlet("/index.htm")
public class IndexServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private final transient GenreRepository genreRepo = new GenreRepository();
    private final transient MovieRepository movieRepo = new MovieRepository();
    
    @Resource(name = GenreRepository.JNDI_NAME)
    void setDataSource(DataSource dataSource) {
        genreRepo.setDataSource(dataSource);
        movieRepo.setDataSource(dataSource);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Genre> genres = genreRepo.findAll();
        Movie movie = movieRepo.read(1L);
        List<Movie> movies = movieRepo.findByYear(2014);
        String genresString = genres.stream().map(Genre::toString)
                .reduce("", (previous, current) -> previous + "\n" + current);
        String moviesString = movies.stream().map(Movie::toString)
                .reduce("", (previous, current) -> previous + "\n" + current);
        response.getWriter().append(movie.toString())
                .append(genresString)
                .append(moviesString).println();
    }
    
    private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
        throw new java.io.NotSerializableException(getClass().getName());
    }

    private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
        throw new java.io.NotSerializableException(getClass().getName());
    }

}
