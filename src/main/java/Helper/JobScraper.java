package Helper;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class JobScraper {
	private static String url = "https://www.linkedin.com/jobs-guest/jobs/api/seeMoreJobPostings/search?";

	public static JSONArray getJobThroughKeywordsAndLocation(ArrayList<String> keywords, ArrayList<String> locations,boolean calulateScore) throws Exception{
		url = "https://www.linkedin.com/jobs-guest/jobs/api/seeMoreJobPostings/search?";
		prepareURL(keywords,locations);

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

			if(calulateScore) {
				int score = CalculateScore.calculateJobScore(jobTitle, company, location, keywords, locations);
				jobObject.put("Score", score);
			}

			jobArray.put(jobObject);
		}
		return jobArray;
	}


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

	// Helper method to process keywords and locations
	private static List<String> processKeywords(List<String> input) {
		List<String> processedKeywords = new ArrayList<>();

		for (String keyword : input) {
			// Replace spaces with %20 and remove double quotes
			String processedKeyword = keyword.replaceAll(" ", "%20").replaceAll("\"", "");

			processedKeywords.add(processedKeyword);
		}

		return processedKeywords;
	}


	public static void main(String[] args) throws Exception {
		String url1 = "https://www.linkedin.com/jobs-guest/jobs/api/seeMoreJobPostings/search?keywords=python&location=United%20States&start=25";
		Document document = Jsoup.connect(url1)
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36")
				.get();

		Elements jobListings = document.select(".base-card"); // Use the appropriate CSS selector for job listings

		JSONArray jobArray = new JSONArray();

		for (Element jobListing : jobListings) {
			String jobTitle = jobListing.select(".base-search-card__title").text();
			String company = jobListing.select(".base-search-card__subtitle a").text();
			String location = jobListing.select(".job-search-card__location").text();
			String applyLink = jobListing.select(".base-card__full-link").attr("href");
			ArrayList<String> arr = new ArrayList<>();arr.add("python");
			ArrayList<String> arr1 = new ArrayList<>();arr.add("United States");
			int score = CalculateScore.calculateJobScore(jobTitle, company, location, arr, arr1);


			JSONObject jobObject = new JSONObject();
			jobObject.put("Job Title", jobTitle);
			jobObject.put("Company", company);
			jobObject.put("Location", location);
			jobObject.put("Apply Link", applyLink);
			jobObject.put("Score", score);
			jobArray.put(jobObject);
		}
		JSONObject result = new JSONObject();
		result.put("Job Listings", jobArray);

		// You can now convert the result JSON to a string or do whatever you want with it
		String jsonString = result.toString();
		System.out.println(jsonString);
	}
}

