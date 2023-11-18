package PersistenceLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Helper.Helper;

public class SearchDAO {

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void insertSearchRecord(int candidateId, String searchKeywords) {
		try (Connection connection = DriverManager.getConnection(Helper.url, Helper.uname, Helper.pass)) {
			String sql = "INSERT INTO SEARCH (USER_ID, SEARCH_KEYWORD) VALUES (?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, candidateId);
            preparedStatement.setString(2, searchKeywords);
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Inserted " + rowsAffected + " record(s) successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
//	SELECT * FROM SEARCH
//	WHERE candidate_id = 1
//	UNION
//	SELECT * FROM SEARCH
//	ORDER BY search_timestamp DESC
//	LIMIT 10;

}
