package com.example.hellospringboot.dao;

import com.example.hellospringboot.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

//@Component
public interface BookRepository extends CrudRepository<Book,Integer>{
}