package be.howest.jwt.o3.data;

import javax.sql.DataSource;

/**
 *
 * @author Hayk
 */
class AbstractRepository {
    public static final String JNDI_NAME = "jdbc/jwto3db";
    
    protected DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
