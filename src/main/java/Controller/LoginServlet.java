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

public class LoginServlet extends HttpServlet {
    // Handle POST requests for user login

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String payloadData = Helper.getPayload(request);
        User user = null;
        JsonObject jsonPayload = new Gson().fromJson(payloadData, JsonObject.class);

        user= LoginServices.authenticate(jsonPayload.get("username").getAsString(), jsonPayload.get("password").getAsString());
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(user);
        System.out.println(jsonResponse);
	    response.setContentType("application/json");
	    response.getWriter().write(jsonResponse);
	    response.setStatus(HttpServletResponse.SC_OK);
	    
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    // Simulate getting a user from the DAO
	    User user = new User();
	    user.setUsername("john_doe");
	    user.setFullName("John");
	    user.setEmail("john.doe@example.com");

	    Gson gson = new Gson();
	    String jsonResponse = gson.toJson(user);

	    // Set CORS headers to allow requests from your React app
	    response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
	    response.setHeader("Access-Control-Allow-Credentials", "true"); // If you need to send cookies

	    response.setContentType("application/json");
	    response.getWriter().write(jsonResponse);
	    response.setStatus(HttpServletResponse.SC_OK);
	}


}
