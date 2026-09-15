    package com.BookingApp;

    public class User {

        private String userId;
        private String email;
        private String password;
        private String name;
        private String phoneNo;

        public User(String userId,String email,String password,String name, String phoneNo){
            this.userId = userId;
            this.email = email;
            this.password=password;
            this.name= name;
            this.phoneNo = phoneNo;

        }

        public String getUserId(){
            return userId;

        }
        public String getEmail(){
            return email;

        }
        public String getPassword(){
            return password;

        }
        public String getName(){
            return name;

        }
        public String getPhoneNo(){
            return phoneNo;

        }

    }

