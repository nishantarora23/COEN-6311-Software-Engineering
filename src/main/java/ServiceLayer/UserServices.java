package ServiceLayer;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import Models.User;
import PersistenceLayer.UserDAO;

public class UserServices {

	public static int createUser(JsonObject jsonPayload, JsonElement jobLookingFor) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			User user = objectMapper.readValue(jsonPayload.toString(), User.class);
			int id = UserDAO.addUser(user);
			if(id>0) {
				//Add Job Looking For Information
				if(ManageProfileServices.createProfile(id,user.getCountry()) && 
						ManageProfileServices.addJobLookingFor(id,jobLookingFor.toString())) {
					return id;
				}
			}
			
		}catch (Exception e) {

			e.printStackTrace();
		}
		return -1;
		
	}
	public static int updateUser(JsonObject jsonPayload, int userID) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Convert the JSON object to a User object
            User updatedUser = objectMapper.readValue(jsonPayload.toString(), User.class);

            // Update the user in the database and return the number of rows updated
            return UserDAO.updateUser(updatedUser, userID);
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Indicate that an exception occurred
        }
    }
}
