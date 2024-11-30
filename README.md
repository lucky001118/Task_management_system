# Task Management Service

This is a microservices-based **Task Management Service** developed using Spring Boot. It enables efficient management of tasks, user assignments, and submissions. The project is structured into multiple services, with centralized service discovery and routing.

## Features
- **UserTaskService**: Manages user-specific tasks.
- **TaskService**: Handles the creation, modification, and retrieval of tasks.
- **SubmissionService**: Manages task submissions and their status.
- **Eureka Server**: Provides service discovery for dynamic load balancing and scaling.
- **API Gateway**: Acts as a unified entry point for routing requests to respective microservices.

## Project Structure
```
Task Management Service
├── UserTaskService
├── TaskService
├── SubmissionService
├── EurekaServer
└── APIGateway
```

## Technologies Used
- **Spring Boot**: Microservice architecture and REST APIs
- **Spring Cloud**: Eureka Server for service discovery
- **API Gateway**: Centralized request routing
- **MySQL/PostgreSQL**: Relational database for data persistence
- **Maven**: Dependency management and build tool

## How to Run
1. **Clone the repository**:
   ```bash
   git clone Task_management_system
   cd TaskManagementService
   ```

2. **Set up the databases**:
   - Create databases for each service as required.
   - Update the respective `application.properties` or `application.yml` files with database configurations.

3. **Start the Eureka Server**:
   ```bash
   cd EurekaServer
   mvn spring-boot:run
   ```

4. **Start the individual services**:
   ```bash
   cd UserTaskService
   mvn spring-boot:run
   cd ../TaskService
   mvn spring-boot:run
   cd ../SubmissionService
   mvn spring-boot:run
   ```

5. **Start the API Gateway**:
   ```bash
   cd APIGateway
   mvn spring-boot:run
   ```

6. Access the services via the API Gateway using `http://<gateway-host>:<gateway-port>`.

## APIs Overview
### UserTaskService
- `GET /users/{userId}/tasks`: Retrieve tasks for a user
- `POST /users/{userId}/tasks`: Assign a task to a user

### TaskService
- `GET /tasks`: Retrieve all tasks
- `POST /tasks`: Create a new task

### SubmissionService
- `GET /submissions/{taskId}`: Retrieve submissions for a task
- `POST /submissions`: Submit a task

### Eureka Server
- Accessible at `http://<eureka-host>:<eureka-port>/`.

## Future Enhancements
- Implement notification services for task updates.
- Enable CI/CD pipelines for seamless integration and deployment.

## Contributing
Contributions are welcome! Please fork the repository and create a pull request for review.

Replace `<repository-url>`, `<gateway-host>`, `<gateway-port>`, `<eureka-host>`, and `<eureka-port>` with the actual values used in your project. Let me know if you’d like help with customizing further!
