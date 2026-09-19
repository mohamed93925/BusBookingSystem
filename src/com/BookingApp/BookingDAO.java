package com.BookingApp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    public boolean addBooking(Booking booking) {

        String sql = "INSERT INTO Bookings " +
                "(Booking_Id, Passenger_Id, busId, seatNumber, " +
                "booking_Date, booking_status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, booking.getBookingId());
            statement.setString(2, booking.getPassengerId());
            statement.setString(3, booking.getBusId());
            statement.setString(4, booking.getSeatNumber());
            statement.setString(5, booking.getBookingDate());
            statement.setString(6, booking.getBookingStatus());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }

    public Booking getBookingById(String bookingId) {

        String sql =
                "SELECT * FROM Bookings WHERE Booking_Id = ?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, bookingId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return new Booking(
                        resultSet.getString("Booking_Id"),
                        resultSet.getString("Passenger_Id"),
                        resultSet.getString("busId"),
                        resultSet.getString("seatNumber"),
                        resultSet.getString("booking_Date"),
                        resultSet.getString("booking_status")
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }

    public List<Booking> getAllBookings() {

        List<Booking> bookings = new ArrayList<>();

        String sql = "SELECT * FROM Bookings";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Booking booking = new Booking(
                        resultSet.getString("Booking_Id"),
                        resultSet.getString("Passenger_Id"),
                        resultSet.getString("busId"),
                        resultSet.getString("seatNumber"),
                        resultSet.getString("booking_Date"),
                        resultSet.getString("booking_status")
                );

                bookings.add(booking);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return bookings;
    }

    public boolean updateBookingStatus(
            String bookingId,
            String status
    ) {

        String sql =
                "UPDATE Bookings SET booking_status=? " +
                        "WHERE Booking_Id=?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, status);
            statement.setString(2, bookingId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteBooking(String bookingId) {

        String sql =
                "DELETE FROM Bookings WHERE Booking_Id=?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, bookingId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }
}