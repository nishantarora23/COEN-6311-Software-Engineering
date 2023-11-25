package ServiceLayer;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;

import Helper.JobScraper;
import PersistenceLayer.ProfileDAO;
import PersistenceLayer.SearchDAO;

/**
 * The JobServices class provides methods for interacting with job-related functionality.
 * It includes methods for retrieving recommended jobs, searching for jobs, and managing search history.
 *
 * @author Merlin Abraham
 * @version 1.0
 */
public class JobServices {

	/**
     * Retrieves recommended jobs for a user based on their profile information.
     *
     * @param id The ID of the user.
     * @return A JSONArray containing recommended job listings.
     */
	public static JSONArray getRecommendedJobs(int id) {

		//get details from DB based on id	
		ArrayList<String> values = ProfileDAO.getValuesFromId(id);
		String keyword = values.get(1).replaceAll("\"","");
		ArrayList<String> keywords = new ArrayList<>(Arrays.asList(keyword.split(",")));
		ArrayList<String> location = new ArrayList<>(Arrays.asList(values.get(2).split(",")));

		//scrap jobs
		JSONArray jsonArray = null;
		try {
			jsonArray= JobScraper.getJobThroughKeywordsAndLocation(keywords, location, false);
			return jsonArray;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonArray;

	}
	 /**
     * Searches for jobs based on specified keywords and location.
     *
     * @param searchKeywords The keywords to search for in job listings.
     * @param searchLocation The location to search for jobs.
     * @return A JSONArray containing job listings matching the search criteria.
     */
	public static JSONArray searchJobs(String searchKeywords, String searchLocation) {

		ArrayList<String> keywords = new ArrayList<>(Arrays.asList(searchKeywords.split(",")));
		ArrayList<String> location = new ArrayList<>(Arrays.asList(searchLocation.split(",")));

		//scrap jobs
		JSONArray jsonArray = null;
		try {
			jsonArray= JobScraper.getJobThroughKeywordsAndLocation(keywords, location, false);
			return jsonArray;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonArray;

	}
	/**
     * Adds a search record to the user's search history.
     *
     * @param searchKeywords The keywords used in the search.
     * @param location The location used in the search.
     * @param id The ID of the user.
     */

	public static void addSearchHistory(String searchKeywords, String location, int id) {
		SearchDAO.insertSearchRecord(id, searchKeywords,location);	
	}
	/**
     * Retrieves the search history for a user and returns the corresponding job listings.
     *
     * @param id The ID of the user.
     * @return A JSONArray containing job listings from the user's search history.
     */
	public static JSONArray getSearchHistory(int id) {
		ArrayList<String> values = SearchDAO.getSearchRecord(id);	
		if(values.size()>0)
			return searchJobs(values.get(0), values.get(1));
		else
			return new JSONArray();
	}
}
