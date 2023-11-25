package ServiceLayer;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;

import Helper.JobScraper;
import PersistenceLayer.ProfileDAO;

/**
 * The ManageProfileServices class provides methods for managing user profiles, including adding skills,
 * specifying job preferences, and creating user profiles. It interacts with the ProfileDAO class to
 * perform database operations related to user profiles.
 *
 * @author Nishant Arora
 * @version 1.0
 */
public class ManageProfileServices {

//	public static JSONArray addSkills(String id, String skills, String location) {
//		int id_1 = Integer.parseInt(id);
//		String[] skillSet = skills.split(" ");
//		String[] locationSet = location.split(" ");
//
//
//		//boolean addedSkills = ProfileDAO.createProfile(id_1,String.join(",", skillSet),String.join(",",locationSet));
//		if(addedSkills) {
//			try {
//				JobScraper.getJobThroughKeywordsAndLocation
//				(new ArrayList<>(Arrays.asList(skillSet)), 
//						new ArrayList<>(Arrays.asList(locationSet)));
//			} catch (Exception e) {			
//				e.printStackTrace();
//			}
//		}
//		return null;
//	}
	
	 /**
     * Adds the desired job role to the user's profile.
     *
     * @param id            The user ID.
     * @param desired_role The desired job role.
     * @return true if the job role is successfully added to the profile, false otherwise.
     */
	public static boolean addJobLookingFor(int id, String desired_role) {
		return ProfileDAO.addJobLookingFor(id, desired_role);
	}
	/**
     * Creates a user profile with the specified location information.
     *
     * @param id       The user ID.
     * @param location The location information to be associated with the profile.
     * @return true if the profile is successfully created, false otherwise.
     */
	public static boolean createProfile(int id, String location) {
		
		return ProfileDAO.createProfile(id, location);
		
	}

}
