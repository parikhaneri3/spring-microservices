### Springboot with Microservices & H2 DB

This project demonstrates orchestration, rest api invocation, error handling, tracing of logs.

**Task-1 Architecture:**
- `Service Registry` 
    - Enables client-side load-balancing and decouples service providers from consumers without the need for DNS.
- `Main Service` 
    - `GET` - Returns `UP` if `main-service `is up.
    - `POST` - Returns the concatenated responses of the `GET` of `welcome-service` and the `Post` of `concat-service`. (Example : {“Hello Aneri Parikh”})
- `Welcome Service` 
    - `GET` - Is called by the `main-service` which returns ”Hello”.
- `Concat Service` 
    - `POST` - Is called by `main-service` to return the concatenated name (Example : “Aneri Parikh”) 

**Task-2 Architecture:**
- `CustomAlgo` 
    - Forms the parent-child relationship after fetching the list from DB.
  
**Steps to run the project:**:
- Clone the project using `git clone https://github.com/parikhaneri3/spring-microservices.git`
- Run `mvn clean install` in terminal and the spring boot project in IDE.
- `Service Registry` runs on `8761`
- `main-service` runs on `5551`
- `welcome-service` runs on `5552`
- `concat-service` runs on `5553`
- `Task-2` runs on `5554`

**Technologies:**
- Springboot
- H2 immemory data storage
- JPA
- Maven
- JDK8+


