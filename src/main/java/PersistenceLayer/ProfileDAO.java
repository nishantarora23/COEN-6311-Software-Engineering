package PersistenceLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Helper.Helper;

public class ProfileDAO {

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static boolean createProfile(int id) {

		String insert_query = "INSERT INTO COEN6311.PROFILES (ID) VALUES (?)";
		try (Connection connection = DriverManager.getConnection(Helper.url,Helper.uname,Helper.pass)) {
			PreparedStatement statement = connection.prepareStatement(insert_query);
			statement.setInt(1, id);

			int rowUpdated = statement.executeUpdate();
			return rowUpdated==1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean createProfile(int id, String location) {

		String insert_query = "INSERT INTO COEN6311.PROFILES (ID, LOCATION) VALUES (?, ?)";
		try (Connection connection = DriverManager.getConnection(Helper.url,Helper.uname,Helper.pass)) {
			PreparedStatement statement = connection.prepareStatement(insert_query);
			statement.setInt(1, id);
			statement.setString(2, location);

			int rowUpdated = statement.executeUpdate();
			return rowUpdated==1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean updateSkills(int id, String skills) {

		String update_query = "UPDATE COEN6311.PROFILES SET SKILLS = ? WHERE ID = ?";
		try (Connection connection = DriverManager.getConnection(Helper.url,Helper.uname,Helper.pass)) {

			PreparedStatement statement = connection.prepareStatement(update_query);
			statement.setInt(1, id);
			statement.setString(2, skills);


			int rowUpdated = statement.executeUpdate();
			return rowUpdated==1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean updatLocation(int id, String location) {

		String update_query = "UPDATE COEN6311.PROFILES SET LOCATION = ? WHERE ID = ?";
		try (Connection connection = DriverManager.getConnection(Helper.url,Helper.uname,Helper.pass)) {

			PreparedStatement statement = connection.prepareStatement(update_query);
			statement.setInt(2, id);
			statement.setString(1, location);


			int rowUpdated = statement.executeUpdate();
			return rowUpdated==1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean addJobLookingFor(int id, String desired_role) {
		String update_query = "UPDATE COEN6311.PROFILES SET DESIRED_ROLE = ? WHERE ID = ?";
		try (Connection connection = DriverManager.getConnection(Helper.url,Helper.uname,Helper.pass)) {

			PreparedStatement statement = connection.prepareStatement(update_query);
			statement.setInt(2, id);
			statement.setString(1, desired_role);


			int rowUpdated = statement.executeUpdate();
			return rowUpdated==1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}

	public static ArrayList<String> getValuesFromId(int id) {
	    ArrayList<String> values = new ArrayList<>();

	    try (Connection connection = DriverManager.getConnection(Helper.url, Helper.uname, Helper.pass)) {
	        String query = "SELECT SKILLS, LOCATION, DESIRED_ROLE FROM COEN6311.PROFILES WHERE ID = ?";
	        PreparedStatement statement = connection.prepareStatement(query);
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

	    return values;
	}
}


