
# Training Center Management API

A Spring Boot REST API for managing government-funded training centers. This MVP provides functionality to create and retrieve training center information with validation and error handling.

## Technologies Used

- Java 21
- Spring Boot 3.2.3
- H2 Database
- JPA/Hibernate
- Jakarta Bean Validation
- Lombok
- Maven

## Prerequisites

- JDK 21
- Maven 3.9.x
- Any REST client (Postman, cURL, etc.)

## Getting Started

1. Clone the repository
```bash
git clone https://github.com/piyush-ghanghav/Backend_Traini8_Piyush_Ghanghav.git
cd training-backend
```

2. Build the project
```bash
./mvnw clean install
```

3. Run the application
```bash
./mvnw spring-boot:run
```

The application will start on port 8080.

## API Endpoints

### 1. Create Training Center
```http
POST /api/training-centers
```

Request Body:
```json
{
  "centerName": "Tech Training Hub",
  "centerCode": "TECH12345678",
  "address": {
    "detailedAddress": "123 Tech Street",
    "city": "Bangalore",
    "state": "Karnataka",
    "pincode": "560001"
  },
  "studentCapacity": 100,
  "coursesOffered": [
    "Java Programming",
    "Python Development"
  ],
  "contactEmail": "contact@techtraininghub.com",
  "contactPhone": "9876543210"
}
```

### 2. Get All Training Centers
```http
GET /api/training-centers
```

Optional Query Parameters:
- `city` - Filter centers by city name (case-insensitive)
- `state` - Filter centers by state name (case-insensitive)
- `minCapacity` - Filter centers by minimum student capacity

Example Requests:
```http
# Get all centers in Bangalore
GET /api/training-centers?city=Bangalore

# Get all centers in Karnataka with capacity >= 100
GET /api/training-centers?state=Karnataka&minCapacity=100

# Get all centers with minimum capacity of 50
GET /api/training-centers?minCapacity=50
```

Example Response:
```json
[
    {
        "id": 1,
        "centerName": "Tech Training Hub",
        "centerCode": "TECH12345678",
        "address": {
            "detailedAddress": "123 Tech Street",
            "city": "Bangalore",
            "state": "Karnataka",
            "pincode": "560001"
        },
        "studentCapacity": 100,
        "coursesOffered": [
            "Java Programming",
            "Python Development"
        ],
        "createdOn": 1679825473000,
        "contactEmail": "contact@techtraininghub.com",
        "contactPhone": "9876543210"
    }
]
```

This documents:
1. The filtering functionality implemented in `TrainingCenterController`
2. Filter parameters available in `TrainingCenterService`
3. Example requests and responses
4. Query parameter descriptions

## Data Validation Rules

1. Center Name
   - Mandatory
   - Maximum 40 characters

2. Center Code
   - Mandatory
   - Exactly 12 alphanumeric characters

3. Address
   - Mandatory
   - Includes: detailedAddress, city, state, pincode
   - Pincode: 6 digits

4. Contact Information
   - Phone: Mandatory, 10 digits
   - Email: Optional, valid email format

5. Student Capacity
   - Mandatory
   - Minimum value: 1

## Database Configuration

H2 in-memory database is configured with:
- Console URL: http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:trainingdb
- Username: sa
- Password: (empty)

## Error Handling

The API implements a global exception handler for:
- Validation errors
- Duplicate center code
- General errors

Error responses include:
- Timestamp
- Status code
- Detailed error messages

## Project Structure

```
src/main/java/com/training/backend/
├── controller/
│   └── TrainingCenterController.java
├── service/
│   └── TrainingCenterService.java
├── repository/
│   └── TrainingCenterRepository.java
├── model/
│   ├── TrainingCenter.java
│   └── Address.java
├── dto/
│   ├── TrainingCenterDTO.java
│   └── AddressDTO.java
└── exception/
    └── GlobalExceptionHandler.java
```

<!-- ## Testing

Run the tests using:
```bash
./mvnw test
``` -->

## Future Enhancements

- Filtering capabilities for GET endpoint
- Pagination support
- Additional CRUD operations
- Integration tests
- API documentation with Swagger/OpenAPI

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.
