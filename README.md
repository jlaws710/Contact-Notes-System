# Read Me First
## Description
* This is a Backend Service for managing contact notes.

### Technologies Used
* Java 17
* Spring Boot
* Postgres
* Docker
* Gradle

### Features
* User authentication is handled via database using Spring Security.
* Supports full CRUD operations for Contacts and Notes attached to contacts.
* A Bearer token is issued via user login.
* The token is needed for accessing contact and note endpoints.

## Getting Started
### Installation
1. Clone the repository: git clone <repository-url>
2. Navigate to the root directory and run 'gradle clean'
3. Run 'docker-compose up' to start Postgres
4. Run 'gradle bootRun' to start the program

### API Endpoint Examples:
1. POST /auth/register
   ````
   {
   "username": "your_username",
   "password": "your_password"
   }
   ````
   
2. POST /auth/login 
   ````
   {
   "username": "your_username",
   "password": "your_password"
   }
   ````
   
3. POST /contacts
   ````
   Authorization: Basic {your-token}
   {
   "firstName": "your_first_name",
   "lastName": "your_last_name",
   "email": "your_email_address"
   }
   ````
 
4. GET /contacts
   ````
   Authorization: Basic {your-token}
   ````
   
5. POST /contacts/{id}/notes
   ````
   Authorization: Basic {your-token}
   {
   "note_text": "This is a test for note text"
   }
   ````
   
### Key Decisions
This Backend Service uses Spring Security via bearer token. 
Postgres was used because of its strong focus on data integrity, scalability, and replication
makes it a great choice for online transactions.

### Assumptions
This application was created under the assumption that the user has experience working with backend services.
An API Tool or Command line Tool is required.

### Tradeoffs
1. JWT Revokes
   * Because JWT is stateless, the tokens are short lived.
   
2. Spring Security Complexity
   * Spring Security is strong but also complex.

3. Spring Boot Application Size
   * Spring Boot requires more memory than Go microservices.

### Improvements
1. Refresh token pattern
2. Use a queue or event bus to decouple note creation.
3. Add integration tests
