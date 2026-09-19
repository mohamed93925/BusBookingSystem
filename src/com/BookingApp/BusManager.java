package com.BookingApp;

import java.util.ArrayList;
import java.util.Scanner;

public class BusManager {

    private ArrayList<Bus> buses = new ArrayList<>();

    private BusDAO busDAO = new BusDAO();

    public void loadBusesFromDatabase() {

        buses = new ArrayList<>(
                busDAO.getAllBuses()
        );
    }

    public void displayBuses() {

        loadBusesFromDatabase();

        if (buses.isEmpty()) {

            System.out.println("No buses available.");
            return;
        }

        System.out.println("\n--- Available Buses ---");

        for (int i = 0; i < buses.size(); i++) {

            Bus bus = buses.get(i);

            System.out.println(
                    "\nBus ID: " + bus.getBusId()
            );

            System.out.println(
                    "Bus Number: " + bus.getBusNumber()
            );

            System.out.println(
                    "Bus Name: " + bus.getBusName()
            );

            System.out.println(
                    "Route: "
                            + bus.getSource()
                            + " -> "
                            + bus.getDestination()
            );

            System.out.println(
                    "Journey Date: "
                            + bus.getJourneyDate()
            );

            System.out.println(
                    "Departure: "
                            + bus.getDepartureTime()
            );

            System.out.println(
                    "Arrival: "
                            + bus.getArrivalTime()
            );

            System.out.println(
                    "Price: ₹" + bus.getPrice()
            );

            System.out.println(
                    "Available Seats: "
                            + bus.getAvailableSeats()
            );
        }
    }

    public void searchBus(Scanner scanner) {

        System.out.print("Enter source: ");
        String source = scanner.nextLine();

        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();

        loadBusesFromDatabase();

        boolean found = false;

        for (int i = 0; i < buses.size(); i++) {

            Bus bus = buses.get(i);

            if (
                    bus.getSource().equalsIgnoreCase(source)
                            &&
                            bus.getDestination()
                                    .equalsIgnoreCase(destination)
            ) {

                System.out.println(
                        "\nBus ID: " + bus.getBusId()
                );

                System.out.println(
                        "Bus Name: " + bus.getBusName()
                );

                System.out.println(
                        "Date: " + bus.getJourneyDate()
                );

                System.out.println(
                        "Price: ₹" + bus.getPrice()
                );

                found = true;
            }
        }

        if (!found) {

            System.out.println("No buses found.");
        }
    }
}