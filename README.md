# Read Me First
## Description
* This is a Backend Service for managing contact notes.

### Technologies Used
* Go
* Gin
* Postgres
* Docker
* Gradle

### Features
* User authentication is handled via database using JWT middleware.
* Supports full CRUD operations for Contacts and Notes attached to contacts.
* A Bearer token is issued via user login.
* The token is needed for accessing contact and note endpoints.

## Getting Started
### Installation
1. Clone the repository: git clone <repository-url>
2. Navigate to the root directory and run 'gradle clean'
3. Run 'docker-compose up' to start Postgres
4. Run 'go run main.go' to start the program

### API Endpoint Examples:
1. POST /auth/register
   ````
   {
   "username": "your_username",
   "password": "your_password"
   }
   ````
   
2. POST /login 
   ````
   {
   "username": "your_username",
   "password": "your_password"
   }
   ````
   
3. POST /contacts/
   ````
   Authorization: Basic {your-token}
   {
   "username": "your_username",
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
