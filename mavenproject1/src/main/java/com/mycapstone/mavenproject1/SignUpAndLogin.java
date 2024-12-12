/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycapstone.mavenproject1;

import java.util.HashMap;

public class SignUpAndLogin {
    private HashMap<String, User> users = new HashMap<>();

    // Sign-up method
    public boolean signUp(String username, String password, String role) {
        if (users.containsKey(username)) {
            System.out.println("Username already exists!");
            return false;
        }
        if (role.equalsIgnoreCase("admin")) {
            users.put(username, new AdminUser(username, password));
        } else {
            users.put(username, new GeneralUser(username, password));
        }
        System.out.println("Sign-up successful!");
        return true;
    }

    // Login method
    public User login(String username, String password) {
        if (users.containsKey(username) && users.get(username).getPassword().equals(password)) {
            System.out.println("Login successful!");
            return users.get(username);
        }
        System.out.println("Invalid credentials!");
        return null;
    }
}
