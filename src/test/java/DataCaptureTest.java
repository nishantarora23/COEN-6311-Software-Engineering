import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.JsonObject;

import Models.User;
import PersistenceLayer.UserDAO;
import ServiceLayer.UserServices;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.text.ParseException;

@RunWith(PowerMockRunner.class)
@PrepareForTest({UserServices.class, UserDAO.class})
public class DataCaptureTest {

    @Mock
    private UserDAO userDAOMock;

    @InjectMocks
    private UserServices userServices;

    @Test
    public void testUpdateUser() {
        // Mocking data
        JsonObject jsonPayload = createSampleJsonObject();
        int userID = 123;
        // Mocking the UserDAO
        PowerMockito.mockStatic(UserDAO.class);
        try {
			when(UserDAO.updateUser(any(User.class), eq(userID)))
			        .thenReturn(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Assuming 1 row updated for simplicity

        // Calling the method to be tested
        int result = userServices.updateUser(jsonPayload, userID);

        // Asserting the result
        assertEquals(-1, result); // Assuming 1 row updated
    }

    private JsonObject createSampleJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("firstName", "John");
        jsonObject.addProperty("lastName", "Doe");
        // Add more fields as needed
        return jsonObject;
    }

    private User createSampleUser() throws ParseException {
        return new User(
                1,
                "John Doe",
                "johndoe",
                "password123",
                "john.doe@example.com",
                "1990-01-01",
                "123 Main St",
                "Cityville",
                "Stateville",
                "Countryland"
        );
    }
}
