package com.BookingApp;

import java.util.Scanner;
import java.util.ArrayList;


public class AdminManager {

    Scanner scan;

    Admin admin;

    public AdminManager(Scanner scan) {
        this.scan = scan;

        admin = new Admin("admin123", "123");


    }

    public void login() {
        System.out.println();
        System.out.println("-------ADMIN LOGIN-------");

        while (true) {

            System.out.print("Enter the AdminID : ");
            String adminId = scan.nextLine();

            System.out.print("Enter the Password : ");
            String password = scan.nextLine();

            if (admin.getAdminId().equals(adminId) && admin.getPassword().equals(password)) {
                System.out.println("Admin Logged in Sucessfully...");
                break;

            } else {
                System.out.println("Invalid AdminId or Password!!!");
            }

        }


    }

}
