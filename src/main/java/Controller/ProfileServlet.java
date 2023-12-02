package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Helper.Helper;
import ServiceLayer.ManageProfileServices;

/**
 * ProfileServlet is a servlet class that handles HTTP POST requests related to user profiles.
 * It provides endpoints for managing user profiles, such as adding skills and updating locations.
 *
 * @version 1.0
 * @author Merlin Abraham
 */
public class ProfileServlet extends HttpServlet {

    private static final long serialVersionUID = -4872038924539756016L;

    /**
     * Handles HTTP POST requests for managing user profiles. It receives payload data from the client,
     * processes the data, and performs actions related to user profiles, such as adding skills or updating locations.
     *
     * @param request  The HttpServletRequest object representing the client's request.
     * @param response The HttpServletResponse object for sending the response to the client.
     * @throws ServletException If an exception occurs that interrupts the servlet's normal operation.
     * @throws IOException      If an input or output error occurs during the processing of the request.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Extract payload data from the request
        String payloadData = Helper.getPayload(request);
        JsonObject jsonPayload = new Gson().fromJson(payloadData, JsonObject.class);

        // Perform actions related to user profiles (e.g., add skills, update locations)
        // Uncomment the following line or add relevant code based on the actual functionality.
        // JSONArray array = ManageProfileServices.addSkills(
        //     jsonPayload.get("id").getAsString(),
        //     jsonPayload.get("skills").getAsString(),
        //     jsonPayload.get("location").getAsString()
        // );
    }
}
