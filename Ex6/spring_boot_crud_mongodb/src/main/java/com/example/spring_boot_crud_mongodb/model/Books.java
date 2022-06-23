package com.example.spring_boot_crud_mongodb.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Getter
@Setter
@Document("books")

public class Books {

    @Id
    private String id;

//    @TextIndexed(weight = 4)
    @Field(name = "name")
    private String name;

    @Field(name = "author")
//    @TextIndexed(weight = 3)
    private String author;

    @Field(name = "publish_date")
    private Date publishDate;

    @Field(name = "description")
    private String description;
}
