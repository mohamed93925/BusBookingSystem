package com.BookingApp;

public class Booking {

    private String bookingId;
    private String passengerId;
    private String busId;
    private int seatNumber;
    private String bookingDate;
    private String status;

    public Booking(String bookingId, String passengerId, String busId,
                   int seatNumber, String bookingDate, String status) {

        this.bookingId = bookingId;
        this.passengerId = passengerId;
        this.busId = busId;
        this.seatNumber = seatNumber;
        this.bookingDate = bookingDate;
        this.status = status;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public String getBusId() {
        return busId;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void displayBooking() {
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Passenger ID: " + passengerId);
        System.out.println("Bus ID: " + busId);
        System.out.println("Seat Number: " + seatNumber);
        System.out.println("Booking Date: " + bookingDate);
        System.out.println("Status: " + status);
    }
}