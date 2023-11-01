package Controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Helper.Helper;
import Models.User;
import ServiceLayer.UserServices;

public class UserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String payloadData = Helper.getPayload(request);       
        JsonObject jsonPayload = new Gson().fromJson(payloadData, JsonObject.class);
        UserServices.createUser(jsonPayload);
        response.setStatus(HttpServletResponse.SC_OK);
	}

}
