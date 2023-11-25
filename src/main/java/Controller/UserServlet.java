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

/**
 * UserServlet is a servlet class that handles HTTP POST requests related to user management.
 * It provides an endpoint for creating a new user with the specified information.
 *
 * @version 1.0
 * @author Merlin Abraham
 */
public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Handles HTTP POST requests for creating a new user. It receives payload data from the client,
     * processes the data, and invokes the necessary service methods to create a new user.
     * It then sends a response indicating the success or failure of the user creation process.
     *
     * @param request  The HttpServletRequest object representing the client's request.
     * @param response The HttpServletResponse object for sending the response to the client.
     * @throws IOException If an input or output error occurs during the processing of the request.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Extract payload data from the request
        String payloadData = Helper.getPayload(request);
        JsonObject jsonPayload = new Gson().fromJson(payloadData, JsonObject.class);

        // Remove unnecessary keys from the payload
        JsonElement desiredRole = null;
        if (jsonPayload.has("preferredRoles")) {
            desiredRole = jsonPayload.remove("preferredRoles");
        }
        if (jsonPayload.has("confirmPassword")) {
            jsonPayload.remove("confirmPassword");
        }

        // Create a new user and get the user ID
        int id = UserServices.createUser(jsonPayload, desiredRole);

        // Prepare the JSON response
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
