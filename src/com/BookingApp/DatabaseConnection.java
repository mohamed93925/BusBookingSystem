package com.BookingApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/bus_booking";

    private static final String USERNAME = "root";

    private static final String PASSWORD =
            "9777";

    public static Connection getConnection() {

        try {

            Connection connection = DriverManager.getConnection(
                    URL,
                    USERNAME,
                    PASSWORD
            );

            return connection;

        } catch (SQLException e) {

            System.out.println("Database connection failed!");
            e.printStackTrace();

            return null;
        }
    }
}