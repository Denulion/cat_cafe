### **Project Overview**

This project is a management system for a cat cafe, allowing users to:
- Register user accounts with specific roles (Admin, User).
- Book reservations for cafe visits.
- Apply for cat adoptions, with applications managed by admins.

Admins can approve or reject adoption applications and manage user reservations.

###Technologies###
-IntelliJ IDEA
-Java 21
-Maven
-Spring Boot 3.4.3
	-Spring WEB
	-MySQL Driver
	-Spring Validation
	-Spring Security
	-Spring Data JPA
-MySQL
-Docker
-Postman

###Requirements###
You will need:
-Docker Desktop
-Postman
-IntelliJ IDEA
-Basic knowledge of how to use these tools

###Installation###
1. **Clone the repository** git clone https://github.com/Denulion/cat_cafe

2. **Open the project** Launch IntelliJ IDEA and open the cloned project.

3. **Enter these commands in Docker Desktop terminal to create MySQL container and interface to access it**:
	1) docker run --name example-mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -d -p 3306:3306 mysql:8.0
	2) docker run --name phpmyadmin -d --link some-mysql:db -p 8081:80 phpmyadmin

4. **Go to localhost:8081 (username: "root", password: "my-secret-pw") and
 use SQL code from MySQLInitCode.txt file to initialize ready-to-go database**

5. **Configure InteliJ IDEA if necessary**:
	1) If dependencies didn't load automatically, import them:
		File -> Project Structure -> Modules -> Dependencies -> Import
	2) You may need to specify JDK version for InteliJ (you will need JDK 21):
		File -> Project Structure -> Project -> Project SDK

###Usage###
1. **Run the application** You can run the project from IntelliJ IDEA
 by executing the "CatCafeApplication.java" class.

2. **Access endpoints:**
    Once the app is running, you can use the following endpoints (for example, via Postman):
	-POST http://localhost:8080/api/auth/register (this endpoint is public, no authentication needed,
		USER - id 1, ADMIN - id 2):
		{
		"username": "catlover123",
		"password": "securepass",
		"roles": [
		{"id": 1},
		{"id": 2}]}
    Customer endpoints (need basic auth and USER role):
	-POST http://localhost:8080/api/reservations :
		{
		"dateOfReservation": "2025-03-01",
		"timeSlot": "14:00 - 15:00",
		"numGuests": 2
		}
	-GET http://localhost:8080/api/reservations - will display current user reservations
	-POST http://localhost:8080/api/adoptions/apply :
		{
		"catName": "Whiskers"
		}
	-GET http://localhost:8080/api/adoptions - will display current user cat adoptions
   Admin endpoints (need basic auth and ADMIN role):
	-GET http://localhost:8080/api/adoptions/pending - will display all adoptions with status PENDING
	-PUT http://localhost:8080/api/adoptions/{adoptionId}/approve - will approve adoption (if exists by id):
		{
		"status": "APPROVED"
		}
	-PUT http://localhost:8080/api/adoptions/{adoptionId}/reject - will reject adoption (if exists by id):
		{
		"status": "REJECTED"
		}
	-DELETE http://localhost:8080/api/reservations/{reservationId} - will cancel a reservation (if exists by id)