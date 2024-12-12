/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycapstone.mavenproject1;

// Base User class
public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Polymorphism: Allow different behavior for Admin and General Users
    public void showDashboard() {
        System.out.println("Welcome to the user dashboard!");
    }
}

// AdminUser inherits from User
class AdminUser extends User {
    public AdminUser(String username, String password) {
        super(username, password);
    }

    @Override
    public void showDashboard() {
        System.out.println("Welcome to the admin dashboard!");
    }
}

// GeneralUser inherits from User
class GeneralUser extends User {
    public GeneralUser(String username, String password) {
        super(username, password);
    }

    @Override
    public void showDashboard() {
        System.out.println("Welcome to the general user dashboard!");
    }
}

