package com.BookingApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PassengerDAO {

    public boolean addPassenger(Passenger passenger) {

        String sql = "INSERT INTO Passenger " +
                "(passenger_Id, passenger_name, ph_number, email) " +
                "VALUES (?, ?, ?, ?)";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, passenger.getPassengerId());
            statement.setString(2, passenger.getPassengerName());
            statement.setString(3, passenger.getPhoneNumber());
            statement.setString(4, passenger.getEmail());

            return statement.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public Passenger getPassengerById(String passengerId) {

        String sql =
                "SELECT * FROM Passenger WHERE passenger_Id = ?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, passengerId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return new Passenger(
                        resultSet.getString("passenger_Id"),
                        resultSet.getString("passenger_name"),
                        resultSet.getString("ph_number"),
                        resultSet.getString("email")
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }
}