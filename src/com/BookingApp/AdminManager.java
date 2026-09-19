package com.BookingApp;

import java.util.Scanner;

public class AdminManager {

    private Admin admin =
            new Admin("admin", "admin123");

    public boolean loginAdmin(Scanner scanner) {

        System.out.println("\n--- Admin Login ---");

        System.out.print("Enter Admin ID: ");
        String adminId = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (
                admin.getAdminId().equals(adminId)
                        &&
                        admin.getPassword().equals(password)
        ) {

            System.out.println(
                    "Admin login successful!"
            );

            return true;
        }

        System.out.println("Invalid admin credentials.");

        return false;
    }
}