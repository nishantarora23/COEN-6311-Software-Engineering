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
		int id = jsonPayload.get("id").getAsInt();

		// API call /search
		if (requestURL.contains("/search")) {
			String keywords = jsonPayload.get("keywords").getAsString();
			String location = jsonPayload.get("location").getAsString();

			arr= JobServices.searchJobs(keywords, location);
			if(arr.length()>0) {

				JobServices.addSearchHistory(keywords,location, id);				
			}
		} 
		// API call /lastsearch
		else if(requestURL.contains("/lastSearch")) {
			arr = JobServices.getSearchHistory(id);
		}
		else {
			arr = JobServices.getRecommendedJobs(id);
		}
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(arr.toString());
		out.close();
	}

}
