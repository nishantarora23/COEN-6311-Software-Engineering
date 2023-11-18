package Controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import Helper.Helper;
import ServiceLayer.UserServices;

public class UserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    String payloadData = Helper.getPayload(request);
	    JsonObject jsonPayload = new Gson().fromJson(payloadData, JsonObject.class);
	    
	 // Remove the "jobLookingFor" key if it exists
	    JsonElement desired_role = null;
        if (jsonPayload.has("preferredRoles")) {
        	desired_role = jsonPayload.remove("preferredRoles");
        }
        if (jsonPayload.has("confirmPassword")) {
        	 jsonPayload.remove("confirmPassword");
        }
	    int id = UserServices.createUser(jsonPayload,desired_role);

	    JsonObject jsonResponse = new JsonObject();

	    if (id == -1) {
	        // If id is -1, send an error response
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // You can choose a different error code if needed
	        jsonResponse.addProperty("status", "error");
	        jsonResponse.addProperty("message", "An error occurred during user creation.");
	    } else {
	        // If id is not -1, send a success response with the ID     
	        jsonResponse.addProperty("id", id);
	        response.setStatus(HttpServletResponse.SC_OK);
	    }

	    // Set the response content type to JSON
	    response.setContentType("application/json");

	    // Convert the JSON response to a string and write it to the response body
	    response.getWriter().write(new Gson().toJson(jsonResponse));
	}


}
