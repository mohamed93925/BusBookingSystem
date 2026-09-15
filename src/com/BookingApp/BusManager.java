package com.BookingApp;

import java.util.ArrayList;

public class BusManager {

    private ArrayList<Bus> buses;

    public BusManager() {
        buses = new ArrayList<>();
    }

    // Add a bus
    public void addBus(Bus bus) {
        buses.add(bus);
        System.out.println("Bus added successfully.");
    }

    // Display all buses
    public void displayBuses() {

        if (buses.isEmpty()) {
            System.out.println("No buses available.");
            return;
        }

        for (int i = 0; i < buses.size(); i++) {
            buses.get(i).displayBus();
            System.out.println("-------------------------");
        }
    }

    // Search bus by source and destination
    public void searchBus(String source, String destination) {

        boolean found = false;

        for (int i = 0; i < buses.size(); i++) {

            Bus bus = buses.get(i);

            if (bus.getSource().equalsIgnoreCase(source)
                    && bus.getDestination().equalsIgnoreCase(destination)) {

                bus.displayBus();
                System.out.println("-------------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No bus found for this route.");
        }
    }
}