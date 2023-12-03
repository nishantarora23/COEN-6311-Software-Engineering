package PersistenceLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Helper.Helper;

/**
 * The DatabaseInstance class is responsible for managing the singleton instance
 * of the Connection class.
 *
 * @author Nishant Arora
 * @version 1.0
 */
public class DatabaseInstance {

    private static Connection instance;

    private DatabaseInstance() {
    }

    /**
     * Returns a database connection.
     *
     * @return A Connection object.
     * @throws SQLException if a database access error occurs.
     */
    public static Connection getDatabaseConnection() throws SQLException {
        if (instance == null) {
        	 try {
                 Class.forName("com.mysql.cj.jdbc.Driver");
             } catch (ClassNotFoundException e) {
                 throw new RuntimeException(e);
             }
        	String connectionUrl = Helper.url + "?useSSL=false";
            instance = DriverManager.getConnection(connectionUrl, Helper.uname, Helper.pass);
        }
        return instance;
    }
}
