package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Helper.Helper;
import ServiceLayer.JobServices;

/**
 * JobServlet is a servlet class that handles HTTP POST requests related to job searches.
 * It provides APIs for searching jobs, retrieving the last search history, and getting
 * recommended jobs based on user preferences.
 *
 * @version 1.0
 * @author Merlin Abraham
 */
public class JobServlet extends HttpServlet {

    /**
     * Handles HTTP POST requests and processes job-related actions based on the request URL.
     * Depending on the request URL ("/search", "/lastsearch", or default), it performs searches,
     * retrieves the last search history, or provides recommended jobs.
     *
     * @param request  The HttpServletRequest object representing the client's request.
     * @param response The HttpServletResponse object for sending the response to the client.
     * @throws ServletException If the servlet encounters difficulties while processing the request.
     * @throws IOException      If an input or output error occurs during the processing of the request.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Extract payload data from the request
        String payloadData = Helper.getPayload(request);
        JsonObject jsonPayload = new Gson().fromJson(payloadData, JsonObject.class);
        String requestURL = request.getRequestURI();
        JSONArray arr = new JSONArray();
        int id = jsonPayload.get("id").getAsInt();

        // API call /search
        if (requestURL.contains("/search")) {
            String keywords = jsonPayload.get("keywords").getAsString();
            String location = jsonPayload.get("location").getAsString();

            // Search for jobs based on keywords and location
            arr = JobServices.searchJobs(keywords, location);

            // Add the search history if jobs are found
            if (arr.length() > 0) {
                JobServices.addSearchHistory(keywords, location, id);
            }
        }
        // API call /lastsearch
        else if (requestURL.contains("/lastsearch")) {
            // Retrieve the last search history for the user
            arr = JobServices.getSearchHistory(id);
        } else {
            // API call for recommended jobs
            arr = JobServices.getRecommendedJobs(id);
        }

        // Set response type to JSON and send the response
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(arr.toString());
        out.close();
    }
}
