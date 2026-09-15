package com.BookingApp;

import java.util.ArrayList;

public class BookingManager {

    private ArrayList<Booking> bookings;

    public BookingManager() {
        bookings = new ArrayList<>();
    }

    // Add a booking
    public void addBooking(Booking booking) {
        bookings.add(booking);
        System.out.println("Booking added successfully.");
    }

    // Display all bookings
    public void displayBookings() {

        if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
            return;
        }

        for (int i = 0; i < bookings.size(); i++) {

            Booking booking = bookings.get(i);

            booking.displayBooking();

            System.out.println("-------------------------");
        }
    }

    // Check whether a seat is already booked
    public boolean isSeatBooked(String busId, int seatNumber) {

        for (int i = 0; i < bookings.size(); i++) {

            Booking booking = bookings.get(i);

            if (booking.getBusId().equals(busId)
                    && booking.getSeatNumber() == seatNumber
                    && booking.getStatus().equalsIgnoreCase("CONFIRMED")) {

                return true;
            }
        }

        return false;
    }

    // Book a ticket
    public void bookTicket(Bus bus, Passenger passenger,
                           String bookingId, int seatNumber,
                           String bookingDate) {

        // Check seat number
        if (seatNumber < 1 || seatNumber > bus.getTotalSeats()) {

            System.out.println("Invalid seat number.");
            return;
        }

        // Check available seats
        if (bus.getAvailableSeats() <= 0) {

            System.out.println("No seats available.");
            return;
        }

        // Check duplicate seat
        if (isSeatBooked(bus.getBusId(), seatNumber)) {

            System.out.println("Seat " + seatNumber + " is already booked.");
            return;
        }

        // Create booking
        Booking booking = new Booking(
                bookingId,
                passenger.getPassengerId(),
                bus.getBusId(),
                seatNumber,
                bookingDate,
                "CONFIRMED"
        );

        // Add booking
        bookings.add(booking);

        // Reduce available seats
        bus.setAvailableSeats(bus.getAvailableSeats() - 1);

        System.out.println("Booking successful!");
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Seat Number: " + seatNumber);
        System.out.println("Remaining Seats: " + bus.getAvailableSeats());
    }

    // Find booking by Booking ID
    public Booking findBooking(String bookingId) {

        for (int i = 0; i < bookings.size(); i++) {

            Booking booking = bookings.get(i);

            if (booking.getBookingId().equals(bookingId)) {

                return booking;
            }
        }

        return null;
    }
    public void cancelBooking(String bookingId, Bus bus) {

        Booking booking = findBooking(bookingId);

        if (booking == null) {

            System.out.println("Booking not found.");
            return;
        }

        if (booking.getStatus().equalsIgnoreCase("CANCELLED")) {

            System.out.println("Booking is already cancelled.");
            return;
        }

        booking.setStatus("CANCELLED");

        bus.setAvailableSeats(bus.getAvailableSeats() + 1);

        System.out.println("Booking cancelled successfully.");
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Seat Number: " + booking.getSeatNumber());
        System.out.println("Available Seats: " + bus.getAvailableSeats());
    }
}