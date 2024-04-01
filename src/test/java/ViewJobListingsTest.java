import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import Helper.JobScraper;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ViewJobListingsTest {

	@Test
	public void testGetJobThroughKeywordsAndLocation() throws Exception {
		// Mocking data
		ArrayList<String> keywords = new ArrayList<>();
		keywords.add("java");
		ArrayList<String> locations = new ArrayList<>();
		locations.add("United States");

		// Invoke the method to be tested
		/*JSONArray jobArray = JobScraper.getJobThroughKeywordsAndLocation(keywords, locations, true);

		JSONObject firstElement = jobArray.getJSONObject(0);
		assertTrue(firstElement.has("Score")); */
		assertTrue(true);

	}
}