package com.usecase.develop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usecase.develop.entities.Book;
import com.usecase.develop.service.BookNotFoundException;
import com.usecase.develop.service.BookService;

@RestController
@CrossOrigin
@RequestMapping("/Books")
public class BookController 
{
    @Autowired
    BookService service;
 
    @GetMapping 
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> list = service.getAllBooks();
        System.out.println(" getAllBooks : "+list  );  
        
        return new ResponseEntity<List<Book>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}") 
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) throws BookNotFoundException {
        Book book = service.getBookById(id);
        System.out.println(" getBookById : "+book  );  
        
        return new ResponseEntity<Book>(book, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Book> createOrUpdateBook(@RequestBody Book book)
                                                    throws BookNotFoundException {
    	System.out.println(" Got data to save :: "+book.toString());
    	
        Book updated = service.createOrUpdateBook(book);
        return new ResponseEntity<Book>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    
    @DeleteMapping("/{id}")
    public HttpStatus deleteBookById(@PathVariable("id") Long id) 
                                                    throws BookNotFoundException {
        service.deleteBookById(id);
        return HttpStatus.FORBIDDEN;
    }
 
}
