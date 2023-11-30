package com.example.BookDbManagement;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
@Table(name = "book")
public class Book {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     int bookId;
     String title;
     String author;
     String isbn;
     Date publicationDate;

}
