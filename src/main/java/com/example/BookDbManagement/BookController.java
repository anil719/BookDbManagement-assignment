package com.example.BookDbManagement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")
    //endpoint to add book in db
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        try {
            bookService.addBook(book);
            return new ResponseEntity<>("Book added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error adding book: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to retrieve all book entries
    @GetMapping("/get")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<String> getBookById(@PathVariable int id) {
        try {
            bookService.getBookById(id);
            return new ResponseEntity<>("Book retrived successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Error in retriving book: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    // Endpoint to update an existing book entry
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
        try {
            bookService.updateBook(id, updatedBook);
            return new ResponseEntity<>("Book updated successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Error updating book: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to delete an existing book in the database
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBook(@PathVariable int bookId){
        try {
            bookService.deleteBook(bookId);
            return new ResponseEntity<>("Book has been deleted successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Book is not present : " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
