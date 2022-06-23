package com.example.spring_boot_crud_mongodb.service.Impl;

import com.example.spring_boot_crud_mongodb.model.Books;
import com.example.spring_boot_crud_mongodb.repository.BooksRepository;
import com.example.spring_boot_crud_mongodb.service.BooksService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class BooksServiceImpl implements BooksService {

    private final BooksRepository booksRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public List<Books> findAll() {
        return booksRepository.findAll();
    }

    @Override
    public Books findById(String id) {
        return booksRepository.findById(id).orElse(new Books());
    }

    @Override
    public Books save(Books books) {
        return booksRepository.save(books);
    }

    @Override
    public Books update(Books books) {
        return booksRepository.save(books);
    }

    @Override
    public void delete(String id) {
        booksRepository.deleteById(id);
    }

    @Override
    public List<Books> findBooksByName(String name) {
        return booksRepository.findByName(name);
    }

    @Override
    public List<Books> findBooksBetweenDate(Date date1, Date date2) {
        return booksRepository.findBetweenPublishDate(date1,date2);
    }

//    @Override
//    public List<Books> fullTextSearchByName(String name) {
//        TextCriteria criteria = TextCriteria
//                .forDefaultLanguage()
//                .matchingPhrase(name);
//        Query query = TextQuery.queryText(criteria).sortByScore();
//        List<Books> booksList = mongoTemplate.find(query,Books.class);
//        return booksList;
//
//    }

}
