package Controller;

import Models.User;
import PersistenceLayer.UserDAO;
import ServiceLayer.LoginServices;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Helper.Helper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * LoginServlet is a servlet class that handles HTTP POST requests for user authentication.
 * It provides an API endpoint for user login, authenticating the provided username and password.
 *
 * @version 1.0
 * @author Merlin Abraham
 */
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles HTTP POST requests for user login. It receives user credentials from the client,
     * authenticates the user, and returns the user information in JSON format.
     *
     * @param request  The HttpServletRequest object representing the client's request.
     * @param response The HttpServletResponse object for sending the response to the client.
     * @throws IOException If an input or output error occurs during the processing of the request.
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Extract payload data from the request
        String payloadData = Helper.getPayload(request);
        User user = null;
        JsonObject jsonPayload = new Gson().fromJson(payloadData, JsonObject.class);

        // Authenticate user based on provided username and password
        user = LoginServices.authenticate(
                jsonPayload.get("username").getAsString(),
                jsonPayload.get("password").getAsString()
        );

        // Convert user object to JSON and send the response
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(user);
        System.out.println(jsonResponse);
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    /**
     * Handles HTTP GET requests. This method is used for simulating getting a user from the DAO
     * and sending user information in JSON format. It also sets CORS headers to allow requests
     * from your React app.
     *
     * @param request  The HttpServletRequest object representing the client's request.
     * @param response The HttpServletResponse object for sending the response to the client.
     * @throws IOException If an input or output error occurs during the processing of the request.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Simulate getting a user from the DAO
        User user = new User();
        user.setUsername("john_doe");
        user.setFullName("John");
        user.setEmail("john.doe@example.com");

        // Convert user object to JSON and send the response with CORS headers
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(user);

        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
