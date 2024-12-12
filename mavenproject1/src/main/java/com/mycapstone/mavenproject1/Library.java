/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycapstone.mavenproject1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.HashMap;


public class Library {
    private static Library instance; // Singleton instance
    private Map<String, Book> books = new HashMap<>(); // Local storage for books

    // Get Singleton instance
    public static synchronized Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    // Add Book
    public void addBook(Book book) {
        books.put(book.getIsbn(), book);
        System.out.println("Book added successfully!");
    }

    // Delete Book by ISBN
    public void deleteBook(String isbn) {
        if (books.remove(isbn) != null) {
            System.out.println("Book deleted successfully!");
        } else {
            System.out.println("Book not found!");
        }
    }

    // Update Book
    public void updateBook(Book book) {
        if (books.containsKey(book.getIsbn())) {
            books.put(book.getIsbn(), book);
            System.out.println("Book updated successfully!");
        } else {
            System.out.println("Book not found!");
        }
    }
    
    // Search Book by ISBN
    public Book searchBook(String isbn) {
        return books.get(isbn);
    }
    // Upload Book Image
    public void uploadBookImage(String isbn, File imageFile) {
        try {
            File destination = new File("images/" + isbn + ".jpg");
            destination.getParentFile().mkdirs(); // Ensure the directory exists
            Files.copy(imageFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Image uploaded successfully for ISBN: " + isbn);
        } catch (IOException e) {
            System.out.println("Failed to upload image: " + e.getMessage());
        }
    }
}
