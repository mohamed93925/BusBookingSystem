package com.BookingApp;

import java.util.ArrayList;
import java.util.Scanner;

public class UserManager {

    private ArrayList<User> users = new ArrayList<>();

    public void registerUser(Scanner scanner) {

        System.out.println("\n--- User Registration ---");

        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();

        if (userId.equals(password)) {

            System.out.println(
                    "User ID and password cannot be the same."
            );

            return;
        }

        User user = new User(
                userId,
                email,
                password,
                username,
                phone
        );

        users.add(user);

        System.out.println("User registered successfully!");
    }

    public boolean loginUser(Scanner scanner) {

        System.out.println("\n--- User Login ---");

        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        for (int i = 0; i < users.size(); i++) {

            User user = users.get(i);

            if (
                    user.getUserId().equals(userId)
                            &&
                            user.getPassword().equals(password)
            ) {

                System.out.println(
                        "Login successful. Welcome "
                                + user.getUsername()
                );

                return true;
            }
        }

        System.out.println("Invalid User ID or Password.");

        return false;
    }
}