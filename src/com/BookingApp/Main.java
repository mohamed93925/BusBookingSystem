package com.BookingApp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        UserManager userManager =
                new UserManager();

        AdminManager adminManager =
                new AdminManager();

        BusManager busManager =
                new BusManager();

        BookingManager bookingManager =
                new BookingManager();

        while (true) {

            System.out.println("\n==============================");
            System.out.println("      BUS BOOKING SYSTEM");
            System.out.println("==============================");

            System.out.println("1. User");
            System.out.println("2. Admin");
            System.out.println("3. Exit");

            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {

                userMenu(
                        scanner,
                        userManager,
                        busManager,
                        bookingManager
                );

            } else if (choice.equals("2")) {

                if (adminManager.loginAdmin(scanner)) {

                    adminMenu(
                            scanner,
                            busManager
                    );
                }

            } else if (choice.equals("3")) {

                System.out.println(
                        "Thank you for using Bus Booking System!"
                );

                break;

            } else {

                System.out.println(
                        "Invalid choice."
                );
            }
        }

        scanner.close();
    }

    private static void userMenu(
            Scanner scanner,
            UserManager userManager,
            BusManager busManager,
            BookingManager bookingManager
    ) {

        while (true) {

            System.out.println("\n--- User Menu ---");

            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Back");

            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {

                userManager.registerUser(scanner);

            } else if (choice.equals("2")) {

                boolean loggedIn =
                        userManager.loginUser(scanner);

                if (loggedIn) {

                    loggedInUserMenu(
                            scanner,
                            busManager,
                            bookingManager
                    );
                }

            } else if (choice.equals("3")) {

                break;

            } else {

                System.out.println(
                        "Invalid choice."
                );
            }
        }
    }

    private static void loggedInUserMenu(
            Scanner scanner,
            BusManager busManager,
            BookingManager bookingManager
    ) {

        while (true) {

            System.out.println(
                    "\n--- Logged In User Menu ---"
            );

            System.out.println("1. View Buses");
            System.out.println("2. Search Bus");
            System.out.println("3. Add Passenger");
            System.out.println("4. Book Ticket");
            System.out.println("5. Cancel Ticket");
            System.out.println("6. View Bookings");
            System.out.println("7. Logout");

            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {

                busManager.displayBuses();

            } else if (choice.equals("2")) {

                busManager.searchBus(scanner);

            } else if (choice.equals("3")) {

                bookingManager.addPassenger(scanner);

            } else if (choice.equals("4")) {

                bookingManager.bookTicket(scanner);

            } else if (choice.equals("5")) {

                bookingManager.cancelBooking(scanner);

            } else if (choice.equals("6")) {

                bookingManager.viewBookings();

            } else if (choice.equals("7")) {

                break;

            } else {

                System.out.println(
                        "Invalid choice."
                );
            }
        }
    }

    private static void adminMenu(
            Scanner scanner,
            BusManager busManager
    ) {

        while (true) {

            System.out.println("\n--- Admin Menu ---");

            System.out.println("1. View Buses");
            System.out.println("2. Back");

            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {

                busManager.displayBuses();

            } else if (choice.equals("2")) {

                break;

            } else {

                System.out.println(
                        "Invalid choice."
                );
            }
        }
    }
}