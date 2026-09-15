package com.BookingApp;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection{

    public static Connection getConnection(){

        Connection connection = null;

        try{

            String url = "jdbc:mysql://localhost3306:/Bus_Booking_System";
            String username = "root";
            String password = "9777";

            connection = DriverManager.getConnection(url,username,password);

            System.out.println("Database Connected Successfully");

        } catch (Exception e) {
            System.out.println("Database connection failed!!!");
            e.printStackTrace();
        }

        return connection;

    }



}
