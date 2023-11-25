package Helper;

import java.util.ArrayList;
/**
 * CalculateScore is a utility class that provides methods for calculating scores related to job recommendations.
 * It includes methods for calculating scores based on job title, company, and location.
 *
 * @version 1.0
 * @author Nishant Arora
 */
public class CalculateScore {

    /**
     * Calculates the overall job score based on job title, company, and location.
     *
     * @param jobTitle   The job title for which the score is calculated.
     * @param company    The company name for which the score is calculated.
     * @param location   The job location for which the score is calculated.
     * @param keywords   A list of keywords relevant to the user's preferences.
     * @param locations  A list of preferred job locations.
     * @return The calculated overall job score.
     */
    public static int calculateJobScore(String jobTitle, String company, String location, ArrayList<String> keywords, ArrayList<String> locations) {
        // Implement your scoring logic here based on jobTitle, company, location, keywords, and locations.
        // You can assign scores to different criteria and calculate a total score.
        int titleScore = calculateTitleScore(jobTitle, keywords);
        int companyScore = calculateCompanyScore(company, keywords);
        int locationScore = calculateLocationScore(location, locations);

        // Calculate total score (you can customize the formula)
        return titleScore + companyScore + locationScore;
    }

    /**
     * Calculates the score based on the job title and the list of keywords.
     *
     * @param jobTitle The job title for which the score is calculated.
     * @param keywords A list of keywords relevant to the user's preferences.
     * @return The calculated score for the job title.
     */
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

    /**
     * Calculates the score based on the company name and the list of keywords.
     *
     * @param company  The company name for which the score is calculated.
     * @param keywords A list of keywords relevant to the user's preferences.
     * @return The calculated score for the company name.
     */
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

    /**
     * Calculates the score based on the job location and the list of preferred locations.
     *
     * @param location  The job location for which the score is calculated.
     * @param locations A list of preferred job locations.
     * @return The calculated score for the job location.
     */
    private static int calculateLocationScore(String location, ArrayList<String> locations) {
        // Implement scoring logic for the job location
        // For example, you can check if the location is one of the specified locations.
        int score = 0;
        for (String keyword : locations) {
            if (location.toLowerCase().contains(keyword.toLowerCase())) {
                score++;
            }
        }
        return score;
    }
}
