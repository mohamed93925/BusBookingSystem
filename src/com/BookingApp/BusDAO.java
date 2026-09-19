package com.BookingApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BusDAO {

    // ==============================
    // ADD BUS
    // ==============================

    public boolean addBus(Bus bus) {

        String sql =
                "INSERT INTO buses " +
                        "(bus_id, bus_number, bus_name, Route_source, " +
                        "Route_destination, journey_date, departure_time, " +
                        "arrival_time, price, total_seats, available_seats) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    bus.getBusId()
            );

            statement.setString(
                    2,
                    bus.getBusNumber()
            );

            statement.setString(
                    3,
                    bus.getBusName()
            );

            statement.setString(
                    4,
                    bus.getSource()
            );

            statement.setString(
                    5,
                    bus.getDestination()
            );

            statement.setString(
                    6,
                    bus.getJourneyDate()
            );

            statement.setString(
                    7,
                    bus.getDepartureTime()
            );

            statement.setString(
                    8,
                    bus.getArrivalTime()
            );

            statement.setDouble(
                    9,
                    bus.getPrice()
            );

            statement.setInt(
                    10,
                    bus.getTotalSeats()
            );

            statement.setInt(
                    11,
                    bus.getAvailableSeats()
            );

            return statement.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println(
                    "Error while adding bus:"
            );

            e.printStackTrace();

            return false;
        }
    }


    // ==============================
    // GET ALL BUSES
    // ==============================

    public List<Bus> getAllBuses() {

        List<Bus> buses =
                new ArrayList<>();

        String sql =
                "SELECT " +
                        "bus_id, " +
                        "bus_number, " +
                        "bus_name, " +
                        "Route_source, " +
                        "Route_destination, " +
                        "journey_date, " +
                        "departure_time, " +
                        "arrival_time, " +
                        "price, " +
                        "total_seats, " +
                        "available_seats " +
                        "FROM buses";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Bus bus =
                        new Bus();

                bus.setBusId(
                        resultSet.getString(
                                "bus_id"
                        )
                );

                bus.setBusNumber(
                        resultSet.getString(
                                "bus_number"
                        )
                );

                bus.setBusName(
                        resultSet.getString(
                                "bus_name"
                        )
                );

                bus.setSource(
                        resultSet.getString(
                                "Route_source"
                        )
                );

                bus.setDestination(
                        resultSet.getString(
                                "Route_destination"
                        )
                );

                bus.setJourneyDate(
                        resultSet.getString(
                                "journey_date"
                        )
                );

                bus.setDepartureTime(
                        resultSet.getString(
                                "departure_time"
                        )
                );

                bus.setArrivalTime(
                        resultSet.getString(
                                "arrival_time"
                        )
                );

                bus.setPrice(
                        resultSet.getDouble(
                                "price"
                        )
                );

                bus.setTotalSeats(
                        resultSet.getInt(
                                "total_seats"
                        )
                );

                bus.setAvailableSeats(
                        resultSet.getInt(
                                "available_seats"
                        )
                );

                buses.add(bus);
            }

        } catch (Exception e) {

            System.out.println(
                    "Error while getting buses:"
            );

            e.printStackTrace();
        }

        return buses;
    }


    // ==============================
    // GET BUS BY ID
    // ==============================

    public Bus getBusById(
            String busId
    ) {

        String sql =
                "SELECT " +
                        "bus_id, " +
                        "bus_number, " +
                        "bus_name, " +
                        "Route_source, " +
                        "Route_destination, " +
                        "journey_date, " +
                        "departure_time, " +
                        "arrival_time, " +
                        "price, " +
                        "total_seats, " +
                        "available_seats " +
                        "FROM buses " +
                        "WHERE bus_id = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    busId
            );

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                Bus bus =
                        new Bus();

                bus.setBusId(
                        resultSet.getString(
                                "bus_id"
                        )
                );

                bus.setBusNumber(
                        resultSet.getString(
                                "bus_number"
                        )
                );

                bus.setBusName(
                        resultSet.getString(
                                "bus_name"
                        )
                );

                bus.setSource(
                        resultSet.getString(
                                "Route_source"
                        )
                );

                bus.setDestination(
                        resultSet.getString(
                                "Route_destination"
                        )
                );

                bus.setJourneyDate(
                        resultSet.getString(
                                "journey_date"
                        )
                );

                bus.setDepartureTime(
                        resultSet.getString(
                                "departure_time"
                        )
                );

                bus.setArrivalTime(
                        resultSet.getString(
                                "arrival_time"
                        )
                );

                bus.setPrice(
                        resultSet.getDouble(
                                "price"
                        )
                );

                bus.setTotalSeats(
                        resultSet.getInt(
                                "total_seats"
                        )
                );

                bus.setAvailableSeats(
                        resultSet.getInt(
                                "available_seats"
                        )
                );

                return bus;
            }

        } catch (Exception e) {

            System.out.println(
                    "Error while getting bus:"
            );

            e.printStackTrace();
        }

        return null;
    }


    // ==============================
    // UPDATE BUS
    // ==============================

    public boolean updateBus(
            Bus bus
    ) {

        String sql =
                "UPDATE buses SET " +
                        "bus_number=?, " +
                        "bus_name=?, " +
                        "Route_source=?, " +
                        "Route_destination=?, " +
                        "journey_date=?, " +
                        "departure_time=?, " +
                        "arrival_time=?, " +
                        "price=?, " +
                        "total_seats=?, " +
                        "available_seats=? " +
                        "WHERE bus_id=?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    bus.getBusNumber()
            );

            statement.setString(
                    2,
                    bus.getBusName()
            );

            statement.setString(
                    3,
                    bus.getSource()
            );

            statement.setString(
                    4,
                    bus.getDestination()
            );

            statement.setString(
                    5,
                    bus.getJourneyDate()
            );

            statement.setString(
                    6,
                    bus.getDepartureTime()
            );

            statement.setString(
                    7,
                    bus.getArrivalTime()
            );

            statement.setDouble(
                    8,
                    bus.getPrice()
            );

            statement.setInt(
                    9,
                    bus.getTotalSeats()
            );

            statement.setInt(
                    10,
                    bus.getAvailableSeats()
            );

            statement.setString(
                    11,
                    bus.getBusId()
            );

            return statement.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println(
                    "Error while updating bus:"
            );

            e.printStackTrace();

            return false;
        }
    }


    // ==============================
    // DELETE BUS
    // ==============================

    public boolean deleteBus(
            String busId
    ) {

        String sql =
                "DELETE FROM buses " +
                        "WHERE bus_id = ?";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    busId
            );

            return statement.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println(
                    "Error while deleting bus:"
            );

            e.printStackTrace();

            return false;
        }
    }
}