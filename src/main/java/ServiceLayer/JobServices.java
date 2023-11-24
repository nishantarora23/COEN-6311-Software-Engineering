package ServiceLayer;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;

import Helper.JobScraper;
import PersistenceLayer.ProfileDAO;
import PersistenceLayer.SearchDAO;

public class JobServices {

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

	public static void addSearchHistory(String searchKeywords, String location, int id) {
		SearchDAO.insertSearchRecord(id, searchKeywords,location);	
	}

	public static JSONArray getSearchHistory(int id) {
		ArrayList<String> values = SearchDAO.getSearchRecord(id);	
		if(values.size()>0)
			return searchJobs(values.get(0), values.get(1));
		else
			return new JSONArray();
	}
}
