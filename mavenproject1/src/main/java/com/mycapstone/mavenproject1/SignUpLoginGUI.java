/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycapstone.mavenproject1;

import javax.swing.*;
import java.awt.*;


public class SignUpLoginGUI extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private SignUpAndLogin authSystem;

    public SignUpLoginGUI(SignUpAndLogin authSystem) {
        this.authSystem = authSystem;

        // Configure main frame
        setTitle("Sign Up and Log In GUI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // CardLayout for switching between sign-up and login
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add sign-up and login panels
        mainPanel.add(createSignUpPanel(), "SignUpPanel");
        mainPanel.add(createLoginPanel(), "LoginPanel");

        add(mainPanel);
        cardLayout.show(mainPanel, "SPanel");

        setVisible(true);
    }

    private JPanel createSignUpPanel() {
        JPanel signUpPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        // Components for sign-up
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JLabel roleLabel = new JLabel("Role (admin/general):");
        JTextField roleField = new JTextField();

        JButton signUpButton = new JButton("Sign Up");
        JButton backButton = new JButton("Back to Login");

        // Add components to panel
        signUpPanel.add(usernameLabel);
        signUpPanel.add(usernameField);
        
        signUpPanel.add(passwordLabel);
        signUpPanel.add(passwordField);

        signUpPanel.add(roleLabel);
        signUpPanel.add(roleField);

        signUpPanel.add(signUpButton);
        signUpPanel.add(backButton);

        // Sign-up button action
        signUpButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String role = roleField.getText();

            if (authSystem.signUp(username, password, role)) {
                JOptionPane.showMessageDialog(this, "Sign-up successful! You can now log in.");
                cardLayout.show(mainPanel, "LoginPanel");
            } else {
                JOptionPane.showMessageDialog(this, "Username already exists! Try again.");
            }
        });

        // Back button action
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "LoginPanel"));

        return signUpPanel;
    }

    private JPanel createLoginPanel() {
        JPanel loginPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        // Components for login
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Log In");
        JButton signUpButton = new JButton("Go to Sign Up");

        // Add components to panel
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);

        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);

        loginPanel.add(loginButton);
        loginPanel.add(signUpButton);

        // Login button action
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            User user = authSystem.login(username, password);
            if (user != null) {
                JOptionPane.showMessageDialog(this, "Welcome, " + user.getUsername() + "!");
                // Redirect to LibraryGUI or main application
                SwingUtilities.invokeLater(() -> {
                    new LibraryGUI();
                    dispose(); // Close the SignUpLoginGUI
                });
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password! Try again.");
            }
        });

        // Go to sign-up button action
        signUpButton.addActionListener(e -> cardLayout.show(mainPanel, "SignUpPanel"));

        return loginPanel;
    }
        
    
    
    
    public static void main(String[] args) {
        SignUpAndLogin authSystem = new SignUpAndLogin(); // Backend system
        new SignUpLoginGUI(authSystem);
    }
}

