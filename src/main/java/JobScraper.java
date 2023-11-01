import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JobScraper {
    public static void main(String[] args) throws Exception {
        String url = "https://www.linkedin.com/jobs-guest/jobs/api/seeMoreJobPostings/search?keywords=python&location=United%20States&start=25";
        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36")
                .get();

        Elements jobListings = document.select(".base-card"); // Use the appropriate CSS selector for job listings

        for (Element jobListing : jobListings) {
            String jobTitle = jobListing.select(".base-search-card__title").text();
            String company = jobListing.select(".base-search-card__subtitle a").text();
            String location = jobListing.select(".job-search-card__location").text();
            String applyLink = jobListing.select(".base-card__full-link").attr("href");

            System.out.println("Job Title: " + jobTitle);
            System.out.println("Company: " + company);
            System.out.println("Location: " + location);
            System.out.println("Apply Link: " + applyLink);
            System.out.println();
        }
    }
}
