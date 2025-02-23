# Project Overview

This project is a management system for a cat cafe, allowing users to:

- Register user accounts with specific roles (Admin, User).
- Book reservations for cafe visits.
- Apply for cat adoptions, with applications managed by admins.

Admins can approve or reject adoption applications and manage user reservations.

### Technologies
- IntelliJ IDEA  
- Java 21  
- Maven  
- Spring Boot 3.4.3  
  - Spring WEB  
  - MySQL Driver  
  - Spring Validation  
  - Spring Security  
  - Spring Data JPA  
- MySQL  
- Docker  
- Postman  

### Requirements
You will need:
- Docker Desktop  
- Postman  
- IntelliJ IDEA  
- Basic knowledge of how to use these tools  

### Installation

1. **Clone the repository**  
   `git clone https://github.com/Denulion/cat_cafe`

2. **Open the project**  
   Launch IntelliJ IDEA and open the cloned project.

3. **Create a MySQL container and interface using Docker Desktop terminal:**  
   - `docker run --name example-mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -d -p 3306:3306 mysql:8.0`  
   - `docker run --name phpmyadmin -d --link some-mysql:db -p 8081:80 phpmyadmin`  

4. **Initialize the database:**  
   Go to `localhost:8081` (username: `root`, password: `my-secret-pw`) and use SQL code from `MySQLInitCode.txt` to set up the database.

5. **Configure IntelliJ IDEA if necessary:**  
   - If dependencies didn't load automatically, import them:  
     `File -> Project Structure -> Modules -> Dependencies -> Import`  
   - Specify JDK version (JDK 21 required):  
     `File -> Project Structure -> Project -> Project SDK`  

### Usage

1. **Run the application**  
   Execute the `CatCafeApplication.java` class from IntelliJ IDEA.

2. **Access endpoints**  
   Once the app is running, use the following endpoints (e.g., via Postman):

   - **Public Endpoint** (no authentication needed):
     - `POST http://localhost:8080/api/auth/register`  
       ```json
       {
         "username": "catlover123",
         "password": "securepass",
         "roles": [
           { "id": 1 },
           { "id": 2 }
         ]
       }
       ```

   - **Customer Endpoints** (require basic auth with USER role):
     - `POST http://localhost:8080/api/reservations`  
       ```json
       {
         "dateOfReservation": "2025-03-01",
         "timeSlot": "14:00 - 15:00",
         "numGuests": 2
       }
       ```
     - `GET http://localhost:8080/api/reservations` - View user's reservations  
     - `POST http://localhost:8080/api/adoptions/apply`  
       ```json
       {
         "catName": "Whiskers"
       }
       ```
     - `GET http://localhost:8080/api/adoptions` - View user's cat adoptions  

   - **Admin Endpoints** (require basic auth with ADMIN role):
     - `GET http://localhost:8080/api/adoptions/pending` - View all pending adoptions  
     - `PUT http://localhost:8080/api/adoptions/{adoptionId}/approve`  
       ```json
       {
         "status": "APPROVED"
       }
       ```
     - `PUT http://localhost:8080/api/adoptions/{adoptionId}/reject`  
       ```json
       {
         "status": "REJECTED"
       }
       ```
     - `DELETE http://localhost:8080/api/reservations/{reservationId}` - Cancel a reservation  
     - `GET http://localhost:8080/api/adoptions/approved` - View all approved adoptions  

### Credits

This project was developed by **Andrej Titkov** as part of a learning and development exercise in Java and Spring Boot.

Special thanks to my teacher **Julius Zabulenas** for guidance and support.
