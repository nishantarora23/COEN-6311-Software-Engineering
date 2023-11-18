CREATE DATABASE IF NOT EXISTS COEN6311;

CREATE TABLE `COEN6311`.`USERS` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `FULLNAME` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `USERNAME` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `PASSWORD` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `EMAIL` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ADDRESS` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  PRIMARY KEY (`ID`)
);

INSERT INTO `COEN6311`.`USERS` (`FULLNAME`, `USERNAME`, `PASSWORD`, `EMAIL`, `ADDRESS`, `DOB`)
VALUES
  ('John Doe', 'johndoe', 'password123', 'john.doe@example.com', '123 Main St', '1990-05-15'),
  ('Jane Smith', 'janesmith', 'securepass', 'jane.smith@example.com', '456 Elm St', '1985-12-10'),
  ('Alice Johnson', 'alicej', 'mysecret', 'alice.johnson@example.com', '789 Oak St', '1993-08-22'),
  ('Bob Wilson', 'bobw', 'supersecret', 'bob.wilson@example.com', '987 Pine St', '1980-04-30'),
  ('Eve Adams', 'evea', 'topsecret', 'eve.adams@example.com', '543 Birch St', '1998-07-18');
