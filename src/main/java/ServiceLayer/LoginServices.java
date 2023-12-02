package ServiceLayer;

import Models.User;
import PersistenceLayer.UserDAO;

/**
 * The LoginServices class provides methods for user authentication during the login process.
 * It interacts with the UserDAO class to retrieve user information from the database.
 *
 * @author Nishant Arora
 * @version 1.0
 */
public class LoginServices {

	 /**
     * Authenticates a user by verifying the provided username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return A User object representing the authenticated user or null if authentication fails.
     */
	public static User authenticate(String username, String password) {
		
		return UserDAO.getUser(username, password);
	}

}
