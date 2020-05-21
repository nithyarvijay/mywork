package com.usecase.develop.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.usecase.develop.entities.Book;
 
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
 
}
