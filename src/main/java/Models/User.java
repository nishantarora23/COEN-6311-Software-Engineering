package Models;

import java.text.ParseException;

/**
 * The User class represents a user entity with attributes such as ID, full name, email, password, date of birth,
 * username, address, city, province, and country.
 *
 * @author Nishant Arora
 * @version 1.0
 */
public class User {

	/** The unique identifier for the user. */
	private int id;

	/** The full name of the user. */
	private String fullName;

	/** The email address of the user. */
	private String email;

	/** The password of the user. */
	private String password;

	/** The date of birth of the user. */
	private String dob;

	/** The username of the user. */
	private String username;

	/** The address of the user. */
	private String address;

	/** The city of the user. */
	private String city;

	/** The province of the user. */
	private String province;

	/** The country of the user. */
	private String country;

	/**
	 * Constructs a new User object with the specified attributes.
	 *
	 * @param id The unique identifier for the user.
	 * @param fullName The full name of the user.
	 * @param username The username of the user.
	 * @param password The password of the user.
	 * @param email The email address of the user.
	 * @param dob The date of birth of the user.
	 * @param address The address of the user.
	 * @param city The city of the user.
	 * @param province The province of the user.
	 * @param country The country of the user.
	 * @throws ParseException If an error occurs while parsing the date of birth.
	 */
	public User(int id, String fullName, String username, String password, String email, String dob,
			String address, String city, String province, String country) throws ParseException {
		this.id = id;
		this.fullName = fullName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.dob = dob;
		this.address = address;
		this.city = city;
		this.province = province;
		this.country = country;
	}

	/**
	 * Default constructor for the User class.
	 */
	public User()
	{
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}