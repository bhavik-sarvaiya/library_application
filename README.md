# Library Application API

This application should provide a REST API that satisfies the following requirements.

a) returns all users who have actually borrowed at least one book
b) returns all non-terminated users who have not currently borrowed anything
c) returns all users who have borrowed a book on a given date
d) returns all books borrowed by a given user in a given date range
e) returns all available (not borrowed) books

## Features

- Manage books: Add, update, delete, and retrieve books.
- Manage users: Add, update, delete, and retrieve users.
- Handle borrowings: Track which user has borrowed which book, including borrowing and return dates.
- Swagger UI for API documentation and interaction.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

What things you need to install the software:

- JDK 11 or later
- Maven 3.6 or later

### Installing

A step-by-step series of examples that tell you how to get a development environment running.

1. Clone the repository:
   ```sh
   git clone https://your-repository-url.git

2. Navigate to the project directory:
	cd library-application
	
3. Build the project using Maven:
	mvn clean install

4. Run the application:
	mvn spring-boot:run
	
The application should now be running on http://localhost:8080.

### Accessing Swagger UI
You can access the Swagger UI to interact with the API at:
	http://localhost:8080/swagger-ui.html
	
#### Built With
Spring Boot - The web framework used
Maven - Dependency Management
Swagger - Used to generate API documentation

	
	
	
		