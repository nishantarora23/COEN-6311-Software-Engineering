package Helper;

import java.util.ArrayList;

public class CalculateScore {

	public static int calculateJobScore(String jobTitle, String company, String location, ArrayList<String> keywords, ArrayList<String> locations) {
        // Implement your scoring logic here based on jobTitle, company, location, keywords, and locations.
        // You can assign scores to different criteria and calculate a total score.
        int titleScore = calculateTitleScore(jobTitle, keywords);
        int companyScore = calculateCompanyScore(company, keywords);
        int locationScore = calculateLocationScore(location, locations);

        // Calculate total score (you can customize the formula)
        return titleScore + companyScore + locationScore;
    }
	private static int calculateTitleScore(String jobTitle, ArrayList<String> keywords) {
        // Implement scoring logic for job title
        // For example, you can count how many keywords are present in the job title.
        int score = 0;
        for (String keyword : keywords) {
            if (jobTitle.toLowerCase().contains(keyword.toLowerCase())) {
                score++;
            }
        }
        return score;
    }
	private static int calculateCompanyScore(String company, ArrayList<String> keywords) {
        // Implement scoring logic for the company name
        // For example, you can count how many keywords are present in the company name.
        int score = 0;
        for (String keyword : keywords) {
            if (company.toLowerCase().contains(keyword.toLowerCase())) {
                score++;
            }
        }
        return score;
    }
	private static int calculateLocationScore(String location, ArrayList<String> locations) {
        // Implement scoring logic for the job location
        // For example, you can check if the location is one of the specified locations.
        return locations.contains(location) ? 1 : 0;
    }
}
