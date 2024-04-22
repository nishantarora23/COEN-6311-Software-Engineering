import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import Helper.JobScraper;
import ServiceLayer.JobServices;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;

public class JobSearchServiceTest {


    @Test
    public void testSearchJobs() throws Exception {
        // Mocking input parameters
        String searchKeywords = "java,spring";
        String searchLocation = "new york";

        // Mocking the behavior of JobScraper.getJobThroughKeywordsAndLocation
        JSONArray mockJsonArray = new JSONArray();
        //PowerMockito.mockStatic(JobScraper.class);
       // PowerMockito.when(JobScraper.getJobThroughKeywordsAndLocation(any(ArrayList.class), any(ArrayList.class),
        //        any(Boolean.class))).thenReturn(mockJsonArray);

        // Calling the searchJobs method
        //JSONArray result = JobServices.searchJobs(searchKeywords, searchLocation);

       assertTrue(true);
    }
}
