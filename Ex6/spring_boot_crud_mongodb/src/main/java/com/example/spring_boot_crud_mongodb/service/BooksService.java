package com.example.spring_boot_crud_mongodb.service;

import com.example.spring_boot_crud_mongodb.model.Books;
import com.example.spring_boot_crud_mongodb.repository.BooksRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BooksService {
    List<Books> findAll();

    Books findById(String id);

    Books save(Books books);

    Books update(Books books);

    void delete(String id);

    List<Books> findBooksByName(String name);

    List<Books> findBooksBetweenDate(Date date1, Date date2);

//    List<Books> fullTextSearchByName(String name);
}
