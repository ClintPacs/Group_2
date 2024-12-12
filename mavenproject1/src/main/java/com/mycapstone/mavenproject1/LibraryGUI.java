/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycapstone.mavenproject1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class LibraryGUI extends JFrame {
    private JTextField isbnField, titleField, authorField;
    private JButton addButton, deleteButton, updateButton, uploadImageButton, searchButton;
    private Library library;

    public LibraryGUI() {
        library = Library.getInstance(); // Use Singleton instance

        setTitle("Library Management System");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Color primaryColor = new Color(127, 4, 4); // RGB for #7F0404
        Color secondaryColor = new Color(253, 222, 84); // RGB for #FDDE54

        // Set the layout of the main frame to BorderLayout
        setLayout(new BorderLayout()); // Change layout to BorderLayout

        // Create and add the title panel
        JPanel titlePanel = new JPanel();
        titlePanel.add(new JLabel("Library Management"));
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(primaryColor);
        titlePanel.setForeground(Color.WHITE);
        add(titlePanel, BorderLayout.NORTH); // Add title panel to the top

        // Create a panel for the main content (ISBN, Title, Author, Buttons)
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(6, 2)); // GridLayout for content panel

        // Components for content
        JLabel isbnLabel = new JLabel("ISBN:");
        isbnField = new JTextField();

        JLabel titleLabel = new JLabel("Title:");
        titleField = new JTextField();

        JLabel authorLabel = new JLabel("Author:");
        authorField = new JTextField();

        addButton = new JButton("Add Book");
        addButton.setBackground(secondaryColor);
        addButton.setForeground(Color.BLACK);
        
        deleteButton = new JButton("Delete Book");
        deleteButton.setBackground(secondaryColor);
        deleteButton.setForeground(Color.BLACK);
        
        updateButton = new JButton("Update Book");
        updateButton.setBackground(secondaryColor);
        updateButton.setForeground(Color.BLACK);
        
        uploadImageButton = new JButton("Upload Image");
        uploadImageButton.setBackground(secondaryColor);
        uploadImageButton.setForeground(Color.BLACK);
        
        searchButton = new JButton("Search Book");
        searchButton.setBackground(secondaryColor);
        searchButton.setForeground(Color.BLACK);

        // Add components to the content panel
        contentPanel.add(isbnLabel);
        contentPanel.add(isbnField);

        contentPanel.add(titleLabel);
        contentPanel.add(titleField);

        contentPanel.add(authorLabel);
        contentPanel.add(authorField);

        contentPanel.add(addButton);
        contentPanel.add(deleteButton);

        contentPanel.add(updateButton);
        contentPanel.add(uploadImageButton);
        
        contentPanel.add(searchButton);
        
        // Add content panel to the center of the frame
        add(contentPanel, BorderLayout.CENTER); 

        // Button actions
        addButton.addActionListener(this::addBook);
        deleteButton.addActionListener(this::deleteBook);
        updateButton.addActionListener(this::updateBook);
        uploadImageButton.addActionListener(this::uploadImage);
        searchButton.addActionListener(e -> searchBook());
        
        setVisible(true);
    }

    private void addBook(ActionEvent e) {
        String isbn = isbnField.getText();
        String title = titleField.getText();
        String author = authorField.getText();

        if (isbn.isEmpty() || title.isEmpty() || author.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!");
            return;
        }

        Book book = new Book(isbn, title, author);
        library.addBook(book);
        JOptionPane.showMessageDialog(this, "Book Added Successfully");
        clearFields();
    }

    private void deleteBook(ActionEvent e) {
        String isbn = isbnField.getText();

        if (isbn.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an ISBN to delete!");
            return;
        }

        library.deleteBook(isbn);
        JOptionPane.showMessageDialog(this, "Book Deleted Successfully");
        clearFields();
    }

    private void updateBook(ActionEvent e) {
        String isbn = isbnField.getText();
        String title = titleField.getText();
        String author = authorField.getText();

        if (isbn.isEmpty() || title.isEmpty() || author.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!");
            return;
        }

        Book book = new Book(isbn, title, author);
        library.updateBook(book);
        JOptionPane.showMessageDialog(this, "Book Updated Successfully");
        clearFields();
    }

    private void uploadImage(ActionEvent e) {
        String isbn = isbnField.getText();

        if (isbn.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an ISBN before uploading an image!");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            library.uploadBookImage(isbn, selectedFile);
            JOptionPane.showMessageDialog(this, "Image Added Successfully");
            clearFields();
        }
    }

    // Placeholder searchBook method
    private void searchBook() {
    String isbn = isbnField.getText();
    if (isbn.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter an ISBN to search for the book.");
        return;
    }

    Book book = library.searchBook(isbn);
    if (book != null) {
        titleField.setText(book.getTitle());
        authorField.setText(book.getAuthor());
        JOptionPane.showMessageDialog(this, "Book Found!");
    } else {
        JOptionPane.showMessageDialog(this, "Book not found.");
        clearFields();
    }
}


    public static void main(String[] args) {
        new LibraryGUI();
    }

    private void clearFields() {
        isbnField.setText("");
        authorField.setText("");
        titleField.setText("");
    }
}
