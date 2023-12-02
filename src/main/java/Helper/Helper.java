package Helper;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Helper is a utility class that provides various helper methods for common tasks.
 *
 * @version 1.0
 * @author Nishant Arora
 */
public class Helper {

    /** The JDBC URL for connecting to the database. */
    public static String url;

    /** The username for connecting to the database. */
    public static String uname;

    /** The password for connecting to the database. */
    public static String pass;

    // Static block to initialize database connection parameters
    static {
        url = "jdbc:mysql://127.0.0.1:3306/COEN6311";
        uname = "root";
        pass = "admin123";
        System.out.println(url + "  " + uname + "  " + pass);
    }

    /**
     * Retrieves the payload (body) of an HTTP request.
     *
     * @param request The HttpServletRequest object representing the HTTP request.
     * @return The payload of the HTTP request as a string.
     * @throws IOException If an I/O error occurs while reading the payload.
     */
    public static String getPayload(HttpServletRequest request) throws IOException {
        StringBuilder payload = new StringBuilder();
        BufferedReader reader = request.getReader();

        String line;
        while ((line = reader.readLine()) != null) {
            payload.append(line);
        }
        return payload.toString();
    }
}
