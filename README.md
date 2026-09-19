# Bus Booking System

A Java-based Bus Booking System that manages buses and bookings using **Core Java, JDBC, and MySQL**. The project also provides REST-style APIs using Java's built-in HTTP server and can be tested using Postman.

## Features

* Bus management

    * Add bus
    * View all buses
    * Search bus by ID
    * Update bus details
    * Delete bus

* Booking management

    * Create booking
    * View bookings
    * Search booking by ID
    * Delete booking

* Passenger management

* User management

* Admin functionality

* MySQL database integration

* JDBC-based database operations

* REST-style HTTP API

* Postman API testing

## Technologies Used

* **Java**
* **Core Java / OOP**
* **JDBC**
* **MySQL**
* **MySQL Connector/J**
* **Java HTTP Server (`com.sun.net.httpserver`)**
* **Postman**
* **Git & GitHub**
* **IntelliJ IDEA**

## Project Structure

```text
BusBookingSystem
│
├── src
│   └── com
│       └── BookingApp
│           │
│           ├── Admin.java
│           ├── AdminManager.java
│           ├── Booking.java
│           ├── BookingDAO.java
│           ├── BookingManager.java
│           ├── Bus.java
│           ├── BusDAO.java
│           ├── BusManager.java
│           ├── DatabaseConnection.java
│           ├── Main.java
│           ├── Passenger.java
│           ├── PassengerDAO.java
│           ├── User.java
│           ├── UserManager.java
│           │
│           └── api
│               ├── ApiServer.java
│               ├── BusApi.java
│               └── BookingApi.java
│
└── README.md
```

## Application Architecture

```text
User
  │
  ├── Console Application
  │       │
  │       ▼
  │     Manager
  │       │
  │       ▼
  │      DAO
  │       │
  │       ▼
  │     JDBC
  │       │
  │       ▼
  │     MySQL
  │
  └── Postman
          │
          ▼
      HTTP API
          │
          ▼
       BusApi /
     BookingApi
          │
          ▼
         DAO
          │
          ▼
        MySQL
```

## Database

The application uses MySQL database:

```text
bus_booking
```

Main tables include:

```text
buses
Passenger
Bookings
Users
```

### Bus Table

The `buses` table stores:

* Bus ID
* Bus number
* Bus name
* Source
* Destination
* Journey date
* Departure time
* Arrival time
* Price
* Total seats
* Available seats

### Booking Table

The `Bookings` table stores:

* Booking ID
* Passenger ID
* Bus ID
* Seat number
* Booking date
* Booking status

## JDBC

The project uses **JDBC (Java Database Connectivity)** to communicate between the Java application and MySQL database.

The database operations are separated into DAO classes such as:

```text
BusDAO
BookingDAO
PassengerDAO
```

`DatabaseConnection` is responsible for creating the MySQL database connection.

## API

The project provides REST-style HTTP APIs using Java's built-in:

```text
com.sun.net.httpserver.HttpServer
```

The API runs on:

```text
http://localhost:8080
```

### Test API

```http
GET /api/test
```

Example:

```text
http://localhost:8080/api/test
```

### Bus APIs

Get all buses:

```http
GET /api/buses
```

Get a bus by ID:

```http
GET /api/buses/{busId}
```

Add a bus:

```http
POST /api/buses
```

Update a bus:

```http
PUT /api/buses/{busId}
```

Delete a bus:

```http
DELETE /api/buses/{busId}
```

### Booking APIs

Get all bookings:

```http
GET /api/bookings
```

Get booking by ID:

```http
GET /api/bookings/{bookingId}
```

Create a booking:

```http
POST /api/bookings
```

Delete a booking:

```http
DELETE /api/bookings/{bookingId}
```

## Example API Request

### Add Bus

```json
{
    "busId": "BUS04",
    "busNumber": "TN04GH3456",
    "busName": "Coimbatore Express",
    "source": "Chennai",
    "destination": "Coimbatore",
    "journeyDate": "20-09-2026",
    "departureTime": "07:00 AM",
    "arrivalTime": "02:00 PM",
    "price": 850,
    "totalSeats": 40,
    "availableSeats": 40
}
```

## Postman Testing

The APIs were tested using **Postman**.

Tested operations include:

* GET all buses
* GET bus by ID
* POST bus
* PUT bus
* DELETE bus
* GET all bookings
* GET booking by ID
* POST booking
* DELETE booking

## How to Run

### 1. Clone the repository

```bash
git clone https://github.com/mohamed93925/BusBookingSystem.git
```

### 2. Open the project

Open the project in **IntelliJ IDEA**.

### 3. Configure MySQL

Create the database:

```sql
CREATE DATABASE bus_booking;
```

Create the required tables and insert the required sample data.

### 4. Configure Database Connection

Open:

```text
src/com/BookingApp/DatabaseConnection.java
```

Update the MySQL username and password according to your local MySQL installation.

### 5. Add MySQL Connector/J

Add the MySQL Connector/J JAR to the project libraries.

### 6. Run the Console Application

Run:

```text
Main.java
```

### 7. Run the API Server

Run:

```text
ApiServer.java
```

The API will start on:

```text
http://localhost:8080
```

### 8. Test with Postman

Use the API endpoints listed above to test the application.

## Future Improvements

Possible future improvements include:

* Authentication and authorization
* Persistent user management
* Seat availability validation
* Input validation
* Better exception handling
* Payment integration
* Front-end application
* Production-ready JSON processing
* Deployment to a cloud server

## Author

**Mohamed S**

GitHub:

```text
https://github.com/mohamed93925/BusBookingSystem
```
