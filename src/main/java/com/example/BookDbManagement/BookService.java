package com.example.BookDbManagement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
       return bookRepository.findAll();
    }

    public void updateBook(int id, Book updatedBook) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();

            // Update the existing book with new details
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setIsbn(updatedBook.getIsbn());
            existingBook.setPublicationDate(updatedBook.getPublicationDate());

            // Save the updated book to the repository
            bookRepository.save(existingBook);
        } else {
            // Handle the case where the book with the given ID is not found
            throw new RuntimeException("Book not found with id: " + id);
        }
    }


    public void deleteBook(int id) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {
            // Delete the book with the given ID
            bookRepository.deleteById(id);
        } else {
            // Handle the case where the book with the given ID is not found
            throw new RuntimeException("Book not found with id: " + id);
        }
    }

    public Book getBookById(int id) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            throw new RuntimeException("Book not found with id: " + id);
        }
    }
}
