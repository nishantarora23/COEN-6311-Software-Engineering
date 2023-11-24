
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import Models.User;
import PersistenceLayer.UserDAO;
import ServiceLayer.ManageProfileServices;
import ServiceLayer.UserServices;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({UserDAO.class, ManageProfileServices.class, UserServices.class, com.google.gson.JsonObject.class})
public class ManageUserProfileTest {

    @Mock
    private JsonObject mockJsonPayload;

    @Mock
    private JsonElement mockJobLookingFor;

    @Mock
    private User mockUser;

    @InjectMocks
    private UserServices yourServiceClass;


    @Test
    public void testManageUserProfile() throws Exception {
        // Mocking static method behavior
        PowerMockito.mockStatic(UserDAO.class);
        PowerMockito.mockStatic(ManageProfileServices.class);

        // Mocking the behavior of non-static methods
        when(mockJsonPayload.toString()).thenReturn("{\"username\":\"testUser\",\"password\":\"testPassword\"}");
        //when(new ObjectMapper().readValue(mockJsonPayload.toString(), User.class)).thenReturn(mockUser);
        when(UserDAO.addUser(mockUser)).thenReturn(1);
        when(ManageProfileServices.createProfile(1, "country")).thenReturn(true);
        when(ManageProfileServices.addJobLookingFor(1, mockJobLookingFor.toString())).thenReturn(true);

        // Calling the method to be tested
        int result = yourServiceClass.createUser(mockJsonPayload, mockJobLookingFor);

        // Verifying the result
        assertEquals(-1, result);
    }
}
