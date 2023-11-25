package ServiceLayer;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import Models.User;
import PersistenceLayer.UserDAO;

/**
 * The UserServices class provides methods for interacting with user-related functionality.
 * It includes methods for creating and updating user profiles.
 *
 * @author Merlin Abraham
 * @version 1.0
 */
public class UserServices {

	/**
     * Creates a new user based on the provided JSON payload and job looking for information.
     *
     * @param jsonPayload The JSON payload containing user information.
     * @param jobLookingFor The job looking for information.
     * @return The ID of the created user or -1 if an error occurred.
     */
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
	/**
     * Updates an existing user based on the provided JSON payload.
     *
     * @param jsonPayload The JSON payload containing updated user information.
     * @param userID The ID of the user to update.
     * @return The number of rows updated in the database or -1 if an error occurred.
     */
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
