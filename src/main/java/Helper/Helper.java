package Helper;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class Helper {

	public static String url;
	public static String uname;
	public static String pass;

	static {

		url = "jdbc:mysql://127.0.0.1:3306/soen6011";
		uname =  "root";
		pass = "admin123";
		System.out.println(url + "  "+ uname+ "  "+pass);
	}

	public static String getPayload(HttpServletRequest request) throws IOException {
		StringBuilder payload = new StringBuilder();
		BufferedReader reader = request.getReader();

		String line;
		while ((line = reader.readLine()) != null) {
			payload.append(line);
		}
		return payload.toString();
	}

}
