CREATE DATABASE IF NOT EXISTS COEN6311;

CREATE TABLE `COEN6311`.`USERS` (
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

INSERT INTO `COEN6311`.`USERS` (FULLNAME, USERNAME, PASSWORD, EMAIL, ADDRESS, DOB, CITY, PROVINCE, COUNTRY) VALUES
('John Doe', 'johndoe', 'password1', 'johndoe@example.com', '123 Main St', '1990-01-15', 'New York', 'NY', 'USA'),
('Jane Smith', 'janesmith', 'password2', 'janesmith@example.com', '456 Elm St', '1985-04-20', 'Los Angeles', 'CA', 'USA'),
('Alice Johnson', 'alicej', 'password3', 'alice@example.com', '789 Oak St', '2000-09-10', 'Chicago', 'IL', 'USA');

CREATE TABLE `COEN6311`.`PROFILES` (
  `ID` int NOT NULL,
  `SKILLS` varchar(255) COLLATE utf8mb4_unicode_ci,
  `LOCATION` varchar(255) COLLATE utf8mb4_unicode_ci,
  `DESIRED_ROLE` varchar(255) COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`ID`)
);
