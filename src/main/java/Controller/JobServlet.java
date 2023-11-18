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

public class JobServlet extends HttpServlet{


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String payloadData = Helper.getPayload(request);
		JsonObject jsonPayload = new Gson().fromJson(payloadData, JsonObject.class);


		String requestURL = request.getRequestURI();
		JSONArray arr = new JSONArray();
		// Check if the URL contains "/search"
		if (requestURL.contains("/search")) {
			String keywords = jsonPayload.get("keywords").getAsString();
			String location = jsonPayload.get("location").getAsString();
			arr= JobServices.searchJobs(keywords, location);
		} 
		else {
			int id = jsonPayload.get("id").getAsInt();
			arr = JobServices.getRecommendedJobs(id);
		}
		// Set the response content type to JSON
		response.setContentType("application/json");

		// Get the PrintWriter to write the response
		PrintWriter out = response.getWriter();

		// Write the JSON array to the response
		out.print(arr.toString());

		// Close the PrintWriter
		out.close();
	}


}
