package Helper;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import PersistenceLayer.SearchDAO;

/**
 * JobScraper is a utility class that provides methods for scraping job listings from LinkedIn
 * based on keywords and locations. It also calculates a score for each job listing.
 *
 * @version 1.0
 * @author Nishant Arora
 */
public class JobScraper {

	/** The base URL for LinkedIn job search. */
	private static String url = "https://www.linkedin.com/jobs-guest/jobs/api/seeMoreJobPostings/search?";

	/** The file path for storing JSON files. */
	private static final String JSON_FILE_PATH = "/WEB-INF/json_files/";

	/**
	 * Retrieves job listings from LinkedIn based on keywords and locations.
	 *
	 * @param keywords The list of keywords for job search.
	 * @param locations The list of locations for job search.
	 * @param calculateScore A flag indicating whether to calculate scores for job listings.
	 * @return A JSONArray containing job listings retrieved from LinkedIn.
	 * @throws Exception If an error occurs during the scraping process.
	 */
	public static JSONArray getJobThroughKeywordsAndLocation(
			ArrayList<String> keywords, ArrayList<String> locations, boolean calculateScore) throws Exception {
		url = "https://www.linkedin.com/jobs-guest/jobs/api/seeMoreJobPostings/search?";
		prepareURL(keywords, locations);
		Document document = Jsoup.connect(url)
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36")
				.get();

		Elements jobListings = document.select(".base-card"); // Use the appropriate CSS selector for job listings

		JSONArray jobArray = new JSONArray();

		for (Element jobListing : jobListings) {
			String jobTitle = jobListing.select(".base-search-card__title").text();
			String company = jobListing.select(".base-search-card__subtitle a").text();
			String location = jobListing.select(".job-search-card__location").text();
			String applyLink = jobListing.select(".base-card__full-link").attr("href");

			JSONObject jobObject = new JSONObject();
			jobObject.put("Job_Title", jobTitle);
			jobObject.put("Company", company);
			jobObject.put("Location", location);
			jobObject.put("Apply_Link", applyLink);

			int score = CalculateScore.calculateJobScore(jobTitle, company, location, keywords, locations);
			if (score == 2)
				jobObject.put("Score", 100);
			else if (score == 1)
				jobObject.put("Score", 50);
			else
				jobObject.put("Score", 0);

			// String jsonFileName = saveToJSONFile(jobArray);
			// SearchDAO.insertCacheRecord(String.join(",", keywords), String.join(",", locations), jsonFileName);

			jobArray.put(jobObject);
		}
		return jobArray;
	}

	/**
	 * Saves a JSONArray of job listings to a JSON file.
	 *
	 * @param jobArray The JSONArray containing job listings.
	 * @return The filename of the saved JSON file.
	 */
	private static String saveToJSONFile(JSONArray jobArray) {
		String jsonFileName = "jobs_" + System.currentTimeMillis() + ".json";
		String filePath = JSON_FILE_PATH + jsonFileName;

		try (FileWriter fileWriter = new FileWriter(filePath)) {
			fileWriter.write(jobArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonFileName;
	}

	/**
	 * Prepares the LinkedIn job search URL with keywords and locations.
	 *
	 * @param keywords The list of keywords for job search.
	 * @param locations The list of locations for job search.
	 */
	private static void prepareURL(ArrayList<String> keywords, ArrayList<String> locations) {
		StringBuilder urlBuilder = new StringBuilder(url);

		// Process keywords
		String joinedKeywords = String.join("&keywords=", processKeywords(keywords));
		urlBuilder.append("keywords=").append(joinedKeywords);
		url = urlBuilder.toString();

		// Process locations
		String locationKeywords = String.join("&location=", processKeywords(locations));
		if (url.endsWith("?")) {
			urlBuilder.append("location=").append(locationKeywords);
		} else {
			urlBuilder.append("&location=").append(locationKeywords);
		}

		url = urlBuilder.toString();
		System.out.println(url);
	}

	/**
	 * Processes keywords and locations for use in the URL.
	 *
	 * @param input The list of keywords or locations.
	 * @return The processed list of keywords or locations.
	 */
	private static List<String> processKeywords(List<String> input) {
		List<String> processedKeywords = new ArrayList<>();

		for (String keyword : input) {
			// Replace spaces with %20 and remove double quotes
			String processedKeyword = keyword.replaceAll(" ", "%20").replaceAll("\"", "");

			processedKeywords.add(processedKeyword);
		}

		return processedKeywords;
	}

}