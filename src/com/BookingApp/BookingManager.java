package com.BookingApp;

import java.time.LocalDate;
import java.util.Scanner;

public class BookingManager {

    private BookingDAO bookingDAO = new BookingDAO();

    private PassengerDAO passengerDAO =
            new PassengerDAO();

    private BusDAO busDAO = new BusDAO();

    public void bookTicket(Scanner scanner) {

        System.out.println("\n--- Book Ticket ---");

        System.out.print("Passenger ID: ");
        String passengerId = scanner.nextLine();

        Passenger passenger =
                passengerDAO.getPassengerById(passengerId);

        if (passenger == null) {

            System.out.println(
                    "Passenger not found."
            );

            System.out.println(
                    "Please add passenger first."
            );

            return;
        }

        System.out.print("Bus ID: ");
        String busId = scanner.nextLine();

        Bus bus = busDAO.getBusById(busId);

        if (bus == null) {

            System.out.println("Bus not found.");
            return;
        }

        if (bus.getAvailableSeats() <= 0) {

            System.out.println(
                    "No seats available."
            );

            return;
        }

        System.out.print("Seat Number: ");
        String seatNumber = scanner.nextLine();

        String bookingId =
                "BOOK" + System.currentTimeMillis();

        Booking booking = new Booking(
                bookingId,
                passengerId,
                busId,
                seatNumber,
                LocalDate.now().toString(),
                "CONFIRMED"
        );

        boolean bookingAdded =
                bookingDAO.addBooking(booking);

        if (bookingAdded) {

            bus.setAvailableSeats(
                    bus.getAvailableSeats() - 1
            );

            busDAO.updateBus(bus);

            System.out.println(
                    "Ticket booked successfully!"
            );

            System.out.println(
                    "Booking ID: " + bookingId
            );

        } else {

            System.out.println(
                    "Booking failed."
            );
        }
    }

    public void cancelBooking(Scanner scanner) {

        System.out.println("\n--- Cancel Booking ---");

        System.out.print("Enter Booking ID: ");
        String bookingId = scanner.nextLine();

        Booking booking =
                bookingDAO.getBookingById(bookingId);

        if (booking == null) {

            System.out.println(
                    "Booking not found."
            );

            return;
        }

        boolean updated =
                bookingDAO.updateBookingStatus(
                        bookingId,
                        "CANCELLED"
                );

        if (updated) {

            Bus bus =
                    busDAO.getBusById(
                            booking.getBusId()
                    );

            if (bus != null) {

                bus.setAvailableSeats(
                        bus.getAvailableSeats() + 1
                );

                busDAO.updateBus(bus);
            }

            System.out.println(
                    "Booking cancelled successfully."
            );

        } else {

            System.out.println(
                    "Cancellation failed."
            );
        }
    }

    public void viewBookings() {

        System.out.println("\n--- Bookings ---");

        var bookings =
                bookingDAO.getAllBookings();

        if (bookings.isEmpty()) {

            System.out.println(
                    "No bookings found."
            );

            return;
        }

        for (int i = 0; i < bookings.size(); i++) {

            Booking booking = bookings.get(i);

            System.out.println(
                    "\nBooking ID: "
                            + booking.getBookingId()
            );

            System.out.println(
                    "Passenger ID: "
                            + booking.getPassengerId()
            );

            System.out.println(
                    "Bus ID: "
                            + booking.getBusId()
            );

            System.out.println(
                    "Seat: "
                            + booking.getSeatNumber()
            );

            System.out.println(
                    "Date: "
                            + booking.getBookingDate()
            );

            System.out.println(
                    "Status: "
                            + booking.getBookingStatus()
            );
        }
    }

    public void addPassenger(Scanner scanner) {

        System.out.println("\n--- Add Passenger ---");

        System.out.print("Passenger ID: ");
        String id = scanner.nextLine();

        System.out.print("Passenger Name: ");
        String name = scanner.nextLine();

        System.out.print("Phone Number: ");
        String phone = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        Passenger passenger =
                new Passenger(
                        id,
                        name,
                        phone,
                        email
                );

        boolean result =
                passengerDAO.addPassenger(passenger);

        if (result) {

            System.out.println(
                    "Passenger added successfully!"
            );

        } else {

            System.out.println(
                    "Failed to add passenger."
            );
        }
    }
}