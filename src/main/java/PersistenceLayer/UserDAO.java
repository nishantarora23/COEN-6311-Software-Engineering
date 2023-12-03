package PersistenceLayer;

import Models.User;

import java.sql.*;
import java.text.ParseException;

import Helper.Helper;

/**
 * The UserDAO class provides data access methods for interacting with the database related to user records.
 * It includes methods for adding, retrieving, and updating user information.
 *
 * @author Merlin Abraham
 * @version 1.0
 */
public class UserDAO {
	
	/**
     * Adds a new user to the database with the provided user information.
     *
     * @param user The user object containing user information.
     * @return The ID of the inserted user, or -1 if the insertion fails.
     * @throws SQLException If a SQL exception occurs during the database operation.
     */
	public static int addUser(User user) throws SQLException {
		try {
			Connection connection = DatabaseInstance.getDatabaseConnection();
			PreparedStatement statement = null;
			String sql = "INSERT INTO COEN6311.USERS (FULLNAME, USERNAME, PASSWORD, EMAIL, DOB, ADDRESS, CITY, PROVINCE, COUNTRY) " +
					"VALUES (?, ?, ?, ?, ?, ?,?,?,?)";
			// Use the additional argument to retrieve the auto-generated ID
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getFullName());
			statement.setString(2, user.getUsername());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getDob());
			statement.setString(6, user.getAddress());
			statement.setString(7, user.getCity());
			statement.setString(8, user.getProvince());
			statement.setString(9, user.getCountry());
			
			int rowUpdated = statement.executeUpdate();
			if (rowUpdated == 1) {
				// Retrieve the auto-generated ID
				ResultSet generatedKeys = statement.getGeneratedKeys();
				if (generatedKeys.next()) {
					return generatedKeys.getInt(1);
				} else {
					throw new SQLException("Failed to retrieve the auto-generated ID.");
				}
			} else {
				return -1; // Indicate that the insertion failed
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1; // Indicate that an exception occurred
		}
	}
	/**
     * Retrieves a user from the database based on the provided username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The User object representing the retrieved user, or null if not found.
     */
	public static User getUser(String username, String password){
		User user = null;
		PreparedStatement statement = null;
		String GET_USER_QUERY = "SELECT * FROM COEN6311.USERS WHERE USERNAME = ? AND PASSWORD = ?";
		try {
			Connection connection = DatabaseInstance.getDatabaseConnection();
			statement = connection.prepareStatement(GET_USER_QUERY);
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
				String retrievedCity = resultSet.getString("CITY");
				String retrievedProvince = resultSet.getString("PROVINCE");
				String retrievedCountry = resultSet.getString("COUNTRY");
				user = new User(retrievedId,retrievedFullName, retrievedUsername, retrievedPassword, retrievedEmail, retrievedDOB,
						retrievedAddress, retrievedCity , retrievedProvince, retrievedCountry);
			}

		} catch (SQLException | ParseException e) {
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
		return user;
	}
	 /**
     * Updates user information in the database for the specified user ID.
     *
     * @param user The User object containing updated user information.
     * @param userID The ID of the user to update.
     * @return The number of rows updated in the database.
     *         Returns 0 if no updates were made, -1 if an exception occurred.
     * @throws SQLException If a SQL exception occurs during the database operation.
     */
	public static int updateUser(User user, int userID) throws SQLException {
		PreparedStatement statement = null;
	    try {
	    	Connection connection = DatabaseInstance.getDatabaseConnection();
	        String sql = "UPDATE COEN6311.USERS SET ";
	        boolean hasUpdates = false; // Flag to track if any updates were made

	        // Check each column for updates
	        if (user.getFullName() != null && !user.getFullName().isEmpty()) {
	            sql += "FULLNAME = ?, ";
	            hasUpdates = true;
	        }
	        if (user.getUsername() != null && !user.getUsername().isEmpty()) {
	            sql += "USERNAME = ?, ";
	            hasUpdates = true;
	        }
	        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
	            sql += "PASSWORD = ?, ";
	            hasUpdates = true;
	        }
	        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
	            sql += "EMAIL = ?, ";
	            hasUpdates = true;
	        }
	        if (user.getDob() != null && !user.getDob().isEmpty()) {
	            sql += "DOB = ?, ";
	            hasUpdates = true;
	        }
	        if (user.getAddress() != null && !user.getAddress().isEmpty()) {
	            sql += "ADDRESS = ?, ";
	            hasUpdates = true;
	        }
	        if (user.getCity() != null && !user.getCity().isEmpty()) {
	            sql += "CITY = ?, ";
	            hasUpdates = true;
	        }
	        if (user.getCountry() != null && !user.getCountry().isEmpty()) {
	            sql += "COUNTRY = ?, ";
	            hasUpdates = true;
	        }

	        // Remove the trailing comma and space if there were updates
	        if (hasUpdates) {
	            sql = sql.substring(0, sql.length() - 2);
	        } else {
	            // No updates were made
	            return 0;
	        }

	        // Add the WHERE clause to specify the user to update based on userID
	        sql += " WHERE ID = ?";

	        statement = connection.prepareStatement(sql);
	        
	        // Set the updated column values
	        int parameterIndex = 1;
	        if (user.getFullName() != null && !user.getFullName().isEmpty()) {
	            statement.setString(parameterIndex++, user.getFullName());
	        }
	        if (user.getUsername() != null && !user.getUsername().isEmpty()) {
	            statement.setString(parameterIndex++, user.getUsername());
	        }
	        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
	            statement.setString(parameterIndex++, user.getPassword());
	        }
	        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
	            statement.setString(parameterIndex++, user.getEmail());
	        }
	        if (user.getDob() != null && !user.getDob().isEmpty()) {
	            statement.setString(parameterIndex++, user.getDob());
	        }
	        if (user.getAddress() != null && !user.getAddress().isEmpty()) {
	            statement.setString(parameterIndex++, user.getAddress());
	        }
	        if (user.getCity() != null && !user.getCity().isEmpty()) {
	            statement.setString(parameterIndex++, user.getCity());
	        }
	        if (user.getCountry() != null && !user.getCountry().isEmpty()) {
	            statement.setString(parameterIndex++, user.getCountry());
	        }

	        // Set the userID for the WHERE clause
	        statement.setInt(parameterIndex, userID);

	        int rowsUpdated = statement.executeUpdate();

	        return rowsUpdated;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return -1; // Indicate that an exception occurred
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
	}
}
