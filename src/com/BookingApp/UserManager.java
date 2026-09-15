package com.BookingApp;


import java.util.ArrayList;
import java.util.Scanner;

public class UserManager {

    ArrayList<User> users = new ArrayList<>();
    Scanner scan;

    public UserManager(Scanner scan){

        this.scan = scan;

    }
     public void Register(){

         System.out.println();
         System.out.println("-----------USER REGISTRATION-----------");

         System.out.print("Enter your name : ");
         String name = scan.nextLine();

         System.out.print("Enter your email : ");
         String email = scan.nextLine();

         System.out.print("Enter UserId : ");
         String userId = scan.nextLine();


         for (int i = 0; i < users.size(); i++) {

             User user = users.get(i);

             if (user.getUserId().equals(userId)) {

                 System.out.println("User ID already exists!");
                 System.out.println("Please register again with a different User ID.");

             }
         }

         System.out.print("Enter your Password : ");
         String password = scan.nextLine();

         while (userId.equals(password)) {

             System.out.println("User ID and Password cannot be the same!");
             System.out.print("Please Re-enter the password : ");

             password = scan.nextLine();
         }

         System.out.print("Enter your Phone number : ");
         String phoneNo = scan.nextLine();

         System.out.println("Registration Sucessfull...");


         User user = new User( userId,  email,  password,  name,  phoneNo);
         users.add(user);





    }

    public void Login(){

        System.out.println();
        System.out.println("---------USER LOGIN---------");

        System.out.print("Enter the UserId : ");
        String userId = scan.nextLine();

        System.out.print("Enter the Password : ");
        String Password = scan.nextLine();

        boolean found = false;

        for(int i =0;i<users.size();i++){
            User user = users.get(i);

            if(user.getUserId().equals(userId) && user.getPassword().equals(Password)){
                System.out.println("Logged in Successfully...");
                System.out.println("Welcome " + user.getName());

                 found = true;
                break;

            }

        }
        if(!found){
            System.out.println("Invalid userId or Password");
        }



    }

}
