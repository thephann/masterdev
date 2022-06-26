package com.example.spring_boot_crud_mongodb.controller;

import com.example.spring_boot_crud_mongodb.model.Books;
import com.example.spring_boot_crud_mongodb.service.BooksService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Books")
@AllArgsConstructor
public class BooksController {

    private final BooksService booksService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Books>> findAllBooks() {
        List<Books> booksList = booksService.findAll();
        return ResponseEntity.ok().body(booksList);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Books> findBookById(@PathVariable String id) {
        Books books = booksService.findById(id);
        return ResponseEntity.ok().body(books);
    }

    @PutMapping
    public ResponseEntity<Books> createBook(@RequestBody Books books) {
        Books books1 = booksService.create(books);
        return ResponseEntity.ok().body(books1);
    }

    @PostMapping
    public ResponseEntity<Books> updateBook(@RequestBody Books books) {
        Books books2 = booksService.update(books);
        return ResponseEntity.ok().body(books2);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable String id) {
        booksService.delete(id);
        return ResponseEntity.ok("Delete successfull");
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Books>> getBooksByName(@PathVariable String name) {
        List<Books> booksList = booksService.findBooksByName(name);
        return ResponseEntity.ok().body(booksList);
    }

    @GetMapping("/findByDate/{startDate}/{endDate}")
    public ResponseEntity<List<Books>> getBooksByPublishDate(@PathVariable String endDate, @PathVariable String startDate) {
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = new SimpleDateFormat("dd-MM-yyyy").parse(startDate);
            date2 = new SimpleDateFormat("dd-MM-yyyy").parse(endDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        List<Books> booksList1 = booksService.findBooksBetweenDate(date1,date2);

        return ResponseEntity.ok().body(booksList1);
    }

    @GetMapping("/fullText/{name}")
    public ResponseEntity<List<Books>> getBooksByFullText(@PathVariable String name) {
        List<Books> booksList2 = booksService.fullTextSearchByName(name);
        return ResponseEntity.ok().body(booksList2);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Books>> getAllPage(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "20") Integer pageSize
    ) {
        Page<Books> books = booksService.findByPage(pageNo,pageSize);
        return ResponseEntity.ok().body(books);
    }

//    @GetMapping("/page")
//    public ResponseEntity<Page<Books>> getBookByNameEachPage(
//            @RequestParam(defaultValue = "0") Integer pageNo,
//            @RequestParam(defaultValue = "20") Integer pageSize,
//            @RequestParam(defaultValue = "hehe") String name
//    ) {
//        Page<Books> booksPage = booksService.findBookByName(pageNo,pageSize,name);
//        return  ResponseEntity.ok().body(booksPage);
//    }



}
