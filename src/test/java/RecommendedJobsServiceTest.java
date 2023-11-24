import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


import PersistenceLayer.ProfileDAO;
import ServiceLayer.JobServices;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ProfileDAO.class, ArrayList.class, Arrays.class})
@PowerMockIgnore({"java.util.*", "java.net.*"})
public class RecommendedJobsServiceTest{

    @Mock
    private ProfileDAO profileDAO;


    @Test
    public void testGetRecommendedJobs() throws Exception {
    	 // Mocking input parameters
        int id = 1;
        ArrayList arr = new ArrayList();
        arr.add("John Doe");
        arr.add("Java");
        arr.add("India");
        PowerMockito.mockStatic(ProfileDAO.class);
        PowerMockito.when(ProfileDAO.getValuesFromId(anyInt())).thenReturn(arr);

        // Calling the getRecommendedJobs method
        JSONArray result = JobServices.getRecommendedJobs(1);
        
        assertTrue(result.length()>0);

    }
}
