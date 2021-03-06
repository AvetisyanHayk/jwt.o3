package be.howest.jwt.o3.servlets;

import be.howest.jwt.o3.data.FilterMap;
import be.howest.jwt.o3.data.GenreRepository;
import be.howest.jwt.o3.data.MovieRepository;
import be.howest.jwt.o3.data.SingleFilter;
import be.howest.jwt.o3.data.SingleFilterOperator;
import be.howest.jwt.o3.genre.Movie;
import be.howest.jwt.o3.pagination.Page;
import be.howest.jwt.o3.pagination.Pagination;
import java.io.IOException;
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

    private static final String VIEW = "/WEB-INF/JSP/index.jsp";
    
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
        request.setAttribute("sortOrderKeys", MovieRepository.PAGE_SORT.getKeys());
        request.setAttribute("perPageTemplates", Pagination.PAGE_OFFSET_LIST);
        request.setAttribute("years", movieRepo.findDistinctYears());
        request.setAttribute("genres", genreRepo.findAll());
        request.getRequestDispatcher(VIEW).forward(request, response);
    }
    
    private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
        throw new java.io.NotSerializableException(getClass().getName());
    }

    private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
        throw new java.io.NotSerializableException(getClass().getName());
    }

}
