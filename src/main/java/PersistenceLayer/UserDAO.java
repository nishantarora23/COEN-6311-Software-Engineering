package PersistenceLayer;

import Models.User;

import java.sql.*;
import java.text.ParseException;
import java.sql.ResultSet;
import Helper.Helper;

public class UserDAO {

	// Insert user data into the users table
	private static String GET_USER_QUERY = "SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean addUser(User user) throws SQLException {

		try (Connection connection = DriverManager.getConnection(Helper.url,Helper.uname,Helper.pass)) {
			String sql = "INSERT INTO COEN6311.USERS (FULLNAME,USERNAME,PASSWORD,EMAIL,ADDRESS,DOB) " +
					"VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getFullName());
			statement.setString(2, user.getUsername());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getAddress());
			statement.setString(6, user.getDob());

			int rowUpdated = statement.executeUpdate();
			return rowUpdated==1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static User getUser(String username, String password){
		User user = null;
		try (Connection connection = DriverManager.getConnection(Helper.url, Helper.uname, Helper.pass)) {
			PreparedStatement statement = connection.prepareStatement(GET_USER_QUERY);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int retrievedId = resultSet.getInt("ID");
				String retrievedUsername = resultSet.getString("USERNAME");
				String retrievedPassword = resultSet.getString("PASSWORD");
				String retrievedFullName = resultSet.getString("FULLNAME");
				String retrievedEmail = resultSet.getString("EMAIL");
				String retrievedAddress = resultSet.getString("ADDRESS");
				String retrievedDOB = resultSet.getString("DOB");
				user = new User(retrievedId,retrievedFullName, retrievedUsername, retrievedPassword, retrievedEmail, retrievedAddress, retrievedDOB);
			}

		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}

		return user;
	}

}
