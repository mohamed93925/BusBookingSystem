package com.BookingApp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        BusManager manager = new BusManager();
        BookingManager bookingManager = new BookingManager();

        // Bus 1
        Bus bus1 = new Bus(
                "BUS01",
                "TN01AB1234",
                "Chennai Express",
                "Chennai",
                "Bangalore",
                "15-09-2026",
                "08:00 AM",
                "02:00 PM",
                750.00,
                40,
                40
        );

        // Bus 2
        Bus bus2 = new Bus(
                "BUS02",
                "TN02CD5678",
                "Hyderabad Express",
                "Chennai",
                "Hyderabad",
                "16-09-2026",
                "07:30 AM",
                "04:00 PM",
                900.00,
                40,
                40
        );

        // Bus 3
        Bus bus3 = new Bus(
                "BUS03",
                "TN03EF9012",
                "Bangalore Express",
                "Chennai",
                "Bangalore",
                "16-09-2026",
                "09:00 AM",
                "03:00 PM",
                800.00,
                40,
                40
        );

        // Add buses
        manager.addBus(bus1);
        manager.addBus(bus2);
        manager.addBus(bus3);


        // Main Menu
        int choice;

        do {

            System.out.println("\n================================");
            System.out.println("       BUS BOOKING SYSTEM");
            System.out.println("================================");
            System.out.println("1. View Buses");
            System.out.println("2. Search Bus");
            System.out.println("3. Book Ticket");
            System.out.println("4. Cancel Ticket");
            System.out.println("5. View Bookings");
            System.out.println("6. Exit");
            System.out.println("================================");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();


            switch (choice) {

                case 1:
                    System.out.println("\n--- AVAILABLE BUSES ---");
                    manager.displayBuses();
                    break;

                case 2:

                    System.out.print("Enter source: ");
                    String source = scanner.nextLine();

                    System.out.print("Enter destination: ");
                    String destination = scanner.nextLine();

                    manager.searchBus(source, destination);

                    break;

                case 3:

                    System.out.print("Enter passenger ID: ");
                    String passengerId = scanner.nextLine();

                    System.out.print("Enter passenger name: ");
                    String passengerName = scanner.nextLine();

                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();

                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();

                    Passenger passenger = new Passenger(
                            passengerId,
                            passengerName,
                            phoneNumber,
                            email
                    );

                    System.out.print("Enter Bus ID: ");
                    String busId = scanner.nextLine();

                    Bus selectedBus = null;

                    if (busId.equalsIgnoreCase(bus1.getBusId())) {
                        selectedBus = bus1;
                    }
                    else if (busId.equalsIgnoreCase(bus2.getBusId())) {
                        selectedBus = bus2;
                    }
                    else if (busId.equalsIgnoreCase(bus3.getBusId())) {
                        selectedBus = bus3;
                    }

                    if (selectedBus == null) {
                        System.out.println("Bus not found.");
                        break;
                    }

                    System.out.print("Enter seat number: ");
                    int seatNumber = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter booking ID: ");
                    String bookingId = scanner.nextLine();

                    System.out.print("Enter booking date: ");
                    String bookingDate = scanner.nextLine();

                    bookingManager.bookTicket(
                            selectedBus,
                            passenger,
                            bookingId,
                            seatNumber,
                            bookingDate
                    );

                    break;

                case 4:

                    System.out.print("Enter Booking ID: ");
                    String cancelBookingId = scanner.nextLine();

                    System.out.print("Enter Bus ID: ");
                    String cancelBusId = scanner.nextLine();

                    Bus cancelBus = null;

                    if (cancelBusId.equalsIgnoreCase(bus1.getBusId())) {
                        cancelBus = bus1;
                    }
                    else if (cancelBusId.equalsIgnoreCase(bus2.getBusId())) {
                        cancelBus = bus2;
                    }
                    else if (cancelBusId.equalsIgnoreCase(bus3.getBusId())) {
                        cancelBus = bus3;
                    }

                    if (cancelBus == null) {
                        System.out.println("Bus not found.");
                        break;
                    }

                    bookingManager.cancelBooking(
                            cancelBookingId,
                            cancelBus
                    );

                    break;

                case 5:
                    bookingManager.displayBookings();
                    break;

                case 6:
                    System.out.println("Thank you for using Bus Booking System.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 6);


        scanner.close();
    }
}