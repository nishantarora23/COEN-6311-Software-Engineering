package Models;

import java.text.ParseException;
import java.util.Date;

public class User {
	private int id;
    private String fullName;
    private String email;
    private String password;
    private String dob;
    private String username;
    private String address;

    public User(int id, String fullName, String username, String password, String email, String address, String dob) throws ParseException {
    	this.id = id;
    	this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.dob = dob;
    }
    public User()
    {
        // Do Nothing;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return this.dob;
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
