package PersistenceLayer;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Helper.Helper;

/**
 * The SearchDAO class provides data access methods for interacting with the database related to search records.
 * It includes methods for inserting and retrieving search records.
 *
 * @author Nishant Arora
 * @version 1.0
 */
public class SearchDAO {

	/**
     * Inserts a search record into the database with the specified candidate ID, search keywords, and search location.
     *
     * @param candidateId The unique identifier for the candidate.
     * @param searchKeywords The keywords used in the search.
     * @param searchLocation The location used in the search.
     */

	public static void insertSearchRecord(int candidateId, String searchKeywords, String searchLocation) {
		try {
			Connection connection = DatabaseInstance.getDatabaseConnection();
			String sql = "INSERT INTO SEARCH (USER_ID, SEARCH_KEYWORD, SEARCH_LOCATION) VALUES (?, ?,?);";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, candidateId);
			preparedStatement.setString(2, searchKeywords);
			preparedStatement.setString(3, searchLocation);
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println("Inserted " + rowsAffected + " record(s) successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
     * Retrieves the latest search record for the user with the specified ID.
     *
     * @param id The unique identifier for the user.
     * @return An ArrayList containing the retrieved search keywords and location.
     */
	public static ArrayList<String> getSearchRecord(int id) {
		ArrayList<String> arr = new ArrayList<>();
		try {
			Connection connection = DatabaseInstance.getDatabaseConnection();
			String sql = "SELECT * FROM SEARCH WHERE USER_ID = ? ORDER BY SEARCH_TIMESTAMP DESC LIMIT 1;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {             
				arr.add(resultSet.getString("SEARCH_KEYWORD"));
				arr.add(resultSet.getString("SEARCH_LOCATION"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}

    /**
     * Inserts a cache record into the database with the specified keywords, location, and JSON file name.
     *
     * @param keywords The keywords used in the search.
     * @param location The location used in the search.
     * @param jsonFileName The name of the JSON file associated with the search results.
     * @return The ID of the inserted record, or -1 if the insertion fails.
     */
	public static int insertCacheRecord(String keywords, String location, String jsonFileName) {
		
		return -1;
	}
}
