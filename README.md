# ICDE-JobHive

## Problem Statement
Finding a job in today's market is incredibly challenging, especially for new graduates who do not have professional working experience. Due to several layoffs, the job market has decreased, leaving fewer opportunities available [1]. Job seekers typically want to secure the best positions for themselves, but they frequently have difficulties matching their education and experience to the demands of the job market. Their abilities and the requirements of the work are incompatible, which makes the job search process difficult. As candidates struggle to discover jobs that properly match their abilities, the process gets discouraging, leading to a sense of disappointment and dissatisfaction [2]. Additionally, there is strong competition among job searchers, which makes it even more difficult to get a job.<br><br>
To address these challenges, our project aims to create a website allowing job seekers to input their skills and preferences, which will be recorded in a database with their search criteria. With the help of ICDE and a web crawler, it will scan it all over the internet find real-time job listings and extract relevant job postings based on the qualifications and criteria provided by job seekers. With all this information, our system will make a personalized list of job options for each job seeker who has registered on the site. As a result, they won't have to waste hours looking through positions that don't fit their personal interests or skill set. Simultaneously, employers will find it easier to discover qualified candidates, making the hiring process more effective.

## Project Overview
ICDE-JobHive is a comprehensive web-based career services platform which aims to provide user-friendly and centralized tools to job seekers in the job search process.

## High Level User Stories
<table>
  <tr>
    <th>S.No.</th>
    <th>Core Feature (Epic)</th>
    <th>Issue ID</th>
    <th>Description</th>
	<th>Priority</th>
  </tr>
  <tr>
    <td>1.</td>
    <td>Release Qualification v1.0 Activities</td>
    <td><a href="https://healthoway.atlassian.net/browse/JH-1">JH-1</a></td>
    <td>This feature aims to handle various tasks needed for the successful completion of the ICE-JobHive v1.0 Release.</td>
	<td>Highest</td>
  </tr>
  <tr>
    <td>2.</td>
    <td>User Registration and Job Seeker Profile Management</td>
    <td><a href="https://healthoway.atlassian.net/browse/JH-3">JH-3</a></td>
    <td>This core feature aims to implement a comprehensive authentication and registration system for ICDE-JobHive web application, allowing job seekers to securely access and manage their accounts.</td>
	<td>Highest</td>
  </tr>
  <tr>
    <td>3.</td>
    <td>Job Search And Job Listings</td>
    <td><a href="https://healthoway.atlassian.net/browse/JH-4">JH-4</a></td>
    <td>This core feature focuses on enhancing the browsing and listing job capabilities within our ICDE-JobHive web application for job seekers.</td>
    <td>Highest</td>
  </tr>
  <tr>
    <td>4.</td>
    <td>ICDE User Activity Tracking</td>
    <td><a href="https://healthoway.atlassian.net/browse/JH-5">JH-5</a></td>
    <td>The focus lies in capturing and storing user search activities within the ICDE platform. It involves logging and preserving crucial data associated with user searches, specifically the skills users are seeking.</td>
	<td>Highest</td>
  </tr>
  <tr>
    <td>5.</td>
    <td>Data Mining And Resume Builder</td>
    <td><a href="https://healthoway.atlassian.net/browse/JH-6">JH-6</a></td>
    <td></td>
	<td>Highest</td>
  </tr>
</table>

### Team Members and Roles
<table>
  <tr>
    <th>Team member</th>
    <th>Role</th>
    <th>Github ID</th>
  </tr>
  <tr>
    <td>Bhargav Ashvanibhai Patel</td>
    <td>TBD</td>
    <td>bhargav0425</td>
  </tr>
  <tr>
    <td>Merlin Mary Abraham</td>
    <td>TBD</td>
    <td>merlinabraham95</td>
  </tr>
  <tr>
    <td>Nishant Arora</td>
    <td>TBD</td>
    <td>aroranish23</td>
  </tr>
  <tr>
    <td>Nishant Barua</td>
    <td>TBD</td>
    <td>barualee</td>
  </tr>
  </table>

## Design, Architecture and Technology Stack 

### Use Case Diagram
![Use case Diagram](https://github.com/nishantarora23/ICDE-JobHive/assets/42149632/d59359cb-86c9-491b-95c6-0756b2f806ce)

### Context Architecture
![Context Architecture](https://github.com/nishantarora23/ICDE-JobHive/assets/42149632/72354413-e698-4572-bd29-dbd6fed270a4)

### Technology Stack
<br>
<table>
  <tr>
    <th>Tool</th>
    <th>Version</th>
	<th>Description</th>
  </tr>
  <tr>
    <td>OpenJDK</td>
    <td>11.0.16.1</td>
	<td>Java programming language will be used for the backend development to create java servlets and expose the APIs.</td>
  </tr>
  <tr>
    <td>Apache Tomcat</td>
    <td>8.5.83</td>
	<td>Apache tomcat will be utilized for the deployment and execution of java servlets.</td>
  </tr>
  <tr>
    <td>React</td>
    <td>18.2.0</td>
	<td>ICDE-JobHive will utilize the JavaScript libraries available in react for the frontend development of the website.</td>
  </tr>
  <tr>
    <td>MySQL</td>
    <td>8.0.31</td>
	<td>MySQL is the relational database that will be used to store the key details of the job seekers</td>
  </tr>
  <tr>
    <td>Gradle</td>
    <td>7.6</td>
	<td>Gradle is the build automation tool that will efficiently compile, package, and deploy ICDE-JobHive, managing dependencies and providing flexibility and scalability for build configurations.</td>
  </tr>
  <tr>
	<td>GitHub</td>
	<td>N/A</td>
	<td>GitHub will be utilized for source code management and hosting project documents like MoM, etc on Wiki pages.</td>
  </tr>
</table>
