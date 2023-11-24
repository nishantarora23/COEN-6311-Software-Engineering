package PersistenceLayer;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Helper.Helper;

public class SearchDAO {

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public static void insertSearchRecord(int candidateId, String searchKeywords, String searchLocation) {
		try (Connection connection = DriverManager.getConnection(Helper.url, Helper.uname, Helper.pass)) {
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

	public static ArrayList<String> getSearchRecord(int id) {
		ArrayList<String> arr = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(Helper.url, Helper.uname, Helper.pass)) {
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

}
