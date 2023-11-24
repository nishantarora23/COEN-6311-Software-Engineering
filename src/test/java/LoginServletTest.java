import Models.User;
import PersistenceLayer.UserDAO;
import ServiceLayer.LoginServices;

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
@PrepareForTest(UserDAO.class)
public class LoginServletTest {

    @Mock
    private UserDAO mockUserDAO;

    @InjectMocks
    private LoginServices loginServices;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAuthenticateValidUser() throws Exception {
        // Mocking static method behavior
        String username = "testUser";
        String password = "testPassword";
        User expectedUser = new User();
        expectedUser.setUsername(username);
        // Set other user properties as needed

        PowerMockito.mockStatic(UserDAO.class);
        when(UserDAO.getUser(username, password)).thenReturn(expectedUser);

        // Calling the method to be tested
        User actualUser = loginServices.authenticate(username, password);

        // Verifying the result
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testAuthenticateInvalidUser() throws Exception {
        // Mocking static method behavior for an invalid user
        String username = "invalidUser";
        String password = "invalidPassword";

        PowerMockito.mockStatic(UserDAO.class);
        when(UserDAO.getUser(username, password)).thenReturn(null);

        // Calling the method to be tested
        User actualUser = loginServices.authenticate(username, password);

        // Verifying the result
        assertEquals(null, actualUser);
    }
}
