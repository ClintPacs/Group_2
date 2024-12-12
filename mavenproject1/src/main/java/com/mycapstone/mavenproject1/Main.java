/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycapstone.mavenproject1;

/**
 *
 * @author Steven Carl
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Initialize SignUpAndLogin handler
        SignUpAndLogin authHandler = new SignUpAndLogin();

        // Launch the SignUpLoginGUI with the required authHandler
        SignUpLoginGUI loginGUI = new SignUpLoginGUI(authHandler);
        loginGUI.setVisible(true);
    }
    
}
