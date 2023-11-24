-- Drop the existing database if it exists
DROP DATABASE IF EXISTS `COEN6311`;

-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS `COEN6311`;

-- Create a new `USERS` table with the desired structure
CREATE TABLE `COEN6311`.`USERS` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `FULLNAME` VARCHAR(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `USERNAME` VARCHAR(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `PASSWORD` VARCHAR(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `EMAIL` VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ADDRESS` VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `DOB` VARCHAR(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL, -- Change data type to VARCHAR
  `CITY` VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PROVINCE` VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `COUNTRY` VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
);

-- Insert dummy data into the `USERS` table
INSERT INTO `COEN6311`.`USERS` (`FULLNAME`, `USERNAME`, `PASSWORD`, `EMAIL`, `ADDRESS`, `DOB`, `CITY`, `PROVINCE`, `COUNTRY`) VALUES
('John Doe', 'johndoe', 'password1', 'johndoe@example.com', '123 Main St', '1990-01-15', 'New York', 'NY', 'USA'),
('Jane Smith', 'janesmith', 'password2', 'janesmith@example.com', '456 Elm St', '1985-04-20', 'Los Angeles', 'CA', 'USA'),
('Alice Johnson', 'alicej', 'password3', 'alice@example.com', '789 Oak St', '2000-09-10', 'Chicago', 'IL', 'USA');

-- Create a new table `PROFILES` for data capture of user skills and location
CREATE TABLE `COEN6311`.`PROFILES` (
  `ID` INT NOT NULL,
  `SKILLS` VARCHAR(255) COLLATE utf8mb4_unicode_ci,
  `LOCATION` VARCHAR(255) COLLATE utf8mb4_unicode_ci,
  `DESIRED_ROLE` VARCHAR(255) COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`ID`)
);

-- Create a new table `SEARCH` for data capture of user search history
CREATE TABLE `COEN6311`.`SEARCH` (
    `SEARCH_ID` INT AUTO_INCREMENT, -- This column will auto-increment
    `USER_ID` INT,
	`SEARCH_KEYWORD` VARCHAR(255),
    `SEARCH_LOCATION` VARCHAR(255),
    `SEARCH_TIMESTAMP` TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Optional: a timestamp for when the search was performed
    PRIMARY KEY (`SEARCH_ID`)
);
