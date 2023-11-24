-- Drop the existing database if it exists
DROP DATABASE IF EXISTS COEN6311;

-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS COEN6311;


-- Create a new USERS table with the desired structure
CREATE TABLE COEN6311.USERS (
  ID int NOT NULL AUTO_INCREMENT,
  FULLNAME varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  USERNAME varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PASSWORD varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  EMAIL varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  ADDRESS varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  DOB varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL, -- Change data type to VARCHAR
  CITY varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PROVINCE varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  COUNTRY varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (ID)
);

-- Insert dummy data into the USERS table
INSERT INTO COEN6311.USERS (FULLNAME, USERNAME, PASSWORD, EMAIL, ADDRESS, DOB, CITY, PROVINCE, COUNTRY) VALUES
('John Doe', 'johndoe', 'password1', 'johndoe@example.com', '123 Main St', '1990-01-15', 'New York', 'NY', 'USA'),
('Jane Smith', 'janesmith', 'password2', 'janesmith@example.com', '456 Elm St', '1985-04-20', 'Los Angeles', 'CA', 'USA'),
('Alice Johnson', 'alicej', 'password3', 'alice@example.com', '789 Oak St', '2000-09-10', 'Chicago', 'IL', 'USA');

-- Create new table profiles for data capture of user skills and location
CREATE TABLE `COEN6311`.`PROFILES` (
  `ID` int NOT NULL,
  `SKILLS` varchar(255) COLLATE utf8mb4_unicode_ci,
  `LOCATION` varchar(255) COLLATE utf8mb4_unicode_ci,
  `DESIRED_ROLE` varchar(255) COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`ID`)
);
-- Create new table 'SEARCH' for data capture of user search history
CREATE TABLE COEN6311.SEARCH (
    SEARCH_ID INT AUTO_INCREMENT, -- This column will auto-increment
    USER_ID INT,
	SEARCH_KEYWORD VARCHAR(255),
    SEARCH_LOCATION VARCHAR(255),
    SEARCH_TIMESTAMP TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Optional: a timestamp for when the search was performed
    PRIMARY KEY (SEARCH_ID)
);