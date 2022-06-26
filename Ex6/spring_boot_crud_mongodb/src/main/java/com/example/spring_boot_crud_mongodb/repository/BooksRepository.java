package com.example.spring_boot_crud_mongodb.repository;

import com.example.spring_boot_crud_mongodb.model.Books;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends MongoRepository<Books, String> {
//    @Query("SELECT b FROM Books b WHERE b.name = :name AND b.author = :author")
//    Optional<Books> findByNameAndAuthor(@Param("name") String name,@Param("author") String author);


    @Query("{'name' : ?0 }")
    List<Books> findByName(String name);

    @Query("{$and :  [{publishDate: {$gt: ?0}},{publishDate: {$lt: ?1}}]}")
    List<Books> findBetweenPublishDate(Date date1, Date date2);


}
