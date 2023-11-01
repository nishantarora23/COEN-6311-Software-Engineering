package ServiceLayer;

import Models.User;
import PersistenceLayer.UserDAO;

public class LoginServices {

	public static User authenticate(String username, String password) {
		
		return UserDAO.getUser(username, password);
	}

}
