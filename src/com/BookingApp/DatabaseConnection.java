package com.BookingApp;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection{

    public static Connection getConnection(){

        Connection connection = null;

        try{

            String url = "jdbc:mysql://localhost:3306/bus_booking";
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
