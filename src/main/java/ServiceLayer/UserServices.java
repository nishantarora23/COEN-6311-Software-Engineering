package ServiceLayer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

import Models.User;
import PersistenceLayer.UserDAO;

public class UserServices {

	public static boolean createUser(JsonObject jsonPayload) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			User user = objectMapper.readValue(jsonPayload.toString(), User.class);
			return UserDAO.addUser(user);
		}catch (Exception e) {

			e.printStackTrace();
		}
		return false;
		
	}

}
