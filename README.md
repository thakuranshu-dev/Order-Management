# Order Management System

A Spring Boot application for managing orders and products with inventory tracking capabilities.

## Features

- **Order Management**: Create, retrieve, and manage customer orders
- **Product Management**: Add, update, and track products with stock levels
- **Inventory Control**: Automatic stock validation and updates when orders are placed
- **Exception Handling**: Comprehensive error handling with meaningful error messages
- **RESTful API**: Clean and intuitive REST API endpoints
- **Data Validation**: Request validation using DTOs (Data Transfer Objects)

## Technologies Used

- **Framework**: Spring Boot
- **Language**: Java
- **Build Tool**: Maven
- **Database**: (Configured in application properties)
- **Additional**: Spring Data JPA, MapStruct/Custom Mappers

## Project Structure

```
ordermanagement/
├── src/main/java/com/springeg/ordermanagement/
│   ├── OrdermanagementApplication.java       # Main application class
│   ├── config/                               # Configuration classes
│   ├── controller/
│   │   ├── OrderController.java              # Order API endpoints
│   │   └── ProductController.java            # Product API endpoints
│   ├── domain/
│   │   ├── entity/
│   │   │   ├── Order.java                    # Order entity
│   │   │   └── Product.java                  # Product entity
│   │   └── enums/
│   │       └── OrderStatus.java              # Order status enumeration
│   ├── dto/
│   │   ├── OrderRequest.java                 # Order request DTO
│   │   ├── OrderResponse.java                # Order response DTO
│   │   ├── ProductRequest.java               # Product request DTO
│   │   └── ProductResponse.java              # Product response DTO
│   ├── exception/
│   │   ├── BusinessException.java            # Custom business exception
│   │   ├── GlobalExceptionHandler.java       # Global exception handler
│   │   ├── InsufficientStockException.java   # Stock exception
│   │   └── ResourceNotFoundException.java    # Resource not found exception
│   ├── mapper/
│   │   └── OrderMapper.java                  # Entity to DTO mapping
│   ├── repository/
│   │   ├── OrderRepository.java              # Order data access
│   │   └── ProductRepository.java            # Product data access
│   ├── service/
│   │   ├── OrderService.java                 # Order business logic interface
│   │   ├── ProductService.java               # Product business logic interface
│   │   └── impl/
│   │       ├── OrderServiceImpl.java          # Order service implementation
│   │       └── ProductServiceImpl.java        # Product service implementation
│   └── util/                                 # Utility classes
├── src/main/resources/
│   ├── application.properties                # Application configuration
│   └── application.yaml                      # YAML configuration
└── src/test/                                 # Test classes
```

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

### Installation

1. Clone the repository:
```bash
git clone git https://github.com/thakuranshu-dev/Order-Management.git
cd Order-Management
```

2. Build the project:
```bash
./mvnw clean build
```

3. Run the application:
```bash
./mvnw spring-boot:run
```

The application will start on the default port (usually 8080).

## API Endpoints

### Product Endpoints
- `GET /api/products` - Get all products
- `GET /api/products/{id}` - Get product by ID
- `POST /api/products` - Create a new product
- `PUT /api/products/{id}` - Update product
- `DELETE /api/products/{id}` - Delete product

### Order Endpoints
- `GET /api/orders` - Get all orders
- `GET /api/orders/{id}` - Get order by ID
- `POST /api/orders` - Create a new order
- `PUT /api/orders/{id}` - Update order status
- `DELETE /api/orders/{id}` - Delete order

## Configuration

Configure the application by updating the properties in:
- `src/main/resources/application.properties`
- `src/main/resources/application.yaml`

## Exception Handling

The application includes a global exception handler that catches and returns appropriate HTTP responses for:
- `BusinessException` - General business logic errors
- `InsufficientStockException` - When product stock is insufficient
- `ResourceNotFoundException` - When a requested resource is not found

## Testing

Run tests using:
```bash
./mvnw test
```

## Development

### Building
```bash
./mvnw clean package
```

### Running Tests
```bash
./mvnw test
```

### Check for Build Issues
```bash
./mvnw clean install
```

## License

This project is licensed under the MIT License.

## Author

Created as a Spring Boot order management system example by thakuranshu-dev.
