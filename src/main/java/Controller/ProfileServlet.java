package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Helper.Helper;
import ServiceLayer.ManageProfileServices;


public class ProfileServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4872038924539756016L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String payloadData = Helper.getPayload(request);       
		JsonObject jsonPayload = new Gson().fromJson(payloadData, JsonObject.class);
		//JSONArray array = ManageProfileServices.addSkills(jsonPayload.get("id").getAsString(),jsonPayload.get("skills").getAsString(),jsonPayload.get("location").getAsString());
		
	}
}
