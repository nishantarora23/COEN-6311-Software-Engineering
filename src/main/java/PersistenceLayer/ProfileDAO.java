package PersistenceLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Helper.Helper;

/**
 * The ProfileDAO class provides data access methods for interacting with the database related to user profiles.
 * It includes methods for creating, updating, and retrieving profile information.
 *
 * @author Nishant Arora
 * @version 1.0
 */
public class ProfileDAO {

    /**
     * Creates a profile for the user with the specified ID.
     *
     * @param id The unique identifier for the user.
     * @return True if the profile creation is successful; otherwise, false.
     */

	public static boolean createProfile(int id) {
		PreparedStatement statement = null;
		String insert_query = "INSERT INTO COEN6311.PROFILES (ID) VALUES (?)";
		try {
			Connection connection = DatabaseInstance.getDatabaseConnection();
			statement = connection.prepareStatement(insert_query);
			statement.setInt(1, id);

			int rowUpdated = statement.executeUpdate();
			return rowUpdated==1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(statement!=null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		return false;
	}
	/**
     * Creates a profile for the user with the specified ID and location.
     *
     * @param id The unique identifier for the user.
     * @param location The location of the user.
     * @return True if the profile creation is successful; otherwise, false.
     */
	public static boolean createProfile(int id, String location) {
		PreparedStatement statement = null;
		String insert_query = "INSERT INTO COEN6311.PROFILES (ID, LOCATION) VALUES (?, ?)";
		try {
			Connection connection = DatabaseInstance.getDatabaseConnection();
			statement = connection.prepareStatement(insert_query);
			statement.setInt(1, id);
			statement.setString(2, location);

			int rowUpdated = statement.executeUpdate();
			return rowUpdated==1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(statement!=null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		return false;
	}
	/**
     * Updates the skills information for the user with the specified ID.
     *
     * @param id The unique identifier for the user.
     * @param skills The skills information to be updated.
     * @return True if the update is successful; otherwise, false.
     */
	public static boolean updateSkills(int id, String skills) {
		PreparedStatement statement = null;
		String update_query = "UPDATE COEN6311.PROFILES SET SKILLS = ? WHERE ID = ?";
		try{
			Connection connection = DatabaseInstance.getDatabaseConnection();
			statement = connection.prepareStatement(update_query);
			statement.setInt(1, id);
			statement.setString(2, skills);


			int rowUpdated = statement.executeUpdate();
			return rowUpdated==1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(statement!=null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		return false;
	}
	/**
     * Updates the location information for the user with the specified ID.
     *
     * @param id The unique identifier for the user.
     * @param location The location information to be updated.
     * @return True if the update is successful; otherwise, false.
     */
	public static boolean updatLocation(int id, String location) {
		PreparedStatement statement = null;
		String update_query = "UPDATE COEN6311.PROFILES SET LOCATION = ? WHERE ID = ?";
		try{
			Connection connection = DatabaseInstance.getDatabaseConnection();
			statement.setInt(2, id);
			statement.setString(1, location);


			int rowUpdated = statement.executeUpdate();
			return rowUpdated==1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(statement!=null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		return false;
	}
	/**
     * Adds the desired role information for the user with the specified ID.
     *
     * @param id The unique identifier for the user.
     * @param desired_role The desired role information to be added.
     * @return True if the addition is successful; otherwise, false.
     */
	public static boolean addJobLookingFor(int id, String desired_role) {
		String update_query = "UPDATE COEN6311.PROFILES SET DESIRED_ROLE = ? WHERE ID = ?";
		PreparedStatement statement = null;
		try {
			Connection connection = DatabaseInstance.getDatabaseConnection();
			statement = connection.prepareStatement(update_query);
			statement.setInt(2, id);
			statement.setString(1, desired_role);


			int rowUpdated = statement.executeUpdate();
			return rowUpdated==1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(statement!=null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		return false;
		
	}
	/**
     * Retrieves the skills, location, and desired role information for the user with the specified ID.
     *
     * @param id The unique identifier for the user.
     * @return An ArrayList containing the retrieved values (skills, desired role, location).
     */
	public static ArrayList<String> getValuesFromId(int id) {
	    ArrayList<String> values = new ArrayList<>();
	    PreparedStatement statement = null;
	    try {
	    	Connection connection = DatabaseInstance.getDatabaseConnection();
	        String query = "SELECT SKILLS, LOCATION, DESIRED_ROLE FROM COEN6311.PROFILES WHERE ID = ?";
	        statement = connection.prepareStatement(query);
	        statement.setInt(1, id);

	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            String skills = resultSet.getString("SKILLS");
	            String location = resultSet.getString("LOCATION");
	            String desiredRole = resultSet.getString("DESIRED_ROLE");

	            // Add the values to the ArrayList
	            values.add(skills);
	            values.add(desiredRole);
	            values.add(location);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    finally {
			if(statement!=null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	    return values;
	}
}


