
# Weather-forecast
This project is a windsurfers weather service application designed to help windsurfing enthusiasts find the best location for their sport on a given day. It uses the Weatherbit Forecast API as its weather data source. You can increase the number of cities to check by adding them in project.
Currently, the Weatherbit API as part of the free plan allows you to check the forecast only for a week and the number of 50 requests per day.

**How to run?**
1. Clone the repository: Start by cloning the project repository to your local machine.
2. Build the project: Execute the command mvn clean install to compile and package the application.
3. Run the application: Launch the application with the command mvn spring-boot:run
4. Access the API: You can call the following endpoint to get windsurfing location recommendations

   ```http://localhost:8080/forecast/{requestedDate}```
Replace {requestedDate} with your desired date in yyyy-MM-dd format to get the best windsurfing spot for this day.

**What I used?**
- Java 17
- Spring Boot
- Spring Framework
- Weatherbit API
- Maven
- Lombok
- Mockito, JUnit
