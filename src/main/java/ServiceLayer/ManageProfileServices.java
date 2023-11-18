package ServiceLayer;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;

import Helper.JobScraper;
import PersistenceLayer.ProfileDAO;

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

	public static boolean addJobLookingFor(int id, String desired_role) {
		return ProfileDAO.addJobLookingFor(id, desired_role);
	}

	public static boolean createProfile(int id, String location) {
		
		return ProfileDAO.createProfile(id, location);
		
	}

}
