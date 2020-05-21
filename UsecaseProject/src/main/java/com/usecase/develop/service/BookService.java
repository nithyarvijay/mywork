package com.usecase.develop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usecase.develop.entities.Book;
import com.usecase.develop.jpa.BookRepository;

 
@Service
public class BookService {
     
    @Autowired
    BookRepository repository;
     
    /**
     * Get all the Book from database
     * 
     * @return
     */
    public List<Book> getAllBooks()
    {
        List<Book> bookList = repository.findAll();         
        if(bookList.size() > 0) {
            return bookList;
        } else {
            return new ArrayList<Book>();
        }
    }
    
    /**
     * Get the Book By Id from database
     * 
     * @return
     */
    public Book getBookById(Long id) throws BookNotFoundException 
    {
        Optional<Book> book = repository.findById(id);         
        if(book.isPresent()) {
            return book.get();
        } else {
            throw new BookNotFoundException("No Book record exist for given details");
        }
    }
     
    /**
     * Save the Books from database
     * 
     * @return
     */
    public Book createOrUpdateBook(Book entity) throws BookNotFoundException 
    {
    	if(entity.getId() != null) {
    		Optional<Book> book = repository.findById(entity.getId());         
            if(book.isPresent()) 
            {
                Book newEntity = book.get();
                newEntity.setBookName(entity.getBookName());
                newEntity.setAuthorName(entity.getAuthorName());
                newEntity.setPublisher(entity.getPublisher());
     
                newEntity = repository.save(newEntity);
                 
                return newEntity;
            }else {
            	entity = repository.save(entity);             
                return entity;
            }
    	}
        else {
            entity = repository.save(entity);             
            return entity;
        }
    } 
    
    /**
     * Delete given Book from database
     * 
     * @return
     */
    
    public void deleteBookById(Long id) throws BookNotFoundException 
    {
        Optional<Book> Book = repository.findById(id);
         
        if(Book.isPresent()) 
        {
            repository.deleteById(id);
        } else {
            throw new BookNotFoundException("No Book exists for given details");
        }
    }
    
}
